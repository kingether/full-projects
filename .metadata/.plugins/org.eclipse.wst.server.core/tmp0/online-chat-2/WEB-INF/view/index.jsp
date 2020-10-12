<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp">
	<jsp:param value="Hello" name="title" />
	<jsp:param value="index" name="page"/>
</jsp:include>

<h1>Just a simple online chat application.</h1>

<p>This project is to showcase Spring, Spring Web MVC, JSPs and
	JSTL. Based on Maven, Hibernate, HSQLDB and Twitter Bootstrap.</p>

<p>This project contains embedded HSQL database, which destroys all
	data at shutdown. If you want different database, see README.MD.
	Demo database will be re-initialized each day.</p>
	
<strong>You can use these credentials to login (username / password):</strong>

<ul>
	<li>admin / admin</li>
	<li>guest / guest</li>
	<li>You can also register a new user</li>
</ul>

<p>
Anonymous user can see all chatrooms, but cannot go inside chatroom and post messages.

Authenticated user can post messages.

Admin can manage users - see all users, delete users, edit users.
</p>


<br />

<jsp:include page="layout/footer.jsp" />
