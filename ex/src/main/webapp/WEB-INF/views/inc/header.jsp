<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/ex/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<title>상단부</title>
</head>
<body>
	<header id="header" class="clear header"> 
	    <div id="logo" class="fl_left">
	      <h1><a href="/ex/index.man">STUDY</a></h1>
	    </div>
	    <nav id="mainav" class="fl_right">
	      <ul class="clear">
	        <li class="active"><a href="/ex/index.man">Home</a></li>
	        <li><a class="drop" href="#">게시판</a>
	          <ul>
	            <li><a href="/ex/pages/gallery.html">자유 게시판</a></li>
	            <li><a href="/ex/pages/full-width.html">질문답변 게시판</a></li>
	            <li><a href="/ex/pages/sidebar-left.html">후기 게시판</a></li>
	            <li><a href="/ex/pages/sidebar-right.html">Sidebar Right</a></li>
	            <li><a href="/ex/pages/basic-grid.html">Basic Grid</a></li>
	          </ul>
	        </li>
	        <li><a class="drop" href="#">스터디</a>
	          <ul>
	            <li><a href="#">Level 2</a></li>
	            <li><a class="drop" href="#">Level 2 + Drop</a>
	              <ul>
	                <li><a href="#">Level 3</a></li>
	                <li><a href="#">Level 3</a></li>
	              </ul>
	            </li>
	          </ul>
	        </li>
	        <li><a href="#">Link Text</a></li>
	        <li><a href="#">Link Text</a></li>
	      </ul>
	    </nav>
	</header>
</body>
</html>