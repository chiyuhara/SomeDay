package com.someday.matching;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.someday.member.MemberModel;

@Service
public class MatchingService implements MatchingDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	// ��Ī ������ ���ڸ� ������
	@Override
	public MatchingModel matchNum() {
		return sqlSessionTemplate.selectOne("member.all_matching_num");
	}

	// �������� ���� 1�� ������
	@Override
	public MemberModel random_female() {
		return sqlSessionTemplate.selectOne("member.random_female");
	}

	// �������� ���� 1�� ������
	@Override
	public MemberModel random_male() {
		return sqlSessionTemplate.selectOne("member.random_male");
	}

}
