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
</head>
<body class="w3-content" style="max-width:1200px">
	<%@ include file="../include/grouplayout.jsp" %>
	<section class="w3-content w3-margin-top">
		<div class="w3-col w3-margin-top w3-margin-bottom">
			<h2 class="w3-padding mgb10 ft24">스터디 만들기</h2>
		</div>
		<form method="POST" action="/study/group/addGroupProc.mentor" name="frm" id="frm"
				class="w3-col mxw700">
			<div class="w3-col">
				<label for="sname">스터디 이름</label>
				<input type="text" name="sname" id="sname">
			</div>
			<div>
				<label for="loc">스터지 지역</label>
				<input type="text" name="loc" id="loc">
			</div>
		</form>

	</section>
</body>
</html>