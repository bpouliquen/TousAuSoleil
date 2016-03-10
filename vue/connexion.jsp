<%@ page import="modele.Compte" %>
<%
	Compte c = (Compte) request.getAttribute("compte");
%>
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
		<% if(c != null) { %>
			<p>F&eacute;licitations M./Mme. <%=c.getNom() %>, Vous &ecirc;tes bien connect&eacute; sous le pseudo <%=c.getLogin() %>
			<br/><a href="vue/accueil.jsp?log=<%=c.getLogin() %>">Aller &agrave; l'accueil</a></p>
		<% } else { %>
			<p>Nous sommes d&eacute;sol&eacute;, nous n'avons pas pu vous connecter. V&eacute;rifiez vos informations.<br/>
			<a href="index.html">Retourner au formulaire de connexion</a></p>
		<% } %>
	</section>
</body>
</html>