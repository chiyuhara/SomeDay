package com.someday.notice;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.someday.member.MemberModel;
//페이징
import com.someday.util.Paging;
import com.someday.validator.NoticeValidator;

@Controller
public class NoticeController {

	@Resource(name = "noticeService")
	private NoticeService noticeService;

	// 검색을 위한 변수 설정
	private int searchNum;
	private String isSearch;

	// 페이징을 위한 변수 설정
	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockPage = 5;
	private String pagingHtml;
	private Paging page;


	// 댓글을 위한 변수 설정
	private int commentcount;
	private int comment_count;
	private int commupdate1;
	private String commenter;
	

	// 공지 목록
	@RequestMapping(value = "/notice/NoticeList")

	// 목록만 있을 때
	/*
	 * public ModelAndView NoticeList(HttpServletRequest request, NoticeModel
	 * noticeModel) throws UnsupportedEncodingException {
	 * System.out.println("공지목록 실행"); ModelAndView mav = new ModelAndView();
	 * List<NoticeModel> noticeList = noticeService.noticeList();
	 * mav.addObject("noticeList", noticeList); mav.setViewName("noticeList");
	 * return mav;
	 */

	// 페이징 없이 검색만 있는 게시판 목록
	/*
	 * public ModelAndView noticeList(HttpServletRequest request) throws
	 * UnsupportedEncodingException{ ModelAndView mav = new ModelAndView();
	 * List<NoticeModel> noticeList = noticeService.noticeList();
	 * 
	 * String isSearch = request.getParameter("isSearch"); if(isSearch != null)
	 * isSearch = new String(isSearch.getBytes("8859_1"), "UTF-8");
	 * 
	 * if(isSearch != null) { searchNum =
	 * Integer.parseInt(request.getParameter("searchNum"));
	 * 
	 * if(searchNum==0) noticeList = noticeService.noticeSearch0(isSearch); else
	 * if(searchNum==1) noticeList = noticeService.noticeSearch1(isSearch); else
	 * if(searchNum==2) noticeList = noticeService.noticeSearch2(isSearch);
	 * 
	 * mav.addObject("isSearch", isSearch); mav.addObject("searchNum",
	 * searchNum); mav.addObject("noticeList", noticeList);
	 * mav.setViewName("noticeList"); return mav; }
	 * 
	 * mav.addObject("noticeList", noticeList); mav.setViewName("noticeList");
	 * return mav;
	 * 
	 * }
	 */

	public ModelAndView noticeList(HttpServletRequest request) throws UnsupportedEncodingException {

		ModelAndView mav = new ModelAndView();

		if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
				|| request.getParameter("currentPage").equals("0")) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		List<NoticeModel> noticeList;
		noticeList = noticeService.noticeList();

		String isSearch = request.getParameter("isSearch");
		if (isSearch != null)
			isSearch = new String(isSearch.getBytes("8859_1"), "UTF-8");

		if (isSearch != null) {
			searchNum = Integer.parseInt(request.getParameter("searchNum"));

			if (searchNum == 0) {
				noticeList = noticeService.noticeSearch0(isSearch);
			} else if (searchNum == 1) {
				noticeList = noticeService.noticeSearch1(isSearch);
			} else if (searchNum == 2) {
				noticeList = noticeService.noticeSearch2(isSearch);
			}

			totalCount = noticeList.size();
			page = new Paging(currentPage, totalCount, blockCount, blockPage, "noticeList", searchNum, isSearch);
			pagingHtml = page.getPagingHtml().toString();

			int lastCount = totalCount;

			if (page.getEndCount() < totalCount)
				lastCount = page.getEndCount() + 1;

			noticeList = noticeList.subList(page.getStartCount(), lastCount);

			mav.addObject("isSearch", isSearch);
			mav.addObject("searchNum", searchNum);
			mav.addObject("totalCount", totalCount);
			mav.addObject("pagingHtml", pagingHtml);
			mav.addObject("currentPage", currentPage);
			mav.addObject("noticeList", noticeList);
			mav.setViewName("noticeList");

		}
		
		noticeList = noticeService.noticeList();
		
		totalCount = noticeList.size();
		page = new Paging(currentPage, totalCount, blockCount, blockPage, "noticeList", searchNum, isSearch);
		pagingHtml = page.getPagingHtml().toString();

		int lastCount = totalCount;

		if (page.getEndCount() < totalCount) {
			lastCount = page.getEndCount() + 1;
		}
		
		noticeList = noticeList.subList(page.getStartCount(), lastCount);

