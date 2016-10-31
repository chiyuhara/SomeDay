package com.someday.today;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements MemberDao{
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	//my_idx 성별이 여자인지
	@Override
	public MemberModel myGenderfemale(int idx) {
		return sqlSessionTemplate.selectOne("member.myGenderfemale", idx);
	}
	
	//my_idx 성별이 남자인지
	@Override
	public MemberModel myGendermale(int idx) {
		return sqlSessionTemplate.selectOne("member.myGendermale", idx);
	}
}
