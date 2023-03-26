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
		<h1>Viewing details of <c:out value="${dig.digName}"/></h1>
		<div>
			<p><a href="/logout">Logout</a></p>
			<p><a href="/home">Home</a></p>
		</div>
	</div>
	<p>Created by 
	<c:choose>
		<c:when test="${dig.digCreator.id.equals(userId)}">you</c:when>
		<c:otherwise><c:out value="${dig.digCreator.userName }"/></c:otherwise>
	</c:choose>
	</p>
	<hr>
	<div class="container row">
	<div class="col">
	<h3>Participants</h3>
		<button onclick="showHide()" class="btn btn-warning">View/Hide Participants</button>
		<div id="participantList">
		<table>
		<tbody>
			<c:forEach var="oneParticipant" items="${dig.digParticipants }">
			<tr>
				<td><c:out value="${oneParticipant.userName}"/></td>
				<c:choose>
					<c:when test="${dig.digCreator.id.equals(oneParticipant.id)}"><td class="text-italic">Dig admin</td></c:when>
					<c:otherwise>
						<td><form method="POST" action="/digs/${dig.id}/removeUser/${oneParticipant.id}"> 
						<input type="hidden" name="_method" value="put">
						<button class="btn btn-warning">Remove</button>
						</form></td>
					</c:otherwise>
				</c:choose>
			</tr>
			</c:forEach>
		</tbody>
		</table>
		</div>
	</div>
	<c:if test="${dig.digCreator.id.equals(userId)}">
	<div class="col">
		<h5>Add participant</h5>
		<p class="red-text"><c:out value="${error}"/></p>
		<form action="/digs/addUser/${dig.id}" method="POST">
		<input type="hidden" name="_method" value="put"/>
			<label for="email">New Participant Email: </label>
			<input type="text" name="email" class="form-control"/>
		<button type="submit" class="btn btn-warning">Add</button>
		</form>
	</div>
	</c:if>
	</div>
	<div class="container row">
	<h3>Duration</h3>
		<h5>Start Date</h5>
		<p>
			<fmt:formatDate type="date" value="${dig.startDate }" pattern="MMM dd YYYY"/>
		</p>
		<h5>End Date</h5>
		<p>
			<fmt:formatDate type="date" value="${dig.endDate }" pattern="MMM dd YYYY"/>
		</p>
	</div>
	<div class="m-3">
	<h3>Artifacts</h3>
	<button class="btn btn-warning" onclick="window.location.href ='/digs/${dig.id}/newArtifact'">Add new artifact</button>
	<c:forEach var="oneArtifact" items="${artifactList}" begin="0" end="4">
		<p><a href="/digs/${dig.id}/artifacts/${oneArtifact.id}"><c:out value="${oneArtifact.category}; ${oneArtifact.material}"/></a> | logged by <c:out value="${oneArtifact.artifactCreator.userName}"/> at <fmt:formatDate type="date" value="${oneArtifact.createdAt}" pattern="MMM dd YYYY hh:mma"/></p>
	</c:forEach>
	<p><a href="/digs/${dig.id}/allArtifacts">See more...</a></p>
	</div>
	<div>
		<button onclick="deleteDigAlert(${dig.id})">Delete Dig</button>
	</div>
<script src="/js/script.js"></script>
</body>
</html>