package com.someday.today;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodayController {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "todayService")
	private TodayService todayService;

	@Resource(name = "memberService")
	private MemberService memberService;

	private int idx; // 세션으로 받아와야될 IDX (회원정보)

	// 오늘의 인연 페이지
	@RequestMapping(value = "/today")
	public ModelAndView tody(HttpServletRequest request) {
		System.out.println("오늘의 인연");
		ModelAndView mav = new ModelAndView();

		MemberModel my = new MemberModel();
		MemberModel target = new MemberModel();

		int idx = 362; // 세션으로 받아온 임의의 회원 IDX
		System.out.println("로그인된 IDX : " + idx);

		// 로그인된 회원이 여자인지 남자인지
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// 로그인한 계정이 여자일경우
		if (myGenderfemale != null) {
			System.out.println("로그인한계정은 여자");
			// 여자가 속한 미팅찾기
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());
			System.out.println("미팅 idx : " + female_today.getIdx());
			System.out.println("상대방 남자 idx : " + female_today.getMale_idx());
			my = memberService.my(idx);
			target = memberService.target(female_today.getMale_idx());
			System.out.println("상대방 남자 이름 : " + target.getName());
		}
		if (myGendermale != null) {
			System.out.println("로그인한계정은 남자");
		}

		mav.addObject("my", my);
		mav.addObject("target", target);
		mav.setViewName("today");

		return mav;
	}

	// 오늘의 인연 페이지 상세보기
	@RequestMapping(value = "/today/TodayView")
	public ModelAndView TodayView(HttpServletRequest request) {
		System.out.println("오늘의인연 상세보기");
		ModelAndView mav = new ModelAndView();

		MemberModel my = new MemberModel();
		MemberModel target = new MemberModel();

		int idx = 362; // 세션으로 받아와야될 나의 idx

		// 로그인된 회원이 여자인지 남자인지
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// 로그인한 계정이 여자일경우
		if (myGenderfemale != null) {
			System.out.println("로그인한계정은 여자");
			// 여자가 속한 미팅찾기
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());
			System.out.println("미팅 idx : " + female_today.getIdx());
			System.out.println("상대방 남자 idx : " + female_today.getMale_idx());
			my = memberService.my(idx);
			target = memberService.target(female_today.getMale_idx());
			System.out.println("상대방 남자 이름 : " + target.getName());
		}
		if (myGendermale != null) {
			System.out.println("로그인한계정은 남자");
		}

		return mav;
	}

}
