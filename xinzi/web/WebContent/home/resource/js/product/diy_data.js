//数据接口
var data = {
	cpanel : null,
	material : null,
	canvas:null,
	bgs:null,
	usersel:{ //用户最终选中数据
			modelid:0,
			kuanshi:{},
			boli:{},
			zikuanshi:{},	
			qichuang:{},
			biankk:{}, 
			seban:{},
			kaixiang:{},
			bancai:
				{menhoudu:{},caizhi:{}},
			qita:
				{lizhu:{}},		
			peijian:
				{suoju:{},suoxin:{},menling:{},maoyan:{},xiadang:{},lashoukuanshi:{},jiaoliankuanshi:{}}			
			} 
	
};

//右边侧栏&门组合图片接口
data.getCpanelVal = function(d) {
	//data.loadJson('cpanel', 'data/door101b.txt');
	data.loadJson('cpanel','http://60.191.235.254:8080/xinzi/pages/company/DoorJudge.do', d );
};

//左下标签内容列表
data.getMaterialVal = function(d) {
	//console.log(d);
	data.loadJson('material','http://60.191.235.254:8080/xinzi/pages/company/sdoorCombo.do', d );
};

data.getCanvasJson = function(){
	// data.loadJson('material', 'data/door101b.txt');
	data.canvas = data.cpanel.data;
	canvas.load();
};



//背景图片列表
data.getBGlist = function(){
	data.loadJson('bg','http://60.191.235.254:8080/xinzi/pages/company/backpic.do');
};

//Demo测试用
data.getTest = function(){
	//http://60.191.235.254:8080/xinzi/pages/company/DoorJudge.do?dLength=2350&&dWidth=1000
	
	//data.loadJson('test', 'http://60.191.235.254:8080/xinzi/pages/company/DoorJudge.do');
};

data.postInfoVal = function(d) {
	
	data.sendloadJson('info', d);
};

data.loadJson = function(type, url, dataset) {
	//console.log(url);
	//jQuery.support.cors = true; 
	var dt = 'json';
	if(type=='test' || type=='cpanel' || type=='bg' || type=='material'){ //test mode!!!
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
			
		}
	});
};

data.sendloadJson = function(type, d) {
	
	$.ajax({
		type : "POST",
		url : "some.php",
		data : {},
		success : function(msg) {
			console.log("Data Saved: " + msg);
		}
	});
};
