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
		
}


