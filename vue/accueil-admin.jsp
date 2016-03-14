<%@ page import="modele.Compte" %>
<% Compte log = Compte.getCompte((String) session.getAttribute("log")); %>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Tous au soleil | Administration</title>
</head>
<body>
	<header>
		<h1>Administration</h1>
	</header>
	<section>
		<h2>Ajouter un vol:</h2>
		<form action="ajouter-vol">
			<p>Destination : <input type="text" name="dest"></p>
			<p>Date de D&eacute;part : <input type="date" name="date"></p>
			<p>Nombre de places : <input type="number" name="nbeplaces"></p>
			<p>Prix : <input type="number" name="prix"></p>
			<input type="submit" value="Ajouter un vol">
		</form>
	</section>
	<footer>
		<p><a href="deconnexion">Se d&eacute;connecter</a></p>
	</footer>
</body>
</html>