package com.someday.admin;

import java.util.List;

import com.someday.member.MemberModel;

public interface AdminDao {
	
	 
	//ȸ�����
	public List<MemberModel> memberList();
	
	//ȸ���˻�
	List<MemberModel> memberSearch0(String search);
	
	//ȸ������
	public Object adminmemberModify(MemberModel member);
	
	//ȸ������
	public int memberDelete(String id);
	
	//idx�� ȸ������ ��������
	public MemberModel member(int idx);

	//idx�� 
	//������ ���� �ֱ�
	public Object authority(MemberModel Member);
	
	//���� ȸ����
	List<MemberModel> selectfemale();
	
	//���� ȸ����
	List<MemberModel> selectmale();
		
}


