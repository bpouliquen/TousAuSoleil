<%@ page import="modele.Compte" %>
<% Compte log = Compte.getCompte((String) session.getAttribute("log")); %>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Tous au soleil | Accueil</title>
</head>
<body>
	<header>
		<h1>Accueil | <%=(String) log.getPrenom() %></h1>
	</header>
	<section>
		<h2><a href="controler-reservations">Contr&ocirc;ler mes r&eacute;servations</a></h2>
	</section>
	<section>
		<h2>Rechercher un vol:</h2>
		<form action="rechercher-vol">
			<p>Destination: <input type="text" name="dest"></p>
			<p>Date de d&eacute;part: <input type="date"  name="date"></p>
			<p>Nombre de personnes: <input type="number" name="nbpers"></p>
			<input type="submit" value="Rechercher">
		</form><br/>
	</section>
	<footer>
		<p><a href="deconnexion">Se d&eacute;connecter</a></p>
	</footer>
</body>
</html>