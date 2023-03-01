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
	<h2>Create a Dig</h2>
	<div class="container mt-5">
		<form:form action="/digs/edit/{id}" method="POST" modelAttribute="">
			<p>
				<form:label path="digName">Name your dig: </form:label>
				<form:input path="digName" class="form-control"/>
				<form:errors path="digName"/>
			</p>
			<p class="font-italic">
				Suggesting naming convention is location or project name and year.
			</p>
			<p>
				<form:label path="startDate">Start date: </form:label>
				<form:input type="date" path="startDate" class="form-control"/>
				<form:errors path="startDate"/>
			</p>
			<p>
				<form:label path="endDate">End date: </form:label>
				<form:input type="date" path="endDate" class="form-control"/>
				<form:errors path="endDate"/>
			</p>
			<p>
				<form:label path="notes">Notes (optional): </form:label>
				<form:input path="notes" class="form-control"/>
				<form:errors path="notes"/>
			</p>
			<form:hidden path="digCreator"/>
	 	<button type="submit">Submit</button>
		</form:form>
		<button onclick="window.location.href = '/home'">Cancel</button>
	</div>
</body>
</html>