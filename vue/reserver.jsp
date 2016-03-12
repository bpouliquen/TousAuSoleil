<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Date" %>
<%@ page import="modele.Vol" %>
<%
	String log = request.getParameter("log");
	String dest = request.getParameter("dest");
	String date = request.getParameter("date");
	int nbPers = (int) request.getAttribute("nbpers");
	ArrayList<Vol> vols = (ArrayList<Vol>) request.getAttribute("vols");
%>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Tous au soleil | R&eacute;server</title>
</head>
<body>
	<header>
		<h1>R&eacute;server un vol | <%=log %></h1>
	</header>
	<section>
		<h2>Param&egrave;tres:</h2>
		<p>Destination: <%=dest %>
		<br/>Date de d&eacute;part: <%=date %>
		<br/>Places: <%=nbPers %></p>
		<h2>Liste des vols:</h2>
		<% if (vols.size() > 0) { %>
		<table>
			<tr>
				<td>N&deg; de vol</td>
				<td>Destination</td>
				<td>Date de d&eacute;part</td>
				<td>Nombres de places</td>
				<td>Prix (&agrave; l'unit&eacute;)</td>
				<td>Action</td>
			</tr>
			<tr><% for(Vol v : vols) { %>
				<td><%=v.getNoVol() %></td>
				<td><%=v.getDestination() %></td>
				<td><%=v.getDateDepart().toString() %></td>
				<td><%=v.getNbePlaces() %></td>
				<td><%=v.getPrix() %></td>
				<td><a href="../reserver?log=<%=log %>&op=re&vol=<%=v.getNoVol() %>">R&eacute;servation</a> | <a href="../reserver?log=<%=log %>&op=pre&vol=<%=v.getNoVol() %>">Prer&eacute;servation</a></td>
			</tr>
			<% } %>
		</table>
		<% } else { %><p>D&eacute;sol&eacute;, aucun vol n'est disponible aux crit&egrave;res demand&eacute;s.<br/><a href="vue/accueil.jsp?log=<%=log %>">Retourner &agrave; l'accueil</a></p><% } %>
	</section>
	<footer>
	</footer>
</body>
</html>