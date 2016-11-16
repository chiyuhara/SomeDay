package com.someday.qna;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.someday.qna.QnAModel;

@Service
public class QnAService implements QnADao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	// qna �� ���
	@Override
	public List<QnAModel> qnaList() {
		return sqlSessionTemplate.selectList("qna.qnaList");
	}

	// �������� �˻�
	@Override
	public List<QnAModel> qnaSearch0(String search) {
		return sqlSessionTemplate.selectList("qna.qnaSearch0", "%" + search + "%");
	}

	// �������� �˻�
	@Override
	public List<QnAModel> qnaSearch1(String search) {
		return sqlSessionTemplate.selectList("qna.qnaSearch1", "%" + search + "%");
	}

	// �ۼ��ڷ� �˻�
	@Override
	public List<QnAModel> qnaSearch2(String search) {
		return sqlSessionTemplate.selectList("qna.qnaSearch2", "%" + search + "%");
	}

	// qna �� ����
	@Override
	public QnAModel qnaView(int idx) {
		return sqlSessionTemplate.selectOne("qna.qnaView", idx);
	}

	// ��۸��
	@Override
	public List<QnAcommModel> qnacommList(int idx) {
		return sqlSessionTemplate.selectList("qna.qnacommList", idx);
	}

	// ��۾���
	@Override
	public int qnacommWrite(QnAcommModel qnacommModel) {
		return sqlSessionTemplate.insert("qna.qnacommWrite", qnacommModel);
	}

	// ��ۻ���
	@Override
	public int qnacommDelete(QnAcommModel qnacommModel) {
		return sqlSessionTemplate.delete("qna.qnacommDelete", qnacommModel);
	}

	// qna �۾��� 
	@Override
	public int qnaWrite(QnAModel qnaModel) {
		return sqlSessionTemplate.insert("qna.qnaWrite", qnaModel);
	}

	// qna �� ���� 
	@Override
	public int qnaModify(QnAModel qnaModel) {
		return sqlSessionTemplate.update("qna.qnaModify", qnaModel);
	}

	// qna �� ����  
	@Override
	public int qnaDelete(int idx) {
		return sqlSessionTemplate.delete("qna.qnaDelete", idx);
	}

}
