<%@ page import="modele.Compte" %>
<%@ page import="modele.Vol" %>
<% Compte log = Compte.getCompte((String) session.getAttribute("log")); %>
<% Vol vol = (Vol) request.getAttribute("vol"); %>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Tous au soleil | Ajout d'un vol</title>
</head>
<body>
	<header>
		<h1>Administration</h1>
	</header>
	<section>
		<% if(vol != null) { %>
			<p>F&eacute;licitations, votre vol n&deg; <%=vol.getNoVol() %> a bien &eacute;t&eacute; ajout&eacute;.</p>
			<p><a href="accueil">Ajouter un autre vol</a></p>
		<% } else { %>
			<p>Nous sommes d&eacute;sol&eacute;s, votre vol n'a pas pu &ecirc;tre ajout&eacute;.</p>
			<p><a href="accueil">R&eacute;&eacute;ssayer</a></p>
		<% } %>
	</section>
	<footer>
		<p><a href="deconnexion">Se d&eacute;connecter</a></p>
	</footer>
</body>
</html>