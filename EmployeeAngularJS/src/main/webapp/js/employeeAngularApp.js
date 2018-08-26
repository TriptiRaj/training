/**
 *	Javascript for Employee Angular Application 
 */

 var app = angular.module('myApp', ['ui.router']);
 app.controller ('myController', function($scope, $http, $state){	 
	 $state.go("welcome");
 });

 app.controller ('searchEmpListController', function($scope, $stateParams){	 
	 
	 if($scope.employeeList!=undefined){
		 $scope.employeeList = angular.fromJson($stateParams.employee);
	 }
 });
 
 app.controller ('addEmpController', function($scope, $http, $state, $stateParams){	 
	 $http.get("/EmployeeAngularJS/getOrganisations")
	 .then(
	     function(response){
	    	 $scope.organisationList = response.data;
	     }), 
	    function(response){
	    	 alert("No organisations found");
	    }
	 
	 	
	 
	 $scope.populateDepartments = function (){
	 	//Fetch Departments List for selected organisation Id
	 	$http.post("/EmployeeAngularJS/getDepartmentsForOrganisation",$scope.selectedOrg.organisationId)
		.then(
		    function(response){
		    	$scope.departmentList = response.data;
		    	})
	 		},
		    function(response){
		    	 alert("No Departments found for given organisations");
		    };
     //Add Employee
	 $scope.addEmployee = function (){
		 var inputData = {
 				organisationId : $scope.selectedOrg.organisationId,
				departmentId : $scope.selectedDept.departmentId,
				employeeFullName : $scope.employeeFullName
		 		}
		 $http.post("/EmployeeAngularJS/addEmployee",inputData)
	 	 .then(
 	      function(response){
 	    	  $state.go("addEmpSuccessPage");
 	      	    })
		    },
		  function(response){
			   	 alert("Employee not added successfully");
		    };

	 $scope.passedEmployeeList = {}
	 if ( $stateParams.employeeList != undefined ) {
		 $scope.passedEmployeeList = angular.fromJson($stateParams.employeeList); 
	 }
			    
	 $scope.searchEmpByOrgAndDept = function (){
		 var employee = {	 
				 organisationId : $scope.selectedOrg.organisationId,
				 departmentId : $scope.selectedDept.departmentId
			 }
		 $http.post("/EmployeeAngularJS/searchByOrgAndDept", employee)
		.then(
 	     function(response){
		    	 var employeeList = angular.toJson(response.data);
		    	 $state.go("displayMultipleEmpDetails", {"employeeList":employeeList});
		     })
		  },
		 function(response){
		   	 alert("Employee not searched successfully");
		 }; 
 });

 app.controller ('searchEmpController', function($scope, $http, $state, $stateParams){	 
	 $scope.passedEmployee = {}
	 $scope.searchByEmpId = function(){
		 $state.go("searchByEmpId");
	 }
	 
	 if ( $stateParams.employeeee != undefined ) {
		 $scope.passedEmployee = angular.fromJson($stateParams.employeeee); 
	 }
	 
	 $scope.searchEmployeeById = function (){
		 $http.post("/EmployeeAngularJS/searchByEmpId",$scope.employeeId)
		 .then(
		 function(response){
		   	 var employeeee = angular.toJson(response.data);
		   	 $state.go("displayEmpDetails", {"employeeee":employeeee});
		  	})
	 	 },
		 function(response){
		   	 alert("Employee not searched successfully");
	 	 }; 

 	 $scope.populateDepartments = function (){
 		 //Fetch Departments List for selected organisation Id
 		 $http.post("/EmployeeAngularJS/getDepartmentsForOrganisation",$scope.selectedOrg.organisationId)
 		 .then(
	     function(response){
	    	 	$scope.departmentList = response.data;
		 	})
  		 },
		 function(response){
  			 	alert("No Departments found for given organisations");
		 };		    
		    
	 $scope.enterDetailsToUpdate = function (){	 
    	 $http.get("/EmployeeAngularJS/getOrganisations")
    	 .then(
	     function(response){
	    	 $scope.organisationList = response.data;
	     }), 
	    function(response){
		    	 alert("No organisations found");
		    }
	    }
		    
	$scope.searchToUpdate = function (){
		 $http.post("/EmployeeAngularJS/searchByEmpId",$scope.employeeId)
		 .then(
		 function(response){
		    	 var employee = angular.toJson(response.data);
		    	 $state.go("detailsToUpdate", {"employeeee":employee});
		     })
   		 },
	     function(response){
		    	 alert("Employee not searched successfully");
		 }; 
		    

	 $scope.updateEmployeeDetails = function (){
		 var employee = {
				 employeeId : $scope.passedEmployee.employeeId,
				 employeeFullName : $scope.passedEmployee.employeeFullName,
				 organisationId : $scope.selectedOrg.organisationId,
				 departmentId : $scope.selectedDept.departmentId
		 }
		 $http.post("/EmployeeAngularJS/updateEmployeeDetails",employee)
		 .then(
		  function(response){
		    	 $state.go("updateEmpSuccessPage")//, {"employee":employee});
		  		})
	 	 },
		 function(response){
	 		 	 alert("Employee not searched successfully");
		 }; 

	 $scope.deleteEmployee = function(){
		 $http.post("/EmployeeAngularJS/deleteEmployee",$scope.passedEmployee.employeeId)
		 .then(
	     function(response){
	    	 $state.go("deleteEmployee");
	     	})
 		 },
 		 function(response){
 			 alert("Employee not searched successfully");
 		 };
 		 
	 $scope.searchByOrgAndDept = function(){
		 $state.go("searchByOrgAndDept");
	 }
 });
 
 app.config(function($stateProvider, $urlRouterProvider) {	 
	 $stateProvider
	 .state('displayAddEmpPage', {
	     url : '/displayAddEmpPage',
	     templateUrl : '../views/addEmployee.html',
	     controller: 'addEmpController'	 
	 }).state('addEmpSuccessPage', {
	     url : '/addEmpSuccessful',
	     templateUrl : '../views/addEmployeeSuccessful.html'
	 }).state('displaySearchEmpPage', {
	     url : '/searchEmployee',
	     templateUrl : '../views/searchEmployee.html',
	     controller : 'searchEmpController'
	 }).state('searchByEmpId', {
	     url : '/searchByEmpId',
	     templateUrl : '../views/searchByEmpId.html',
	     controller : 'searchEmpController'
	 }).state('searchByOrgAndDept', {
	     url : '/searchByOrgAndDept',
	     templateUrl : '../views/searchByOrgAndDept.html',
	     controller : 'addEmpController'
	 }).state('displayEmpDetails', {
	     url : '/displayEmpDetails/:employeeee',
	     templateUrl : '../views/displayEmpDetails.html',
	     controller : 'searchEmpController'
	 }).state('displayMultipleEmpDetails', {
	     url : '/displayMultipleEmpDetails/:employeeList',
	     templateUrl : '../views/displayMultipleEmpDetails.html',
	     controller : 'addEmpController'
	 }).state('displayUpdateEmpPage', {
	     url : '/updateEmployee', 
	     templateUrl : '../views/updateEmployee.html',
		 controller : 'searchEmpController'
	 }).state('enterDetailsToUpdate', {
	     url : '/enterDetails/:employee/:organisationList', 
	     templateUrl : '../views/enterDetailsToUpdate.html',
		 controller : 'searchEmpController'			 
	 }).state('detailsToUpdate', {
	     url : '/displayEmpDetails/:employeeee',
	     templateUrl : '../views/detailsToUpdate.html',
	     controller : 'searchEmpController'
	 }).state('updateEmpSuccessPage', {
	     url : '/updateEmpSuccessful',
	     templateUrl : '../views/updateEmployeeSuccessful.html'
	 }).state('deleteEmployee', {
	     url : '/deleteEmployee',
	     templateUrl : '../views/deleteEmployeeSuccessful.html'
	 }).state('welcome', {
		 url : "/welcome",
		 templateUrl : '../views/employeeHomePage.html',
		 controller : 'myController'
	 });
	 
});