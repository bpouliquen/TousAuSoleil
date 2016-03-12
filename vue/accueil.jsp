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
		<form action="../rechercher-vol">
			<p>Destination: <input type="text" name="dest"></p>
			<p>Date de d&eacute;part: <input type="text"  name="date"> format: YYYY/MM/DD</p>
			<p>Nombre de personnes: <input type="number" name="nbpers"></p>
			<input type="hidden" name="log" value="<%=log %>">
			<input type="submit" value="Rechercher">
		</form><br/>
	</section>
	<footer>
	</footer>
</body>
</html>