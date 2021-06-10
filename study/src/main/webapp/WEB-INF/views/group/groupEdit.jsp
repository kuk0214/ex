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
		alert('### 그룹이름, 지역, 최대인원수 변경이 가능합니다. ###')
		$('#ebtn').click(function() {
			var tsname = $('#sname').val();
			var tloc = $('#loc').val();
			var tmaxcnt = $('#maxcnt').val();
			var tnowcnt = $('#nowcnt').html();
			
			if(!(tsname && tloc && tmaxcnt)) {
				alert('### 비어있는 내용이 있습니다. ###');
				return
			}
			
			if(tnowcnt > tmaxcnt) {
				alert('### 현재 인원수보다 낮게 할 수 없습니다. ###');
				return
			}
			
			if(tsname == '${DATA.sname}') {
				$('#sname').prop('disabled', true);
			}
			
			if (tloc == '${DATA.loc}') {
				$('#loc').prop('disabled', true);
			}
			
			if (tmaxcnt == ${DATA.maxcnt}) {
				$('#maxcnt').prop('disabled', true);
			}
			
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<%@ include file="../include/grouplayout.jsp" %>
	<section class="w3-content w3-margin-top">
		<div class="w3-col w3-margin-top w3-margin-bottom">
			<h2 class="w3-padding mgb10 ft24">${DATA.sname} 수정</h2>
		</div>
		
		<form method="POST" action="/study/group/groupEditProc.mentor" id="frm" name="frm"
					class="w3-col mxw803 w3-border w3-margin-top">
			<input type="hidden" name="sno" value="${DATA.sno}">
			<div class="w3-col w3-border-bottom h40">
				<label for="sname" class="w3-col w200 w3-center w3-border-right w3-border-bottom bgc ft14 h40 pdt10">스터디 이름</label>
				<input type="text" class="w3-col w200 w3-center w3-border-right w3-border-bottom ft14 h40 bordernone" name="sname" id="sname" value="${DATA.sname}">
				<label for="loc" class="w3-col w200 w3-center w3-border-right w3-border-bottom bgc ft14 h40 pdt10">지역</label>
				<input type="text" class="w3-col w200 w3-center w3-border-bottom ft14 h40 bordernone" name="loc" id="loc" value="${DATA.loc}">
			</div>
			<div class="w3-col w3-border-bottom h40">
				<div class="w3-col w200 w3-center w3-border-right w3-border-bottom bgc ft14 h40 pdt10">그룹장</div>
				<div class="w3-col w200 w3-center w3-border-right w3-border-bottom ft14 h40 pdt10">${DATA.id}</div>
				<div class="w3-col w200 w3-center w3-border-right w3-border-bottom bgc ft14 h40 pdt10">생성일</div>
				<div class="w3-col w200 w3-center ft14 h40 pdt10">${DATA.sdate3}</div>
			</div>
			<div class="w3-col h40">
				<div class="w3-col w200 w3-center w3-border-right  bgc ft14 h40 pdt10">현재 인원수</div>
				<div class="w3-col w200 w3-center w3-border-right ft14 h40 pdt10" id="nowcnt">${DATA.nowcnt}</div>
				<label for="maxcnt" class="w3-col w200 w3-center w3-border-right bgc ft14 h40 pdt10">최대 인원수</label>
				<input type="text" class="w3-col w200 w3-center ft14 h40 bordernone" name="maxcnt" id="maxcnt" value="${DATA.maxcnt}">
			</div>
		</form>
		<div class="w3-col w3-margin-top">
			<div class="w3-thrid w3-button w3-red" id="cbtn">수정취소</div>
			<div class="w3-thrid w3-button w3-blue" id="ebtn">수정완료</div>
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