/**
 * Contains URL mappings for the application.
 */
 // this app is defined in employeeAngularApp.js
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