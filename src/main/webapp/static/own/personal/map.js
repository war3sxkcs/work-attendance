var map = new BMap.Map("allmap");
var point = new BMap.Point(116.331398, 39.897445);
map.centerAndZoom(point, 12);

function myFun(result) {
    var cityName = result.name;
    map.setCenter(cityName);
}

var myCity = new BMap.LocalCity();
myCity.get(myFun);
var login_div = document.getElementById("login_div");
var h1 = (login_div.offsetHeight + 20);
var h2 = $(window).height();
var h = h2 - h1;
$("#allmap").css("height", h);