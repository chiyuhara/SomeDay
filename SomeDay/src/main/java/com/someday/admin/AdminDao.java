package com.someday.admin;

import java.util.List;

import com.someday.member.MemberModel;

public interface AdminDao {
	
	
	//ȸ�����
	public List<MemberModel> memberList();
	
	//ȸ���˻�
	List<MemberModel> memberSearch0(String search);
	
		
}


