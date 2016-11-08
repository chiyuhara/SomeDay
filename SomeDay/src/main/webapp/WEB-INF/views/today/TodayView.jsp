<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>


<!-- 팝업 css부분 -->
<style type="text/css">
.layer {
	display: none;
	position: fixed;
	_position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 100;
}

.layer .bg {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: #000;
	opacity: .5;
	filter: alpha(opacity = 50);
}

.layer .pop-layer {
	display: block;
}

.pop-layer {
	display: none;
	position: absolute;
	top: 50%;
	left: 50%;
	width: 450px;
	height: auto;
	background-color: #fff;
	border: 5px solid #3571B5;
	z-index: 10;
}

.pop-layer .pop-container {
	padding: 20px 25px;
}

.pop-layer p.ctxt {
	color: #666;
	line-height: 25px;
}

.pop-layer .btn-r {
	width: 100%;
	margin: 10px 0 20px;
	padding-top: 10px;
	border-top: 1px solid #DDD;
	text-align: right;
}

a.cbtn {
	display: inline-block;
	height: 25px;
	padding: 0 14px 0;
	border: 1px solid #304a8a;
	background-color: #3f5a9d;
	font-size: 13px;
	color: #fff;
	line-height: 25px;
}

a.cbtn:hover {
	border: 1px solid #091940;
	background-color: #1f326a;
	color: #fff;
}
</style>

<link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
#hearts {
	color: #ee8b2d;
}

