package com.someday.today;

public interface MemberDao {
	
	//my_idx ������ ��������
	MemberModel myGenderfemale(int idx);
	
	//my_idx ������ ��������
	MemberModel myGendermale(int idx);
	
	//���� ȸ�������� ������
	MemberModel my(int idx);
	
	//���� ȸ�������� ������
	MemberModel target(int idx);
}
