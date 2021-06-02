<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/user.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<script type="text/javascript" src="/study/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/study/js/w3color.js"></script>

<style type="text/css">
	label.ft14 {
		line-height: 200%;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#lbtn').click(function() {
			$('#pfrm').submit();
		});
		
		$('#ebtn').click(function() {
			$('#pfrm').attr('action', '/study/notice/noticeEdit.mentor');
			$('#pfrm').submit();
		});
		
		$('#dbtn').click(function() {
			$('#pfrm').attr('action', '/study/notice/noticeDel.mentor');
			$('#pfrm').submit();
		});
	});
</script>
</head>
<body>
	<form method="POST" action="/study/notice/noticeList.mentor" id="pfrm" name="pfrm">
		<input type="hidden" name="nowPage" id="nowPage" value="${nowPage}">
		<input type="hidden" name="no" id="no" value="${DATA.no}">
	</form>
	<div class="w3-content mxw700 w3-margin-top w3-padding">
		<div class="w3-col w3-padding w3-margin-bottom w3-border w3-border-light-grey">
			<div class="w3-col w3-margin-top pdb10 w3-border-bottom w3-border-light-grey">
				<div class="w3-rest pdr30">
					<span class="w3-rest ft14">${DATA.title}</span>
				</div>
			</div>
			<div class="w3-col w3-margin-top pdb10 w3-border-bottom w3-border-light-grey">
				<div class="w3-rest">
					<span class="w3-col w100 ft10">${DATA.wid}</span>
					<span class="w3-col w50 ft10"><i class='far fa-eye'></i> ${DATA.click}</span>
					<span class="w3-col w150 ft10 w3-right">${DATA.sdate}</span>
				</div>
			</div>
			<div class="w3-col w3-margin-top pdAll10 w3-border w3-border-light-grey">
				<div class="w3-rest ">
					<span class="w3-col ft12">${DATA.body}</span>
				</div>
			</div>
		</div>
		<div class="w3-col w3-margin-top">
			<div class="w3-thrid w3-button w3-green" id="lbtn">목록보기</div>
	<c:if test="${SID eq 'admin1'}">
			<div class="w3-thrid w3-button w3-right w3-blue" id="ebtn">수정하기</div>
			<div class="w3-thrid w3-button w3-right w3-red" id="dbtn">삭제하기</div>
	</c:if>
		</div>
	</div>
</body>
</html>