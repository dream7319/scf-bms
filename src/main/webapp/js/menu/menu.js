app.config(['$routeProvider',function ($routeProvider) {
    $routeProvider.when("/",{templateUrl:'/views/menu/lists.html?t='+getVersion(),controller:'menuControllerList'})
        .when("/menu/list",{templateUrl:'/views/menu/lists.html?t='+getVersion(),controller:'menuControllerList'})
        .when("/menu/edit/:id",{templateUrl:'/views/menu/edit.html?t='+getVersion(),controller:'editCtrl'})
        .when("/menu/add",{templateUrl:'/views/menu/add.html?t='+getVersion(),controller:'addCtrl'})
        .otherwise({redirectTo:'/'});
}]);

app.controller('menuControllerList',['$scope','$http','toastr','$location','$route',function ($scope,$http,toastr,$location,$route) {
    //全选
    $('table th input:checkbox').on('click' , function(){
        var that = this;
        $(this).closest('table').find('tr > td:first-child input:checkbox')
            .each(function () {
                this.checked = that.checked;
                $(this).closest('tr').toggleClass('selected');
            });
    });

    $scope.query = function (pageNum) {
        $http.get('/api/menu/list',{params:{pageNum:pageNum,pageSize:10}}).then(function (response) {
            $scope.menus = response.data.data.records;
            console.log($scope.menus);
            $scope.pages = response.data.data.showPages;
            $scope.cur = response.data.data.num;
            $scope.prev = response.data.data.prev;
            $scope.next = response.data.data.next;
            $scope.pageCount=response.data.data.pageCount;
            $scope.pageSize = 10;
        },function (response) {
            toastr.error('查询失败');
        });
    }
    $scope.query(1);
}]);

app.controller('editCtrl',['$scope','$http','$routeParams','$ocLazyLoad','$location','toastr',function ($scope,$http,$routeParams,$ocLazyLoad,$location,toastr) {


}]);

app.controller('addCtrl',['$scope','$http','$ocLazyLoad','$location','toastr',function ($scope,$http,$ocLazyLoad,$location,toastr) {

}]);