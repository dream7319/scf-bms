'use strict';
// Define `TrialApp` module
var app = angular.module('TrialApp', ['ui.router']);
// Define routers
app.config(function($stateProvider) {
	$stateProvider
	    .state('home', {
		    url: '/home',
		    templateUrl: 'demo2.html',
		    controller: 'MainController'
	    })
	    .state('1', { 
	    	url: '/Page1',
	    	templateUrl:'Page1.html',
	    	controller: function($scope, $state) {
	    		$scope.change = function() {
	    			$state.go('1.1');
	    		}
	    	}
	    })
	    .state('1.1', {
	    	url: '/Page1.1',
	    	templateUrl: 'Page1.1.html',
	    	controller: function($scope, $state) {
	    		$scope.change = function() {
	    			$state.go('1.1');
	    		}
	    	}
	    })
	    .state('1.1.1', {
	    	url: '/Page1.1.1',
	    	templateUrl: 'Page1.1.1.html',
	    })
	    .state('2', {
	    	url: '/Page2',
	    	templateUrl: 'Page2.html',
	    	controller: function($scope, $state) {
	    		$scope.change = function() {
	    			$state.go();
	    		}
	    	}
	    })
});
app.controller('MainController', function() {
     alert("Welcome to nested view demo!");
});