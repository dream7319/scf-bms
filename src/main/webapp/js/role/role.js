app.config(['$routeProvider',function ($routeProvider) {
    $routeProvider.when("/",{templateUrl:'/views/role/lists.html?t='+getVersion(),controller:'roleControllerList'})
        .when("/role/list",{templateUrl:'/views/role/lists.html?t='+getVersion(),controller:'roleControllerList'})
        .otherwise({redirectTo:'/'});
}]);

app.controller('roleControllerList',['$scope','$http','toastr','$location','$route','$uibModal','$ocLazyLoad',function ($scope,$http,toastr,$location,$route,$uibModal,$ocLazyLoad) {
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
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            html: true
        }, function(inputValue){
            if (inputValue === false) return false;
            if (inputValue === "") {
                swal.showInputError("请输入角色名称!");
                return false;
            }
            $scope.role={};
            $scope.role.roleName = inputValue;
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
        $http.get('/api/role?id='+role.id).then(function (response) {
            swal({
                title: "角色修改",
                text: "请输入角色名称:",
                type: "input",
                showCancelButton: true,
                closeOnConfirm: false,
                animation: "slide-from-top",
                inputPlaceholder: "角色名称",
                inputValue:response.data.role.roleName,
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                html: true
            }, function(inputValue){
                if (inputValue === false) return false;
                if (inputValue === "") {
                    swal.showInputError("请输入角色名称!");
                    return false;
                }
                if(inputValue === response.data.role.roleName){
                    swal.showInputError("角色名称未改变,请重新输入");
                    return false;
                }
                $scope.r={};
                $scope.r.roleName = inputValue;
                $scope.r.id = response.data.role.id;
                $http.put('/api/role/update',$scope.r).then(function (response) {
                    var result = response.data.result;
                    if(result == 'success'){
                        swal.close();
                        toastr.success('修改成功');
                        $route.reload();
                    }else{
                        toastr.error('修改失败');
                    }
                },function (response) {
                    toastr.error('修改失败');
                });

            });
        });
    }

    
    $scope.lockRole = function (role,status) {
        $scope.r = {};
        $scope.r.id = role.id;
        $scope.r.roleStatus = status;
        $http.put('/api/role/update',$scope.r).then(function (response) {
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

    $ocLazyLoad.load("ztreePlugins");

    $scope.addRoleMenu = function (role) {
        //这里很关键,是打开模态框的过程
        var modalInstance = $uibModal.open({
            animation: true,//打开时的动画开关
            templateUrl: '/views/modal/menuModal.html',//模态框的页面内容,这里的url是可以自己定义的,也就意味着什么都可以写
            controller: 'menuModalCtrl',//这是模态框的控制器,是用来控制模态框的
            size: '',//模态框的大小尺寸
            resolve: {//这是一个入参,这个很重要,它可以把主控制器中的参数传到模态框控制器中
                role: function () {//items是一个回调函数
                    return role;//这个值会被模态框的控制器获取到
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {//这是一个接收模态框返回值的函数
            var result = onCheck();
            console.log(result);
            $http.get('/api/roleMenu/addOrUpdate',{params:{ids:result,roleId:role.id}}).then(function (response) {
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

app.controller('menuModalCtrl',['$scope','$http','toastr','$uibModalInstance','role',function ($scope,$http,toastr,$uibModalInstance,role) {
    //这是模态框的控制器,记住$uibModalInstance这个是用来调用函数将模态框内的数据传到外层控制器中的,items则上面所说的入参函数,
    //它可以获取到外层主控制器的参数

    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback:{
            onCheck:onCheck
        }
    };

    var datas= [];
    $http.get('/api/menu/role/'+role.id).then(function (response) {
        $scope.menus = response.data.menus;

        angular.forEach($scope.menus,function (data,index,array) {
            var pId = array[index].parentId;
            if(pId == 0){
                datas.push({"id": array[index].id,
                    "pId": 0,
                    "name": array[index].menuName,
                    "open": true,
                    "iconOpen": "/static/css/img/diy/1_open.png",
                    "iconClose": "/static/css/img/diy/1_close.png",
                    checked:array[index].checked});
            }else{
                datas.push({"id": array[index].id,
                    "pId": array[index].parentId,
                    "name": array[index].menuName,
                    "icon": "/static/css/img/diy/5.png",
                    checked:array[index].checked});
            }

        });
        $.fn.zTree.init($("#menuTree"), setting, datas);
    },function (response) {
        toastr.error("加载数据失败");
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

function onCheck() {
    var treeObj=$.fn.zTree.getZTreeObj("menuTree");
    var nodes=treeObj.getCheckedNodes(true);
    var menuIds = [];

    for(var i=0;i<nodes.length;i++){
        menuIds.push(nodes[i].id);
    }
    return menuIds.join(",");
}

function getVersion(){
    return new Date(1487552899999).getTime();
}
