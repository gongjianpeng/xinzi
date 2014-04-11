<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="../themes/apusic/om-all.css" />
		<link href="../css/css.css" rel="stylesheet" type="text/css" />
		
		<!-- by make start -->
		<link href="../css/all_css.css" rel="stylesheet" type="text/css" />
		
		<!-- by make end -->
		<script src="../jquery/js/jquery.min.js"></script>
		<script src="../ui/om-core.js"></script>
		<script src="../ui/om-mouse.js"></script>
		<script src="../ui/om-tree.js"></script>
		<script src="../ui/om-menu.js"></script>
		<script src="../ui/om-draggable.js"></script> 
		<script src="../ui/om-position.js"></script>
		<script src="../ui/om-resizable.js"></script>
		<script src="../ui/om-dialog.js"></script>
		<script src="../ui/om-messagebox.js"></script>
		<script src="../ui/om-messagetip.js"></script>
		<script src="../ui/om-buttonbar.js"></script>
		<script src="../ui/om-button.js"></script>
		<script src="../ui/om-ajaxsubmit.js"></script>
		<script src="../ui/om-grid.js"></script>
		<script src="../ui/om-grid-headergroup.js"></script>
		<script src="../ui/om-validate.js"></script>
		<script src="../ui/om-combo.js"></script>
		<script src="../ui/om-calendar.js"></script>
		<script src="../ui/om-panel.js"></script>
		<script src="../ui/om-tabs.js"></script>
		<script src="../ui/om-borderlayout.js"></script>
		<script src="../ui/om-fileupload.js"></script>
		<style>
html,body {
	font-size: 16px;
	font-family: tahoma;
	border: 0px;
	padding: 0px;
	margin: 0px;
	height: 100%;
	width: 100%;
	overflow: hidden;
}

div#confirm {
	height: 25px;
	line-height: 25px;
	font-weight: bold;
}

div#tipCon {
	height: 25px;
	line-height: 25px;
	font-weight: bold;
}

.om-grid div.hDiv td {
	text-align: center;
}

.om-grid img {
	cursor: pointer;
	vertical-align: middle !important;
	margin-top: -5px;
	padding: 0px;
	border: 0px;
}

.om-grid a {
	cursor: pointer;
	vertical-align: middle !important;
	font-weight: normal;
	font-size: 12px;
}

.om-grid div.bDiv td {
	vertical-align: middle !important;
	cursor: pointer;
}

.search {
	background-image: url(../images/icons/search.gif) !important;
}

input[type='text'] {
	margin: 0px;
	padding: 0px;
	height: 18px;
	width: 140px
}
</style>
		<script>
		
		$(document).ready(function() {
          $('body').omBorderLayout({
			   panels:[
			        {
			       		id:"panel", 
			   	        resizable:false,
			   	        collapsible:false,
			   	        title: decodeURI("${param.windowname}"),
			   	        region:"north",
			   	        height:57
			        },
			        {
			   	        id:"doorDiv",
			   	     	header:false,
			   	        region:"center"
			   	    }],
			   	    fit:true,
			   	    spacing:2
	    }); 
	    var id =<%=request.getParameter("id")%>
			$('#doorTable').omGrid({
		        dataSource : 'getOrderjxs.do?id='+1 ,
		        limit : 15,
		        showIndex : false,
		        autoFit : true,
		        height:'fit',
		        colModel : [
		        			{header : "商品名称", name : 'doorid', align : 'center'},                              
                            {header : "订单编号", name : 'number', align : 'center'},
                            {header : "订单时间", name : 'ordertime', align : 'center'}  
                             
               			 ]
	        });
			
        });
    </script>
<script type="text/javascript">
//***************************************************************
function checkAll(str){
	var a = document.getElementsByName(str);
	var n = a.length;
	for(var i = 0; i< n; i++){
		a[i].checked = window.event.srcElement.checked;
		}
	}
//*******************************************************************
//获取文本框对象
		var value;
		//增加
		function adds(){
			value = $("#countent");
			value.val(parseInt(value.val())+1);
			if(parseInt(value.val()) != 1){
				$("#min").attr("disabled",false);	
			}
			setTatal();	
		}
		//$("#add").click(function(){
		//	value.val(parseInt(value.val())+1);
			//if(parseInt(value.val()) != 1){
			//	$("#min").attr("disabled",false);	
			//}
			//setTatal();
		//});
		
		//减少
		function mins(){
			value = $("#countent");
			value.val(parseInt(value.val())-1);
			if(parseInt(value.val())==1){
				$("#min").attr("disabled",true);
			}
			setTatal();
		}
		//$("#min").click(function(){
			//value.val(parseInt(value.val())-1);
			//if(parseInt(value.val())==1){
				//$("#min").attr("disabled",true);
			//}
			//setTatal();
		//});
		//计算总价
		function setTatal(){
			var qq = $("#total").val((parseInt(value.val())*30).toFixed(2));			
		}
		//初始化
		setTotal();
</script>
  </head>
  <fmt:bundle basename="messages.messages_orderlist" />
  <body style="overflow: hidden">
    
    	<div id="panel">
	<form id="queryForm">
		<table class="table1" style="float: left">
			<tr class="tr1">
				<td class="td1">
				<a href="orderset.jsp" target="_top">提交订单</a>
				</td>
			</tr>
		</table>
	</form>
	</div>
	<div id="doorDiv" class="div1">
		<table id="doorTable" class="table2"></table>
	</div>
    
  </body>
</html>
