<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>STUSY Main</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/user.css">
<link rel="stylesheet" type="text/css" href="/study/css/userInfo.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="/study/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/study/js/w3color.js"></script>
<style type="text/css">
.w3-sidebar a {
	font-family: "Roboto", sans-serif
	}
body,h1,h2,h3,h4,h5,h6,.w3-wide {
	font-family: "Montserrat", sans-serif;
	}
#pwmsg, #mmsg, #pwmsg, #repwmsg  {
	display: none;
	}
</style>

<script type="text/javascript">

	$(document).ready(function(){
		

		$('#pw').blur(function(){
			$('#pwmsg').stop().slideUp(300);
			var spw = $('#pw').val();
			
			$.ajax({
				url: '/study/pwCheck.man',
				type: 'post',
				dataType: 'json',
				data: {
					pw: spw
				},
				success: function(data){
					var result = data.result;
					
					if(result == 'OK'){
						$('#pwck').css('background-position', '-102px 0px');
						$('#npw').prop("disabled", false);
						$('#repw').prop("disabled", false);
					} else {
						$('#pwck').css('background-position', '-37px -37px');
						$('#pwmsg').html('### 비밀번호를 다시 확인해 주세요 ###')
						$('#pwmsg').removeClass('w3-text-blue').addClass('w3-text-red');
						$('#pwmsg').stop().slideDown(500);
					}
						
				},
				error: function(){
						alert('### 통신 실패 ###');
				}
			});
		});
		
		
		$('#dbtn').click(function(){	
			var spw = $('#pw').val();
			if(!spw){
				$('#pw').focus();
				return;
			}
			var result = confirm("진심?");
			if(!result){
				return;
			}
			$('#frm').submit();
		});


		
		$('#itemBar').click(function(){
			$('#mySidebar').css('display', 'block');
			$('#myOverlay').css('display', 'block');
		});
			 
		$('#myOverlay').click(function(){
			$('#mySidebar').css('display', 'none');
			$('#myOverlay').css('display', 'none');
		});

		$('.btn').click(function(){
			var tid = $(this).attr('id');
			var url = '';
			
			switch(tid){
			case 'hbtn':
				url = '/study/main.man'
				break;	
			}
			$(location).attr('href', url);
		});
	
		$('#modalbtn').click(function(){
			var search = $('#search').val();
			if(search == 'DB'){
				$(location).attr('href', '/study/DBTableSet.man');
			}
			$('#searchModal').css('display', 'none');
		});
		$('#searchIcon').click(function(){
			$('#searchModal').css('display', 'block');
		});
	});
</script>
</head>
<body class="w3-content" style="max-width:1200px">

	<!-- Sidebar/menu -->
	<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
	  <div class="w3-container w3-display-container w3-padding-16">
	    <h3 class="w3-wide w3-button btn" id="hbtn"><b>Home</b></h3>
	  </div>
	  <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
	    <a href="/study/userInfo.man" class="w3-bar-item w3-button Fbtn" id="userbtn"> 내 정보 열람 </a>
	    <a href="#" class="w3-bar-item w3-button Fbtn" id="studybtn"> 스터디 그룹 관리 </a>
	    <a href="/study/userInfoEdit.man" class="w3-bar-item w3-button Fbtn" id="userEditbtn">정보 수정</a>
	    <a href="#" class="w3-bar-item w3-button Fbtn" id="userDeletebtn">회원 탈퇴</a>
	  </div>
	  <div class="w3-padding-64">
		  <a href="#footer" class="w3-bar-item w3-button w3-padding">Contact</a> 
		  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding" >공지사항</a> 
		  <a href="#footer"  class="w3-bar-item w3-button w3-padding">즐겨찾기</a>
	  </div>
	</nav>
	
	<!-- Top menu on small screens -->
	<header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
	  <div class="w3-bar-item w3-padding-24 w3-wide">STUDY</div>
	  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding-24 w3-right" id="itemBar"><i class="fa fa-bars"></i></a>
	</header>
	
	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large"  style="cursor:pointer" title="close side menu" id="myOverlay"></div>
	
	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left:250px">
	
	  <!-- Push down content on small screens -->
	  <div class="w3-hide-large" style="margin-top:83px"></div>
	  
	  <!-- Top header -->
	  <header class="w3-container w3-xlarge">
	    <p class="w3-right">
	      <a href="#" class="fa fa-search w3-margin-right" id="searchIcon"></a>

<c:if test='${not empty SID}'>
	      <a href="/study/logout.man" class="fa fa-sign-out w3-margin-right" id="logoutIcon"></a>
	      <a href="/study/userInfo.man" class="fa fa-user-circle" id="userInfoIcon"></a>
</c:if>
<c:if test='${empty SID}'>
	      <a href="/study/login.man" class="fa fa-sign-in w3-margin-right" id="loginIcon"></a>
	      <a href="/study/join.man" class="fa fa-address-card-o"></a>
</c:if>
	    </p>
	  </header>
	
	  <!-- Image header -->
	  	
	  	<section class="userDel-form" >
	  	
			<form method="POST" action="/study/userDelProc.man" name="frm" id="frm"
				class="">

				<div class="int-area">
					<input type="text" name="id" id="id"  value="${DATA.id}" readonly>
					<label for="id">USER ID</label>
				</div>
				
				<div class="int-area">
					<input type="password" name="pw" id="pw"  placeholder="패스워드를 입력하세요" required>
					<label for="pw">PASSWORD</label>
					<div class="logo pwckBox" id="pwck"></div>
					<span class="w3-col" id="pwmsg"></span>
				</div>
				
				<div class="btn-area">
					<button type="button" class="w3-green" id="dbtn">회원탈퇴</button>
				</div>
			
			</form>
	</section>
	
	

	</div>
	
	<!-- Search Modal -->
	<div id="searchModal" class="w3-modal">
	  <div class="w3-modal-content w3-animate-zoom" style="padding:32px">
	    <div class="w3-container w3-white w3-center">
	      <i onclick="$('#searchModal').css('display', 'none')" class="fa fa-remove w3-right w3-button w3-transparent w3-xxlarge"></i>
	      <h2 class="w3-wide">스터디 검색</h2>
	      <p>아무 내용이나</p>
	      <p><input class="w3-input w3-border" type="text" name="search" id="search" placeholder="Enter study"></p>
	      <button type="button" class="w3-button w3-padding-large w3-red w3-margin-bottom" id="modalbtn">검색</button>
	    </div>
	  </div>
	</div>


</body>
</html>