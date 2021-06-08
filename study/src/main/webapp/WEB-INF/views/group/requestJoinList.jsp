<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/w3-colors-flat.css">
<link rel="stylesheet" type="text/css" href="/study/css/layout.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<script type="text/javascript" src="/study/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/study/js/w3color.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#msgWin').css('display', 'block');	
	
	$('#closeBtn').click(function() {
		$('#msgWin').css('display', 'none');
		var tsno = $('.a').attr('id');
		$('#sno').val(tsno);
		$('#frm').submit();
	});
	
	$('.a').click(function() {
		var tsno = $(this).attr('id');
		var tid = $(this).prev().prev().attr('id');
		$('#sno').val(tsno);
		$('#id').val(tid);
		$('#frm').attr('action', '/study/group/requestAccept.mentor');
		$('#frm').submit();
	});
	
	$('.d').click(function() {
		var tsno = $(this).attr('id');
		var tid = $(this).prev().attr('id');
		$('#sno').val(tsno);
		$('#id').val(tid);
		$('#frm').attr('action', '/study/group/requestDeny.mentor');
		$('#frm').submit();
	});
});
</script>
</head>
<body class="">
	<form method="POST" action="/study/group/myGroup.mentor" name="frm" id="frm">
		<input type="hidden" name="sno" id="sno">
		<input type="hidden" name="id" id="id">
	</form>
	<div id="msgWin" class="w3-modal">
			<div class="w3-modal-content w450 w3-card-4">
				<header class="w3-container w3-green">
					<span class="w3-button w3-display-topright" id="closeBtn">&times;</span>
					<h2 class="w3-padding">가입요청 목록</h2>
				</header>
				<div class="w3-center w3-margin-bottom">
			<c:forEach var="data" items="${LIST}">
					<div class="w3-padding h50">
						<span class="pdt30 ft16" id="${data.id}">${data.id}</span>
						<div class="w3-right w3-button w3-red d" id="${data.sno}">거절</div>			
						<div class="w3-right w3-button w3-blue a" id="${data.sno}">수락</div>			
					</div>
			</c:forEach>					
				</div>
			<c:if test="${empty LIST}">
				<div class="w3-center w3-margin-bottom">
					<h1>가입요청이 없습니다</h1>
				</div>
			</c:if>
			</div>
		</div>
		
</body>
</html>