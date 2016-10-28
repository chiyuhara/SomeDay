package com.someday.notice;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

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

}
