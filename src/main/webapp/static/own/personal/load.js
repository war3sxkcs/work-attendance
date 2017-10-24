function chageCode() {
    document.getElementById("codeImage").src = "/login/authCode?" + Math.random();
}

var ur1 = "/static/own/music/music";
var ur2 = ".mp3";
var num = parseInt(Math.random() * 10);
document.getElementById("music").src = ur1 + num + ur2;