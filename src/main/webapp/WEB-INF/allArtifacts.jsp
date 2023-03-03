<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ArchaeoLog</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<!-- <link rel="preload" href="/images/wall.jpeg" as="image"/> -->
</head>
<body>
<div class="artifactImg">
	<div class="d-flex justify-content-between seethru">
		<h1>Viewing All Logged Artifacts: <c:out value="${dig.digName}"/></h1>
		<div>
			<p><a href="/logout">Logout</a></p>
			<p><a href="/digs/view/${dig.id}">back to dig</a></p>
		</div>
	</div>
	<div class="m-3 opaque p-3">
	<h3>Artifacts</h3>
	<button class="btn btn-warning" onclick="window.location.href ='/digs/${dig.id}/newArtifact'">Add new artifact</button>
	<c:forEach var="oneArtifact" items="${artifactList}">
		<p><a href="/digs/${dig.id}/artifacts/${oneArtifact.id}"><c:out value="${oneArtifact.category}; ${oneArtifact.material}"/></a> | logged by <c:out value="${oneArtifact.artifactCreator.userName}"/> at <fmt:formatDate type="date" value="${oneArtifact.createdAt}" pattern="MMM dd YYYY hh:mma"/></p>
	</c:forEach>
	</div>
</div>
<script src="/js/script.js"></script>
</body>
</html>