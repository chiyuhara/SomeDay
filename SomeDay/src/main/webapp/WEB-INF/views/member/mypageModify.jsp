<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>회원 정보수정</title>
<script>
function openZipcode(){
	var url="zipcodeCheck.action";
	open(url, "confirm","toolbar=no, location=no, status=no, menubar=no, scrollbars=yes, resizable=no, width=410, height=400");
}

function validation() {
	
	var frm = document.join;
	
	if (frm.pass.value == "") {
		alert("비밀번호를 입력해주세요.");
		return false;
	}
		if (frm.password2.value != frm.pass.value) {
		alert("입력한 비밀번호가 서로 다릅니다.!");
		return false;
	}
		if (frm.nick.value == "") {
		alert("닉네임 입력해주세요.");
		return false;
	}
		if (frm.name.value == "") {
		alert("이름을 입력해주세요.");
		return false;
	}
		if(frm.age.value == ""){
		alert("나이를 입력해주세요.");
		return false;
	}	
		if(frm.area.value == ""){
		alert("주소를 입력해주세요.");
		return false;
	}
		if(frm.email.value == ""){
		alert("E-mail를 입력해주세요.");
		return false;
	}
		if(frm.phone.value == ""){
		alert("전화번호를 입력해주세요.");
		return false;
	}
		if(frm.addr1.value == ""){
		alert("주소를 입력해주세요.");
		return false;
	}
		if(frm.addr2.value == ""){
		alert("상세주소를 입력해주세요.");
		return false;
	}
		if(frm.intro.value == ""){
		alert("자기소개를 입력해주세요.");
		return false;
	}		

	alert("수정되었습니다");
	return true;
}
</script>
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
</head>

<body>
	<table width="600" border="0" cellspacing="0" cellpadding="2">
		<h2>회원 정보수정</h2>
	</table>

	<form:form commandName="member" name="join" action="update.action" method="post" enctype="multipart/form-data">
		
		<table width="600" border="0" cellspacing="0" cellpadding="2">	
			<tr>
				<td width="200">아이디</td>
				<td>${member.id }</td>
			</tr>
			<tr>
				<td width="200">비밀번호</td>
				<td><form:password class="txt w200" path="pass" showPassword="true" maxlength="10"/>
				</td>
			</tr>
			<tr>
				<td width="200">비밀번호확인</td>
				<td><form:password class="txt w200" path="pass2" showPassword="true"/>
				</td>
			</tr>
			<tr>
				<td width="200">닉네임</td>
				<td>
				<form:input class="txt w200" path="nick" maxlength="5"/>
				</td>
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
				<td><form:input id="email" name="email" type="text" path="email"/>@     
               		<form:input id="email2" name="email2" type="text" path="email2" disabled="true"/>
					<form:select id="selectEmail" name="selectEmail" path="selectEmail">
					<form:option value="" label="- 이메일 선택  -" />
					<form:option value="naver.com" label="naver.com" />
					<form:option value="daum.net" label="daum.net" />
					<form:option value="nate.com" label="nate.com" />
					<form:option value="hotmail.com" label="hotmail.com" />
					<form:option value="yahoo.com" label="yahoo.com" />
					<form:option value="empas.com" label="empas.com" />	
					<form:option value="korea.com" label="korea.com" />	
					<form:option value="dreamwiz.com" label="dreamwiz.com" />	
					<form:option value="gmail.com" label="gmail.com" />	
					<form:option value="1" label="직접입력" />	
					</form:select>
				</td>
			</tr>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
//이메일 입력방식 선택
$('#selectEmail').change(function(){
$("#selectEmail option:selected").each(function () {       
       if($(this).val()== '1'){ //직접입력일 경우
          $("#email2").val('');                        //값 초기화
          $("#email2").attr("disabled",false); //활성화
       }else{ //직접입력이 아닐경우
          $("#email2").val($(this).text());      //선택값 입력
          $("#email2").attr("disabled",true); //비활성화
       }
   });
});
</script>			
			<tr>
				<td width="200">전화번호</td>
				<td>
					<form:select path="phone3">
					<form:option value="010" label="010" />
					<form:option value="011" label="011" />
					<form:option value="016" label="016" />
					<form:option value="017" label="017" />
					<form:option value="019" label="019" />	
					</form:select>-
					<form:input type="phone" class="txt w200" path="phone"/>-
					<form:input type="phone2" class="txt w200" path="phone2"/> 
				</td>
			</tr>
			<tr>
				<td width="200">주민번호</td>
				<td>${member.num1} - ${member.num2}</td>
				</td>
			</tr>	
 			 <tr>
				<td height="30" align="left">우편번호</td>
				<td align="left">
				<form:input class="txt w200" path="zipcode"/>
				<input type="button" value="우편번호찾기" onclick="return openZipcode()" class="BBUTTON">
				</td>
			</tr>
			<tr>
				<td height="30" align="left" valign="top">주 소</td>
				<td height="30" align="left">
				<form:input class="txt w200" path="area"/><br>
				<form:input class="txt w200" path="addr1" /><br>
				<form:input class="txt w200" path="addr2"/>
			</td>
			<tr>
				<td width="200">가입일</td>
				<td>${member.times}</td>
			</tr>
			<tr>
				<td width="200">소개</td>
				<td><form:input class="txt w200" path="intro"/>
				</td>
			</tr>
			<tr>
				<td width="100">첨부파일</td>
				<td width="500">
					<s:file name="upload" theme="simple"/>
				</td>
			</tr>
		</table>
		
		<br></br>
		
		<div>
					<input name="submit" type="submit" value="수정" class="button" onclick="return validation();" />
					<input name="list" type="button" value="목록" class="button" onclick="javascript:location.href='mypageAction.action'" />
				</div>
	</form:form>
	
	<br></br>
	
</body>
</html>