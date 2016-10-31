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
	
	private int idx; //�������� �޾ƿ;ߵ� IDX (ȸ������)
	
	//������ �ο� ������
	@RequestMapping(value = "/today")
	public ModelAndView noticeView(HttpServletRequest request) {
		System.out.println("������ �ο�");
		ModelAndView mav = new ModelAndView();
		
		int idx = 362; //�������� �޾ƿ� ������ ȸ�� IDX
		System.out.println("�α��ε� IDX : " + idx);
		
		//�α��ε� ȸ���� �������� ��������
		MemberModel myGenderfemale =  memberService.myGenderfemale(idx);
		MemberModel myGendermale =  memberService.myGendermale(idx);
		
		if(myGenderfemale != null){
			System.out.println("�α����Ѱ����� ����");
		}
		if(myGendermale != null){
			System.out.println("�α����Ѱ����� ����");
		}
		
		mav.setViewName("today");
	
		return mav;
	}

}
