<%@page import="projet.RequetesSQL"%>
<%@page import="projet.VueSQL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META NAME = "author" CONTENT = "MAIA Anthony, MARNAT Josselin, MOUTRILLE Romain, STINDEL Hugo">
		<META NAME = "description" CONTENT = "page d'accueil">
		<META NAME = "keywords" CONTENT = "éditore++, éditeur, édition, fichier">
		<TITLE>Editore++ : Personne</TITLE>
</head>
<body>
	<div class="header">
		<div class="center-header">
		<form action="Deco" method="post" name="deco">
		<input type="submit" value="Deconnexion"></input>
		</form>
		</div>
	</div>
 	<%session.setAttribute("pseudo", request.getParameter("pseudo")); %>
	<h1>Page de : ${pseudo}</h1>
	<hr> 
	<h2>Ses contributions :</h2>
		<%=VueSQL.listeFichiers(RequetesSQL.listeFichiers((String) session.getAttribute("pseudo")))%>
		<a href="Perso.jsp">retour à ma page</a>
	<hr>

</body>
</html>