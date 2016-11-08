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
	
	//���̵� �ߺ�üũ
	public MemberModel inputIdCheck(String id) throws Exception;
	
	//my_idx ������ ��������
	MemberModel myGenderfemale(int idx);
	
	//my_idx ������ ��������
	MemberModel myGendermale(int idx);
	
	//�α���
	public MemberModel memberLogin(MemberModel mem);
	
	//���̵� ã�� ������
	public MemberModel getMember(String id);

	//���̵�ã��
	public MemberModel idFindByName(MemberModel member);
	
	//��й�ȣã��
	public MemberModel pwFindById(MemberModel member);
	
	//ȸ������ ����
	public Object memberModify(MemberModel member);
	
	//ȸ��Ż��
	public Object memberDelete(String id);
}