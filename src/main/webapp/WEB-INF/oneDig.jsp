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
	<h1>Viewing details of <c:out value="${dig.digName}"/></h1>
	<p>Created by 
	<c:choose>
		<c:when test="${dig.digCreator.id.equals(userId)}">you</c:when>
		<c:otherwise><c:out value="${dig.digCreator.userName }"/></c:otherwise>
	</c:choose>
	</p>
	<hr>
	<button onclick="showHide()">View/Hide Participants</button>
	<div id="participantList">
		<c:forEach var="oneParticipant" items="${dig.digParticipants }">
			<p><c:out value="${oneParticipant.userName}"/></p>
		</c:forEach>
	</div>
	<c:if test="${dig.digCreator.id.equals(userId)}">
	<div>
	<h3>Add participant</h3>
	<form action="/digs/addUser/${dig.id}" method="POST">
	<input type="hidden" name="_method" value="put"/>
		<label for="email">New Participant Email: </label>
		<input type="text" name="email" class="form-control"/>
	<button type="submit">Add</button>
	</form>
	</div>
	</c:if>
	<h3>Start Date</h3>
	<p>
		<fmt:formatDate type="date" value="${dig.startDate }" pattern="MMM dd YYYY"/>
	</p>
	<h3>End Date</h3>
	<p>
		<fmt:formatDate type="date" value="${dig.endDate }" pattern="MMM dd YYYY"/>
	</p>
	<div>
	<h3>Artifacts</h3>
	<p><a href="/artifacts/new">Add new artifact</a></p>
	</div>
</body>
<script>
	function showHide() {
		let content = document.getElementById("participantList");
		if (content.style.display === "none") {
			content.style.display = "block";
		} else {
			content.style.display = "none";
		}
	}
</script>
</html>