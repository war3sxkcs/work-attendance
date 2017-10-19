$("#submitId").click(function () {
    var user = $("#user").val();
    var pwd = $("#pwd").val();
    var veryfiy = true;
    if (user == 0 && pwd == 0) {
        $.pnotify({
            title: 'New Thing',
            text: '请输入用户名和密码.',
            type: 'info'
        });
        veryfiy = false;
        return;
    }
    if (user == 0) {
        $.pnotify({
            title: 'New Thing',
            text: '请输入用户名.',
            type: 'error'
        });
        veryfiy = false;
    }
    if (pwd == 0) {
        $.pnotify({
            title: 'New Thing',
            text: '请输入密码.',
            type: 'error'
        });
        veryfiy = false;
    }
    if (veryfiy) {
        //用ajax提交表单
        $.ajax({
            type: "POST",
            url: "/login/check",
            // username是后端的求值;user是本页面js从html中获取的输入的值;   通过ajax这样进行传值
            data: {"username": user, "password": pwd},
            //成功之后   通过回调函数 跳转到主页
            success: function (data) {
                alert(data);
            }
        });
    }
})