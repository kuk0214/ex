<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/w3-colors-flat.css">
<link rel="stylesheet" type="text/css" href="/study/css/user.css">
<style type="text/css">
	aside {
		position : relative;
		left: 22%;
		margin-top: 100px;
		z-index: 1;
	}
</style>
</head>
<body>
	<aside class="w3-sidebar mxw200 w3-bar-block w3-white w3-collapse w3-top"id="mySidebar">
	  <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
	  	<h1>게시판</h1><br>
	    <a href="#" class="w3-bar-item w3-button" id="userbtn"> 자유 게시판 </a>
	    <a href="#" class="w3-bar-item w3-button" id="studybtn"> 질문&답변 게시판 </a>
	    <a href="#" class="w3-bar-item w3-button" id="userEditbtn"> 후기 게시판</a>
	  </div>
	  <div class="w3-padding-64">
		  <a href="#footer" class="w3-bar-item w3-button w3-padding">Contact</a> 
		  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding" >공지사항</a> 
		  <a href="#footer"  class="w3-bar-item w3-button w3-padding">즐겨찾기</a>
	  </div>
	</aside>
</body>
</html>