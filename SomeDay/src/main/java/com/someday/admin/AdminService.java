package com.someday.admin;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.someday.member.MemberModel;

@Service
public class AdminService implements AdminDao{
	   @Resource(name="sqlSessionTemplate")
	   private SqlSessionTemplate sqlSessionTemplate;
	
	//회원목록 출력
	@Override
	public List<MemberModel> memberList() {
		return sqlSessionTemplate.selectList("member.memberList");
	}
	
	//회원목록 검색
	@Override
	public List<MemberModel> memberSearch0(String search) {
		return sqlSessionTemplate.selectList("member.memberSearch0", "%"+search+"%"); 
	}
		
		
}
