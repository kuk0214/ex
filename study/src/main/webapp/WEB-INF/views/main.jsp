<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>STUSY Main</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/user.css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c1afd2a16f49bfa19fe8410db6196e66&libraries=services"></script>
<script type="text/javascript" src="/study/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/study/js/w3color.js"></script>

<style type="text/css">
</style>
<script type="text/javascript">
	$(document).ready(function(){
		
		$('#itemBar').click(function(){
			$('#mySidebar').css('display', 'block');
			$('#myOverlay').css('display', 'block');
		});
			 
		$('#myOverlay').click(function(){
			$('#mySidebar').css('display', 'none');
			$('#myOverlay').css('display', 'none');
		});
			
		$('.Fbtn').click(function(){
			$(this).addClass('w3-white');
			$(this).siblings().removeClass('w3-white');
			
			var tid = $(this).attr('id');
			var id = "#" + tid + "Bar";
			if ($(id).attr('class').indexOf("w3-show") == -1) {
				  $(id).addClass('w3-show');
				  $(id).siblings().removeClass('w3-show');
			  } else {
				  $(id).removeClass('w3-show');
			  }
		});

		$('.btn').click(function(){
			var tid = $(this).attr('id');
			var url = '';
			
			switch(tid){
			case 'sjbtn':
				url = '/study/studyboard/studyboardList.cls'
				break;	
			}
			$(location).attr('href', url);
		});
		
		$('#modalbtn').click(function(){
			var search = $('#search').val();
			if(search == 'DB'){
				$(location).attr('href', '/study/DBTableSet.cls');
			}
			$('#searchModal').css('display', 'none');
		});
		$('#searchIcon').click(function(){
			$('#searchModal').css('display', 'block');
		});
		
		var mapContainer = document.getElementById('map');
		var mapOptions = {
			center: new kakao.maps.LatLng(37.55507, 126.97070),
			level: 8,
			scrollwheel : false,
			draggable : false,
			disableDoubleClickZoom : true,
			tileAnimation : false
		};
		var map = new kakao.maps.Map(mapContainer, mapOptions);
		
		var obj = ${STR}
		
		for (let i = 0 ; i < obj.length ; i++ ) {
			
			var geocoder = new kakao.maps.services.Geocoder();
			
			geocoder.addressSearch((obj[i].loc.substring(3) == '노원구')? '동일로 213길' : (obj[i].loc == '온라인')? '경기 고양시 덕양구 내곡동 1-4' : 
									(obj[i].loc.substring(3) == '중구')? '마른내로2길' : obj[i].loc.substring(3),
									function(result, status) {

			     if (status === kakao.maps.services.Status.OK) {
	
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords
			        });
	
			        var infowindow = new kakao.maps.InfoWindow({
			            content: '<div class="w150 w3-center">'+ ((obj[i].loc == '온라인') ? obj[i].loc : obj[i].loc.substring(3)) + ' 그룹 ' + obj[i].cnt + '</div>'
			        });
			        infowindow.open(map, marker);
			     }
		     });
		}
		
		var title = '<h2 class="ft24 w3-center w3-blue">이용하고있는 그룹 수</h2>';
		var position = new kakao.maps.LatLng(37.65507, 126.94070);
		var customOverlay = new kakao.maps.CustomOverlay({
		    position: position,
		    content: title   
		});
		customOverlay.setMap(map);
	});
</script>
</head>
<body class="w3-content" style="max-width:1200px">

	<%@ include file="include/header.jsp" %>
	<div id="map"  style="width:1200px;height:800px;"></div>
	
	

	
	
	<%-- 메일인증 처리 메세지 처리 --%>

<c:if test="${not empty param.msg}">
		<div id="msgWin" class="w3-modal">
			<div class="w3-modal-content mxw600 w3-card-4 ">
	<c:if test="${param.msg eq 'OK'}">
				<header class="w3-container w3-blue">
					<span class="w3-button w3-display-topright" id="closeBtn">&times;</span>
					<h2>인증 성공</h2>
				</header>
			
				<div class="w3-container w3-maring-bottom">
					<h3 class="w3-padding w3-text-blue">메일인증이 성공하였습니다.</h3>
				</div>
	</c:if>
	
	<c:if test="${param.msg eq 'NO'}">
				<header class="w3-container w3-red">
					<span class="w3-button w3-display-topright" id="closeBtn">&times;</span>
					<h2>인증 실패</h2>
				</header>
			
				<div class="w3-container w3-maring-bottom">
					<h3 class="w3-padding w3-text-red">메일인증이 실패하였습니다.</h3>
				</div>
	</c:if>
	
	<c:if test="${param.msg eq 'needmail'}">
				<header class="w3-container w3-blue">
					<span class="w3-button w3-display-topright" id="closeBtn">&times;</span>
					<h2>메일 인증</h2>
				</header>
			
				<div class="w3-container w3-maring-bottom">
					<h3 class="w3-padding w3-text-red">메일인증 후 로그인 해주세요.</h3>
				</div>	
	</c:if>
			
			</div>
		</div>

		
		<script type="text/javascript">
			$(function(){
				$('#msgWin').css('display', 'block');
				
				$('#closeBtn').click(function(){
					$('#msgWin').css('display', 'none');					
				});
			});
		</script>
</c:if>
</body>
</html>