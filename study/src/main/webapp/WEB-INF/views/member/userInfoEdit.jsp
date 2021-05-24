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

		if(${DATA.gen eq '남자'}){
			$('#mgen').attr("checked", true);			
		} else {
			$('#fgen').attr("checked", true);	
		}

		
		$('#loc option').each(function(){
			if("${DATA.loc}" == $(this).val()){
		    	$(this).attr("selected", true); 
			}
		});
		  
		$('#grade option').each(function(){
			if("${DATA.grade}" == $(this).val()){
				$(this).attr("selected", true);
			}
		});
		

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
						$('#npw').prop("placeholder", "변경할 패스워드를 입력하세요");
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
		
		$('#npw').change(function(){
			var spw = $('#npw').val();
			var exp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!#$%&*-+_])[a-zA-Z0-9~!#$%&*-+_]{4,15}$/;
			var pwresult = exp.test(spw);
			
			if(spw && !pwresult){
				alert(' 비밀번호 형식이 맞지 않습니다. ')
				$('#npw').val('');
				$('#npw').focus();
				return;
			}
		});
		$('#repw').change(function(){
			var spw = $('#npw').val();
			var srepw = $('#repw').val();
			
			if(spw != srepw){
				alert(' 비밀번호가 일치하지 않습니다. ')
				$('#repw').val('');
				$('#repw').focus();
				return;
			}
		});
	
		
		$('#rbtn').click(function(){
			document.frm.reset();
		});

		$('#ebtn').click(function(){

			var ename = $('#name').val();
			var epw = $('#pw').val();
			var enpw = $('#npw').val();
			var erepw = $('#repw').val();
			var etel = $('#tel').val();
			var egen = $('[name="gen"]:checked').val();
			var egrade = $('[name="grade"] option:selected').val();
			var eloc = $('[name="loc"] option:selected').val();

			if(ename == '${DATA.name}'){
				$('#name').prop('disabled', true);
			}
			if(etel == '${DATA.tel}'){	
				$('#tel').prop('disabled', true);
			}
			if(egrade == '${DATA.grade}'){	
				$('#grade').prop('disabled', true);
			}
			if(eloc == '${DATA.loc}'){
				$('#loc').prop('disabled', true);
			}
			if(egen == '${DATA.gen}'){
				$('#gen').prop('disabled', true);
			}
			if(enpw == '${DATA.pw}'){
				$('#npw').prop('disabled', true);
			
			}else {
				if(enpw != erepw){
					alert(' 비밀번호가 일치하지 않습니다. ')
					$('#repw').val('');
					$('#repw').focus();
					return;
				}
			}
			
			$('#pw').prop('disabled', true);
			$('#repw').prop('disabled', true);
			
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
	    <a href="/study/userDel.man" class="w3-bar-item w3-button Fbtn" id="userDeletebtn">회원 탈퇴</a>
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
	  	
	  	<section class="login-form" >
			<h1>${DATA.id}님 정보 수정</h1>
			<form method="POST" action="/study/userInfoEditProc.man" name="frm" id="frm"
				class="">
				<div class="int-area">
					<input type="text" name="name" id="name"  value="${DATA.name}" required>
					<label for="id">NAME</label>
				</div>
				<div class="int-area">
					<input type="text" name="id" id="id"  value="${DATA.id}" disabled>
					<label for="id">USER ID</label>
				</div>
				<span class="w3-col" id="idmsg"></span>
				
				<div class="int-area">
					<input type="password" name="pw" id="pw"  placeholder="기존 패스워드를 입력하세요">
					<label for="pw">PASSWORD</label>
					<div class="logo pwckBox" id="pwck"></div>
					<span class="w3-col" id="pwmsg"></span>
				</div>
				<div class="int-area">
					<input type="password" name="pw" id="npw" disabled>
					<label for="npw">NEW PASSWORD</label>
				</div>
				<div class="int-area">
					<input type="password" name="repw" id="repw"  disabled>
					<label for="repw">PASSWORD CHECK</label>
				</div>
				<span class="w3-col w3-text-red" id="repwmsg"># 비밀번호가 일치하지 않습니다.</span>
				
				<div class="int-area">
					<input type="text" name="tel" id="tel" value="${DATA.tel}" required>
					<label for="tel">TEL</label>
				</div>
				
			<div class="select-area">
				<select name="grade" id="grade" required>
					<option value="">회원 분류</option>
					<option value="일반">일반</option>
					<option value="멘토">멘토</option>
					<option value="업체">업체</option>
				</select>		
				<select name="loc" id="loc" required>
					<option value="">지역 선택</option>
					<option value="서울">서울</option>
					<option value="부산">부산</option>
					<option value="none">선택안함</option>
				</select>		
			</div>
				
				<div class="radio-area">		
						<div class="w3-half">
							<input type="radio" name="gen" id="mgen" class="w3-radio" value="M"> <label for="mgen"> 남자</label>
						</div>
						<div class="w3-half">
							<input type="radio" name="gen" id="fgen" class="w3-radio" value="F"> <label for="fgen"> 여자</label>
						</div>			
				</div>
				
				<div class="w3-half btn-area">
					<button type="button" class="w3-green" id="rbtn">RESET</button>
				</div>
				<div class="w3-half btn-area">
					<button type="button" id="ebtn">EDIT</button>
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