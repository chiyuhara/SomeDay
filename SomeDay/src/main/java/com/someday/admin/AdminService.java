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
			
	//ȸ�������ϱ�
	@Override
	public Object adminmemberModify(MemberModel member) {
		return sqlSessionTemplate.update("member.adminupdateMember", member);
	}
	
	//ȸ������
	@Override
	public int memberDelete(String id) {
		return sqlSessionTemplate.delete("member.deleteMember",id);
	}
	
	//idx�� ȸ������ ��������
	@Override
	public MemberModel member(int idx){
		
		return sqlSessionTemplate.selectOne("member.selectOne",idx);
	}
	
    //������ ���� �ֱ�
	 @Override
	public Object authority(MemberModel member){
	   return sqlSessionTemplate.update("member.authority",member);
    }
	 
	//���� ȸ����
	public List<MemberModel> selectfemale(){
		return sqlSessionTemplate.selectList("member.selectfemale");
	}
	
	//���� ȸ����
	public List<MemberModel> selectmale(){
		return sqlSessionTemplate.selectList("member.selectmale");
	}
		
}
