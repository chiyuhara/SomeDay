package com.someday.admin;

import java.util.List;

import com.someday.member.MemberModel;

public interface AdminDao {
	
	 
	//회원목록
	public List<MemberModel> memberList();
	
	//회원검색
	List<MemberModel> memberSearch0(String search);
	
	//회원수정
	public Object adminmemberModify(MemberModel member);
	
	//회원삭제
	public int memberDelete(String id);
	
	//idx로 회원정보 가져오기
	public MemberModel member(int idx);

	//idx로 
	//관리자 권한 주기
	public Object authority(MemberModel Member);
	
	//여자 회원수
	List<MemberModel> selectfemale();
	
	//남자 회원수
	List<MemberModel> selectmale();
		
}


