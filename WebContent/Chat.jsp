<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Chat</title>
	</head>
	<body>
		Chat de l'application :
		<div id="chat_appli" style="height:6em; overflow: scroll; border: dashed 1px gray;">
			<%@ page import="projet.ChatApplication" %>
			<%= ChatApplication.affiche() %>
		</div>
		<% response.setIntHeader("Refresh", 5); %>
	</body>
</html>