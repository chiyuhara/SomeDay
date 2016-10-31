<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	var onWrite = function() {
		location.href = 'NoticeWrite';
	};
</script>
<title>지브롱</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<body>
	<h2>공지 목록</h2>
	<table style="border: 1px solid #ccc">
		<colgroup>
			<col width="10%" />
			<col width="*" />
			<col width="15%" />
			<col width="20%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">글번호</th>
				<th scope="col">제목</th>
				<th scope="col">글타입</th>
				<th scope="col">글쓴이</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="list" items="${noticeList}">
				<tr>
					<c:url var="viewURL" value="NoticeView">
						<c:param name="idx" value="${list.idx}" />
						<c:param name="currentPage" value="${currentPage}" />
					</c:url>

					<td align="center">${list.idx}</td>
					<td style="text-align: left;"><a href="${viewURL}">${list.subject}</a>
					</td>
					<td align="center">${list.type}</td>
					<td align="center">${list.writer}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<button type="button" onclick="onWrite()" class="btn btn-primary">글쓰기</button>
</body>
</html>