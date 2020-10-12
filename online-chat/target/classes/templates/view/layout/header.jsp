<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
<title>${param.title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-2.0.3.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/" />">Online chat</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="${param.page == 'index' ? 'active' : ''}"><a href="<c:url value="/" />">Home</a></li>
					<li class="${param.page == 'chatroom' ? 'active' : ''}"><a href="<c:url value="/chatroom.html" />">New Chatroom</a></li>
					<li class="${param.page == 'list' ? 'active' : ''}"><a href="<c:url value="/chatroom/list.html" />">Chatroom List</a></li>
					<li class="${param.page == 'about' ? 'active' : ''}"><a href="<c:url value="/about.html" />">About</a></li>
					<security:authorize access="isAuthenticated()" var="loggedIn" />
					<c:choose>
						<c:when test="${loggedIn}">
							<li><a href="/logout">Logout ${pageContext.request.remoteUser}</a></li>
						</c:when>
						<c:otherwise>
							<li class="${param.page == 'login' ? 'active' : ''}"><a href="/login.jsp">Login</a></li>
							<li class="${param.page == 'usersAdd' ? 'active' : ''}"><a href="<c:url value="/users/add.html" />">Register new user</a></li>
						</c:otherwise>
					</c:choose>
					<security:authorize access="hasRole('ROLE_ADMIN')">
						<li class="${param.page == 'users' ? 'active' : ''}"><a href="<c:url value="/users.html" />">Users</a></li>
					</security:authorize>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<div class="container">
	
	<br><br><br>