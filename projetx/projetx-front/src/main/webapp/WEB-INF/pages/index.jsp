<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
</head>
<body>
	<%-- 	<form name="auth" action="<c:url value='j_spring_security_check' />" --%>
	<form action="/login" name="auth" method="POST">
		<label>Username: <input type="text" name="j_username"></label>
		<label>Password: <input type="password" name="j_password"></label>
		<input type="submit" value="login" />
	</form>
</body>
</html>


