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
	
	//ȸ����� ���
	@Override
	public List<MemberModel> memberList() {
		return sqlSessionTemplate.selectList("member.memberList");
	}
	
	//ȸ����� �˻�
	@Override
	public List<MemberModel> memberSearch0(String search) {
		return sqlSessionTemplate.selectList("member.memberSearch0", "%"+search+"%"); 
	}
		
		
}
