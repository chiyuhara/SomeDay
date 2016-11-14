<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body> 
   <!-- Navigation -->
   <nav class="navbar navbar-default" role="navigation">
      <nav class="navbar navbar-default">
         <form:form commandName="member" name="headerlogin"
            action="${contextpath}/someday/member/login"
            onsubmit="return fhead_submit(this);" method="post"
            autocomplete="off">
            <c:if test="${session_member_id == null }">
               <form id="signin" class="navbar-form navbar-right" role="form">
                  <div class="input-group">
                     <span class="input-group-addon"><i
                        class="glyphicon glyphicon-user"></i></span> <!-- <input id="email"
                        type="email" class="form-control" name="email" value=""
                        placeholder="Email Address"> -->
                         <input type="text" id="ol_id" name="id"  title="회원아이디" placeholder="ID" 
                         required class="form-control" maxlength="20">
                  </div>

                  <div class="input-group">
                     <span class="input-group-addon"><i
                        class="glyphicon glyphicon-lock"></i></span> <!-- <input id="password"
                        type="password" class="form-control" name="password" value=""
                        placeholder="Password"> -->
                        <input type="password" name="pass" id="ol_pw" title="비밀번호"  placeholder="PW" 
                        required class="form-control" maxlength="20">
                  </div>

                  <button type="submit" class="btn btn-primary">Login</button>

                  <div class="input-group">
                     <a href="${contextpath}/someday/member/memberForm"><b>회원가입</b></a>
                     <a href="${contextpath}/someday/member/findForm"> <b>ID/PW찾기</b></a>
                  </div>
               </form>
            </c:if>
            <c:if test="${session_member_id != null }">
               <div style="float: right">
                  <div class="input-group">
                     <strong>${session_member_name}&nbsp;님</strong> 
                     <c:if test="${session_member_name == 'admin' }">
					<a href="${contextpath}/someday/admin/admin">관리자페이지</a>
				</c:if>    
                     <a href="http://www.lovehanda.com/bbs/member_confirm.php?url=register_form.php">정보수정</a>
                     <a href="${contextpath}/someday/member/logout">로그아웃</a>
                  </div>
               </div>
            </c:if>
         </form:form>
      </nav>
      <div class="container">
         <!-- Brand and toggle get grouped for better mobile display -->
         <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
               data-target="#bs-example-navbar-collapse-1">
               <span class="sr-only">Toggle navigation</span> <span
                  class="icon-bar"></span> <span class="icon-bar"></span> <span
                  class="icon-bar"></span>
            </button>
            <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
            <a class="navbar-brand" href="index.html">Business Casual</a>
         </div>
         <!-- Collect the nav links, forms, and other content for toggling -->
         <div class="collapse navbar-collapse"
            id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
               <li class="dropdown"><a href="#" class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-expanded="false">SOME
                     DAY 가이드</span>
               </a>
                  <ul class="dropdown-menu" role="menu">
                     <li><a href="#">이용하기</a></li>
                     <li><a href="#">FAQ</a></li>
                     <li><a href="#">Q&A</a></li>
                  </ul></li>
               <li><a href="${contextpath}/someday/today">오늘의인연</a></li>
               <li><a href="${contextpath}/someday/my">마이페이지</a></li>
               <li><a href="${contextpath}/someday/notice/NoticeList">공지&이벤트</a></li>
            </ul>
         </div>
         <!-- /.navbar-collapse -->
      </div>
      <!-- /.container -->
   </nav>
</body>