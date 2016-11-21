<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="http://www.lovehanda.com/theme/basic/css/default.css">
<link rel="stylesheet" href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css">
<link type="text/css" href="http://www.lovehanda.com/theme/basic/css/jquery-ui.css" rel="stylesheet" />
<link type="text/css" href="http://www.lovehanda.com/theme/basic/css/ck_style.css" rel="stylesheet" />
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false&key=AIzaSyC9VgJ-jD1i_fxZ9b4mAzhEJNuk2_A5Pr0"></script>
<script type="text/javascript">
    function initialize() {
     
        var mapOptions = {
                            zoom: 18, // 지도를 띄웠을 때의 줌 크기
                            mapTypeId: google.maps.MapTypeId.ROADMAP
                        };
         
         
        var map = new google.maps.Map(document.getElementById("map-canvas"), // div의 id과 값이 같아야 함. "map-canvas"
                                    mapOptions);
         
        var size_x = 40; // 마커로 사용할 이미지의 가로 크기
        var size_y = 40; // 마커로 사용할 이미지의 세로 크기
     
        // 마커로 사용할 이미지 주소
        var image = new google.maps.MarkerImage( '주소 여기에 기입!',
                                                    new google.maps.Size(size_x, size_y),
                                                    '',
                                                    '',
                                                    new google.maps.Size(size_x, size_y));
         
        // Geocoding *****************************************************
        var address = '서울특별시 강남구 역삼동 823-24 남도빌딩'; // DB에서 주소 가져와서 검색하거나 왼쪽과 같이 주소를 바로 코딩.
        var marker = null;
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode( { 'address': address}, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                map.setCenter(results[0].geometry.location);
                marker = new google.maps.Marker({
                                map: map,
                                icon: image, // 마커로 사용할 이미지(변수)
                                title: 'SomeDay', // 마커에 마우스 포인트를 갖다댔을 때 뜨는 타이틀
                                position: results[0].geometry.location
                            });
 
                var content = "남도빌딩 3층 I반"; // 말풍선 안에 들어갈 내용
             
                // 마커를 클릭했을 때의 이벤트. 말풍선 뿅~
                var infowindow = new google.maps.InfoWindow({ content: content});
                google.maps.event.addListener(marker, "click", function() {infowindow.open(map,marker);});
            } else {
                alert("Geocode was not successful for the following reason: " + status);
            }
        });
        // Geocoding // *****************************************************
         
    }
    google.maps.event.addDomListener(window, 'load', initialize);
</script>
<style type="text/css">
body{background:url(http://www.lovehanda.com/theme/basic/img/sub_bg.gif)}
</style>
</head>
<body>

<div id="subContainer">
<h2 id="pageTitle">찾아오시는길</h2>	
		<div id="map-canvas" style="width: 100%; height: 340px" title="SomeDay"></div>
<div class=h20></div>



<div class=con12>
 <div class=left>
<img src=http://www.lovehanda.com/theme/basic/img/blit_map.png> <span class=black>지번주소 :</span> 서울특별시 강남구 테헤란로 14길 6 남도빌딩 2F, 3F, 4F<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(T: 1544-9970 / F: 070-8290-2889)<br>
<img src=http://www.lovehanda.com/theme/basic/img/blit_map.png> <span class=black>도로명주소 :</span>  경기도 수원시 팔달구 권광로 159, 6층 604호<br><br>

<img src=http://www.lovehanda.com/theme/basic/img/blit_subway.png> <span class=black>지하철 :</span> 지하철 2호선 역삼역 3번출구 100m <br>
<img src=http://www.lovehanda.com/theme/basic/img/blit_bus.png> <span class=black>버스 :</span> 역삼역.포스코P&S타워 정류장 <br><br>
</div>
 <div class=right>
<ul>
   <span class=bus1>일반</span> <span class=num>146</span><br>
   <span class=bus1>일반</span> <span class=num>740</span><br>
   <span class=bus1>일반</span> <span class=num>341</span><br>
   <span class=bus1>일반</span> <span class=num>360</span><br>
</ul>


<ul>
   <span class=bus2>직행</span> <span class=num>1100</span><br>
   <span class=bus2>직행</span> <span class=num>1700</span><br>
   <span class=bus2>직행</span> <span class=num>2000</span><br>
   <span class=bus2>직행</span> <span class=num>7007</span><br>
   <span class=bus2>직행</span> <span class=num>8001</span><br>
</ul>
</div>

</div></div>
</div>
</div>
</body>
</html>