#hearts-existing {
	color: #ee8b2d;
}
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
	// Starrr plugin (https://github.com/dobtco/starrr)
	var __slice = [].slice;

	(function($, window) {
	  var Starrr;

	  Starrr = (function() {
	    Starrr.prototype.defaults = {
	      rating: void 0,
	      numStars: 5,
	      change: function(e, value) {}
	    };

	    function Starrr($el, options) {
	      var i, _, _ref,
	        _this = this;

	      this.options = $.extend({}, this.defaults, options);
	      this.$el = $el;
	      _ref = this.defaults;
	      for (i in _ref) {
	        _ = _ref[i];
	        if (this.$el.data(i) != null) {
	          this.options[i] = this.$el.data(i);
	        }
	      }
	      this.createStars();
	      this.syncRating();
	      this.$el.on('mouseover.starrr', 'span', function(e) {
	        return _this.syncRating(_this.$el.find('span').index(e.currentTarget) + 1);
	      });
	      this.$el.on('mouseout.starrr', function() {
	        return _this.syncRating();
	      });
	      this.$el.on('click.starrr', 'span', function(e) {
	        return _this.setRating(_this.$el.find('span').index(e.currentTarget) + 1);
	      });
	      this.$el.on('starrr:change', this.options.change);
	    }

	    Starrr.prototype.createStars = function() {
	      var _i, _ref, _results;
 
	      _results = [];
	      for (_i = 1, _ref = this.options.numStars; 1 <= _ref ? _i <= _ref : _i >= _ref; 1 <= _ref ? _i++ : _i--) {
	        _results.push(this.$el.append("<span class='glyphicon .glyphicon-star-empty'></span>"));
	      }
	      return _results;
	    };

	    Starrr.prototype.setRating = function(rating) {
	      if (this.options.rating === rating) {
	        rating = void 0;
	      }
	      this.options.rating = rating;
	      this.syncRating();
	      return this.$el.trigger('starrr:change', rating);
	    };

	    Starrr.prototype.syncRating = function(rating) {
	      var i, _i, _j, _ref;

	      rating || (rating = this.options.rating);
	      if (rating) {
	        for (i = _i = 0, _ref = rating - 1; 0 <= _ref ? _i <= _ref : _i >= _ref; i = 0 <= _ref ? ++_i : --_i) {
	          this.$el.find('span').eq(i).removeClass('glyphicon-star-empty').addClass('glyphicon-star');
	        }
	      }
	      if (rating && rating < 5) {
	        for (i = _j = rating; rating <= 4 ? _j <= 4 : _j >= 4; i = rating <= 4 ? ++_j : --_j) {
	          this.$el.find('span').eq(i).removeClass('glyphicon-star').addClass('glyphicon-star-empty');
	        }
	      }
	      if (!rating) {
	        return this.$el.find('span').removeClass('glyphicon-star').addClass('glyphicon-star-empty');
	      }
	    };

	    return Starrr;

	  })();
	  return $.fn.extend({
	    starrr: function() {
	      var args, option;

	      option = arguments[0], args = 2 <= arguments.length ? __slice.call(arguments, 1) : [];
	      return this.each(function() {
	        var data;

	        data = $(this).data('star-rating');
	        if (!data) {
	          $(this).data('star-rating', (data = new Starrr($(this), option)));
	        }
	        if (typeof option === 'string') {
	          return data[option].apply(data, args);
	        }
	      });
	    }
	  });
	})(window.jQuery, window);

	$(function() {
	  return $(".starrr").starrr();
	});

	$( document ).ready(function() {
	      
	  $('#hearts').on('starrr:change', function(e, value){
	    $('#count').html(value);
	  });
	  
	  $('#hearts-existing').on('starrr:change', function(e, value){
	    $('#count-existing').html(value);
	  });
	});
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

					<div style="float: left">
						<ahref-""> <img
							src="/Hi_Admin/image/<s:property value='resultClass.file_savname' />"
							style="width: auto; height: 150px; margin-left: -10px;" /> <br>
							<b><s:property value="resultClass.name" /></b><br> <b><s:property
										value="resultClass.phone" /></b><br>
					</div>

					<div style="float: left">
						<img src="image/like_off.png" />
					</div>

					<div style="float: right">
						<img
							src="/Hi_Admin/image/<s:property value='target_resultClass.file_savname' />"
							style="width: auto; height: 150px; margin-left: -10px;" /><br>
							<b><s:property value="target_resultClass.name" /></b><br> <b><s:property
										value="target_resultClass.phone" /></b><br>
					</div>

					<div style="float: right" class="btn-r">
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
	<form:form commandName="todayModel" action="Like" method="post"
		enctype="multipart/form-data">
		<table width="600" border="0" cellspacing="0" cellpadding="2">
			<div id="relative">
				<img
					src="/Hi_Admin/image/<s:property value='target_resultClass.file_savname' />" />
				<div id="absolute2">
					<c:choose>
						<c:when
							test="${meeting.female_like == 0 && meeting.male_like == 0}">
					♡
				</c:when>
						<c:when
							test="${meeting.female_like == 1 && meeting.male_like == 1}">
					♥
					<script>
						layer_open('layer');
					</script>
						</c:when>
						<c:when
							test="${meeting.female_like == 1 && meeting.male_like == 0}">
					☜
				</c:when>
						<c:when
							test="${meeting.female_like == 0 && meeting.male_like == 1}">
					☞
				</c:when>
					</c:choose>
				</div>
			</div>

			<!-- 커플이됬을 경우 이름과 연락처 공개-->
			<c:choose>
				<c:when test="${meeting.female_like == 1 && meeting.male_like == 1}">
					<tr>
						<td width="200"><b>이름</b></td>
						<td><b>${target.name}</b></td>
					</tr>
					<tr>
						<td width="200"><b>연락처</b></td>
						<td><b>${target.phone}</b></td>
					</tr>
				</c:when>
			</c:choose>

			<tr>
				<td width="200">닉네임</td>
				<td>${target.nick}</td>
			</tr>
			<tr>
				<td width="200">나이</td>
				<td>${target.age}</td>
			</tr>
			<tr>
				<td width="200">지역</td>
				<td>${target.area}</td>
			</tr>
			<tr>
				<td width="200">혈액형</td>
				<td>${target.bloodgroup}</td>
			</tr>
			<tr>
				<td width="200">지역</td>
				<td>${target.area}</td>
			</tr>
			<tr>
				<td width="200">소개</td>
				<td>${target.intro}</td>
			</tr>
			<!-- 평점 -->
			<div class="container">
				<div class="row lead">
					<div id="hearts" class="starrr"></div>
					You gave a rating of <span id="count">0</span> star(s)
				</div>

				<div class="row lead">
					<div id="hearts-existing" class="starrr" data-rating='4'></div>
					You gave a rating of <span id="count-existing">4</span> star(s)
				</div>
			</div>
			<!-- 평점 -->
		</table>
		<br></br>
		<div>
			<a href='Like' onclick="return button_event();"> <img
				src="image/like_off.png" onMouseOver="this.src='image/like_on.png'"
				onMouseOut="this.src='image/like_off.png'" border="0"></a>
		</div>
	</form:form>
	<br></br>
</body>
</html>