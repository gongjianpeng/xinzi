//整体界面交互控制
var ui = {
	data : {
		brand : '',
		id : '',
		model : '',
		size : '',
		quantity : 0,
		height : 0,
		thickness : 0
	}
};

ui.init = function() {
	//data.getCpanelVal({dWidth:parseInt($('#size_w').val()),dLength:parseInt($('#size_h').val())}); //debug mode!!!
	// main.entry();
	
	$('#submitInfo').click(function() {
		
		//data.postInfoVal(ui.data);
		
		if(parseInt($('#size_w').val())>0 && parseInt($('#size_h').val())>0){
			main.popupMsg();
			$('.door_content').show();
			var d = {dWidth:parseInt($('#size_w').val()),dLength:parseInt($('#size_h').val())};
			data.getCpanelVal(d);
		}else{			
			alert('请输入门洞尺寸！');
		}
		
		
		//data.getTest();
	});
	
	//calculate total price API
	$('#calcBtn').click(function(){
		//$('#calculator').show();
		//console.log(data.usersel.kuanshi);
		//canvas.saveImg(); //test 
		var str = canvas.saveImg();
		var d = ui.getDataset();
		//d.img = str;
	    // {img:str};
	//	var d = ui.getDataset();
		console.log(d);
		data.postInfoVal(d);
		
	});
	
	$('#orderBtn').click(function(){	
		
		
		var d = ui.getDataset();
		var str = canvas.saveImg();
		//var d={};
		d.img = str;
	    // {img:str};
		//console.log('order: ',d.pp,d.img);
		data.postOrder(d);
	});
	
	$('#addcartBtn').click(function(){
	
		var d = ui.getDataset();
		var str = canvas.saveImg();
	
		d.img = str;
		//console.log(str);
		data.postAddcart(d);
	});
	
	
};

ui.getDataset = function(){
	var d = {pp:$('#brand').val(),
			mx:data.usersel.modelid,
			num:$('#quantity').val(),
			mh:$('#size_h').val(),
			mw:$('#size_w').val(),
			mhd:$('#thickness').val(),
			ks:data.usersel.kuanshi.id,
			zks:data.usersel.zikuanshi.id,
			bl:data.usersel.boli.id,
			qc:data.usersel.qichuang.id,
			bmk:data.usersel.menkuangkuanshi.id,
			bmt:data.usersel.mentoukuanshi.id,
			bmz:data.usersel.menzhukuanshi.id,
			sb:data.usersel.seban.id,
			kx:data.usersel.kaixiang.id,
			bhd:data.usersel.menhoudu.id,
			bcz:data.usersel.caizhi.id,
			qlz:data.usersel.qita.id,
			psj:data.usersel.suoju.id,
			psx:data.usersel.suoxin.id,
			pml:data.usersel.menling.id,
			pmy:data.usersel.maoyan.id,
			pxd:data.usersel.xiadang.id,
			pls:data.usersel.lashoukuanshi.id,
			pjl:data.usersel.jiaoliankuanshi.id,
			pbmq:data.usersel.bimenqi.id,
			ptouj:data.usersel.mentouj.id
			
			};
		return d;
};

//显示价格
ui.displayPrice = function(d){
	$('#price1').html('￥'+d.sp+'.00');
	$('#pnum').html(d.number);
	$('#price2').html('￥'+d.tp+'.00');
	$('#calculator').show();
};


