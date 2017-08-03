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

    $scope.deleteMenu = function (menu) {
        $http.delete('/api/menu/delete/'+menu.id).then(function (response) {
            var result = response.data.result;
            if(result == 'success'){
                toastr.success('删除成功');
                $route.reload();
            }else{
                toastr.error('删除失败');
            }
        },function (response) {
            toastr.error('删除失败');
        });
    }

    $scope.lockMenu = function (menu,status) {
        $scope.m = {};
        $scope.m.id = menu.id;
        $scope.m.menuStatus = status;
        $http.put('/api/menu/update',$scope.m).then(function (response) {
            var result = response.data.result;
            if(result == 'success'){
                $scope.promptMsg('success',status);
                $route.reload();
            }else{
                $scope.promptMsg('error',status);
            }
        },function (response) {
            $scope.promptMsg('error',status);
        });
    }

    $scope.promptMsg = function (result,status) {
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
}]);

app.controller('editCtrl',['$scope','$http','$routeParams','$ocLazyLoad','$location','toastr',function ($scope,$http,$routeParams,$ocLazyLoad,$location,toastr) {
    $ocLazyLoad.load("autocomplete");

    $scope.queryParentMenu = function (parentMenuName) {
        $("#parentMenuNameId").autocomplete({
            url:'/api/menu/queryKey?key='+parentMenuName,
            remoteDataType: 'json',
            processData: function(data) {
                var result = data.data;
                var processed = [];
                angular.forEach(result,function (data,index,array) {
                    processed.push([array[index].menuName,array[index].id]);
                });
                return processed;
            },
            onItemSelect: function(item) {
                $("#parentId").val(item.data[0]);
            },
        });
    }
    $http.get('/api/menu?id='+$routeParams.id).then(function (response) {
        $scope.menu = response.data.menu;
    });

    $scope.editMenu = function (menu) {
        console.log(menu);
        menu.parentId = $("#parentId").val();
        $http.put('/api/menu/update',menu).then(function (response) {
            var result = response.data.result;
            if(result == 'success'){
                toastr.success('修改成功');
                $location.path("/");
            }else{
                toastr.error('修改失败');
            }
        },function (response) {
            toastr.error('修改失败');
        });
    }
}]);

app.controller('addCtrl',['$scope','$http','$ocLazyLoad','$location','toastr',function ($scope,$http,$ocLazyLoad,$location,toastr) {

    $ocLazyLoad.load("autocomplete");
    
    $scope.queryParentMenu = function (parentMenuName) {
        $("#parentMenuNameId").autocomplete({
            url:'/api/menu/queryKey?key='+parentMenuName,
            remoteDataType: 'json',
            processData: function(data) {
                var result = data.data;
                var processed = [];
                angular.forEach(result,function (data,index,array) {
                    processed.push([array[index].menuName,array[index].id]);
                });
                return processed;
            },
            onItemSelect: function(item) {
                $("#parentId").val(item.data[0]);
            },
        });
    }

    $scope.addMenu = function (menu) {
        menu.parentId = $("#parentId").val();
        $http.post('/api/menu/add',menu).then(function (response) {
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

}]);

function query(http,key) {
    /*http.get('/api/menu/queryKey?key='+key).then(function (response) {
        
    },function (response) {
        
    });*/

    http.get('/api/menu/lists').then(function (response) {
        console.log(response.data.data);

    },function (response) {

    });
}