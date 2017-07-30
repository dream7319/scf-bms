app.config(['$routeProvider',function ($routeProvider) {
    $routeProvider.when("/",{templateUrl:'/views/role/lists.html?t='+getVersion(),controller:roleControllerList})
        .when("/role/list",{templateUrl:'/views/role/lists.html?t='+getVersion(),controller:roleControllerList})

}]);

function roleControllerList() {
}

function getVersion(){
    return new Date(1487552899999).getTime();
}
