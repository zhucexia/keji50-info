define("components/mask/mask",["require","exports","module","components/jquery/jquery"],function(t){function o(t){this.options=e.extend({autoOpen:!0,container:n},t||{}),this.init()}var e=t("components/jquery/jquery"),i=document,n=i.body;return o.prototype={init:function(){var t=this,o=t.container=e(t.options.container);o[0]!=n&&!/fixed|absolute/.test(o.css("position"))&&o.css("position","relative"),t.mask=e('<div class="ui-mask">').hide().appendTo(t.container),t.options.autoOpen&&this.open(),e(window).resize(function(){t.resetPosition()})},open:function(){this.resetPosition(),this.mask.show()},close:function(){this.mask.hide()},resetPosition:function(){var t=this.container[0];this.mask.css({width:t.scrollWidth||i.documentElement.scrollWidth,height:t.scrollHeight||i.documentElement.scrollHeight})},destory:function(){this.destroy()},destroy:function(){this.mask.remove()}},o});