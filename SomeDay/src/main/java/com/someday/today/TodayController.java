package com.someday.today;

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
import com.someday.today.TodayMemberModel;

@Controller
public class TodayController {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "todayService")
	private TodayService todayService;

	@Resource(name = "memberService")
	private MemberService memberService;

	// ������ �ο� ������

	@RequestMapping(value = "/today")
	public ModelAndView tody(HttpServletRequest request, HttpSession session) {
		System.out.println("������ �ο�");
		int checkId;
		ModelAndView mav = new ModelAndView();

		MemberModel my = new MemberModel();
		MemberModel target = new MemberModel();

		if (session.getAttribute("session_member_idx") != null) {
		int idx = (int) session.getAttribute("session_member_idx");
		System.out.println("�α��ε� IDX : " + idx);

		// �α��ε� ȸ���� �������� ��������
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// �α����� ������ �����ϰ��
		if (myGenderfemale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ����ã��
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());

			if (female_today != null) {
				System.out.println("���� idx : " + female_today.getIdx());
				System.out.println("���� ���� idx : " + female_today.getMale_idx());
				my = memberService.my(idx);
				target = memberService.target(female_today.getMale_idx());
				System.out.println("���� ���� �̸� : " + target.getName());
				mav.addObject("my", my);
				mav.addObject("target", target);
				mav.setViewName("today");
			} else {
				my = memberService.my(idx);
				mav.addObject("my", my);
				mav.addObject("target", null);
				mav.setViewName("today");
			}

		}
		// �α����� ������ �����ϰ��
		if (myGendermale != null) {
			System.out.println("�α����Ѱ����� ����");
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());

			if (male_today != null) {
				System.out.println("���� idx : " + male_today.getIdx());
				System.out.println("���� ���� idx : " + male_today.getFemale_idx());
				my = memberService.my(idx);
				target = memberService.target(male_today.getFemale_idx());
				System.out.println("���� ���� �̸� : " + target.getName());
				mav.addObject("my", my);
				mav.addObject("target", target);
				mav.setViewName("today");
			} else {
				my = memberService.my(idx);
				mav.addObject("my", my);
				mav.addObject("target", null);
				mav.setViewName("today");
			}
		}
		return mav;
	}
      	checkId = 1;
      	mav.addObject("checkId",checkId);
      	mav.setViewName("member/loginSuccess");
		return mav;
	}

	// ������ �ο� ������ �󼼺���
	@RequestMapping(value = "/today/TodayView")
	public ModelAndView TodayView(HttpServletRequest request, HttpSession session) {
		System.out.println("�������ο� �󼼺���");
		ModelAndView mav = new ModelAndView();

		TodayMemberModel targetfemale = new TodayMemberModel();
		TodayMemberModel targetmale = new TodayMemberModel();

		int idx = (int) session.getAttribute("session_member_idx");

		// �α��ε� ȸ���� �������� ��������
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// �α����� ������ �����ϰ��
		if (myGenderfemale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ����ã��
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());
			if (female_today != null) {
				System.out.println("���� idx : " + female_today.getIdx());
				System.out.println("���� ���� idx : " + female_today.getMale_idx());

				// ������ ������ ���� ���� ������ ������ ������
				targetmale = memberService.targetmale(female_today.getMale_idx());
				System.out.println("���� ���� �̸� : " + targetmale.getName());

				mav.addObject("targetmale", targetmale);
				mav.addObject("meeting", female_today);
				mav.setViewName("todayView");
			} else {

				mav.addObject("targetmale", null);
				mav.setViewName("todayView");
			}
		}
		// �α����� ������ �����ϰ��
		if (myGendermale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ����ã��
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());
			if (male_today != null) {
				System.out.println("���� idx : " + male_today.getIdx());
				System.out.println("���� ���� idx : " + male_today.getFemale_idx());

				// ������ ������ ���� ���� ������ ������ ������
				targetfemale = memberService.targetfemale(male_today.getFemale_idx());
				System.out.println("���� ���� �̸� : " + targetfemale.getName());

				mav.addObject("targetfemale", targetfemale);
				mav.addObject("meeting", male_today);
				mav.setViewName("todayView");
			} else {

				mav.addObject("targetfemale", null);
				mav.setViewName("todayView");
			}
		}

		return mav;
	}

	// ������
	@RequestMapping(value = "/today/Like")
	public ModelAndView Like(HttpServletRequest request, HttpSession session) {
		System.out.println("���ƿ� ����");

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
			todayService.female_like(female_today.getIdx());

		}
		// �α����� ������ �����ϰ��
		if (myGendermale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ���� ����ã��
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());
			todayService.male_like(male_today.getIdx());
		}

		mav.setViewName("redirect:TodayView");

		return mav;
	}

	// ����
	@RequestMapping(value = "/today/Score", method = RequestMethod.POST)
	public ModelAndView Score(TodayModel todayModel, HttpServletRequest request, HttpSession session) {
		System.out.println("����");
		ModelAndView mav = new ModelAndView();

		System.out.println("���� :" + request.getParameter("score"));
		int idx = (int) session.getAttribute("session_member_idx");
		int score = Integer.parseInt(request.getParameter("score"));
		System.out.println("����: " + score);

		// �α��ε� ȸ���� �������� ��������
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// �α����� ������ �����ϰ��
		if (myGenderfemale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ���� ����ã��
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());
			todayModel.setIdx(female_today.getIdx());
			todayModel.setScore(score);
			todayService.female_score(todayModel);
		}

		// �α����� ������ �����ϰ��
		if (myGendermale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ���� ����ã��
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());
			todayModel.setIdx(male_today.getIdx());
			todayModel.setScore(score);
			todayService.male_score(todayModel);
		}

		mav.setViewName("redirect:/today/TodayView");

		return mav;
	}

	// ����
	@RequestMapping(value = "/today/Message", method = RequestMethod.POST)
	public ModelAndView Message(TodayModel todayModel, HttpServletRequest request, HttpSession session) {
		System.out.println("����");
		ModelAndView mav = new ModelAndView();

		System.out.println("���� ���� :" + request.getParameter("message"));
		int idx = (int) session.getAttribute("session_member_idx");
		String message = request.getParameter("message");
		// �α��ε� ȸ���� �������� ��������
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// �α����� ������ �����ϰ��
		if (myGenderfemale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ���� ����ã��
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());
			// �������� ���
			todayModel.setIdx(female_today.getIdx());
			todayModel.setMale_msg(message);
			todayService.female_message(todayModel);
		}

		// �α����� ������ �����ϰ��
		if (myGendermale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ���� ����ã��
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());
			// �������� ���
			todayModel.setIdx(male_today.getIdx());
			todayModel.setFemale_msg(message);
			todayService.male_message(todayModel);
		}

		mav.setViewName("redirect:/today/TodayView");

		return mav;
	}
}
