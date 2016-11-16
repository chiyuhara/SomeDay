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

	// qna 글 목록
	@Override
	public List<QnAModel> qnaList() {
		return sqlSessionTemplate.selectList("qna.qnaList");
	}

	// 제목으로 검색
	@Override
	public List<QnAModel> qnaSearch0(String search) {
		return sqlSessionTemplate.selectList("qna.qnaSearch0", "%" + search + "%");
	}

	// 내용으로 검색
	@Override
	public List<QnAModel> qnaSearch1(String search) {
		return sqlSessionTemplate.selectList("qna.qnaSearch1", "%" + search + "%");
	}

	// 작성자로 검색
	@Override
	public List<QnAModel> qnaSearch2(String search) {
		return sqlSessionTemplate.selectList("qna.qnaSearch2", "%" + search + "%");
	}

	// qna 글 보기
	@Override
	public QnAModel qnaView(int idx) {
		return sqlSessionTemplate.selectOne("qna.qnaView", idx);
	}

	// 댓글목록
	@Override
	public List<QnAcommModel> qnacommList(int idx) {
		return sqlSessionTemplate.selectList("qna.qnacommList", idx);
	}

	// 댓글쓰기
	@Override
	public int qnacommWrite(QnAcommModel qnacommModel) {
		return sqlSessionTemplate.insert("qna.qnacommWrite", qnacommModel);
	}

	// 댓글삭제
	@Override
	public int qnacommDelete(QnAcommModel qnacommModel) {
		return sqlSessionTemplate.delete("qna.qnacommDelete", qnacommModel);
	}

	// qna 글쓰기 
	@Override
	public int qnaWrite(QnAModel qnaModel) {
		return sqlSessionTemplate.insert("qna.qnaWrite", qnaModel);
	}

	// qna 글 수정 
	@Override
	public int qnaModify(QnAModel qnaModel) {
		return sqlSessionTemplate.update("qna.qnaModify", qnaModel);
	}

	// qna 글 삭제  
	@Override
	public int qnaDelete(int idx) {
		return sqlSessionTemplate.delete("qna.qnaDelete", idx);
	}

}
