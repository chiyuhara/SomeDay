<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

h7 {
    font-size: 18px;
}

h8 {
    font-size: 16px;
}

h9 {
    font-size: 18px;
    color: red;
}


td, th {
    border: 2px solid #dddddd;
    text-align: center;
    padding: 8px;
}

</style>

<script type="text/javascript">
	var onWrite = function() {
		location.href = 'NoticeWrite';
	};
</script>
<title>SOME DAY</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<body>
<div class="box">
<div class="col-lg-12 text-center">
	<h2>공지 목록</h2>
	<table style="width:100%">
		<thead>
			<tr>
				<th><h4>글번호</h4></th>
				<th><h4>글타입</h4></th>
				<th><h4>제목</h4></th>
				<th><h4>글쓴이</h4></th>
				<th><h4>작성일</h4></th>
				<th><h4>조회수</h4></th>
			</tr>
		<tbody>
			<c:forEach var="list" items="${noticeList}">
				<tr>
					<c:url var="viewURL" value="NoticeView">
						<c:param name="idx" value="${list.idx}" />
						<c:param name="currentPage" value="${currentPage}" />
					</c:url>

					<td align="center"><h7>${list.idx}</h7></td>
					<td align="center"><h9>${list.type}</h9></td>
					
					<td>
					
						<c:if test="${list.file_savname != null}">
							<a href="${viewURL}">${list.subject}[${list.cnt}]&nbsp;
							<img src="../resources/images/list/file.png" width="10" height="10"></a>							
						</c:if>
						
						<c:if test="${list.file_savname == null}">
							<a href="${viewURL}"> ${list.subject}[${list.cnt}]</a>
						</c:if>
					
					</td>
					<td align="center">${list.writer}</td>
					<td align="center">${list.times }</td>
					<td align="center">${list.readhit}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- </table> -->
     <div class="text-right">
	<button type="button" onclick="onWrite()" class="btn btn-primary">글쓰기</button>
	</div>
	
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
						<input type="submit" value="검색" style="font-size: 15px; padding-bottom: 20; 
						       vertical-align: middle;" />
						</span>
						   <div class="paging">${pagingHtml}</div>
					</form>
					</div>
				</div>
			</div> 
		</div>
	</div>
	
</body>
</html>