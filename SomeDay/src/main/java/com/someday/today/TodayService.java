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
	

}
