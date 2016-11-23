<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<script>
	function Email(){
		var form = document.authenform;
	        authNum = ${authNum};
		
		if(!form.authnum.value){
			alert("인증번호를 입력하세요");
			return false;
		}
		if(form.authnum.value!=authNum){
			alert("틀린 인증번호입니다. 인증번호를 다시 입력해주세요");
			form.authNum.value="";
			return false;
		}
		if(form.authnum.value==authNum){
			alert("인증완료");
			opener.document.join.mailCheck.value="1";
			authNum = "";
			self.close();
			return true;
		}
	}
</script>
<body>
  <div class="container">
        <div class="row">
             <div class="box"> 
                <div class="col-lg-12 text-center">
                    <div id="carousel-example-generic" class="carousel slide">
                        <!-- Indicators -->
                        <ol class="carousel-indicators hidden-xs">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <img class="img-responsive img-full" src="resources/img/slide_01.jpg" alt="">
                            </div>
                            <div class="item">
                                <img class="img-responsive img-full" src="resources/img/slide_02.jpg" alt="">
                            </div>
                            <div class="item">
                                <img class="img-responsive img-full" src="resources/img/slide_03.jpg" alt="">
                            </div>
                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                            <span class="icon-prev"></span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                            <span class="icon-next"></span>
                        </a>
                    </div>
                    <h2 class="brand-before">
                        <small>Welcome to Some Day</small>
                    </h2>
                    <h2>하루에한번 당신의 인연이 기다리고있습니다</h2>
                    <hr class="tagline-divider">
                    <h2>
                        <small>By
                            <strong>Your ties are waiting for you once a day</strong>
                        </small>
                    </h2>
                </div>
            </div>
        </div>
    </div>
</body>