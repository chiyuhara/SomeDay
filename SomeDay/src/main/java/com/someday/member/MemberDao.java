package com.someday.member;

import java.util.List;

import com.someday.member.MemberModel;

public interface MemberDao {

	//ȸ������
	public Object insertMember(MemberModel mem);
	
	//�����ȣ
	public List<ZipcodeModel> zipcodeCheck(ZipcodeModel zipcodeModel) throws Exception;
	
	//���̼������
	public Object AgeGender(MemberModel ag);
	
	//���̵� ã��
	public MemberModel inputIdCheck(String id) throws Exception;
	
	//my_idx ������ ��������
	MemberModel myGenderfemale(int idx);
	
	//my_idx ������ ��������
	MemberModel myGendermale(int idx);
}




