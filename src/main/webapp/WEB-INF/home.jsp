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
		<h1>Welcome, ${userName }!</h1>
		<a href="/logout">Logout</a>
	</div>
	<div>
		<h3>New project?</h3>
		<p>Create a new dig</p>
	</div>
	<div>
		<h3>My Digs</h3>
		<!-- Map user's digs -->
	</div>
</body>
</html>