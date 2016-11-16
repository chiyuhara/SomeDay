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
				member.setIdx(idx.getIdx());
				/*사진 업로드*/
				memberService.UpdateFile(member, request);
				
				mav.addObject("member", member);
				mav.setViewName("main");
				return mav;
	      
	        }


   //로그인 폼
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView noticeForm(HttpServletRequest request) {
		System.out.println("로그인 폼 실행");
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberModel", new MemberModel());
		mav.setViewName("loginForm");
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
      
         session.setAttribute("TOKEN_SAVE_CHECK", "TRUE");
                 
         mav.setViewName("main");
         mav.setViewName("redirect:/");
         return mav;
      }
      
      //System.out.println("로그인 실패");         
      mav.setViewName("main");
      return mav;
         
   }
   
   

   //로그아웃
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

   	//아이디 찾기 폼
  	@RequestMapping(value = "/memberIdFind", method = RequestMethod.GET)
  	public ModelAndView memberFindForm() {
  		mav.setViewName("member/idFind");
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
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("member/idFindOk");
  				return mav;
  			} else {
  				memberFindChk = -1; // 이름 , 닉네임 틀림
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("member/idFindError");
  				return mav;
  			} 
  		}
  	}

  	// 비밀번호찾기
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
  			memberFindChk = 0; // 가입되어 있는 사람 X;
  			mav.addObject("memberFindChk", memberFindChk);
  			mav.setViewName("member/idFindError");
  			return mav;

  		} else {
  			
  			if (member.getName().equals(member.getName()) && member.getId().equals(member.getId()) && member.getNum1().equals(member.getNum1()) && member.getNum2().equals(member.getNum2())) {
  				memberFindChk = 1; // 회원가입되어 있음, 닉네임 일치
  				mav.addObject("member", member);
  				mav.addObject("memberFindChk", memberFindChk);
  				mav.setViewName("member/pwFindOk");
  				return mav;
  			} else {
  				memberFindChk = -1; // 이름 , 닉네임 틀림
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
      
        //회원정보수정
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
        
    	//회원정보 수정완료
    	@RequestMapping("/memberModifyEnd")
    	public ModelAndView memberModifyEnd(@ModelAttribute("member") MemberModel member, BindingResult result) {
    		// Validation Binding
    		/*new MemberValidator().validate(member, result);*/
    	
    		try {
    			// 유효성검사에 통과하면
    			memberService.memberModify(member);
    			mav.setViewName("memberModifyEnd");
    			return mav;
    		} catch (DuplicateKeyException e) {
    			// db에서 id의 제약조건을 unique로 바꿨기 때문에 중복된 아이디로 가입하려하면
    			// DuplicateKeyException이 뜨게되고
    			// 예외처리로 properties파일에 등록된 "invalid"의 내용이 나오게 만들고 회원가입폼으로 돌아가게했음.
    			// 아이디 중복검사
    			result.reject("invalid", null);
    			System.out.println("캐치에러");
    			mav.setViewName("memberModify");
    			return mav;
    		}

    	}
    	
    	//회원탈퇴
    	@RequestMapping("/memberOutForm")
     	public ModelAndView memberOutForm() {
     		mav.setViewName("memberOut");
     		return mav;
     	}
     	
     	@RequestMapping("/memberDelete")
     	public ModelAndView memberDelete(@ModelAttribute("member") MemberModel member, BindingResult result, HttpSession session, HttpServletRequest request) {
     		
     		MemberModel memberModel; // 쿼리 결과 값을 저장할 객체
     		
     		String id;
     		String pass;
     		pass = request.getParameter("pass");
     		int deleteCheck;
     		
     		//해당 아이디의 정보를 가져온다
     		id = session.getAttribute("session_member_id").toString();
     		memberModel = (MemberModel) memberService.getMember(id);
     		
     		
     		
     		if(memberModel.getPass().equals(pass)) {
     			//패스워드가 맞으면
     			deleteCheck = 1;
     			//삭제 쿼리 수행
     			memberService.memberDelete(id);
     			session.removeAttribute("session_member_id");
     			session.removeAttribute("session_member_name");
     		/*	session.removeAttribute("session_member_no");*/
     		}
     		else {
     			deleteCheck = -1; //패스워드가 안맞을때
     		}
     		
     		mav.addObject("deleteCheck", deleteCheck);
     		mav.setViewName("memberDelete");
     		return mav;
     	}

}

