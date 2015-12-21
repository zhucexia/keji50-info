define('widget/go2top/go2top', ['require', 'exports', 'module', 'components/jquery/jquery'],function(require, exports, module) {
var $ = require('components/jquery/jquery');

var e = $("#J_backTop .j_small-rcode"),
	f = $("#J_backTop .j_back-top"),
	g = $("#J_backTop .j_hf-app-content"),
	h = $("#header"),
	i = $("#lpDetailNav");
function d() {
	var a = 0 !== h.length ? h.height() : null,
		b = 0 !== i.length ? i.offset().top + i.height() : null,
		c = a || b;
	return c && c > 50 ? c : 250
}
f.click(function() {
	document.body.scrollTop ? document.body.scrollTop = 0 : document.documentElement.scrollTop = 0
});
$(window).scroll(function() {
	var a = document.body.scrollTop || document.documentElement.scrollTop,
		b = d();
	a > b ? f.removeClass("hide") : f.addClass("hide")
});

});