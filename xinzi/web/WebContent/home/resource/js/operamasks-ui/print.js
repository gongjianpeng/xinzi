	var LODOP; //声明为全局变量   	
	function Print(){
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  
		LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_按网址打印");
		var strBodyStyle="<style>input {border:0px;}.button09 {display:none;}</style>";
		var strBodyHtml=strBodyStyle+"<body>"+$("#printDiv").html()+"</body>";
		LODOP.ADD_PRINT_HTM(0,0,600,900,strBodyHtml);
		//LODOP.ADD_PRINT_URL(30,20,746,"100%",location.href);
		LODOP.SET_PRINT_STYLEA(0,"HOrient",3);
		LODOP.SET_PRINT_STYLEA(0,"VOrient",3);
		LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT","Width:90%");
//		LODOP.SET_SHOW_MODE("MESSAGE_GETING_URL",""); //该语句隐藏进度条或修改提示信息
//		LODOP.SET_SHOW_MODE("MESSAGE_PARSING_URL","");//该语句隐藏进度条或修改提示信息
		LODOP.PREVIEW();			
	};
		