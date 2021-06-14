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
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<style type="text/css">
	label.ft14 {
		line-height: 200%;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		/* $('#cbtn').click(function() {
			$('#frm').attr('action', '/study/freeboard/freeBoardList.mentor');
			$('#frm').submit();
		}); */
		
		$('#ebtn').click(function() {
			var ttitle = $('#title').val().trim();
			var tbody = $('#body').val().trim();
			
			if(ttitle == '${DATA.title}' && tbody == '${DATA.body}') {
				alert('### 수정된 내용이 없습니다. ###');
				return
			}
			
			if(!ttitle || ttitle == '${DATA.title}') {
				$('#title').prop('disabled', true);
			}
			
			if(!tbody || tbody == '${DATA.body}') {
				$('#body').prop('disabled', true);
			}
			
			$('#frm').submit();			
		});
	});
</script>
</head>
<body>
	<div class="w3-content mxw700 w3-margin-top w3-padding">
		<h1 class="w3-purple w3-padding w3-center w3-card-4 w3-margin-top w3-margin-bottom">글 수정</h1>
		<form method="POST" action="/study/freeboard/freeBoardEditProc.mentor" name="frm" id="frm"
				class="w3-col w3-padding w3-margin-bottom w3-border w3-border-light-grey">
			<input type="hidden" name="nowPage" id="nowPage" value="${nowPage}">
			<input type="hidden" name="frbno" id="frbno" value="${DATA.frbno}">
			<div class="w3-col w3-margin-top pdb10 w3-border-bottom w3-border-light-grey">
				<div class="w3-rest pdr30">
					<input name="title" id="title" class="w3-rest ft12" value="${DATA.title}">
				</div>
			</div>
			<div class="w3-col w3-margin-top pdAll10 w3-border w3-border-light-grey">
				<div class="w3-rest ">
					<textarea rows="7" name="body" id="body" class="w3-col ft12" >${DATA.body}</textarea>
				</div>
			</div>
		</form>
		<div class="w3-col w3-margin-top">
			<!-- <div class="w3-thrid w3-button w3-green" id="cbtn">취소</div> -->
			<div class="w3-thrid w3-button w3-right w3-blue" id="ebtn">수정완료</div>
		</div>
	</div>
</body>
</html>