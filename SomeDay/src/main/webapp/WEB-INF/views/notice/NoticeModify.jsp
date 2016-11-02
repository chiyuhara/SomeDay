<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function noticeList() {
		if (confirm("목록으로 가시겠습니까?") == true) {
			location.href = 'NoticeList';
		}  {
			return;
		}
	}
</script>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script>
<link href="/pet/resources/admincss/bootstrap.min.css" rel="stylesheet">
<link href="/pet/resources/css/reset.css" rel="stylesheet">
<link href="/pet/resources/admincss/sb-admin-2.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.contents-wrap {
	margin: 30px 0 0 0;
	min-height: 500px;
}

.contents {
	margin: 60px 0 0 0;
}

.recode-wrap {
	text-align: right;
	color: #888;
}

.hit-wrap {
	color: #888;
	margin: 10px 0;
}

.viewForm {
	margin: 20px 0 0 0;
}
</style>
<title>NOTICE</title>
</head>
<body>

	<div id="wrapper">
		<form:form commandName="noticeModel" action="NoticeModifySuccess" method="post"> 
			<div id="page-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">NOTICE 글 수정</h3>
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							
						<form:input type="hidden" path="idx" value="${noticeModel.idx}" />
							<thead>
								<tr>
									<th width="15%" align="center">글제목</th>
									<td colspan=5><form:input path="subject" theme="simple"
											value="${noticeModel.subject}" /></td>
								</tr>
							</thead>
							<tr>
							<tbody>

							<!-- 글 내용 -->
								<tr>
									<td colspan=6 height=300 style="padding: 0px !important;">
										<form:textarea path="content" theme="simple"
											value="${noticeModel.content}"
											style="padding:3px; margin:0px; width: 100%; height: 100%;" />
									</td>
								</tr>
							</tbody>

						</table>
					</div>
				</div>
			</div>
			<!-- 취소 작성완료 버튼 -->
			<div>
				<input type="submit" value="작성완료" class="btn btn-primary"/>
				<button type="button" onclick="noticeList();" class="btn btn-primary">목록</button>
				<!-- 취소 작성완료 버튼 -->
			</div>
		</form:form>

		<br /> <br /> <br /> <br />
	</div>


</body>
</html>