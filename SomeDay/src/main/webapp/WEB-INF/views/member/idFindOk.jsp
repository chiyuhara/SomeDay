<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>아이디 찾기</title>


</head>
<body>
<center>

<br>
		
<table width="100%" height="250" border="0" cellpadding="0" cellspacing="0" >
<tr>
	<td valign="top" align="center">
	<table width="450" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="50" valign="top"></td>
		<td width="400" valign="top">
	
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="40"></td>
		</tr>
		<tr>
			<td align="center"><font size="3"><strong>아이디 검색 결과</strong></td>
		</tr>
		<tr><td height="10"></td></tr>
		</table>
	
		<table width="100%" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td align="center">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="270">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr height="10">
					<td></td>
					<td></td>
				</tr>
				<tr height="30">
					<td width="100" align="left">아이디</td>
					&nbsp;<td>${member.id}</td>
				</tr>

				<tr height="10">
					<td></td>
					<td></td>
				</tr>
				</table>
				</td>
				<td width="60" align="center">
					<input type="button" value="뒤로가기" onclick="location.href='/someday/member/findForm'">
				</td>
			</tr>
			</table>
			</td>
		</tr>
		</table>
		</td>
	</tr>
	</table>
	
	</td>
</tr>
</table>
</center>
</body>
</html>
