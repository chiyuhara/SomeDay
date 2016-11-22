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
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;



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
	   
	   @Autowired
	   private JavaMailSender mailSender;
	  
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
                int index =(idx.getIdx());

				/*���� ���ε�*/
                memberService.UpdateFile(index, request);

				
				mav.addObject("member", member);
				mav.setViewName("redirect:/");
				return mav;
	      
	        }


   //�α��� ��
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView noticeForm(HttpServletRequest request) {
		System.out.println("�α��� �� ����");
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberModel", new MemberModel());
		mav.setViewName("redirect:/");
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
         session.setAttribute("session_member_authority", result.getAuthority());
      
         session.setAttribute("TOKEN_SAVE_CHECK", "TRUE");
                 
         mav.setViewName("redirect:/");
         return mav;
      }
      
      //System.out.println("�α��� ����");         
      mav.setViewName("/member/loginError");
      return mav;
         
   }
   
   

   //�α׾ƿ�
   @RequestMapping("/logout")
   public ModelAndView memberLogout(HttpServletRequest request, MemberModel mem){
      
      HttpSession session = request.getSession(false);
      
      if(session!=null){
         session.invalidate();
      }
      mav.setViewName("redirect:/");
      
      return mav;
   }
   
   //���̵�,��й�ȣ ã�� ��
   @RequestMapping("/findForm")
   public ModelAndView findForm() {
	   
	   ModelAndView mav = new ModelAndView();

	   mav.setViewName("findForm");
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
  				mav.addObject("member1", null);
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("findForm");
  				return mav;
  			} else { 
  				memberFindChk = -1; // �̸� , �г��� Ʋ��
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("member/idFindError");
  				return mav;
  			} 
  		}
  	}
  	/*��й�ȣã��*/
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
  				mav.addObject("member", null);
  				mav.addObject("member1", member);
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("findForm");
  				return mav;
  			} else {
  				memberFindChk = -1; // �̸� , �г��� Ʋ��
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("member/idFindError");
  				return mav;
  			}
  		}
  	}

  	
   
      
  		/*�����ȣ ��*/
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

      //ȸ������ ��������
      	@RequestMapping("/MypageView")
      	public ModelAndView mypageView(@ModelAttribute("member") MemberModel member, HttpSession session){
      		
      	session.getAttribute("session_member_idx");
      	if (session.getAttribute("session_member_idx") != null) {
      	int idx = (int) session.getAttribute("session_member_idx");
      	System.out.println(idx);
      	
      	memberModel = memberService.memberList(idx);
      	ModelAndView mav = new ModelAndView();
		  mav.addObject("member", memberModel);	  
		  mav.setViewName("MypageView");
		  return mav;
      	}
      	mav.setViewName("MypageView");
      	return mav;
      	}
        
    	//ȸ������ ������
    	@RequestMapping("/MypageModify")
    	public ModelAndView memberModifyEnd(@ModelAttribute("member") MemberModel member, HttpSession session) {
        
    		session.getAttribute("session_member_idx");
          	if (session.getAttribute("mysession_member_idx") != null) {
          	int idx = (int) session.getAttribute("session_member_idx");
          	System.out.println(idx);
          	
          	memberModel = memberService.memberList(idx);

          	//���ѹ� ������
          	String[] ph = memberModel.getPhone().split("-");
          	
          	memberModel.setPhone3(ph[0]);
          	memberModel.setPhone(ph[1]);
          	memberModel.setPhone2(ph[2]);
          	
          	//�̸��� ������
          	String[] em = memberModel.getEmail().split("@");
          	
          	memberModel.setEmail(em[0]);
          	if(em[1] != "naver.com" || em[1] != "daum.net" || em[1] != "nate.com" || em[1] != "hotmail.com" || 
          			em[1] != "yahoo.com" || em[1] != "empas.com" || em[1] != "korea.com" || em[1] != "dreamwiz.com" ||
          			em[1] != "gmail.com"){
          		memberModel.setEmail2(em[1]);
          	} else {
          		memberModel.setSelectEmail(em[1]);
          	}
          	

          	ModelAndView mav = new ModelAndView();
    		  mav.addObject("member", memberModel);	  
    		  mav.setViewName("MypageModify");
    		  return mav;
    		}
          	mav.setViewName("MypageModify");
          	return mav;

    	}
    	
    	//ȸ�����������Ϸ�
    	@RequestMapping("/memberUpdate")
    	public ModelAndView memberUpdate(@ModelAttribute("member")MemberModel member){
    		
    		member.setPhone(member.getPhone3()+"-"+member.getPhone()+"-"+member.getPhone2()); //���ѹ� ��ġ��
        	
        	if(member.getEmail2() != null){	//�̸��� �ּҰ� ���� �ƴϸ� ����
        		member.setEmail(member.getEmail()+"@"+member.getEmail2());
			} else { // �̸��� �ּҰ� ���Ͻ� ����
				member.setEmail(member.getEmail()+"@"+member.getSelectEmail());
			}
        	
			memberService.memberModify(member);
			
			
		  ModelAndView mav = new ModelAndView();
  		  mav.setViewName("main");
  		  return mav;
    	}
    	
    	//ȸ��Ż�� ��й�ȣȮ�� ��
    	@RequestMapping("/memberDeleteForm")
    	public ModelAndView memberDeleteForm(){
    		mav.setViewName("check/checkPassword");
    		return mav;
    	}
    	
    	//ȸ��Ż�� �Ϲ�ȸ��
    	@RequestMapping("/memberDelete")
    	public ModelAndView memberDelete(@ModelAttribute("member") MemberModel member, HttpServletRequest request,  HttpSession session){
    		int deleteCheck;
    		int idx = (int) session.getAttribute("session_member_idx"); //�α����� ���̵��� idx���� �����´�
    		String pass = request.getParameter("pass");	//��� �ؽ�Ʈ������ �����°��� pass�� �־���
    		memberModel = memberService.memberList(idx);//idx�� �α����� ����������� ������
    		
    		if(memberModel.getPass().equals(pass)) { //������ pass�� �Է��� pass�� ������
    			deleteCheck = 1;//������ ������
    			memberService.memberDelete(idx);     // idx������ ����
    		}else{
    			deleteCheck = 0;	//��й�ȣ�� Ʋ������
    		}
    		session.invalidate();	//�����ʱ�ȭ
    		mav.addObject("deleteCheck",deleteCheck);
    		mav.setViewName("check/checkPassword");
    		return mav;
    	}
    	
    	//�̸������� ��
    	@RequestMapping("/emailForm")
    	public ModelAndView emailForm(){
    		mav.setViewName("/check/Email");
    		return mav;
    	}
    	
    	//�̸��� ����
    	@RequestMapping("/emailAuth")
    	public ModelAndView emailAuth(HttpServletResponse response, HttpServletRequest request){		
    		
    	   String email1 = request.getParameter("email");
    	   String email2 = request.getParameter("email2");
    		
           String email = email1+"@"+email2;
           String authNum = RandomNum(); //���� �߻�
               
	       String setfrom = "qudcks317@gmail.com";         
	   	   String tomail  = email;     // �޴� ��� �̸���
	   	   String title   = "SomeDay ������ȣ";      // ������ ��� �̸���
	   	   String content = "������ȣ ["+ authNum + "]";   // ������ ��� �̸���
          
           try {
             MimeMessage message = mailSender.createMimeMessage();
             MimeMessageHelper messageHelper 
                               = new MimeMessageHelper(message, true, "UTF-8");
        
             messageHelper.setFrom(setfrom);  // �����»�� �����ϰų� �ϸ� �����۵��� ����
             messageHelper.setTo(tomail);     // �޴»�� �̸���
             messageHelper.setSubject(title); // ���������� ������ �����ϴ�
             messageHelper.setText(content);  // ���� ����
            
             mailSender.send(message);
           } catch(Exception e){
             System.out.println(e);
           }
           System.out.println(authNum);
           mav.addObject("authNum", authNum);
    	   mav.setViewName("/check/Email");
    		
    		return mav;
    	}
    	
		public String RandomNum(){
			StringBuffer buffer = new StringBuffer();
			for(int i = 0; i <= 6; i++){
				int n = (int)(Math.random() * 10);
				buffer.append(n);
			}
			return buffer.toString();
		}
    	
}

