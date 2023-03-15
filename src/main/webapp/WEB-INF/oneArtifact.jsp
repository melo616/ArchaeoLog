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
</head>
<body>
	<div class="d-flex justify-content-between">
		<h1>Artifact Details: <c:out value="${dig.digName}"/></h1>
		<div>
			<p><a href="/logout">Logout</a></p>
			<p><a href="/digs/view/${dig.id}">back to dig</a></p>
		</div>
	</div>
	<div class="m-3">
		<h5>Category:</h5>
		<p><c:out value="${artifact.category}"/></p>
		<h5>Material(s):</h5>
		<p><c:out value="${artifact.material}"/></p>
		<h5>Description:</h5>
		<p><c:out value="${artifact.description}"/></p>
		<h5>Logged by:</h5>
		<p><c:out value="${artifact.artifactCreator.userName}"/></p>
		<h5>Created:</h5>
		<p><fmt:formatDate type="date" value="${artifact.createdAt}" pattern="MMM dd YYYY hh:mma"/></p>
		<c:if test="${artifact.updatedAt} != null">
		<h5>Last Updated:</h5>
		<p><fmt:formatDate type="date" value="${artifact.updatedAt}" pattern="MMM dd YYYY hh:mma"/></p>
		</c:if>
	</div>
	<div class="d-flex gap-2">
		<form method="POST" action="/digs/${dig.id}/artifacts/delete/${artifact.id}">
			<input type="hidden" name="_method" value="delete"/>
			<button class="btn btn-danger" type="submit">Delete Artifact</button>
		</form>
		<button class="btn btn-warning" onclick="window.location.href ='/digs/${dig.id}/artifacts/edit/${artifact.id }'">Edit Artifact</button>
	</div>
<script src="/js/script.js"></script>
</body>
</html>