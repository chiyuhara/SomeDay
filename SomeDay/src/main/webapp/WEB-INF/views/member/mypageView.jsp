<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>My Page</title>
<!-- <link rel="stylesheet" href="/StrutsBoard/board/css/css.css" type="text/css"> -->

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
<style>

table, th, td {
    border: 2px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: center;
}

</style>
</head>
<body>
	<div class="container">
        <div class="row">
            <div class="box"> 
               <div class="col-lg-12 text-center">
              <table width="700" border="1" cellspacing="0" cellpadding="2">
		<h2>My Page</h2>	   
	  <img src="/someday/resources/img/${member.file_savname}" width="400" height="300"/>
		<tr>
			<td width="100"><h4>IDX</h4></td>
			<td>${member.idx}</td>
			<td width="100"><h4>아이디</h4></td>
			<td>${member.id}</td>
		</tr>
		
		<tr>
			<td width="100"><h4>비밀번호</h4></td>
			<td>${member.pass}</td>
			<td width="100"><h4>닉네임</h4></td>
			<td>${member.nick}</td>
		</tr>
		
		<tr>
			<td width="100"><h4>이름</h4></td>
			<td>${member.name}</td>
			<td width="100"><h4>나이</h4></td>
			<td>${member.age}</td>
		</tr>
		
		<tr>
			<td width="100"><h4>성별</h4></td>
			<td>${member.gender}</td>
			<td width="100"><h4>혈액형</h4></td>
			<td>${member.bloodgroup}</td>
		</tr>
		
		<tr>
			<td width="100"><h4>E-Mail</h4></td>
			<td>${member.email}</td>
			<td width="100"><h4>전화번호</h4></td>
			<td>${member.phone}</td>
		</tr>
		
		<tr>
			<td width="100"><h4>주민번호</h4></td>
			<td>${member.num1} - ${member.num2}</td>
			<td width="100"><h4>우편번호</h4></td>
			<td>${member.zipcode}</td>
		</tr>
	
		<tr>
			<td width="100"><h4>지역</h4></td>
			<td>${member.area}</td>
			<td width="100"><h4>주소</h4></td>
			<td>${member.addr1}</td>
		</tr>
		
		<tr>
			<td width="100"><h4>상세주소</h4></td>
			<td>${member.addr2}</td>
		</tr>
		<tr>
			<td width="200">가입일</td>
			<td><fmt:formatDate value="${member.times}" pattern="YY.MM.dd HH:mm" /></td>
		</tr>
		
		<tr>
			<td width="100"><h4>소개</h4></td>
			<td>${member.intro}</td>
			<td width="100"><h4>사진</h4></td>
			<td>${member.file_savname}</td>
		</tr>	   
	      </table>
	       <div class="col-lg-12 text-right">
				    <input type="button" value="수정" class="btn btn-primary" onclick="javascript:location.href='${contextpath}/someday/member/MypageModify'"/>
				    <input type="button" value="회원탈퇴" class="btn btn-primary" onclick="return button_event();"/>
					<input name="list" type="button" value="메인으로" class="btn btn-primary" onclick="javascript:location.href='${contextpath}/someday/'"/>
     	 </div>
	    </div>
	   </div>
	  </div>
	</div>
	<br></br>
	
	

	<br></br>
</body>
</html>