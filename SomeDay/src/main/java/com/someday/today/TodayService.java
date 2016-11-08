package com.someday.today;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
public class TodayService implements TodayDao{
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	//여자 회원이 속한 오늘 미팅 찾기
	@Override
	public TodayModel female_today(int idx) {
		return sqlSessionTemplate.selectOne("today.female_today", idx);
	}
	//남자 회원이 속한 오늘 미팅 찾기
	@Override
	public TodayModel male_today(int idx) {
		return sqlSessionTemplate.selectOne("today.male_today", idx);
	}
	
	//오늘 생성된 미팅중에 남자,또는 여자가 중복되는 회원이 있는지 채크
	@Override
	public TodayModel meeting_femalecheck(int idx) {
		return sqlSessionTemplate.selectOne("today.meeting_femalecheck", idx);
	}
	
	//오늘 생성된 미팅중에 남자,또는 여자가 중복되는 회원이 있는지 채크
	@Override
	public TodayModel meeting_malecheck(int idx) {
		return sqlSessionTemplate.selectOne("today.meeting_malecheck", idx);
	}

	//미팅 생성
	@Override
	public int create_meeting(TodayModel meeting) {
		return sqlSessionTemplate.insert("today.insertMeeting", meeting);
	}

	//여자가 좋아요를 누름
	@Override
	public int female_like(int idx) {
		return sqlSessionTemplate.update("today.female_like", idx);
	}
	
	//남자가 좋아요를 누름
	@Override
	public int male_like(int idx) {
		return sqlSessionTemplate.update("today.male_like", idx);
	}

}
