package com.someday.qna;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.someday.member.MemberService;
import com.someday.qna.QnAModel;
import com.someday.util.Paging;
import com.someday.validator.QnAValidator;

@Controller
public class QnAController {
	
	@Resource(name = "qnAService")
	//���ϸ��� �ձ��ڸ� �ҹ��ڷ� �ٲ��ָ� �� (QnAServic->qnAServic)
	private QnAService qnAService;
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	private MemberModel memberModel = new MemberModel();
	
	// ����¡�� ���� ���� ����
		private int currentPage = 1;
		private int totalCount;
		private int blockCount = 10;
		private int blockPage = 5;
		private String pagingHtml;
		private Paging page;
	
	// �˻��� ���� ���� ����
		private int searchNum;
		private String isSearch;

	//����� ���� ���� ���� 
		private int commentcount;

	// ���� ���
	@RequestMapping(value = "/qna/QnAList")
	public ModelAndView QnAList(HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException {
		
		ModelAndView mav = new ModelAndView();
		System.out.println("������� ����"); 
		
		if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
				|| request.getParameter("currentPage").equals("0")) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		List<QnAModel> qnaList = qnAService.qnaList();
		qnaList = qnAService.qnaList();
		
		System.out.println("�˻� ����"); 
		String isSearch = request.getParameter("isSearch"); 
		
		if (isSearch != null)
			isSearch = new String(isSearch.getBytes("8859_1"), "UTF-8");

		if (isSearch != null) {
			searchNum = Integer.parseInt(request.getParameter("searchNum"));
			 	 
			 if (searchNum==0) {
				qnaList = qnAService.qnaSearch0(isSearch); 
			 } else if (searchNum==1) {
				 qnaList = qnAService.qnaSearch1(isSearch); 
			 } else if(searchNum==2) { 
				 qnaList = qnAService.qnaSearch2(isSearch);
			 }
			 
			 totalCount = qnaList.size();
				page = new Paging(currentPage, totalCount, blockCount, blockPage, "qnaList", searchNum, isSearch);
				pagingHtml = page.getPagingHtml().toString();

				int lastCount = totalCount;

				if (page.getEndCount() < totalCount)
					lastCount = page.getEndCount() + 1;

				qnaList = qnaList.subList(page.getStartCount(), lastCount);
				
				int id;
				if(session.getAttribute("session_member_idx") != null){
					 id = 1;
				}else{
					 id = 2;
				}
				
		mav.addObject("id",id);		
		mav.addObject("isSearch", isSearch); 
		mav.addObject("searchNum", searchNum);
		mav.addObject("totalCount", totalCount);
		mav.addObject("pagingHtml", pagingHtml);
		mav.addObject("currentPage", currentPage);
		mav.addObject("qnaList", qnaList); 
		mav.setViewName("qnaList");
		return mav;
		}
		
		qnaList = qnAService.qnaList();
		
		totalCount = qnaList.size();
		page = new Paging(currentPage, totalCount, blockCount, blockPage, "qnaList");
		pagingHtml = page.getPagingHtml().toString();

		int lastCount = totalCount;

		if (page.getEndCount() < totalCount) {
			lastCount = page.getEndCount() + 1;
		}
		
		qnaList = qnaList.subList(page.getStartCount(), lastCount);
		
		int id;
		if(session.getAttribute("session_member_idx") != null){
			 id = 1;
		}else{
			 id = 2;
		}
		
		mav.addObject("id",id);		
		mav.addObject("totalCount", totalCount);
		mav.addObject("pagingHtml", pagingHtml);
		mav.addObject("currentPage", currentPage);
		mav.addObject("qnaList", qnaList); 
		mav.setViewName("qnaList");
		
		return mav;
	}
	
	// ���� �󼼺���
		@RequestMapping(value = "/qna/QnAView")
		public ModelAndView qnaView(HttpServletRequest request, HttpSession session) {
			ModelAndView mav = new ModelAndView();
			int idcheck;
			
			int idx = Integer.parseInt(request.getParameter("idx"));

			QnAModel qnaModel = qnAService.qnaView(idx);
			
			List<QnAcommModel> qnacommList;
			qnacommList = qnAService.qnacommList(idx);
			commentcount = qnacommList.size();
			
			if(session.getAttribute("session_member_idx") != null){
			int memberidx = (int) session.getAttribute("session_member_idx");
			memberModel = memberService.memberList(memberidx);
			
			System.out.println(memberModel.getNick());
			System.out.println(qnaModel.getWriter());
			
			if(memberModel.getNick().equals(qnaModel.getWriter())){
				idcheck = 1; // ���� ������
			}else if(memberModel.getAuthority().equals("Y")){
				idcheck = 1;
			}
			else{
				idcheck = 0;
			}
			System.out.println(idcheck);
			mav.addObject("idcheck", idcheck);
			mav.addObject("qnaModel", qnaModel);
			mav.addObject("qnacommList", qnacommList);
			mav.addObject("commentcount", commentcount);
			mav.setViewName("qnaView");

			return mav;
			
			}
			idcheck = 0;
			mav.addObject("idcheck", idcheck);
			mav.addObject("qnaModel", qnaModel);
			mav.addObject("qnacommList", qnacommList);
			mav.addObject("commentcount", commentcount);
			mav.setViewName("qnaView");

			return mav;
		} 
		
