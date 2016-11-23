<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SOME DAY</title>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 2px;
}
th {
    text-align: center;
}

table {
    
}
</style>
</head>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
</head>
<body>
 <div class="box">
 
 <div class="col-lg-12 text-center">
   <h2>미팅 현황</h2>
   </div>
   
   <table style="border: 10px align=center 100%: solid #ccc">
      <colgroup>
        <col width="10%" />
         <col width="" />
         <col width="18%" />
         <col width="" />
          <col width="20%" />
         <col width="" />
         <col width="10%" />
         <col width="" />
         <col width="15%" />
         <col width="%" />
         
      </colgroup>
      <thead>
         <tr>
            <th scope="col">번호</th>
            <th scope="col">여자</th>
             <th scope="col"></th>
            <th scope="col">여자가 좋아요</th>
              <th scope="col">남자가 좋아요</th>
            <th scope="col"></th>
             <th scope="col">남자</th>
              <th scope="col">날짜</th>
            <th scope="col"></th>
           
            <th></th>
            <th scope="col"></th>
         </tr>
      </thead>
      <tbody>

         <c:forEach var="list" items="${meetModel}">
            <tr>
               <c:url var="viewURL" value="Meetlist">
                  <c:param name="idx" value="${list.idx}" />
                  <c:param name="currentPage" value="${currentPage}" />
               </c:url>

               <td align="center">${list.idx}</td>
               <td align="center">${list.f_name}</td>
               <td align="center"><img src="/someday/resources/img/${list.f_pic}"
                                    width="80" height="80" /></td>
               <c:if test="${list.female_like == 1}">
               <td align="center"><img src="/someday/resources/img/female_like.png" />
               </c:if>
               <c:if test="${list.female_like == 0}">
               <td align="center"><img src="/someday/resources/img/female_no.png" />
               </c:if>
               <c:if test="${list.male_like == 1}">
               <td align="center"><img src="/someday/resources/img/male_like.png" />
               </c:if>
               <c:if test="${list.male_like == 0}">
               <td align="center"><img src="/someday/resources/img/male_no.png" />
               </c:if>
               <td align="center"><img src="/someday/resources/img/${list.m_pic}"
                                    width="80" height="80" /></td>
               <td align="center">${list.m_name}</td>
              <td align="center"><fmt:formatDate value="${list.times}"  pattern="YY.MM.dd HH:mm" />
            </tr>
         </c:forEach>
          
      </tbody>
   </table>
   </div>
</body>
</html>