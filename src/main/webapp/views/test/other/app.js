angular.module('Darren', ['ngAnimate',
    'ui.router',
    'categories',
    'categories.bookmarks'
]).config(function($stateProvider, $urlRouterProvider){
    //默认情况下导航到首页
    $stateProvider
        .state('darren',{
            url: '',
            abstract: true
        });
    $urlRouterProvider.otherwise('/');
});