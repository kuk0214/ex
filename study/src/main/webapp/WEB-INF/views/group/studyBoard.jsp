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
		var sbno = $(this).attr('id').substring(1);
		var sno = $(this).children().eq(0).attr('id');
		// 추출한 글번호를 폼태그의 입력태그에 셋팅해주고
		$('#sbno').val(sbno);
		$('#sno').val(sno);
		// 폼태그가 전송될 주소를 셋팅하고
		$('#frm').attr('action', '/study/group/studyBoardDetail.mentor');
		
		// 폼태그를 전송한다.
		$('#frm').submit();
	});
	
	$('#wbtn').click(function() {
		$('#frm').attr('action', '/study/group/studyBoardWrite.mentor')
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
});
</script>
</head>
<body>
	<%@ include file="../include/grouplayout.jsp" %>
	<form method="POST" action="/study/group/studyBoard.mentor" id="frm" name="frm">
		<input type="hidden" name="nowPage" id="nowPage" value="${PAGE.nowPage}">
		<input type="hidden" name="sbno" id="sbno">
		<input type="hidden" name="sno" id="sno">
		<input type="hidden" name="sid" id="sid" value="${SID}">
	</form>
	<section class="w3-content w3-margin-top">
		<div class="w3-col w3-margin-top w3-margin-bottom">
			<h2 class="w3-padding mgb10 ft24">스터디원 모집</h2>
		</div>
		<form method="POST" action="/study/group/studyBoard.mentor" class="w3-right w500">
			<button type="submit" class="w3-col w3-button w70 h20 mgl10 w3-right pd0 w3-green">검색</button>
			<input type="text" class="w3-col mgl10 w120 h20 w3-right" name="keyword" value="${keyword}">
			<select class="w3-col w70 h20 w3-right" name="option">
				<option value="stitle"<c:if test="${option eq 'stitle'}">selected</c:if>>제목</option>
				<option value="sbody"<c:if test="${option eq 'sbody'}">selected</c:if>>내용</option>
				<option value="swid"<c:if test="${option eq 'swid'}">selected</c:if>>작성자</option>
			</select>
		</form>
		<div class="w3-col w3-margin-top">
			<div class="w3-col w3-margin-top w3-border-bottom bgc bdt h40">
				<span class="w3-col w450 mgt5 w3-center w3-border-right">제목</span>
				<span class="w3-col w100 mgt5 w3-center w3-border-right">작성자</span>
				<span class="w3-col w100 mgt5 w3-center w3-border-right">인원수</span>
				<span class="w3-col w100 mgt5 w3-center w3-border-right">지역</span>
				<span class="w3-col w100 mgt5 w3-center w3-border-right">조회수</span>
				<div class="w-rest mgt5 w3-center">작성일</div>
			</div>
			
			<!-- 글 리스트 -->
<c:forEach var="data" items="${LIST}">
	<fmt:formatDate var="nowDate" type="date" value="${data.sysdate}" pattern="yyyy.MM.dd"/>
	<fmt:formatDate var="wDate" type="date" value="${data.wdate}" pattern="yyyy.MM.dd"/>
			<div class="w3-col  w3-border-bottom w3-hover-lime list" id="l${data.sbno}">
				<span class="w3-col w450 pdl30" id="${data.sno}">${data.title}</span>
				<span class="w3-col w100 w3-center">${data.id}</span>
				<span class="w3-col w100 w3-center">${data.nowcnt} / ${data.maxcnt}</span>
				<span class="w3-col w100 w3-center">${data.loc}</span>
				<span class="w3-col w100 w3-center">${data.click}</span>
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
				<h2 class="w3-text-grey w3-center">입력된 데이터가 없습니다!</h2>
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
			<div class="w3-button w3-right w3-margin-top w3-border w3-border-lime" id="wbtn">글쓰기</div>
		</div>
	</section>
</body>
</html>