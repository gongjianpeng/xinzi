//左下标签内容列表
var material = {};

material.init = function() {
	//data.getMaterialVal();
};

material.create = function() {
	var d = data.material;
	material.clear();
	console.log(d);
	//for (var i = 0; i < d.length; i++) {

	var h = material.moduler(d);

	$('#material .manuList').append(h);

	material.addEvents($('#material .manuList'));
	//}

	if (d.length <= 1) {
		$('#prev_btn').hide();
		$('#next_btn').hide();
		//$('#material .manuList').hide();
	}

	//set default
	material.setListModuler(d[0].list);
};

material.clear=function(){
	$('#material .manuList').find('li').each(function(i) {
		$(this).off();
	});
	$('#material .manuList').empty();
};

material.moduler = function(data) {
	//var list = $('#material .manuList');
	var c = '';
	for (var i = 0; i < data.length; i++) {
		if (i == 0) {
			c += '<li class="li_cur" pid="' + data[i].id + '">' + data[i].name + '</li>';
		} else {
			c += '<li pid="' + data[i].id + '">' + data[i].name + '</li>';
		}

	}

	return c;
};

material.setListModuler = function(data) {
	// $('.rollbar-content').css('left','0px');

	var o = $('#example ul');
	o.empty();
	var c = '';
	for (var i = 0; i < data.length; i++) {
		// if (i == 0) {
			// c += '<li pid="' + data[i].id + '"><img alt="' + data[i].name + '" src="' + data[i].img + '" /><br/><em>拉花镂空双开门</em></li>';
		// } else {
			c += '<li pid="' + data[i].id + '"><img alt="' + data[i].name + '" src="' + data[i].img + '" /><br/><em>'+data[i].name+'</em></li>';
		// }

	}

	o.append(c);

	//fix rollbar position!
	$('.rollbar-handle').css('left', '0px');
	$('.rollbar-content').css('left', '0px');

	material.addListEvents();
};
// material.clearList = function(){
// }

material.addEvents = function(item) {
	item.find('li').each(function(i) {
		$(this).on('click', function() {
			$(this).addClass('li_cur').siblings().removeClass('li_cur');

			var d = data.material;
			material.setListModuler(d[i].list);
		});

	});

	//slider
	//$(".manuList li:first").addClass("cur");
	/*
	 $(".manuList li a").click(function(){
	 $(this).parent().addClass("cur").siblings().removeClass("cur");
	 var imgSrc_small = $(this).attr("Thumbnail");
	 //var imgSrc_big = $(this).attr("jqimg");
	 //$("#bigImg").attr({"src": imgSrc_small ,"jqimg": imgSrc_big });
	 return false;
	 });*/

	if ($(".manuList li").length > 5) {
		$("#View_list").imageScroller({
			next : "prev_btn",
			prev : "next_btn",
			frame : "imgFrame",
			width : 91,
			child : "li"
		});
		//$("#prev_btn,#next_btn").show();
	}

};

material.addListEvents = function() {
	var o = $('#example ul li');
	o.each(function(i) {
		$(this).on('click', function() {
			$(this).addClass('d_img_cur').siblings().removeClass('d_img_cur');
		});
	});
};
