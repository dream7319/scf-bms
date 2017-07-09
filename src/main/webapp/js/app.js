var app = angular.module("index",['ngRoute']);
app.config(['$routeProvider',function ($routeProvider) {
    $routeProvider.when('/',{templateUrl:'/common/body.html?t='+getVersion(),controller:userControllerList})
        .when('/user/list',{templateUrl:'/common/foot.html'});
}]);

function userControllerList() {

}

function getVersion(){
    return new Date(1487552899999).getTime();
}