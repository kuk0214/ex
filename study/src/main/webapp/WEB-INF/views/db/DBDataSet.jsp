<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/user.css">
<script type="text/javascript" src="/study/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/study/js/w3color.js"></script>
<style type="text/css"></style>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="w3-content w3-center mxw600 w3-margin-top">
<c:if test="${not empty MSG}">
		<h1>모든 데이터 생성에 성공했습니다</h1>
</c:if>
<c:if test="${empty MSG}">
		<h1>생성 실패한 데이터가 있습니다</h1>
</c:if>
	</div>
</body>
</html>