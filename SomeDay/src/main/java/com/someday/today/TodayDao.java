package com.someday.today;

public interface TodayDao {
	
	//여자 회원이 속한 오늘 미팅 찾기
	TodayModel female_today(int idx);
	
	//남자 회원이 속한 오늘 미팅 찾기
	TodayModel male_today(int idx);
	
	//오늘 생성된 미팅중에 남자,또는 여자가 중복되는 회원이 있는지 채크
	TodayModel meeting_femalecheck(int idx);
	
	//오늘 생성된 미팅중에 남자,또는 여자가 중복되는 회원이 있는지 채크
	TodayModel meeting_malecheck(int idx);

	//미팅 생성
	int create_meeting(TodayModel meeting);

	//여자가 좋아요 누름
	int female_like(int idx);

	//남자가 좋아요 누름
	int male_like(int idx);
}
