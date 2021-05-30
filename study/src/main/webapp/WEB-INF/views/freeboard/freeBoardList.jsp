<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/w3-colors-flat.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<script type="text/javascript" src="/study/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/study/js/w3color.js"></script>
<style type="text/css">
	span.h25 {
		line-height: 50%;
	}
	img {
		height: 20px;
		width: auto;
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	// 게시글 상세보기 이벤트 처리
	$('.list').click(function() {
		// 선택된 태그의 아이디값에서 두번째 자리에서부터 마지막까지 잘라서 꺼내보자.
		// 예 ]		id="l100001"	==> 100001
		var tno = $(this).attr('id').substring(1);
		// 추출한 글번호를 폼태그의 입력태그에 셋팅해주고
		$('#frbno').val(tno);
		
		// 폼태그가 전송될 주소를 셋팅하고
		$('#frm').attr('action', '/study/freeboard/freeBoardDetail.mentor');
		
		// 폼태그를 전송한다.
		$('#frm').submit();
	});
	
	$('#wbtn').click(function() {
		$('#frm').attr('action', '/study/freeboard/freeBoardWrite.mentor')
		$('#frm').submit();
	});
	
	$('#jbtn').click(function() {
		$(location).attr('href', '/study/join.mentor');
	});
	
	$('#lbtn').click(function() {
		$(location).attr('href', '/study/login.mentor');
	});
	
	$('#outbtn').click(function() {
		$(location).attr('href', '/study/logout.mentor');
	});
	
	$('#hbtn').click(function() {
		$(location).attr('href', '/study/main.mentor');
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
});
</script>
</head>
<body  class="w3-content" style="max-width:1200px">
	<%@ include file="../include/layout.jsp" %>
	<form method="POST" action="/study/freeboard/freeBoardList.mentor" id="frm" name="frm">
		<input type="hidden" name="nowPage" id="nowPage" value="${PAGE.nowPage}">
		<input type="hidden" name="frbno" id="frbno">
	</form>
	
	<section class="w3-content w3-margin-top">
		<div class="w3-col w3-margin-top w3-margin-bottom">
			<h2 class="w3-padding mgb10 ft24">자유게시판</h2>
		</div>
		<div class="w3-right w500">
			<div class="w3-col w3-button w70 h20 mgl10 w3-right pd0 w3-green">검색</div>
			<input type="text" class="w3-col mgl10 w120 h20 w3-right" id="search" name="search">
			<select class="w3-col w70 h20 w3-right">
				<option>제목</option>
				<option>내용</option>
				<option>작성자</option>
			</select>
		</div>
		
		<div class="w3-col w3-margin-top datafr">
			<div class="w3-col w3-margin-top w3-border-bottom bgc bdt h40">
				<span class="w3-col w650 mgt5 w3-center w3-border-right">제목</span>
				<span class="w3-col w120 mgt5 w3-center w3-border-right">작성자</span>
				<span class="w3-col w100 mgt5 w3-center w3-border-right">조회수</span>
				<div class="w-rest mgt5 w3-center">작성일</div>
			</div>
			
			<!-- 글 리스트 -->
<c:forEach var="data" items="${LIST}">
			<div class="w3-col  w3-border-bottom w3-hover-lime list" id="l${data.frbno}">
				<span class="w3-col w650 pdl30">
					${data.title}
			<c:if test="${data.cnt != 0}">
					<i class='far fa-comment-alt'></i> ${data.cnt}
			</c:if> 
				</span>
				<span class="w3-col w120 w3-center ">${data.wid}</span>
				<span class="w3-col w100 w3-center ">${data.click}</span>
				<div class="w3-rest w3-center">${data.sdate1}</div>
			</div>
</c:forEach>
		<c:if test="${empty LIST}">
			<div class="w3-col w3-padding">
				<h2 class="w3-text-grey w3-center">입력된 데이터가 없습니다!</h2>
			</div>
		</c:if>
		</div>
		
		<!-- 페이징 처리 -->
		<div class="w3-center w3-margin-bottom">
			<div class="w3-bar w3-border w3-round w3-margin-top">
	<c:if test="${PAGE.startPage == 1}">

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

	</c:if>
	<c:if test="${PAGE.endPage != PAGE.totalPage}">
				<span class="w3-bar-item w3-button w3-hover-lime pbtn">next</span>
	</c:if>
			</div>
		<div class="w3-button w3-right w3-margin-top w3-border w3-border-lime" id="wbtn">글쓰기</div>
		</div>
		
	</section>
</body>
</html>