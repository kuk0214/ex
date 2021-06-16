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
	
	$('.list').click(function() {
		var no = $(this).attr('id').substring(1);
		$.ajax({
			url: '/study/mentor/mentorPr.mentor',
			type: 'post',
			dataType: 'json',
			data: {
				no: no
			},
			success: function(obj) {
				$('#msgWin').css('display', 'block');
				$('#pr').html(obj.pr);
				$('#name').html(obj.name + ' 멘토 자기소개');
				$('#mtid').val(obj.mtid);
				if('${SID}' == obj.mtid) {
					$('.mxw600').append('<div class="w3-thrid w3-button w3-right w3-blue" id="ebtn">수정하기</div>');
					$('.mxw600').append('<div class="w3-thrid w3-button w3-right w3-red" id="dbtn">삭제하기</div>');
				}
				if(obj.cnt == 1 ) {
					$('#sfrm').remove();
					$('#rqbtn').remove();
					$('.mxw600').append('<div class="w3-thrid w3-button w3-right w3-red" id="rcbtn">요청중</div>');
				}
			},
			error: function() {
				alert('####### 통신 에러 #######')
			}
		});
	});
	
	$(document).on('click', '#closeBtn', function() {
		$('#msgWin').css('display', 'none');
		$('#ebtn').remove();
		$('#dbtn').remove();
		$('#rcbtn').remove();
	});

	
	$('#rbtn').click(function() {
		$('#frm').attr('action', '/study/mentor/mentorWrite.mentor')
		$('#frm').submit();
	});
	
	$('.w3-button.pbtn').click(function(){
		var pno = $(this).html();
		
		if(pno == 'pre'){
			pno = '${PAGE.startPage - 1}';
		} else if(pno == 'next'){
			pno = '${PAGE.endPage + 1}';
		}
		
		$('#nowPage').val(pno);
		$('#frm').submit();
	});
	
	$(document).on('click', '#dbtn', function() {
		var tno = $('.list').attr('id').substring(1);
		$('#no').val(tno);
		$('#frm').attr('action', '/study/mentor/mentorDel.mentor');
		$('#frm').submit();
	});
	
	$(document).on('click', '#ebtn', function() {
		var tno = $('.list').attr('id').substring(1);
		$('#no').val(tno);
		$('#frm').attr('action', '/study/mentor/mentorEdit.mentor');
		$('#frm').submit();
	});
	
	$('#rqbtn').click(function() {
		var tno = $('#nowPage').val();
		$('#sNowPage').val(tno);
		$('#sfrm').submit();
	});
	
	$('#rqlbtn').click(function() {
		$('#RL').css('display', 'block');
	});
	
	$('#closeBtn2').click(function() {
		$('#RL').css('display', 'none');
	});
	
	$('.a').click(function() {
		var sno2 = $(this).attr('id');
		var name = $(this).prev().prev().attr('id');
		$('#sno2').val(sno2);
		$('#name').val(name);
		$('#frm').attr('action', '/study/mentor/requestAccept.mentor');
		$('#frm').submit();
	});
});
</script>
</head>
<body class="">
	<%@ include file="../include/grouplayout.jsp" %>
	<form method="POST" action="/study/mentor/mentorList.mentor" id="frm" name="frm">
		<input type="hidden" name="nowPage" id="nowPage" value="${PAGE.nowPage}">
		<input type="hidden" name="sno2" id="sno2">
		<input type="hidden" name="name" id="name">
	</form>
	<section class="w3-content w3-margin-top">
		<div class="w3-col w3-margin-top w3-margin-bottom">
			<h2 class="w3-padding mgb10 ft24">멘토 리스트</h2>
		</div>
		<form method="POST" action="/study/mentor/mentorList.mentor">
			<button type="submit" class="w3-col w3-button w70 h20 mgl10 w3-right pd0 w3-green">검색</button>
			<input type="text" class="w3-col mgl10 w120 h20 w3-right" name="keyword" value="${keyword}">
			<select class="w3-col w70 h20 w3-right" name="option">	
				<option value="subject"<c:if test="${option eq 'subject'}">selected</c:if>>과목</option>
				<option value="title"<c:if test="${option eq 'title'}">selected</c:if>>제목</option>
				<option value="mtid"<c:if test="${option eq 'mtid'}">selected</c:if>>멘토id</option>
			</select>
		</form>
		
		<div class="w3-col w3-margin-top">
			<div class="w3-col w3-margin-top w3-border-bottom bgc bdt h40">
				<span class="w3-col w250 mgt5 w3-center w3-border-right">과목</span>
				<span class="w3-col w550 mgt5 w3-center w3-border-right">제목</span>
				<span class="w3-col w100 mgt5 w3-center w3-border-right">멘토id</span>
				<div class="w-rest mgt5 w3-center">등록일</div>
			</div>
			
