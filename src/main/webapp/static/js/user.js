$(function () {
    $.ajax({
        type: "POST",
        url: "/user/userinfo",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        data: {},
        success: function (data) {
            $(".user_head_img").attr("src", data.headImage);
            if ((data.realName) == null || (data.realName) == "") {
                $(".user_name").html(data.username);
            }
            $(".user_name").html(data.realName);
        }
    });
});