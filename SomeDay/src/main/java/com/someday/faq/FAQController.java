package com.someday.faq;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FAQController {
	
	@RequestMapping(value = "/faq/FAQ")
	public ModelAndView faq(HttpServletRequest request) throws UnsupportedEncodingException {

		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("faq");
		
		return mav;
	}
	
	@RequestMapping("/guide")
	public ModelAndView guide(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("guide");
		return mav;
	}

}
