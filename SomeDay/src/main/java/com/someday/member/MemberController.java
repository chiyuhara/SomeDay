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

	  /*회원가입폼*/
		@RequestMapping("/memberForm")
		public ModelAndView memberStep1(){
		   
			 ModelAndView mav = new ModelAndView();
			  
		   mav.setViewName("memberForm");
		   return mav;
		}
		/*회원가입*/
		@RequestMapping(value="/memberjoin", method=RequestMethod.POST)
		public ModelAndView memberJoin(MemberModel member, MultipartHttpServletRequest request ) throws Exception {
			
	        	member.setPhone(member.getPhone3()+"-"+member.getPhone()+"-"+member.getPhone2()); //폰넘버 합치기
	        	
	        	if(member.getEmail2() != null){	//이메일 주소가 널이 아니면 실행
	        		member.setEmail(member.getEmail()+"@"+member.getEmail2());
				} else { // 이메일 주소가 널일시 실행
					member.setEmail(member.getEmail()+"@"+member.getSelectEmail());
				}
				memberService.insertMember(member);
				
				/*나이 성별 등록*/
				memberService.AgeGender(member);
				
				/*아이디로 IDX찾기*/
				MemberModel idx = (MemberModel)memberService.Idx(member);
                int index =(idx.getIdx());

				/*사진 업로드*/
                memberService.UpdateFile(index, request);

				
				mav.addObject("member", member);
				mav.setViewName("redirect:/");
				return mav;
	      
	        }


   //로그인 폼
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView noticeForm(HttpServletRequest request) {
		System.out.println("로그인 폼 실행");
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberModel", new MemberModel());
		mav.setViewName("redirect:/");
		return mav;
	}

   //로그인동작 및 세션 생성
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
      
      //System.out.println("로그인 실패");         
      mav.setViewName("/member/loginError");
      return mav;
         
   }
   
   

   //로그아웃
   @RequestMapping("/logout")
   public ModelAndView memberLogout(HttpServletRequest request, MemberModel mem){
      
      HttpSession session = request.getSession(false);
      
      if(session!=null){
         session.invalidate();
      }
      mav.setViewName("redirect:/");
      
      return mav;
   }
   
   //아이디,비밀번호 찾기 폼
   @RequestMapping("/findForm")
   public ModelAndView findForm() {
	   
	   ModelAndView mav = new ModelAndView();

	   mav.setViewName("findForm");
	   return mav;
   }
  	
  	//아이디 찾기
  	@RequestMapping(value = "/memberIdFind", method = RequestMethod.POST)
  	public ModelAndView memberIdFind(@ModelAttribute("member") MemberModel member, HttpServletRequest request) {

  		int memberFindChk;

  		member = memberService.idFindByName(member);
  		if (member == null) {
  			memberFindChk = 0; // 가입되어 있는 사람 X;
  			mav.addObject("memberFindChk", memberFindChk);
  			mav.setViewName("member/idFindError");
  			return mav;

  		} else {
  			if (member.getName().equals(member.getName()) && member.getNum1().equals(member.getNum1()) && member.getNum2().equals(member.getNum2())) {
  				memberFindChk = 1; // 회원가입되어 있음, 닉네임 일치
  				mav.addObject("member", member);
  				mav.addObject("member1", null);
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("findForm");
  				return mav;
  			} else { 
  				memberFindChk = -1; // 이름 , 닉네임 틀림
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("member/idFindError");
  				return mav;
  			} 
  		}
  	}
  	/*비밀번호찾기*/
  	@RequestMapping(value = "/memberPwFind", method = RequestMethod.POST)
  	public ModelAndView memberPwFind(@ModelAttribute("member") MemberModel member, HttpServletRequest request) {

  		int memberFindChk;


  		member = memberService.pwFindById(member);
  		
  		if (member == null) {
  			memberFindChk = 0; // 가입되어 있는 사람 X;
  			mav.addObject("memberFindChk", memberFindChk);
  			mav.setViewName("member/idFindError");
  			return mav;

  		} else {
  			
  			if (member.getName().equals(member.getName()) && member.getId().equals(member.getId()) && member.getNum1().equals(member.getNum1()) && member.getNum2().equals(member.getNum2())) {
  				memberFindChk = 1; // 회원가입되어 있음, 닉네임 일치
  				mav.addObject("member", null);
  				mav.addObject("member1", member);
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("findForm");
  				return mav;
  			} else {
  				memberFindChk = -1; // 이름 , 닉네임 틀림
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("member/idFindError");
  				return mav;
  			}
  		}
  	}

  	
   
      
  		/*우편번호 폼*/
	  @RequestMapping(value="/zipcodeCheckForm")
      public ModelAndView zipcodeCheckForm( HttpServletRequest req) throws Exception{
         ModelAndView mv = new ModelAndView();

             mv.setViewName("check/zipcodeCheck");
          return mv;
   }

      /*회원가입시 우편번호 검색 로직*/ 
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

      //아이디 중복체크 
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
    	  
    	  mv.addObject("member",memberModel); // 쿼리 결과값
    	  mv.addObject("ick",ick); // 아이디 있나없나
    	  mv.setViewName("check/inputIdCheck");
    	  return mv;
    	  
    	  
      }

      //회원정보 가져오기
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
        
    	//회원정보 수정폼
    	@RequestMapping("/MypageModify")
    	public ModelAndView memberModifyEnd(@ModelAttribute("member") MemberModel member, HttpSession session) {
        
    		session.getAttribute("session_member_idx");
          	if (session.getAttribute("mysession_member_idx") != null) {
          	int idx = (int) session.getAttribute("session_member_idx");
          	System.out.println(idx);
          	
          	memberModel = memberService.memberList(idx);

          	//폰넘버 나누기
          	String[] ph = memberModel.getPhone().split("-");
          	
          	memberModel.setPhone3(ph[0]);
          	memberModel.setPhone(ph[1]);
          	memberModel.setPhone2(ph[2]);
          	
          	//이메일 나누기
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
    	
    	//회원정보수정완료
    	@RequestMapping("/memberUpdate")
    	public ModelAndView memberUpdate(@ModelAttribute("member")MemberModel member){
    		
    		member.setPhone(member.getPhone3()+"-"+member.getPhone()+"-"+member.getPhone2()); //폰넘버 합치기
        	
        	if(member.getEmail2() != null){	//이메일 주소가 널이 아니면 실행
        		member.setEmail(member.getEmail()+"@"+member.getEmail2());
			} else { // 이메일 주소가 널일시 실행
				member.setEmail(member.getEmail()+"@"+member.getSelectEmail());
			}
        	
			memberService.memberModify(member);
			
			
		  ModelAndView mav = new ModelAndView();
  		  mav.setViewName("main");
  		  return mav;
    	}
    	
    	//회원탈퇴 비밀번호확인 폼
    	@RequestMapping("/memberDeleteForm")
    	public ModelAndView memberDeleteForm(){
    		mav.setViewName("check/checkPassword");
    		return mav;
    	}
    	
    	//회원탈퇴 일반회원
    	@RequestMapping("/memberDelete")
    	public ModelAndView memberDelete(@ModelAttribute("member") MemberModel member, HttpServletRequest request,  HttpSession session){
    		int deleteCheck;
    		int idx = (int) session.getAttribute("session_member_idx"); //로그인한 아이디의 idx값을 가져온다
    		String pass = request.getParameter("pass");	//모든 텍스트정보를 가져온것을 pass에 넣어줌
    		memberModel = memberService.memberList(idx);//idx로 로그인한 사람의정보를 가져옴
    		
    		if(memberModel.getPass().equals(pass)) { //가져온 pass와 입력한 pass가 맞으면
    			deleteCheck = 1;//정보가 있으면
    			memberService.memberDelete(idx);     // idx정보로 삭제
    		}else{
    			deleteCheck = 0;	//비밀번호가 틀렸을때
    		}
    		session.invalidate();	//세션초기화
    		mav.addObject("deleteCheck",deleteCheck);
    		mav.setViewName("check/checkPassword");
    		return mav;
    	}
    	
    	//이메일인증 폼
    	@RequestMapping("/emailForm")
    	public ModelAndView emailForm(){
    		mav.setViewName("/check/Email");
    		return mav;
    	}
    	
    	//이메일 인증
    	@RequestMapping("/emailAuth")
    	public ModelAndView emailAuth(HttpServletResponse response, HttpServletRequest request){		
    		
    	   String email1 = request.getParameter("email");
    	   String email2 = request.getParameter("email2");
    		
           String email = email1+"@"+email2;
           String authNum = RandomNum(); //난수 발생
               
	       String setfrom = "qudcks317@gmail.com";         
	   	   String tomail  = email;     // 받는 사람 이메일
	   	   String title   = "SomeDay 인증번호";      // 보내는 사람 이메일
	   	   String content = "인증번호 ["+ authNum + "]";   // 보내는 사람 이메일
          
           try {
             MimeMessage message = mailSender.createMimeMessage();
             MimeMessageHelper messageHelper 
                               = new MimeMessageHelper(message, true, "UTF-8");
        
             messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
             messageHelper.setTo(tomail);     // 받는사람 이메일
             messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
             messageHelper.setText(content);  // 메일 내용
            
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

