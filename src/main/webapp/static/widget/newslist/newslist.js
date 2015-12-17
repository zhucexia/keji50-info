define('widget/newslist/newslist', ['require', 'exports', 'module', 'components/jquery/jquery', 'components/template/template'],function(require, exports, module) {
var $ = require('components/jquery/jquery');
var Template = require('components/template/template');
var app = module.exports = function(opt) {
	var list = {
		init : function(){
			this.listTemplate = "<% for(var i = 0,len = list.length; i<len ;i++){%>\n    <article>\n        <a class=\"pic info_flow_news_image badge-o2o before-fade-in after-fade-in\" data-fit-mobile=\"true\" href=\"$rc.getContextPath()/p/$info.id\" style=\"background-image: url($info.image);\">\n            <span class=\"mask-tags\" data-type=\"$info.infoCategory.code\" style=\"background-color: $info.infoCategory.colorCode\">$info.infoCategory.name</span>\n        </a>\n        <div class=\"desc\">\n            <a class=\"title info_flow_news_title\" href=\"$rc.getContextPath()/p/$info.id\" target=\"_blank\">$info.title</a>\n            <div class=\"author\">\n            <a href=\"$rc.getContextPath()/a/$info.author.id\">\n                <span class=\"avatar before-fade-in\" style=\"background-image: url($info.author.image);\"></span>\n                <span class=\"name\">$info.author.getNickname()</span></a>\n                <span class=\"time\">&nbsp;•&nbsp;<time class=\"timeago\" title=\"$info.getCreateDate()\" datetime=\"$info.getCreateDate()\">$info.getCreateDate()</time></span>\n            </div>\n            <div class=\"brief\">$info.shortDesc</div>\n        </div>\n    </article>\n<%}%>    ";
			this.addEvent();
		},
		addEvent : function(){
			var _this = this;
			$(opt.dom).click(function(){
				console.log(_this.listTemplate);
			});
		}
	};
	list.init();
	$(opt.dom).click(function(){
		$("#mask").css('height', document.body.scrollHeight).show();
	})
	$("#mask").click(function(){
		$(this).hide();
	})
};
});