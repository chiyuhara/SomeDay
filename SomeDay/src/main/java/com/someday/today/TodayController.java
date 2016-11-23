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

	// 오늘의 인연 페이지

	@RequestMapping(value = "/today")
	public ModelAndView tody(HttpServletRequest request, HttpSession session) {
		System.out.println("오늘의 인연");
		int checkId;
		ModelAndView mav = new ModelAndView();

		MemberModel my = new MemberModel();
		MemberModel target = new MemberModel();

		if (session.getAttribute("session_member_idx") != null) {
		int idx = (int) session.getAttribute("session_member_idx");
		System.out.println("로그인된 IDX : " + idx);

		// 로그인된 회원이 여자인지 남자인지
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// 로그인한 계정이 여자일경우
		if (myGenderfemale != null) {
			System.out.println("로그인한계정은 여자");
			// 여자가 속한 미팅찾기
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());

			if (female_today != null) {
				System.out.println("미팅 idx : " + female_today.getIdx());
				System.out.println("상대방 남자 idx : " + female_today.getMale_idx());
				my = memberService.my(idx);
				target = memberService.target(female_today.getMale_idx());
				System.out.println("상대방 남자 이름 : " + target.getName());
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
		// 로그인한 계정이 남자일경우
		if (myGendermale != null) {
			System.out.println("로그인한계정은 남자");
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());

			if (male_today != null) {
				System.out.println("미팅 idx : " + male_today.getIdx());
				System.out.println("상대방 여자 idx : " + male_today.getFemale_idx());
				my = memberService.my(idx);
				target = memberService.target(male_today.getFemale_idx());
				System.out.println("상대방 여자 이름 : " + target.getName());
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

	// 오늘의 인연 페이지 상세보기
	@RequestMapping(value = "/today/TodayView")
	public ModelAndView TodayView(HttpServletRequest request, HttpSession session) {
		System.out.println("오늘의인연 상세보기");
		ModelAndView mav = new ModelAndView();

		TodayMemberModel targetfemale = new TodayMemberModel();
		TodayMemberModel targetmale = new TodayMemberModel();

		int idx = (int) session.getAttribute("session_member_idx");

		// 로그인된 회원이 여자인지 남자인지
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// 로그인한 계정이 여자일경우
		if (myGenderfemale != null) {
			System.out.println("로그인한계정은 여자");
			// 여자가 속한 미팅찾기
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());
			if (female_today != null) {
				System.out.println("미팅 idx : " + female_today.getIdx());
				System.out.println("상대방 남자 idx : " + female_today.getMale_idx());

				// 남자의 정보와 오늘 미팅 남자의 점수를 가져옴
				targetmale = memberService.targetmale(female_today.getMale_idx());
				System.out.println("상대방 남자 이름 : " + targetmale.getName());

				mav.addObject("targetmale", targetmale);
				mav.addObject("meeting", female_today);
				mav.setViewName("todayView");
			} else {

				mav.addObject("targetmale", null);
				mav.setViewName("todayView");
			}
		}
		// 로그인한 계정이 남자일경우
		if (myGendermale != null) {
			System.out.println("로그인한계정은 남자");
			// 남자가 속한 미팅찾기
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());
			if (male_today != null) {
				System.out.println("미팅 idx : " + male_today.getIdx());
				System.out.println("상대방 여자 idx : " + male_today.getFemale_idx());

				// 여자의 정보와 오늘 미팅 여자의 점수를 가져옴
				targetfemale = memberService.targetfemale(male_today.getFemale_idx());
				System.out.println("상대방 여자 이름 : " + targetfemale.getName());

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

	// 좋아융
	@RequestMapping(value = "/today/Like")
	public ModelAndView Like(HttpServletRequest request, HttpSession session) {
		System.out.println("좋아요 실행");

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
			todayService.female_like(female_today.getIdx());

		}
		// 로그인한 계정이 남자일경우
		if (myGendermale != null) {
			System.out.println("로그인한계정은 남자");
			// 남자가 속한 오늘 미팅찾기
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());
			todayService.male_like(male_today.getIdx());
		}

		mav.setViewName("redirect:TodayView");

		return mav;
	}

	// 평점
	@RequestMapping(value = "/today/Score", method = RequestMethod.POST)
	public ModelAndView Score(TodayModel todayModel, HttpServletRequest request, HttpSession session) {
		System.out.println("평점");
		ModelAndView mav = new ModelAndView();

		System.out.println("평점 :" + request.getParameter("score"));
		int idx = (int) session.getAttribute("session_member_idx");
		int score = Integer.parseInt(request.getParameter("score"));
		System.out.println("점수: " + score);

		// 로그인된 회원이 여자인지 남자인지
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// 로그인한 계정이 여자일경우
		if (myGenderfemale != null) {
			System.out.println("로그인한계정은 여자");
			// 여자가 속한 오늘 미팅찾기
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());
			todayModel.setIdx(female_today.getIdx());
			todayModel.setScore(score);
			todayService.female_score(todayModel);
		}

		// 로그인한 계정이 남자일경우
		if (myGendermale != null) {
			System.out.println("로그인한계정은 남자");
			// 남자가 속한 오늘 미팅찾기
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());
			todayModel.setIdx(male_today.getIdx());
			todayModel.setScore(score);
			todayService.male_score(todayModel);
		}

		mav.setViewName("redirect:/today/TodayView");

		return mav;
	}

	// 쪽지
	@RequestMapping(value = "/today/Message", method = RequestMethod.POST)
	public ModelAndView Message(TodayModel todayModel, HttpServletRequest request, HttpSession session) {
		System.out.println("쪽지");
		ModelAndView mav = new ModelAndView();

		System.out.println("쪽지 내용 :" + request.getParameter("message"));
		int idx = (int) session.getAttribute("session_member_idx");
		String message = request.getParameter("message");
		// 로그인된 회원이 여자인지 남자인지
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// 로그인한 계정이 여자일경우
		if (myGenderfemale != null) {
			System.out.println("로그인한계정은 여자");
			// 여자가 속한 오늘 미팅찾기
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());
			// 쪽지내용 등록
			todayModel.setIdx(female_today.getIdx());
			todayModel.setMale_msg(message);
			todayService.female_message(todayModel);
		}

		// 로그인한 계정이 남자일경우
		if (myGendermale != null) {
			System.out.println("로그인한계정은 남자");
			// 남자가 속한 오늘 미팅찾기
			TodayModel male_today = todayService.male_today(myGendermale.getIdx());
			// 쪽지내용 등록
			todayModel.setIdx(male_today.getIdx());
			todayModel.setFemale_msg(message);
			todayService.male_message(todayModel);
		}

		mav.setViewName("redirect:/today/TodayView");

		return mav;
	}
}
