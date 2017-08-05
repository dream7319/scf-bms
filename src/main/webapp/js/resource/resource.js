app.config(['$routeProvider',function ($routeProvider) {
    $routeProvider.when("/",{templateUrl:'/views/resource/lists.html?t='+getVersion(),controller:'resourceControllerList'})
        .when("/resource/list",{templateUrl:'/views/resource/lists.html?t='+getVersion(),controller:'resourceControllerList'})
        .when("/resource/edit/:id",{templateUrl:'/views/resource/edit.html?t='+getVersion(),controller:'editCtrl'})
        .when("/resource/add",{templateUrl:'/views/resource/add.html?t='+getVersion(),controller:'addCtrl'})
        .otherwise({redirectTo:'/'});
}]);


app.controller('resourceControllerList',['$scope','$http','toastr','$location','$route',function ($scope,$http,toastr,$location,$route) {
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
        $http.get('/api/resource/list',{params:{pageNum:pageNum,pageSize:10}}).then(function (response) {
            $scope.resources = response.data.resources.records;
            $scope.pages = response.data.resources.showPages;
            $scope.cur = response.data.resources.num;
            $scope.prev = response.data.resources.prev;
            $scope.next = response.data.resources.next;
            $scope.pageCount=response.data.resources.pageCount;
            $scope.pageSize = 10;
        },function (response) {
            toastr.error('查询失败');
        });
    }
    $scope.query(1);

}]);

app.controller('editCtrl',['$scope','$http','$routeParams','$ocLazyLoad','$location','toastr',function ($scope,$http,$routeParams,$ocLazyLoad,$location,toastr) {
    $ocLazyLoad.load('autocomplete');
    $scope.queryMenu = function (key) {
        queryMenu(key);
    }
}]);

app.controller('addCtrl',['$scope','$http','$ocLazyLoad','$location','toastr',function ($scope,$http,$ocLazyLoad,$location,toastr) {
    $ocLazyLoad.load('autocomplete');
    $scope.addResource = function (resource) {
        resource.menuId = $("#menuId").val();
        $http.post('/api/resource/add',menu).then(function (response) {
            var result = response.data.result;
            if(result == 'success'){
                toastr.success('添加成功');
                $location.path("/");
            }else{
                toastr.error('添加失败');
            }
        },function (response) {
            toastr.error('添加失败');
        });
    }
    
    $scope.queryMenu = function (key) {
        queryMenu(key);
    }
}]);

function queryMenu(key) {
    $("#menuNameId").autocomplete({
        url:'/api/menu/queryKey?key='+key+"&query=all",
        remoteDataType: 'json',
        processData: function(data) {
            var result = data.data;
            console.log(result);
            var processed = [];
            angular.forEach(result,function (data,index,array) {
                processed.push([array[index].menuName,array[index].id]);
            });
            return processed;
        },
        onItemSelect: function(item) {
            $("#menuId").val(item.data[0]);
        },
    });
}
