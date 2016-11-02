package com.someday.member;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.someday.member.MemberService;
import com.someday.member.ZipcodeModel;
import com.someday.validator.MemberValidator;
import com.someday.member.MemberModel;

@Controller
@RequestMapping("/member")
public class MemberController {

	Logger log = Logger.getLogger(this.getClass());
	
	 @Resource(name="memberService")
	   private MemberService memberService;
	 
	   private List<ZipcodeModel> zipcodeList = new ArrayList<ZipcodeModel>();
	   private MemberModel memberModel = new MemberModel();
	   ModelAndView mav = new ModelAndView();

	  /*ȸ��������*/
	@RequestMapping("/member")
	public ModelAndView memberStep1(){
	   
		 ModelAndView mav = new ModelAndView();
		  
	   mav.setViewName("member/join");
	   return mav;
	}
	/*ȸ������*/
	@RequestMapping("/memberjoin")
	public ModelAndView memberJoin(@ModelAttribute("member") MemberModel member,
								   BindingResult result) {
		
		System.out.println(member.getId());
		System.out.println(member.getPass());
		System.out.println(member.getName());
		System.out.println(member.getEmail());
		System.out.println(member.getNick());
		System.out.println(member.getArea());
		System.out.println(member.getAddr1());
		System.out.println(member.getAddr2());
		System.out.println(member.getPhone());
		System.out.println(member.getNum1());	
		System.out.println(member.getNum2());
		System.out.println(member.getIntro());
		System.out.println(member.getZipcode());
		
/*		new MemberValidator().validate(member, result);
        
        // ������������ ȸ������������ �Ѿ
        if(result.hasErrors()) {
       	 
       	ModelAndView mav = new ModelAndView();
       	 System.out.println(member.getEmail());
           mav.setViewName("join");
           return mav;
        }*/
        try{
        	member.setPhone(member.getPhone3()+"-"+member.getPhone()+"-"+member.getPhone2()); //���ѹ� ��ġ��
        	
        	if(member.getEmail2() != null){	//�̸��� �ּҰ� ���� �ƴϸ� ����
        		member.setEmail(member.getEmail()+"@"+member.getEmail2());
			} else { // �̸��� �ּҰ� ���Ͻ� ����
				member.setEmail(member.getEmail()+"@"+member.getSelectEmail());
			}
			memberService.insertMember(member);
			
			/*���� ���� ���*/
			memberService.AgeGender(member);
			
			mav.addObject("member", member);
			mav.setViewName("member/join");
			return mav;
        	} catch (DuplicateKeyException e) {
                // db���� id�� ���������� unique�� �ٲ�� ������ �ߺ��� ���̵�� �����Ϸ��ϸ� DuplicateKeyException�� �߰Եǰ�
                // ����ó���� properties���Ͽ� ��ϵ� "invalid"�� ������ ������ ����� ȸ������������ ���ư�������.
                // ���̵� �ߺ��˻�
                result.reject("invalid", null);
                mav.setViewName("member/join");
                return mav;
        	}
	}
	
	  @RequestMapping(value="/zipcodeCheckForm")
      public ModelAndView zipcodeCheckForm( HttpServletRequest req) throws Exception{
         ModelAndView mv = new ModelAndView();

             mv.setViewName("check/zipcodeCheck");
          return mv;
   }
      /*ȸ�����Խ� �����ȣ �˻� ����*/ 
      @RequestMapping(value="/zipcodeCheck")
      public ModelAndView zipcodeCheck( @ModelAttribute ZipcodeModel zipcodeModel ,HttpServletRequest req) throws Exception{
         
    	 ModelAndView mv = new ModelAndView();
         
         int chk=100;

         zipcodeList = memberService.zipcodeCheck(zipcodeModel);
             
         mv.addObject("zipcode", zipcodeList);
                
         if(zipcodeList.size() == 0){
        	 chk =0;
         }else{
        	 chk=1;
         }
             mv.addObject("chk",chk);
             mv.setViewName("check/zipcodeCheck");
             return mv;
          }   
      //���̵� ã�� 
      @RequestMapping(value="/inputIdCheck")
      public ModelAndView inputIdCheck(String id) throws Exception {
    	  
    	  System.out.println(id);
    	  ModelAndView mv = new ModelAndView();
    	  
    	  int ick = 100;
    	  
    	  memberModel = memberService.inputIdCheck(id);
    	  if(memberModel == null){
    		  ick= 0;
    	  } else {
    		  ick = 1;
    	  }
    	  
    	  mv.addObject("member",memberModel); // ���� �����
    	  mv.addObject("ick",ick); // ���̵� �ֳ�����
    	  mv.setViewName("check/inputIdCheck");
    	  return mv;
    	  
    	  
      }

}