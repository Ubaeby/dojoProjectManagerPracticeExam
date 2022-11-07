<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Project: <c:out value="${assignments.title}"></c:out> </h1>
<a href="/dashboard">Back to Dashboard</a>
<p> Project Lead: <c:out value="${assignments.lead.userName}"></c:out> </p>

<form:form action="/assignments/${assignments.id}/tasks" method="post" modelAttribute="task">

<form:label path="description">Add a task ticket for this team: </form:label>
<form:errors path="description"/>
<form:textarea path="description"/>

<button>Submit</button>
</form:form>


<c:forEach var="t"  items="${tasks}">
	<div>
	<p>Added by <c:out value="${t.creator.userName}"/> at <fmt:formatDate value="${t.createdAt}" pattern="h:maa MMM d"/>:</p>
	<p>
	<c:out value="${t.description}"></c:out>
	</p>
	</div>
</c:forEach>

</body>
</html>