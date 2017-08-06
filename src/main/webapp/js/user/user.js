app.config(['$routeProvider',function ($routeProvider) {
    $routeProvider.when("/",{templateUrl:'/views/user/lists.html?t='+getVersion(),controller:'userControllerList'})
        .when("/user/list",{templateUrl:'/views/user/lists.html?t='+getVersion(),controller:'userControllerList'})
        .when("/user/edit/:id",{templateUrl:'/views/user/edit.html?t='+getVersion(),controller:'editCtrl'})
        .when("/user/add",{templateUrl:'/views/user/add.html?t='+getVersion(),controller:'addCtrl'})
        .when("/user/detail/:id",{templateUrl:'/views/user/detail.html?t='+getVersion(),controller:'detailCtrl'})
        .otherwise({redirectTo:'/'});
}]);

app.controller('userControllerList',['$scope','$http','toastr','$location','$route','$uibModal',function ($scope,$http,toastr,$location,$route,$uibModal){
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
        $http.get('/api/user/list',{params:{pageNum:pageNum,pageSize:10}}).then(function (response) {
            $scope.users = response.data.data.records;
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

    $scope.lockUser = function (user,status) {
        $scope.u = {};
        $scope.u.id = user.id;
        $scope.u.userStatus = status;

        $http.put('/api/user/update',$scope.u).then(function (response) {
            var result = response.data.result;
            if(result == 'success'){
                promptMsg(toastr,'success',status);
                $route.reload();
            }else{
                promptMsg(toastr,'error',status);
            }
        },function (response) {
            promptMsg(toastr,'error',status);
        });
    }
    
    $scope.addUserRole = function (user) {
        //这里很关键,是打开模态框的过程
        var modalInstance = $uibModal.open({
            animation: true,//打开时的动画开关
            templateUrl: '/views/modal/roleModal.html',//模态框的页面内容,这里的url是可以自己定义的,也就意味着什么都可以写
            controller: 'modalCtrl',//这是模态框的控制器,是用来控制模态框的
            size: '',//模态框的大小尺寸
            resolve: {//这是一个入参,这个很重要,它可以把主控制器中的参数传到模态框控制器中
                user: function () {//items是一个回调函数
                    return user;//这个值会被模态框的控制器获取到
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {//这是一个接收模态框返回值的函数
            $scope.roleIds = [];
            $("input[type=checkbox]").each(function () {
                if($(this).is(':checked')){
                    var value = $(this).next().next().val();
                    $scope.roleIds.push(value);
                }
            });
            $http.get('/api/userRole/addOrUpdate',{params:{ids:$scope.roleIds.join(','),userId:user.id}}).then(function (response) {
                var result = response.data.result;
                if(result == 'success'){
                    toastr.success('保存成功');
                }else{
                    toastr.error('保存失败');
                }
            },function (response) {
                toastr.error('保存失败');
            });
            
        }, function () {
            // console.log('Modal dismissed at: ' + new Date());
        });
    }
}]);

app.controller('modalCtrl',['$scope','$http','toastr','$uibModalInstance','user',function ($scope,$http,toastr,$uibModalInstance,user) {
    //这是模态框的控制器,记住$uibModalInstance这个是用来调用函数将模态框内的数据传到外层控制器中的,items则上面所说的入参函数,
    //它可以获取到外层主控制器的参数
    // $scope.user = user;//这里就可以去外层主控制器的数据了
    $http.get('/api/role/user/'+user.id).then(function (response) {
        $scope.roles = response.data.data;
    },function (response) {
        toastr.error('加载数据失败');
    });

    $scope.ok = function () {
        //close函数是在模态框关闭后调用的函数,他会将这个参数传到主控制器的results函数中,作为回调值
        $uibModalInstance.close();
    };

    $scope.cancel = function () {
        //dismiss也是在模态框关闭的时候进行调用,而它返回的是一个reason
        $uibModalInstance.dismiss('cancel');
    };
}]);

app.controller('detailCtrl',['$scope','$http','$routeParams',function ($scope,$http,$routeParams) {
    $http.get('/api/user?id='+$routeParams.id).then(function (response) {
        $scope.user = response.data.user;
        console.log($scope.user);
    });
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

        if($("#userStatusId").hasClass('switch-on')){
            user.userStatus = true;
        }else{
            user.userStatus = false;
        }
        // console.log(user);
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
