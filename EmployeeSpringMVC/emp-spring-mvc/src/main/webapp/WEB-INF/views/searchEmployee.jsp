<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

function toggleDiv(divToShowId, divToHideId){
	divToShow = document.getElementById(divToShowId);
	divToHide = document.getElementById(divToHideId);
	
	divToShow.style.visibility = "visible";
	divToHide.style.visibility = "hidden";
}

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
<title>Search Employee Details</title>
</head>
<body>
	<form:form modelAttribute="employee">
		<input type="button" onclick="toggleDiv('searchByEmpId','searchByOrgAndDeptId')" value="Search By Employee Id">
		<input type="button" onclick="toggleDiv('searchByOrgAndDeptId', 'searchByEmpId')" value="Search By Employee Details">
		<input type="submit" value="Back to Home Page" formaction=".">
		<div id="searchByEmpId" style="visibility: hidden;">
			<form:label path="employeeId">Employee Id :</form:label>
			<form:input path="employeeId"/>
			<input type="submit" value="Search" formaction="searchByEmpId">
		</div>
		<div id="searchByOrgAndDeptId" style="visibility: hidden;">
			<form:label path="organisationId">Organisation : </form:label>		
			<form:select id = "organisationList" path="organisationId" onchange="populateDepartments()">
				<form:option value="">Select Organisation</form:option>
			    <form:options items="${organisationList}" itemValue="organisationId" itemLabel="organisationName" />
			</form:select>
			<br>
	
			<form:label path="departmentId">Department :</form:label>
			<form:select id = "departmentList" path="departmentId">
				<form:option value="">Select Department</form:option>
			</form:select>		
			<input type="submit" value="Search" formaction="searchByOrgAndDept">
					
		</div>

		<c:choose>
			<c:when test="${employee!=null && employee.employeeId!=null}">
				<table style="style="border-color: red;">
					<tr>
						<td>Employee Id</td>
						<td> Employee Name</td>
					</tr>
					
					<tr>
						<td>
							<form:input path="employeeId" disabled="true"/>
						</td>
						<td>
							<form:input path="employeeFullName" disabled="true"/>
						</td>
					</tr>
				</table>			
			</c:when>
			<c:when test="${employeeList!=null && fn:length(employeeList)>0}">
				<table style="border-color: red;">
					<tr>
						<td>Employee Id</td>
						<td> Employee Name</td>
					</tr>
					<c:forEach var = "currentEmployee" items="${ employeeList}">
					<tr>
						<td>
							<c:out value="${currentEmployee.employeeId }"></c:out>
						</td>
						<td>
							<c:out value="${currentEmployee.employeeFullName }"></c:out>
						</td>
					</tr>
					</c:forEach>
				</table>			
			</c:when>
			<c:otherwise>
				No Employee to display
			</c:otherwise>
		</c:choose>
	</form:form>
</body>
</html>