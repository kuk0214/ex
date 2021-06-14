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
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<style type="text/css">
	label.ft14 {
		line-height: 200%;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var op1 = $('option').eq(0).val();
		var op2 = $('option').eq(1).val();
		var op3 = $('option').eq(2).val();
		if(op1 == '${DATA.category}') {
			$('option').eq(0).prop('selected', true);
		} else if(op2 == '${DATA.category}') {
			$('option').eq(1).prop('selected', true);
		} else {
			$('option').eq(2).prop('selected', true);
		}
		$('#cbtn').click(function() {
			$('#frm').attr('action', '/study/reviewboard/reviewBoardDetail.mentor');
			$('#frm').submit();
		});
		
		$('#ebtn').click(function() {
			var ttitle = $('#title').val().trim();
			var tbody = $('#body').val().trim();
			var tcategory = $('option:selected').val();
			
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
			
			if(tcategory == '${DATA.category}') {
				$('select').prop('disabled', true);
			}
			
			$('#frm').submit();			
		});
	});
</script>
</head>
<body>
	<div class="w3-content mxw700 w3-margin-top w3-padding">
		<form method="POST" action="/study/reviewboard/reviewBoardEditProc.mentor" name="frm" id="frm"
				class="w3-col w3-padding w3-margin-bottom w3-border w3-border-light-grey">
			<input type="hidden" name="nowPage" id="nowPage" value="${nowPage}">
			<input type="hidden" name="rvbno" id="rvbno" value="${DATA.rvbno}">
			<div class="w3-col pdr30">
				<select class="w3-col w150" name="category">
					<option value="그룹">그룹</option>
					<option value="업체">업체</option>
					<option value="멘토">멘토</option>
				</select>
			</div>
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
			<div class="w3-thrid w3-button w3-green" id="cbtn">취소</div>
			<div class="w3-thrid w3-button w3-right w3-blue" id="ebtn">수정완료</div>
		</div>
	</div>
</body>
</html>