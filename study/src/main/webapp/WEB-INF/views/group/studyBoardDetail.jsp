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
			$('#pfrm').attr('action', '/study/group/studyBoardEdit.mentor');
			$('#pfrm').submit();
		});
		
		$('#dbtn').click(function() {
			$('#pfrm').attr('action', '/study/group/studyBoardDel.mentor');
			$('#pfrm').submit();
		});
		
		$('#jbtn').click(function() {
			if(${DATA.nowcnt} == ${DATA.maxcnt}) {
				alert('인원이 꽉 찾습니다.');
				return
			}
			$('#pfrm').attr('action', '/study/group/groupRequestJoin.mentor');
			$('#pfrm').submit();
		});
		
		$('#jcbtn').click(function() {
			$('#pfrm').attr('action', '/study/group/requestJoinCancle.mentor');
			$('#pfrm').submit();
		});
	});
</script>
</head>
<body>
	<form method="POST" action="/study/group/studyBoard.mentor" id="pfrm" name="pfrm">
		<input type="hidden" name="nowPage" id="nowPage" value="${nowPage}">
		<input type="hidden" name="sno" id="sno" value="${DATA.sno}">
		<input type="hidden" name="sbno" id="sbno" value="${DATA.sbno}">
		<input type="hidden" name="sid" id="sid" value="${SID}">
		<input type="hidden" name="id" id="id" value="${DATA.id}">
		<input type="hidden" name="nowcnt" value="${DATA.nowcnt}">
		<input type="hidden" name="maxcnt" value="${DATA.maxcnt}">
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
					<span class="w3-col w100 ft10 w3-border-right">${DATA.id}</span>
					<span class="w3-col w100 ft10">${DATA.nowcnt} / ${DATA.maxcnt}</span>
					<span class="w3-col w50 ft10"><i class='far fa-eye'></i> ${DATA.click}</span>
					<span class="w3-col w150 ft10 w3-right">${DATA.sdate}</span>
				</div>
			</div>
			<div class="w3-col w3-margin-top pdb10 w3-border-bottom w3-border-light-grey">
				<div class="w3-rest">
					<span class="w3-col ft10">스터디 그룹 이름 : ${DATA.sname}</span>
				</div>
			</div>
			<div class="w3-col w3-margin-top pdAll10 w3-border w3-border-light-grey">
				<div class="w3-rest">
					<span class="w3-col ft12">${DATA.body}</span>
				</div>
			</div>
		</div>
		<div class="w3-col w3-margin-top">
			<div class="w3-thrid w3-button w3-green" id="lbtn">목록보기</div>
	<c:if test="${DATA.id eq SID}">
			<div class="w3-thrid w3-button w3-right w3-blue" id="ebtn">수정하기</div>
			<div class="w3-thrid w3-button w3-right w3-red" id="dbtn">삭제하기</div>
	</c:if>		
	<c:if test="${DATA.id ne SID}">
		<c:if test="${CNT == 0}">
			<div class="w3-thrid w3-button w3-right w3-blue" id="jbtn">가입요청</div>
		</c:if>
		<c:if test="${CNT == 1}">
			<div class="w3-thrid w3-button w3-right w3-red" id="jcbtn">요청취소</div>
		</c:if>
	</c:if>
		</div>
	</div>
</body>
</html>