<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/w3-colors-flat.css">
<link rel="stylesheet" type="text/css" href="/study/css/layout.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<script type="text/javascript" src="/study/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/study/js/w3color.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
$(document).ready(function(){
	// 게시글 상세보기 이벤트 처리
	$('.list').click(function() {
		// 선택된 태그의 아이디값에서 두번째 자리에서부터 마지막까지 잘라서 꺼내보자.
		// 예 ]		id="l100001"	==> 100001
		var tno = $(this).attr('id').substring(1);
		// 추출한 글번호를 폼태그의 입력태그에 셋팅해주고
		$('#sno').val(tno);
		
		// 폼태그가 전송될 주소를 셋팅하고
		$('#frm').attr('action', '/study/group/groupDetail.mentor');
		
		// 폼태그를 전송한다.
		$('#frm').submit();
	});
	
	$('.pd0').click(function() {
		var tsno = $(this).attr('id');
		$('#sno').val(tsno);
		$('#frm').submit();
	});
});
</script>
</head>
<body class="">
	<%@ include file="../include/grouplayout.jsp" %>
	<form method="POST" action="/study/group/requestJoinList.mentor" id="frm" name="frm">
		<input type="hidden" name="sno" id="sno">
	</form>
	<section class="w3-content w3-margin-top w3-margin-left">
		<div class="w3-col w3-margin-top w3-margin-bottom">
			<h2 class="w3-padding mgb10 ft24">내 스터디 관리</h2>
		</div>
		<div class="w3-col w3-margin-top">
			<div class="w3-col w3-margin-top w3-border-bottom bgc bdt h40">
				<span class="w3-col w550 mgt5 w3-center w3-border-right">스터디 이름</span>
				<span class="w3-col w100 mgt5 w3-center w3-border-right">그룹장</span>
				<span class="w3-col w120 mgt5 w3-center w3-border-right">인원수</span>
				<span class="w3-col w100 mgt5 w3-center">생성일</span>
				<div class="w-rest mgt5 w3-center"></div>
			</div>
			
			<!-- 그룹 리스트 -->
<c:forEach var="data" items="${LIST}">
			<div class="w3-col  w3-border-bottom w3-hover-lime">
				<span class="w3-col w550 w3-center list" id="l${data.sno}">${data.sname}</span>
				<span class="w3-col w100 w3-center">${data.id}</span>
				<span class="w3-col w120 w3-center">${data.nowcnt} / ${data.maxcnt}</span>
				<span class="w3-col w100 w3-center">${data.sdate3}</span>
				<div class="w3-rest w3-center">
			<c:if test="${data.id eq SID}">
					<div class="w3-col w3-blue w3-button h20 pd0" id="${data.sno}">요청목록</div>
			</c:if>
				</div>
			</div>
</c:forEach>
		<c:if test="${empty LIST}">
			<div class="w3-col w3-padding">
				<h2 class="w3-text-grey w3-center">가입한 스터디가 없습니다!</h2>
			</div>
		</c:if>
		</div>
	</section>
</body>
</html>