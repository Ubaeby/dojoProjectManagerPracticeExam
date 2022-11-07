<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<h1>Create a Project</h1>

<form:form action="/assignments/new" method = "post" modelAttribute="newAssignment">
	<form:label path="title">Assignment Title: </form:label>
	<form:errors path="title"/>
	<form:input path="title"/>
	
	<form:label path="description">Description: </form:label>
	<form:errors path="description"/>
	<form:textarea path="description"/>
	
	<form:label path="dueDate">Due Date: </form:label>
	<form:errors path="dueDate"/>
	<form:input type="date" path="dueDate"/>

	<button>Create</button>
</form:form>

</body>
</html>