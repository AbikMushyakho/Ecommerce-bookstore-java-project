<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<%
HttpSession ses =request.getSession(false);
String Cname = (String) ses.getAttribute("companyName");
%>
<h2>Hello World!</h2>
</body>
</html>
