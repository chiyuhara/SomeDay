package com.someday.today;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TodayController {
	
	@Resource(name = "todayService")
	private TodayService todayService;
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	private int idx; //세션으로 받아와야될 IDX (회원정보)
	
	//오늘의 인연 페이지
	@RequestMapping(value = "/today")
	public ModelAndView noticeView(HttpServletRequest request) {
		System.out.println("오늘의 인연");
		ModelAndView mav = new ModelAndView();
		
		int idx = 362; //세션으로 받아온 임의의 회원 IDX
		System.out.println("로그인된 IDX : " + idx);
		
		//로그인된 회원이 여자인지 남자인지
		MemberModel myGenderfemale =  memberService.myGenderfemale(idx);
		MemberModel myGendermale =  memberService.myGendermale(idx);
		
		if(myGenderfemale != null){
			System.out.println("로그인한계정은 여자");
		}
		if(myGendermale != null){
			System.out.println("로그인한계정은 남자");
		}
		
		mav.setViewName("today");
	
		return mav;
	}

}
