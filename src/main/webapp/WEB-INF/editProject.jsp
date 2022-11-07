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

<h1>Edit Assignment</h1>
<a href="/dashboard"><button>Cancel</button></a>

<form:form action="/assignments/edit/${newAssignment.id}" method = "post" modelAttribute="newAssignment">
<input type="hidden" name="_method" value="put"/>
<form:input type="hidden" path="users"/>

	<form:label path="title">Assignment Title: </form:label>
	<form:errors path="title"/>
	<form:input path="title" value="${newAssignment.title}"/>
	
	<form:label path="description">Description: </form:label>
	<form:errors path="description"/>
	<form:textarea path="description" value="${newAssignment.description}"/>
	
	<form:label path="dueDate">Due Date: </form:label>
	<form:errors path="dueDate"/>
	<form:input type="date" path="dueDate" value="${newAssignment.dueDate}"/>

	<button>Submit</button>
</form:form>

</body>
</html>