//左下标签内容列表
var material = {
	typeid:0
};

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

	// console.log(d.length);
	if (d.length <= 1) {
		$('#prev_btn').hide();
		$('#next_btn').hide();
		//$('#material .manuList').hide();
	}else{
		$('#prev_btn').show();
		$('#next_btn').show();
		
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
	//console.log(data);
	var c = '';
	for (var i = 0; i < data.length; i++) {
		if (i == 0) {
			c += '<li class="li_cur" pid="' + data[i].id + '" typename="' + data[i].typename + '">' + data[i].name + '</li>';
		} else {
			c += '<li pid="' + data[i].id + '" typename="' + data[i].typename + '">' + data[i].name + '</li>';
		}

	}

	return c;
};

//生成最终选择列表
material.setListModuler = function(data) {
	// $('.rollbar-content').css('left','0px');

	var o = $('#example ul');
	o.empty();
	var c = '';
	for (var i = 0; i < data.length; i++) {
		// if (i == 0) {
			// c += '<li pid="' + data[i].id + '"><img alt="' + data[i].name + '" src="' + data[i].img + '" /><br/><em>拉花镂空双开门</em></li>';
		// } else {
			
		// }
		
		if(material.checkTypename('seban')){
			c += '<li pid="' + data[i].id + '" color="' + data[i].color + '"><img alt="' + data[i].name + '" src="' + data[i].img + '" /><br/><em>'+data[i].name+'</em></li>';
		}else{
			c += '<li pid="' + data[i].id + '"><img alt="' + data[i].name + '" src="' + data[i].img + '" /><br/><em>'+data[i].name+'</em></li>';
		}

	}

	o.append(c);

	//fix rollbar position!
	$('.rollbar-handle').css('left', '0px');
	$('.rollbar-content').css('left', '0px');

	material.addListEvents();
};
// material.clearList = function(){
// }

//更新选中部件名称
material.updateTypename = function(id){
	
	var name = $('#accordion h3').eq(cpanel.idx).attr('typename');
	if(name=='bancai' || name=='peijian' || name=='biankkuangyangshi' ){
		var pn = $('#material .manuList li').eq(id).attr('typename');
		data.usersel.curpart = pn;
	}
	
};

material.checkTypename= function(n){
	var name = $('#accordion h3').eq(cpanel.idx).attr('typename');
	if(name==n){
		return true;
	}else{
		return false;
	}
};

material.addEvents = function(item) {
	material.updateTypename(0);
	
	item.find('li').each(function(i) {
		$(this).on('click', function() {
			$(this).addClass('li_cur').siblings().removeClass('li_cur');

			var d = data.material;
			material.setListModuler(d[i].list);
			material.updateTypename(i);
			material.typeid = i;
			// console.log('label id:',$(this).attr('typename'));
		});

	});
	

	if ($(".manuList li").length > 5) {
		$("#View_list").imageScroller({
			next : "prev_btn",
			prev : "next_btn",
			frame : "imgFrame",
			width : 91,
			child : "li"
		});
		
	}

};

//更新部件数据
material.addListEvents = function() {
	var o = $('#example ul li');
	o.each(function(i) {
		$(this).on('click', function() {
			
			console.log(data.usersel.curpart, $(this).attr('pid'), $(this).find('img').attr('src'),$(this).find('em').html());
			var d = {typename:data.usersel.curpart, id:$(this).attr('pid'), img:$(this).find('img').attr('src')};
			// updates.push();	
			if(material.checkTypename('seban')){
				d=$(this).attr('color');
				if(d==''){
					// console.log(material.typeid, i);
					d = data.material[material.typeid].list[i].list;
					//material.getNewcolorList($(this).attr('pid'));
				}
				data.usersel[data.usersel.curpart] = {id:$(this).attr('pid'), name:$(this).find('em').html(),color:d};
				canvas.updateColorboard(d);
				
			}else{
				canvas.updateParts(d);
				data.usersel[data.usersel.curpart] = {id:$(this).attr('pid'), name:$(this).find('em').html(),img:$(this).find('img').attr('src')};
			}		
			
			
			cpanel.updateDef($(this).find('em').html());				
			$(this).addClass('d_img_cur').siblings().removeClass('d_img_cur');
		});
	});
};

material.getNewcolorList = function(id){
	
};

