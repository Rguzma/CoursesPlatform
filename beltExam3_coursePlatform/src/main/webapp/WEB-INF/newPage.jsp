
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
<form:form method="POST" action="/new/course" modelAttribute="newcourse">
			<fieldset>
				<legend>
					New Course
				</legend>
				<div>
					<form:label path="name"> Name: </form:label>
					<form:errors path="name"/>
					<form:input type="text" path="name" name="name" />
				</div>
				<div>
					<form:label path="price"> Price: </form:label>
					<form:errors path="price"/>
					<form:input type="number" path="price" name="price" />
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
					<form:label path="time"> Price: </form:label>
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
					<form:textarea path="description" name="description" />
				</div>
				<div>
					<button type="submit">
						Submit
					</button>
				</div>
				<br/>

				<div>
					<c:out value="${loginErrorMessage}"></c:out>
				</div>
			</fieldset>
		</form:form>
		
						<div>
				<a href="/home">Cancel</a>
				</div>

</body>
</html>