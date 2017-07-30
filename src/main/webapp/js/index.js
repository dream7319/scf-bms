app.config(['$routeProvider',function ($routeProvider) {
    $routeProvider.when('/',{templateUrl:'/common/body.html?t='+getVersion()});
}]);

function getVersion(){
    return new Date(1487552899999).getTime();
}