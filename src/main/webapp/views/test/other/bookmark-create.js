angular.module('categories.bookmarks.create',[])

    .config(function($stateProvider){
        $stateProvider
            .state('darren.categories.bookmarks.create',{
                url: '/bookmarks/create',
                templateUrl: 'bookmark-create.tmpl.html',
                controller: 'CreateBookmarkCtrl as createBookmarkCtrl'
            });
    })
    .controller('CreateBookmarkCtrl', function($state, $stateParams, BookmarksModel){
        var createBookmarkCtrl = this;

        //添加或取消完成后执行
        function returnToBookmarks(){
            $state.go('darren.categories.bookmarks',{
                category: $stateParams.category
            });
        }

        //取消
        function cancelCreating(){
            returnToBookmarks();
        }

        //添加
        function createBookmark(bookmark){
            BookmarksModel.createBookmark(bookmark);
            returnToBookmarks();
        }

        createBookmarkCtrl.cancelCreating = cancelCreating;
        createBookmarkCtrl.createBookmark = createBookmark;

        //重置表单
        function resetForm(){
            createBookmarkCtrl.newBookmark = {
                title: '',
                url: '',
                category: $stateParams.category
            };
        }

        resetForm();
    })
;