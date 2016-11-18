<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body> 
   <!-- Navigation -->
   <nav class="navbar navbar-default" role="navigation">
      <nav class="navbar navbar-default">
		<form:form commandName="member" name="headerlogin" action="${contextpath}/someday/member/login" onsubmit="return fhead_submit(this);" method="post" autocomplete="off">
   		 <fieldset>
   		 <input type="hidden" value="session_member_idx">
   		 <c:if test="${session_member_id == null }">
    	 <div style="float: right;">
         <input type="text" id="ol_id" name="id"  title="회원아이디" placeholder="ID" required class="required" maxlength="20">
         <input type="password" name="pass" id="ol_pw" title="비밀번호"  placeholder="PW" required class="required" maxlength="20">
         <input type="submit" id="ol_submit" value="로그인"><br>
         
         <div style="float: right;">
            <a href="${contextpath}/someday/member/memberForm"><b>회원가입</b></a>
            <a href="http://www.lovehanda.com/bbs/password_lost.php" id="ol_password_lost">ID/PW분실</a>
         </div>
         </div>
         </c:if>
         <c:if test="${session_member_id != null }">
           <div style="float: right;">
            <strong>${session_member_name}&nbsp;님</strong>
             <c:if test="${session_member_authority == 'Y'}">
                <a href="${contextpath}/someday/admin/admin">관리자페이지</a>
             </c:if>     
            <a href="${contextpath}/someday/member/MypageView" id="ol_after_info">정보수정</a>
            <a href="${contextpath}/someday/member/logout" id="ol_after_logout">로그아웃</a>
         </div>
         </c:if>
     </fieldset>
     </form:form>

	<!-- ------- -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

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
			<div class="collapse navbar-collapse" id="bs-slide-dropdown">
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

					<li><a href="today">오늘의인연</a></li>
					<li><a href="about.html">마이페이지</a></li>
					<li><a href="notice/NoticeList">공지&이벤트</a></li>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</div>
	<!-- /.container -->
</nav>
<!-- /.container -->



<script type="text/javascript">
	$(document).ready(
			function() {
				$(".dropdown").hover(
						function() {
							$('.dropdown-menu', this).not('.in .dropdown-menu')
									.stop(true, true).slideDown("fast");
							$(this).toggleClass('open');
						},
						function() {
							$('.dropdown-menu', this).not('.in .dropdown-menu')
									.stop(true, true).slideUp("fast");
							$(this).toggleClass('open');
						});
			});
</script>