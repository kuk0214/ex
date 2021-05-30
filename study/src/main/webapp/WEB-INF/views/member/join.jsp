<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/user.css">
<link rel="stylesheet" type="text/css" href="/study/css/style.css">
<script type="text/javascript" src="/study/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/study/js/w3color.js"></script>
<style type="text/css">
	#idmsg, #mmsg, #pwmsg, #repwmsg  {
		display: none;
	}
</style>
<script type="text/javascript">

	$(document).ready(function(){

		$('#rbtn').click(function(){
			document.frm.reset();
		});
		
		$('#id').change(function(){
			$('#idmsg').stop().slideUp(300);
			var sid = $('#id').val();
			
			var exp = /^(?=.*[a-zA-Z])[a-zA-Z0-9!#$%&*-+_]{4,15}$/;
			var pwresult = exp.test(sid);
			
			if(sid && !pwresult){
				alert(' 아이디 형식이 맞지 않습니다. ')
				$('#id').val('');
				$('#id').focus();
				return;
			}
			
			$.ajax({
				url: '/study/idcheck.mentor',
				type: 'post',
				dataType: 'json',
				data: {
					id: sid
				},
				success: function(data){
					var result = data.result;
					
					if(result == 'OK'){
						$('#idmsg').html('*** 사용가능한 아이디 입니다.***')
						$('#idmsg').removeClass('w3-text-red').addClass('w3-text-blue');
						$('#idmsg').stop().slideDown(500);
					} else {
						$('#idmsg').html('### 사용불가능한 아이디 입니다.###')
						$('#idmsg').removeClass('w3-text-blue').addClass('w3-text-red');
						$('#idmsg').stop().slideDown(500);
						
					}
				},
				error: function(){
						alert('### 통신 실패 ###');
				}
			});

		});
		
		$('#mck').click(function(){
			$('#mmsg').stop().slideUp(300);
			var smail = $('#mail').val();

			$.ajax({
				url: '/study/gmail/gmailSendAction.mentor',
				type: 'post',
				dataType: 'json',
				data: {
					mail: smail
				},
				success: function(data){
					var result = data.result;
					
					if(result == 'OK'){
						$('#mmsg').html('*** 가입 후 메일을 확인하세요 ***')
						$('#mmsg').removeClass('w3-text-red').addClass('w3-text-blue');
						$('#mmsg').stop().slideDown(500);
					} else {
						$('#mmsg').html('### 사용불가능한 이메일 입니다.###')
						$('#mmsg').removeClass('w3-text-blue').addClass('w3-text-red');
						$('#mmsg').stop().slideDown(500);
						
					}
				},
				error: function(){
						alert('### 통신 실패 ###');
				}
			});

		});
		
		$('#pw').change(function(){
			var spw = $('#pw').val();
			var exp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!#$%&*-+_])[a-zA-Z0-9~!#$%&*-+_]{4,15}$/;
			var pwresult = exp.test(spw);
			
			if(spw && !pwresult){
				alert(' 비밀번호 형식이 맞지 않습니다. ')
				$('#pw').val('');
				$('#pw').focus();
				return;
			}
		});
		$('#repw').change(function(){
			var spw = $('#pw').val();
			var srepw = $('#repw').val();
			
			if(spw != srepw){
				alert(' 비밀번호 일치하지 않습니다. ')
				$('#repw').val('');
				$('#repw').focus();
				return;
			}
		});
		
		$('#jbtn').click(function(){
			var sname = $('#name').val();
			var sid = $('#id').val();
			var spw = $('#pw').val();
			var smail = $('#mail').val();
			var stel = $('#tel').val();
			var sgen = $('[name="gen"]:checked').val();
			var sgrade = $('[name="grade"] option:selected').val();
			var sloc = $('[name="loc"] option:selected').val();
			$('#repw').prop('disabled', true);
			
			if(!(sid && sname && spw && smail && stel && sgen && sloc)) {
				alert('정보를 입력하세요');
				return;
			}			
			$('#frm').submit();
		});
		
	});
	
</script>
</head>
<body>
	<section class="login-form" >
		<h1>RESISTER</h1>
		<form method="POST" action="/study/member/joinProc.mentor" name="frm" id="frm"
			class="">
			<div class="int-area">
				<input type="text" name="name" id="name" autocomplete="off" required>
				<label for="id">NAME</label>
			</div>
			<div class="int-area">
				<input type="text" name="id" id="id" autocomplete="off" required>
				<label for="id">USER ID</label>
			</div>
			<span class="w3-col" id="idmsg"></span>
			
			<div class="int-area">
				<input type="password" name="pw" id="pw" autocomplete="off" required>
				<label for="pw">PASSWORD</label>
			</div>
			<div class="int-area">
				<input type="password" name="repw" id="repw" autocomplete="off" required>
				<label for="repw">PASSWORD CHECK</label>
			</div>
			<span class="w3-col w3-text-red" id="repwmsg"># 비밀번호가 일치하지 않습니다.</span>
			
			<div class="int-area">
				<input type="text" name="mail" id="mail" autocomplete="off" required>
				<label for="id">MAIL</label>
				<div class="w3-button w3-red w3-small w3-round-large mckBox" id="mck">mail check</div>
			</div>
			<span class="w3-col" id="mmsg"></span>
			
			<div class="int-area">
				<input type="text" name="tel" id="tel" autocomplete="off" required>
				<label for="id">TEL</label>
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
				<button type="button"  id="jbtn">join</button>
			</div>
		</form>

	</section>

        
<%-- 회원가입 처리 실패시 메세지 처리 --%>
<c:if test="${not empty MSG}">
		<div id="msgWin" class="w3-modal">
			<div class="w3-modal-content mxw600 w3-card-4">
				<header class="w3-container w3-red">
					<span class="w3-button w3-display-topright" id="closeBtn">&times;</span>
					<h2>회원가입 실패</h2>
				</header>
			
				<div class="w3-container w3-maring-bottom">
					<h3 class="w3-padding w3-text-red">${MSG}</h3>
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