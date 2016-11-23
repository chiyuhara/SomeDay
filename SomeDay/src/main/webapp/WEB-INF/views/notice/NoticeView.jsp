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
<div class="container">
        <div class="row">
             <div class="box"> 
                <div class="col-lg-12 text-center">
                
	 <table width="1100" border="1" cellspacing="" cellpadding="0">
		<h2>공지 및 이벤트 상세보기</h2>
	

	    
		<tr height="30">
			<td align="center" bgcolor="#FFC8C8" width="160"><h5>글 번 호</h5></td>
			<td align="center" width="400" style="padding: 2px 5px;"
				bgcolor=""><h6>${noticeModel.idx}</h6></td>
				
			<td align="center" bgcolor="#FFC8C8" width="160"><h5>조 회 수</h5></td>
			<td align="center" width="400" style="padding: 2px 5px;"
				bgcolor=""><h4>${noticeModel.readhit + 1}</h4></td>
		</tr>
		
		<tr height="30">
			<td align="center" bgcolor="#FFC8C8" width="100"><h5>글 타 입</h5></td>
			<td align="center" width="200" style="padding: 2px 5px;"
				bgcolor=""><h5><font color="red">${noticeModel.type}</font></h5></td>
			<td align="center" bgcolor="#FFC8C8" width="100"><h5>작 성 자</h5></td>
			<td align="center" width="200" style="padding: 2px 5px;"
				bgcolor=""><h6>${noticeModel.writer}</h6></td>
		</tr>
		<tr height="30">
			<td align="center" bgcolor="#FFC8C8"><h5>글 제 목</h5></td>
			<td colspan="3" style="padding: 2px 5px;" bgcolor=""
				bgcolor=""><b><h4>${noticeModel.subject}</h4></b></td>
		</tr> 
		<tr>
			<td align="center" bgcolor="#FFC8C8"><h5>글 내 용</h5></td>
			<td align="center" colspan="3" height="100" style="padding: 10px 5px;"
				bgcolor=""><h6>${noticeModel.content}</h6></td>
		</tr>
		
		<tr>
			<td align="center" bgcolor=""><h5>첨 부 파 일</h5> </td>
		
            <td colspan="3" style="padding: 2px 5px;" bgcolor="">
        	
            	<c:if test="${noticeModel.file_savname != NULL}">
                <img src="/Java/upload/${noticeModel.file_savname}" />
                <br>${noticeModel.file_orgname}</br>
                </c:if>
                
               <c:if test="${noticeModel.file_savname == NULL}">
                첨부파일이 없습니다.
                </c:if>
            </td>
		</tr>
		
	</table>
	

	<!— 댓글 —>
	<div>
		<tr>
			<c:if test="${fn:length(noticecommList) eq 0}">
				<br/><center>등록된 댓글이 없습니다</center><br/>
			</c:if>
		
		    <c:forEach var="noticecommList" items="${noticecommList}">
				<br></br>
			<td>${noticecommList.writer }님|${noticecommList.content}
				
				<a href="noticecommDelete?idx=${noticecommList.idx}&originidx=${noticecommList.originidx}" 
				
				class="bin btnC_01 btnP_02">
				<span class="bin btnC_05 reply_btn">삭제</span>
				</a>
			</td>
			</c:forEach>
		</tr>
		
		   
	 	<form:form commandName="noticecommList" action="NoticecommWrite" method="post">
		<input type="hidden" name="originidx" value="${noticeModel.idx}"/>
			<tr>
				<div class="col-lg-12 text-right">		
				<textarea name="content" style="margin: 5px; width: 700px; height: 55px;"></textarea>
				<input name="submit" type="submit" value="댓글쓰기" class="btn btn-primary" />&nbsp;&nbsp;&nbsp;&nbsp;
				
				<button type="button" onclick="onList()" class="btn btn-primary">목록</button>
				<button type="button" onclick="onModify()" class="btn btn-primary">수정</button>
				<button type="button" onclick="Delete()" class="btn btn-primary">삭제</button>
			
				</div>
			</tr>
		</form:form>
		</div>
		</div>
		</div> 
		</div>
	</div>
	</div>

	
</tbody>
</html>