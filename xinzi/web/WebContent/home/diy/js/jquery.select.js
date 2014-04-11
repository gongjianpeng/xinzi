(function($) {
	$.fn.plusSelect = function(options) {
		//定义要用的参数
		var opts = {
			opt_1 : '.selectauto',
			opt_2 : '#'+$(this).attr('id')+' a',
			opt_3 : '#'+$(this).attr('id')+' em',
			opt_4 : 'input[name='+$(this).attr('id')+']',
			onclick: function(){}

		};
		//合并参数供下面调用
		var opt = $.extend(opts, options);
		//fn内容开始

		this.each(function() {
			var _obj = $(this);
			_obj.click(function(e) {
				//console.log(_obj);
				e.stopPropagation();
				$(opt.opt_1).hide();
				_obj.find(opt.opt_1).show();
			})
		});

		$(document).bind("click", function() {
			$(opt.opt_1).hide();
		});
		
		// console.log($(opt.opt_2));
		$(opt.opt_2).each(function(i){
			$(this).on('click', function(e){
				//console.log(e.target);
				e.stopPropagation();
				var thisHtml = $(this).html();
				$(opt.opt_3).html(thisHtml);
				$(opt.opt_1).hide();
				$(this).addClass("cur").siblings().removeClass("cur");
				$(opt.opt_4).val($(this).attr('Svalue'));
				
				opt.onclick();
				
			})
		});

	/*
		$(opt.opt_2).click(function(e) {
				console.log(opt.opt_2);
				e.stopPropagation();
				var thisHtml = $(this).html();
				$(opt.opt_3).html(thisHtml);
				$(opt.opt_1).hide();
				$(this).addClass("cur").siblings().removeClass("cur");
	
			})*/
	

		
		//$(opt.opt_2).click(function() {
					//console.log('click!');
					
				//});
		
		//return this;
	}
})(jQuery);