		//��۴ޱ� 
		@RequestMapping(value = "/qna/QnAcommWrite", method = RequestMethod.POST)
		public ModelAndView qnacommWrite(QnAcommModel qnacommModel, QnAModel qnaModel) {
			System.out.println("��۾���ex ����");
			System.out.println("�۹�ȣ" + qnacommModel.getOriginidx());
			
			ModelAndView mav = new ModelAndView();

			String content = qnacommModel.getContent().replaceAll("\r\n", "<br />");
			qnacommModel.setContent(content);
			
			qnAService.qnacommWrite(qnacommModel);

			mav.setViewName("redirect:/qna/QnAView?idx=" + qnacommModel.getOriginidx());
			
			return mav;
		}

		//��ۻ��� 
		@RequestMapping(value="/qna/qnacommDelete")
		public ModelAndView qnacommDelete(QnAModel qnaModel, QnAcommModel qnacommModel){			   
				ModelAndView mav = new ModelAndView();
				
				System.out.println("��ۻ��� ex ����");
				System.out.println("������ ��۹�ȣ" + qnacommModel.getIdx());
				
				qnAService.qnacommDelete(qnacommModel);
				qnAService.qnaView(qnaModel.getIdx());
				
				mav.setViewName("redirect:/qna/QnAView?idx=" + qnacommModel.getOriginidx());
				
				return mav;
		}
		
		// qna ���� ��
		@RequestMapping(value = "/qna/QnAWrite", method = RequestMethod.GET)
		public ModelAndView qnaForm(HttpServletRequest request) {
			System.out.println("�۾��� �� ����");
			ModelAndView mav = new ModelAndView();
			mav.addObject("qnaModel", new QnAModel());
			mav.setViewName("qnaForm");
			return mav;
		}
		
		// qna �� ����
		@RequestMapping(value = "/qna/QnAWrite", method = RequestMethod.POST)
		public String qnaWrite(QnAModel qnaModel,  BindingResult result, HttpSession session,
				MultipartHttpServletRequest multipartHttpServletRequest) throws Exception, Exception{
			System.out.println("�۾���ex ����");
			System.out.println(qnaModel.getSubject());
			System.out.println(qnaModel.getContent());
			System.out.println(qnaModel.getPass());
			
			ModelAndView mav = new ModelAndView();

			new QnAValidator().validate(qnaModel, result);
			
			String content = qnaModel.getContent().replaceAll("\r\n", "<br />");
			qnaModel.setContent(content);
			
			session.getAttribute("session_member_idx");
			int memberidx = (int) session.getAttribute("session_member_idx");
			memberModel = memberService.memberList(memberidx);
			String writer = memberModel.getNick();		
			
			qnaModel.setWriter(writer);

			//��¥ �� �ð� 
			Date currentTime = new Date ( );
			System.out.println ( currentTime );
			
			qnaModel.setTimes(currentTime);
			
			qnAService.qnaWrite(qnaModel);
			
			//idx �������� 
			QnAModel idx = (QnAModel)qnAService.Idx(qnaModel);
			System.out.println("�ε�" + idx.getIdx());
			
			int index =(idx.getIdx());
			System.out.println("index ���" + index);
			
			//���� ���ε�
			qnAService.UpdateFile(index, multipartHttpServletRequest);
	    	
			mav.setViewName("redirect:QnAList");
			
			return "redirect:QnAList";
		}
		
		// �������� ������
		@RequestMapping("/qna/QnAModify")
		public ModelAndView qnaModifyForm(@ModelAttribute("qnaModel") QnAModel qnaModel, 
				BindingResult result, HttpServletRequest request) {
			System.out.println("���� ���� ��");
			ModelAndView mav = new ModelAndView();
			qnaModel = qnAService.qnaView(qnaModel.getIdx());

			String content = qnaModel.getContent().replaceAll("<br />", "\r\n");
			qnaModel.setContent(content);

			mav.addObject("qnaModel", qnaModel);
			mav.setViewName("qnaModify");

			return mav;
		}

		// ���� ���� �Ϸ�
		@RequestMapping("/qna/QnAModifySuccess")
		public ModelAndView qnaModify(@ModelAttribute("qnaModel") QnAModel qnaModel,
				HttpServletRequest request) {

			System.out.println("��������");

			ModelAndView mav = new ModelAndView("redirect:QnAView");
			System.out.println("����������:" + currentPage);

			String content = qnaModel.getContent().replaceAll("\r\n", "<br />");
			qnaModel.setContent(content);

			qnAService.qnaModify(qnaModel);

			mav.addObject("idx", qnaModel.getIdx());
			System.out.println("���� �� ������:" + currentPage);
			return mav;
		}
		
		// �������� ����
		@RequestMapping("/qna/QnADelete")
		public ModelAndView qnaDelete(HttpServletRequest request) {
			System.out.println("���� ����");
			ModelAndView mav = new ModelAndView();
			int idx = Integer.parseInt(request.getParameter("idx"));
			qnAService.qnaDelete(idx);
			mav.setViewName("redirect:QnAList");

			return mav;
		}
		
		//ã�ƿ��ô±�
		@RequestMapping("/load")
		public ModelAndView load(){
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("qna/load");
			return mav;
		}


}