<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<head>

<script type="text/javascript">
function delchk(){
    return confirm("삭제하시겠습니까?");
}
</script>
<style type="text/css">
.paging{text-align:center;height:32px;margin-top:5px;margin-bottom:15px;}
.paging a,
.paging strong{display:inline-block;width:36px;height:32px;line-height:28px;font-size:14px;border:1px solid #e0e0e0;margin-left:5px;
-webkit-border-radius:3px;
   -moz-border-radius:3px;
		border-radius:3px;
-webkit-box-shadow:1px 1px 1px 0px rgba(235,235,235,1);
	-moz-box-shadow:1px 1px 1px 0px rgba(235,235,235,1);
		  box-shadow:1px 1px 1px 0px rgba(235,235,235,1);
}
.paging a:first-child{margin-left:0;}
.paging strong{color:#fff;background:#337AB7;border:1px solid #337AB7;}
.paging .page_arw{font-size:11px;line-height:30px;}

p.outset {border-style: outset;}
</style>
</head>
         <div class="container">
            <div class="row">
            <div class="box"> 
                <div class="col-lg-12 text-center">
                
              <h2><p class="outset">회  원  목  록</p></h2>
  
 </div>
 </div>
 </div>
 </div>
 
<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">
                         회원목록페이지 검색, 수정, 삭제 기능하는 페이지입니다.
        </div>
        <div class="panel-body">
			<div class="dataTable_wrapper">
				<div id="dataTables-example_wrapper"
					class="dataTables_wrapper form-inline dt-bootstrap no-footer">
					<div class="row" style="margin-bottom:5px;">
						<div class="col-sm-6">
							<a href="/someday/admin/memberadminList?searchNum=0&isSearch="><button type="button" class="btn btn-outline btn-default">전체</button></a>													
						</div>
						<div class="col-sm-6" style="text-align:right;">
							<div class="dataTables_info" id="dataTables-example_info" role="status" aria-live="polite">총 회원수 : ${totalCount}
							총 남자회원수 : ${malecount}
							총 여자회원수 : ${femalecount}
							</div>
						</div>
						
					</div>
					<div class="row">
						<div class="col-sm-12">
							<table
								class="table table-striped table-bordered table-hover dataTable no-footer"
								id="dataTables-example" role="grid"
								aria-describedby="dataTables-example_info">
								<thead>
									<tr role="row">
										<th style="width: 5%; text-align:center;">번호</th>
										<th style="width: 8%; text-align:center;">ID</th>
										<th style="width: 7%; text-align:center;">이름</th>										
										<th style="width: 9%; text-align:center;">전화번호</th>
										<!-- <th style="width: 14%; text-align:center;">E-Mail</th> -->
										<!-- <th style="width: 29%; text-align:center;">주소</th> -->
										<!-- <th style="width: 5%; text-align:center;">Point</th> -->
										<th style="width: 10%; text-align:center;">가입일자</th>
										<th style="width: 10%; text-align:center;">관리자</th>
										<th style="width: 13%; text-align:center;">관리</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="memberlist"  items="${memberlist}" varStatus="stat">
								<c:url var="viewURL" value="/admin/adminmemberModify" >
									<c:param name="id" value="${memberlist.id }" />
								</c:url>									
									<tr class="gradeA even" role="row">
										<td style="text-align:center;vertical-align:middle;">${memberlist.idx}</td>
										<td style="text-align:center;vertical-align:middle;">${memberlist.id}</td>
										<td style="text-align:center;vertical-align:middle;">${memberlist.name}</td>
										<td style="text-align:center;vertical-align:middle;">${memberlist.phone}</td>
										<%-- <td style="text-align:center;vertical-align:middle;">${memberlist.email}</td> --%>
										<%-- <td style="text-align:center;vertical-align:middle;">${memberlist.addr1}</td> --%>
										<%-- <td style="text-align:center;vertical-align:middle;">${memberlist.point}</td> --%>										
										<td style="text-align:center;vertical-align:middle;"><fmt:formatDate value="${memberlist.times}" pattern="YY.MM.dd HH:mm" /></td>
										<td style="text-align:center;vertical-align:middle;">${memberlist.authority}</td>
										<td style="text-align:center;vertical-align:middle;">
											<a href="${viewURL}"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/Cog_font_awesome.svg/32px-Cog_font_awesome.svg.png"></a>&nbsp;&nbsp;
										<c:url var="viewURL2" value="/admin/adminMemberDelete" >
											<c:param name="id" value="${memberlist.id }" />							
										</c:url>	
										 <a href="${viewURL2}"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Trash_font_awesome.svg/32px-Trash_font_awesome.svg.png" onclick="return delchk()"></a>								
										<c:url var="viewURL3" value="/admin/authority" >
											<c:param name="idx" value="${memberlist.idx}" />							
										</c:url>
										 <%-- <a href="${viewURL3}"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/Cog_font_awesome.svg/32px-Cog_font_awesome.svg.png"></a>&nbsp;&nbsp;</td> --%>
										 <a href="${viewURL3}"><img src="/someday/resources/img/admin.png" width="50" height="50"/></a>&nbsp;&nbsp;</td>
										 
									</tr>
								</c:forEach>
								<!--  등록된 상품이 없을때 -->
									<c:if test="${fn:length(memberlist) le 0}">
										<tr><td colspan="9" style="text-align:center;">등록된 회원이 없습니다</td></tr>
									</c:if> 
								</tbody>
							</table>
						</div>
					</div>
					<div class="paging">
						${pagingHtml}
					</div>
					<div class="row">
							<div style="text-align:center;">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<form action="">
									<select class="form-control" name="searchNum" id="searchNum">
										<option value="0">전체</option>
									</select>
										<input class="form-control" type="text" name="isSearch" id="isSearch"/>
										<span>
										<button type="submit" class="btn btn-default">검색</button>
										</span>
									</form>
								</div>							
							</div>
							
					</div>
				</div>
			</div>
			<!-- /.table-responsive -->							
		</div>
	</div>
        <!-- /.panel -->   
</div>
