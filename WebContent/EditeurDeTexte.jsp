<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="projet.RequetesSQL"%>
<%@page import="projet.VueSQL"%>

<%
	if (!(RequetesSQL.verifUtilisateur(
			(String) session.getAttribute("nom"),
			(String) session.getAttribute("mdp")))) {
%>
<c:redirect url="/index.html" />
<%
	}
%>

<%
	session.setAttribute("idFichier", request.getParameter("idFichier"));
%>
<%
	session.setAttribute("nomStockage", RequetesSQL
			.getNomStockage(Integer.parseInt(request
					.getParameter("idFichier"))));
%>
<%
	session.setAttribute("nomAff", RequetesSQL.getNomAff(Integer
			.parseInt(request.getParameter("idFichier"))));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edithore++ : éditeur de texte</title>
<link rel="stylesheet" type="text/css" href="include/page.css">
</head>
<body>
	<div id="header">
		<div class="center-header">
			<img src="include/titre.png">
			<form action="Deco" method="post" name="deco">
				<input type="submit" value="Deconnexion"></input>
			</form>
		</div>
	</div>
	<div id="corps">
		<div id="bonjour">
			Éditeur de fichiers.<br>
			<%=(request.getParameter("svg") != null) ? "<i>fichier sauvegardé avec succès</i><br><br>": ""%>
		</div>
	
		<div id="gauche">
			<div class="cadre">
				<form action="MAJFic" method="post">

					<div class="legendeCadre">
						<span class="legende">${nomAff} modifié par ${nom}</span>
					</div>
					<textarea id="editeur_de_texte" name="texte"><%=(new VueSQL()).contenuFichier((String) session
					.getAttribute("nomStockage"))%></textarea>
					<script type="text/javascript" src="enableTab.js"></script>
					<input type="hidden" name="titre" value="${nomStockage}" /> <input
						type="hidden" name="idFichier" value="${idFichier}" /> <input
						type="hidden" name="nomDernierUser" value="${nom}" /> <br> <input
						type="submit" value="Enregistrer cette version" title="MAJFic" />
				</form>
			</div>
			<div class="cadre">
				<div class="legendeCadre">
					<span class="legende">Liste des Contributeurs</span>
				</div>
				<%=VueSQL.listeContributeurs(RequetesSQL
					.listeContributeurs(Integer.parseInt(request
							.getParameter("idFichier"))))%>

				<form action="AjouteContributeur" method="post">
					<input type="hidden" name="idFichier" value="${idFichier}" /> <input
						type="text" name="userEnPlus" placeholder='exemple: "bibi42"' />
					<input type="submit" value="Ajouter ce contributeur" />
				</form>
			</div>
		</div>
		<div id="chat">
			<h2>Commentaires fichier</h2>
			<div class="cadreChat">
				<form action="Chat" method="post">
					<br>
					<iframe src="CommentairesFichiers.jsp"></iframe>
					<br> <input type="hidden" name="idFichier"
						value="${idFichier}" /> <input type="text" name="userEnPlus"
						placeholder='exemple: "bibi42"' /> <input type="submit"
						value="message" />
				</form>
			</div>
		</div>

		<a href="Perso.jsp">retour à la page Perso</a>
</body>
</html>