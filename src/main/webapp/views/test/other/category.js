angular.module('categories',[
    'darren.models.categories'
])
    .config(function($stateProvider){
        $stateProvider
            .state('darren.categories',{
                url: '/',
                views: {
                    //target the ui-view named categories in root state
                    'categories@':{
                        controller: 'CategoriesCtrl as categoriesListCtrl',
                        templateUrl: 'categories.tmpl.html'
                    },
                    //target the ui-view named 'bookmarks' in root state to show all bookmarks for all categories
                    'bookmarks@':{
                        controller: 'BookmarksCtrl as bookmarksListCtrl',
                        templateUrl: 'bookmarks.tmpl.html'
                    }
                }
            });
    })
    .controller('CategoriesCtrl', function CategoriesCtrl(CategoriesModel){
        var categoriesListCtrl = this;

        CategoriesModel.getCategoires()
            .then(function(result){
                categoriesListCtrl.categories = result;
            });

    })
;