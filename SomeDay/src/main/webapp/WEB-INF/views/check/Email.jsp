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
			alert("������ȣ�� �Է��ϼ���");
			return false;
		}
		if(form.authnum.value!=authNum){
			alert("Ʋ�� ������ȣ�Դϴ�. ������ȣ�� �ٽ� �Է����ּ���");
			form.authNum.value="";
			return false;
		}
		if(form.authnum.value==authNum){
			alert("�����Ϸ�");
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
<h5>���� ��ȣ7�ڸ��� �Է��ϼ���</h5>

<div class="container">
	<form method="post" name="authenform" action="${contextpath}/someday/member/emailForm" onSubmit="return Email()";>
			<input type="text" name="authnum"><br /><br />
			<input type="submit" class="btn btn-info" value="Ȯ��">
	</form>
</div>
</center>
</body>
</html>