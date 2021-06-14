<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/study/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if("${MSG}") {
			alert('### 인원이 가득 찼습니다. ###');
		}
		$('#frm').submit();
	});
</script>
</head>
<body>
	<form method="post" action="${PATH}" id="frm" name="frm">
		<input type="hidden" name="nowPage" id="nowPage" value="${nowPage}">
		<input type="hidden" name="sno" id="sno" value="${SNO}">
		<input type="hidden" name="sbno" id="sbno" value="${SBNO}">
		<input type="hidden" name="sid" id="sid" value="${SID}">
	</form>
</body>
</html>