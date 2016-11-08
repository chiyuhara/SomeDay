package com.someday.member;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.someday.member.MemberModel;

public interface MemberDao {

	//회원가입
	public Object insertMember(MemberModel mem);

	// 우편번호
	public List<ZipcodeModel> zipcodeCheck(ZipcodeModel zipcodeModel) throws Exception;

	// 나이성별등록
	public Object AgeGender(MemberModel ag);
	
	//아이디로 IDX 찾기
	public Object Idx(MemberModel mem);
	
	//Session idx로 회원정보 찾기
	public MemberModel memberList(int idx);
	
	//파일업로드
	public Object UpdateFile(int index, HttpServletRequest request) throws Exception;
	//우리가 화면에서 전송한 모든 데이터는 HttpServletRequest에 담겨서 전송되고, 그것을 HandlerMethodArgumentResolver를 이용하여 MemberModel에 담아주었다
	//텍스트 데이터 뿐만아니라 전송한 파일정보도 함께 담겨있다

	//아이디 중복체크
	public MemberModel inputIdCheck(String id) throws Exception;

	// my_idx 성별이 여자인지
	MemberModel myGenderfemale(int idx);

	// my_idx 성별이 남자인지
	MemberModel myGendermale(int idx);

	// 로그인
	public MemberModel memberLogin(MemberModel mem);

	//아이디 찾기 쿼리문
	public MemberModel getMember(String id);

	// 아이디찾기
	public MemberModel idFindByName(MemberModel member);

	// 비밀번호찾기
	public MemberModel pwFindById(MemberModel member);

	// today: 나의정보
	MemberModel my(int idx);

	// today: 상대방정보
	MemberModel target(int idx);

	//회원정보 수정
	public Object memberModify(MemberModel member);
	
	//회원탈퇴
	public Object memberDelete(String id);

}