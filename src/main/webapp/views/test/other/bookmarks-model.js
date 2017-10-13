angular.module('darren.models.bookmarks',[])
    .service('BookmarksModel', function($http, $q){
        var model = this,
            bookmarks,
            URL={
                FETCH: 'bookmarks.json'
            };

        //获取
        model.getBookmarks = function(){

            var deferred = $q.defer();

            if(bookmarks){
                deferred.resolve(bookmarks);
            } else{
                $http.get(URL.FETCH).then(function(bookmarks){
                    deferred.resolve(cacheBookmarks(bookmarks));
                });
            }

            return deferred.promise;
        }

        function extract(result){
            return result.data;
        }

        function cacheBookmarks(result){
            bookmarks = extract(result);
            return bookmarks;
        }

        //创建
        model.createBookmark = function(bookmark){
            bookmark.id = bookmarks.length;
            bookmarks.push(bookmark);
        }

        //根据编号查找
        function findBookmark(bookmarkId){
            return _.find(bookmarks, function(bookmark){
                return bookmark.id === parseInt(bookmarkId, 10);
            });
        }

        model.getBookmarkById = function(bookmarkId){
            //创建defered object
            var deferred = $q.defer();

            if(bookmarks){
                deferred.resolve(findBookmark(bookmarkId));
            } else{
                model.getBookmarks().then(function(){
                    deferred.resolve(findBookmark(bookmarkId));
                });
            }

            return deferred.promise;
        }

        //更新
        model.updateBookmark = function(bookmark){
            var index = _.findIndex(bookmarks, function(b){
                return b.id == bookmark.id;
            });

            bookmarks[index] = bookmark;
        }

        //删除
        model.deleteBookmark = function(bookmark){
            _.remove(bookmarks, function(item){
                return item.id == bookmark.id;
            });
        }
    })
;