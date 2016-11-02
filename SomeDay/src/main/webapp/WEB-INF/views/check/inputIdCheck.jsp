<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="./style/common.css" />
<script type="text/javascript">
	function windowclose() {
		
		opener.document.join.id.value ="${param.id}";
		opener.document.join.checkid.value=1; //아이디 중복채크를 했으면 value 1 값 저장
		window.close();
	}

	function check() {

		var f = document.checkIdForm; //문서.Form name="";

		if (f.id.value == "") {
			alert("아이디를 입력해주세요.");
			f.id.focus();
			return false;
		}

		/* 	var idNum=f.id.value.search(/[^(0-9)]/);
		 var idEng=f.id.value.search(/[^(a-z)]/);
		 if(idNum<0 || idEng<0){
		 alert("아이디는 숫자와 영문자를 혼용하셔야 합니다.");
		 f.id.select();
		 return false;
		 } */
	}
</script>
</head>
<body>
	<div id="checkid">
		<c:if test="${ick==1}">
			<table align="center">
				<tr>
					<td><font size="2"><b>${param.id}</b> 은(는) 이미 사용 중인 아이디입니다</font></td>
				</tr>
			</table>
			<form action="inputIdCheck" method="post" name="checkIdForm"
				onsubmit="return check()">
				<table>
					<tr>
						<td>아이디 <input type="text" size="10" maxlength="12" name="id" /><input
							type="submit" class="button" value="중복확인" /></td>
					</tr>
				</table>
			</form>
		</c:if>

		<c:if test="${ick==0}">
			<table align="center">
				<tr>
					<td><br> <br>입력하신 
					<b>${param.id}</b> 은(는) 사용할 수 있는 아이디입니다. <br /> <br />
						<center>
							<input type="button" value="닫기" onclick="windowclose()"
								class="button" />
						</center></td>
				</tr>
			</table>
		</c:if>
	</div>
</body>
</html>