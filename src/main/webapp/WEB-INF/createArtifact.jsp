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
	<h2>Add an Artifact</h2>
	<div class="container mt-5">
		<form:form action="/digs/${dig.id}/newArtifact" method="POST" modelAttribute="newArtifact">
			<p>
				<form:label path="category">Category: </form:label>
				<form:select path="category" class="form-control">
				<form:errors path="category"/>
					<form:option value="none" label="select"/>
					<form:option value="jewelry" label="jewelry"/>
					<form:option value="pottery" label="pottery"/>
					<form:option value="remains, faunal" label="remains, faunal"/>
					<form:option value="remains, human" label="remains, human"/>
					<form:option value="tools" label="tools"/>
					<form:option value="art" label="art"/>
				</form:select>
			</p>
			<p>
				<form:label path="material">Material: </form:label>
				<form:input path="material" class="form-control"/>
				<form:errors path="material"/>
			</p>
			<p>
				<form:label path="description">Description: </form:label>
				<form:input path="description" class="form-control"/>
				<form:errors path="description"/>
			</p>
			<p>
<%-- 				<form:label path="image">Description: </form:label>
				<form:input type="file" path="image" class="form-control"/>
				<form:errors path="image"/> --%>
			</p>
 			<form:hidden path="artifactCreator" value="${userId }"/>
			<form:hidden path="dig" value="${dig.id}"/>
	 	<button type="submit" class="btn btn-warning">Submit</button>
		</form:form>
		<button class="mt-2 btn btn-warning" onclick="window.location.href = '/digs/view/${dig.id}'">Cancel</button>
	</div>
</body>
</html>