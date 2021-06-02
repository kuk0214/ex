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
<style type="text/css">
	label.ft14 {
		line-height: 200%;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#cbtn').click(function() {
			$('#pfrm').submit();
		});
		
		$('#rbtn').click(function() {
			document.frm.reset();
		});
		
		$('#sbtn').click(function() {
			var title = $('#title').val().trim();
			var body = $('#body').val().trim();
			if(!title){
				alert('# 제목을 확인하세요! #');
				return;
			}
			if(!body){
				alert('# 내용을 확인하세요! #');
				return;
			}
			$('#frm').submit();
		});
			
	});
</script>
</head>
<body>
	<form method="POST"  action="/study/notice/noticeList.mentor" id="pfrm" name="pfrm">
		<input type="hidden" id="nowPage" name="nowPage" value="${nowPage}">
	</form>
	<div class="w3-content mxw700 w3-margin-top w3-padding">
		<h1 class="w3-purple w3-padding w3-center w3-card-4 w3-margin-top w3-margin-bottom">글 작성</h1>
		
		<!-- form 태그 -->
		<form method="POST" action="/study/notice/noticeWriteProc.mentor" name="frm" id="frm"
			class="w3-col w3-padding w3-margin-bottom w3-card-4 ">
			<div class="w3-col w3-margin-top pdb10 w3-border-bottom w3-border-light-grey">
				<div class="w3-rest pdr30">
					<input type="text" name="title" id="title" class="w3-rest w3-input w3-round w3-border" placeholder="제목을 입력하세요!">
				</div>
			</div>
			<div class="w3-col w3-margin-top w3-margin-bottom pdb10 w3-border-bottom w3-border-light-grey">
				<div class="w3-rest pdr30">
					<textarea name="body" id="body" rows="7" 
							class="w3-rest w3-input w3-round w3-border vresize" placeholder="내용을 입력하세요!"></textarea>
				</div>
			</div>
		</form>
		
		<!-- 버튼 태그 -->
		<div class="w3-col w3-margin-top w3-card-4">
			<div class="w3-third w3-green w3-hover-lime w3-button" id="cbtn">취소</div>
			<div class="w3-third w3-blue w3-hover-pink w3-button" id="rbtn">리셋</div>
			<div class="w3-third w3-orange w3-hover-red w3-button" id="sbtn">작성완료</div>
		</div>
	</div>
</body>
</html>