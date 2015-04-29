<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Chat</title>
	</head>
	<body>
		<div id="chat_appli" style="height:100%; overflow: scroll; border: none;">
			<%@ page import="projet.ChatApplication" %>
			<%= ChatApplication.affiche() %>
		</div>
		<% response.setIntHeader("Refresh", 5); %>
	</body>
</html>