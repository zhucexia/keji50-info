define('widget/detail/detail', ['require', 'exports', 'module', 'components/jquery/jquery', 'components/template/template'],function(require, exports, module) {
var $ = require('components/jquery/jquery');
var Template = require('components/template/template');
var app = module.exports = function(opt){
	var detail = {
		init : function(){
			this.commentTemplate = "<% for(var i = 0,len = data.data.length; i<len ;i++){%>\n<% var comment = data.data[i],path = data.contextpath ; %>\n<div class=\"comment cf comment_details\" data-comment-id=\"<%=comment.id %>\">\n    <div class=\"avatar left\">\n        <a href=\"javascript:void(0)\"><img alt=\"科技50用户<%=comment.author.nickname %>\" src=\"<%=comment.author.image %>\" raw_iden=\"<%=comment.id %>\" class=\"before-fade-in\"></a>\n    </div>\n    <div class=\"comment-wrapper\">\n        <div class=\"postmeta\"><a class=\"user_info_name\" href=\"javascript:void(0)\"><%=comment.author.nickname %></a>&nbsp;•&nbsp;\n            <abbr class=\"timeago\" title=\"<%=comment.createDate %>\"><%=comment.createDate %></abbr>\n        </div>\n        <div class=\"commemt-main\">\n            <p><%=comment.content %></p>\n        </div>\n        <div class=\"opts\"></div>\n        <a class=\"pull-right\" href=\"javascript:;\"> 回复</a></div>\n</div>\n\n<%}%>   ";
			this.addEvent();
		},
		addEvent : function(){
			var _this = this,
				i = 0
			var getlogin = setInterval(function(){
				i++;
				console.log(loginInfo)
				if(loginInfo || i > 10){
					clearInterval(getlogin);
					if(loginInfo && loginInfo.isUserLogin){
						$('#J_userInfo').show().find('.avatar').css('backgroundImage',loginInfo.image);
						$('#J_loginname').html(loginInfo.nickName);
						$('.require-login').show();
						$('.J_requirelogin').hide();
					}else{
						$('.require-login').hide();
						$('.J_requirelogin').show();
					}
					_this.getList(opt.commentUrl,{
						author_id : loginInfo ? loginInfo.id : 0
					},'get',function(data){
						if(data.code == 0){
							if(data.data.length){
								$('#commentTotalCount').html(data.data.length);
								$('#commentFormCount').html(data.data.length);
								var result = Template.parse(_this.commentTemplate,{data:data});
								$('#J_comments').html(result);
							}
						}
					});
				}
			},200);
			
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