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
		if(user == undefined || user.username == undefined){
			$("#loginusername").tooltip({title:"请输入用户名",placement:"auto"}).tooltip('show');
		}else{
			$http.post('/api/login',user).then(function(response){
                var status = response.data.status;
                if(status == 201){
                    $cookies.remove('token');
                    $("#loginpassword").tooltip({title:response.data.message,placement:"auto"}).tooltip('show');
                }else{
                    //半个小时
                    $cookies.putObject("token",response.data,{expires:new Date(new Date().getTime()+1000*60*30)});
                    $window.location.href="/views/index.html";
                }
            },function(data){

            });
        }
	}

	if(token != undefined){
		$scope.login(token);
	}
}]);

/**
 * 判断密码一致
 */
loginApp.directive('pwdEquals',function () {
	return {
		require:'ngModel',
		link:function ($scope,elem,attrs,ngModelCtrl) {
			function validatePwdEquals(value) {
				var valid = (value === $scope.$eval(attrs.pwdEquals));
				ngModelCtrl.$setValidity('pwdEquals',valid);
				return valid ? value : undefined;
            }
            ngModelCtrl.$parsers.push(validatePwdEquals);
			ngModelCtrl.$formatters.push(validatePwdEquals);
			$scope.$watch(attrs.pwdEquals,function () {
				ngModelCtrl.$setViewValue(ngModelCtrl.$viewValue);
            })
        }
	}
});

loginApp.controller('registerController',['$scope','$http','$location',function ($scope,$http,$location) {
	console.log("registerController");

	$scope.submitted = false;
	$scope.register = function (user) {
        //表单正常提交
        if($scope.registerForm.$valid){
            //正常提交表单
            if(user.userProtocol){
                $http.post('/api/user/add',user).then(function (response) {
                    $location.path("/");
                },function (response) {

                });
            }else{
                alert("请选择用户协议");
            }
        }else{
            $scope.submitted = true;
        }
    }
}]);

loginApp.controller('forgetController',['$scope','$http',function ($scope,$http) {
    console.log("forgetController");
	$scope.forgetPwd = function(email){
		$http.get('/api/forget?email='+email).then(function (response) {
			
        },function (response) {
			
        });
	}
}]);