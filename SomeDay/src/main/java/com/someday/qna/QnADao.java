package com.someday.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface QnADao {

	// qna 글 목록
	List<QnAModel> qnaList();

	// 검색 (0=제목, 1=내용, 2=이름)
	List<QnAModel> qnaSearch0(String search);

	List<QnAModel> qnaSearch1(String search);

	List<QnAModel> qnaSearch2(String search);

	// qna 글 상세보기
	QnAModel qnaView(int idx);

	// 댓글목록
	List<QnAcommModel> qnacommList(int idx);

	// 댓글쓰기
	int qnacommWrite(QnAcommModel qnacommModel);

	// 댓글삭제
	int qnacommDelete(QnAcommModel qnacommModel);

	// qna 글 쓰기
	int qnaWrite(QnAModel qnaModel);

	// qna 글 수정
	int qnaModify(QnAModel qnaModel);

	// qna 글 삭제
	int qnaDelete(int idx);

	// 아이디로 IDX 찾기
	public Object Idx(QnAModel qnaModel);

	// 파일업로드
	public Object UpdateFile(int index, HttpServletRequest request) throws Exception;

}
