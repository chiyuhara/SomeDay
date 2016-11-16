<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	var onWrite = function() {
		location.href = 'QnAWrite';
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
				<th scope="col">답변여부 </th>
				<th scope="col">제목</th>
				<th scope="col">글쓴이</th>
				<th scope="col">작성일 </th>
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
					<td align="center">
						<c:if test="${list.cnt eq 0}">
							<center>답변중 </center>
						</c:if>
				
						<c:if test="${list.cnt ne 0}">
							<center>답변완료 </center>
						</c:if></td>
					
					<td>
					<a href="${viewURL}"> ${list.subject}[${list.cnt}]</a>
					
					
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
						<span class="btn btnC_03 btnP_04 mr10"> 
						<input type="submit" value="검색" style="font-size: 11px; padding-bottom: 20; 
						       vertical-align: middle;" />
						</span>
					</form>
				</div>
			</div>
		</div>
	
</body>
</html>