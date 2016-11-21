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

	// 채팅 보기
	@RequestMapping(value = "/today/Chat")
	public ModelAndView Chat(TodayModel todayModel, ChatModel chatModel, HttpServletRequest request,
			HttpSession session) {
		System.out.println("채팅목록");
		ModelAndView mav = new ModelAndView();

		int idx = (int) session.getAttribute("session_member_idx");
		// 로그인된 회원이 여자인지 남자인지
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// 로그인한 계정이 여자일경우
		if (myGenderfemale != null) {
			System.out.println("로그인한계정은 여자");
			// 여자가 속한 오늘 미팅찾기
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());
			System.out.println("여자가 속한 오늘의 미팅 idx: " + female_today.getIdx());

			int couple_idx = female_today.getIdx();
			List<ChatModel> chatList;
			chatList = chatService.chatList(couple_idx);
			mav.addObject("chatList", chatList);
			mav.setViewName("/today/Chat");

		}

		// 로그인한 계정이 남자일경우
		if (myGendermale != null) {
			System.out.println("로그인한계정은 남자");
			// 남자가 속한 오늘 미팅찾기
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());
			System.out.println("남자가 속한 오늘의 미팅 idx: " + male_today.getIdx());

			int couple_idx = male_today.getIdx();
			List<ChatModel> chatList;
			chatList = chatService.chatList(couple_idx);
			mav.addObject("chatList", chatList);
			mav.setViewName("/today/Chat");
		}
		return mav;
	}

	// 채팅 글쓰기
	@RequestMapping(value = "/today/ChatSend", method = RequestMethod.POST)
	public ModelAndView ChatSend(ChatModel chatModel, HttpServletRequest request, HttpSession session) {
		System.out.println("채팅 글쓰기");

		ModelAndView mav = new ModelAndView();

		int idx = (int) session.getAttribute("session_member_idx");
		// 로그인된 회원이 여자인지 남자인지
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// 로그인한 계정이 여자일경우
		if (myGenderfemale != null) {
			System.out.println("로그인한계정은 여자");
			// 여자가 속한 오늘 미팅찾기
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());
			System.out.println("여자가 속한 오늘의 미팅 idx: " + female_today.getIdx());

			chatModel.setTo_idx(idx);
			chatModel.setFrom_idx(female_today.getFemale_idx());
			chatModel.setCouple_idx(female_today.getIdx());
			chatModel.setContent(request.getParameter("content"));
			chatService.chatSend(chatModel);
		}

		// 로그인한 계정이 남자일경우
		if (myGendermale != null) {
			System.out.println("로그인한계정은 남자");
			// 남자가 속한 오늘 미팅찾기
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());
			System.out.println("남자가 속한 오늘의 미팅 idx: " + male_today.getIdx());
			System.out.println("작성한내용 :" + request.getParameter("content"));
			System.out.println("상대방 여자의 idx : " + male_today.getFemale_idx());
			
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
