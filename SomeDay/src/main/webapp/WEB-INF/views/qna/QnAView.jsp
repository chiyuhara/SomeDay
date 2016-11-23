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
		location.href = 'QnAList';
	};

	var onModify = function() {
		location.href = 'QnAModify?idx=${qnaModel.idx}';
	};

	function Delete() {
		if (confirm("게시글이 삭제됩니다.") == true) {
			location.href = 'QnADelete?idx=${qnaModel.idx}';
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
<div class="container">
	<div class="row">
		<div class="box">
	<table width="600" border="0" cellspacing="0" cellpadding="0">
		<h2>공지 및 이벤트 상세보기</h2>
	</table>
	<table>
		<tr height="30">
			<td align="center" bgcolor="#FFC8C8" width="100">글 번호</td>
			<td align="center" width="200" style="padding: 2px 5px;"
				bgcolor="#FFF2E6">${qnaModel.idx}</td>
				
			<td align="center" bgcolor="#FFC8C8" width="100">답변여부 </td>
			<td align="center" width="200" style="padding: 2px 5px;" bgcolor="#FFF2E6">

				<c:if test="${fn:length(qnacommList) eq 0}">
				<br/><center>답변중 </center><br/>
				</c:if>
				
				<c:if test="${fn:length(qnacommList) ne 0}">
				<br/><center>답변완료 </center><br/>
				</c:if>
				
			</td>
		</tr>
		
		<tr height="30">
			<td align="center" bgcolor="#FFC8C8">작성자</td>
			<td align="center" width="200" style="padding: 2px 5px;" 
				bgcolor="#FFF2E6">${qnaModel.writer}</td>
				<td align="center" bgcolor="#FFC8C8">작성일 </td>
			<td align="center" width="200" style="padding: 2px 5px;" 
				bgcolor="#FFF2E6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<fmt:formatDate value="${qnaModel.times}" pattern="YYYY.MM.dd hh:mm" /></td>
				
		</tr>
		<tr height="30">
			<td align="center" bgcolor="#FFC8C8">글제목</td>
			<td colspan="3" style="padding: 2px 5px;" bgcolor="#FFF2E6"
				bgcolor="#FFF2E6"><b>${qnaModel.subject}</b></td>
		</tr>
		<tr>
			<td align="center" bgcolor="#FFC8C8">글내용</td>
			<td align="left" colspan="3" height="100" style="padding: 10px 5px;"
				bgcolor="#FFF2E6">${qnaModel.content}</td>
		</tr>
		<tr>
			<td align="center" bgcolor="#FFC8C8">첨부파일 </td>
            <td colspan="3" style="padding: 2px 5px;" bgcolor="#FFF2E6" bgcolor="#FFF2E6">
            	
            	<c:if test="${qnaModel.file_savname != NULL}">
                <img src="/Java/upload/${qnaModel.file_savname}" />
                <br>${qnaModel.file_orgname}</br>
                </c:if>
                
               <c:if test="${qnaModel.file_savname == NULL}">
                첨부파일이 없습니다.
                </c:if>
            </td>
		</tr>
	</table>

	<!-- 댓글 -->
	<div>
		<tr>
			<c:if test="${fn:length(qnacommList) eq 0}">
				<br/><center>답변중입니다.</center><br/>
			</c:if>
			<c:forEach var="qnacommList" items="${qnacommList}">
				<br></br>
			<td>${qnacommList.writer }님|${qnacommList.content}
				
				<a href="qnacommDelete?idx=${qnacommList.idx}&originidx=${qnacommList.originidx}" 
				
				class="btn btnC_01 btnP_02">
				<span class="btn btnC_05 reply_btn">삭제</span>
				</a>
			</td>
			</c:forEach>
		</tr>
		
		
		<form:form commandName="qnacommList" action="QnAcommWrite" method="post">
		<input type="hidden" name="originidx" value="${qnaModel.idx}"/>
			<tr>
						
				<textarea name="content" style="margin: 10px; width: 849px; height: 55px;"></textarea>
				<input name="submit" type="submit" value="댓글쓰기" class="btn btn-default btn-xs" />
				
			</tr>
		</form:form>
		
	</div>

	<button type="button" onclick="onList()" class="btn btn-primary">목록</button>
	<c:if test="${idcheck==1}">
        <button type="button" onclick="onModify()" class="btn btn-primary">수정</button>
	<button type="button" onclick="Delete()" class="btn btn-primary">삭제</button>
    </c:if>
</div>
</div>
</div>
</tbody>
</html>