define('widget/listbar/listbar', ['require', 'exports', 'module', 'components/jquery/jquery'],function(require, exports, module) {
var $ = require('components/jquery/jquery');
var app = module.exports = function(opt) {
	var list = {
		init : function(){
			this.addEvent();
		},
		addEvent : function(){
			var $J_navBar = $('#J_navBar'),
				J_navBar = $J_navBar[0];
			$(window).scroll(function(){
				var topset = parseInt(J_navBar.getBoundingClientRect().top),
					wrapset = $(".J_articleListWrap")[0].getBoundingClientRect(),
					wrapsetTop = parseInt(wrapset.top),
					wrapsetBottom = parseInt(wrapset.bottom);
				
				if(!$J_navBar.hasClass('fixed') && topset < 60 && topset > -60){
					$J_navBar.addClass('fixed');
					return ;
				}
				if($J_navBar.hasClass('fixed') && ( wrapsetTop > -60 || wrapsetBottom < 80)){
					$J_navBar.removeClass('fixed');
				}
			});
		},
	};
	list.init();
};
});