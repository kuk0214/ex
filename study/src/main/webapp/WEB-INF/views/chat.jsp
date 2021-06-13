<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>STUSY Main</title>
<link rel="stylesheet" type="text/css" href="/study/css/w3.css">
<link rel="stylesheet" type="text/css" href="/study/css/user.css">
<link rel="stylesheet" type="text/css" href="/study/css/userInfo.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="/study/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/study/js/w3color.js"></script>
<!-- <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script> -->
<style type="text/css">
</style>
<script type="text/javascript">
	$(document).ready(function(){
		var ws;
	    var messages = $('#messages');	    
	    
	    $('#openSocket').click(function(){
	        if(ws != undefined && ws.readyState != WebSocket.CLOSED ){
	            writeResponse("서버가 이미 연결 되어 있습니다.");
	            return;
	        };
	
	        ws = new WebSocket("ws://localhost/study/echo.mentor");
	        
	        ws.onopen = function(event){
	            if(event.data == undefined){
	          		return;
	            }
	            writeResponse(event.data);
	        };
	        
	        ws.onmessage = function(event){
	            console.log('writeResponse');
	            console.log(event.data)
	            writeResponse(event.data);
	        };
	        
	        ws.onclose = function(event){
	            writeResponse("대화 종료");
	        }
	        
	    });
	    
	    $('#send').click(function(){
	        var text = $('#messageinput').val() + "," + $('#sender').val();
	        ws.send(text);
	        text = "";
	    });
	    
	    $('#closeSocket').click(function(){
	        ws.close();
	    });
	    
	    function writeResponse(text){
	    	$('#messages').html("<br/>" + text);
	    }
	
	    $('#clearText').click(function(){
	        console.log(messages.parentNode);
	        messages.parentNode.removeChild(messages)        
	    });
	    
	    function clearText(){
	  	}
	});        

</script>
</head>
<body >
	<div>
	    <button type="button" id="openSocket">대화방 참여</button>
	    <button type="button" id="closeSocket">대회방 나가기</button>
		<br/><br/><br/>
					메세지 입력 : 
	    <input type="text" id="sender" value="${SID}" style="display: none;">
	    <input type="text" id="messageinput">
	    <button type="button" id="send">메세지 전송</button>
	    <button type="button" id="clearText">대화내용 지우기</button>
	</div>
	
	<!-- Server responses get written here -->
	<div id="messages">
	</div>
<script type="text/javascript">
	
</script>
</body>
</html>