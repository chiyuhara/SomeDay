package com.someday.matching;

import com.someday.member.MemberModel;

public interface MatchingDao {

	// matching: ��Ī ������ ���ڸ� ������
	MatchingModel matchNum();

	// matching: �������� ���� 1�� ������
	MemberModel random_female();

	// matching: �������� ���� 1�� ������
	MemberModel random_male();
}
