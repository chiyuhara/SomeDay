package com.someday.qna;

import java.util.List;

public interface QnADao {

	// qna �� ���
	List<QnAModel> qnaList();

	// �˻� (0=����, 1=����, 2=�̸�)
	List<QnAModel> qnaSearch0(String search);

	List<QnAModel> qnaSearch1(String search);

	List<QnAModel> qnaSearch2(String search);

	// qna �� �󼼺���
	QnAModel qnaView(int idx);

	// ��۸�� 
	List<QnAcommModel> qnacommList(int idx);

	// ��۾��� 
	int qnacommWrite(QnAcommModel qnacommModel);

	// ��ۻ��� 
	int qnacommDelete(QnAcommModel qnacommModel);

	// qna �� ���� 
	int qnaWrite(QnAModel qnaModel);

	// qna �� ���� 
	int qnaModify(QnAModel qnaModel);

	// qna �� ���� 
	int qnaDelete(int idx);

}
