define('widget/detail/detail', ['require', 'exports', 'module', 'components/jquery/jquery', 'components/template/template'],function(require, exports, module) {
var $ = require('components/jquery/jquery');
var Template = require('components/template/template');
var app = module.exports = function(opt){
	var detail = {
		init : function(){
			this.commentTemplate = "<% for(var i = 0,len = data.data.length; i<len ;i++){%>\r\n<% var comment = data.data[i],path = data.contextpath ; %>\r\n<div class=\"comment cf comment_details\" data-comment-id=\"<%=comment.id %>\">\r\n    <div class=\"avatar left\">\r\n        <a href=\"javascript:void(0)\"><img alt=\"科技50用户<%=comment.author.nickname %>\" data-lazyload=\"<%=comment.author.image %>\" raw_iden=\"<%=comment.id %>\" class=\"before-fade-in\"></a>\r\n    </div>\r\n    <div class=\"comment-wrapper\">\r\n        <div class=\"postmeta\"><a class=\"user_info_name\" href=\"javascript:void(0)\"><%=comment.author.nickname %></a>&nbsp;•&nbsp;\r\n            <abbr class=\"timeago\" title=\"<%=comment.createDate %>\"><%=comment.createDate %></abbr>\r\n        </div>\r\n        <div class=\"commemt-main\">\r\n            <p><%=comment.content %></p>\r\n        </div>\r\n        <div class=\"opts\"></div>\r\n        <a class=\"pull-right\" href=\"javascript:;\"> 回复</a></div>\r\n</div>\r\n\r\n<%}%>   ";
			this.addEvent();
		},
		addEvent : function(){
			var _this = this;
			var getlogin = setInterval(function(){
				if(loginInfo){
					clearInterval(getlogin);
					if(loginInfo.isUserLogin){
						$('#J_userInfo').find('.avatar').css('backGroundImage',loginInfo.image);
						$('.require-login').show();
						$('.J_requirelogin').hide();
					}else{
						$('.require-login').hide();
						$('.J_requirelogin').show();
					}
					_this.getList(opt.commentUrl,{
						author_id : loginInfo.id
					},'get',function(data){
						if(data.code == 0){
							if(data.data.length){
								var result = Template.parse(_this.commentTemplate,{data:data});
								$('#J_comments').html(result);
							}
						}
					});
				}
			},100);
			$('#J_comments').delegate('.pull-right','click',function(){

			});
		},
		getList : function(url,opt,type,callback){
			var _this = this;
			$.ajax({
			   	type: type,
			   	url: url,
			   	data: opt,
			   	success: callback
			}).complete(function(){
				console.log('success');
			});
		} 
	};
	detail.init();
}


});