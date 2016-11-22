<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

      <title>관리자페이지</title>

    <!-- Bootstrap Core CSS -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="resources/css/business-casual2.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

	 <!-- jQuery -->
    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/bootstrap.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="resources/js/bootstrap.min.js"></script>

    <!-- Script to Activate the Carousel -->
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>
</head>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

   <title>관리자페이지</title>

    <!-- Bootstrap Core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="../resources/css/bootstrap.css" rel="stylesheet"> 

    <!-- Custom CSS -->
    <link href="../resources/css/business-casual2.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

	 <!-- jQuery -->
    <script src="../resources/js/jquery.js"></script>
    

    <!-- Bootstrap Core JavaScript -->
    <script src="../resources/js/bootstrap.min.js"></script>
    
     
    <!-- Script to Activate the Carousel -->
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>
</head>
<body>

<a class="navbar-brand" href="">SOME DAY-ADMIN<br>관리자 전용</a>
        
         <div class="collapse navbar-collapse"
            id="bs-example-navbar-collapse-1">
              <div class="row">
                <div class="box">
                 <div class="col-lg-12 text-l">        
                    <ul class="nav navbar-nav"> 
                                 
                     <li><a href="${contextpath}/someday/admin/admin">관리자홈</a></li>
                     
                     <li><a href="${contextpath}/someday/">MAIN으로 이동</a></li>
                                  
                     <li><a href="/someday/admin/memberadminList">회원목록</a></li>
                                  
                     <li><a href="/someday/notice/NoticeList">공지사항</a></li>         
         
         </div>
         </div>
         </div>
         </div>

	 <tiles:insertAttribute name="body" />	
  </body> 
</html>