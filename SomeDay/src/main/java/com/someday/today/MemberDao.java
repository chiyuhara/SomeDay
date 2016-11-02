package com.someday.today;

public interface MemberDao {
	
	//my_idx 성별이 여자인지
	MemberModel myGenderfemale(int idx);
	
	//my_idx 성별이 남자인지
	MemberModel myGendermale(int idx);
	
	//나의 회원정보를 가져옴
	MemberModel my(int idx);
	
	//상대방 회원정보를 가져옴
	MemberModel target(int idx);
}
