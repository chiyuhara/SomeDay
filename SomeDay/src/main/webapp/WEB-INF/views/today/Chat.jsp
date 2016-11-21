<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
<script>
	function autoRefresh_sample_div() {
		var currentLocation = window.location;
		$("#sample").load(currentLocation + ' #sample').fadeIn(
				"slow");
	}
	setInterval('autoRefresh_sample_div()', 1000);
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>채팅</title>
</head>
<body>
	<div id="sample">
		<c:if test="${fn:length(chatList) eq 0}">
			<br />
			<center>등록된 글이 없습니다</center>
			<br />
		</c:if>

		<c:forEach var="chatList" items="${chatList}">
			</br>
			<td>${chatList.nick}:${chatList.content}</td>
		</c:forEach>
	</div>
	</br>
	</br>

	<form:form commandName="chatList" action="ChatSend" method="post">
		<input type="hidden" name="couple_idx" value="${chatModel.couple_idx}" />
		<input name="content" style="margin: 5px; width: 500px;"
			class="inputTypeText" type="text" />
		<input name="submit" type="submit" value="전송" class="bin ban-primary" />
	</form:form>

</body>
</html>