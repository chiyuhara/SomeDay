package com.someday.today;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
public class TodayService implements TodayDao{
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	//���� ȸ���� ���� ���� ���� ã��
	@Override
	public TodayModel female_today(int idx) {
		return sqlSessionTemplate.selectOne("today.female_today", idx);
	}
	//���� ȸ���� ���� ���� ���� ã��
	@Override
	public TodayModel male_today(int idx) {
		return sqlSessionTemplate.selectOne("today.male_today", idx);
	}
	
	//���� ������ �����߿� ����,�Ǵ� ���ڰ� �ߺ��Ǵ� ȸ���� �ִ��� äũ
	@Override
	public TodayModel meeting_femalecheck(int idx) {
		return sqlSessionTemplate.selectOne("today.meeting_femalecheck", idx);
	}
	
	//���� ������ �����߿� ����,�Ǵ� ���ڰ� �ߺ��Ǵ� ȸ���� �ִ��� äũ
	@Override
	public TodayModel meeting_malecheck(int idx) {
		return sqlSessionTemplate.selectOne("today.meeting_malecheck", idx);
	}

	//���� ����
	@Override
	public int create_meeting(TodayModel meeting) {
		return sqlSessionTemplate.insert("today.insertMeeting", meeting);
	}

	//���ڰ� ���ƿ並 ����
	@Override
	public int female_like(int idx) {
		return sqlSessionTemplate.update("today.female_like", idx);
	}
	
	//���ڰ� ���ƿ並 ����
	@Override
	public int male_like(int idx) {
		return sqlSessionTemplate.update("today.male_like", idx);
	}

}
