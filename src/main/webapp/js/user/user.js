app.config(['$routeProvider',function ($routeProvider) {
    $routeProvider.when("/",{templateUrl:'/views/user/lists.html?t='+getVersion(),controller:'userControllerList'})
        .when("/user/list",{templateUrl:'/views/user/lists.html?t='+getVersion(),controller:'userControllerList'})
        .when("/user/edit/:id",{templateUrl:'/views/user/edit.html?t='+getVersion(),controller:'editCtrl'})
        .when("/user/add",{templateUrl:'/views/user/add.html?t='+getVersion(),controller:'addCtrl'})
        .otherwise({redirectTo:'/'});
}]);

app.controller('userControllerList',['$scope','$http','toastr','$location','$route',function ($scope,$http,toastr,$location,$route) {
    //全选
    $('table th input:checkbox').on('click' , function(){
        var that = this;
        $(this).closest('table').find('tr > td:first-child input:checkbox')
            .each(function(){
                this.checked = that.checked;
                $(this).closest('tr').toggleClass('selected');
            });

    });
    
    $scope.query = function (pageNum) {
        $http.get('/api/user/list',{params:{pageNum:pageNum,pageSize:10}}).then(function (response) {
            $scope.users = response.data.data.records;
            $scope.pages = response.data.data.showPages;
            $scope.cur = response.data.data.num;
            $scope.prev = response.data.data.prev;
            $scope.next = response.data.data.next;
            $scope.pageCount=response.data.data.pageCount;

        },function (response) {
            toastr.error('查询失败');
        });
    }
    
    $scope.deleteUser = function (user) {
        $http.delete('/api/user/delete/'+user.id).then(function (response) {
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

    $scope.query(1);
}]);

app.controller('editCtrl',['$scope','$http','$routeParams','$ocLazyLoad','$location','toastr',function ($scope,$http,$routeParams,$ocLazyLoad,$location,toastr) {
    $ocLazyLoad.load('datetimepicker').then(function() {
        $(".datetimepicker").datetimepicker({
            format: 'yyyy-mm-dd',
            minView:'month',
            language: 'zh-CN',
            autoclose:true
        });
    });

    $http.get('/api/user?id='+$routeParams.id).then(function (response) {
        $scope.user = response.data.user;
    });
    
    $scope.editUser = function (user) {
        $http.put('/api/user/update',user).then(function (response) {
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
    $ocLazyLoad.load('datetimepicker').then(function() {
        $(".datetimepicker").datetimepicker({
            format: 'yyyy-mm-dd',
            minView:'month',
            language: 'zh-CN',
            autoclose:true
        });
    });
    
    $scope.addUser = function (user) {
        user.birthday = $(".datetimepicker").val();
        user.sex = $("input[type='radio']:checked").val();
        user.userStatus = $scope.userStatus;
        $http.post('/api/user/add',user).then(function (response) {
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

function getVersion(){
    return new Date().getTime();
}
