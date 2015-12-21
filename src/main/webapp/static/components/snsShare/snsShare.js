define('components/snsShare/snsShare', ['require', 'exports', 'module', 'common/web:jquery'],function(require, exports, module) {
(function(window,$){
	var SnsShare = function(opt){
		this.options = $.extend({
			dom : null,
			sns : '', //分享列表，空格分隔 '微博 豆瓣 qq空间 qq 人人 腾讯微博'
			className : '', //自定义class (选填)
			appkey : '', //appkey (选填)
			title : '', //分享标题 (微博不分title和content,内容取title)
			url : location.href, //分享链接,默认为当前地址
			pic : '', //分享图片地址
			content : '', //分享主体内容 
			summary : '', //分享摘要，可不填
			site : '', //分享来源
			flash : '' //分享视频地址
		}, opt || {});
		this.init();
	};	

	SnsShare.DEFAULTS = {
		PREFIX : {
			'wb' : 'http://service.weibo.com/share/share.php?',
			'db' : 'http://www.douban.com/share/service?updated=&bm=1&sel=&v=1&',
			'qz' : 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?',
			'qq' : 'http://connect.qq.com/widget/shareqq/index.html?',
			'rr' : 'http://widget.renren.com/dialog/share?',
			'tb' : 'http://share.v.t.qq.com/index.php?c=share&a=index&'
		},
		PIC : {
			'wb' : 'pic',
			'db' : 'image',
			'qq' : 'pics',
			'qz' : 'pics',
			'rr' : 'images',
			'tb' : 'pic'
		},
		CONTENT : {
			'wb' : 'title',
			'tb' : 'title',
			'db' : 'text',
			'qq' : 'desc',
			'qz' : 'desc',
			'rr' : 'description'
		},
		NOTITLE : ['wb','tb'],
		TEMPLATE : '<ul class="ui-sns-container"></ul>'
	};

	SnsShare.prototype.init = function(){
		var self = this, opt = self.options || {}, sns = [] ,depends;

		if(opt.dom && !opt.sns){
			this.children = $(opt.dom).find('[sns-data]').each(function(index, el) {
				sns.push($(el).attr('sns-data'));
			}); 
			depends = 1;
		}
		this.sns = sns.length ? sns : opt.sns.split(' ') ;
		self.generateContainer(this.sns.length,opt,depends);
	};

	SnsShare.prototype.generateContainer = function(len,opt,depends){
		var self = this , 
			str = '',
			opt = self.options, 
			args = [], 
			part = ['dom','sns','className'],
			defaults = SnsShare.DEFAULTS,
			key ;

		self.urls = {};	
		$.each(self.sns, function(i, sns) {
			args = [];
			$.each(opt, function(idx, val) {
				if(idx == 'title' && (sns == 'wb' || sns == 'tb')){}
				else if(val && part.indexOf(idx) < 0){
					
					key = (idx == 'content' ? defaults.CONTENT[sns] : (idx == 'pic' ? defaults.PIC[sns] : idx));
					console.log(sns,idx,key);
					args.push(key + '=' + encodeURIComponent(val || ''));
				}
			});

			self.urls[sns] = defaults.PREFIX[sns]+args.join('&');
			console.log(args);
			if(depends){
				$(self.children).filter('[sns-data="'+ sns +'"]').attr('href',self.urls[sns]);
			}else{
				str += '<li><a sns-data="'+ sns +'" class="'+ sns +'-sns" href="'+ self.urls[sns] +'">'+ sns +'</a></li>';
			}
		});

		str && $(defaults.TEMPLATE).appendTo(opt.dom || document.body).append(str);
	};

	// SnsShare.prototype.bindEvent = function(opt){
	// 	var self = this,
	// 		screenWidth = screen.width,
	// 		screenHeight = screen.height,
	// 		width = opt.width || screenWidth / 2,
	// 		height = opt.height || screenHeight /2;

	// 	// $('.ui-sns-container').delegate('a','click',function(){
	// 	// 	window.open(self.urls[$(this).attr('data')],opt.title || '','toolbar=0,resizable=1,scrollbars=yes,status=1,width='+width+',height='+height+',left='+(screenWidth-width)/2+',top='+(screenHeight-height)/2);
	// 	// });
	// };
	module.exports = SnsShare;
})(window,require('common/web:jquery'));
// http://service.weibo.com/share/share.php?title=%E5%88%86%E4%BA%AB%E5%88%B0%E4%BA%BA%E4%BA%BA%E7%BD%91%E4%BB%A3%E7%A0%81+-+JiaThis%20%E5%85%B3%E4%BA%8E%E4%BA%BA%E4%BA%BA%E7%BD%91%EF%BC%9A%E4%BA%BA%E4%BA%BA%E7%BD%91%EF%BC%88http%3A%2F%2Fwww.renren.com%2F%EF%BC%89%E6%98%AF%E4%B8%AD%E5%9B%BD%E6%9C%80%E5%A4%A7%E7%9A%84%E5%AE%9E%E5%90%8D%E5%88%B6%E7%A4%BE%E4%BA%A4%E7%BD%91%E7%AB%99%EF%BC%8C%E5%B8%AE%E5%8A%A9%E4%BD%A0%E4%B8%8E%E6%9C%8B%E5%8F%8B%E3%80%81%E5%90%8C%E5%AD%A6%E3%80%81%E5%90%8C%E4%BA%8B%E3%80%81%E5%AE%B6%E4%BA%BA%E4%BF%9D%E6%8C%81%E6%9B%B4%E7%B4%A7%E5%AF%86%E7%9A%84%E8%81%94%E7%B3%BB%E3%80%82%E5%9C%A8%E8%BF%99%E9%87%8C%EF%BC%8C%E4%BD%A0%E5%8F%AF%E4%BB%A5%E5%86%99%E6%97%A5%E5%BF%97%E3%80%81%E4%BC%A0%E7%85%A7%E7%89%87%E3%80%81%E7%8E%A9%E6%B8%B8%E6%88%8F%EF%BC%8C%E4%B8%8E%E5%A5%BD%E5%8F%8B%E5%88%86%E4%BA%AB%E7%94%9F%E6%B4%BB%E7%82%B9%E6%BB%B4%E3%80%82%E5%BF%AB%E5%92%8C%E5%A5%BD%E5%8F%8B%E4%B8%80%E8%B5%B7%E4%B8%8A%E4%BA%BA%E4%BA%BA%E7%BD%91%EF%BC%8C%E7%8E%A9%E5%9C%A8%E4%B8%80%E8%B5%B7%EF%BC%8C%E5%BF%83%E5%9C%A8%E4%B8%80%E8%B5%B7%EF%BC%81%E2%80%BB%E5%A6%82%E4%BD%95%E7%BB%99%E8%87%AA%E5%B7%B1%E7%9A%84%E7%BD%91%E7%AB%99%E6%B7%BB%E4%B8%8A%5C%EF%BC%82%E5%88%86%E4%BA%AB%E5%88%B0%E4%BA%BA%E4%BA%BA%E7%BD%91%5C%EF%BC%82%E7%9A%84%E5%8A%9F%E8%83%BD%EF%BC%9F&url=http%3A%2F%2Ft.jiathis.com%2FzV1k&source=bookmark&appkey=2992571369&pic=&ralateUid=1647863564
// 'http://service.weibo.com/share/share.php?appkey={appid}&url={url}&pic={picurl}&searchPic=true&style=simple' //新浪微博
// 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url={url}&title={title}&pics=&summary=&desc=' //qq空间
// 'http://share.v.t.qq.com/index.php?c=share&a=index&title={title}&url=url&appkey=id&site=xxx&pic=xxx' //腾讯微博
// 'http://widget.renren.com/dialog/share?resourceUrl={url}&srcUrl={url}&title={title}&images={img}|{img}&description={content}' //人人
// 'http://connect.qq.com/widget/shareqq/index.html?url={url}&desc={content}&pics={image}&site={site}'//qq好友
//<a href="http://www.douban.com/share/service?updated=&amp;bm=1&amp;sel=&amp;v=1&amp;url=http%3A%2F%2Fzhuanlan.zhihu.com%2Fniceliving%2F20256770%23comments&amp;pic=http%3A%2F%2Fpic4.zhimg.com%2F59504d5d1d596bce6af184cb06f1f9b7_b.jpg&amp;title=%E5%88%86%E4%BA%AB%E5%86%85%E5%AE%B9&amp;title=%E5%88%86%E4%BA%AB%E6%A0%87%E9%A2%98&amp;url=http%3A%2F%2Fzhuanlan.zhihu.com%2Fniceliving%2F20256770%23comments&amp;image=http%3A%2F%2Fpic4.zhimg.com%2F59504d5d1d596bce6af184cb06f1f9b7_b.jpg&amp;text=%E5%88%86%E4%BA%AB%E5%86%85%E5%AE%B9" sns-data="db">db</a>
});