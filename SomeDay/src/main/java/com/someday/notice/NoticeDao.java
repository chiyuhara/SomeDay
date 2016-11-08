package com.someday.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.someday.member.MemberModel;
import com.someday.notice.NoticeModel;

public interface NoticeDao {

	// ���� �� ���
	List<NoticeModel> noticeList();

	// ���� �� �󼼺���
	NoticeModel noticeView(int idx);

	// �۾��� 
	int noticeWrite(NoticeModel noticeModel);
	
	//���Ͼ��ε�
	public Object UpdateFile(NoticeModel noticeModel, HttpServletRequest request) throws Exception;

	// �ۻ���
	int noticeDelete(int idx);

	// �ۼ���
	int noticeModify(NoticeModel noticeModel);

	// �˻� (0=����, 1=����, 2=�̸�)
	List<NoticeModel> noticeSearch0(String search);

	List<NoticeModel> noticeSearch1(String search);

	List<NoticeModel> noticeSearch2(String search);

	// ��۸�� 
	List<NoticecommModel> noticecommList(int idx);

	// ��۾���
	int noticecommWrite(NoticecommModel noticecommModel);

	// ��ۻ���
	int noticecommDelete(NoticecommModel noticecommModel);


}
