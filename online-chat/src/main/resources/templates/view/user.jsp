<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp">
	<jsp:param value="Add new user" name="title" />
	<jsp:param value="usersAdd" name="page" />
</jsp:include>

<h2>New user:</h2>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form commandName="user">
	<div class="form-group" style="width: 200px">
		<form:errors path="name" /><br />
		<form:input path="name" cssClass="form-control" placeholder="Name" /><br />
		<form:errors path="password" /><br />
		<form:password path="password" cssClass="form-control" placeholder="Password" /><br />
		<input type="submit" class="btn btn-primary" />
	</div>
</form:form>

<jsp:include page="layout/footer.jsp" />













