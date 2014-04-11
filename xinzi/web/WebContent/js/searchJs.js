(function($) {
	$.fn.plusTab = function(options) {
		//定义要用的参数
		var opts = {
			opt_1:'cur',
			opt_2:'.tabBox',
			opt_3:'hide'
		};
		var opt = $.extend(opts, options);
		return this.each(function() {
			var _obj = $(this);
			_obj.click(function(e) {
				e.stopPropagation();
				_obj.addClass(opt.opt_1).siblings().removeClass(opt.opt_1);
				var i=_obj.index();
				$(opt.opt_2+ '> div').eq(i).removeClass(opt.opt_3).siblings().addClass(opt.opt_3);
			});
		});
		return this;
	}
})(jQuery);


$(function() {	
	$(".recorList li").plusTab({ opt_1:'cur',opt_2:'.recorBox'});
	
	$(".Search").click(function(){
		$('.Search_wap').toggle();
		$(".header").toggleClass('headt');
		$(".regMargin").toggleClass('marTop');
	});
	
	
	var t = $(".num_text");	
	$(".Synops_plus").click(function(){		
		t.val(parseInt(t.val())+1);
		if (parseInt(t.val())!=1){
			$('.Synops_reduce').attr('disabled',false);
		}
	});	
	$(".Synops_reduce").click(function(){
		t.val(parseInt(t.val())-1);		
		if (parseInt(t.val()) <=1){
			$('.Synops_reduce').attr('disabled',true);
			t.val(1)
		}
	});
	
	$(".proceTab .Nodis").each(function(i) {
		var num = i+1
		$(this).attr("id", "checkbox-" + num);
		var _id = $(this).attr("id");
		$('.iCheck label').eq(i).attr("for",_id);
		$('.pro_num input').eq(i).attr("id",_id); //添加数量 id 值
		if($(this).attr("checked")=="checked"){
			$('.iCheck label').eq(i).addClass('checked');
		}		
		$('.proceTab .iCheck label').eq(i).on("click",function() {
			var _this = $(this);
			_this.toggleClass('checked');
		});
	});
	countChecked();
	$( ".proceTab .iCheck .Nodis" ).on( "click", countChecked );
	
});
function countChecked(){
	var total = 0;
	$('.Nodis:checked').each(function(){
		var inputNum = $(this).parents().parents().find(".pro_num input").val();
		// alert(inputNum);
		total = total + (parseInt($(this).attr('price')))*inputNum;
	});		
	$( ".totalNum" ).html((total).toFixed(2));
}

function upperCase(x)
{
var y=x.value
x.value = x.value.replace(/\D/g,'')
countChecked();
}
