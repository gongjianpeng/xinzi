$(function(){
	$(".wbk").focus(function(){
		$(this).addClass("inputFocus");
	}).blur(function(){
		$(this).removeClass("inputFocus");
	});
	})
	
	
//首页banner切换
$(function() {
	var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积）
	var len = $("#focus ul li").length; //获取焦点图个数
	var index = 0;
	var picTimer;

	//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
	$("#focus ul").css("width",sWidth * (len));
	
	//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
	$("#focus").hover(function() {
		clearInterval(picTimer);
	},function() {
		picTimer = setInterval(function() {
			showPics(index);
			index++;
			if(index == len) {index = 0;}
		},4000); //此4000代表自动播放的间隔，单位：毫秒
	}).trigger("mouseleave");
	
	//显示图片函数，根据接收的index值显示相应的内容
	function showPics(index) { //普通切换
		var nowLeft = -index*sWidth; //根据index值计算ul元素的left值
		$("#focus ul").stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
		//$("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
	}
	var text_val=['','手机号码','固定电话','','','','送货前确认是否在家']
	$('.text').focus(function(){
		var index=$(this).attr('index');
		if($(this).val()==text_val[index]){
			$(this).val('').css('color','#666');
		}
		$(this).siblings('div').text('');
	})
	$('.text').blur(function(){
		var index=$(this).attr('index');
		if($(this).val()==text_val[index]||$(this).val()==''){
			$(this).val(text_val[index]).css('color','#999');
		}	
	})
	$('.tab_reg li').click(function(){
		var _class=$(this).attr('name');
		$(this).addClass('hover').siblings('li').removeClass('hover').parents('.tab_reg').attr('class','tab_reg '+_class);
		$('.reg_form .'+_class).show().siblings('ul').hide();	
	})
});
