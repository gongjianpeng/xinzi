//右侧部件分类列表
var cpanel = {
	cid : 0, //1st level currentID
	tid : 0, //2nd level
	idx: 0
};

cpanel.init = function() {
	//data.getCategoryVal();
	$("#accordion").accordion();
};

cpanel.clearAll = function(){
	$('#accordion').accordion('destroy');
	$('#accordion').empty();
	
};

cpanel.create = function() {
	//console.log(data.cpanel.category);
	cpanel.clearAll();
	
	var c = data.cpanel;
	for (var i = 0; i < c.length; i++) {
		var l = '';
		//console.log(c[i].list);
		if (c[i].list != '') {
			var n = 'clist_' + i;
			l = cpanel.createList(n, c[i].list);
		}

		// console.log(c[i]);
		var h = '';
		if(cpanel.checkType(c[i].typename) ){
			h = cpanel.moduler(i+1 , c[i].name,'', c[i].def, c[i]);
		}else{
			h = cpanel.moduler(i+1 , c[i].name, l, c[i].def, c[i]);
		}
		
		
		// var h = cpanel.moduler(i + 1, c[i]);

		$('#accordion').append(h);

		if (c[i].list != '') {
			cpanel.addEvents(n); 
		}

	}

	$("#accordion").accordion({
		activate : function(event, item) {
			// console.log($(item.newPanel).find('input[name=clist_0]').val());
			cpanel.selector(item);
		}
	});
	
	cpanel.addSubmitEvents();
	
	cpanel.setDefault();
	
};

//默认选中第一个栏目
cpanel.setDefault = function(){
	var n = data.cpanel[0].list.data[0].typename;
	data.getMaterialVal({chstyle:n});
	
	data.usersel.curIdx = 0;
	//console.log(data.cpanel[0].typename);
	data.usersel.curpart = data.cpanel[0].typename;
	
};

//select 1st level
cpanel.selector = function(item) {
	//var header = $('#accordion').accordion( "option", "header" );
	var id = $(item.newHeader).first().attr('cid');
	var name = $(item.newHeader).first().attr('typename');	
	cpanel.idx = $(item.newHeader).first().attr('idx')-1;
	console.log('main select: ',id, name);
	
	if(cpanel.checkType(name)){
		if(name!='zikuanshi'){
			data.getMaterialVal({chstyle:name,id:data.usersel.modelid });
		}else{
			//data.getMaterialVal({chstyle:name,id:data.usersel.modelid });
		}
		
	}
	
	//更新选中部件名称
	if(name!='bancai' || name!='peijian'){
		data.usersel.curpart = name;
	}
	
	
};

cpanel.updateDef = function(value){
	var p = $('#accordion h3').eq(cpanel.idx);
	p.find('.userdef').html(value);
	// console.log(cpanel.idx);
	
};

cpanel.checkType = function(name){
	if(name=='qichuang' || name=='seban' || name=='kaixinag' || name=='qita' || name=='peijian' || name=='kaixiang' || name=='bancai'){
		return true;
	}else{
		return false;
	}
};

cpanel.moduler = function(index, name, list, value, dat) {
	var c = '';
	var _id = 'inputxt_'+index;
	var _btnid = 'submit_'+index;
	
	if (list != '') {
		c = '<input name="" id="'+_id+'" type="text" class="gray_yes w_120  fl" value="" />';
	} else {
		c = '<input name="" id="'+_id+'" type="text" class="gray_yes w_120 m_l_197 fl" value="" />';
	}
	
	var _html = '<h3 idx="' + index + '" cid="' + dat.id + '" typename="' + dat.typename + '"><em class="fl"><i>' + index + '</i><b>' + name + '</b></em><em class="fr p_r_30"><a class="userdef" href="javascript:void(0)">' + value.name + '</a></em></h3>' + '<div class="d_s_body"><ul class="styles_body"><li class="clearfix">' + list + c + '<input name="" id="'+_btnid+'" act="'+_id+'" type="button" value="确定" class="qd_btn02 fr submit"/>' + '</li></ul></div>';
	return _html;
};


cpanel.addSubmitEvents = function(){
	//add submit button events
	$('#accordion div').each(function(i) {
		//$(this).find('.submit').off();
		//console.log(i, $(this).find('#submit_'+(i+1)));
		var btn = $(this).find('.submit');
			
	    btn.on('click', function(){
	    	console.log(data.usersel.curpart, $('#'+btn.attr('act')).val());
	    	var t = $('#'+btn.attr('act')).val();	
	    	if(t!=''){
	    		
	    		var d = {inputname:t, typename:data.usersel.curpart};
	    		data.getFindingVal(d);
	    	}
	    	
	    });
	});
};




//创建下拉列表
cpanel.createList = function(name, list) {
	var l = '';
	for (var i = 0; i < list.data.length; i++) {
		// l += '<a href="javascript:void(0)" Svalue="'+list.data[i].id+'">'+list.data[i].name+'</a>';
		l += '<a href="javascript:void(0)" Svalue="' + list.data[i].typename + '">' + list.data[i].name + '</a>';
	}
//	console.log(name,list.data[0]);
	var dn = '';
	if(list.data[0]!=undefined){
		dn = list.data[0].name;
	}
	var c = '<i class="fl p_r_5">' + list.name + '</i>' + '<div id="' + name + '" class="deliv2 m_r_14 fl">' + '<input type="hidden" name="' + name + '" />' + '<div class="shtitle styles_c fl">' + '<em>' + dn + '</em>' + '<div class="selectauto">' + l + '</div>' + '</div>' + '</div>';

	return c;
};

