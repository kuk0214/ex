<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/layout.css">
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
			$(location).attr('href', '/study/freeboard/freeBoardList.mentor');
		});
		
		$('#sbtn').click(function() {
			var tbody = $('#body').val().trim();
			var tfrbno = $('#frbno').val();
			var wid = '${SID}';
			if(!tbody) {
				alert('내용이 없습니다');
				return;
			}
			$.ajax({
				url: '/study/freeboard/freeBoardReplyProc.mentor',
				type: 'post',
				dataType: 'json',
				data: {
					body: tbody,
					frbno: tfrbno,
					wid: wid
				},
				success: function(obj) {
					if(obj.result == 'OK') {
						document.location.reload();
					} else {
						alert('*** 댓글 달기에 실패했습니다! ***');
					}
				},
				error: function() {
					alert('####### 통신 에러 #######')
				}
			});
		});
		
		$(document).on("click", ".sbtn", function() {
			$('.sbody').attr('id', 'body').attr('name', 'body');
			var tbody = $('#body').val().trim();
			var tfrbno = $('#frbno').val();
			var id = $(this).parent().parent().attr('id').substring(2);
			var tupid = $('#i' + id).html();
			var tgno = $('#g' + id).html();
			var wid = '${SID}';
			
			if(!tbody) {
				alert('내용이 없습니다');
				return;
			}
			$.ajax({
				url: '/study/freeboard/freeBoardReplyProc2.mentor',
				type: 'post',
				dataType: 'json',
				data: {
					body: tbody,
					frbno: tfrbno,
					upid: tupid,
					groupno: tgno,
					wid: wid
				},
				success: function(obj) {
					if(obj.result == 'OK') {
						document.location.reload();
					} else {
						alert('*** 댓글 달기에 실패했습니다! ***');
					}
				},
				error: function() {
					alert('####### 통신 에러 #######')
				}
			});
		});
		
		$(document).on("click", ".ebtn", function() {
			$('.ebody').attr('id', 'body').attr('name', 'body');
			var tbody = $('#body').val().trim();
			var tfrreno = $(this).parent().prev().prev().attr('id').substring(1);
			if(!tbody) {
				alert('내용이 없습니다');
				return;
			}
			$.ajax({
				url: '/study/freeboard/freeBoardReplyEditProc.mentor',
				type: 'post',
				dataType: 'json',
				data: {
					body: tbody,
					frreno: tfrreno
				},
				success: function(obj) {
					if(obj.result == 'OK') {
						document.location.reload();
					} else {
						alert('*** 댓글 수정에 실패했습니다! ***');
					}
				},
				error: function() {
					alert('####### 통신 에러 #######')
				}
			});
		});
		
		$(document).on('click', '.del', function() {
			var tfrreno = $(this).attr('id').substring(1);
			
			$.ajax({
				url: '/study/freeboard/freeBoardReplyDelProc.mentor',
				type: 'post',
				dataType: 'json',
				data: {
					frreno: tfrreno
				},
				success: function(obj) {
					if(obj.result == 'OK') {
						document.location.reload();
					} else {
						alert('*** 댓글 삭제에 실패했습니다! ***');
					}
				},
				error: function() {
					alert('####### 통신 에러 #######')
				}
			});
		});
		
		$(document).on('click', '.reply', function() {
			var id = $(this).attr('id').substring(1);
			$('#r' + id).remove();
			$('#e' + id).remove();
			$('#d' + id).remove();
			$('#in' + id).append('<span class="w3-col m3 w3-button w3-text-orange rclose" id="c' + id + '">답글창 닫기</span>' +
								   '<form method="POST" action="/study/freeboard/freeBoardReplyProc" class="w3-col w3margin-top" id="frm" name="frm">' +
								   '<textarea rows="7" class="w3-col pdAll10 vresize sbody" placeholder="댓글을 입력하세요"></textarea>' +
								   '</form>' +
								   '<div class="w3-col w3-margin-top w3-margin-bottom">' +
								   '<div class="w3-col m3 w3-button w3-right w3-blue sbtn">댓글 달기</div>' +
								   '</div>');
			$('.replysubmit').css('display', 'none');
		});

		$(document).on('click', '.rclose', function() {
			var id = $(this).attr('id').substring(1);
			var sid = '${SID}';
			var wid = $('#i' + id).html();
			$('#in' + id).children().remove();
			$('#reply' + id).append('<div class="w3-col w50 w3-button reply" id="r' + id + '">답글</div>');
			if(sid == wid) {
				$('#reply' + id).append('<div class="w3-col w50 w3-button edit" id="e' + id + '">수정</div>');
				$('#reply' + id).append('<div class="w3-col w50 w3-button del" id="d' + id + '">삭제</div>');				
			}

			$('.replysubmit').css('display', 'block');
		});
		
		$(document).on('click', '.edit', function() {
			var id = $(this).attr('id').substring(1);
			var tbody = $('#b' + id).html();
			$('#r' + id).remove();
			$('#e' + id).remove();
			$('#d' + id).remove();
			$('#in' + id).append('<span class="w3-col m3 w3-button w3-text-orange eclose" id="c' + id + '">수정창 닫기</span>' +
									'<form method="POST" action="/study/freeboard/freeBoardReplyProc" class="w3-col w3margin-top" id="frm" name="frm">' +
									'<textarea rows="7" class="w3-col pdAll10 vresize ebody">'+ tbody +'</textarea>' +
					   				'</form>' +
					   				'<div class="w3-col w3-margin-top w3-margin-bottom">' +
					   				'<div class="w3-col m3 w3-button w3-right w3-blue ebtn">수정 완료</div>' +
					   				'</div>');		
		});
		
		$(document).on('click', '.eclose', function() {
			var id = $(this).attr('id').substring(1);
			var sid = '${SID}';
			var wid = $('#i' + id).html();
			$('#in' + id).children().remove();
			$('#reply' + id).append('<div class="w3-col w50 w3-button reply" id="r' + id + '">답글</div>');
			if(sid == wid) {
				$('#reply' + id).append('<div class="w3-col w50 w3-button edit" id="e' + id + '">수정</div>');
				$('#reply' + id).append('<div class="w3-col w50 w3-button del" id="d' + id + '">삭제</div>');				
			}

			$('.replysubmit').css('display', 'block');
		});
		
		$('.w3-button.pbtn').click(function(){
			var pno = $(this).html();
			
			if(pno == 'pre'){
				pno = '${PAGE.startPage - 1}';
			} else if(pno == 'next'){
				pno = '${PAGE.endPage + 1}';
			}
			
			$('#nowPage').val(pno);
			$('#pfrm').submit();
		});
		
		$('#dbtn').click(function() {
			$('#pfrm').attr('action', '/study/freeboard/freeBoardDel.mentor');
			$('#pfrm').submit();
		});
		
		$('#ebtn').click(function() {
			$('#pfrm').attr('action', '/study/freeboard/freeBoardEdit.mentor');
			$('#pfrm').submit();
		});	
	});
