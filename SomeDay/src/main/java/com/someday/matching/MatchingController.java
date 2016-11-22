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

	// 랜덤 매칭
	@RequestMapping(value = "/Matching")
	public ModelAndView Matching(HttpServletRequest request) {
		System.out.println("매칭");
		ModelAndView mav = new ModelAndView();

		MemberModel random_female = new MemberModel();
		MemberModel random_male = new MemberModel();
		
		TodayModel create_meeting = new TodayModel(); // 미팅 생성
		TodayModel meeting = new TodayModel(); // 미팅 생성
	
		TodayModel female_check = new TodayModel();
		TodayModel male_check = new TodayModel();

		MatchingModel total_meeting = new MatchingModel(); // 미팅 생성 가능한 숫자
		int add_meeting = 0; // total_meeting 과 비교할 숫자

		// 매칭 가능한 커플 숫자 구하기
		System.out.println("매칭 가능한 커플 숫자 구하기");
		total_meeting = matchingService.matchNum();
		System.out.println(total_meeting.getSmall());

		// 매칭 가능한 커플 숫자만큼 MEETING 데이터 생성
		while (add_meeting <= total_meeting.getSmall()) {
			random_female = matchingService.random_female();
			random_male = matchingService.random_male();
			System.out.println("랜덤으로 뽑아온 여자 idx 값 : " + random_female.getIdx());
			System.out.println("랜덤으로 뽑아온 남자 idx 값 : " + random_male.getIdx());

			// 오늘 생성된 미팅중에 남자,또는 여자가 중복되는 회원이 있는지 채크
			female_check = todayService.meeting_femalecheck(random_female.getIdx());
			male_check = todayService.meeting_malecheck(random_male.getIdx());

			if (female_check == null && male_check == null) {
				System.out.println("중복되는 값 없음으로 미팅 생성");
				meeting.setFemale_idx(random_female.getIdx());
				meeting.setMale_idx(random_male.getIdx());
				
				todayService.create_meeting(meeting);
				
				add_meeting++;
			} else {
				System.out.println("중복되는 값 있음 미팅 생성X");
			}
			if (add_meeting == total_meeting.getSmall()) {
				System.out.println(add_meeting + "번 추가됨");
				break;
			}
		}

		return mav;
	}
}
