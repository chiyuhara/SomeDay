/*package com.someday.today;

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
import com.someday.member.MemberModel;
import com.someday.member.MemberService;

@Controller
public class TodayController {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "todayService")
	private TodayService todayService;

	@Resource(name = "memberService")
	private MemberService memberService;

	private int idx; // �������� �޾ƿ;ߵ� IDX (ȸ������)

	// ������ �ο� ������
	@RequestMapping(value = "/today")
	public ModelAndView tody(HttpServletRequest request) {
		System.out.println("������ �ο�");
		ModelAndView mav = new ModelAndView();

		MemberModel my = new MemberModel();
		MemberModel target = new MemberModel();

		int idx = 362; // �������� �޾ƿ� ������ ȸ�� IDX
		System.out.println("�α��ε� IDX : " + idx);

		// �α��ε� ȸ���� �������� ��������
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// �α����� ������ �����ϰ��
		if (myGenderfemale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ����ã��
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());

			if (female_today != null) {
				System.out.println("���� idx : " + female_today.getIdx());
				System.out.println("���� ���� idx : " + female_today.getMale_idx());
				my = memberService.my(idx);
				target = memberService.target(female_today.getMale_idx());
				System.out.println("���� ���� �̸� : " + target.getName());
				mav.addObject("my", my);
				mav.addObject("target", target);
				mav.setViewName("today");
			} else {
				my = memberService.my(idx);
				mav.addObject("my", my);
				mav.addObject("target", null);
				mav.setViewName("today");
			}

		}
		if (myGendermale != null) {
			System.out.println("�α����Ѱ����� ����");
		}
		return mav;
	}

	// ������ �ο� ������ �󼼺���
	@RequestMapping(value = "/today/TodayView")
	public ModelAndView TodayView(HttpServletRequest request) {
		System.out.println("�������ο� �󼼺���");
		ModelAndView mav = new ModelAndView();

		MemberModel my = new MemberModel();
		MemberModel target = new MemberModel();

		int idx = 362; // �������� �޾ƿ;ߵ� ���� idx

		// �α��ε� ȸ���� �������� ��������
		MemberModel myGenderfemale = memberService.myGenderfemale(idx);
		MemberModel myGendermale = memberService.myGendermale(idx);

		// �α����� ������ �����ϰ��
		if (myGenderfemale != null) {
			System.out.println("�α����Ѱ����� ����");
			// ���ڰ� ���� ����ã��
			TodayModel female_today = todayService.female_today(myGenderfemale.getIdx());
			System.out.println("���� idx : " + female_today.getIdx());
			System.out.println("���� ���� idx : " + female_today.getMale_idx());
			my = memberService.my(idx);
			target = memberService.target(female_today.getMale_idx());
			System.out.println("���� ���� �̸� : " + target.getName());
		}
		if (myGendermale != null) {
			System.out.println("�α����Ѱ����� ����");
		}

		return mav;
	}

}
*/