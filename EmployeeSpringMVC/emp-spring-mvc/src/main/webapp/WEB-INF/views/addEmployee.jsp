<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

function populateDepartments(){
	var selectedOrgId = $('#organisationList').val();
       $.ajax({
       	url	 : "getDepartmentsForOrganisation",
       	data : "organisationId="+selectedOrgId,
       	type : "GET",
       	success: function(result){
    		var departmentDropdown =  document.getElementById('departmentList');
   			departmentDropdown.innerHTML="";
   			var position = 0;
        	result.forEach(function(entry){
        		var optionElement = document.createElement('option');
	       		optionElement.value = entry.departmentId;
	       		optionElement.label = entry.departmentName;
	       		departmentDropdown.appendChild(optionElement);
       	});
      		},
       	error : function(xhr, status, error) {
           alert(xhr.responseText); 
       	}
     });
}

</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>
</head>
<body>
	<form:form action="addEmployee" modelAttribute="employee">		 
		<form:label path="employeeFullName">Employee's Full Name :</form:label>
		<form:input path="employeeFullName" pattern = "[A-Za-z]{1-15}"  placeholder="Enter Employee's full name here" title="Full name must not contain numbers"></form:input><br>
		<form:label path="organisationId">Employee's Organisation : </form:label>		
		<form:select id = "organisationList" path="organisationId" onchange="populateDepartments()">
			<form:option value="">Select Organisation</form:option>
		    <form:options items="${organisationList}" itemValue="organisationId" itemLabel="organisationName" />
		</form:select>
		<br>

		<form:label path="departmentId">Employee's Department :</form:label>
		<form:select id = "departmentList" path="departmentId">
			<form:option value="">Select Department</form:option>
		</form:select>
		<br>
		
		<input type="submit" name="submit">
		<input type="submit" value="Back to Home Page" formaction=".">
	</form:form>
</body>
</html>