<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
#align {
	width: 100%;
	text-align: center;
}

#content {
	margin: 0 auto;
	width: 350px;
	text-align: left;
}
</style>

<body>
	<div class="container">

		<div class="row">
			<div class="box">
				<div id="align">
					<div id="content">
						<table width="600" border="0" cellspacing="0" cellpadding="0">

							<h2>오늘의 인연</h2>

							<table width="600" border="0" cellspacing="0" cellpadding="2">

								<h3>나의 프로필</h3>
								<tr class="line1">
									<img src="/someday/resources/img/${my.file_savname}"
										width="300" height="300" />
								</tr>

								<tr class="line1">
									<td width="200"><b>닉네임</b></td>
									<td><b>${my.nick}</b></td>
								</tr>
								<tr class="line1">
									<td width="200">소개</td>
									<td>${my.intro}</td>
								</tr>

								<%-- <tr class="line1">
            <td><input name="list" type="button" value="내정보 수정하기"
               class="button"
               onclick="javascript:location.href='mypageModifyAction.action?idx=<c:property value="resultClass.idx" />'" />
            </td>
            <td></td>
         </tr> --%>

							</table>
							<br></br>
							<!-- ---------------------상대방 target------------------------------------- -->

							<%-- <c:iterator value="check_meeting" status="stat">
         <c:if test='check_meeting.female_like == 1 && check_meeting.male_like == 1'>
            <img src="image/couple_success.png"  style="height:100px"/>
         </c:if> 커플 하트표시를 위한구문..
      </c:iterator> --%>
							<br></br>
							<c:choose>
								<c:when test="${target != null}">
									<table width="600" border="0" cellspacing="0" cellpadding="2">
										<tr>
											<h3>오늘의 인연</h3>
										</tr>
										<tr class="line1">
											<img src="/someday/resources/img/${target.file_savname}"
												width="300" height="300" />
										<tr class="line1">
											<td width="200"><b>닉네임</b></td>
											<td><b>${target.nick}</b></td>
										</tr>
										<tr class="line1">
											<td width="200">나이</td>
											<td>${target.age}</td>
										</tr>
										<tr class="line1">
											<td width="200">지역</td>
											<td>${target.area}</td>
										</tr>
										<tr class="line1">
											<td width="200">혈액형</td>
											<td>${target.bloodgroup}</td>
										</tr>
										<tr class="line1">
											<td><input name="list" type="button" value="인연 프로필 더보기"
												class="button"
												onclick="javascript:location.href='TodayView'" /></td>
											<td></td>
										</tr>
										</form>
									</table>
								</c:when>
								<c:otherwise>

									<table width="600" border="0" cellspacing="0" cellpadding="2">
										<tr>
											<td align="center">오늘의 이성이 없습니다..</td>
										</tr>
										<tr>
											<img src="/someday/resources/img/T_T.gif"
												width="200" height="200" />
										</tr>
									</table>

								</c:otherwise>
							</c:choose>
							<br>
							<br>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>