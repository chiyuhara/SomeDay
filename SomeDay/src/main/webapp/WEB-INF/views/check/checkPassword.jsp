<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>비밀번호 확인</title>

<link rel="stylesheet" type="text/css" href="./style/boardview.css" />
<link rel="stylesheet" type="text/css" href="./style/common.css" />
</head>
<script language="javascript">
	window.onload = function(){
	var chk = ${deleteCheck}
	if(chk == 1){
		alert('탈퇴 되었습니다');
		window.opener.location.href = "${contextpath}/someday/";		
		window.close();
	}
	if(chk == 0){
		alert('비밀번호가 틀렸습니다');
		history.back();
	}
}
</script>
<body>
	<div id="checkid">
		<form name="delete" action="memberDelete" method="post">
			<table width="250" border="0" cellspacing="0" cellpadding="0"
				align="center">
				<tr bgcolor="#777777">
					<td height="1" colspan="2"></td>
				</tr>

				<tr>
					<td width="100">비밀번호 입력</td>
					<td width="150">&nbsp;&nbsp;
					<input name="pass" maxlength="20" />
						&nbsp;&nbsp;
				    <input type="submit" value="확인" class="button"></td>
				</tr>

				<tr bgcolor="#777777">
					<td height="1" colspan="2"></td>
				</tr>

			</table>

		</form>
	</div>
</body>
</html>