function chageCode() {
    document.getElementById("codeImage").src = "/login/authCode?" + Math.random();
}

// var ur1 = "/static/own/music/music";
var ur1 = "http://p0cj2p6dz.bkt.clouddn.com/music";
var ur2 = ".mp3";
var num = Math.random();
num=Math.ceil(num*20);
document.getElementById("music").src = ur1 + num + ur2;
