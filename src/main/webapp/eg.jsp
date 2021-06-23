<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<html>
<head>
<title>Tag Example</title>
</head>
<body>
<h1>Welcome to practise page</h1>
	<%
    HttpSession session =request.getSession();
    String Cname = (String) session.getAttribute("companyName");
    out.println("hello" + Cname);
    %>
Name of company:
<c:out value="$(Cname)"/>
</body>
</html>