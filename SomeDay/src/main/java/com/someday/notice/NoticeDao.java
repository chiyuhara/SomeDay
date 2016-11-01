package com.someday.notice;

import java.util.List;

import com.someday.notice.NoticeModel;

public interface NoticeDao {

	// 공지 글 목록
	List<NoticeModel> noticeList();

	// 공지 글 상세보기
	NoticeModel noticeView(int idx);

	// 글쓰기
	int noticeWrite(NoticeModel noticeModel);

	// 글삭제
	int noticeDelete(int idx);

	// 글수정
	int noticeModify(NoticeModel noticeModel);

	// 검색 (0=제목, 1=내용, 2=이름)
	List<NoticeModel> noticeSearch0(String search);

	List<NoticeModel> noticeSearch1(String search);

	List<NoticeModel> noticeSearch2(String search);


}
