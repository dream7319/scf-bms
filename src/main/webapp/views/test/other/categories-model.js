angular.module('darren.models.categories',[])
    .service('CategoriesModel', function($http, $q){

        var model=this,
            URLS={
                FETCH:'categories.json'
            },
            categories,
            currentCategory;

        model.getCategoires = function(){

            //如果categories存在，使用$q返回promise
            //如果不存在才去提取数据
            return (categories) ? $q.when(categories) : $http.get(URLS.FETCH).then(cacheCategories);
        }

        function extract(result){
            return result.data;
        }

        function cacheCategories(result){
            categories = extract(result);
            return categories;
        }

        model.getCategoryByName = function(categoryName){
            var defered = $q.defer();

            function findCategory(){
                return _.find(categories, function(c){
                    return c.name == categoryName;
                });
            }

            if(categories){
                defered.resolve(findCategory());
            } else{
                model.getCategoires()
                    .then(function(result){
                        defered.resolve(findCategory());
                    });
            }

            return defered.promise;
        };

        model.setCurrentCategory = function(categoryName){
            return model.getCategoryByName(categoryName)
                .then(function(category){
                    currentCategory = category;
                });
        };

        model.getCurrentCategory = function(){
            return currentCategory;
        }

        model.getCurrentCategoryName = function(){
            return currentCategory ? currentCategory.name : '';
        };

    })
;