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
                            zoom: 18, // ������ ����� ���� �� ũ��
                            mapTypeId: google.maps.MapTypeId.ROADMAP
                        };
         
         
        var map = new google.maps.Map(document.getElementById("map-canvas"), // div�� id�� ���� ���ƾ� ��. "map-canvas"
                                    mapOptions);
         
        var size_x = 40; // ��Ŀ�� ����� �̹����� ���� ũ��
        var size_y = 40; // ��Ŀ�� ����� �̹����� ���� ũ��
     
        // ��Ŀ�� ����� �̹��� �ּ�
        var image = new google.maps.MarkerImage( '�ּ� ���⿡ ����!',
                                                    new google.maps.Size(size_x, size_y),
                                                    '',
                                                    '',
                                                    new google.maps.Size(size_x, size_y));
         
        // Geocoding *****************************************************
        var address = '����Ư���� ������ ���ﵿ 823-24 ��������'; // DB���� �ּ� �����ͼ� �˻��ϰų� ���ʰ� ���� �ּҸ� �ٷ� �ڵ�.
        var marker = null;
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode( { 'address': address}, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                map.setCenter(results[0].geometry.location);
                marker = new google.maps.Marker({
                                map: map,
                                icon: image, // ��Ŀ�� ����� �̹���(����)
                                title: 'SomeDay', // ��Ŀ�� ���콺 ����Ʈ�� ���ٴ��� �� �ߴ� Ÿ��Ʋ
                                position: results[0].geometry.location
                            });
 
                var content = "�������� 3�� I��"; // ��ǳ�� �ȿ� �� ����
             
                // ��Ŀ�� Ŭ������ ���� �̺�Ʈ. ��ǳ�� ��~
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
<h2 id="pageTitle">ã�ƿ��ô±�</h2>	
		<div id="map-canvas" style="width: 100%; height: 340px" title="SomeDay"></div>
<div class=h20></div>



<div class=con12>
 <div class=left>
<img src=http://www.lovehanda.com/theme/basic/img/blit_map.png> <span class=black>�����ּ� :</span> ����Ư���� ������ ������� 14�� 6 �������� 2F, 3F, 4F<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(T: 1544-9970 / F: 070-8290-2889)<br>
<img src=http://www.lovehanda.com/theme/basic/img/blit_map.png> <span class=black>���θ��ּ� :</span>  ��⵵ ������ �ȴޱ� �Ǳ��� 159, 6�� 604ȣ<br><br>

<img src=http://www.lovehanda.com/theme/basic/img/blit_subway.png> <span class=black>����ö :</span> ����ö 2ȣ�� ���￪ 3���ⱸ 100m <br>
<img src=http://www.lovehanda.com/theme/basic/img/blit_bus.png> <span class=black>���� :</span> ���￪.������P&SŸ�� ������ <br><br>
</div>
 <div class=right>
<ul>
   <span class=bus1>�Ϲ�</span> <span class=num>146</span><br>
   <span class=bus1>�Ϲ�</span> <span class=num>740</span><br>
   <span class=bus1>�Ϲ�</span> <span class=num>341</span><br>
   <span class=bus1>�Ϲ�</span> <span class=num>360</span><br>
</ul>


<ul>
   <span class=bus2>����</span> <span class=num>1100</span><br>
   <span class=bus2>����</span> <span class=num>1700</span><br>
   <span class=bus2>����</span> <span class=num>2000</span><br>
   <span class=bus2>����</span> <span class=num>7007</span><br>
   <span class=bus2>����</span> <span class=num>8001</span><br>
</ul>
</div>

</div></div>
</div>
</div>
</body>
</html>