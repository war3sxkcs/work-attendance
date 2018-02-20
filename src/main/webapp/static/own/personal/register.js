$("#register").click(function () {
    var user = $("#emailuser").val();
    var pwd = $("#emailpwd").val();
    var code = $("#emailcode").val();
    var realname = $("#realname").val();
    var mobile = $("#mobile").val();
    if (user == "" || pwd == "" || code == "") {
        $.pnotify({
            title: '注册信息',
            text: '请正确输入您的注册信息 !',
            type: 'error'
        });
        return;
    }
    var regex = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g;
    if (!regex.test(user)) {
        $.pnotify({
            title: '邮箱',
            text: '您输入的邮箱格式不合法 !',
            type: 'error'
        });
        return;
    }
    // 用ajax提交表单
    $.ajax({
        type: "POST",
        url: "/login/register",
        data: {"emailuser": user, "emailpwd": pwd, "emailcode": code, "realname": realname, "mobile": mobile},
        success: function (data) {
            if (data == "register_succ") {
                $.pnotify({
                    title: 'success ! ',
                    text: '注册成功 !',
                    type: 'success'
                });
                $.pnotify({
                    title: 'success ! ',
                    text: '请注意查收电子邮件以便激活 !',
                    type: 'success'
                });
                $.pnotify({
                    title: 'success ! ',
                    text: '<a href="/login" style="text-decoration-line: none">跳转到主页登录</a>',
                    type: 'success'
                });

            } else {
                $.pnotify({
                    title: '验证码',
                    text: '您输入的验证码不正确,请重新注册 !',
                    type: 'error'
                });
            }
        }
    });
});
