var app = angular.module("app",['ngRoute','oc.lazyLoad','chieffancypants.loadingBar',
                                'ngAnimate','toastr','ui.bootstrap']);
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
                })
            })
        }
    }
});

function getVersion(){
    return new Date().getTime();
}