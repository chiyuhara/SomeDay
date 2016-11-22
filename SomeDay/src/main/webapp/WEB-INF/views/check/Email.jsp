<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script>
	function Email(){
		var form = document.authenform;
	        authNum = ${authNum};
		
		if(!form.authnum.value){
			alert("인증번호를 입력하세요");
			return false;
		}
		if(form.authnum.value!=authNum){
			alert("틀린 인증번호입니다. 인증번호를 다시 입력해주세요");
			form.authNum.value="";
			return false;
		}
		if(form.authnum.value==authNum){
			alert("인증완료");
			opener.document.join.mailCheck.value="1";
			authNum = "";
			self.close();
			return true;
		}
	}
</script>
</head>
<body>

<center>
<br /><br />
<h5>인증 번호7자리를 입력하세요</h5>

<div class="container">
	<form method="post" name="authenform" action="${contextpath}/someday/member/emailForm" onSubmit="return Email()";>
			<input type="text" name="authnum"><br /><br />
			<input type="submit" class="btn btn-info" value="확인">
	</form>
</div>
</center>
</body>
</html>