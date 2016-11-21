<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css" text-align="center">
* {
	margin: 0;
	padding: 0;
}

body {

	font: 12px;
}

h3 {
	font-size: 15px;
	width: 800px;
	background: #FFF0F5;
	padding: 10px;
	border: 0px solid #ccc;
	cursor: pointer;
}

p {
	
	font-size: 15px;
	width: 800px;
	background: #FFE4E1;
	border: 0px solid #ccc;
}

.active {
	background-position: right 16px;
}
</style>

<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
	//<![CDATA[
	$(document).ready(
			function() {
				$("#faq p").hide();
				$("#faq h3").click(
						function() {
							$(this).next("p").slideToggle("fast").siblings(
									"p:visible").slideUp("fast");
							$(this).toggleClass("active").siblings("h3")
									.removeClass("active");
						});
			});
	//]]>
</script>
<title>Insert title here</title>
</head>
<body>

	<div id="faq" >
		<h3 >1. 같은 사람이 여러 개의 계정을 만들 수 있나요?</h3>
		<p>&nbsp;&nbsp;한 사람이 다수의 계정을 보유하는 것은 불가능합니다. <br>
		&nbsp;&nbsp;썸데이에서는 회원별로 고유한 코드를 부여하고 있기 때문에, <br>
		&nbsp;&nbsp;다수의 계정을 등록하는 것이 불가능합니다.</p>
		
		<h3>2. 로그인 아이디와 비밀번호를 변경할 수 있나요?</h3>
		<p>&nbsp;&nbsp;네. 두 가지 모두 변경할 수 있습니다. 로그인 아이디(이메일) 변경을 원할 경우,<br>
		&nbsp;&nbsp;고객센터에 연락을 주시면 변경할 수 있습니다. <br>
		&nbsp;&nbsp;단, 아이디는 현재 사용하고 있는 이메일 계정으로 변경할 것을 권유 드립니다. <br>
		&nbsp;&nbsp;인증과 반려, 각종 승인과 관련된 내용들이 회원님의 이메일 계정으로 전송되기 때문입니다. <br>
		&nbsp;&nbsp;비밀번호 변경은 로그인 후 "내 계정"의 "알림수신 및<br>
		&nbsp;&nbsp;비밀번호 변경" 페이지에서 원하는 비밀번호로 변경하실 수 있습니다.</p>
		
		<h3>3. 탈퇴 후 개인 정보는 어떻게 처리되나요?</h3>
		<p>&nbsp;&nbsp;탈퇴 신청 후 7일 간의 유예 기간이 있습니다. <br>
		&nbsp;&nbsp;이 기간 동안 내에 탈퇴 신청 철회를 언제든 하실 수 있습니다.<br>
		&nbsp;&nbsp;유예 기간이 지나면 자동적으로 회원님의 모든 개인 정보는 즉시 삭제됩니다. <br>
		&nbsp;&nbsp;단, 법에서 지정한 일부 정보는 의무기간(3개월~5년, 자세한 사항은 개인정보취급방침 참조)에 <br>
		&nbsp;&nbsp;한해 보존됩니다.</p>
		
		<h3>4. 탈퇴 후 다시 가입할 수 있나요?</h3>
		<p>&nbsp;&nbsp;네, 탈퇴 후 언제든 재가입이 가능합니다. <br>
		&nbsp;&nbsp;탈퇴 신청 직후부터 7일간은 탈퇴 유예 기간으로 탈퇴를 언제든지 철회할 수 있습니다. <br>
		&nbsp;&nbsp;유예 기간이 지날 경우 다시 재가입을 하셔야 하며, <br>
		&nbsp;&nbsp;이 경우 신규 계정 등록, 심층 설문, 인증의 절차를 다시 밟으시면 됩니다.</p>
		
		<h3>5. 저에게 추천되는 상대는 어떻게 정해지나요?</h3>
		<p>&nbsp;&nbsp;썸데이는 외모와 조건뿐만 아니라 성격 및 가치관을 포함한 <br>
		&nbsp;&nbsp;35가지 요인들을 종합적으로 분석하여, 과학적인 매칭 시스템을 통해 <br>
		&nbsp;&nbsp;회원님과 “잘 어울리는 상위 0.3%의 인연”만을 추천합니다. <br>
		&nbsp;&nbsp;더욱 자세한 사항은 홈페이지 내 서비스 상세 설명을 확인하시길 바랍니다.</p>
		
		<h3>6. 상호 호감이 이루어진 뒤에도 계속 새로운 상대가 추천되나요?</h3>
		<p>&nbsp;&nbsp;상호 호감 표현(같은 마음)이 이루어졌더라도 썸데이의 인연 추천은 계속됩니다. <br>
		&nbsp;&nbsp;하지만 마음에 드시는 분을 발견했다면 잠시 "추천 중지 신청"을 하고, <br>
		&nbsp;&nbsp;그분에게 집중하시길 권유 드립니다.</p>
		
		<h3>7. 추천된 상대방의 프로필은 어떻게 보는건가요?</h3>
		<p>&nbsp;&nbsp;마이페이지>나의인연에 들어가 보시면 추천된 인연의 썸네일(요약)사진이 모여 있습니다. <br>
		&nbsp;&nbsp;사진을 클릭하면 어울림 차트, 매력 차트, 성향 차트, 가치관 차트 등이 나오고, <br>
		&nbsp;&nbsp;맨 마지막에 상대의 프로필을 확인할 수 있습니다. <br>
		&nbsp;&nbsp;차트 간 이동은 화면 중앙 양 끝에 있는 화살표 버튼을 클릭해 주시면 됩니다.</p>
		
		<h3>8. 네버굿바이 서비스를 모바일에서도 사용할 수 있나요?</h3>
		<p>&nbsp;&nbsp;네, 모바일 브라우저에서 모바일웹 형태로 이용하실 수 있습니다. <br>
		&nbsp;&nbsp;핸드폰의 인터넷 브라우저에서 썸데이 홈페이지를 방문하시면 이용이 가능합니다. <br>
		&nbsp;&nbsp;모바일 어플리케이션은 현재 개발 진행 중이며, 빠른 시일 내로 출시할 예정입니다.</p>
		
	</div>
</html>
</body>