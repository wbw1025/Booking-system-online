/**
 * Created by admin on 2017/3/11.
 */
//图片轮播
function picCarousel(){
    $(function () {
        //图片轮播
        var curIndex = 0, //当前index
            imgLen = $(".img_box li").length; //图片总数
        var autoChange = setInterval(function () {
            if (curIndex < imgLen - 1) {
                curIndex++;
            } else {
                curIndex = 0;
            }
            changeTo(curIndex);
        }, 2500);
        //对右下角按钮index进行事件绑定处理等
        $(".num").find("li").each(function (item) {
            $(this).hover(function () {
                clearInterval(autoChange);
                changeTo(item);
                curIndex = item;
            }, function () {
                autoChangeAgain();
            });
        });
        //清除定时器时候的重置定时器--封装
        function autoChangeAgain() {
            autoChange = setInterval(function () {
                if (curIndex < imgLen - 1) {
                    curIndex++;
                } else {
                    curIndex = 0;
                }
                //调用变换处理函数
                changeTo(curIndex);
            }, 2500);
        }

        function changeTo(num) {
            var goLeft = num * 880;
            $(".img_box").animate({left: "-" + goLeft + "px"}, 500);
            $(".num").find("li").removeClass("curr").eq(num).addClass("curr");
        }
    });

}