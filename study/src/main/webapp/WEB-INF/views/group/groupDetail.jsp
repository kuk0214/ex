<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/layout.css">
<script type="text/javascript" src="/study/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/study/js/w3color.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#lbtn').click(function() {
			$('#msgWin').css('display', 'block');		
		});
		
		$('#closeBtn').click(function() {
			$('#msgWin').css('display', 'none');
		});
		$('.pd0').click(function() {
			var tid = $(this).attr('id');
			if(confirm('추방 하시겠습니까?')) {
				$('#id').val(tid);
				$('#frm').attr('action', '/study/group/expelGroupMember.mentor');
				$('#frm').submit();			
			}
		});
		
		$('#obtn').click(function() {
			$('#frm').attr('action', '/study/group/groupMemberOut.mentor');
			$('#frm').submit();
		});
		
		$('#ebtn').click(function() {
			$('#frm').submit();
		});
		
		$('#dbtn').click(function() {
			var tsname = '${DATA.sname}';
			if(${DATA.nowcnt} != 1) {
				alert('### 그룹장만 있어야 해체가 가능합니다. ###');
				return
			}
			
			if(confirm('정말로 ' + tsname + ' 그룹을 해체하시겠습니까?')) {
				$('#frm').attr('action', '/study/group/groupDel.mentor');
				$('#frm').submit();
			}
		});
	});
</script>
</head>
<body>
	<%@ include file="../include/grouplayout.jsp" %>
	<form method="POST" action="/study/group/groupEdit.mentor" name="frm" id="frm">
		<input type="hidden" name="id" id="id" value="${SID}">
		<input type="hidden" name="sno" value="${DATA.sno}">
	</form>
	<section class="w3-content w3-margin-top">
		<div class="w3-col w3-margin-top w3-margin-bottom">
			<h2 class="w3-padding mgb10 ft24">${DATA.sname}</h2>
		</div>
		
		<div class="w3-col mxw802 w3-border w3-margin-top">
			<div class="w3-col w3-border-bottom h40">
				<div class="w3-col w200 w3-center w3-border-right w3-border-bottom bgc ft14 h40 pdt10">스터디 이름</div>
				<div class="w3-col w200 w3-center w3-border-right ft14 h40 pdt10">${DATA.sname}</div>
				<div class="w3-col w200 w3-center w3-border-right w3-border-bottom bgc ft14 h40 pdt10">지역</div>
				<div class="w3-col w200 w3-center ft14 h40 pdt10">${DATA.loc}</div>
			</div>
			<div class="w3-col w3-border-bottom h40">
				<div class="w3-col w200 w3-center w3-border-right w3-border-bottom bgc ft14 h40 pdt10">그룹장</div>
				<div class="w3-col w200 w3-center w3-border-right ft14 h40 pdt10">${DATA.id}</div>
				<div class="w3-col w200 w3-center w3-border-right w3-border-bottom bgc ft14 h40 pdt10">생성일</div>
				<div class="w3-col w200 w3-center ft14 h40 pdt10">${DATA.sdate3}</div>
			</div>
			<div class="w3-col h40">
				<div class="w3-col w200 w3-center w3-border-right  bgc ft14 h40 pdt10">현재 인원수</div>
				<div class="w3-col w200 w3-center w3-border-right ft14 h40 pdt10">${DATA.nowcnt}</div>
				<div class="w3-col w200 w3-center w3-border-right bgc ft14 h40 pdt10">최대 인원수</div>
				<div class="w3-col w200 w3-center ft14 h40 pdt10">${DATA.maxcnt}</div>
			</div>
		</div>
		<div class="w3-col w3-margin-top">
			<div class="w3-thrid w3-button w3-green" id="lbtn">스터디 그룹원 리스트</div>
		<c:if test="${DATA.id eq SID}">
			<div class="w3-thrid w3-button w3-blue" id="ebtn">수정하기</div>
			<div class="w3-thrid w3-button w3-red" id="dbtn">해체하기</div>
		</c:if>
		<c:if test="${DATA.id ne SID}">
			<div class="w3-thrid w3-button w3-red" id="obtn">탈퇴하기</div>
		</c:if>
		</div>
		<div id="msgWin" class="w3-modal">
			<div class="w3-modal-content w450 w3-card-4">
				<header class="w3-container w3-green">
					<span class="w3-button w3-display-topright" id="closeBtn">&times;</span>
					<h2 class="w3-padding">스터디 그룹원 리스트</h2>
				</header>
				<div class="w3-center w3-margin-bottom">
		<c:forEach var="data" items="${LIST}">
					<div class="w3-margin-top w3-margin-bottom">
				<c:if test="${DATA.id ne data.id}">
						<span class="pdt20 ft16">${data.id}</span>
					<c:if test="${DATA.id eq SID}">
						<span class="w3-button mgb10 pd0" id="${data.id}">&times;</span>	
					</c:if>
				</c:if>
					</div>
		</c:forEach>					
				</div>
		<c:if test="${empty LIST}">
				<div class="w3-center w3-margin-bottom">
					<h1>그룹원이 없습니다</h1>
				</div>
		</c:if>
			</div>
		</div>
	</section>	
</body>
</html>