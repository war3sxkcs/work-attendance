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
        $("#login_from").submit();
    }
})