<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body class="container p-2 my-3" >


	<div class="d-flex">

		<h1>
			Welcome,
			<c:out value="${allUsers.userName}" />
		</h1>

		<a class="ms-5 mt-3" href="/logout">Log out</a>
	</div>


	<div>
		<p>All Projects</p>

		<a href="/assignments/new">
			<button>+ New Project</button>
		</a>
	</div>

	<div>
		<table class="table table-dark">
			<thead>
				<tr>
					<td>Project</td>
					<td>Team Lead</td>
					<td>Due Date</td>
					<td>Actions</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="a" items="${uAssignments}">
					<tr>
						<c:if test = "${a.lead.id!=user.id}">
			<td><a href="/assignments/${a.id}">${a.title}</a></td>
			<td><c:out value="${a.lead.userName}"></c:out></td>
			<td><fmt:formatDate value="${a.dueDate}" pattern="MMMM dd"/></td>
		    <td><a href="/dashboard/join/${a.id}">Join Team</a></td>
		    </c:if>
					</tr>

				</c:forEach>

			</tbody>
		</table>
	</div>

	<div>
		<p>Your Projects</p>
	</div>
	<div>
		<table class="table">
			<thead>
				<tr>
					<td>Project</td>
					<td>Lead</td>
					<td>Due Date</td>
					<td>Actions</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="a" items="${aAssignments}">
					<tr>
						<td><a href="/assignments/${a.id}">${a.title}</a></td>
			<td><c:out value="${a.lead.userName}"></c:out></td>
			<td><fmt:formatDate value="${a.dueDate}" pattern="MMMM dd"/></td>
			<c:if test = "${a.lead.id == allUsers.id}">
		       <td><a href="/assignments/edit/${a.id}">Edit Project</a></td>
		    </c:if>
		    <c:if test = "${a.lead.id != allUsers.id}">
		       <td><a href="/dashboard/leave/${a.id}">Leave Team</a></td>
		    </c:if>
					</tr>

				</c:forEach>

			</tbody>
		</table>
	</div>


</body>
</html>