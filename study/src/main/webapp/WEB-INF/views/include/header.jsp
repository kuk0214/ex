<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/study/css/layout.css" rel="stylesheet" type="text/css" media="all">

</head>
<body>
	<div class="wrapper row0">
		<div id="topbar" class="hoc clear">
			<div id="logo" class="fl_left">
			  <a href="/study/main.man"><h1>Study</h1></a>
			</div>
			<div class="fl_right">
				<ul class="nospace">
					<li><a href="#"><i class="fa fa-lg fa-home"></i></a></li>
					<li><a href="/study/member/login.man">로그인</a></li>
					<li><a href="#">가입하기</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="wrapper row1">
		<header id="header" class="hoc clear"> 
			<nav id="mainav" class="fl_right">
				<ul class="clear">
					<li class="active"><a href="/study/main.man">Home</a></li>
					<li><a class="drop" href="#">스터디</a>
						<ul>
							<li><a href="pages/gallery.html">스터디 관리</a></li>
							<li><a href="pages/full-width.html">스터디 모집</a></li>
						</ul>
					</li>
					<li><a class="drop" href="#">게시판</a>
						<ul>
							<li><a href="/study/freeboard/freeBoardList.man">자유 게시판</a></li>
							<li><a href="#">질문&답변 게시판</a></li>
							<li><a href="#">후기 게시판</a></li>
						</ul>
					</li>
					<li><a class="drop" href="#">마이페이지</a>
						<ul>
							<li><a href="/study/member/userInfo.man">내 정보 보기</a></li>
							<li><a href="#">내 정보 수정</a></li>
							<li><a href="#">1:1 문의하기</a></li>
							<li><a href="#">회원 탈퇴</a></li>
						</ul>
					</li>
					<li><a class="drop" href="#">고객센터</a></li>
				</ul>
			</nav>
		</header>
	</div>
</body>
</html>