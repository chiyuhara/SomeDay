<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>아이디 찾기</title>

<script language="javascript">

function memberIdFindCheck() {
   var mem = eval("document.memberIdFindForm");
   
   if(mem.name.value=="") {
        alert('이름을 입력하세요');
        mem.name.focus();
        return false;
    }   
   
    if(mem.num1.value=="") {
        alert('주민번호를 입력하세요');
          mem.num1.focus();
        return false;
    }
    
    if(mem.num2.value=="") {
        alert('주민번호를 입력하세요');
          mem.num2.focus();
        return false;
    }
    
   return true;
}

function memberFindCheck() {
      var mem1 = eval("document.memberPwdFindForm");
      
      if(mem1.name.value=="") {
           alert('이름을 입력하세요');
           mem1.name.focus();
           return false;
       } 
      
      if(mem1.id.value=="") {
           alert('아이디를 입력하세요');
           mem1.id.focus();
           return false;
       }   
      
       if(mem1.num1.value=="") {
           alert('주민번호를 입력하세요');
             mem1.num1.focus();
           return false;
       }
       
       if(mem1.num2.value=="") {
           alert('주민번호를 입력하세요');
             mem1.num2.focus();
           return false;
       }
       return true;
   }


</script>
<style type="text/css">
input[type="text"], input[type="password"], select {
   -webkit-border-radius: 12px;
   -moz-border-radius: 12px;
   border-radius: 5px;
   border: 1px #CCC solid;
   font-size: 1.2em;
   outline: none;
   text-indent: 8px;
   padding: 6px 0 6px 0;
}

input[type="button"], input[type="submit"], input[type="reset"] {
   -webkit-border-radius: 12px;
   -moz-border-radius: 12px;
   border-radius: 5px;
   border: 1px #286090 solid;
   font-size: 1.2em;
   background-color: #286090;
   outline: none;
   height: inherit;
   width: 85px;
   padding: 4px 0 4px 0;
   color: white;
}

.headertitle{
   position: relative;
   display:block;
    border-bottom: 2px solid #e5e5e5;
    margin-bottom: 8px;   
    margin: 10px 0;
}
.headertitle h2{

   float: left;
    clear: both;
    position: relative;
    height: 21px;
    padding: 0 2px 0 1px;
    border-bottom: 2px solid #4d4d4d;    
    margin-bottom: 0px;
    
}
.headertitle:after{
    display: block;
    clear: both;
    font-size: 0;
    line-height: 0;
    content: '';
}
.titlebottom{
   margin:0;
   margin-bottom: 20px;
    font-size: 12px;
    line-height: 17px;
}
.box{
    position: relative;
    padding: 20px 20px 20px 20px;
    border: 1px solid #e5e5e5;
    margin-bottom:50px;
}
.label{
    font-size: 14px;
    font-weight: bold;
    line-height: 18px;
    letter-spacing: -1px;
}

</style>
</head>
<body>
<div class="container">

        <div class="row">
            <div class="box">
<div><br>&nbsp;<br><br></div>
<div  style="width:600px; height:auto; margin:0 auto;">

   <div class="headertitle" style="height:36px;">
      <h2>
         <img
            src="https://static.nid.naver.com/images/user/images/user/h_find_id.gif"
            width="70" height="16">
      </h2>      
   </div>
   
<p class="titlebottom">이름과 주민번호을 입력해 주세요.</p>


<!-- 테이블 -->


<table class="box" width="100%" border="0" cellpadding="0" cellspacing="0" >
<tr>

   <td valign="top" align="center">
   
   <table border="0" cellpadding="0" cellspacing="0">
   <tr>      
      <td width="450" valign="top">   
      <table width="100%" border="0" cellpadding="2" cellspacing="0">
   
      <tr>
         <td align="center">
         <form name="memberIdFind" action="/someday/member/memberIdFind" method="post" onsubmit="return memberIdFindCheck()">
         <table width="100%" border="0" cellpadding="0" cellspacing="0">
         <tr>
            <td width="180">
               <table  border="0" cellpadding="0" cellspacing="0">
               
               <tr height="40">
               <td width="80" align="right" class="label"><td>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</td>
               <td width="130">&nbsp;&nbsp; <input name="name" type="text" size="10"></td>
               <td>ex)홍길동</td>
            </tr> 
            
            <tr height="40">
               <td width="80" align="right" class="label"><td>주민번호</td>
                <td colspan=2>&nbsp;&nbsp; 
                     <input name="num1" type="text" size="5" maxlength="6"> -
                     <input name="num2" type="text" size="6" maxlength="7"> 
                     </td>
            </tr>
               </table>
            <td width="60" align="center">
               <input type="submit" value="찾  기"  style="cursor:pointer">
            </td>
         </tr>
         </table>
         </form>
         </td>
      </tr>
      </table>   
      </td>   
   </tr>
   </table>   
   </td>
</tr>
</table>
<c:if test="${member != null}"> <!-- session_member_id -->
<table class="box" width="100%" border="0" cellpadding="0" cellspacing="0" >

   <tr align="center">
      <td align="center"> 회원님의 아이디는 ${member.id}입니다.</td>
   </tr>
</table>
</c:if>


<div class="headertitle" style="height:36px;">
<h2><img src="https://static.nid.naver.com/images/web/user/h_find_pw2.gif" width="84" height="16" alt="비밀번호 찾기"></h2>   
</div>   
<p class="titlebottom">이름과 아이디 그리고 주민번호를 입력해 주세요.</p>
<table class="box" width="100%"  cellpadding="0" cellspacing="0" >
<tr>
   <td valign="top" align="center">
   <table width="450" border="0" cellpadding="0" cellspacing="0">
   <tr>
      <td width="100%" valign="top">
   
   
    
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
         <td align="center">
         <form name="memberPwdFind" action="/someday/member/memberPwFind" method="post" onsubmit="return memberFindCheck()">
         <table width="100%" border="0" cellpadding="0" cellspacing="0">
         <tr>
            <td width="180">
               <table  border="0" cellpadding="0" cellspacing="0">
            
      
            <tr height="40">
               <td width="80" align="right" class="label"><td>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</td>
               <td width="130">&nbsp;&nbsp; <input name="name" type="text" size="10"></td>
               <td>ex)홍길동</td>
            </tr>
               
            <tr height="40">
               <td width="80" align="right" class="label"><td>아&nbsp;&nbsp;이&nbsp;&nbsp;디</td>
               <td width="100">&nbsp;&nbsp; <input name="id" type="text" size="10"></td>
               <td>ex)hong231</td>
            </tr>
            <div align="center">         
            <tr height="40">
               <td width="80" align="right" class="label"><td>주민번호</td>
                <td colspan=2>&nbsp;&nbsp; 
                     <input name="num1" type="text" size="5" maxlength="6"> -
                     <input name="num2" type="text" size="6" maxlength="7"> 
                     </td>
            </tr>
            </div>   
            </table>
            </td>
            <td width="60" align="center">
               <input type="submit" value="찾  기" style="cursor:pointer">
            </td>
         </tr>
         </table>
         </form>
         </td>
      </tr>
      </table>
      </td>
   </tr>
   </table>
   
   </td>
</tr>
</table>
<c:if test="${member1 != null}">
<table class="box" width="100%" border="0" cellpadding="0" cellspacing="0" >

   <tr align="center">
      <td align="center"> 회원님의 비밀번호는 ${member1.pass}입니다.</td>
   </tr>
</table>
</c:if>

</div>
</div>
</div>
</div>
</body>
</html>