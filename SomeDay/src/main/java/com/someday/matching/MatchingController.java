package com.someday.matching;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.someday.member.MemberModel;
import com.someday.today.TodayModel;
import com.someday.today.TodayService;

@Controller
public class MatchingController {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "todayService")
	private TodayService todayService;

	@Resource(name = "matchingService")
	private MatchingService matchingService;

	// ���� ��Ī
	@RequestMapping(value = "/Matching")
	public ModelAndView Matching(HttpServletRequest request) {
		System.out.println("��Ī");
		ModelAndView mav = new ModelAndView();

		MemberModel random_female = new MemberModel();
		MemberModel random_male = new MemberModel();
		
		TodayModel create_meeting = new TodayModel(); // ���� ����
		TodayModel meeting = new TodayModel(); // ���� ����
	
		TodayModel female_check = new TodayModel();
		TodayModel male_check = new TodayModel();

		MatchingModel total_meeting = new MatchingModel(); // ���� ���� ������ ����
		int add_meeting = 0; // total_meeting �� ���� ����

		// ��Ī ������ Ŀ�� ���� ���ϱ�
		System.out.println("��Ī ������ Ŀ�� ���� ���ϱ�");
		total_meeting = matchingService.matchNum();
		System.out.println(total_meeting.getSmall());

		// ��Ī ������ Ŀ�� ���ڸ�ŭ MEETING ������ ����
		while (add_meeting <= total_meeting.getSmall()) {
			random_female = matchingService.random_female();
			random_male = matchingService.random_male();
			System.out.println("�������� �̾ƿ� ���� idx �� : " + random_female.getIdx());
			System.out.println("�������� �̾ƿ� ���� idx �� : " + random_male.getIdx());

			// ���� ������ �����߿� ����,�Ǵ� ���ڰ� �ߺ��Ǵ� ȸ���� �ִ��� äũ
			female_check = todayService.meeting_femalecheck(random_female.getIdx());
			male_check = todayService.meeting_malecheck(random_male.getIdx());

			if (female_check == null && male_check == null) {
				System.out.println("�ߺ��Ǵ� �� �������� ���� ����");
				meeting.setFemale_idx(random_female.getIdx());
				meeting.setMale_idx(random_male.getIdx());
				
				todayService.create_meeting(meeting);
				
				add_meeting++;
			} else {
				System.out.println("�ߺ��Ǵ� �� ���� ���� ����X");
			}
			if (add_meeting == total_meeting.getSmall()) {
				System.out.println(add_meeting + "�� �߰���");
				break;
			}
		}

		return mav;
	}
}
