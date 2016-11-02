<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>오늘의 인연</title>
<link rel="stylesheet" type="text/css" href="./style/popup.css" />

<script src="http://code.jquery.com/jquery-latest.js"></script>


<!-- 팝업 css부분 -->
<style type="text/css"> 
	.layer {display:none; position:fixed; _position:absolute; top:0; left:0; width:100%; height:100%; z-index:100;}
		.layer .bg {position:absolute; top:0; left:0; width:100%; height:100%; background:#000; opacity:.5; filter:alpha(opacity=50);}
		.layer .pop-layer {display:block;}

	.pop-layer {display:none; position: absolute; top: 50%; left: 50%; width: 450px; height:auto;  background-color:#fff; border: 5px solid #3571B5; z-index: 10;}	
	.pop-layer .pop-container {padding: 20px 25px;}
	.pop-layer p.ctxt {color: #666; line-height: 25px;}
	.pop-layer .btn-r {width: 100%; margin:10px 0 20px; padding-top: 10px; border-top: 1px solid #DDD; text-align:right;}

	a.cbtn {display:inline-block; height:25px; padding:0 14px 0; border:1px solid #304a8a; background-color:#3f5a9d; font-size:13px; color:#fff; line-height:25px;}	
	a.cbtn:hover {border: 1px solid #091940; background-color:#1f326a; color:#fff;}
</style>

<script type="text/javascript">
	function open_win_noresizable(url, name) {
		var oWin = window.open(url, name,
				"scrollbars=no,status=no,resizable=no,width=300,height=150");
	}
	function button_event() {
		if (confirm("상대방에게 호감을 보내시겠습니까??") == true) { //확인
			document.form.submit();
		} else { //취소
			return;
		}
	}

	function layer_open(el) {

		var temp = $('#' + el);
		var bg = temp.prev().hasClass('bg'); //dimmed 레이어를 감지하기 위한 boolean 변수

		if (bg) {
			$('.layer').fadeIn(); //'bg' 클래스가 존재하면 레이어가 나타나고 배경은 dimmed 된다. 
		} else {
			temp.fadeIn();
		}

		// 화면의 중앙에 레이어를 띄운다.
		if (temp.outerHeight() < $(document).height())
			temp.css('margin-top', '-' + temp.outerHeight() / 2 + 'px');
		else
			temp.css('top', '0px');
		if (temp.outerWidth() < $(document).width())
			temp.css('margin-left', '-' + temp.outerWidth() / 2 + 'px');
		else
			temp.css('left', '0px');

		temp.find('a.cbtn').click(function(e) {
			if (bg) {
				$('.layer').fadeOut(); //'bg' 클래스가 존재하면 레이어를 사라지게 한다. 
			} else {
				temp.fadeOut();
			}
			e.preventDefault();
		});

		$('.layer .bg').click(function(e) { //배경을 클릭하면 레이어를 사라지게 하는 이벤트 핸들러
			$('.layer').fadeOut();
			e.preventDefault();
		});

	}
</script>

</head>

<body>
<!-- 팝업부분 -->
	<div class="layer">
		<div class="bg"></div>
		<div id="layer" class="pop-layer">
			<div class="pop-container">
				<div class="pop-conts">
					<!--content //-->
					<p class="ctxt mb20">
						축하합니다! 커플이 성사되었습니다.<br> 먼저 연락해보세요!<br>
					</p>
					
					<div style="float:left">
					<a href-""><img src="/Hi_Admin/image/<s:property value='resultClass.file_savname' />"style="width: auto; height: 150px; margin-left: -10px;"  /><br>
					<b><s:property value="resultClass.name" /></b><br>
					<b><s:property value="resultClass.phone" /></b><br>
					</div>
					
					<div style="float:left">
					<img src="image/like_off.png" />
					</div>
					
					<div style="float:right">
					<img src="/Hi_Admin/image/<s:property value='target_resultClass.file_savname' />"style="width: auto; height: 150px; margin-left: -10px;"  /><br>
					<b><s:property value="target_resultClass.name" /></b><br>
					<b><s:property value="target_resultClass.phone" /></b><br>
					</div>
					
					<div style="float:right" class="btn-r">
						<a href="#" class="cbtn">닫기</a>
					</div>
					<!--// content-->
				</div>
			</div>
		</div>
	</div>
<!-- ---------------------------- -->
	<table width="600" border="0" cellspacing="0" cellpadding="2">
		<h2>오늘의 인연 상세보기</h2>
	</table>

	<form name="todayViewAction" method="post" />
	<table width="600" border="0" cellspacing="0" cellpadding="2">
		<div id="relative">
			<img
				src="/Hi_Admin/image/<s:property value='target_resultClass.file_savname' />" />
			<div id="absolute2">
				<s:iterator value="check_meeting" status="stat">
					<s:if
						test='check_meeting.female_like == 0 && check_meeting.male_like == 0'>
						<img src="image/no_couple.png" />
					</s:if>
					<s:if
						test='check_meeting.female_like == 1 && check_meeting.male_like == 1'>
						<img src="image/couple.png" />
						<!-- <a href="#" onclick="layer_open('layer');return false;">예제-2 보기</a> -->
						<script>layer_open('layer');</script>
					</s:if>
					<s:if
						test='check_meeting.female_like == 1 && check_meeting.male_like == 0'>
						<img src="image/female_like.png" />
					</s:if>
					<s:if
						test='check_meeting.male_like == 1 && check_meeting.female_like == 0'>
						<img src="image/male_like.png" />
					</s:if>
				</s:iterator>
			</div>
		</div>

		<!-- 커플이됬을 경우 이름과 연락처 공개-->
		<s:iterator value="check_meeting" status="stat">
			<s:if test='check_meeting.female_like == 1 && check_meeting.male_like == 1'>
				<tr>
					<td width="200">이름</td>
					<td><s:property value="target_resultClass.name" /></td>
				</tr>
				<tr>
					<td width="200">연락처</td>
					<td><s:property value="target_resultClass.phone" /></td>
				</tr>
			</s:if>
		</s:iterator>
		<tr class="line1">
			<td width="200" >닉네임</td>
			<td><s:property value="target_resultClass.nick" /></td>
		</tr>
		<tr class="line1">
			<td width="200">나이</td>
			<td><s:property value="target_resultClass.age" /></td>
		</tr>
		<tr class="line1">
			<td width="200">혈액형</td>
			<td><s:property value="target_resultClass.bloodgroup" /></td>
		</tr>
		<tr class="line1">
			<td width="200">지역</td>
			<td><s:property value="target_resultClass.area" /></td>
		</tr>
		<tr class="line1">
			<td width="200">소개</td>
			<td><s:property value="target_resultClass.intro" /></td>
		</tr >
	</table>
	<br></br>
	<div>
		<a href='likeAction.action'
					onclick="return button_event();"> <img src="image/like_off.png"
					onMouseOver="this.src='image/like_on.png'"
					onMouseOut="this.src='image/like_off.png'" border="0"></a>
	</div>		
	</form>
	<br></br>
</body>
</html>