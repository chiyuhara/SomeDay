package com.someday.notice;

import java.util.List;

import com.someday.notice.NoticeModel;

public interface NoticeDao {

	// ���� �� ���
	List<NoticeModel> noticeList();

	// ���� �� �󼼺���
	NoticeModel noticeView(int idx);

	// �۾���
	int noticeWrite(NoticeModel noticeModel);

	// �ۻ���
	int noticeDelete(int idx);

	// �ۼ���
	int noticeModify(NoticeModel noticeModel);

	// �˻� (0=����, 1=����, 2=�̸�)
	List<NoticeModel> noticeSearch0(String search);

	List<NoticeModel> noticeSearch1(String search);

	List<NoticeModel> noticeSearch2(String search);


}
