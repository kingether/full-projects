<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="WEB-INF/view/layout/header.jsp">
	<jsp:param value="Login" name="title" />
	<jsp:param value="login" name="page" />
</jsp:include>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty param.login_error}">
	<font color="red">Your login attempt was not successful, try
		again.<br />
	<br /> Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
	</font>
</c:if>
<form name="f" action="j_spring_security_check" method="post"
	style="width: 200px;">
	<h2>Please sign in</h2>
	<input type="text" name="j_username" placeholder="Username"
		class="form-control" /><br /> <input type="password"
		name="j_password" placeholder="Password" class="form-control" /><br />
	<input type="submit" name="submit" class="btn btn-primary btn-lg" />
</form>

<jsp:include page="WEB-INF/view/layout/footer.jsp" />