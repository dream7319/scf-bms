var loginApp = angular.module('login', ['ngRoute','ngCookies']);
loginApp.config(['$routeProvider',function($routeProvider) {
	$routeProvider.when('/',{templateUrl:'/views/login/login.html'})
			.when('/login/register', {templateUrl:'/views/login/register.html'})
			.when('/login/forget',{templateUrl:'/views/login/forget.html'});
}]);

loginApp.controller('loginController', ['$scope','$http','$cookies','$window',function($scope,$http,$cookies,$window){

	var token = $cookies.getObject("token");

	$scope.hide=function(){
		$("loginusername").tooltip('destory');
		$("loginpassword").tooltip('destory');
	}

	$scope.login = function(user){
		if(user == undefined){
			$("#loginusername").tooltip({title:"请输入用户名",placement:"auto"}).tooltip('show');
		}

		if(user.username == undefined){
			$("#loginusername").tooltip({title:"请输入用户名",placement:"auto"}).tooltip('show');
		}

		$http.post('/api/login',user).then(function(response){
			var status = response.status;
			if(status != 200){
				$cookies.remove('token');
				$("#loginpassword").tooltip({title:response.message,placement:"auto"}).tooltip('show');
			}else{
				$cookies.putObject("token",response.data,{expires:new Date(new Date().getTime+1000*60)});
				$window.location.href="/views/index.html";
			}
		},function(data){

		});
	}

	if(token != undefined){
		console.log(token.data);
	}
}]);