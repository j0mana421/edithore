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
	session.setAttribute("nomStockage", RequetesSQL.getNomStockage(Integer.parseInt(request.getParameter("idFichier"))));
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
<link rel="stylesheet" type="text/css" href="include/editeur_de_texte.css">
<script type="text/javascript" src="include/editeur.js"></script>
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
			<%=(request.getParameter("svg") != null)
					? "<i>fichier sauvegardé avec succès</i><br><br>"
					: ""%>
		</div>

		<div id="gauche">
			<div class="cadre">
				<form action="MAJFic" method="post">

					<div class="legendeCadre">
						<span class="legende">${nomAff} modifié par ${nom}</span>
					</div>

					<script type="text/javascript" src="enableTab.js"></script>

					<br>Couleur du texte :
					<button class="bouton_couleur" style="background-color: #00FFFF"
						onclick="colorise('texte','#00FFFF')"></button>
					<button class="bouton_couleur" style="background-color: #000000"
						onclick="colorise('texte','#000000')"></button>
					<button class="bouton_couleur" style="background-color: #0000FF"
						onclick="colorise('texte','#0000FF')"></button>
					<button class="bouton_couleur" style="background-color: #FF00FF"
						onclick="colorise('texte','#FF00FF')"></button>
					<button class="bouton_couleur" style="background-color: #808080"
						onclick="colorise('texte','#808080')"></button>
					<button class="bouton_couleur" style="background-color: #008000"
						onclick="colorise('texte','#008000')"></button>
					<button class="bouton_couleur" style="background-color: #00FF00"
						onclick="colorise('texte','#00FF00')"></button>
					<button class="bouton_couleur" style="background-color: #800000"
						onclick="colorise('texte','#800000')"></button>
					<button class="bouton_couleur" style="background-color: #000080"
						onclick="colorise('texte','#000080')"></button>
					<button class="bouton_couleur" style="background-color: #808000"
						onclick="colorise('texte','#808000')"></button>
					<button class="bouton_couleur" style="background-color: #800080"
						onclick="colorise('texte','#800080')"></button>
					<button class="bouton_couleur" style="background-color: #FF0000"
						onclick="colorise('texte','#FF0000')"></button>
					<button class="bouton_couleur" style="background-color: #C0C0C0"
						onclick="colorise('texte','#C0C0C0')"></button>
					<button class="bouton_couleur" style="background-color: #008080"
						onclick="colorise('texte','#008080')"></button>
					<button class="bouton_couleur" style="background-color: #FFFFFF"
						onclick="colorise('texte','#FFFFFF')"></button>
					<button class="bouton_couleur" style="background-color: #FFFF00"
						onclick="colorise('texte','#FFFF00')"></button>

					<br>Couleur du fond :
					<button class="bouton_couleur" style="background-color: #00FFFF"
						onclick="colorise('fond','#00FFFF')"></button>
					<button class="bouton_couleur" style="background-color: #000000"
						onclick="colorise('fond','#000000')"></button>
					<button class="bouton_couleur" style="background-color: #0000FF"
						onclick="colorise('fond','#0000FF')"></button>
					<button class="bouton_couleur" style="background-color: #FF00FF"
						onclick="colorise('fond','#FF00FF')"></button>
					<button class="bouton_couleur" style="background-color: #808080"
						onclick="colorise('fond','#808080')"></button>
					<button class="bouton_couleur" style="background-color: #008000"
						onclick="colorise('fond','#008000')"></button>
					<button class="bouton_couleur" style="background-color: #00FF00"
						onclick="colorise('fond','#00FF00')"></button>
					<button class="bouton_couleur" style="background-color: #800000"
						onclick="colorise('fond','#800000')"></button>
					<button class="bouton_couleur" style="background-color: #000080"
						onclick="colorise('fond','#000080')"></button>
					<button class="bouton_couleur" style="background-color: #808000"
						onclick="colorise('fond','#808000')"></button>
					<button class="bouton_couleur" style="background-color: #800080"
						onclick="colorise('fond','#800080')"></button>
					<button class="bouton_couleur" style="background-color: #FF0000"
						onclick="colorise('fond','#FF0000')"></button>
					<button class="bouton_couleur" style="background-color: #C0C0C0"
						onclick="colorise('fond','#C0C0C0')"></button>
					<button class="bouton_couleur" style="background-color: #008080"
						onclick="colorise('fond','#008080')"></button>
					<button class="bouton_couleur" style="background-color: #FFFFFF"
						onclick="colorise('fond','#FFFFFF')"></button>
					<button class="bouton_couleur" style="background-color: #FFFF00"
						onclick="colorise('fond','#FFFF00')"></button>

					<br>Mise en page :
					<button onclick="balise_simble('br')">retour à la ligne</button>
					<button onclick="balise_simble('hr')">ligne horizontale</button>
					<button onclick="balise('b')">
						<b>gras</b>
					</button>
					<button onclick="balise('i')">
						<i>italique</i>
					</button>
					<button onclick="balise('u')">
						<u>souligné</u>
					</button>
					<button onclick="balise('h1')">grand titre</button>
					<button onclick="balise('h2')">sous-titre</button>
					<button onclick="balise('h3')">sous-sous-titre</button>
					<button onclick="tage('span', 'class')">classe</button>
					<button onclick="tage('span', 'style')">style</button>
					<button onclick="tage('a','href')">lien</button>
					<button onclick="tage('img','src')">image</button>

					<button onclick="balise('ol')">liste ordonnée</button>
					<button onclick="balise('ul')">liste non-ordonnée</button>
					<button onclick="balise('li')">element de liste</button>
					<button onclick="balise('table')">tableau</button>
					<button onclick="balise('tr')">ligne</button>
					<button onclick="balise('td')">cellule</button>

					<hr> Entrée du texte :<br>
					<button onclick="selectionne_tout()">tout sélectionner</button>
					<button onclick="efface_tout()">tout effacer</button>
					<textarea onkeyup="met_a_jour()" id="editeur_de_texte" name="texte"><%=(new VueSQL()).contenuFichier((String) session
					.getAttribute("nomStockage"))%></textarea>
					
					<br><br>
					Résultat :
					<div id="resultat"></div>
					
					<input type="hidden" name="titre" value="${nomStockage}" /> <input
						type="hidden" name="idFichier" value="${idFichier}" /><br> <input
						type="submit" value="Enregistrer cette version" title="MAJFic" />
			</div>
			<script type="text/javascript">
				met_a_jour();
			</script>
			</form>
		</div>
		<div class="cadre">
			<div class="legendeCadre">
				<span class="legende">Liste des Contributeurs</span>
			</div>
			IDFIC: <%=request.getParameter("idFichier")%>
			<%=VueSQL.listeContributeurs(RequetesSQL.listeContributeurs(Integer.parseInt(request.getParameter("idFichier"))))%>

			<form action="AjouteContributeur" method="post">
				<input type="hidden" name="idFichier" value="${idFichier}" /> <input
					type="text" name="userEnPlus" placeholder='exemple: "bibi42"' /> <input
					type="submit" value="Ajouter ce contributeur" />
			</form>
		</div>
	</div>
	<div id="chat">
		<h2>Commentaires fichier</h2>
		<div class="cadreChat">
			<form action="Chat" method="post">
				<br>
				<iframe src="CommentairesFichiers.jsp?idFichier=6"></iframe>
				<br> <input type="hidden" name="idFichier" value="${idFichier}" />
				<input type="text" name="userEnPlus" placeholder='exemple: "bibi42"' />
				<input type="submit" value="message" />
			</form>
		</div>
	</div>

	<a href="Perso.jsp">retour à la page Perso</a>
	</div>
</body>
</html>