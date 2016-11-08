package com.someday.notice;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

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

//����¡
import com.someday.util.Paging;
import com.someday.validator.NoticeValidator;

@Controller
public class NoticeController {

	// ���� ���ε�
	private static final String uploadPath = "/SomeDay/src/main/webapp/resources/Upload/";

	@Resource(name = "noticeService")
	private NoticeService noticeService;

	// �˻��� ���� ���� ����
	private int searchNum;
	private String isSearch;

	// ����¡�� ���� ���� ����
	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockPage = 5;
	private String pagingHtml;
	private Paging page;

	// ����� ���� ���� ����
	private int commentcount;

	// ���� ���
	@RequestMapping(value = "/notice/NoticeList")

	// ��ϸ� ���� ��
	/*
	 * public ModelAndView NoticeList(HttpServletRequest request, NoticeModel
	 * noticeModel) throws UnsupportedEncodingException {
	 * System.out.println("������� ����"); ModelAndView mav = new ModelAndView();
	 * List<NoticeModel> noticeList = noticeService.noticeList();
	 * mav.addObject("noticeList", noticeList); mav.setViewName("noticeList");
	 * return mav;
	 */

	// ����¡ ���� �˻��� �ִ� �Խ��� ���
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

	// ���� �󼼺���
	@RequestMapping(value = "/notice/NoticeView")
	public ModelAndView noticeView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		int idx = Integer.parseInt(request.getParameter("idx"));

		NoticeModel noticeModel = noticeService.noticeView(idx);

		/* noticeService.noticeUpdateReadcount(idx); //��ȸ�� 1 ���� */
		 
		List<NoticecommModel> noticecommList;
		noticecommList = noticeService.noticecommList(idx);
		commentcount = noticecommList.size();

		mav.addObject("noticeModel", noticeModel);
		mav.addObject("noticecommList", noticecommList);
		mav.addObject("commentcount", commentcount);
		mav.setViewName("noticeView");

		return mav;
	}

	// ���� ���� ��
	@RequestMapping(value = "/notice/NoticeWrite", method = RequestMethod.GET)
	public ModelAndView noticeForm(HttpServletRequest request) {
		System.out.println("�۾��� �� ����");
		ModelAndView mav = new ModelAndView();
		mav.addObject("noticeModel", new NoticeModel());
		mav.setViewName("noticeForm");
		return mav;
	}

	// �������� �۾���
	@RequestMapping(value = "/notice/NoticeWrite", method = RequestMethod.POST)
	public String reviewWrite(NoticeModel noticeModel,  BindingResult result,
			MultipartHttpServletRequest multipartHttpServletRequest) throws Exception, Exception{
		System.out.println("�۾���ex ����");
		System.out.println(noticeModel.getSubject());
		System.out.println(noticeModel.getContent());
		ModelAndView mav = new ModelAndView();

		new NoticeValidator().validate(noticeModel, result);

//		if (result.hasErrors()) {
//			mav.setViewName("noticeForm");
//			return "noticeForm";
//		}

		String content = noticeModel.getContent().replaceAll("\r\n", "<br />");
		noticeModel.setContent(content);
		
		//���ε�
		MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");
    	String filename = multipartFile.getOriginalFilename();
        	if (filename != ""){ 
			    noticeModel.setFile_savname(System.currentTimeMillis()+"_"+multipartFile.getOriginalFilename());
			    String savimagename = System.currentTimeMillis()+"_"+multipartFile.getOriginalFilename();
		        FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(uploadPath+"/"+savimagename));
		        noticeModel.setFile_savname(savimagename);
        	}else{
        		noticeModel.setFile_savname("NULL");		
        	}

		noticeService.noticeWrite(noticeModel);

		mav.setViewName("redirect:NoticeList");
		
		return "redirect:NoticeList";
	}

	// �������� ����
	@RequestMapping("/notice/NoticeDelete")
	public ModelAndView noticeDelete(HttpServletRequest request) {
		System.out.println("���� ����");
		ModelAndView mav = new ModelAndView();
		int idx = Integer.parseInt(request.getParameter("idx"));
		noticeService.noticeDelete(idx);
		mav.setViewName("redirect:NoticeList");

		return mav;
	}

	// �������� ������
	@RequestMapping("/notice/NoticeModify")
	public ModelAndView noticeModifyForm(@ModelAttribute("noticeModel") NoticeModel noticeModel, BindingResult result,
			HttpServletRequest request) {
		System.out.println("���� ���� ��");
		ModelAndView mav = new ModelAndView();
		noticeModel = noticeService.noticeView(noticeModel.getIdx());

		String content = noticeModel.getContent().replaceAll("<br />", "\r\n");
		noticeModel.setContent(content);

		mav.addObject("noticeModel", noticeModel);
		mav.setViewName("noticeModify");

		return mav;
	}

	// ���� ���� �Ϸ�
	@RequestMapping("/notice/NoticeModifySuccess")
	public ModelAndView noticeModify(@ModelAttribute("noticeModel") NoticeModel noticeModel,
			HttpServletRequest request) {

		System.out.println("���� ����");

		ModelAndView mav = new ModelAndView("redirect:NoticeView");

		String content = noticeModel.getContent().replaceAll("\r\n", "<br />");
		noticeModel.setContent(content);

		noticeService.noticeModify(noticeModel);

		mav.addObject("idx", noticeModel.getIdx());

		return mav;
	}
	
	//��۴ޱ� 
	@RequestMapping(value = "/notice/NoticecommWrite", method = RequestMethod.POST)
	public ModelAndView noticecommWrite(NoticecommModel noticecommModel, HttpServletRequest request) {
		System.out.println("��۾���ex ����");
		System.out.println("�۹�ȣ" +noticecommModel.getOriginidx());
		ModelAndView mav = new ModelAndView();
		//new NoticeValidator().validate(noticecommModel, result);

		String content = noticecommModel.getContent().replaceAll("\r\n", "<br />");
		noticecommModel.setContent(content);
		
		noticeService.noticecommWrite(noticecommModel);

		mav.setViewName("redirect:/notice/NoticeView?idx="+noticecommModel.getOriginidx());
		
		return mav;
	}

	//��ۻ��� 
	@RequestMapping(value="/notice/noticecommDelete")
	public ModelAndView noticecommDelete(HttpServletRequest request, NoticeModel noticeModel, NoticecommModel noticecommModel){			   
			ModelAndView mav = new ModelAndView();
			
			System.out.println("��ۻ��� ex ����");
			System.out.println("������ ��۹�ȣ" + noticecommModel.getIdx());
			
			noticeService.noticecommDelete(noticecommModel);
			noticeService.noticeView(noticeModel.getIdx());
			
			mav.setViewName("redirect:/notice/NoticeView?idx=" + noticecommModel.getOriginidx());
			
			return mav;
	}

}
