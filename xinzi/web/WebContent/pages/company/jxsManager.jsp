
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

 .black_overlay{ 
	  display: none;  position: absolute; 
	  top: 0%;  left: 0%;  width: 100%;  height: 100%;  
	  background-color: black;  z-index:1001;  
	  -moz-opacity: 0.8;  opacity:.80; 
	  filter: alpha(opacity=80); 
 }  
 .white_content {  
	  display: none;  position: absolute;  
	  top: 25%;  left: 25%;  width: 250px;  
	  height: 180px;  padding: 16px;  
	  border: 16px solid #cad6e6;  
	  background-color: white;  
	  z-index:1002;  overflow: auto;  
  }  
</style>
<link rel="stylesheet" type="text/css" href="../../themes/apusic/om-all.css" />
<link href="../../css/css.css" rel="stylesheet" type="text/css" />
<link href="../../js/jupload/uploadify.css" rel="stylesheet" type="text/css" />
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

<script src="../../js/jupload/jquery.uploadify.js"></script>
<script src="../../js/jupload/jquery.uploadify.min.js"></script>
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
				   	        height:57
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
             title : '经销商列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    	//	'<a id="create" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		            '<a id="modify" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="delete" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		                 '<a id="uploadButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/sort_up.gif" title="上传"/>&nbsp;上传视频&nbsp;</a>'+
		        
		            '</div>',
	            dataSource : 'getCompany.do' ,
		        limit : 15,
		      	autoFit : true,
		      	height:'fit',
		        showIndex : false,
		      
		        colModel : [
		                    {header :"经销商名称", name : 'companyname', align : 'center'},
		                    {header : "省份", name : 'shengName', align : 'center'},
							{header : "地区", name : 'diqu', align : 'center'}, 
						//	{header : "品牌", name : 'pinpai', align : 'center'},
						//	{header : "销售方式", name : 'salefor', align : 'center'},
						//	{header : "合约", name : 'heyue', align : 'center'},		
						//	{header : "联系方式", name : 'lianxi', align : 'center'},		
						//	{header : "报价政策", name : 'baojia', align : 'center'},			
						//	{header : "物流", name : 'wuliu', align : 'center'},
							{header : "类型", name : 'type', align : 'center'},
							{header : "企业简介", name : 'intro', align : 'center'},
		                    {header : "视频", name : 'video', align : 'center'},	
							{header : "其他介绍", name : 'picturebook', align : 'center'}
							],	
		     
                onRowDblClick:function(rowIndex,rowData){
			        isAdd = false;
			        $('#operation').val("update");
                 //   showDialog("<fmt:message key='dialog.submit.modify'/>");//Display dialog
				}
            });
           
            var dialog = $("#dialog-form").omDialog({
            	height:390,
        		width:800,
                autoOpen : false,
                modal : true,
                resizable : false,
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
                 
                    
                $("textarea[name='intro']",dialog).val(rowData.intro);
                $("input[name='video']",dialog).val(rowData.video);
                $("textarea[name='picturebook']",dialog).val(rowData.picturebook);
                 $("input[name='code']",dialog).val(rowData.code);
                	$("input[name='org']",dialog).val(rowData.org);
                 	$("input[name='createPerson']",dialog).val(rowData.createPerson);
                 	$("input[name='createPersonId']",dialog).val(rowData.createPersonId);
                dialog.omDialog("option", "title", title);
                dialog.omDialog("open");//Display dialog
            };
            //Point when the submit button to submit data to the background in dialog and perform the appropriate Add or modify operation
            function showResponse(data){
            	if(data!=null&&data=="true"){
				 	$("#dialog-form").omDialog("close");  
			 		$('#operation').val("");
				 	$('#CompanyTable').omGrid('setData','getCompany.do');
				 	$.omMessageTip.show({title: "提示", content: "操作成功", timeout: 1500});
			 	} else {
				 	$.omMessageTip.show({title: "提示", content: "操作失败", timeout: 1500});
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
            $('#modify').die().live('click', function() {
                var selections=$('#CompanyTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                    $.omMessageBox.alert({  content:'<fmt:message key='dialog.submit.modify.confirm'/>' });
                    return false;
                }
                isAdd = false;
                $('#operation').val("update");
               
                showDialog("<fmt:message key='dialog.submit.modify'/>",selections[0]);//Display dialog
            });
            
              $('#delete').die().live('click', function(e) {
                var selections=$('#CompanyTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                    $.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
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
                    $('#CompanyTable').omGrid("setData", 'getCompany.do?companyname='+encodeURI(companyname));
                }
                
            });
            
            
             /******************************************/
         	$('#uploadButton').die().live('click', function(e) {
			 	var selections=$('#CompanyTable').omGrid('getSelections',true);
				if (selections.length == 0) {
				 $.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
					return false;
				}
		        //$( "#dialog-upload").omDialog('open');
		       var toDeleteRecordID=selections[0].id;
		   //    alert(toDeleteRecordID);
		    $('#file_upload').uploadify( {
			'debug':'false',
			'swf' : '../../js/jupload/uploadify.swf',//上传按钮的图片，默认是这个flash文件
		    'uploader' : '<%=basePath%>FileUploadServletjxs?ID='+toDeleteRecordID,  //上传所处理的服务器
		 //   'uploader' : 'getdep.do?ID='+toDeleteRecordID,  //上传所处理的服务器
			'cancelImg' : '../../js/jupload/uploadify-cancel.png',//取消图片
			'method':'post',
			'folder' : '/upload',//上传后，所保存文件的路径
			'queueID' : 'fileQueue',//上传显示进度条的那个div
			'buttonText' : '请选择文件',
			progressData : 'percentage',
			'auto' : false,
			'multi' : false,
			'onDisable' : function() {
				alert('uploadify is disable');
			},//在调用disable方法时候触发
			'onError' : function(errorType,errObj) {
				alert('The error was: ' + errObj.info)
			},
			'onUploadSuccess' : function(file, data, response){
				  $('#CompanyTable').omGrid('reload');
				}
		
		});
	 
      
      $("#uploadButton").click(function(){
    	  document.getElementById('light').style.display='block';
 		  document.getElementById('fade').style.display='block';
      });
      
      $("#colseWindow").click(function(){
    	  document.getElementById('light').style.display='none';
    	  document.getElementById('fade').style.display='none';
      });
			});    
	 /*******************/
            
            // Validate form
            var validator = $('#ipForm').validate({
                rules : {
                    companyname : {required : true},
                    code : {required : true},
                    shengName : {required : true},
                    diqu : {required : true}
                }, 
                messages : {
                    companyname : {required : "*"},
                    code : {required : "*"},
                    shengName : {required : "*"},
                    diqu : {required : "*"}
                }
            });
			
        });
    </script>

</head>
<fmt:bundle basename="classpath:messages.messages_company" />
<body style="overflow: hidden">
<div id="panel">
<form id="queryForm">
<table border="0" cellspacing="0" cellpadding="0">
			<tr height="30" align="left">
				<td text-align:centers align="right">经销商名称: 
				<input id="companyname" name="companyname" />
				<input id="query" type="button" class="button09"  value="查询"/>
				</td>
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
				<td colspan="1" rowspan="1" align="right">经销商名称:</td>
				<td colspan="1" rowspan="1"><input id="companyname" name="companyname"></input></td>
				<td colspan="1" rowspan="1" align="right">经销商编号:</td>
				<td colspan="1" rowspan="1"><input id="code" name="code" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"></input></td>
				<td colspan="1" rowspan="1" align="right">类型:</td>
				<td colspan="1" rowspan="1"><input  id="type" name="type" width="120"> </input></td>				
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="right">省份:</td>
				<td colspan="1" rowspan="1"><select id="province" name="shengName" onchange="changeProvince(document.getElementById('province'),document.getElementById('city'));"></select></td>
				<td colspan="1" rowspan="1" align="right">地区:</td>
				<td colspan="1" rowspan="1"><select id="city" name="diqu"></select></td>				
				<td colspan="1" rowspan="1" align="right">地址:</td>
				<td colspan="1" rowspan="1"><input id="address" name="address" width="120"> </input></td>				
			</tr>
			<!-- 
			<tr>
				<td colspan="1" rowspan="1" align="right">合约:</td>
				<td colspan="1" rowspan="1"><input id="heyue" name="heyue" width="120"> </input></td>
				<td colspan="1" rowspan="1" align="right">联系方式:</td>
				<td colspan="1" rowspan="1"><input id="lianxi" name="lianxi" width="120" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"> </input></td>
				<td colspan="1" rowspan="1" align="right">座机:</td>
				<td colspan="1" rowspan="1"><input id="zuoji" name="zuoji" width="120" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"> </input></td>				
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="right">品牌:</td>
				<td colspan="1" rowspan="1"><input id="pinpai" name="pinpai" width="120"> </input></td>
				<td colspan="1" rowspan="1" align="right">销售方式:</td>
				<td colspan="1" rowspan="1"><input id="salefor" name="salefor"></input></td>
				<td colspan="1" rowspan="1" align="right">报价政策:</td>
				<td colspan="1" rowspan="1"><input  id="baojia" name="baojia" width="120"> </input></td>
			</tr>			
			<tr>
				<td colspan="1" rowspan="1" align="right">传真:</td>
				<td colspan="1" rowspan="1"><input id="chuanzhen" name="chuanzhen" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"></input></td>
				<td colspan="1" rowspan="1" align="right">物流:</td>
				<td colspan="1" rowspan="1"><input  id="wuliu" name="wuliu" width="120"> </input></td>
				<td colspan="1" rowspan="1" align="right">物流电话:</td>
				<td colspan="1" rowspan="1"><input id="wuliutel" name="wuliutel" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"></input></td>
			</tr>
			 -->
				<tr>
				<td colspan="1" rowspan="1" align="right">视频:</td>
				<td colspan="1" rowspan="1">
					<input id="video" name="video" width="120"> </input>
					 
				</td>
			
			<tr>
				<td colspan="1" rowspan="1" align="right">简介：</td>
				<td colspan="5"><textarea name="intro"	style="width: 400px; height: 90px"></textarea></td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="right">备注：</td>
				<td colspan="5"><textarea name="remark"	style="width: 400px; height: 90px"></textarea></td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="right">其他介绍：</td>
				<td colspan="5"><textarea name="picturebook"	style="width: 400px; height: 90px"></textarea></td>
			</tr>
			
			<tr style="display: none;">
				<td><input id="org" name="org"></input></td>
				<td><input id="createPerson" name="createPerson"></input></td>
				<td><input id="createPersonId" name="createPersonId"></input></td>
			</tr>
			
		</table>
</form>
</div>

<div id="light" class="white_content"> 
	 <div id="fileQueue"></div>
		<div >
			<input id="file_upload" name="file_upload" type="file" multiple="true"/>
		</div>
		
	 	<div style="float: left;margin-left: 20px;margin-top: 40px;margin-right: 50px;">
			<a href="javascript:$('#file_upload').uploadify('upload','*')" style="cursor: pointer;"> <input type="button" value="上传"></a>
		</div>
		<div style="float: left;margin-left: 20px;margin-top: 40px;margin-right: 60px;">
			<a href="javascript:void(0)" id="colseWindow" style="cursor: pointer;"> <input type="button" value="取消"></a>
		
		</div>
 
</div> 
<div id="fade" class="black_overlay"> </div>

</body>
<script language="javascript" type="text/javascript">
	initialize(document.getElementById('province'),document.getElementById('city'),'${prvoince}','${city}');
  </script>
</html>