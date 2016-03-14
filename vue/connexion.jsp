<%@ page import="modele.Compte" %>
<% Compte log = Compte.getCompte((String) session.getAttribute("log")); %>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Tous au soleil | Connexion</title>
</head>
<body>
	<header>
		<h1>Connexion</h1>
	</header>
	<section>
		<% if(log != null) { %>
			<p>F&eacute;licitations M./Mme. <%=log.getNom() %>, Vous &ecirc;tes bien connect&eacute; sous le pseudo <%=session.getAttribute("log") %>
			<br/><a href="accueil">Aller &agrave; l'accueil</a></p>
		<% } else { %>
			<p>Nous sommes d&eacute;sol&eacute;, nous n'avons pas pu vous connecter. V&eacute;rifiez vos informations.<br/>
			<a href="index.html">Retourner au formulaire de connexion</a></p>
		<% } %>
	</section>
	<footer>
		<p><a href="deconnexion">Se d&eacute;connecter</a></p>
	</footer>
</body>
</html>