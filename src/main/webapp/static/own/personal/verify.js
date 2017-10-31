$("#submitId").click(function () {
    var user = $("#user").val();
    var pwd = $("#pwd").val();
    var veryfiy = true;

// cookie验证; 记住密码
    if ($("input[type='checkbox']").is(':checked')) {
        var remember = "1";
    } else {
        var remember = "0";
    }

    if (user == 0 && pwd == 0) {
        $.pnotify({
            title: 'Token',
            text: '请输入用户名和密码.',
            type: 'info'
        });
        veryfiy = false;
        return;
    }
    if (user == 0) {
        $.pnotify({
            title: 'UserName ',
            text: '请输入用户名.',
            type: 'error'
        });
        veryfiy = false;
        return;
    }
    if (pwd == 0) {
        $.pnotify({
            title: 'Password ',
            text: '请输入密码.',
            type: 'error'
        });
        veryfiy = false;
        return;
    }

    if (veryfiy) {
        //用ajax提交表单
        $.ajax({
            type: "POST",
            url: "/login/check",
            // username是后端的求值;user是本页面js从html中获取的输入的值;   通过ajax这样进行传值
            data: {"username": user, "password": pwd, "remember": remember},
            //成功之后   通过回调函数 跳转到主页
            success: function (data) {
                if (data == "login_succ") {
                    window.location.href = "/user/home";
                } else {
                    $.pnotify({
                        title: 'Error !',
                        text: '用户名或密码不正确,请重新输入.',
                        type: 'success'
                    });
                }
            }
        });
    }
})