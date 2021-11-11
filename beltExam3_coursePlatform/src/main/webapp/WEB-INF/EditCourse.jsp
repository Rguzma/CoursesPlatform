
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
<title>Create Course</title>
</head>
<body>

<h1>
Create a Course
</h1>
<div>
<form:form method="POST" action="/edit/course/${idcourse}" modelAttribute="editcourse">
			<fieldset>
				<legend>
					New Course
				</legend>
				<div>
					<form:label path="name"> Name: </form:label>
					<form:errors path="name"/>
					<form:input type="text" path="name" name="name" value="${course.name}"/>
				</div>
				<div>
					<form:label path="price"> Price: </form:label>
					<form:errors path="price"/>
					<form:input type="number" path="price" name="price" value="${course.price}"/>
				</div>
				<div>
					<form:label path="dayoftheweek"> Day Of The Week: </form:label>
					<form:errors path="dayoftheweek"/>
					<form:select path="dayoftheweek">
						<optgroup label="Days">
							<option>Monday</option>
							<option>Tuesday</option>
							<option>Wednesday</option>
							<option>Thursday</option>
							<option>Friday</option>
						</optgroup>
					</form:select>
				</div>
				<div>
					<form:label path="time"> Time: </form:label>
					<form:errors path="time"/>
					<form:select path="time">
						<optgroup label="Morning">
							<option>07:00 AM</option>
							<option>08:00 AM</option>
							<option>09:00 AM</option>
							<option>10:00 AM</option>
							<option>11:00 AM</option>
						</optgroup>
						<optgroup label="Aftrenoon">
							<option>01:00 PM</option>
							<option>02:00 PM</option>
							<option>03:00 PM</option>
							<option>04:00 PM</option>
							<option>05:00 PM</option>
						</optgroup>
					</form:select>
				</div>
				<div>
					<form:label path="description"> Description: </form:label>
					<form:errors path="description"/>
					<form:textarea path="description" name="description" value="${course.description}" />
				</div>
				<div>
					<button type="submit">
						Submit
					</button>
				</div>

				<div>
					<c:out value="${loginErrorMessage}"></c:out>
				</div>
			</fieldset>
		</form:form>
	</div>
	<div>
	<form:form method="POST" action="/delete/course/${idcourse}" modelAttribute="deletecourse">
	<div>
	<button type="submit">Delete</button>
	</div>
	</form:form>
	</div>
					<br/>
				<div>
				<a href="/home">Cancel</a>
				</div>

</body>
</html>