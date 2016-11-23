<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <script type="text/javascript">
location.replace(document.referrer);
</script> -->



<script type="text/javascript">
	window.onload = function(){
		var chk = ${checkId}
		
		if(chk == 1){
			alert('로그인을 해주세요');
			location.href="${contextPath}/someday/";
		}
	}
</script><br />