<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/layout.css">
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
		
		$('#ebtn').click(function() {
			var title = $('#title').val().trim();
			var body = $('#body').val().trim();
			if(!title){
				alert('# 제목을 확인하세요! #');
				return
			}
			if(!body){
				alert('# 내용을 확인하세요! #');
				return
			}
			
			if(title == '${DATA.title}' && body == '${DATA.body}') {
				alert('### 수정된 내용이 없습니다. ###');
				return
			}
			
			if(title == '${DATA.title}') {
				$('#title').prop('disabled', true);
			}
			
			if(body == '${DATA.body}') {
				$('#body').prop('disabled', true);
			}
			
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<form method="POST"  action="/study/group/studyBoard.mentor" id="pfrm" name="pfrm">
		<input type="hidden" name="nowPage" value="${nowPage}">
	</form>
	<div class="w3-content mxw700 w3-margin-top w3-padding">
		<h1 class="w3-purple w3-padding w3-center w3-card-4 w3-margin-top w3-margin-bottom">글 수정</h1>
		
		<!-- form 태그 -->
		<form method="POST" action="/study/group/studyBoardEditProc.mentor" name="frm" id="frm"
			class="w3-col w3-padding w3-margin-bottom w3-card-4 ">
			<input type="hidden" id="sbno" name="sbno" value="${DATA.sbno}">
			<input type="hidden" id="sno" name="sno" value="${DATA.sno}">
			<div class="w3-col w3-margin-top">
				<div class="w3-col pdr30">
					<span class="w3-col ft10">스터디 그룹 이름 : ${DATA.sname}</span>
				</div>
			</div>
			<div class="w3-col w3-margin-top pdb10 w3-border-bottom w3-border-light-grey">
				<div class="w3-rest pdr30">
					<input type="text" name="title" id="title" class="w3-rest w3-input w3-round w3-border" placeholder="제목을 입력하세요!" value="${DATA.title}">
				</div>
			</div>
			<div class="w3-col w3-margin-top w3-margin-bottom pdb10 w3-border-bottom w3-border-light-grey">
				<div class="w3-rest pdr30">
					<textarea name="body" id="body" rows="7" 
							class="w3-rest w3-input w3-round w3-border vresize" placeholder="내용을 입력하세요!" >${DATA.body}</textarea>
				</div>
			</div>
		</form>
		
		<!-- 버튼 태그 -->
		<div class="w3-col w3-margin-top w3-card-4">
			<div class="w3-third w3-green w3-hover-lime w3-button" id="cbtn">취소</div>
			<div class="w3-third w3-blue w3-hover-pink w3-button" id="rbtn">리셋</div>
			<div class="w3-third w3-orange w3-hover-red w3-button" id="ebtn">수정완료</div>
		</div>
	</div>
</body>
</html>