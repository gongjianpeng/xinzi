
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
<script src="../../ui/om-validate.js"></script>
<script src="../../ui/om-combo.js"></script>
<script src="../../ui/om-calendar.js"></script>
<script src="../../ui/om-panel.js"></script>
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
		    
        
            $('#CompanyTable').omGrid({
             title : '供应商数据列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    		'<a id="create"   href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		            '<a id="modify" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="delete" href="javascript:void(0)"><img src="<%=basePath%>home/resource/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		            '</div>',
	            dataSource : 'getCompany.do' ,
		        limit : 10,
		        autoFit : true,
		      //  height:'fit',
		        colModel : [{header :"编号", name : 'id', align : 'center'},
		                    {header : "经销商编号", name : 'code', align : 'center'},
		                    {header :"经销商名称", name : 'companyname', align : 'center'},
		                    {header : "省份", name : 'shengName', align : 'center'},
							{header : "地区", name : 'diqu', align : 'center'}, 
							{header : "品牌", name : 'pinpai', align : 'center'},
							{header : "销售方式", name : 'salefor', align : 'center'},
							{header : "合约", name : 'heyue', align : 'center'},		
							{header : "联系方式", name : 'lianxi', align : 'center'},		
							{header : "座机", name : 'zuoji', align : 'center'},		
							{header : "传真", name : 'chuanzhen', align : 'center'},		
							{header : "地址", name : 'address', align : 'center'},		
							{header : "报价政策", name : 'baojia', align : 'center'},			
							{header : "物流", name : 'wuliu', align : 'center'},		
							{header : "物流电话", name : 'wuliutel', align : 'center'},		
							{header : "备注", name : 'remark', align : 'center'}	,
							{header : "类型", name : 'type', align : 'center'}	
							],		
                
                
             
                
                
                onRowDblClick:function(rowIndex,rowData){
			        isAdd = false;
			        $('#operation').val("update");
                    showDialog("<fmt:message key='dialog.submit.modify'/>",rowData);//Display dialog
				}
            });
           
            var dialog = $("#dialog-form").omDialog({
            	height:490,
        		width:1000,
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
                $("input[name='companyname']",dialog).val(rowData.companyname);
                $("input[name='shengName']",dialog).val(rowData.shengName);
                $("input[name='diqu']",dialog).val(rowData.diqu);
                $("input[name='pinpai']",dialog).val(rowData.pinpai);
                $("input[name='salefor']",dialog).val(rowData.salefor);
                $("input[name='heyue']",dialog).val(rowData.heyue);
                $("input[name='lianxi']",dialog).val(rowData.lianxi);
                $("input[name='zuoji']",dialog).val(rowData.zuoji);
                 $("input[name='chuanzhen']",dialog).val(rowData.chuanzhen);
                  $("input[name='address']",dialog).val(rowData.address);
                   $("input[name='baojia']",dialog).val(rowData.baojia);
                    $("input[name='wuliu']",dialog).val(rowData.wuliu);
                     $("input[name='wuliutel']",dialog).val(rowData.wuliutel);
                      $("input[name='remark']",dialog).val(rowData.remark);
                       $("input[name='type']",dialog).val(rowData.type);
                
                
                dialog.omDialog("option", "title", title);
                dialog.omDialog("open");//Display dialog
            };
            //Point when the submit button to submit data to the background in dialog and perform the appropriate Add or modify operation
            function showResponse(data){
            	if(data!=null&&data=="true"){
				 	$("#dialog-form").omDialog("close");  
			 		$('#operation').val("");
				 	$('#CompanyTable').omGrid('setData','getCompany.do');
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
            	/*var $code=$("#dcode").val();
            	if($code==null){
            		alert("请你输入编码!");
            	}*/
            
                isAdd = true;
                $('#operation').val("add");
                showDialog("<fmt:message key='dialog.submit.add'/>");//Display dialog
            });
            $('#modify').die().live('click', function() {
                var selections=$('#CompanyTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                    alert("<fmt:message key='dialog.submit.modify.confirm'/>");
                    return false;
                }
                isAdd = false;
                $('#operation').val("update");
                showDialog("<fmt:message key='dialog.submit.modify'/>",selections[0]);//Display dialog
            });
            
              $('#delete').die().live('click', function(e) {
                var selections=$('#CompanyTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                    alert("<fmt:message key='dialog.submit.modify.confirm'/>");
                    return false;
                }
                $.omMessageBox.confirm({
                   title:'提示',
                   content:'确定要删除吗 ?',
                   onClose:function(value){
                        if(value==false)
                        	return false;
                        $('#operation').val("delete");
		                var toDeleteRecordID=selections[0].id;
		                $.post('companyOperate.do',{operation:'delete',id:toDeleteRecordID},function(){
		                    $('#CompanyTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "<fmt:message key='dialog.submit.success'/>", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
             });
             $('#query').bind('click', function(e) {
               var companyname=$('#companyname').val();
                var code=$('#code').val();
               // var qType=$('#qType').val();
                if(companyname==""&&code===""){ //No query criteria to, to display all data
                    $('#CompanyTable').omGrid("setData", 'getCompany.do');
                }else{ //Query, display the query data
                    $('#CompanyTable').omGrid("setData", 'getCompany.do?companyname='+encodeURI(companyname)+'&code='+encodeURI(code));
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
    
<script type="text/javascript">
/*function CheckBaseAdd(){

	var code=$("#dcode").val();
	var type=$("#type").val();
	if(code=="www"){
		alert(code+"编码已经存在!");
		$("#dcode").val("");
		$("#dcode").focus(); 
		return false;
	} 
	if(type=="wite"){
		alert(type+"类型已经存在!");
		$("#type").val(""); 
		$("#type").focus(); 
		return false;
	}else {
		$("#type").focus();
		$("#dcode").focus();
		return true;
	}
 }
 */
</script>
</head>
<fmt:bundle basename="classpath:messages.messages_dataDictionary" />
<body style="overflow: hidden">
<div id="panel">
<form id="queryForm">
<table  border="0" cellspacing="0" cellpadding="0">
			<tr height="50" align="left">
				<td height="25" align="right">经销商名称: 
				<input id="companyname" name="companyname" />
				</td>
				<td height="25" align="left">
				编号:<input id="code" name="code" />
				<input id="query" type="button" class="button09"  value="查询"/></td>
			</tr>
		</table>
</form>
</div>

<div id="dataDictionaryDiv" style="width: 'auto'">
<table id="CompanyTable"></table>
</div>
<div id="dialog-form">
<form id="ipForm" action="companyOperate.do" method="post"><input name="operation"
	id="operation" style="display: none" /> <input name="id" style="display: none">
<table>
			
			<tr>
				<td colspan="1" rowspan="1" align="center">经销商名称:</td>
				<td colspan="1" rowspan="1"><input id="companyname" name="companyname"></input></td>
				<td colspan="1" rowspan="1" align="center">省份：</td>
				<td colspan="1" rowspan="1"><input id="shengName" name="shengName" width="120"> </input></td>
					<td colspan="1" rowspan="1" align="center">地区：</td>
				<td colspan="1" rowspan="1"><input id="diqu" name="diqu" width="120"> </input></td>
					<td colspan="1" rowspan="1" align="center">品牌：</td>
				<td colspan="1" rowspan="1"><input id="pinpai" name="pinpai" width="120"> </input></td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="center">销售方式:</td>
				<td colspan="1" rowspan="1"><input id="salefor" name="salefor"></input></td>
				<td colspan="1" rowspan="1" align="center">合约：</td>
				<td colspan="1" rowspan="1"><input id="heyue" name="heyue" width="120"> </input></td>
					<td colspan="1" rowspan="1" align="center">联系方式：</td>
				<td colspan="1" rowspan="1"><input id="lianxi" name="lianxi" width="120"> </input></td>
					<td colspan="1" rowspan="1" align="center">座机：</td>
				<td colspan="1" rowspan="1"><input id="zuoji" name="zuoji" width="120"> </input></td>
			</tr>
		
			<tr>
				<td colspan="1" rowspan="1" align="center">传真:</td>
				<td colspan="1" rowspan="1"><input id="chuanzhen" name="chuanzhen"></input></td>
				<td colspan="1" rowspan="1" align="center">地址：</td>
				<td colspan="1" rowspan="1"><input id="address" name="address" width="120"> </input></td>
					<td colspan="1" rowspan="1" align="center">报价政策：</td>
				<td colspan="1" rowspan="1"><input  id="baojia" name="baojia" width="120"> </input></td>
					<td colspan="1" rowspan="1" align="center">物流：</td>
				<td colspan="1" rowspan="1"><input  id="wuliu" name="wuliu" width="120"> </input></td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="center">物流电话:</td>
				<td colspan="1" rowspan="1"><input id="wuliutel" name="wuliutel"></input></td>
				<td colspan="1" rowspan="1" align="center">备注：</td>
				<td colspan="1" rowspan="1"><input id="remark" name="remark" width="120"> </input></td>
					
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="center">type:</td>
				<td colspan="1" rowspan="1"><input id="type" name="type"></input></td>
				<td colspan="1" rowspan="1" align="center">code:</td>
				<td colspan="1" rowspan="1"><input id="code" name="code"></input></td>
				
					
			</tr>
			<tr>
				
			</tr>
			
		</table>
</form>
</div>
</body>
</html>