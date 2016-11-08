package com.someday.notice;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.someday.notice.NoticecommModel;
import com.someday.notice.NoticeModel;

@Service
public class NoticeService implements NoticeDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	// 공지 글 목록
	@Override
	public List<NoticeModel> noticeList() {
		return sqlSessionTemplate.selectList("notice.noticeList");
	}
 
	// 공지 글 보기
	@Override
	public NoticeModel noticeView(int idx) {
		return sqlSessionTemplate.selectOne("notice.noticeView", idx);
	}

	// 공지 글쓰기
	@Override
	public int noticeWrite(NoticeModel noticeModel) {
		return sqlSessionTemplate.insert("notice.noticeWrite", noticeModel);
	}

	// 글삭제
	@Override
	public int noticeDelete(int idx) {
		return sqlSessionTemplate.delete("notice.noticeDelete", idx);
	}

	// 글수정
	@Override
	public int noticeModify(NoticeModel noticeModel) {
		return sqlSessionTemplate.update("notice.noticeModify", noticeModel);
	}

	// 제목으로 검색
	@Override
	public List<NoticeModel> noticeSearch0(String search) {
		return sqlSessionTemplate.selectList("notice.noticeSearch0", "%" + search + "%");
	}

	// 내용으로 검색
	@Override
	public List<NoticeModel> noticeSearch1(String search) {
		return sqlSessionTemplate.selectList("notice.noticeSearch1", "%" + search + "%");
	}

	// 작성자로 검색
	@Override
	public List<NoticeModel> noticeSearch2(String search) {
		return sqlSessionTemplate.selectList("notice.noticeSearch2", "%" + search + "%");
	}

	// 댓글목록 
	@Override
	public List<NoticecommModel> noticecommList(int idx) {
		return sqlSessionTemplate.selectList("notice.noticecommList", idx);
	}

	// 댓글쓰기
	@Override
	public int noticecommWrite(NoticecommModel noticecommModel) {
		return sqlSessionTemplate.insert("notice.noticecommWrite", noticecommModel);
	}

	// 댓글삭제
	@Override
	public int noticecommDelete(NoticecommModel noticecommModel) {
		return sqlSessionTemplate.delete("notice.noticecommDelete", noticecommModel);
	}


}