</script>
</head>
<body>
	<form method="POST" action="/study/freeboard/freeBoardDetail.mentor" id="pfrm" name="pfrm">
		<input type="hidden" name="nowPage" id="nowPage" value="${nowPage}">
		<input type="hidden" name="frbno" id="frbno" value="${DATA.frbno}">
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
					<span class="w3-col w50 ft10"><i class='far fa-comment-alt'></i> ${CNT}</span>
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
		<div class="w3-col w3-center w3-margin-top">
	<c:if test="${SID eq DATA.wid}">
			<div class="w3-col m3 w3-button w3-right w3-blue" id="ebtn">수정하기</div> 
			<div class="w3-col m3 w3-margin-right w3-button w3-right w3-red" id="dbtn">삭제하기</div> 
	</c:if>
		</div>
		<div class="w3-col w3-margin-top w3-border w33-border-light-grey pdAll20"> 댓글 : ${CNT} 개
<c:forEach var="data" items="${LIST}">
			<div class="w3-col" style="padding-left: ${(data.upid ne null) ? 70 : 0}px;">
				<div class="w3-col w3-padding w3-margin-bottom w3-border w3-border-light-grey">
					<div class="w3-col w3-margin-top pdb10">
						<div class="w3-rest">
							<span class="nodisplay" id="g${data.frreno}">${data.groupno}</span>
							<span class="w3-col w100 ft12" id="i${data.frreno}">${data.wid}</span>
							<span class="w3-col w150 ft12 w3-right">${data.sdate}</span>
						</div>
					</div>
				<c:if test="${data.upid ne null}">
					<div class="w3-col">
						<div class="w3-rest ">
							<span class="w3-col w3-text-orange ft12">@ ${data.upid}</span>
						</div>
					</div>
				</c:if>
					<div class="w3-col w3-margin-top w3-margin-bottom">
						<div class="w3-rest ">
							<span class="w3-col w3-margin-bottom ft12" id="b${data.frreno}">${data.body}</span>
						</div>
						<div class="w3-rest w3-center" id="reply${data.frreno}">
				<c:if test="${not empty SID}">
							<div class="w3-col w50 w3-button reply" id="r${data.frreno}">답글</div>
						<c:if test="${SID eq data.wid}">
							<div class="w3-col w50 w3-button edit" id="e${data.frreno}">수정</div>
							<div class="w3-col w50 w3-button del" id="d${data.frreno}">삭제</div>
						</c:if>
				</c:if>
						</div>
						<div class="w3-rest" id="in${data.frreno}">
						</div>
						
					</div>
				</div>
			</div>
</c:forEach>
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
		</div>
	<c:if test="${empty SID}">
			<div class="w3-col w3-margin-top w3-margin-bottom">
				<div class="w3-col m3 w3-button w3-blue" id="lbtn">목록 보기</div>
			</div>
	</c:if>
	<c:if test="${not empty SID}">
		<div class="replysubmit">
			<form method="POST" action="/study/freeboard/freeBoardReplyProc.mentor" class="w3-col w3-margin-top" id="frm" name="frm">
				<textarea rows="7" class="w3-col pdAll10 vresize" id="body" name="body" placeholder="댓글을 입력하세요"></textarea>
			</form>
			<div class="w3-col w3-margin-top w3-margin-bottom">
				<div class="w3-col m3 w3-button w3-green" id="lbtn">목록 보기</div>
				<div class="w3-col m3 w3-button w3-right w3-blue" id="sbtn">댓글 달기</div>
			</div>
		</div>
	</c:if>
	</div>
</body>
</html>