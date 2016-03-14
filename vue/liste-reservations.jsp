<%@ page import="java.util.ArrayList" %>
<%@ page import="modele.Reservation" %>
<%@ page import="modele.Compte" %>
<% Compte log = Compte.getCompte((String) session.getAttribute("log")); %>
<%
	ArrayList<Reservation> res = (ArrayList<Reservation>) request.getAttribute("res");
%>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Tous au soleil | Mes r&eacute;servations</title>
</head>
<body>
	<header>
		<h1>Mes r&eacute;servations | <%=log.getPrenom() %></h1>
	</header>
	<section>
		<% if(res==null) { %>
			<p>Erreur de r&eacute;cup&eacute;ration des donn&eacute;es.</p>
		<% } else {
			if(res.size() == 0) { %>
				<p>Vous n'avez aucune r&eacute;servation en cours.</p>
			<% } else { %>
				<table>
					<tr>
						<td>N&deg; R&eacute;servation</td>
						<td>N&deg; Vol</td>
						<td>Nombre de personnes</td>
						<td>Confirm&eacute;</td>
						<td>Action</td>
					</tr>
					<% for(Reservation r : res) { %>
						<tr>
							<td><%=r.getNoReservation() %></td>
							<td><%=r.getNoVol() %></td>
							<td><%=r.getNbePlaces() %></td>
							<td><% if(r.getConfirmation()) { %>OUI<% } else { %>NON<% } %></td>
							<% if(!r.getConfirmation()) { %><td><a href="confirmer-reservation?res=<%=r.getNoReservation() %>">Confirmer</a></td><% } %>
						</tr>
					<% } %>
				</table>
			<% } %>
		<% } %>
		<p><a href="accueil">Retourner &agrave; l'accueil</a></p>
	</section>
	<footer>
		<p><a href="deconnexion">Se d&eacute;connecter</a></p>
	</footer>
</body>
</html>