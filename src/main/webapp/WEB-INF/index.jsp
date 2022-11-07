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

<h1>Project Manager</h1>

<div>
	<h3>Register</h3>
	
	<form:form action="/register" method = "post" modelAttribute="newUser">
		<form:label path="userName">User Name: </form:label>
		<form:errors path="userName"/>
		<form:input path="userName"/>
		
		<form:label path="email">Email: </form:label>
		<form:errors path="email"/>
		<form:input path="email"/>
		
		<form:label path="password">Password: </form:label>
		<form:errors path="password"/>
		<form:input path="password"/>
		
		<form:label path="confirm">Confirm PW: </form:label>
		<form:errors path="confirm"/>
		<form:input path="confirm"/>
		
		<button>Register</button>
	</form:form>
</div>


<div>

<h3>Log in</h3>
<form:form action="/login" method="post" modelAttribute="newLogin">
<form:label path="email">Email: </form:label>
		<form:errors path="email"/>
		<form:input path="email"/>
		
		<form:label path="password">Password: </form:label>
		<form:errors path="password"/>
		<form:input path="password"/>
		
		<button>Log In</button>
</form:form>

</div>
</body>
</html>