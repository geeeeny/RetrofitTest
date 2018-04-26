<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.js">
</script>
</head>

<script>
	$(function() {
		var socket = new SockJS("dashboard"); //~gateway/dashboard
		socket.onopen = function() {
			console.log('접속 성공');
		}

		socket.onclose = function() {
			console.log('접속 해제');
		}

		socket.onmessage = function(msg) {
			var status = JSON.parse(msg.data);
			console.log('데이터 수신: ', status);
		}

		socket.onerror = function(err) {
			console.log('에러 ', err);
		}

	});
</script>
<body>
	<h1>DashBoard</h1>

	<P>The time on the server is ${serverTime}.</P>
</body>
</html>
