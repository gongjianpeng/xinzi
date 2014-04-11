//右侧部件分类列表
var cpanel = {
	cid : 0, //1st level currentID
	tid : 0, //2nd level
	idx: 0
};

cpanel.init = function() {
	//data.getCategoryVal();

};

cpanel.create = function() {
	//console.log(data.cpanel.category);
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
			h = cpanel.moduler(i + 1, c[i].name,'', c[i].def, c[i]);
		}else{
			h = cpanel.moduler(i + 1, c[i].name, l, c[i].def, c[i]);
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
	
	cpanel.setDefault();
	
};

//默认选中第一个栏目
cpanel.setDefault = function(){
	var n = data.cpanel[0].list.data[0].typename;
	data.getMaterialVal({chstyle:n});
};

//select 1st level
cpanel.selector = function(item) {
	//var header = $('#accordion').accordion( "option", "header" );
	var id = $(item.newHeader).first().attr('cid');
	var name = $(item.newHeader).first().attr('typename');	
	cpanel.idx = $(item.newHeader).first().attr('idx');
	console.log('main select: ',id, name);
	
	if(cpanel.checkType(name)){
		data.getMaterialVal({chstyle:name,id:data.usersel.modelid });
	}
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
	if (list != '') {
		c = '<input name="" type="text" class="gray_yes w_120  fl" value="" />';
	} else {
		c = '<input name="" type="text" class="gray_yes w_120 m_l_197 fl" value="" />';
	}
	
	var _html = '<h3 idx="' + index + '" cid="' + dat.id + '" typename="' + dat.typename + '"><em class="fl"><i>' + index + '</i><b>' + name + '</b></em><em class="fr p_r_30"><a href="javascript:void(0)">' + value.name + '</a></em></h3>' + '<div class="d_s_body"><ul class="styles_body"><li class="clearfix">' + list + c + '<input name="" type="button" value="确定" class="qd_btn02 fr"/>' + '</li></ul></div>';
	return _html;
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
		l += '<a href="javascript:void(0)" Svalue="' + i + '">' + list.data[i].name + '</a>';
	}
	var c = '<li class="clearfix"><i class="fl p_r_5">' + list.name + '</i>' + '<div id="' + name + '" class="deliv2 m_r_14 fl">' + '<input type="hidden" name="' + name + '" />' + '<div class="shtitle styles_c fl">' + '<em>' + list.data[0].name + '</em>' + '<div class="selectauto">' + l + '</div>' + '</div>' + '</div></li>';

	return c;
};

cpanel.addEvents = function(name) {

	$('#' + name).plusSelect({
		onclick : function() {
			//console.log('2rd selected: ',$('#'+name).find('input[name='+name+']').val());
			var v = $('#' + name).find('input[name=' + name + ']').val();
			cpanel.listSelect(name, v);
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

			var v = $('#' + name).find('input[name=' + name + ']').val();
			console.log('2nd select: ',name, v);
			// cpanel.listSelect(name, v);
		}
	});

	cpanel.updateDepth();
};

//2nd level select
cpanel.listSelect = function(name, value) {

	// var o = data.cpanel.category[0].list.data[1].list;
	// console.log(name,value);
	data.getMaterialVal({chstyle:value});
	
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
	var p = $('.styles_body').eq(cpanel.idx);
			if (p.find('li').length > 1) {
				p.find('li').last().remove();
	}
};

//jump to next level
cpanel.gotoList = function() {

};
