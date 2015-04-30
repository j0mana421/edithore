<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="projet.RequetesSQL"%>
<%@page import="projet.VueSQL"%>
<%@page import="projet.TablePanel"%>
<%@page import="projet.Tracker"%>

<%
	if (!(RequetesSQL.verifUtilisateur(
			(String) session.getAttribute("nom"),
			(String) session.getAttribute("mdp")))) {
%>
<c:redirect url="/index.html" />
<%
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author"
	content="MAIA Anthony, MARNAT Josselin, MOUTRILLE Romain, STINDEL Hugo">
<meta name="description" content="page d'accueil">
<meta name="keywords" content="éditore++, éditeur, édition, fichier">
<title>Editore++</title>
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
		<%
			session = request.getSession();
			String pseudo = (String) session.getAttribute("nom");
		%>
		
		<div id="bonjour">
			Bonjour ${nom}<br>
			Bienvenue dans votre gestionnaire de fichiers.
		</div>

		<div id="gauche">
			<div class="cadre">
				<div class="legendeCadre">
					<span class="legende">Liste de vos fichiers</span>
				</div>
				<%=VueSQL.listeFichiers(RequetesSQL.listeFichiers(pseudo))%>
			</div>

			<div class="cadre">
				<form action="AjoutFic" method="post" enctype="multipart/form-data">
					<div class="legendeCadre">
						<span class="legende">Envoi de fichier</span>
					</div>
					<br /> <label for="fichier">Emplacement du fichier</label> <input
						type="file" name="fichier" /> <br /> <label for="description">Description
						du fichier</label> <input type="text" name="description" size="60" /> <br />
					<input type="submit" value="Envoyer" /> <br />
				</form>
			</div>
			<br />
			<div class="cadre">
				<form action="SearchU" method="post">
					<div class="legendeCadre">
						<span class="legende">Recherche de gens</span>
					</div>
					<input type="text" name="ru" placeholder="Recherche utilisateur"
						size=30></input> <input type="submit" value="OK"></input>
				</form>
			</div>
		</div>
		<div id="droite">
			<div class="cadre mp">
				<div class="legendeCadre">
					<span class="legende">Messages privés</span>
				</div>
				<form class="form_inline" action="MP" method="post">
					<input type="submit" value="voir MP" />
				</form>
				<form class="form_inline" action="formulaireMP.html" method="post">
					<input type="submit" value="Envoyer MP" />
				</form>
			</div>
			<br><br><br><br>
			<div class="cadre">
				<div class="legendeCadre">
					<span class="legende">Chat de l'application</span>
				</div>
				<form action="/Projet/SendMessage" method="post">
					<br />
					<iframe src="ChatApplication.jsp"></iframe>
					<br /> <input type="text" name="txt"
						placeholder="votre message ici" /> <input type="submit"
						value="Envoyer" />
				</form>
			</div>
		</div>
	</div>

</body>
</html>