/**
 * Created by on 2017/2/16.
 */
//添加点击效果
function addCo(cla){
    $(cla).click(function(){
        $(this).siblings().removeClass('curr');
        $(this).addClass('curr');
    })
}
//返回顶部
$(".top").click(function () {
    var speed = 500;
    $('body,html').animate({scrollTop: 0}, speed);
});