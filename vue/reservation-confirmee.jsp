<%@ page import="modele.Compte" %>
<% Compte log = Compte.getCompte((String) session.getAttribute("log")); %>
<%
	int noRes = (int) request.getAttribute("nores");
	int statut = (int) request.getAttribute("statut");
%>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Tous au soleil | Confirmation</title>
</head>
<body>
	<header>
		<h1>Confirmation de r&eacute;seervation | <%=log.getPrenom() %></h1>
	</header>
	<section>
		<% if(statut > 0) { %>
			<p>F&eacute;licitations, votre r&eacute; n&deg; <%=noRes %> a bien &eacute;t&eacute; confirm&eacute;e.</p>
		<% } else { %>
			<p>Nous sommes d&eacute;sol&eacute;, votre r&eacute;servation n&deg; <%=noRes %> n'a pas pu &ecirc;tre confirm&eacute;e.</p>
		<% } %>
		<p><a href="accueil">Retourner &agrave; l'accueil</a></p>
	</section>
	<footer>
		<p><a href="deconnexion">Se d&eacute;connecter</a></p>
	</footer>
</body>
</html>