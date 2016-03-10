<% String log = request.getParameter("log"); %>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Tous au soleil | Accueil</title>
</head>
<body>
	<header>
		<h1>Accueil | <%=log %></h1>
	</header>
	<section>
		<h2>Rechercher un vol:</h2>
		<form action="rechercher-vol">
			<p>Destination: <input type="text" name="dest"></p>
			<p>Date de d&eacute;part: Jour <input type="number" maxlength=2 max=31 name="jour"> Mois <input type="number" maxlength=2 max=12 name="mois"> Ann&eacute;e <input type="number" maxlength=4 name="annee" value=2016></p>
			<p>Nombre de personnes: <input type="number" name="nbpers"></p>
			<input type="submit" value="Rechercher">
		</form><br/>
	</section>
	<footer>
	</footer>
</body>
</html>