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
	
	//������ �ο� ������
	@RequestMapping(value = "/today/Today")
	public ModelAndView Today(HttpServletRequest request, TodayModel todayModel)
			throws UnsupportedEncodingException {
		System.out.println("������ �ο�");
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}

}
