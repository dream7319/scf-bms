angular.module('categories.bookmarks',[
    'categories.bookmarks.create',
    'categories.bookmarks.edit',
    'darren.models.bookmarks'
])
    .config(function($stateProvider){
        $stateProvider
            .state('darren.categories.bookmarks',{
                url: 'categories/:category',
                views: {
                    'bookmarks@':{
                        templateUrl: 'bookmarks.tmpl.html',
                        controller:'BookmarksCtrl as bookmarksListCtrl'
                    }
                }
            });
    })

    //不需要$scope的写法
    .controller('BookmarksCtrl', function($stateParams,BookmarksModel,CategoriesModel){
        var bookmarksListCtrl = this;

        CategoriesModel.setCurrentCategory($stateParams.category);

        BookmarksModel.getBookmarks()
            .then(function(bookmarks){
                bookmarksListCtrl.bookmarks = bookmarks;
            });

        bookmarksListCtrl.getCurrentCategory = CategoriesModel.getCurrentCategory;
        bookmarksListCtrl.getCurrentCategoryName = CategoriesModel.getCurrentCategoryName;
        bookmarksListCtrl.deleteBookmark = BookmarksModel.deleteBookmark;

    })
;