package com.someday.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import com.someday.member.MemberModel;
import com.someday.member.MemberService;

import com.someday.util.Paging;


@Controller
/*@RequestMapping("/admin")*/
public class AdminController {
	@Resource
	private AdminService adminService;
	@Resource(name = "memberService")
	private MemberService memberService;
	
	//페이징 
	private int searchNum;
	private String isSearch;
	
	private int currentPage = 1;	 
	private int totalCount; 		 
	private int blockCount = 7;	 
	private int blockPage = 5; 	 
	private String pagingHtml;  
	private Paging page;
	
	ModelAndView mav = new ModelAndView();
	
	@RequestMapping(value="/admin/admin")
	public String mainForm(){
		return "adminForm";
	}
	
		 
	    //회원목록
		@RequestMapping("/admin/memberadminList")
		public ModelAndView memberList(HttpServletRequest request) throws Exception{
			
			if(request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty() || request.getParameter("currentPage").equals("0")) {
	            currentPage = 1;
	        } else {
	            currentPage = Integer.parseInt(request.getParameter("currentPage"));
	        }
			
			List<MemberModel> memberlist=adminService.memberList();
			
			isSearch = request.getParameter("isSearch");
			if(isSearch != null)
			{
				searchNum = Integer.parseInt(request.getParameter("searchNum"));

				if(searchNum==0)//회원목록 검색(전체)
					memberlist = adminService.memberSearch0(isSearch);
							
				totalCount = memberlist.size();
				page = new Paging(currentPage, totalCount, blockCount, blockPage, "memberadminList", searchNum, isSearch);
				pagingHtml = page.getPagingHtml().toString();
			
				int lastCount = totalCount;
			
				if(page.getEndCount() < totalCount)
					lastCount = page.getEndCount() + 1;
				
				memberlist = memberlist.subList(page.getStartCount(), lastCount);
			
				mav.addObject("isSearch", isSearch);
				mav.addObject("searchNum", searchNum);
				mav.addObject("totalCount", totalCount);
				mav.addObject("pagingHtml", pagingHtml);
				mav.addObject("currentPage", currentPage);
				mav.addObject("memberlist", memberlist);
				mav.setViewName("memberadminList");
				return mav;
			}
			
			totalCount = memberlist.size();
			
			page = new Paging(currentPage, totalCount, blockCount, blockPage, "memberadminList");
			pagingHtml=page.getPagingHtml().toString(); 
			

			int lastCount = totalCount;
			 
			if (page.getEndCount() < totalCount)
				lastCount = page.getEndCount() + 1;
			 
			memberlist = memberlist.subList(page.getStartCount(), lastCount);
			
			mav.addObject("totalCount", totalCount);
			mav.addObject("pagingHtml", pagingHtml);
			mav.addObject("currentPage", currentPage);
			
			mav.addObject("memberlist", memberlist);
			mav.setViewName("memberadminList");
			
			return mav;
		}
				 
		//회원 1명 View 상세보기
		// 회원정보수정
	  	@RequestMapping("/admin/adminmemberModify")
	  	public ModelAndView memberModify(MemberModel member, HttpServletRequest request) {		  		 		
	  		
 			member =  memberService.getMember(member.getId());
 	
 			mav.addObject("member", member);
 			mav.setViewName("memberadminModify");
 			return mav;
	  	}
	  	
	  	//회원수정 등록
	    @RequestMapping("/admin/memberModifyEnd")
 		public ModelAndView adminmemberModifyEnd(MemberModel member) {
 		
		System.out.println("수정시작");
		
 			adminService.adminmemberModify(member);
 			mav.setViewName("redirect:/admin/memberadminList");
 			return mav;
	    }
	    
	  //회원삭제하기
		 @RequestMapping("/admin/adminMemberDelete")
			public ModelAndView memberDelete(HttpServletRequest request){		
			 String id = request.getParameter("id");
			 adminService.memberDelete(id);
			 mav.setViewName("redirect:/admin/memberadminList");
				
			 return mav;	
		}
		
		
		//관리자 권한 주기
		 @RequestMapping("/admin/authority")
		 public ModelAndView authority(MemberModel member){
		  
		 int idx = member.getIdx();
		
		 MemberModel memberlist = adminService.member(idx);
		 
		 System.out.println("결과값"+memberlist);
		 
		 if(memberlist.getAuthority().equals("N")){
			 
			 memberlist.setAuthority("Y");
		 }else{
			 
			 memberlist.setAuthority("N");
		 }
			
		 
		 adminService.authority(memberlist);
		 
		 mav.setViewName("redirect:/admin/memberadminList");
			 return mav;
		 }
		
    	
	
}
