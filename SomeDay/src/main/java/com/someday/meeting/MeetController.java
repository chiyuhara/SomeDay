package com.someday.meeting;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.someday.member.MemberService;
import com.someday.util.Paging;

@Controller
@RequestMapping("/Meet")
public class MeetController {

	   @Resource(name="meetService")
	   private MeetService meetService;
	   
	   private ModelAndView mav = new ModelAndView();
	   
	   private List<MeetModel> meetModel = new ArrayList<MeetModel>();
	   //페이징을 위한 변수
	   private int currentPage = 1;
	   private int totalCount;
	   private int blockCount = 10;
	   private int blockPage = 5;
	   private String pagingHtml;
	   private Paging page;

	   @RequestMapping("/MeetList")
	   public ModelAndView MeetingList(HttpSession session, HttpServletRequest request){
		   
		   session.getAttribute("session_member_idx");
		   if(session.getAttribute("session_member_idx") != null){
			   
				//페이지 정보가 넘어온게 없으면 1페이지
				if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
						|| request.getParameter("currentPage").equals("0")) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}//정보가 있으면 그값을 페이지로 설정한다
				
			   meetModel = meetService.meetingList();
			   
			   totalCount = meetModel.size();
			   page = new Paging(currentPage, totalCount, blockCount, blockPage, "meetModel");
			   pagingHtml = page.getPagingHtml().toString();
			   
			   int lastCount = totalCount;
			   
			   if(page.getEndCount() < totalCount){
				   lastCount = page.getEndCount() + 1;
			   }
			   
			   meetModel = meetModel.subList(page.getStartCount(), lastCount);
			   
				mav.addObject("totalCount", totalCount);
				mav.addObject("pagingHtml", pagingHtml);
				mav.addObject("currentPage", currentPage);
				mav.addObject("meetModel", meetModel);
			    mav.setViewName("MeetList");
			   
			   return mav;
					   
		   }
		   
		   mav.setViewName("MeetList");
		   return mav;
	   }
	   
}
