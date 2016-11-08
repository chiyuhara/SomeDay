package com.someday.member;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.someday.member.MemberModel;

public interface MemberDao {

	//ȸ������
	public Object insertMember(MemberModel mem);

	// �����ȣ
	public List<ZipcodeModel> zipcodeCheck(ZipcodeModel zipcodeModel) throws Exception;

	// ���̼������
	public Object AgeGender(MemberModel ag);
	
	//���̵�� IDX ã��
	public Object Idx(MemberModel mem);
	
	//Session idx�� ȸ������ ã��
	public MemberModel memberList(int idx);
	
	//���Ͼ��ε�
	public Object UpdateFile(int index, HttpServletRequest request) throws Exception;
	//�츮�� ȭ�鿡�� ������ ��� �����ʹ� HttpServletRequest�� ��ܼ� ���۵ǰ�, �װ��� HandlerMethodArgumentResolver�� �̿��Ͽ� MemberModel�� ����־���
	//�ؽ�Ʈ ������ �Ӹ��ƴ϶� ������ ���������� �Բ� ����ִ�

	//���̵� �ߺ�üũ
	public MemberModel inputIdCheck(String id) throws Exception;

	// my_idx ������ ��������
	MemberModel myGenderfemale(int idx);

	// my_idx ������ ��������
	MemberModel myGendermale(int idx);

	// �α���
	public MemberModel memberLogin(MemberModel mem);

	//���̵� ã�� ������
	public MemberModel getMember(String id);

	// ���̵�ã��
	public MemberModel idFindByName(MemberModel member);

	// ��й�ȣã��
	public MemberModel pwFindById(MemberModel member);

	// today: ��������
	MemberModel my(int idx);

	// today: ��������
	MemberModel target(int idx);

	//ȸ������ ����
	public Object memberModify(MemberModel member);
	
	//ȸ��Ż��
	public Object memberDelete(String id);

}