		mav.addObject("isSearch", isSearch);
		mav.addObject("searchNum", searchNum);
		mav.addObject("totalCount", totalCount);
		mav.addObject("pagingHtml", pagingHtml);
		mav.addObject("currentPage", currentPage);
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
		noticeService.noticeUpdateReadhit(idx); //조회수 1증가 
		
		List<NoticecommModel> noticecommList;
		noticecommList = noticeService.noticecommList(idx);
		commentcount = noticecommList.size();

		System.out.println(noticeModel.getFile_savname());
		System.out.println("노티스모델 안에가지고있는 값" +noticeModel.getType());
		
		mav.addObject("noticeModel", noticeModel);
		mav.addObject("noticecommList", noticecommList);
		mav.addObject("commentcount", commentcount);
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
	public String reviewWrite(NoticeModel noticeModel,  BindingResult result, String type,
								MultipartHttpServletRequest multipartHttpServletRequest) throws Exception, Exception{
		System.out.println("글쓰기ex 실행");
		System.out.println(noticeModel.getSubject());
		System.out.println(noticeModel.getContent());
		
		ModelAndView mav = new ModelAndView();

		new NoticeValidator().validate(noticeModel, result);

//		if (result.hasErrors()) {
//			mav.setViewName("noticeForm");
//			return "noticeForm";
//		}
		
		//타입 

		String content = noticeModel.getContent().replaceAll("\r\n", "<br />");
		noticeModel.setContent(content);

		//날짜 및 시간 
		Date currentTime = new Date ( );
		System.out.println ( currentTime );
		
		noticeModel.setTimes(currentTime);
		

		noticeService.noticeWrite(noticeModel);
		
		//idx 가져오기 
		NoticeModel idx = (NoticeModel)noticeService.Idx(noticeModel);
		System.out.println(idx.getIdx());
		
		int index =(idx.getIdx());
		System.out.println("index 결과" + index);
		
		//사진 업로드
		noticeService.UpdateFile(index, multipartHttpServletRequest);
    	
		mav.setViewName("redirect:NoticeList");
		
		return "redirect:NoticeList";
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
	public ModelAndView noticeModifyForm(@ModelAttribute("noticeModel") NoticeModel noticeModel, 
			BindingResult result, HttpServletRequest request) {
		System.out.println("공지 수정 폼");
		ModelAndView mav = new ModelAndView();
		noticeModel = noticeService.noticeView(noticeModel.getIdx());

		String content = noticeModel.getContent().replaceAll("<br />", "\r\n");
		noticeModel.setContent(content);

		mav.addObject("noticeModel", noticeModel);
		mav.setViewName("noticeModify");

		return mav;
	}

	// 공지 수정 완료
	@RequestMapping("/notice/NoticeModifySuccess")
	public ModelAndView noticeModify(@ModelAttribute("noticeModel") NoticeModel noticeModel,
			HttpServletRequest request) {

		System.out.println("공지수정");

		ModelAndView mav = new ModelAndView("redirect:NoticeView");
		System.out.println("현재페이지:" + currentPage);

		String content = noticeModel.getContent().replaceAll("\r\n", "<br />");
		noticeModel.setContent(content);

		noticeService.noticeModify(noticeModel);

		mav.addObject("idx", noticeModel.getIdx());
		System.out.println("수정 후 페이지:" + currentPage);
		return mav;
	}
	
	//댓글달기 
	@RequestMapping(value = "/notice/NoticecommWrite", method = RequestMethod.POST)
	public ModelAndView noticecommWrite(NoticecommModel noticecommModel, HttpServletRequest request) {
		System.out.println("댓글쓰기ex 실행");
		System.out.println("글번호" +noticecommModel.getOriginidx());
		ModelAndView mav = new ModelAndView();
		//new NoticeValidator().validate(noticecommModel, result);

		String content = noticecommModel.getContent().replaceAll("\r\n", "<br />");
		noticecommModel.setContent(content);
		
		noticeService.noticecommWrite(noticecommModel);

		mav.setViewName("redirect:/notice/NoticeView?idx="+noticecommModel.getOriginidx());
		
		return mav;
	}

	//댓글삭제 
	@RequestMapping(value="/notice/noticecommDelete")
	public ModelAndView noticecommDelete(HttpServletRequest request, NoticeModel noticeModel, NoticecommModel noticecommModel){			   
			ModelAndView mav = new ModelAndView();
			
			System.out.println("댓글삭제 ex 실행");
			System.out.println("삭제될 댓글번호" + noticecommModel.getIdx());
			
			noticeService.noticecommDelete(noticecommModel);
			noticeService.noticeView(noticeModel.getIdx());
			
			mav.setViewName("redirect:/notice/NoticeView?idx=" + noticecommModel.getOriginidx());
			
			return mav;
	}

}
