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
	<div class="paging">${pagingHtml}</div>
</body>
</html>