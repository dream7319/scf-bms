app.config(['$routeProvider',function ($routeProvider) {
    $routeProvider.when("/",{templateUrl:'/views/role/lists.html?t='+getVersion(),controller:'roleControllerList'})
        .when("/role/list",{templateUrl:'/views/role/lists.html?t='+getVersion(),controller:'roleControllerList'})
        .when("/role/edit/:id",{templateUrl:'/views/role/edit.html?t='+getVersion(),controller:'editCtrl'})
        .when("/role/add",{templateUrl:'/views/role/add.html?t='+getVersion(),controller:'addCtrl'})
        .when("/role/detail/:id",{templateUrl:'/views/role/detail.html?t='+getVersion(),controller:'detailCtrl'})
        .otherwise({redirectTo:'/'});
}]);

app.controller('roleControllerList',['$scope','$http','toastr','$location','$route',function ($scope,$http,toastr,$location,$route) {

}]);

app.controller('detailCtrl',['$scope','$http','$routeParams',function ($scope,$http,$routeParams) {

}]);

app.controller('editCtrl',['$scope','$http','$routeParams','$ocLazyLoad','$location','toastr',function ($scope,$http,$routeParams,$ocLazyLoad,$location,toastr) {


}]);
app.controller('addCtrl',['$scope','$http','$ocLazyLoad','$location','toastr',function ($scope,$http,$ocLazyLoad,$location,toastr) {

}]);


function getVersion(){
    return new Date(1487552899999).getTime();
}
