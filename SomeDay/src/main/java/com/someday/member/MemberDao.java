package com.someday.member;

import java.util.List;

import com.someday.member.MemberModel;

public interface MemberDao {

	//회원가입
	public Object insertMember(MemberModel mem);
	
	//우편번호
	public List<ZipcodeModel> zipcodeCheck(ZipcodeModel zipcodeModel) throws Exception;
	
	//나이성별등록
	public Object AgeGender(MemberModel ag);
	
	//아이디 찾기
	public MemberModel inputIdCheck(String id) throws Exception;
	
	//my_idx 성별이 여자인지
	MemberModel myGenderfemale(int idx);
	
	//my_idx 성별이 남자인지
	MemberModel myGendermale(int idx);
}




