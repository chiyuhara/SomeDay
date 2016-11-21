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
			
	//회원수정하기
	@Override
	public Object adminmemberModify(MemberModel member) {
		return sqlSessionTemplate.update("member.adminupdateMember", member);
	}
	
	//회원삭제
	@Override
	public int memberDelete(String id) {
		return sqlSessionTemplate.delete("member.deleteMember",id);
	}
	
	//idx로 회원정보 가져오기
	@Override
	public MemberModel member(int idx){
		
		return sqlSessionTemplate.selectOne("member.selectOne",idx);
	}
	
    //관리자 권한 주기
	 @Override
	public Object authority(MemberModel member){
	   return sqlSessionTemplate.update("member.authority",member);
    }
	 
	//여자 회원수
	public List<MemberModel> selectfemale(){
		return sqlSessionTemplate.selectList("member.selectfemale");
	}
	
	//남자 회원수
	public List<MemberModel> selectmale(){
		return sqlSessionTemplate.selectList("member.selectmale");
	}
		
}
