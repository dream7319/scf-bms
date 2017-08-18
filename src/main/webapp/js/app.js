var app = angular.module("app",['ngRoute','oc.lazyLoad','chieffancypants.loadingBar',
    'ngAnimate','toastr','ui.bootstrap','ngCookies']);
//全局拦截器
app.factory('myInteceptor',['$q','$rootScope','$window','$cookies','cfpLoadingBar',function ($q,$rootScope,$window,$cookies,cfpLoadingBar) {
    var requestInteceptor = {
        //通过实现 request 方法拦截请求: 该方法会在 $http 发送请求道后台之前执行
        request:function (config) {
            var tokenObj = $cookies.getObject("token");
            if(tokenObj == undefined){
                $window.location.href="/login.html";
                return config;
            }
            $rootScope.username = tokenObj.username;
            cfpLoadingBar.start();
            /*config.headers = config.headers || {};
             var token = $cookies.get('token');
             if (token) {
             config.headers['Authorization'] =tokenObj.username;
             }*/

            var aaa = $window.sessionStorage.getItem("aaa");

            console.log(JSON.parse(aaa).img);
            return config;
        },
        requestError:function () {
            console.log("请求错误");
        },
        //通过实现 response 方法拦截响应: 该方法会在 $http 接收到从后台过来的响应之后执行
        response:function (response) {
            cfpLoadingBar.complete();
            return response;
        },

        responseError:function (response) {
            cfpLoadingBar.complete();
            console.log(response.status);
            if(response.status == 500){
                $window.location.href="/error/500.html";
                return $q.reject(response);
            }else if(response.status == 201){
                $window.location.href="/login.html";
                return $q.reject(response);
            }else if(response.status == 404){
                $window.location.href="/error/404.html";
                return $q.reject(response);
            }
            return $q.reject(response);
        }
    }
    return requestInteceptor;
}]).config(['$httpProvider',function($httpProvider){
    $httpProvider.interceptors.push('myInteceptor');
}]);

app.config(['$ocLazyLoadProvider',function ($ocLazyLoadProvider) {
    $ocLazyLoadProvider.config({
        events:true,
        modules:[{
            name:'datetimepicker',
            insertBefore:'#ng_load_plugins_before',
            serie:true,
            files:[
                '/static/css/custom-switch.css',
                '/static/css/bootstrap-datetimepicker.min.css',
                '/static/js/bootstrap-datetimepicker.min.js',
                '/static/js/bootstrap-datetimepicker.zh-CN.js',
                '/static/js/custom-switch.js'
            ]
        },{
            name:'autocomplete',
            insertBefore:'#ng_load_plugins_before',
            serie:true,
            files:[
                '/static/css/jquery.autocomplete.css',
                '/static/js/jquery.autocomplete.min.js'
            ]
        },{
            name:'ztreePlugins',
            insertBefore:'#ng_load_plugins_before',
            serie:true,
            files:[
                '/static/css/zTreeStyle.css',
                '/static/js/jquery.ztree.core.min.js',
                '/static/js/jquery.ztree.excheck.min.js'
            ]
        }]
    });
}]).config(['cfpLoadingBarProvider',function(cfpLoadingBarProvider) {
    var svg= '<svg width="42" height="42" viewBox="0 0 42 42" xmlns="http://www.w3.org/2000/svg" stroke="#3498DB"><g fill="none" fill-rule="evenodd"><g transform="translate(1 1)" stroke-width="2"><circle stroke-opacity=".5" cx="18" cy="18" r="18"></circle><path d="M36 18c0-9.94-8.06-18-18-18" transform="rotate(96.1606 18 18)"><animateTransform attributeName="transform" type="rotate" from="0 18 18" to="360 18 18" dur="1s" repeatCount="indefinite"></animateTransform></path></g></g></svg>';
    cfpLoadingBarProvider.includeSpinner = true;
    cfpLoadingBarProvider.spinnerTemplate = '<div class="row"><div class="col-md-2 col-md-offset-5" style="padding-left: 80px;position: absolute;top: 50%;-webkit-transform: translateY(-50%);-moz-transform:  translateY(-50%);-ms-transform:  translateY(-50%);-o-transform:  translateY(-50%);transform:  translateY(-50%);">'+svg+'</div></div>';
}]);

app.directive('pwdEquals',function () {
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

app.directive('ngFocus', function () {
    var FOCUS_CLASS = "ng-focused";
    return{
        restrict:'A',
        require:'ngModel',
        link: function (scope, element, attrs,ctrl) {
            ctrl.$focused = false;
            element.bind('focus', function (evt) {
                element.addClass(FOCUS_CLASS);
                scope.$apply(function () {
                    ctrl.$focused = false;
                });
            }).bind('blur', function () {
                element.removeClass(FOCUS_CLASS);
                scope.$apply(function(){
                    ctrl.$focused = true;
                });
            })
        }
    }
});
app.controller('MenuController',['$scope','$cookies','$http','toastr','$window',function ($scope,$cookies,$http,toastr,$window) {

    var token = $cookies.getObject('token');
    if(token == undefined ){
        $window.location.href="/login.html";
        return false;
    }
    $http.get('/api/menu/user/'+token.id).then(function (response) {
        $scope.menus = response.data.menus;
    },function (response) {
        toastr.error("加载失败");
    });
}]);

app.controller('TopController',['$scope','$cookies','$window',function ($scope,$cookies,$window) {
    $scope.logout = function () {
        // delete $cookies['token'];
        // $cookies.remove('token');
        // console.log($cookies.getObject("token"));

        // console.log($cookies.get("token"));

        $cookies.remove("token");
        console.log($cookies.get("token"));
        var aaa = $window.sessionStorage.getItem("aaa");

        console.log(JSON.parse(aaa).id);

        var wsCache = new WebStorageCache();
        console.log(wsCache.get("test"));
        // $window.location.href="/login.html";
    }
}]);

function promptMsg(toastr,result,status) {
    if(result == 'success'){
        if(status){
            toastr.success('启用成功');
        }else{
            toastr.success('禁用成功');
        }
    }else{
        if(status){
            toastr.error('启用失败');
        }else{
            toastr.error('禁用失败');
        }
    }
}

function getVersion(){
    return new Date().getTime();
}