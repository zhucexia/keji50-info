define('widget/newslist/newslist', ['require', 'exports', 'module', 'components/jquery/jquery'],function(require, exports, module) {
var $ = require('components/jquery/jquery');
var app = module.exports = function(opt) {
	$(opt.dom).click(function(){
		$("#mask").css('height', document.body.scrollHeight).show();
	})
	$("#mask").click(function(){
		$(this).hide();
	})
};
});