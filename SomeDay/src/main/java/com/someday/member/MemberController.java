package com.someday.member;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import com.someday.member.MemberService;
import com.someday.member.ZipcodeModel;
import com.someday.notice.NoticeModel;
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
		@RequestMapping("/memberForm")
		public ModelAndView memberStep1(){
		   
			 ModelAndView mav = new ModelAndView();
			  
		   mav.setViewName("memberForm");
		   return mav;
		}
		/*ȸ������*/
		@RequestMapping(value="/memberjoin", method=RequestMethod.POST)
		public ModelAndView memberJoin(MemberModel member, MultipartHttpServletRequest request ) throws Exception {
			
	        	member.setPhone(member.getPhone3()+"-"+member.getPhone()+"-"+member.getPhone2()); //���ѹ� ��ġ��
	        	
	        	if(member.getEmail2() != null){	//�̸��� �ּҰ� ���� �ƴϸ� ����
	        		member.setEmail(member.getEmail()+"@"+member.getEmail2());
				} else { // �̸��� �ּҰ� ���Ͻ� ����
					member.setEmail(member.getEmail()+"@"+member.getSelectEmail());
				}
				memberService.insertMember(member);
				
				/*���� ���� ���*/
				memberService.AgeGender(member);
				
				/*���̵�� IDXã��*/
				MemberModel idx = (MemberModel)memberService.Idx(member);
				member.setIdx(idx.getIdx());
				/*���� ���ε�*/
				memberService.UpdateFile(member, request);
				
				mav.addObject("member", member);
				mav.setViewName("main");
				return mav;
	      
	        }


   //�α��� ��
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView noticeForm(HttpServletRequest request) {
		System.out.println("�α��� �� ����");
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberModel", new MemberModel());
		mav.setViewName("loginForm");
		return mav;
	}

   //�α��ε��� �� ���� ����
   @RequestMapping(value="/login", method=RequestMethod.POST)
   public ModelAndView memberLogin(HttpServletRequest request, MemberModel mem) {

      MemberModel result = memberService.memberLogin(mem);
      //ModelAndView mav = new ModelAndView();
      
      if(result!=null) {
         
         HttpSession session = request.getSession();         
         
         session.setAttribute("mem", result);
         session.setAttribute("session_member_id", result.getId());
         session.setAttribute("session_member_name", result.getName());
         session.setAttribute("session_member_idx", result.getIdx());
      
         session.setAttribute("TOKEN_SAVE_CHECK", "TRUE");
                 
         mav.setViewName("main");
         mav.setViewName("redirect:/");
         return mav;
      }
      
      //System.out.println("�α��� ����");         
      mav.setViewName("main");
      return mav;
         
   }
   
   

   //�α׾ƿ�
   @RequestMapping("/logout")
   public ModelAndView memberLogout(HttpServletRequest request, MemberModel mem){
      
      HttpSession session = request.getSession(false);
      
      if(session!=null){
         session.invalidate();
      }
      mav.setViewName("main");
      mav.setViewName("redirect:/");
      return mav;
   }

   	//���̵� ã�� ��
  	@RequestMapping(value = "/memberIdFind", method = RequestMethod.GET)
  	public ModelAndView memberFindForm() {
  		mav.setViewName("member/idFind");
  		return mav;
  	}
  	
  	//���̵� ã��
  	@RequestMapping(value = "/memberIdFind", method = RequestMethod.POST)
  	public ModelAndView memberIdFind(@ModelAttribute("member") MemberModel member, HttpServletRequest request) {

  		int memberFindChk;

  		member = memberService.idFindByName(member);
  		if (member == null) {
  			memberFindChk = 0; // ���ԵǾ� �ִ� ��� X;
  			mav.addObject("memberFindChk", memberFindChk);
  			mav.setViewName("member/idFindError");
  			return mav;

  		} else {
  			if (member.getName().equals(member.getName()) && member.getNum1().equals(member.getNum1()) && member.getNum2().equals(member.getNum2())) {
  				memberFindChk = 1; // ȸ�����ԵǾ� ����, �г��� ��ġ
  				mav.addObject("member", member);
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("member/idFindOk");
  				return mav;
  			} else {
  				memberFindChk = -1; // �̸� , �г��� Ʋ��
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("member/idFindError");
  				return mav;
  			} 
  		}
  	}

  	// ��й�ȣã��
  	@RequestMapping(value = "/memberPwFind", method = RequestMethod.GET)
  	public ModelAndView memberPwFindForm() {
  		mav.setViewName("member/pwFind");
  		return mav;
  	}

  	@RequestMapping(value = "/memberPwFind", method = RequestMethod.POST)
  	public ModelAndView memberPwFind(@ModelAttribute("member") MemberModel member, HttpServletRequest request) {

  		int memberFindChk;


  		member = memberService.pwFindById(member);
  		
  		if (member == null) {
  			memberFindChk = 0; // ���ԵǾ� �ִ� ��� X;
  			mav.addObject("memberFindChk", memberFindChk);
  			mav.setViewName("member/idFindError");
  			return mav;

  		} else {
  			
  			if (member.getName().equals(member.getName()) && member.getId().equals(member.getId()) && member.getNum1().equals(member.getNum1()) && member.getNum2().equals(member.getNum2())) {
  				memberFindChk = 1; // ȸ�����ԵǾ� ����, �г��� ��ġ
  				mav.addObject("member", member);
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("member/pwFindOk");
  				return mav;
  			} else {
  				memberFindChk = -1; // �̸� , �г��� Ʋ��
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("member/idFindError");
  				return mav;
  			}
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

      //���̵� �ߺ�üũ 
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
      
        //ȸ����������
    	@RequestMapping("/memberModify")
    	public ModelAndView memberModify(@ModelAttribute("member") MemberModel member, BindingResult result,
    			HttpSession session) {
    		session.getAttribute("session_member_id");

    		if (session.getAttribute("session_member_id") != null) {
    			String id = (String) session.getAttribute("session_member_id");
    			member = memberService.getMember(id);

    			mav.addObject("member", member);
    			mav.setViewName("memberModify");
    			return mav;
    		} else {

    			mav.setViewName("loginConfirm");
    			return mav;
    		}
    	}
        
    	//ȸ������ �����Ϸ�
    	@RequestMapping("/memberModifyEnd")
    	public ModelAndView memberModifyEnd(@ModelAttribute("member") MemberModel member, BindingResult result) {
    		// Validation Binding
    		/*new MemberValidator().validate(member, result);*/
    	
    		try {
    			// ��ȿ���˻翡 ����ϸ�
    			memberService.memberModify(member);
    			mav.setViewName("memberModifyEnd");
    			return mav;
    		} catch (DuplicateKeyException e) {
    			// db���� id�� ���������� unique�� �ٲ�� ������ �ߺ��� ���̵�� �����Ϸ��ϸ�
    			// DuplicateKeyException�� �߰Եǰ�
    			// ����ó���� properties���Ͽ� ��ϵ� "invalid"�� ������ ������ ����� ȸ������������ ���ư�������.
    			// ���̵� �ߺ��˻�
    			result.reject("invalid", null);
    			System.out.println("ĳġ����");
    			mav.setViewName("memberModify");
    			return mav;
    		}

    	}
    	
    	//ȸ��Ż��
    	@RequestMapping("/memberOutForm")
     	public ModelAndView memberOutForm() {
     		mav.setViewName("memberOut");
     		return mav;
     	}
     	
     	@RequestMapping("/memberDelete")
     	public ModelAndView memberDelete(@ModelAttribute("member") MemberModel member, BindingResult result, HttpSession session, HttpServletRequest request) {
     		
     		MemberModel memberModel; // ���� ��� ���� ������ ��ü
     		
     		String id;
     		String pass;
     		pass = request.getParameter("pass");
     		int deleteCheck;
     		
     		//�ش� ���̵��� ������ �����´�
     		id = session.getAttribute("session_member_id").toString();
     		memberModel = (MemberModel) memberService.getMember(id);
     		
     		
     		
     		if(memberModel.getPass().equals(pass)) {
     			//�н����尡 ������
     			deleteCheck = 1;
     			//���� ���� ����
     			memberService.memberDelete(id);
     			session.removeAttribute("session_member_id");
     			session.removeAttribute("session_member_name");
     		/*	session.removeAttribute("session_member_no");*/
     		}
     		else {
     			deleteCheck = -1; //�н����尡 �ȸ�����
     		}
     		
     		mav.addObject("deleteCheck", deleteCheck);
     		mav.setViewName("memberDelete");
     		return mav;
     	}

}

