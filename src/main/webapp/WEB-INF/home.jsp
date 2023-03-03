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
<div class="artifactImg">
	<div class="d-flex justify-content-between seethru">
		<h1>Welcome, ${loggedInUser.userName }!</h1>
		<a href="/logout">Logout</a>
	</div>
	<div class="d-flex gap-3">
	<div class="m-5 col opaque p-3">
		<h3>New project?</h3>
		<p><a href="/digs/new">Create a new dig</a></p>
	</div>
	<div class="m-5 col opaque p-3">
		<h3>My Digs</h3>
		<c:forEach var="oneDig" items="${digList }">
			<c:if test="${oneDig.digParticipants.contains(loggedInUser)}">
				<p><a href="/digs/view/${oneDig.id }"><c:out value="${oneDig.digName}"/></a></p>
			</c:if>
		</c:forEach>
	</div>
</div>
</div>
</body>
</html>