$(function () {
    var path = window.location.pathname;
    $('a').each(function () {
        var href = $(this).attr('href');
        if(href == path){
            $(this).parent().parent().parent('li').toggleClass("open");
            $(this).parent('li').addClass('active');
            $(this).parent().parent('.submenu').css('display','block');
        }
    });
});