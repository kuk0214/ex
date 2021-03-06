<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/w3-colors-flat.css">
<link href="/study/css/layout.css" rel="stylesheet" type="text/css">
<style type="text/css">
	aside {
		float: left;
		overflow: hidden;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#main').click(function() {
			$(location).attr('href', '/study/notice/noticeList.mentor');
		});
	});
</script>
</head>
<body>
	<aside class=" mxw190 w3-bar-block w3-white"id="mySidebar">
	  <div class="pdt10 w3-large w3-text-grey">
	  	<a><h1 class="ft24 mgt20 w3-button" id="main">스터디</h1></a><br>
	    <a href="/study/group/addGroup.mentor" class="w3-bar-item w3-button"> 스터디 만들기</a>
	    <a href="/study/group/myGroup.mentor" class="w3-bar-item w3-button"> 내 스터디 관리</a>
	    <a href="/study/group/studyBoard.mentor" class="w3-bar-item w3-button"> 스터디 모집</a>
	    <a href="/study/mentor/mentorList.mentor" class="w3-bar-item w3-button"> 멘토 리스트</a>
	  </div>
	  <div class="w3-padding-64">
		  <a href="#footer" class="w3-bar-item w3-button w3-padding">Contact</a> 
		  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding" id="notice">공지사항</a> 
		  <a href="#footer"  class="w3-bar-item w3-button w3-padding">즐겨찾기</a>
	  </div>
	</aside>
</body>
</html>