<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/layout.css">
<script type="text/javascript" src="/study/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/study/js/w3color.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#cbtn').click(function() {
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<%@ include file="../include/grouplayout.jsp" %>
	<section class="w3-content w3-margin-top">
		
		<form method="POST" action="/study/group/addGroupProc.mentor" name="frm" id="frm"
				class="w3-col mgl50 mgt50 w800">
			<input type="hidden" name="id" value="${SID}">
			<div style="margin-left: 300px;">
				<label for="sname">스터디 이름</label>
				<input type="text" name="sname" id="sname" class="mgl20">
			</div>
			<div style="margin-left: 300px;">
				<label for="loc">스터지 지역</label>
				<input type="text" name="loc" id="loc" class="mgl20">
			</div>
			<div style="margin-left: 300px;">
				<label for="maxcnt">최대 인원수</label>
				<input type="text" name="maxcnt" id="maxcnt" class="mgl20">
			</div>
		</form>
		<div class="w3-col m2 w3-button w3-right w3-margin-top w3-border w3-border-lime" id="cbtn">스터디 만들기</div>
	</section>
</body>
</html>