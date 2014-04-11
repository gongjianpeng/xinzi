//数据接口
var data = {
	domain:'http://localhost:8080/',
	cpanel : null,
	material : null,
	canvas:null,
	bgs:null,	
	usersel:{ //用户最终选中数据
			//curIdx:0,
			curpart:'',
			modelid:0,
			kuanshi:{id:0},
			boli:{id:0},
			zikuanshi:{id:0},	
			qichuang:{id:0},
			menkuangkuanshi:{id:0},menzhukuanshi:{id:0}, mentoukuanshi:{id:0},
			seban:{id:0},
			kaixiang:{id:0},			
			menhoudu:{id:0},caizhi:{id:0},
			qita:{id:0},		
			suoju:{id:0},suoxin:{id:0},menling:{id:0},maoyan:{id:0},xiadang:{id:0},lashoukuanshi:{id:0},jiaoliankuanshi:{id:0},bimenqi:{id:0},mentouj:{id:0}
			
			} 
	
};

//右边侧栏&门组合图片接口
data.getCpanelVal = function(d) {
	//data.loadJson('cpanel', 'data/door101b.txt');
	data.loadJson('cpanel',data.domain+'xinzi/pages/company/DoorJudge.do', d );
};

//左下标签内容列表
data.getMaterialVal = function(d) {
	//console.log(d);
	data.loadJson('material',data.domain+'xinzi/pages/company/sdoorCombo.do', d );
};

data.getMaterialKexuanVal = function(d) {
	
	data.loadJson('kexuan',data.domain+'xinzi/pages/company/getDoorJsonkexuan.do', d );
};

//quick find
data.getFindingVal = function(d) {
	
	data.loadJson('find',data.domain+'xinzi/pages/company/getDiyRightData.do', d );
};

data.getCanvasJson = function(){
	// data.loadJson('material', 'data/door101b.txt');
	data.canvas = data.cpanel.data;
	canvas.load();
};



//背景图片列表
data.getBGlist = function(){
	data.loadJson('bg',data.domain+'xinzi/pages/company/backpic.do');
};

//Demo测试用
data.getTest = function(){
	//http://60.191.235.254:8080/xinzi/pages/company/DoorJudge.do?dLength=2350&&dWidth=1000
	
	//data.loadJson('test', 'http://60.191.235.254:8080/xinzi/pages/company/DoorJudge.do');
};

data.postInfoVal = function(d) {
	
	data.sendloadJson('price', data.domain+'xinzi/pages/company/getdiyProduct.do', d);
};


data.postOrder = function(d) {
	data.sendloadJson('order', data.domain+'xinzi/pages/company/getdiyProductOrder.do', d);
	//data.sendloadJson('order', 'http://60.191.235.254:8080/xinzi/pages/company/getdiyProductOrder.do', d);
};

data.postAddcart = function(d) {
	data.sendloadJson('cart',data.domain+'xinzi/pages/company/getdiyProductaddCat.do', d);
	//data.sendloadJson('order', 'http://60.191.235.254:8080/xinzi/pages/company/getdiyProductOrder.do', d);
};

data.loadJson = function(type, url, dataset) {
	main.popupMsg();
	
	//console.log(url);
	//jQuery.support.cors = true; 
	var dt = 'json';
	if(type=='test' || type=='cpanel' || type=='bg' || type=='material' || type=='kexuan' || type=='find'){ //test mode!!!
		dt='jsonp';
	}
	
	console.log(dataset);
	$.ajax({
		type : 'GET',
		url : url,		
		dataType : dt,		
		//contentType: "application/json",	
		data: dataset, //"dLength=2350&dWidth=1000",
		success : function(json) {
			//console.log(json);
			switch(type) {
				case 'cpanel':
					data.cpanel = json.data[0];
					console.log('d1:',json.data[0]);
					console.log('d2:',json.data2);
					cpanel.create();
					//data.getCanvasJson();
					data.usersel.modelid = json.data2.id;
					data.usersel.seban.color = json.data2.color;
					data.canvas = json.data2.data;
					canvas.load();
	
					break;
				case 'bg':
					data.bgs = json.data;					
					break;
					
				case 'material':
					data.material = json.data;
					//console.log(json.data);
					material.create();
					main.closePopupMsg();
					
					break;
				case 'kexuan':
					//data.material = json.data;
					console.log(json.data);
					cpanel.createKexuan(json.data);
					//material.create();
					main.closePopupMsg();
					
					break;
				case 'find':
					console.log(json.data);
					break;
						
				case 'test':
					console.log(json);
					console.log(json.data[0],json.data[0][0]);
					break;
			}
			
			

		},
		error : function(error) {
			//console.log('error! ', error);
			switch(error.status){
				case 200:
					alert('请输入正确的门洞尺寸！');
				break;
				default:
				alert(error.statusText+', '+error.status);
			}
			main.closePopupMsg();
		}
	});
};

//POST Data
data.sendloadJson = function(type, url, d) {
	
	$.ajax({
		type : "POST",
		url : url,
		dataType : 'jsonp',	
		data : d,
		timeout:8000,
		success : function(json) {
			console.log(json);
			switch(type){
				case 'price':
					ui.displayPrice(json.data2[0]);
				break;
				case 'order':
					//todo
				break;
				case 'cart':
				     //todo
				break;
			}
			
		},
		error : function(error) {
			//console.log('error! ', error);					
			alert(error.statusText+', '+error.status);			
		}
	});
};

data.postURL = function(url, d) {
	//$.support.cors = true; 
	$.post( url, d, function( data ) {
	  console.log(data);
	});
	
};
