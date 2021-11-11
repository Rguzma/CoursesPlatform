<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h1>Namaste, ${instructor.name}</h1>
	<h3>Course Schedule</h3>
	<table>
		<thead>
			<tr>
				<th>Class Name</th>
				<th>Instructor</th>
				<th>Weekday</th>
				<th>Price</th>
				<th>Time</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${courses}" var="c">
			<tr>
				<td>
					<a href="/courses/${c[0].idcourse}">
						<c:out value="${c[0].name}"/>
					</a>
					<a href="edit/course/${c[0].idcourse}">Edit</a>
					
				</td>
				<td>
					<c:out value="${c[1].name}"/>
				</td>
				<td>
					<c:out value="${c[0].dayoftheweek}"/>
				</td>
				<td>
					<c:out value="${c[0].price}"/>
				</td>
				<td>
					<c:out value="${c[0].time}"/>
				</td>
		   </tr>
			</c:forEach>
		</tbody>
	</table> 
	
	<div>
	<a href="create/newCourse"> Create New Course</a>
	
	</div>
	<br>
	<div>
	<a href="/reset">Log Out</a>
	</div>
</body>
</html>