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
	data.getCpanelVal({dWidth:parseInt($('#size_w').val()),dLength:parseInt($('#size_h').val())}); //debug mode!!!
	
	$('#submitInfo').click(function() {
		
		//data.postInfoVal(ui.data);
		
		if(parseInt($('#size_w').val())>0 && parseInt($('#size_h').val())>0){
			main.popupMsg();
			var d = {dWidth:parseInt($('#size_w').val()),dLength:parseInt($('#size_h').val())};
			data.getCpanelVal(d);
		}else{			
			alert('请输入门洞尺寸！');
		}
		
		
		//data.getTest();
	});
	
	//calculate total price API
	$('#calcBtn').click(function(){
		$('#calculator').show();
	});
	
	$('#orderBtn').click(function(){
		canvas.saveImg(); //test 
	});
};

