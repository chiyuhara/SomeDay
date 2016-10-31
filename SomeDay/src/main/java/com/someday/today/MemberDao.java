package com.someday.today;

public interface MemberDao {
	
	//my_idx 성별이 여자인지
	MemberModel myGenderfemale(int idx);
	
	//my_idx 성별이 남자인지
	MemberModel myGendermale(int idx);
}
