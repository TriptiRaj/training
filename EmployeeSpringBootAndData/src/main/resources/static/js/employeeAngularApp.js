/**
 *	Angular JS Controller for Employee Angular Application. 
 */

 var base_url = window.location.protocol+"//"+window.location.host+"/"+window.location.pathname.split("/")[1];
 
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
	 $http.get(base_url+"/getOrganisations")
	 .then(
	     function(response){
	    	 $scope.organisationList = response.data;
	     }), 
	    function(response){
		 	//Error handling if any
	    }
	 
	 	
	 
	 $scope.populateDepartments = function (){
	 	//Fetch Departments List for selected organisation Id
	 	$http.post(base_url+"/getDepartmentsForOrganisation",$scope.selectedOrg.organisationId)
		.then(
		    function(response){
		    	$scope.departmentList = response.data;
		    	})
	 		},
		    function(response){
	 			//Error handling if any
		    };
     //Add Employee
	 $scope.addEmployee = function (){
		 var inputData = {
 				organisationId : $scope.selectedOrg.organisationId,
				departmentId : $scope.selectedDept.departmentId,
				employeeFullName : $scope.employeeFullName
		 		}
		 $http.post(base_url+"/addEmployee",inputData)
	 	 .then(
 	      function(response){
 	    	  $state.go("addEmpSuccessPage");
 	      	    })
		    },
		  function(response){
		    	//Error handling if any
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
		 $http.post(base_url+"/searchByOrgAndDept", employee)
		.then(
 	     function(response){
		    	 var employeeList = angular.toJson(response.data);
		    	 $state.go("displayMultipleEmpDetails", {"employeeList":employeeList});
		     })
		  },
		 function(response){
			//Error handling if any
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
		 $http.post(base_url+"/searchByEmpId",$scope.employeeId)
		 .then(
		 function(response){
		   	 var employeeee = angular.toJson(response.data);
		   	 $state.go("displayEmpDetails", {"employeeee":employeeee});
		  	})
	 	 },
		 function(response){
	 		//Error handling if any
	 	 }; 

 	 $scope.populateDepartments = function (){
 		 //Fetch Departments List for selected organisation Id
 		 $http.post(base_url+"/getDepartmentsForOrganisation",$scope.selectedOrg.organisationId)
 		 .then(
	     function(response){
	    	 	$scope.departmentList = response.data;
		 	})
  		 },
		 function(response){
  			 	//Error handling if any
		 };		    
		    
	 $scope.enterDetailsToUpdate = function (){	 
    	 $http.get(base_url+"/getOrganisations")
    	 .then(
	     function(response){
	    	 $scope.organisationList = response.data;
	     }), 
	     function(response){
    		 //Error handling if any
		    }
	    }
		    
	$scope.searchToUpdate = function (){
		 $http.post(base_url+"/searchByEmpId",$scope.employeeId)
		 .then(
		 function(response){
		    	 var employee = angular.toJson(response.data);
		    	 $state.go("detailsToUpdate", {"employeeee":employee});
		     })
   		 },
	     function(response){
   			 //Error handling if any
		 }; 
		    

	 $scope.updateEmployeeDetails = function (){
		 var employee = {
				 employeeId : $scope.passedEmployee.employeeId,
				 employeeFullName : $scope.passedEmployee.employeeFullName,
				 organisationId : $scope.selectedOrg.organisationId,
				 departmentId : $scope.selectedDept.departmentId
		 }
		 $http.post(base_url+"/updateEmployeeDetails",employee)
		 .then(
		  function(response){
		    	 $state.go("updateEmpSuccessPage")//, {"employee":employee});
		  		})
	 	 },
		 function(response){
	 		//Error handling if any
		 }; 

	 $scope.deleteEmployee = function(){
		 $http.post(base_url+"/deleteEmployee",$scope.passedEmployee.employeeId)
		 .then(
	     function(response){
	    	 $state.go("deleteEmployee");
	     	})
 		 },
 		 function(response){
 			//Error handling if any
 		 };
 		 
	 $scope.searchByOrgAndDept = function(){
		 $state.go("searchByOrgAndDept");
	 }
 });
 