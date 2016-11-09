<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="ko">
<head>
<title>SOME DAY</title>
<link rel="stylesheet" href="http://www.lovehanda.com/theme/basic/css/default.css">
<link rel="stylesheet" href="http://www.lovehanda.com/skin/latest/basic/style.css">
<link rel="stylesheet" href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css">
<link type="text/css" href="http://www.lovehanda.com/theme/basic/css/jquery-ui.css" rel="stylesheet" />
<link type="text/css" href="http://www.lovehanda.com/theme/basic/css/ck_style.css" rel="stylesheet" />
<script src="http://www.lovehanda.com/js/jquery-1.8.3.min.js"></script>
<script src="http://www.lovehanda.com/js/jquery.menu.js"></script>
<script src="http://www.lovehanda.com/js/common.js"></script>
<script src="http://www.lovehanda.com/js/wrest.js"></script>
<script src="http://www.lovehanda.com/js/myscript.js"></script>
<script src="http://www.lovehanda.com/js/ckshow_Img1.5.js"></script>
<script src="http://chang.peopleweb.biz/inc/jquery.cksliding.js"></script>
</head>

<script type="text/javascript">
   
   
   var onSearch = function(){
      
      submit();
   };
   
      
</script>

<body>

   
 <div id="header_top">
 <div class="wrap">
<section id="ol_before">
    <form:form commandName="member" name="headerlogin" action="${contextpath}/someday/member/login" onsubmit="return fhead_submit(this);" method="post" autocomplete="off">
    <fieldset>
    <c:if test="${session_member_id == null }">
        <input type="text" id="ol_id" name="id"  title="회원아이디" placeholder="ID" required class="required" maxlength="20">
        <input type="password" name="pass" id="ol_pw" title="비밀번호"  placeholder="PW" required class="required" maxlength="20">
        <input type="submit" id="ol_submit" value="로그인">
        <div id="ol_svc">
            <a href="${contextpath}/someday/member/memberForm"><b>회원가입</b></a>
            <a href="http://www.lovehanda.com/bbs/password_lost.php" id="ol_password_lost">ID/PW분실</a>
        </div>
        </c:if>
        <c:if test="${session_member_id != null }">
           <div id="ol_svc">
           <strong>${session_member_name}&nbsp;님</strong>     
           <a href="http://www.lovehanda.com/bbs/member_confirm.php?url=register_form.php" id="ol_after_info">정보수정</a>
           <a href="${contextpath}/someday/member/logout" id="ol_after_logout">로그아웃</a>
        </div>
        </c:if>
    </fieldset>
    </form:form>
</section>
<div id="header">
<div class="wrap">

<div class="header_q">
   <h1><a href="" title=""><img src="/someday/resources/images/SkinImg/logo.png" alt="" style="width:250px;" /></a></h1>      
   <div class="search_main">
      <div class="search_inner">
      </div>
   </div>

      <ul id="topMenu">
                  <li>
            <a href="/intro.html" class="m"><h1>SOME DAY 가이드</h1></a>
                        <dl class="dl1">
                              <dd class="dd1"><a href="/intro.html" class="s ">이용하기</a></dd>
                              <dd class="dd1"><a href="/load.html" class="s ">FAQ</a></dd>
                              <dd class="dd1"><a href="/friend.html" class="s ">Q&A</a></dd>
                           </dl>
                     </li>
                  <li>
            <a href="/use.html" class="m"><h1>오늘의인연</h1></a>
                        <dl class="dl1">
                              <dd class="dd1"><a href="/use.html" class="s ">오늘의인연</a></dd>
                           </dl>
                     </li>
                  <li>
            <a href="/love.html" class="m"><h1>마이페이지</h1></a>
                        <dl class="dl1">
                              <dd class="dd1"><a href="/love.html" class="s ">마이페이지</a></dd>
                           </dl>
                     </li>
                  <li>
            <a href="/after.html" class="m"><h1>공지&이벤트</h1></a>
                        <dl class="dl1">
                              <dd class="dd1"><a href="/after.html" class="s ">공지</a></dd>
                              <dd class="dd1"><a href="/after.html" class="s ">이벤트</a></dd>
                           </dl>
                     </li>
                  
               </ul>   </div> 
</div>