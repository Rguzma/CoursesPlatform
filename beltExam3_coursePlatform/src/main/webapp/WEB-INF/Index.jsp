
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
<title>Log in and Registration</title>
</head>
<body>
	<h1>
		Login and registration
	</h1>
	
	<p><form:errors path="instructor.*"/> </p>
	
			<form:form  method="POST" action="register/instructor" modelAttribute="newinstructor">
			<fieldset>
				<legend>
					Register as a new instructor
				</legend>                             


				<div>
					<form:label path="name"> Instructor Name: </form:label>
					<form:errors path="name"/>
					<form:input  type="text" path="name" name="name" />
				</div>
				<div>
					<form:label path="email"> Email: </form:label>
					<form:errors path="email"/>
					<form:input  type="email" path="email" name="email" />
				</div>
				<div>
					<form:label path="password"> Password: </form:label>
					<form:errors path="password"/>
					<form:input type="password" path="password" name="password" />
				</div>
				<div>
					<form:label path="confirmpassword"> Password confirmation: </form:label>
					<form:errors path="confirmpassword"/>
					<form:input type="password" path="confirmpassword" name="confirmpassword" />
				</div>
				<div>
					<button type="submit">
						Register
					</button>
				</div>

			</fieldset>
		</form:form>
		<form:form method="POST" action="/validate/instructor" modelAttribute="newlogin">
			<fieldset>
				<legend>
					Login
				</legend>
				<div>
					<form:label path="email"> Email: </form:label>
					<form:input type="email" path="email" name="email" />
					<form:errors path="email"/>
				</div>
				<div>
					<form:label path="password"> Password: </form:label>
					<form:input type="password" path="password" name="password" />
					<form:errors path="password" class="text-danger" />
				</div>
				<div>
					<button type="submit">
						Login
					</button>
				</div>

			</fieldset>
		</form:form>

</body>
</html>