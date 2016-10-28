package com.someday.notice;

import java.util.List;

import com.someday.notice.NoticeModel;

public interface NoticeDao {
	
	//공지 글 목록
	List<NoticeModel> noticeList();

	//공지 글 상세보기
	NoticeModel noticeView(int idx);
	
	//글쓰기
	int noticeWrite(NoticeModel noticeModel);

}
