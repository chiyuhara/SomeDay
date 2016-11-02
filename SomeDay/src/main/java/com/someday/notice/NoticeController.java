package com.someday.notice;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.someday.validator.NoticeValidator;

@Controller
public class NoticeController {
	
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "noticeService")
	private NoticeService noticeService;

	// 공지 목록
	@RequestMapping(value = "/notice/NoticeList")
	public ModelAndView NoticeList(HttpServletRequest request, NoticeModel noticeModel)
			throws UnsupportedEncodingException {
		System.out.println("공지목록 실행");
		ModelAndView mav = new ModelAndView();
		List<NoticeModel> noticeList = noticeService.noticeList();
		mav.addObject("noticeList", noticeList);
		mav.setViewName("noticeList");

		return mav;
	}

	// 공지 상세보기
	@RequestMapping(value = "/notice/NoticeView")
	public ModelAndView noticeView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		int idx = Integer.parseInt(request.getParameter("idx"));

		NoticeModel noticeModel = noticeService.noticeView(idx);

		/* noticeService.noticeUpdateReadcount(idx); //조회수 1 증가 */

		mav.addObject("noticeModel", noticeModel);
		mav.setViewName("noticeView");

		return mav;
	}

	// 공지 쓰기 폼
	@RequestMapping(value = "/notice/NoticeWrite", method = RequestMethod.GET)
	public ModelAndView noticeForm(HttpServletRequest request) {
		System.out.println("글쓰기 폼 실행");
		ModelAndView mav = new ModelAndView();
		mav.addObject("noticeModel", new NoticeModel());
		mav.setViewName("noticeForm");
		return mav;
	}

	// 공지사항 글쓰기
	@RequestMapping(value = "/notice/NoticeWrite", method = RequestMethod.POST)
	public ModelAndView noticeWrite(@ModelAttribute("noticeModel") NoticeModel noticeModel, BindingResult result,
			HttpServletRequest request, HttpSession session) {
		System.out.println("글쓰기ex 실행");
		System.out.println(noticeModel.getSubject());
		System.out.println(noticeModel.getContent());
		ModelAndView mav = new ModelAndView();

		new NoticeValidator().validate(noticeModel, result);

		if (result.hasErrors()) {
			mav.setViewName("noticeForm");
			return mav;
		}

		String content = noticeModel.getContent().replaceAll("\r\n", "<br />");
		noticeModel.setContent(content);

		noticeService.noticeWrite(noticeModel);

		mav.setViewName("redirect:NoticeList");

		return mav;
	}

	// 공지사항 삭제
	@RequestMapping("/notice/NoticeDelete")
	public ModelAndView noticeDelete(HttpServletRequest request) {
		System.out.println("공지 삭제");
		ModelAndView mav = new ModelAndView();
		int idx = Integer.parseInt(request.getParameter("idx"));
		noticeService.noticeDelete(idx);
		mav.setViewName("redirect:NoticeList");

		return mav;
	}

	// 공지사항 수정폼
	@RequestMapping("/notice/NoticeModify")
	public ModelAndView noticeModifyForm(@ModelAttribute("noticeModel") NoticeModel noticeModel, BindingResult result,
			HttpServletRequest request) {
		System.out.println("공지 수정 폼");
		ModelAndView mav = new ModelAndView();
		noticeModel = noticeService.noticeView(noticeModel.getIdx());

		String content = noticeModel.getContent().replaceAll("<br />", "\r\n");
		noticeModel.setContent(content);

		mav.addObject("noticeModel", noticeModel);
		mav.setViewName("noticeModify");

		return mav;
	}
	
	//공지 수정 완료
	@RequestMapping("/notice/NoticeModifySuccess")
	public ModelAndView noticeModify(@ModelAttribute("noticeModel") NoticeModel noticeModel, HttpServletRequest request){

		System.out.println("공지 수정");
		
		ModelAndView mav = new ModelAndView("redirect:NoticeView");
		
		
		String content = noticeModel.getContent().replaceAll("\r\n", "<br />");
		noticeModel.setContent(content);
		
		noticeService.noticeModify(noticeModel);
			
		mav.addObject("idx", noticeModel.getIdx());
			
		return mav;	
	}

}