cpanel.createSecondList = function(name, list) {
	var l = '';
	for (var i = 0; i < list.data.length; i++) {
		// l += '<a href="javascript:void(0)" Svalue="'+list.data[i].id+'">'+list.data[i].name+'</a>';
		l += '<a href="javascript:void(0)" Svalue="' + list.data[i].id + '">' + list.data[i].name + '</a>';
	}
	var c = '<li class="clearfix"><i class="fl p_r_5">' + list.name + '</i>' + '<div id="' + name + '" class="deliv2 m_r_14 fl">' + '<input type="hidden" name="' + name + '" />' + '<div class="shtitle styles_c fl">' + '<em>' + list.data[0].name + '</em>' + '<div class="selectauto">' + l + '</div>' + '</div>' + '</div></li>';

	return c;
};

cpanel.createKexuan = function(d){
	
	var dat = d[0];
	//for(var i=0;i<dat.length;i++){			
			//if(dat[i].typename==value){				
				//console.log('dat: ',dat);
				//if(dat[i].list[0]!=undefined){
					var l = cpanel.createkexuanSecondList('clist_1' + '_1', dat);
					var p = $('.styles_body').eq(cpanel.idx);
					p.append(l);					
					cpanel.addSecondListEvents('clist_1' + '_1');
				//}
				//break;
			//}
	//}
};


//子款式可选
cpanel.createkexuanSecondList = function(name, list) {
	var l = '';
	for (var i = 0; i < list.list.length; i++) {
		// l += '<a href="javascript:void(0)" Svalue="'+list.data[i].id+'">'+list.data[i].name+'</a>';
		l += '<a href="javascript:void(0)" Svalue="' + list.list[i].typename + '">' + list.list[i].name + '</a>';
	}
	var c = '<li class="clearfix"><i class="fl p_r_5">' + list.name + '</i>' + '<div id="' + name + '" class="deliv2 m_r_14 fl">' + '<input type="hidden" name="' + name + '" />' + '<div class="shtitle styles_c fl">' + '<em>' + list.list[0].name + '</em>' + '<div class="selectauto">' + l + '</div>' + '</div>' + '</div></li>';

	return c;
};



cpanel.addEvents = function(name) {

	$('#' + name).plusSelect({
		onclick : function() {
			//console.log('2rd selected: ',$('#'+name).find('input[name='+name+']').val());
			var v = $('#' + name).find('input[name=' + name + ']').val();
			cpanel.listSelect(name, v);
			return false;
		}
		//opt_2:'#'+name+' a',
		//opt_3:'#'+name+' em',
		//opt_4: 'input[name='+name+']'
	});

	cpanel.updateDepth();
	
	

};

cpanel.updateDepth = function() {
	$(".shtitle").each(function(i) {
		$('.shtitle').eq(i).css({
			"z-index" : 100 - i
		});
	});
};

//主分类中第2个下拉列表！
cpanel.addSecondListEvents = function(name) {

	$('#' + name).plusSelect({
		onclick : function() {

			var val = $('#' + name).find('input[name=' + name + ']').val();
			
			if(data.usersel.curpart=='kuanshi'){
				data.usersel.boli = {id:val, name:''};
			}else if(data.usersel.curpart=='zikuanshi'){
				data.getMaterialVal({chstyle:'kexuan', typename:val});
			}
			console.log(data.usersel, ' | 2nd select: ',name, val);
			return false;
			// cpanel.listSelect(name, v);
		}
	});

	cpanel.updateDepth();
};

//2nd level select
cpanel.listSelect = function(name, value) {

	// var o = data.cpanel.category[0].list.data[1].list;
	console.log('2/list: ',name,value);
	if(value=='peitao' ){
		data.getMaterialVal({chstyle:value, typename:'yahua'});
	}else if(value=='kexuan'){
		data.getMaterialKexuanVal({chstyle:value});
	}else if(value=='menkuangkuanshi' || value=='mentoukuanshi'){
		data.getMaterialVal({chstyle:value, modeldibuId:data.usersel.modelid});
	}else{
		data.getMaterialVal({chstyle:value});
	}
	
	
	//console.log('1st select: ',name, value, data.cpanel[cpanel.idx].list.data);
	var dat = data.cpanel[cpanel.idx].list.data;
	
	cpanel.clearSecondList();
	if(value=='pinjie'){	
		for(var i=0;i<dat.length;i++){			
			if(dat[i].typename==value){				
				console.log('dat: ',dat[i].list[0]);
				if(dat[i].list[0]!=undefined){
					var l = cpanel.createSecondList(name + '_1', dat[i].list[0]);
					var p = $('.styles_body').eq(cpanel.idx);
					p.append(l);					
					cpanel.addSecondListEvents(name + '_1');
				}
				break;
			}
		}
	}
	
	/*
	var o = data.cpanel[cpanel.idx].list.data[value].list;
			
			
		
			if (o != '') {
				
		
			} */
	
	
};


cpanel.clearSecondList=function(){
	//console.log(cpanel.idx); 
	var p = $('.styles_body').eq(cpanel.idx); 
			if (p.find('li').length > 1) {
				p.find('li').last().remove();
	}
};

//jump to next level
cpanel.gotoList = function() {

};
