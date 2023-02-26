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
	<div>
		<h1>ArchaeoLog</h1>
		<h3>Digital Field Log for Archaeologists</h3>
	</div>
		<div class="container mt-5">
		<h1>Register</h1>
		<form:form action="/register" method="POST" modelAttribute="newUser">
			<div class="form-group">
				<form:label path="userName">User Name: </form:label>
				<form:input path="userName" class="form-control"/>
				<form:errors path="userName" class="text-danger"/>
			</div>
			<div class="form-group">
				<form:label path="email">Email: </form:label>
				<form:input path="email" class="form-control"/>
				<form:errors path="email" class="text-danger"/>
			</div>
			<div class="form-group">
				<form:label path="password">Password: </form:label>
				<form:input path="password" class="form-control" type="password"/>
				<form:errors path="password" class="text-danger"/>
			</div>
			<div class="form-group">
				<form:label path="confirm">Confirm password: </form:label>
				<form:input path="confirm" class="form-control" type="password"/>
				<form:errors path="confirm" class="text-danger"/>
			</div>
			<button type="submit" class="btn btn-primary">Register</button>
		</form:form>
	</div>
	<div class="container mt-5">
		<h1>Login</h1>
		<form:form action="/login" method="POST" modelAttribute="newLogin">
			<div class="form-group">
				<form:label path="email">Email: </form:label>
				<form:input path="email" class="form-control"/>
				<form:errors path="email" class="text-danger"/>
			</div>
			<div class="form-group">
				<form:label path="password">Password: </form:label>
				<form:input path="password" class="form-control" type="password"/>
				<form:errors path="password" class="text-danger"/>
			</div>
			<button type="submit" class="btn btn-primary">Login</button>
		</form:form>
	</div>
</body>
</html>