<c:forEach var="data" items="${LIST}">
	<fmt:formatDate var="nowDate" type="date" value="${data.sysdate}" pattern="yyyy.MM.dd"/>
	<fmt:formatDate var="wDate" type="date" value="${data.wdate}" pattern="yyyy.MM.dd"/>
			<div class="w3-col  w3-border-bottom w3-hover-lime list" id="l${data.no}">
				<span class="w3-col w250 w3-center">${data.subject}</span>
				<span class="w3-col w550 pdl30">${data.title}</span>
				<span class="w3-col w100 w3-center">${data.mtid}</span>
		<c:if test="${nowDate eq wDate}">
				<div class="w3-rest w3-center">${data.sdate2}</div>
		</c:if>
		<c:if test="${nowDate ne wDate}">
				<div class="w3-rest w3-center">${data.sdate1}</div>
		</c:if>
			</div>
</c:forEach>
		<c:if test="${empty LIST}">
			<div class="w3-col w3-padding">
				<h2 class="w3-text-grey w3-center">등록된 멘토가 없습니다!</h2>
			</div>
		</c:if>
		</div>
		
		<!-- 페이징 처리 -->
		<div class="w3-center w3-margin-bottom">
			<div class="w3-bar w3-border w3-round w3-margin-top">
	<c:if test="${PAGE.startPage == 1}">
				<span class="w3-bar-item w3-grey">pre</span>
	</c:if>
	<c:if test="${PAGE.startPage != 1}">
				<span class="w3-bar-item w3-button w3-hover-lime pbtn">pre</span>
	</c:if>
<c:forEach var="page" begin="${PAGE.startPage}" end="${PAGE.endPage}">
	<c:if test="${PAGE.nowPage == page}">
				<span class="w3-bar-item w3-button w3-hover-lime w3-green pbtn">${page}</span>
	</c:if>
	<c:if test="${PAGE.nowPage != page}">
				<span class="w3-bar-item w3-button w3-hover-lime pbtn">${page}</span>
	</c:if>
</c:forEach>
	<c:if test="${PAGE.endPage == PAGE.totalPage}">
				<span class="w3-bar-item w3-grey">next</span>
	</c:if>
	<c:if test="${PAGE.endPage != PAGE.totalPage}">
				<span class="w3-bar-item w3-button w3-hover-lime pbtn">next</span>
	</c:if>
			</div>
		<c:if test="${CNT == 1}">
			<div class="w3-thrid w3-button w3-left w3-margin-top w3-green" id="rqlbtn">가입 요청 리스트</div>
			<div class="w3-button w3-right w3-margin-top w3-border w3-border-lime" id="rbtn">멘토 등록</div>
		</c:if>
		</div>
	</section>
	
	
	<div id="msgWin" class="w3-modal">
		<div class="w3-modal-content mxw600 w3-card-4">
			<header class="w3-container w3-green">
				<span class="w3-button w3-display-topright" id="closeBtn">&times;</span>
				<h2 class="w3-padding" id="name"></h2>
			</header>
			<div class="w3-container w3-margin-bottom">
				<h3 class="w3-padding" id="pr"></h3>
			</div>
	<c:if test="${CNT2 == 1}">
			<form method="POST" action="/study/mentor/requestMentor.mentor" id="sfrm">
				<input type="hidden" name="nowPage" id="sNowPage">
				<input type="hidden" name="sid" value="${SID}">
				<input type="hidden" name="mtid" id="mtid">
				<select class="w3-col w150" name="sno">
					<option>요청 할 스터디 선택</option>
		<c:forEach var="data" items="${LIST2}">
					<option value="${data.sno}">${data.sname}</option>
		</c:forEach>
				</select>
			</form>
			<div class="w3-thrid w3-button w3-right w3-blue" id="rqbtn">요청하기</div>
	</c:if>
		</div>
	</div>
	<div id="RL" class="w3-modal">
		<div class="w3-modal-content mxw700 w3-card-4">
			<header class="w3-container w3-green">
				<span class="w3-button w3-display-topright" id="closeBtn2">&times;</span>
				<h2 class="w3-padding">멘토 가입 요청 리스트</h2>
			</header>
			<div class="w3-container w3-margin-bottom">
				<div class="w3-col w3-margin-top w3-border-bottom bgc bdt h40">
					<span class="w3-col w150 mgt5 w3-center w3-border-right">그룹이름</span>
					<span class="w3-col w150 mgt5 w3-center w3-border-right">지역</span>
					<span class="w3-col w150 mgt5 w3-center w3-border-right">그룹장</span>
				</div>
	<c:forEach var="data" items="${LIST3}">
				<div class="w3-col w3-border-bottom">
					<span class="w3-col w150 w3-center pdt10">${data.sname2}</span>
					<span class="w3-col w150 w3-center pdt10">${data.loc}</span>
					<span class="w3-col w150 w3-center pdt10" id="${data.name}">${data.sid}</span>
					<div class="w3-right w3-button w3-red w100 h40 d" id="${data.sno2}">거절</div>			
					<div class="w3-right w3-button w3-blue w100 h40 a" id="${data.sno2}">수락</div>
				</div>
	</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>