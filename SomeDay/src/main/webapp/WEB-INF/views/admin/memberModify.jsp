<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!--PG크로스브라우징필수내용 시작-->
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<!--PG크로스브라우징필수내용 끝-->
<!-- 스마트디자인에서는 JQuery가 내장되어있습니다. 추가로 호출하면 충돌이 생길 수 있습니다. -->
<script type="text/javascript" src="/pet/resources/js/common.js"></script>
<!-- 해당 JS는 플래시를 사용하기 위한 스크립트입니다. -->
<meta name="google-site-verification"
	content="tvlinYJSu0jx2mqhjOag5X0T655GMY0lg1WjIMX7r3c" />
<link rel="canonical" href="http://okidogki.com/member/join.html" />
<link rel="alternate" href="http://m.okidogki.com/member/join.html" />
<link rel="shortcut icon" href="/web/upload/favicon_20150707144445.ico" />
<link rel="stylesheet" type="text/css" href="/pet/resources/css/d.css" />
<script type="text/javascript" src="/pet/resources/js/cid.generate.js"></script>
<script type="text/javascript" src="/pet/resources/js/wcslog.js"></script>
<script type="text/javascript" src="/pet/resources/js/cc.js"></script>
<script type="text/javascript">

	function check() {

		var f = document.join;

		 if (f.pass.value == "") {
	         alert("비밀번호를 입력해주십시요.");
	         f.pass.focus();
	         return false;
	      }

	      if (f.pass.value.length<4 || f.pass.value.length>8) {
	         alert("비밀번호는 4자 이상 8자 이하로 입력하셔야 합니다.");
	         f.pass.select(); //모두선택된 상태에서 focus
	         return false;
	      } 

/* 	      if (!idPs.test(f.pass.value)) {
	         alert("유효한 비밀번호 형식이 아닙니다.");
	         f.pass.value = ""; //id값을 비워줌
	         f.pass.focus(); //id focus이동
	         return false;]
	      }  */

	      var psNum = f.pass.value.search(/[^(0-9)]/);
	      var psEng = f.pass.value.search(/[^(a-z)]/);
	      var spe =   f.pass.value.search(/[`~!@#$%^&*()_+]/);

	      if (psNum < 0 || psEng < 0 || spe < 0) {
	         alert("비밀번호는 숫자와 영문자 특수문자를 혼용하여야 합니다.");
	         f.pass.select();
	         return false;
	      }
	   
	      if (f.pass2.value == "") {
	         alert("비밀번호 확인을 해주십시요.");
	         f.pass2.select();
	         return false;
	      }

	      if (f.pass.value != f.pass2.value) {
	         alert("비빌번호를 다르게 입력했습니다.");
	         f.pass2.select();
	         return false;
	      } 
	      if (f.nick.value == "") {
		   		alert("닉네임을 입력해주십시요.");
		   		f.nick.focus();
		        return false;
		    } 
	      
		 if (f.name.value == "") {
			alert("이름을 입력하세요");
			f.name.focus();
			return false;
		}

		 if(f.phone.value=="") {
	         alert("핸드폰번호 앞자리를 입력해주세요.");
	         f.phone.focus();
	         return false;     
	      }
		 
		 if(f.phone2.value=="") {
	         alert("핸드폰번호 뒷자리를 입력해주세요.");
	         f.phone2.focus();
	         return false;     
	      }
		 if (f.email.value == "") {
			alert("이메일을 입력하세요");
			f.email.focus();
			return false;
		}
	      if (f.selectEmail.value == "") {
				alert("이메일을 선택하세요");
				f.selectEmail.focus();
				return false;
			}
		return true;
	}
	 
	function openZipcode(){
		var url="zipcodeCheckForm";
		open(url, "confirm","toolbar=no, location=no, status=no, menubar=no, scrollbars=yes, resizable=no, width=410, height=400");
	}

	
	
</script>

<link rel="stylesheet" type="text/css" href="/pet/resources/css/c.css" />

<div id="header">
   
<!-- 메뉴 시작 -->    

    		<div id="container">
			<div class="contents1">

<div class="titleArea">
    <h2><img src="/pet/resources/images/SkinImg/modify.jpg" alt="회원가입"></h2>
</div> 
			<form:form commandName="member" action="/someday/admin/memberModifyEnd"
				method="post" name="join" onsubmit="return check()">
				<div class="xans-element- xans-member xans-member-join">


					<div class="boardWrite ">
						<h3>기본정보</h3>
						<p class="required">정보 수정이 가능합니다.</p>
						<div class="boardWrite "></div>
						<table border="1" summary="">
							<!-- <caption>회원가입</caption> -->


						</table>
						<table border="1" summary="">
							<!-- <caption>회원 기본정보</caption> -->

							<tbody>
								<tr>
									<th scope="row">아이디</th>
									<td>${member.id }<form:input type="hidden"
											class="txt w200" path="id" />
									</td>
								</tr>
								<tr>
									<th scope="row">비밀번호</th>
									<td><form:input type="password" class="txt w200"
											path="pass"  /> <font color="red"><form:errors
												path="pass" /></font></td>
								</tr>
								<tr>
									<th scope="row">비밀번호 확인</th>
									<td><form:input type="password" class="txt w200"
											path="pass2"  /> <font color="red"><form:errors
												path="pass2" /></font></td>
								</tr>
								
								<tr>
									<th scope="row">닉네임</th>
									<td><form:input type="text" name="nick" class="txt w200"
											path="nick"  /> <font color="red"><form:errors
												path="nick" /></font></td>
								</tr>


								<tr>
									<th scope="row">이름</th>
									<td>${member.name }<form:input type="hidden"
											class="txt w200" path="name" />
									</td>
								</tr>
								
								<tr>
									<th scope="row">나이</th>
									<td>${member.age }<form:input type="hidden"
											class="txt w200" path="age" />
									</td>
								</tr>
								
								<tr>
									<th scope="row">성별</th>
									<td>${member.gender }<form:input type="hidden"
											class="txt w200" path="gender" />
									</td>
								</tr>
								
								<tr>
									<th scope="row">혈액형</th>
									<td>${member.bloodgroup }<form:input type="hidden"
											class="txt w200" path="bloodgroup" />
									</td>
								</tr>	
															
								<tr>
								<th scope="row" id="nameTitle">E-Mail</th>
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
				<th scope="row" id="nameTitle">전화번호</th>
				<td>
					<form:select name="phone3" path="phone3">
					<form:option value="010" label="010" />
					<form:option value="011" label="011" />
					<form:option value="016" label="016" />
					<form:option value="017" label="017" />
					<form:option value="019" label="019" />	
					</form:select>-
					<form:input name="phone" type="phone" class="txt w200" path="phone" maxlength="4"/>-
					<form:input name="phone2" type="phone2" class="txt w200" path="phone2" maxlength="4"/> 
				</td>
			</tr>
													
								<tr>
									<th scope="row">혈액형</th>
									<td>${member.num1} - ${member.num2}<form:input type="hidden"
											class="txt w200" path="num1" />
									</td>
								</tr>	
								
																
								<tr class="">

								</tr>
								
								<tr>
								<th scope="row" id="nameTitle">우편번호</th>
				<td align="left">
				<form:input name="zipcode" class="txt w200" path="zipcode"/>
				<input type="button" value="우편번호찾기" onclick="openZipcode()" class="BBUTTON">
				</td>
			</tr>
			<tr>
				<th scope="row" id="nameTitle">주소</th>
				<td height="30" align="left">
				<form:input name="area" class="txt w200" path="area"/><br>
				<form:input name="addr1" class="txt w200" path="addr1" /><br>
				<form:input name="addr2" class="txt w200" path="addr2"/>
			</td>
			</tr>
								<tr>
									<th scope="row">혈액형</th>
									<td>${member.times}<form:input type="hidden"
											class="txt w200" path="times" />
									</td>
								</tr>	
							
							
							<tr>
								<th scope="row" id="nameTitle">소개</th>
									<td><form:input type="text" class="txt w200" path="intro"
											maxlength="11"  /> <span class="ibk"></span></td>
							</tr> 

								

							</tbody>
						</table>
					</div>

<div class="btn_type_07">
					<span class="btn btnC_04 btnP_04 mr5"> <input type="submit" 
						value="수정완료" />
					</span> <span class="btn btnC_04 btnP_04 ml5"> <input type="button"
						value="취소" onclick="javascript:location.href='/someday/admin/memberadminList'" />
					</span>
				</div>
					<!-- <div style="margin: 20px auto; text-align: center;">
				<input type="submit" class="input" value="수정">
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
						value="취소" onclick="javascript:location.href='/someday/admin/memberadminList'" />
		</div> -->
				</div>
			</form:form> 
		</div>
		</div>

   
    


<!-- // container -->
		</div>
	</div>
</body>
</html>








<%-- <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
 	function check(){
		var f = document.memberModifyForm;
		
		if(f.passwd.value == ""){
			alert("비밀번호를 입력하세요");
			f.passwd.focus();
			return false;
		}else if((f.name.value == "") || (f.name.value.length <= 1)){
			alert("이름을 제대로 입력해 주세요");
			f.name.focus();
			return false;
		}else if(f.id.value == ""){
			alert("아이디를 입력하세요");
			f.id.focus();
			return false;
		}else if(f.passwd.value != f.passwd2.value){
			alert("비밀번호가 일치하지 않습니다");
			f.passwd.value = "";
			f.passwd2.value = "";
			f.passwd.focus();
			return false;
		}else if(f.email.value == ""){
			alert("이메일을 제대로 입력해주세요");
			f.email.focus();
			return false;
		}else if(f.cellphone.value == ""){
			alert("휴대폰 번호를 입력하세요");
			f.cellphone.focus();
			return false;
		}
		return true;
	} 
 	
</script>
<div class="mypage_grp">
	<div class="mypage_list">
		<ul>
			<li class="on"><a href="/TorrentBox/mypage/memberMypage.torrent">회원정보수정</a></li>
			<li><a href="/TorrentBox/mypage/reserveList.torrent">예매내역</a></li>
			<li><a href="/TorrentBox/mypage/cancelList.torrent">취소내역</a></li>
			<li><a href="/TorrentBox/mypage/wishList.torrent">보고싶은 영화</a></li>
			<li><a href="/TorrentBox/mypage/torrentPage.torrent">토렌트 코인</a></li>
		</ul>
	</div>
	<div class="mypage_ct">
		<h3 class="sub_tit">회원정보 수정</h3>
		<form name="memberModifyForm"
			action="/TorrentBox/member/memberModify.torrent" method="post"
			onsubmit="return check()">

			<div class="tbl_type_01">
				<table>
					<caption>아이디,비밀번호,비밀번호확인,이름,생년월일,전화번호,이메일,수신확인 수정하는 표</caption>
					<colgroup>
						<col style="width: 120px" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">아이디</th>
							<td>${member.id} <input type="hidden" value="${member.id}"
								name="id" />
							</td>
						</tr>
						<tr>
							<th scope="row">비밀번호</th>
							<td><input type="password" class="txt w200" name="passwd" />
								<span class="bk"><font color="red">현재 사용중인 비밀번호를
										입력하셔야 수정됩니다.</font></span></td>
						</tr>
						<tr>
							<th scope="row">비밀번호 확인</th>
							<td><input type="password" class="txt w200" name="passwd2" /></td>
						</tr>
						<tr>
							<th scope="row">이름</th>
							<td>${member.name} <input type="hidden"
								value="${member.name}" name="name" />
							</td>
						</tr>
						<tr>
							<th scope="row">생년월일</th>
							<td><input type="text" class="txt w200" name="birth"
								value="${member.birth}" maxlength="8" /> <span class="ibk"><font
									color="red">예 20160527</font></span></td>

						</tr>


						<tr>
							<th scope="row">휴대폰</th>
							<td><input type="text" class="txt w200" name="cellphone"
								maxlength="11" value="${member.cellphone}" /> <span class="ibk"><font
									color="red">" - " 없이 입력하세요.</font></span></td>
						</tr>
						<tr>
							<th scope="row">이메일</th>
							<td><input type="text" class="txt w200" name="email"
								value="${member.email}" /></td>
						</tr>

						<tr>
							<th scope="row">이메일 수신여부</th>
							<td><span class="rdo_grp mr10"> <input type="radio"
									name="emailagree" value="Y" checked /> <font size="2">수신</font>
									<!-- <label for="email_res">수신</label> -->
							</span> <span class="rdo_grp"> <input type="radio"
									name="emailagree" value="N" /> <font size="2">수신 거부</font><br>
									<!-- <label for="email_resno">수신 거부</label> -->
							</span></td>
						</tr>
						<!-- <tr>
							<th scope="row">이메일 수신여부</th>
							<td>
								<span class="rdo_grp mr10">
									<input type="radio" name="emailagree" value="Y" checked />
									 <font color=#C36666 size="2">수신</font>
									 <label for="email_res">수신</label> 
								</span>
								<span class="rdo_grp">
									<input type="radio" name="emailagree" value="N" />
									<font color=#C36666 size="2">수신 거부</font>
									 <label for="email_resno">수신 거부</label> 
								</span>
							</td>
						</tr> -->

					</tbody>
				</table>
			</div>

			<!-- <div class="btn_type_06">
				<a href="/TorrentBox/member/memberWith.torrent" class="btn btnC_03 btnF_02 mr10">
					<span>회원탈퇴</span>
				</a>
			</div> -->

			<div class="btn_type_04">
				<a href="/TorrentBox/main.torrent" class="btn btnC_03 btnF_02 mr10">
					<span>취소</span>
				</a> <span class="btn btnC_04 btnF_02 mr10"> <input type="submit"
					value="확인" />
				</span> <a href="/TorrentBox/member/memberWith.torrent"
					class="btn btnC_03 btnF_02"> <span>회원탈퇴</span>
				</a>
			</div>
		</form>
	</div>
</div> --%>