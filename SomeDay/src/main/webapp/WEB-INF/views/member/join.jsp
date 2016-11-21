<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<script type="text/javascript" src="/app/Eclog/js/cid.generate.js?vs=811645615d6b4844b2208aa3b5af791b"></script>
<script type="text/javascript" src="http://wcs.naver.net/wcslog.js"></script>
<script type="text/javascript">var EC_SDE_SHOP_NUM = 1;var SHOP = {getLanguage : function() { return "ko_KR"; },getCurrency : function() { return "KRW"; },getFlagCode : function() { return "KR"; },isMultiShop : function() { return true; },isDefaultShop : function() { return true; },getProductVer : function() { return 2; },isSDE : function() { return true; }};</script>
<script type="text/javascript">
	var EC_SDE_SHOP_NUM = 1;
	var SHOP = {
		getLanguage : function() {
			return "ko_KR";
		},
		getCurrency : function() {
			return "KRW";
		},
		getFlagCode : function() {
			return "KR";
		},
		isMultiShop : function() {
			return false;
		},
		isDefaultShop : function() {
			return true;
		},
		getProductVer : function() {
			return 2;
		},
		isSDE : function() {
			return true;
		}
	};
	
	
	function openZipcode(){
		var url="zipcodeCheckForm";
		open(url, "confirm","toolbar=no, location=no, status=no, menubar=no, scrollbars=yes, resizable=no, width=410, height=400");
	}
	
	function checkId() {

		var url = "inputIdCheck?id=" + document.join.id.value;
		var f = document.join; //문서.Form name="";

		var IDPW = /^[a-z0-9_]{4,8}$/; //아이디 비밀번호 체크표현식

		if (f.id.value == '') {
			alert("아이디를 입력해주세요.");
			f.id.focus();
			return false;
		}
	 	if (!IDPW.test(f.id.value)) {
				alert("유효한 아이디 형식이 아닙니다.");
				f.id.value = ""; //ID작성했던 값을 비워줌
				f.id.focus(); //ID focus이동
				return false;
		}
		open(
				url,
				"confirm",
				"toolbar=no, location=no, status=co, menubar=no, scrollbars=no, resizable=no, width=320, height=160");
	}
	
	 function check() {

	      var f = document.join; //문서.Form name="";
	      var idPs = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{6,16}$/; //아이디 비밀번호 체크표현식
	      var checkid = document.join.checkid.value;

	      if (f.id.value == "") {
	         alert("아이디를 입력해주세요.");
	         f.id.focus();
	         return false;
	      }

	      if (f.pass.value == "") {
	         alert("비밀번호를 입력해주십시요.");
	         f.pass.focus();
	         return false;
	      }
	      if (f.pass2.value == "") {
		         alert("비밀번호 확인을 해주십시요.");
		         f.pass2.select();
		         return false;
		      }

	      if (f.pass.value.length<4 || f.pass.value.length>8) {
	         alert("비밀번호는 4자 이상 8자 이하로 입력하셔야 합니다.");
	         f.pass.select(); //모두선택된 상태에서 focus
	         return false;
	      }

	      var psNum = f.pass.value.search(/[^(0-9)]/);
	      var psEng = f.pass.value.search(/[^(a-z)]/);
	      var spe =   f.pass.value.search(/[`~!@#$%^&*()_+]/);

	      if (psNum < 0 || psEng < 0 || spe < 0) {
	         alert("비밀번호는 숫자와 영문자 특수문자를 혼용하여야 합니다.");
	         f.pass.select();
	         return false;
	      }

	      if (f.id.value.search(f.pass.value) > -1) {
	         alert("아이디가 포함된 비밀번호는 사용할수 없습니다.");
	         f.pass.select();
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
	            alert("이름을 입력해주십시요.");
	            f.name.focus();
	            return false;
	         }

		      if (f.email.value == "") {
		         alert("이메일을 입력해주세요.");
		         f.email.focus();
		         return false;
		      }
				
		      if((f.phone.value=="") || (f.phone2.value=="")) {
			         alert("핸드폰번호를 입력해주세요.");
			         f.phone.focus();
			         return false;     
			      }
	      if ((f.num1.value == "") || (f.num1.value.length < 6)) {
	         alert("생년월일을 6자리로 입력해 주세요");
	         f.num1.focus();
	         return false;
	      }

/* 
	      var eCheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	      if (!eCheck.test(f.email.value)) {
	         alert("잘못된 이메일 형식입니다.");
	         f.email.value = "";
	         f.email.focus();
	         return false;
	      }
 */
	      
/* 	      var chkphone1=/^\d{3}$/;
	      var chkphone2=/^\d{3,4}$/;
	      var chkphone3=/^\d{4}$/;
	      //변수 3개로 나눠서 숫자길이와 형식체크합니다~
	      if((!chkphone1.test(f.phone.value)) || (!chkphone2.test(f.phone2.value)) || (!chkphone3.test(f.phone3.value))){
	         alert("유효한 핸드폰번호 형식이 아닙니다.");
	         f.phone.value="";
	         f.phone2.value="";
	         f.phone3.value="010";
	         f.phone3.focus();
	         return false;
	       }
	        
	      if(isNaN(f.phone.value) || isNaN(f.phone2.value) || isNaN(f.phone3.value)) { 
	      //isNaN(숫자인지 확인하는 함수)변수이용 전화번호 숫자체크 숫자인지 아닌지만 체크.
	         alert("핸드폰번호는 숫자로만 입력해주시기 바랍니다.");
	          f.phone.value="";
	          f.phone2.value="";
	          f.phone3.value="010";
	          f.phone3.focus();
	          return false;
	        } */

	      if (f.zipcode.value == "") {
	         alert("우편번호를 검색하여 입력해주세요");
	         f.zipcode.focus();
	         return false;
	      }
	      if (f.area.value.length == 0) {
		         alert("지역을 입력하세요");
		         f.addr.focus();
		         return false;
		      }

	      if (f.addr1.value.length == 0) {
	         alert("집주소를 입력하세요");
	         f.addr1.focus();
	         return false;
	      }

	      if (f.addr2.value.length == 0) {
	         alert("상세주소를 입력하세요");
	         f.addr2.focus();
	         return false;
	      }
	      if(f.intro.value.length == 0){
	    	  alert("자기소개를 입력하세요");
	    	  f.intro.focus();
	    	  return false;
	      }
			if (checkid == 0) {
				alert("ID 중복체크를 하세요!");
				return false;
			}

	   }
</script>
<link rel="stylesheet" type="text/css" href="/ind-script/optimizer.php?filename=06118f634a443f6e176680a1d0672b7553eb8064_1469556636&type=css&" />
<form:form commandName="member" action="memberjoin" method="post" name="join" onsubmit="return check()" enctype="multipart/form-data">
<h3>기본정보</h3>
<p class="required">회원가입을 위한 필수입력 항목을 입력해 주십시오.</p>
<div class="board_view ">
        <table border="1" summary="">
<caption>회원 기본정보</caption>

        <tbody>
<tr>
<th scope="row">아이디</th>
                <td><input id="id" name="id" theme="simple" fw-filter="isFill&isFill&isMin[4]&isMax[16]&isIdentity" fw-label="아이디" fw-msg="" class="inputTypeText" value="" type="text" onfocus="on(this)" onblur="off(this)"  />
                 <input type="hidden" name="checkid" value="0" />
                 <a href="#none" title="아이디중복확인(새창으로 열기)" onclick="return checkId();"><img src="http://img.echosting.cafe24.com/design/skin/default/member/btn_check1.gif" alt="아이디중복확인"/></a> (영문소문자/숫자, 4~16자)</td>
           							<font color="red"><form:errors path="id" /></font>
									<font color="red"><form:errors element="id" /></font>
            </tr>
<tr>
<th scope="row">비밀번호</th>
                <td><input id="pass" name="pass" fw-filter="isFill&isMin[4]&isMax[16]" fw-label="비밀번호" fw-msg="" autocomplete="off" maxlength="16" 0="disabled" value="" type="password"  /> (영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 8자~16자)</td>
					<font color="red"><form:errors path="pass" /></font>
</tr>
<tr>
<th scope="row">비밀번호 확인</th>
                <td><input id="pass2" name="pass2" fw-filter="isFill&isMatch[pass]" fw-label="비밀번호 확인" fw-msg="비밀번호가 일치하지 않습니다." autocomplete="off" maxlength="16" 0="disabled" value="" type="password"  /></td>
					
</tr>
<tr>
<th scope="row">닉네임</th>
                <td><input id="nick" name="nick" fw-filter="isFill" fw-label="별명" fw-msg="" class="inputTypeText" maxlength="20" value="" type="text"  /> (한글2~10자/영문 대소문자4~20자/숫자 혼용가능) <p id="nickMsg"></p>
</td>
</tr>

<tr>
<th scope="row">이름</th>
                <td>
                    <span id="nameContents"><input id="name" name="name" fw-filter="isFill&isMax[20]" fw-label="이름" fw-msg="" class="inputTypeText" maxlength="20" value="" type="text"  /></span>
                	<font color="red"><form:errors path="name" /></font>
                </td>
</tr>
			<tr>
<th scope="row">혈액형</td>
				<td><select name="bloodgroup">
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="O">O</option>
					<option value="AB">AB</option>		
				</select>
				</td>
</tr>
<tr>
<th scope="row">이메일</th>
                <td><input id="email" name="email" fw-filter="isFill" fw-label="이메일" fw-alone="N" fw-msg="" value="" type="text"  />@     
                <input id="email2" name="email2" fw-label="이메일" fw-alone="N" fw-msg=""  value="" type="text" disabled value="- 이메일 선택  -" />
                <select id="selectEmail" name="selectEmail">
					<option value="" selected="selected">- 이메일 선택  -</option>
					<option value="naver.com">naver.com</option>
					<option value="daum.net">daum.net</option>
					<option value="nate.com">nate.com</option>
					<option value="hotmail.com">hotmail.com</option>
					<option value="yahoo.com">yahoo.com</option>
					<option value="empas.com">empas.com</option>
					<option value="korea.com">korea.com</option>
					<option value="dreamwiz.com">dreamwiz.com</option>
					<option value="gmail.com">gmail.com</option>
					<option value="1">직접입력</option>
</select>

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
</td>
</tr>
<tr>
<th scope="row">휴대전화</th>
		<td>
		<select name="phone3">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					<option value="017">017</option>
					<option value="019">019</option>		
				</select>		
		<input id="phone" name="phone" maxlength="4" fw-filter="isNumber&isFill" fw-label="휴대전화" fw-alone="N" fw-msg="" value="" type="text"  />-<input id="phone2" name="phone2" maxlength="4" fw-filter="isNumber&isFill" fw-label="휴대전화" fw-alone="N" fw-msg="" value="" type="text"/>
		</td>
</tr>			
<tr>
<th scope="row">주민등록번호</th>
		  <td>
          <input id="num1" name="num1" maxlength="6" fw-filter="isNumber&isFill" fw-label="주민번호앞자리" fw-alone="N" fw-msg="" value="" type="text"  />-<input id="num2" name="num2" maxlength="7" fw-filter="isNumber&isFill" fw-label="주민번호뒷자리" fw-alone="N" fw-msg="" value="" type="text"/>
          </td> 
</tr>
<tr>
<th scope="row">주소</th>
                <td>
                    <input id="zipcode" name="zipcode" fw-filter="isLengthRange[1][14]" fw-label="우편번호" fw-msg="" class="inputTypeText" readonly="readonly" maxlength="14" value="" type="text"  /> <a href="#none" title="우편번호(새창으로 열기)" onclick="openZipcode()" id="postBtn"><img src="http://img.echosting.cafe24.com/design/skin/default/member/btn_zip.gif" alt="우편번호"/></a><br/>
                    <input id="area" name="area" fw-filter="isFill" fw-label="지역" fw-msg="" class="inputTypeText" readonly="readonly" value="" type="text"  /><br/>
                    <input id="addr1" name="addr1" fw-filter="isFill" fw-label="주소" fw-msg="" class="inputTypeText" readonly="readonly" value="" type="text"  /><br/>	
                    <input id="addr2" name="addr2" fw-filter="" fw-label="상세주소" fw-msg="" class="inputTypeText" value="" type="text"  />                </td>
            </tr>
			<tr>
<th scope="row">소개</td>
				<td><input id="intro" name="intro" maxlength="20" />
						<font class="BFONT">간단한 소개를 입력해주세요</font>
				</td>
			</tr>
			<tr>
<th scope="row">파일첨부</td>
			<td><div id="fileDiv">
				<input type="file" id="file" name="file">
					<a href="#this" class="btn" id="delete" name="delete">삭제</a>
					<a href="#this" class="btn" id="addFile">파일 추가</a>

				</td>
			</div>
			</tr>
<script type="text/javascript">
		var gfv_count = 1;
		var up = 0;
		$(document).ready(function(){
			$("#addFile").on("click", function(e){ //파일 추가 버튼
				up++;
				if(up == 3){
					up = 2;
					alert("더이상 생성되지 않습니다");
					fn_deleteFile(this);
				}
				e.preventDefault();
				fn_addFile();
			
				$("a[name='delete']").on("click", function(e){ //삭제 버튼
					if(up != 2){
						up--;
					}if(up == 2){
						up = 1;
					}
					
					
					e.preventDefault();
					fn_deleteFile($(this));
				});
			});

		});
		
		function fn_addFile(){
			var str = "<p><input type='file' name='file_"+(gfv_count++)+"'><a href='#this' class='btn' name='delete'> 삭제</a></p>";
			$("#fileDiv").append(str);
			$("a[name='delete']").on("click", function(e){ //삭제 버튼
				e.preventDefault();
				fn_deleteFile($(this));
			});
		}
		
		function fn_deleteFile(obj){
			obj.parent().remove();
		}
	</script>
</tbody>
</table>
</div>

			

</tbody>
</table>
</div>
<div class="btnArea">
 	<input type="image" src="/pet/resources/images/SkinImg/btn_member_join.gif" name="Submit" value="" style="border:0px;" />
        <a href="/index.html"><img src="/web/upload/goodymallSkin/mypage/btn_member_cancel.gif" alt="회원가입취소"/></a>
      
    </div>
</div>
</form:form>
</form>
</div>
</div>