package com.someday.notice;

import java.util.List;

import com.someday.notice.NoticeModel;

public interface NoticeDao {
	
	//���� �� ���
	List<NoticeModel> noticeList();

	//���� �� �󼼺���
	NoticeModel noticeView(int idx);
	
	//�۾���
	int noticeWrite(NoticeModel noticeModel);

}
