<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/study/css/layout.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="wrapper row0">
		<div id="topbar" class="hoc clear">
			<div id="logo" class="fl_left">
			  <a href="/study/main.mentor"><h1>Study</h1></a>
			</div>
			<div class="fl_right">
				<ul class="nospace">
					<li><a href="/study/main.mentor"><i class="fa fa-lg fa-home"></i></a></li>
				<c:if test="${empty SID}">
					<li><a href="/study/member/login.mentor">로그인</a></li>
				</c:if>
				<c:if test="${not empty SID}">
					<li><a href="/study/member/logout.mentor">로그아웃</a></li>				
				</c:if>
					<li><a href="#">가입하기</a></li>
				<c:if test="${SID eq 'admin1'}">
					<li><a href="/study/db/DBSetData.mentor">더미데이터 생성</a></li>
				</c:if>
				</ul>
			</div>
		</div>
	</div>

	<div class="wrapper row1">
		<header id="header" class="hoc clear"> 
			<nav id="mainav" class="fl_right">
				<ul class="clear">
					<li class="active"><a href="/study/main.mentor">Home</a></li>
					<li><a class="drop" href="#">스터디</a>
						<ul>
							<li><a href="/study/group/addGroup.mentor">스터디 만들기</a></li>
							<li><a href="/study/group/myGroup.mentor">내 스터디 관리</a></li>
							<li><a href="/study/group/studyBoard.mentor">스터디원 모집</a></li>
							<li><a href="/study/mentor/mentorList.mentor">멘토 리스트</a></li>
						</ul>
					</li>
					<li><a class="drop" href="/study/freeboard/freeBoardList.mentor">게시판</a>
						<ul>
							<li><a href="/study/freeboard/freeBoardList.mentor">자유 게시판</a></li>
							<li><a href="#">질문&답변 게시판</a></li>
							<li><a href="/study/reviewboard/reviewBoardList.mentor">후기 게시판</a></li>
						</ul>
					</li>
					<li><a class="drop" href="#">마이페이지</a>
						<ul>
							<li><a href="/study/member/userInfo.mentor">내 정보 보기</a></li>
							<li><a href="#">내 정보 수정</a></li>
							<li><a href="#">내 문의보기</a></li>
							<li><a href="#">회원 탈퇴</a></li>
						</ul>
					</li>
					<li><a class="drop" href="#">고객센터</a>
						<ul>
							<li><a href="/study/notice/noticeList.mentor">공지사항</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</header>
	</div>
	<div>
		<img src="/study/img/배경.jpg">
	</div>
</body>
</html>