package com.someday.matching;

import com.someday.member.MemberModel;

public interface MatchingDao {

	// matching: 매칭 가능한 숫자를 가져옴
	MatchingModel matchNum();

	// matching: 랜덤으로 여자 1명 가져옴
	MemberModel random_female();

	// matching: 랜덤으로 남자 1명 가져옴
	MemberModel random_male();
}
