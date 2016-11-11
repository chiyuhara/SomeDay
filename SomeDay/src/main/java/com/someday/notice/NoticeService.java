package com.someday.notice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.someday.notice.NoticecommModel;
import com.someday.util.FileUtils;
import com.someday.notice.NoticeModel;

@Service
public class NoticeService implements NoticeDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;


	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	// ���� �� ���
	@Override
	public List<NoticeModel> noticeList() {
		return sqlSessionTemplate.selectList("notice.noticeList");
	}

	// ���� �� ����
	@Override
	public NoticeModel noticeView(int idx) {
		return sqlSessionTemplate.selectOne("notice.noticeView", idx);
	}

	// ��ȸ�� ����
	@Override
	public int noticeUpdateReadhit(int idx) {
		return sqlSessionTemplate.update("notice.noticeUpdateReadhit", idx);
	}

	// ���� �۾���
	@Override
	public int noticeWrite(NoticeModel noticeModel) {
		return sqlSessionTemplate.insert("notice.noticeWrite", noticeModel);
	}

	// idx ��������
	@Override
	public Object Idx(NoticeModel noticeModel) {
		return sqlSessionTemplate.selectOne("notice.noticeselectIdx", noticeModel);
	}

	// ���� ���ε�
	@Override
	public Object UpdateFile(int index, HttpServletRequest request) throws Exception {

		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(index, request);
		System.out.println(list);

		for (int i = 0, size = list.size(); i < size; i++) {
			return sqlSessionTemplate.update("notice.updateFile", list.get(i));
		}
		return list;

	}

	// �ۻ���
	@Override
	public int noticeDelete(int idx) {
		return sqlSessionTemplate.delete("notice.noticeDelete", idx);
	}

	// �ۼ���
	@Override
	public int noticeModify(NoticeModel noticeModel) {
		return sqlSessionTemplate.update("notice.noticeModify", noticeModel);
	}

	// �������� �˻�
	@Override
	public List<NoticeModel> noticeSearch0(String search) {
		return sqlSessionTemplate.selectList("notice.noticeSearch0", "%" + search + "%");
	}

	// �������� �˻�
	@Override
	public List<NoticeModel> noticeSearch1(String search) {
		return sqlSessionTemplate.selectList("notice.noticeSearch1", "%" + search + "%");
	}

	// �ۼ��ڷ� �˻�
	@Override
	public List<NoticeModel> noticeSearch2(String search) {
		return sqlSessionTemplate.selectList("notice.noticeSearch2", "%" + search + "%");
	}

	// ��۸��
	@Override
	public List<NoticecommModel> noticecommList(int idx) {
		return sqlSessionTemplate.selectList("notice.noticecommList", idx);
	}

	// ��۾���
	@Override
	public int noticecommWrite(NoticecommModel noticecommModel) {
		return sqlSessionTemplate.insert("notice.noticecommWrite", noticecommModel);
	}

	// ��ۻ���
	@Override
	public int noticecommDelete(NoticecommModel noticecommModel) {
		return sqlSessionTemplate.delete("notice.noticecommDelete", noticecommModel);
	}

}
