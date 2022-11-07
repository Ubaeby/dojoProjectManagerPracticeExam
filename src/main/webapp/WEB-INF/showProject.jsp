<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Assignment Details</h1> <a href="/dashboard"> Back to Dashboard</a>

<div>
<p>Project:</p>
<p> <c:out value="${assignments.title}"></c:out></p>
</div>

<div>
<p>Description:</p>
<p><c:out value="${assignments.description}"/></p>
</div>

<div>
<p>Due Date:</p>

<p> <fmt:formatDate value="${assignments.dueDate}" pattern="M/d/YYYY"/></p>
</div>


<a href="/assignments/${assignments.id}/tasks">See tasks</a>
</body>
</html>