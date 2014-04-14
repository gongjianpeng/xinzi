<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>
<fmt:message key='datadictionary.title' />
</title>
<style type="text/css">
html,body {
	font-size: 14px;
	font-family: tahoma;
	border:0px;
	padding:0px;
	margin:0px;
	height:100%;
	width:100%;
	overflow:hidden;
}
.om-grid div.bDiv td {
	border-bottom: 1px solid #fff;
	vertical-align: top;
	white-space: nowrap;
	cursor: pointer;
}
.om-grid div.hDiv td {
	text-align: center;
}
.om-grid img{
	cursor:pointer;
	vertical-align: middle !important;
	margin-top: -5px;
	padding:0px;
	border:0px;
}
.om-grid a{
	cursor: pointer;
	vertical-align: middle !important;
	font-weight:normal;
	font-size: 12px;
}

.om-grid div.bDiv td {
	vertical-align: middle !important;
	cursor: pointer;
}
.search {
	background-image: url(../../images/icons/search.gif) !important;
}
</style>
<link rel="stylesheet" type="text/css" href="../../themes/apusic/om-all.css" />
<link href="../../css/css.css" rel="stylesheet" type="text/css" />
<script src="../../jquery/js/jquery.min.js"></script>
<script src="../../ui/om-core.js"></script>
<script src="../../ui/om-mouse.js"></script>
<script src="../../ui/om-tree.js"></script>
<script src="../../ui/om-menu.js"></script>
<script src="../../ui/om-draggable.js"></script>
<script src="../../ui/om-position.js"></script>
<script src="../../ui/om-resizable.js"></script>
<script src="../../ui/om-dialog.js"></script>
<script src="../../ui/om-messagebox.js"></script>
<script src="../../ui/om-messagetip.js"></script>
<script src="../../ui/om-buttonbar.js"></script>
<script src="../../ui/om-button.js"></script>
<script src="../../ui/om-ajaxsubmit.js"></script>
<script src="../../ui/om-grid.js"></script>
<script src="../../ui/om-grid-headergroup.js"></script>
<script src="../../ui/om-grid-rowexpander.js"></script>
<script src="../../ui/om-validate.js"></script>
<script src="../../ui/om-combo.js"></script>
<script src="../../ui/om-calendar.js"></script>
<script src="../../ui/om-panel.js"></script>
<script src="../../js/province.js"></script>
<script src="../../ui/om-tabs.js"></script>
<script src="../../ui/om-borderlayout.js"></script>
<script src="../../ui/om-fileupload.js"></script>
<script type="text/javascript">
        var gridData;
        $(document).ready(function() {
        
            $('body').omBorderLayout({
				   panels:[
				        {
				       		id:"panel", 
				   	        resizable:false,
				   	        collapsible:false,
				   	        title: decodeURI("${param.windowname}"),
				   	        region:"north",
				   	        height:80
				        },
				        {
				   	        id:"dataDictionaryDiv",
				   	        //title:"",
				   	     	header:false,
				   	        region:"center"
				   	    }],
				   	    fit : true,
				   	    spacing:2
		    });
		    
        
            $('#dataDictionaryTable').omGrid({
             title : '订单数据列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    		'<a id="create1" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="等待处理订单" />&nbsp;等待处理订单&nbsp;</a>'+
		            '<a id="modify3" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="订单受理中" />&nbsp;订单受理中&nbsp;</a>'+
		       
		            '<a id="modify4" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="厂商取消订单" />&nbsp;厂商取消订单&nbsp;</a>'+
		            '<a id="modify5" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="生产中" />&nbsp;生产中&nbsp;</a>'+
		              '<a id="modify8" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="等待支付" />&nbsp;等待支付&nbsp;</a>'+
		
		            '<a id="modify6" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="已发货" />&nbsp;已发货&nbsp;</a>'+
		              '<a id="modify7" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="交易成功" />&nbsp;交易成功&nbsp;</a>'+
		         '<a id="delete" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		        
		            '</div>',
	           	dataSource :'getDataOrderDetails.do',
	           	limit : 15,
	            height : 'fit',
                width : '100%',
                showIndex : false,
                colModel : [ 
                              {header : "订单编号", name : 'orderNo', align : 'left', width : 100}, 
                              {header : "订单名称", name : 'name', align : 'left', width : 100},
                               {header : "品牌", name : 'brand', align : 'left', width : 100},
                              {header : "订单状态", name : 'orderState', align : 'left', width : 100},
                              {header : "创建人", name : 'createPerson', align : 'left', width : 100},
                             
                            
                              {header : "详情->", name : '', align : 'left', width : 100},
                              
                               {header : "单价", name : 'unitPrice', align : 'left', width : 100},  
                             {header : "数量", name : '', align : 'left', width : 100}, 
                             {header : "总价", name : 'totalprice', align : 'left', width : 100}, 
                                  
                               {header : "开向", name : 'opento', align : 'left', width : 100},
                              {header : "色板", name : 'panelname', align : 'left', width : 100},
                              {header : "压花款式", name : 'doorstyletype', align : 'left', width : 100},
                               {header : "边框款式", name : 'framename', align : 'left', width : 100},
                               {header : "色板款式", name : 'palettename', align : 'left', width : 100},
                               {header : "气窗款式", name : 'scuttlename', align : 'left', width : 100},
                               {header : "其他款式", name : 'elsessename', align : 'left', width : 100},
                               
                               {header : "锁具", name : 'suojuName', align : 'left', width : 100},
                               {header : "锁芯", name : 'suoxName', align : 'left', width : 100},
                               {header : "门铃", name : 'menglingName', align : 'left', width : 100},
                                {header : "猫眼", name : 'maoyanName', align : 'left', width : 100},
                              {header : "下档", name : 'xiadangName', align : 'left', width : 100},
                              {header : "拉手", name : 'lashouName', align : 'left', width : 100},
                              {header : "头加", name : 'toujiaName', align : 'left', width : 100},
                               {header : "铰链", name : 'jiaolianName', align : 'left', width : 100},
                                {header : "闭门器", name : 'bimenqiName', align : 'left', width : 100},
                                  {header : "门颜色", name : 'colour', align : 'left', width : 100},
                                    {header : "门类型", name : 'type', align : 'left', width : 100}
                              
              
                ],
                
                 rowDetailsProvider:function(rowData,rowIndex){
                return '第'+rowIndex+'行，ID='+rowData.id+'<br/><b>'
                           
                            +'</b>订单收货详情是：<font color="red">'+'收货人为:'+rowData.ordershouName
                             +'    客户公司信息为:'+rowData.shoucompany+'<br/><br/>'
                            +'     联系电话为:'+rowData.shoumobile+'<b/><b/>'
                            +'     收货人地址为:'+rowData.shouaddress+'<br/><br/>'
                            +'     接单时间为:'+rowData.shouordertime+'<br/><br/>'
                              +'</font>';
            },
                
                onRowDblClick:function(rowIndex,rowData){
			        isAdd = false;
			        $('#operation').val("update");
                   // showDialog("<fmt:message key='dialog.submit.modify'/>",rowData);//Display dialog
				}
            });
            var dialog = $("#dialog-form").omDialog({
            	height:490,
        		width:500,
                autoOpen : false,
                modal : true,
                resizable : true,
                buttons : {
                    "<fmt:message key='dialog.submit'/>" : function(){
		                submitDialog();
		                return false; //Block form's default submit action
		            },
                    "<fmt:message key='dialog.cancel'/>" : function() {
                        $("#dialog-form").omDialog("close");//Close dialog
                    }
                }
            });
            //Displays a dialog and initialized the data entered into the box
            var showDialog = function(title,rowData){
                rowData = rowData || {};
                $("input[name='id']",dialog).val(rowData.id);
                $("input[name='code']",dialog).val(rowData.code);
                $("input[name='name']",dialog).val(rowData.name);
                if(rowData.type!=null)
               		$("input[name='type']",dialog).val(rowData.type);
                if(rowData.remark!=null)
                	$("input[name='remark']",dialog).val(rowData.remark);
                dialog.omDialog("option", "title", title);
                dialog.omDialog("open");//Display dialog
            };
            //Point when the submit button to submit data to the background in dialog and perform the appropriate Add or modify operation
            function showResponse(data){
            	if(data!=null&&data=="true"){
				 	$("#dialog-form").omDialog("close");  
			 		$('#operation').val("");
				 	//$('#dataDictionaryTable').omGrid('setData','getDataDictionarys.do');
				 	alert("excute success!");
			 	} else {
				 	alert("excute failed!");
			 	}
			}
            var options = {
				success : showResponse
			  };
	       	var submitDialog = function(){
	       		if(validator.form()){
	       			$("#ipForm").omAjaxSubmit(options);
	       		}
	       		return false;
	      	};
	      	
            var isAdd = true; //Is the add operation in a pop-up window or modify the operation?
            $('#create').die().live('click', function() {
                isAdd = true;
                $('#operation').val("add");
                showDialog("<fmt:message key='dialog.submit.add'/>");//Display dialog
            });
            $('#modify2').die().live('click', function() {
                var selections=$('#dataDictionaryTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                    alert("<fmt:message key='dialog.submit.modify.confirm'/>");
                    return false;
                }
                isAdd = false;
                $('#operation').val("update");
             //   showDialog("<fmt:message key='dialog.submit.modify'/>",selections[0]);//Display dialog
            });
            
             $('#modify3').die().live("click",function(){
	            var selections= $('#dataDictionaryTable').omGrid('getSelections',true);
	            if(selections.length>0){
	              $.omMessageBox.confirm({
                   title:'提示',
                   content:'确定要改变状态吗 ?',
                   onClose:function(value){
                        if(value==false)
                        	return false;
                        $('#operation').val("update");
		                var id=selections[0].id;
		                $.post('orderOperateqiye.do',{operation:'update',id:id},function(){
		                    $('#dataDictionaryTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "提示", content: "成功", timeout: 1500});
		                });
                   	}
                 });
                	}else{
                  		$.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
             			return false;
            		}
            
             });
              $('#modify4').die().live("click",function(){
	            var selections= $('#dataDictionaryTable').omGrid('getSelections',true);
	            if(selections.length>0){
	              $.omMessageBox.confirm({
                   title:'提示',
                   content:'确定要改变状态吗 ?',
                   onClose:function(value){
                        if(value==false)
                        	return false;
                        $('#operation').val("update2");
		                var id=selections[0].id;
		                $.post('orderOperateqiye.do',{operation:'update2',id:id},function(){
		                    $('#dataDictionaryTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "提示", content: "成功", timeout: 1500});
		                });
                   	}
                 });
                	}else{
                  		$.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
             			return false;
            		}
            
             });
              $('#modify5').die().live("click",function(){
	            var selections= $('#dataDictionaryTable').omGrid('getSelections',true);
	            if(selections.length>0){
	              $.omMessageBox.confirm({
                   title:'提示',
                   content:'确定要改变状态吗 ?',
                   onClose:function(value){
                        if(value==false)
                        	return false;
                        $('#operation').val("update3");
		                var id=selections[0].id;
		                $.post('orderOperateqiye.do',{operation:'update3',id:id},function(){
		                    $('#dataDictionaryTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "提示", content: "成功", timeout: 1500});
		                });
                   	}
                 });
                	}else{
                  		$.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
             			return false;
            		}
            
             });
               $('#modify6').die().live("click",function(){
	            var selections= $('#dataDictionaryTable').omGrid('getSelections',true);
	            if(selections.length>0){
	              $.omMessageBox.confirm({
                   title:'提示',
                   content:'确定要改变状态吗 ?',
                   onClose:function(value){
                        if(value==false)
                        	return false;
                        $('#operation').val("update4");
		                var id=selections[0].id;
		                $.post('orderOperateqiye.do',{operation:'update4',id:id},function(){
		                    $('#dataDictionaryTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "提示", content: "成功", timeout: 1500});
		                });
                   	}
                 });
                	}else{
                  		$.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
             			return false;
            		}
            
             });
               $('#modify7').die().live("click",function(){
	            var selections= $('#dataDictionaryTable').omGrid('getSelections',true);
	            if(selections.length>0){
	              $.omMessageBox.confirm({
                   title:'提示',
                   content:'确定要改变状态吗 ?',
                   onClose:function(value){
                        if(value==false)
                        	return false;
                        $('#operation').val("update5");
		                var id=selections[0].id;
		                $.post('orderOperateqiye.do',{operation:'update5',id:id},function(){
		                    $('#dataDictionaryTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "提示", content: "成功", timeout: 1500});
		                });
                   	}
                 });
                	}else{
                  		$.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
             			return false;
            		}
            
             });
             $('#modify8').die().live("click",function(){
	            var selections= $('#dataDictionaryTable').omGrid('getSelections',true);
	            if(selections.length>0){
	              $.omMessageBox.confirm({
                   title:'提示',
                   content:'确定要改变状态吗 ?',
                   onClose:function(value){
                        if(value==false)
                        	return false;
                        $('#operation').val("update9");
		                var id=selections[0].id;
		                $.post('orderOperateqiye.do',{operation:'update9',id:id},function(){
		                    $('#dataDictionaryTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "提示", content: "成功", timeout: 1500});
		                });
                   	}
                 });
                	}else{
                  		$.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
             			return false;
            		}
            
             });
            
            $('#delete').die().live('click', function(e) {
                var selections=$('#dataDictionaryTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                    alert("<fmt:message key='dialog.submit.modify.confirm'/>");
                    return false;
                }
                var  status=selections[0].orderState;
                if(status=='等待处理订单'){
              
                $.omMessageBox.confirm({
                   title:'提示',
                   content:'确定要删除吗 ?',
                   onClose:function(value){
                        if(value==false)
                        	return false;
                        $('#operation').val("delete");
		                var toDeleteRecordID=selections[0].id;
		                $.post('orderOperateqiye.do',{operation:'delete',id:toDeleteRecordID},function(){
		                    $('#dataDictionaryTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "<fmt:message key='dialog.submit.success'/>", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
                }else{
                $.omMessageBox.alert({title:'提示',content:'您索要删除的订单在受理中！'});
             			return false;
                }
                
             });
             
              $('#starttime').omCalendar();
              $('#endtime').omCalendar();
             
             $('#query').bind('click', function(e) {
               var qCode=$('#qCode').val().trim();
                var qName=$('#qName').val().trim();
                var qstarttime=$('#starttime').val();
                var qendtime=$('#endtime').val();
                var qcolour=$('#qcolour').val();
                var qtype=$('#qtype').val();
               
                if(qCode==""&&qName===""&&qstarttime==""&&qendtime==""&&qcolour==""&&qtype==""){ //No query criteria to , to display all data
                    $('#dataDictionaryTable').omGrid("setData", 'getDataOrderDetails.do');
                }else{ //Query, display the query data
                    $('#dataDictionaryTable').omGrid("setData", 'getDataOrderDetailsQuery.do?qcode='+encodeURI(qCode)
                    +'&qName='+encodeURI(qName)+'&qstarttime='+encodeURI(qstarttime)+'&qendtime='+encodeURI(qendtime)
                     +'&qcolour='+encodeURI(qcolour)+'&qtype='+encodeURI(qtype)
                    );
                }
                
            });
            
            // Validate form
            var validator = $('#ipForm').validate({
                rules : {
                    code : {required: true}, 
                    name : {required : true},
                    type : {required : true}
                }, 
                messages : {
                    code : {required : "<fmt:message key='table.code.confirm'/>"},
                    name : {required : "<fmt:message key='table.name.confirm'/>"},
                    type : {required : "<fmt:message key='table.type.confirm'/>"}
                }
            });
			
        });
    </script>
    

</head>
<fmt:bundle basename="classpath:messages.messages_dataDictionary" />
<body style="overflow: hidden">
<div id="panel">
<form id="queryForm">
<table border="0" cellspacing="0" cellpadding="0">
   <div>
	<tr height="30" align="left">
		<td text-align:center align="right"> 
		订单编号： <input id="qCode" name="qCode"/>
		订单名称： <input id="qName" name="qName" />
		开始时间： <input id="starttime" name="starttime" > 
		结束时间： <input id="endtime" name="endtime">
		
		
		</td>
		<td text-align:center align="right"> 
		品牌： <input id="*" name="qCode"/>
		类型： <input id="*" name="qName" />
		客户： <input id="*" name="starttime" > 
		状态： <input id="*" name="endtime">
		
		 <input id="query" type="button" value="<fmt:message key='datadictionary.search'/>" class="button09" />
		</td>
			
	</tr>
	<tr  text-align:center align="left">
	
	
	</tr>
	
	</div>
</table>
</form>
</div>

<div id="dataDictionaryDiv" style="width: 'auto'">
<table id="dataDictionaryTable"></table>
</div>
<div id="dialog-form">
<form id="ipForm" action="orderOperate.do" method="post"><input name="operation"
	id="operation" style="display: none" /> <input name="id" style="display: none">

</form>
</div>
</body>
</html>