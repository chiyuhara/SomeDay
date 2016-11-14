<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>My Page</title>
<link rel="stylesheet" href="/StrutsBoard/board/css/css.css" type="text/css">
<style>

body{font:14px/1.3 Nanum Gothic,나눔고딕,sans-serif; max-width:100%;}

/* table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border-top: 1px solid #bcbcbc;
	border-bottom: 1px solid #bcbcbc;
	padding: 5px 10px;
} */
</style>
<script type="text/javascript">
	function open_win_noresizable(url, name) {
		var oWin = window.open(url, name,
				"scrollbars=no,status=no,resizable=no,width=300,height=150");
	}
	
	function button_event(){

		if (confirm("정말 삭제하시겠습니까??") == true){    //확인
			open_win_noresizable('memberDeleteForm','memberDelete')
		   

		}else{   //취소

		    return;

		}
		}
</script>
</head>

<body>
	<form:form commandName="member" name="mypageView" action="mypagedeleteAction.action" method="post" />
	<table width="600" border="0" cellspacing="0" cellpadding="2">
		<h2>My Page</h2>
		
	</table>

	<table width="600" border="0" cellspacing="0" cellpadding="2">
		<%-- <img src="image/<s:property value="resultClass.file_savname" />" /> --%><!-- 경로 수정 필요 -->
		<img src="/Hi_Admin/image/${member.file_savname}" /><!-- Hi_Admin/image -->
		<tr>
			<td width="200">IDX</td>
			<td>${member.idx}</td>
		</tr>
		<tr>
			<td width="200">아이디</td>
			<td>${member.id}</td>
		</tr>
		<tr>
			<td width="200">비밀번호</td>
			<td>${member.pass}</td>
		</tr>
		<tr>
			<td width="200">닉네임</td>
			<td>${member.nick}</td>
		</tr>
		<tr>
			<td width="200">이름</td>
			<td>${member.name}</td>
		</tr>
		<tr>
			<td width="200">나이</td>
			<td>${member.age}</td>
		</tr>
		<tr>
			<td width="200">성별</td>
			<td>${member.gender}</td>
		</tr>
		<tr>
			<td width="200">혈액형</td>
			<td>${member.bloodgroup}</td>
		</tr>
		<tr>
			<td width="200">E-Mail</td>
			<td>${member.email}</td>
		</tr>
		<tr>
			<td width="200">전화번호</td>
			<td>${member.phone}</td>
		</tr>
		<tr>
			<td width="200">주민번호</td>
			<td>${member.num1} - ${member.num2}</td>
		</tr>
		<tr>
			<td width="200">우편번호</td>
			<td>${member.zipcode}</td>
		</tr>
		<tr>
			<td width="200">지역</td>
			<td>${member.area}</td>
		</tr>
		<tr>
			<td width="200">주소</td>
			<td>${member.addr1}</td>
		</tr>
		<tr>
			<td width="200">상세주소</td>
			<td>${member.addr2}</td>
		</tr>
		<tr>
			<td width="200">가입일</td>
			<td>${member.times}</td>
		</tr>
		<tr>
			<td width="200">소개</td>
			<td>${member.intro}</td>
		</tr>
		<tr>
			<td width="200">사진</td>
			<td>${member.file_savname}</td>
		</tr>
	</table>
	<!-- 수정 페이지로 이동할 url 생성 -->
	<s:url id="viewURL" action="mypageModifyAction">
		<s:param name="idx">
			<s:property value="idx" />
		</s:param>
	</s:url>
	
	<br></br>
	
	<div>
				    <input type="button" value="수정" class="button" onclick="javascript:location.href='${contextpath}/someday/member/MypageModify'"/>
				    <input type="button" value="회원탈퇴" class="button" onclick="return button_event();"/>
					<input name="list" type="button" value="메인으로" class="button" onclick="javascript:location.href='${contextpath}/someday/'"/>

	</div>
	<br></br>
</body>
</html>