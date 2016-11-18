<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
	var onWrite = function() {
		location.href = 'QnAWrite';
};

function message() {
	var pswd = prompt("비밀번호입력 ");
	var pswdcheck = $("#pass").text();
	
	/* if (pswd == null){
		alert("비밀번호를 입력해주세요.  ");
		return message();
	} */
	
	if (pswd == pswdcheck) {
		alert("비밀번호가 일치합니다.");
		location.href='QnAView';
		
	} else {
		/* alert("비밀번호가 입력되지 않습니다. "); */
		$("#hijack").attr('href', 'http://localhost:8080/someday/qna/QnAList');
		/* document.location.href='someday/qna/QnAList'; */
		/* window.location = "http://localhost:8080/someday/qna/QnAList"; */
		/* return window.location = "http://localhost:8080/someday/qna/QnAList"; */
		/* document.write(pswdcheck); */
		}
};
	
</script>

<title>지브롱</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<body>
	<h2>공지 목록</h2>
	<table style="border: 1px solid #ccc" bgcolor="gray">
		<colgroup>
			<col width="10%" />
			<col width="20%" />
			<col width="*" />
			<col width="20%" />
			<col width="20%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">글번호</th>
				<th scope="col">답변여부</th>
				<th scope="col">제목</th>
				<th scope="col">글쓴이</th>
				<th scope="col">작성일</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="list" items="${qnaList}">
				<tr>
					<c:url var="viewURL" value="QnAView">
						<c:param name="idx" value="${list.idx}" />
						<c:param name="currentPage" value="${currentPage}" />
					</c:url>

					<td align="center">${list.idx}</td>
					<td align="center"><c:if test="${list.cnt eq 0}">
							<center>답변중</center>
						</c:if> <c:if test="${list.cnt ne 0}">
							<center>답변완료</center>
						</c:if></td>
						
						<%-- <td>
							<a href="${viewURL}" onclick="message()" id="hijack">${list.subject}[${list.cnt}]&nbsp; 
								<img src="../resources/images/list/lock.png" width="10" height="10"></a>
								<div id="pass" style=display:none; >${list.pass}</div>										
						</td> --%>
						
						<td>
					
						<!-- 관리자 -->
						<c:if test="${session_member_id == 'admin'}">
						
							<!-- 비밀글 -->
							<c:if test="${list.pass != null}">
								<a href="${viewURL}">${list.subject}[${list.cnt}]&nbsp; 
								<img src="../resources/images/list/lock.png" width="10" height="10"></a>
							</c:if>
						 
							<!-- 공개글 -->
							<c:if test="${list.pass == null}">
								<a href="${viewURL}">${list.subject}[${list.cnt}]&nbsp; 
							</c:if>
							
						</c:if>
					
						<!-- 사용자 -->
						<c:if test="${session_member_id != 'admin'}">
							
							<!-- 비밀글 -->
							<c:if test="${list.pass != null}">
								<a href="${viewURL}" onclick="message()" id="hijack">${list.subject}[${list.cnt}]&nbsp; 
								<img src="../resources/images/list/lock.png" width="10" height="10"></a>
								<div id="pass" style=display:none; >${list.pass}</div>
							</c:if>
						 
							<!-- 공개글 -->
							<c:if test="${list.pass == null}">
								<a href="${viewURL}">${list.subject}[${list.cnt}]&nbsp; 
								
							</c:if>
							
						</c:if>
						
					</td>
						
					<td align="center">${list.writer}</td>
					<td align="center">${list.times}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<button type="button" onclick="onWrite()" class="btn btn-primary">글쓰기</button>

	<!-- 페이징  -->

	<div class="paging" style="text-align: center;">${pagingHtml}</div>

	<!-- 검색 -->
	<div class="row">
		<div style="text-align: center;">
			<div id="dataTables-example_filter" class="dataTables_filter">
				<form>
					<select class="slcte" name="searchNum" id="searchNum">

						<option value="0">제목</option>
						<option value="1">내용</option>
						<option value="2">글쓴이</option>

					</select> <input class="txte" type="text" name="isSearch" id="isSearch" />
					<span class="btn btnC_03 btnP_04 mr10"> <input type="submit"
						value="검색"
						style="font-size: 11px; padding-bottom: 20; vertical-align: middle;" />
					</span>
				</form>
			</div>
		</div>
	</div>

</body>
</html>