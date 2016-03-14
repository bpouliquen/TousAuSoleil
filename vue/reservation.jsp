<%@ page import="modele.Reservation" %>
<%@ page import="modele.Compte" %>
<% Compte log = Compte.getCompte((String) session.getAttribute("log")); %>
<%
	Reservation re = (Reservation) request.getAttribute("re");
%>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Tous au soleil | R&eacute;servation</title>
</head>
<body>
	<header>
		<h1>R&eacute;servation | <%=log.getPrenom() %></h1>
	</header>
	<section>
		<% if(re != null) { %>
			<p>F&eacute;licitations, votre vol n&deg; <%=re.getNoVol() %> a bien &eacute;t&eacute; 
			<% if(re.getConfirmation()) { %>
				r&eacute;serv&eacute;.</p>
			<% } else { %>
				prer&eacute;serv&eacute;. Vous avez 24h pour confirmer votre r&eacute;servation.</p>
			<% }
		} else { %>
			<p>Nous sommes dé&eacute;l&eacute;, votre r&eacute; n'a pas pu &ecirc;tre &eacute;ffectu&eacute;e. Veuillez r&eacute;essayer.</p>
		<% } %>
		<a href="accueil">Retourner &agrave; l'accueil</a>
	</section>
	<footer>
		<p><a href="deconnexion">Se d&eacute;connecter</a></p>
	</footer>
</body>
</html>