<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- header 부분 -->
	<tiles:insertAttribute name="header" />
	
	<!-- aside 부분 -->
	<tiles:insertAttribute name="aside" />
	
	<tiles:insertAttribute name="body" />
</body>
</html>