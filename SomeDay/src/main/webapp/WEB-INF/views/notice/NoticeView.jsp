<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
	var onList = function() {
		location.href = 'NoticeList';
	};

	var onModify = function() {
		location.href = 'NoticeModify?idx=${noticeModel.idx}';
	};

	function Delete() {
		if (confirm("게시글이 삭제됩니다.") == true) {
			location.href = 'NoticeDelete?idx=${noticeModel.idx}';
		}
		{
			return;
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>공지 및 이벤트 상세보기</title>

	<style>
body {
	font: 14px/1.3 Nanum Gothic, 나눔고딕, sans-serif;
	max-width: 100%;
}
</style>

	<script type="text/javascript">
		function validation() {
			var frm = document.forms(0);

			if (frm.subject.value == "") {
				alert("입력해주세요");
				return false;
			}

			if (frm.content.value == "") {
				alert("입력해주세요");
				return false;
			}

			return true;
		}

		$(document).ready(function() {
			$("#content").cleditor();
		});
	</script>
</head>

<tbody>
	<table width="600" border="0" cellspacing="0" cellpadding="0">
		<h2>공지 및 이벤트 상세보기</h2>
	</table>
	<table>
		<tr height="30">
			<td align="center" bgcolor="#FFC8C8" width="100">글 번호</td>
			<td align="center" width="200" style="padding: 2px 5px;"
				bgcolor="#FFF2E6">${noticeModel.idx}</td>
			<td align="center" bgcolor="#FFC8C8" width="100">조회수</td>
			<td align="center" width="200" style="padding: 2px 5px;"
				bgcolor="#FFF2E6">${noticeModel.readhit}</td>
		</tr>
		<tr height="30">
			<td align="center" bgcolor="#FFC8C8">글제목</td>
			<td colspan="3" style="padding: 2px 5px;" bgcolor="#FFF2E6"
				bgcolor="#FFF2E6"><b>${noticeModel.subject}</b></td>
		</tr>
		<tr height="30">
			<td align="center" bgcolor="#FFC8C8" width="100">글 타입</td>
			<td align="center" width="200" style="padding: 2px 5px;"
				bgcolor="#FFF2E6">${noticeModel.type}</td>
			<td align="center" bgcolor="#FFC8C8" width="100">작성자</td>
			<td align="center" width="200" style="padding: 2px 5px;"
				bgcolor="#FFF2E6">${noticeModel.writer}</td>
		</tr>
		<tr>
			<td align="center" bgcolor="#FFC8C8">글내용</td>
			<td align="left" colspan="3" height="100" style="padding: 10px 5px;"
				bgcolor="#FFF2E6">${noticeModel.content}</td>
		</tr>
		
		<tr>
			<td align="center" bgcolor="#FFC8C8">첨부파일 </td>
            <td colspan="3" style="padding: 2px 5px;" bgcolor="#FFF2E6" bgcolor="#FFF2E6">
                <img src="/Java/upload/${noticeModel.file_savname}" />
                <br>${noticeModel.file_savname}</br>
            </td>
		</tr>
		
	</table>


	<!-- 댓글 -->
	<div>
	
		<tr>
			<c:if test="${fn:length(noticecommList) eq 0}">
				<br/><center>등록된 댓글이 없습니다</center><br/>
			</c:if>
			<c:forEach var="noticecommList" items="${noticecommList}">
				<br></br>
			<td>${noticecommList.writer }님|${noticecommList.content}
				
				<a href="noticecommDelete?idx=${noticecommList.idx}&originidx=${noticecommList.originidx}" 
				
				class="btn btnC_01 btnP_02">
				<span class="btn btnC_05 reply_btn">삭제</span>
				</a>
			</td>
			</c:forEach>
		</tr>
		
		
		<form:form commandName="noticecommList" action="NoticecommWrite" method="post">
		<input type="hidden" name="originidx" value="${noticeModel.idx}"/>
			<tr>
						
				<textarea name="content" style="margin: 10px; width: 849px; height: 55px;"></textarea>
				<input name="submit" type="submit" value="댓글쓰기" class="btn btn-default btn-xs" />
				
			</tr>
		</form:form>
		
		
		
		
	</div>

	<button type="button" onclick="onList()" class="btn btn-primary">목록</button>
	<button type="button" onclick="onModify()" class="btn btn-primary">수정</button>
	<button type="button" onclick="Delete()" class="btn btn-primary">삭제</button>

</tbody>
</html>