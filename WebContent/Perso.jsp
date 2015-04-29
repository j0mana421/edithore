<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <META NAME = "author" CONTENT = "MAIA Anthony, MARNAT Josselin, MOUTRILLE Romain, STINDEL Hugo">
    <META NAME = "description" CONTENT = "page d'accueil">
    <META NAME = "keywords" CONTENT = "éditore++, éditeur, édition, fichier">
    <TITLE>Editore++</TITLE>
</head>
<body>
    <div class="header">
      <div class="center-header">
      <form action="Deco" method="post" name="deco">
	    <input type="submit" value="Deconnexion"></input>
      </form>
      </div>
      <input type="text" name="recherche-pseudo" placeholder="Recherche utilisateur" size=30></input>
      <input type="submit" value="OK"></input>
      <input type="text" name="recherche-fichier" placeholder="Recherche fichier" size=30></input>
      <input type="submit" value="OK"></input>
	<%
		session = request.getSession();
		String pseudo = (String) session.getAttribute("nom");
	%>
      <hr>Bonjour <%= pseudo %><hr>
      Bienvenue dans votre gestionnaire de fichiers
      <br>
      <form action="AjoutFic" method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>Envoi de fichier</legend>

                <label for="description">Description du fichier</label>
                <input type="text" name="description" size="60"/>
                <br />
	
                <label for="fichier">Emplacement du fichier</label>
                <input type="file" name="fichier" />
                <br />
                
                <input type="submit" value="Envoyer" />
                <br />                
            </fieldset>
        </form>
    </div>
    <hr>
 	<h2>Vos fichiers</h2>
    <%@page import="javax.swing.JFrame"%>
    <%@page import="java.awt.BorderLayout"%>
    <%@page import="projet.RequetesSQL"%>
    <%@page import="projet.VueSQL"%>
    <%@page import="projet.TablePanel"%>
    <%=VueSQL.listeFichiers(RequetesSQL.listeFichiers(pseudo))%>
    
    <hr>
    <div id="chat" style="dispay:block; width:100%; border: none;">
	    
		<form action="/Projet/SendMessage" method="post">
			<fieldset>
				<legend>Chat de l'application</legend>
				<iframe src="Chat.jsp" style="width: 100%; border: dashed 1px gray;"></iframe>
				<select name="dest">
					<optgroup label="Groupes :">
						<option value="#all">Tout le monde</option>
					</optgroup>
					<optgroup label="Personnes :">
						<%@ page import="projet.Tracker" %>
						<%=Tracker.listeMachines()%>
					</optgroup>
				</select>
				<input type="text" name="txt" value="votre message ici"/>
				<input type="submit" value="Envoyer" />
			</fieldset>
		</form>
    </div>
</body>
</html>