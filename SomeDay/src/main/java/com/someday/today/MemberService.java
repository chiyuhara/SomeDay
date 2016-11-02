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
	
	//나의 회원정보를 가져옴
	@Override
	public MemberModel my(int idx){
		return sqlSessionTemplate.selectOne("member.target", idx);
	}
	
	//상대방 회원정보를 가져옴
	@Override
	public MemberModel target(int idx){
		return sqlSessionTemplate.selectOne("member.target", idx);
	}
}
