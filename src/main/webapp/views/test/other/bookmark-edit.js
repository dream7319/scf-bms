angular.module('categories.bookmarks.edit',[])
    .config(function($stateProvider){
        $stateProvider
            .state('darren.categories.bookmarks.edit',{
                url: '/bookmarks/:bookmarkId/edit',
                templateUrl: 'bookmark-edit.tmpl.html',
                controller: 'EditBookmarkCtrl as editBookmarkCtrl'
            });
    })
    .controller('EditBookmarkCtrl', function($state, $stateParams, BookmarksModel){
        var editBookmarkCtrl = this;

        //更新成功或取消更新
        function returnToBookmarks(){
            $state.go('darren.categories.bookmarks',{
                category: $stateParams.category
            });
        }

        function cancelEditing(){
            returnToBookmarks();
        }

        //editBookmarkCtrl.bookmark
        //editBookmarkCtrl.editedBookmark
        BookmarksModel.getBookmarkById($stateParams.bookmarkId)
            .then(function(bookmark){
                if(bookmark){
                    editBookmarkCtrl.bookmark = bookmark;
                    editBookmarkCtrl.editedBookmark = angular.copy(editBookmarkCtrl.bookmark);
                } else {
                    returnToBookmarks();
                }
            });

        //更新
        function updateBookmark(){
            editBookmarkCtrl.bookmark = angular.copy(editBookmarkCtrl.editedBookmark);
            BookmarksModel.updateBookmark(editBookmarkCtrl.bookmark);
            returnToBookmarks();
        }

        editBookmarkCtrl.cancelEditing = cancelEditing;
        editBookmarkCtrl.updateBookmark = updateBookmark;
    })
;