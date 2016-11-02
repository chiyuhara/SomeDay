package com.someday.today;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements MemberDao{
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	//my_idx ������ ��������
	@Override
	public MemberModel myGenderfemale(int idx) {
		return sqlSessionTemplate.selectOne("member.myGenderfemale", idx);
	}
	
	//my_idx ������ ��������
	@Override
	public MemberModel myGendermale(int idx) {
		return sqlSessionTemplate.selectOne("member.myGendermale", idx);
	}
	
	//���� ȸ�������� ������
	@Override
	public MemberModel my(int idx){
		return sqlSessionTemplate.selectOne("member.target", idx);
	}
	
	//���� ȸ�������� ������
	@Override
	public MemberModel target(int idx){
		return sqlSessionTemplate.selectOne("member.target", idx);
	}
}
