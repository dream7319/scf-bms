app.config(['$routeProvider',function ($routeProvider) {
    $routeProvider.when("/",{templateUrl:'/views/role/lists.html?t='+getVersion(),controller:'roleControllerList'})
        .when("/role/list",{templateUrl:'/views/role/lists.html?t='+getVersion(),controller:'roleControllerList'})
        .when("/role/edit/:id",{templateUrl:'/views/role/edit.html?t='+getVersion(),controller:'editCtrl'})
        .otherwise({redirectTo:'/'});
}]);

app.controller('roleControllerList',['$scope','$http','toastr','$location','$route',function ($scope,$http,toastr,$location,$route) {
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
        $http.get('/api/role/list',{params:{pageNum:pageNum,pageSize:10}}).then(function (response) {
            $scope.roles = response.data.data.records;
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

    $scope.deleteRole = function (role) {
        $http.delete('/api/role/delete/'+role.id).then(function (response) {
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

    $scope.addRole = function () {
        swal({
            title: "角色添加",
            text: "请输入角色名称:",
            type: "input",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: "slide-from-top",
            inputPlaceholder: "角色名称",
            html: true
        }, function(inputValue){
            if (inputValue === false) return false;
            if (inputValue === "") {
                swal.showInputError("请输入角色名称!");
                return false;
            }
            $scope.role={};
            $scope.role.roleName = inputValue;
            console.log($scope.role);
            $http.post('/api/role/add',$scope.role).then(function (response) {
                var result = response.data.result;
                if(result == 'success'){
                    swal.close();
                    toastr.success('添加成功');
                    $route.reload();
                }else{
                    toastr.error('添加失败');
                }
            },function (response) {
                toastr.error('添加失败');
            });
        });
    }
    
    $scope.editRole = function (role) {
        $scope.role = {};
        $http.get('/api/role?id='+role.id).then(function (response) {
            $scope.role = response.data.role;
        });

        console.log($scope.role);
    }
}]);

app.controller('editCtrl',['$scope','$http','$routeParams','$ocLazyLoad','$location','toastr',function ($scope,$http,$routeParams,$ocLazyLoad,$location,toastr) {


}]);

function getVersion(){
    return new Date(1487552899999).getTime();
}
