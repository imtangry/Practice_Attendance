/**
 * Created by tryu on 2017/7/2.
 */
$(function(){
    $.ajax({
        type:"POST",
        url:"/user/info",
        dataType:"json",
        contentType:"Application/json",
        success:function (data) {
            $(".user_name").html(data.name);
            $(".user_head_img").attr("src",data.head_img);
        }
    });
});


