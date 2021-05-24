<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cafe login</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/user.css">
<link rel="stylesheet" type="text/css" href="/study/css/style.css">
<script type="text/javascript" src="/study/js/jquery-3.6.0.min.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#lbtn').click(function(){
			var sid = $('#id').val();
			var spw = $('#pw').val();
			if(!sid){
				$('#id').next('label').addClass('warning');
				setTimeout(function(){
					$('label').removeClass('warning');
				}, 1500);
				return;
			} else if(!spw){
				$('#pw').next('label').addClass('warning');	
				setTimeout(function(){
					$('label').removeClass('warning');
				}, 1500);
				return;
			}
			
			$("#frm").submit();
		});
		
		// forgot passwoard click event
		$('#fpbtn').click(function(){		
			var sid = prompt("USER ID : ");
			var smail = prompt("USER MAIL : ");
				$.ajax({
					url: '/study/gmail/fpmailSendAction.man',
					type: 'post',
					dataType: 'json',
					data: {
						id: sid,
						mail: smail
					},
					success: function(data){
						var result = data.result;
						
						if(result == 'OK'){
							alert('*** 메일을 확인하세요 ***');
							location.href = '/study/main.man';
						} else {
							alert('### 실패 하였습니다.###');							
						}
					},
					error: function(){
							alert('### 통신 실패 ###');
					}
				});			
		});



		
		
	});
</script>
</head>
<body>
	<section class="login-form">
		<h1>LOGIN</h1>
		<form method="POST" action="/study/member/loginProc.man" name="frm" id="frm"
			class="">
			<div class="int-area">
				<input type="text" name="id" id="id" autocomplete="off" required>
				<label for="id">USER ID</label>
			</div>
			<div class="int-area">
				<input type="password" name="pw" id="pw" autocomplete="off" required>
				<label for="pw">PASSWORD</label>
			</div>
			<div class="btn-area">
				<button id="lbtn">LOGIN</button>
			</div>
		</form>
		<div class="caption">
			<a href="#" id="fpbtn">Forgot Passowrd?</a>
		</div>
	</section>
	
	
	
	

<%-- 로그인 처리 실패시 메세지 처리 --%>
<%-- <c:if test="${not empty param.MSG}"> --%>
<c:if test="${not empty MSG}">
		<div id="msgWin" class="w3-modal">
			<div class="w3-modal-content mxw600 w3-card-4 ">
				<header class="w3-container w3-red">
					<span class="w3-button w3-display-topright" id="closeBtn">&times;</span>
					<h2>로그인 실패</h2>
				</header>
			
				<div class="w3-container w3-maring-bottom">
					<h3 class="w3-padding w3-text-red">로그인에 실패했습니다!</h3>
				</div>
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