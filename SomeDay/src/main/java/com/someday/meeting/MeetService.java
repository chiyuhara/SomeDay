package com.someday.meeting;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
public class MeetService implements MeetDao {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	//���� ������ ������ ������
	@Override
	public List<MeetModel> meetingList(){
		return sqlSessionTemplate.selectList("today.select-memberjoin");
		
	}
}
