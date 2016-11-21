package com.someday.chat;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.someday.member.MemberModel;
import com.someday.member.MemberService;
import com.someday.notice.NoticecommModel;
import com.someday.today.TodayModel;
import com.someday.today.TodayService;

@Controller
public class ChatController {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "chatService")
	private ChatService chatService;

	@Resource(name = "memberService")
	private MemberService memberService;

	@Resource(name = "todayService")
	private TodayService todayService;

	// ä�� ����
	@RequestMapping(value = "/today/Chat")
	public ModelAndView Chat(TodayModel todayModel, ChatModel chatModel, HttpServletRequest request,
			HttpSession session) {
		System.out.println("ä�ø��");
		ModelAndView mav = new ModelAndView();

		int idx = (int) session.getAttribute("session_member_idx");
		// �α��ε� ȸ���� �������� ��������
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// �α����� ������ �����ϰ��
		if (myGenderfemale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ���� ����ã��
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());
			System.out.println("���ڰ� ���� ������ ���� idx: " + female_today.getIdx());

			int couple_idx = female_today.getIdx();
			List<ChatModel> chatList;
			chatList = chatService.chatList(couple_idx);
			mav.addObject("chatList", chatList);
			mav.setViewName("/today/Chat");

		}

		// �α����� ������ �����ϰ��
		if (myGendermale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ���� ����ã��
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());
			System.out.println("���ڰ� ���� ������ ���� idx: " + male_today.getIdx());

			int couple_idx = male_today.getIdx();
			List<ChatModel> chatList;
			chatList = chatService.chatList(couple_idx);
			mav.addObject("chatList", chatList);
			mav.setViewName("/today/Chat");
		}
		return mav;
	}

	// ä�� �۾���
	@RequestMapping(value = "/today/ChatSend", method = RequestMethod.POST)
	public ModelAndView ChatSend(ChatModel chatModel, HttpServletRequest request, HttpSession session) {
		System.out.println("ä�� �۾���");

		ModelAndView mav = new ModelAndView();

		int idx = (int) session.getAttribute("session_member_idx");
		// �α��ε� ȸ���� �������� ��������
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// �α����� ������ �����ϰ��
		if (myGenderfemale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ���� ����ã��
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());
			System.out.println("���ڰ� ���� ������ ���� idx: " + female_today.getIdx());

			chatModel.setTo_idx(idx);
			chatModel.setFrom_idx(female_today.getFemale_idx());
			chatModel.setCouple_idx(female_today.getIdx());
			chatModel.setContent(request.getParameter("content"));
			chatService.chatSend(chatModel);
		}

		// �α����� ������ �����ϰ��
		if (myGendermale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ���� ����ã��
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());
			System.out.println("���ڰ� ���� ������ ���� idx: " + male_today.getIdx());
			System.out.println("�ۼ��ѳ��� :" + request.getParameter("content"));
			System.out.println("���� ������ idx : " + male_today.getFemale_idx());
			
			chatModel.setTo_idx(idx);
			chatModel.setFrom_idx(male_today.getFemale_idx());
			chatModel.setCouple_idx(male_today.getIdx());
			chatModel.setContent(request.getParameter("content"));
			chatService.chatSend(chatModel);
		}

		mav.setViewName("redirect:/today/Chat");
		return mav;
	}
}
