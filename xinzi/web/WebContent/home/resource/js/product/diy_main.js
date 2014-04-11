//init
$(function($) {
	main.init();
});

var main = {
	full : false
};

//###init entry###
main.init = function() {
	// console.log($(document).height());
	data.getBGlist();
	
	ui.init();
	cpanel.init();
	material.init();
	canvas.init();
	
	console.log(data.usersel);

	//fullscreen
	$('#fullscreenBtn').click(function() {
		if (!main.full) {
			main.fullscreen(true);
		} else {
			main.fullscreen(false);
		}

	});

	$('#bgBtn').click(function() {
		main.changeBg();
	});

	//
	$(".pack_up").click(function() {
		if ($(".detail_body").is(":visible")) {
			$('.detail_body').slideUp();
			$('.pack_up em').text("展开");
		} else {
			$('.detail_body').slideDown();
			$('.pack_up em').text("收起");
		}

	});

	//rollbar start
	$('#example').rollbar({
		scroll : 'horizontal',
		lazyCheckScroll : 100,
		zIndex : 80,
		pathPadding : '0px'

	});

	//popup
	$('.dialog-box').click(function() {
		$('.dialog-box, .dialogs').hide();
	});
	
	//resize event
	$(window).resize(function(){
	  	main.changeBgSize();
	});
	
	//console.log('width: ',$('.jqzosub ').width(),$('#imgFrame').width());
};

main.fullscreen = function(act) {
	// console.log($(".detail_body"));
	if (act) {
		$('.detail_body').hide();
		$('.pack_up em').text("展开");

		$('#cpanel').hide();
		$('#category').css('margin-right', '0px');
		$('#fullscreenBtn').html('恢复显示');
		
		//set canvas height
		$('#material').hide();
		main.changeBgSize();
		main.full = true;
		
		// console.log($(document).height());
	} else {
		$('.detail_body').slideDown();
		$('.pack_up em').text("收起");

		$('#cpanel').show();
		$('#category').css('margin-right', '420px');
		$('#fullscreenBtn').html('全屏显示');
		$('#material').show();
		
		//restore canvas height
		$('.door').height(500);
		$('.door_bg').height(500);
		main.full = false;
	}
};

main.changeBgSize = function(){
	var h = $(document).height()-189;
	if(main.full){
		 $('.door').height(h);
		 $('.door_bg').height(h);
	}
	
};

main.changeBg = function() {
	var n = randomRage(0, data.bgs.length - 1);
	// console.log(n);
	$('.door_bg').css('background-image', 'url(' + data.bgs[n].img + ')');
};

//弹出消息窗口
main.popupMsg = function() {
	var docmH = $(document).height();
	$(".dialogs").height(docmH);
	$(".dialog-box,.dialogs").show();
};

//关闭消息窗口
main.closePopupMsg = function(){};

function randomRage(min, max) {
	var r = Math.floor(Math.random() * (max - min + 1)) + min;
	return r;

}
