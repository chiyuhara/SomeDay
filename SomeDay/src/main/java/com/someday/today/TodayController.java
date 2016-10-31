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


public class TodayController {
	
	@Resource(name = "todayService")
	private TodayService todayService;
	
	//오늘의 인연 페이지
	@RequestMapping(value = "/today/Today")
	public ModelAndView Today(HttpServletRequest request, TodayModel todayModel)
			throws UnsupportedEncodingException {
		System.out.println("오늘의 인연");
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}

}
