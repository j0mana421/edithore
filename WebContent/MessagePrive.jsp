<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="projet.ConnexionSocket"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editore++ : Messages Privés</title>
</head>
<body>
	Ceci est la jsp des messages privés de ${nom}. Voici vos messages :
	<%
	ConnexionSocket cs = ((ConnexionSocket) session
			.getAttribute("ConnexionSocket"));
%>
	<%=cs.getMessage()%>
</body>
</html>