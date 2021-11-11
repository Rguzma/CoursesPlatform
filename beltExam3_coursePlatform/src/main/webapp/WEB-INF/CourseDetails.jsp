
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
<title>Course Details</title>
</head>
<body>
	<div>
 	<h1>
 		${course.name } with ${instructor.name }
 	</h1>
 	<fieldset>
 	<p>
 	Day: ${course.dayoftheweek}<br/></p>
 	<p>
 	Cost:$ ${course.price }<br/></p>
 	<p>
 	${course.description }<br/>
 	</p>
 	</div>
 	</fieldset>
 	<fieldset>
 	<div>
 	<h3>
 	Students
 	</h3>
 	<ul>
 		<c:forEach items="${studentsincourse}" var="stdentsin">
 		<li>
 			<c:out value="${stdentsin.name}"/> <c:out value="${stdentsin.email}"/>
 		</li>
 		</c:forEach>
 	</ul>
 	</div>
 	
 	
 	<div>
 		<div>
 			<h2>
 				Add Students to Course
 			</h2>
 			
 			<h3>
 			Add New Student
 			</h3>
 			<form:form method="POST" action="/add/student/${course.idcourse}/${instructor.idinstructor}" modelAttribute="addstudent">
					<div>
					<form:label path="name"> Student Name: </form:label>
					<form:errors path="name"/>
					<form:input  type="text" path="name" />
				</div>
				<div>
					<form:label path="email"> Email: </form:label>
					<form:errors path="email"/>
					<form:input  type="email" path="email" />
				</div>
				<div>
					<button type="submit">
						Add Student
					</button>
				</div>
		</form:form>
 			
 		</div>
 		
 		
 		<br>
 		
 		<h3>
 		Add Existing Student
 		</h3>
 		<div>
 		<form:form method="POST" action="/add/existing/student/${course.idcourse}/${instructor.idinstructor}" modelAttribute="stdentsnot">
					<div>
					<form:label path="idstudent"> Choose Student: </form:label>
					<form:errors path="idstudent"/>
					<form:select   path="idstudent" >
						<c:forEach items="${studentsnotincourse}" var="stdentsnot">
							<option value="${stdentsnot.idstudent}" >  ${stdentsnot.name} ${stdentsnot.email}  </option>
						</c:forEach>
					</form:select>
				</div>
				<div>
					<button type="submit">
						Add Student
					</button>
				</div>
		
		
		
		</form:form>
		</div>
 		
 	</div>
 	</fieldset>
 	<br/>
				<div>
				<a href="/home">Cancel</a>
				</div>

</body>
</html>