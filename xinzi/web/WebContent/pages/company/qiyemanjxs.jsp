
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

.div-a{ float:left;width:49%;border:1px solid #F00} 
.div-b{ float:left;width:49%;border:1px solid #000} 

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
				   	        height:57
				        },
				        {
				   	        id:"companyqiyeDiv",
				   	        //title:"",
				   	     	header:false,
				   	        region:"center"
				   	    }],
				   	    fit : true,
				   	    spacing:2
		    });
		    
        
            $('#CompanyqiyeTable').omGrid({
             title : '经销商列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    		'<a id="create"   href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		    	// '<a id="modify2" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="查看" />&nbsp;查看&nbsp;</a>'+
		    	
		            '<a id="modify" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="delete" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		              '<a id="delete2" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/lock.gif" title="解除授权申请" />&nbsp;解除授权申请&nbsp;</a>'+
		            '</div>',
	            dataSource : 'getCompanyqiye.do' ,
		        limit : 15,
		        autoFit : true,
		        height:'fit',
		        showIndex : false,
		        colModel : [{header :"经销商编号", name : 'code', align : 'center'},
		                    {header :"经销商名称", name : 'companyname', align : 'center'},
		                    {header : "省份", name : 'shengName', align : 'center'},
							{header : "地区", name : 'diqu', align : 'center'}, 
							{header : "授予品牌", name : 'pinpai', align : 'center'},
							{header : "销售方式", name : 'salefor', align : 'center'},
							{header : "合约", name : 'heyue', align : 'center'},		
							{header : "联系方式", name : 'lianxi', align : 'center'}
							],		
                
                //展开行时使用下面的方法生成详情，必须返回一个字符串
            rowDetailsProvider:function(rowData,rowIndex){
                return '授权经销商的品牌为：'+rowData.pinpai
                 
		        
           },
                onRowDblClick:function(rowIndex,rowData){
			        isAdd = false;
			        $('#operation').val("update");
                    showDialog("<fmt:message key='dialog.submit.modify'/>",rowData);//Display dialog
				}
            });
           
            var dialog = $("#dialog-form").omDialog({
            	height:500,
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
                $("select[name='shengName']",dialog).val(rowData.shengName);
                $("select[name='diqu']",dialog).val(rowData.diqu);
                
                $("input[name='type']",dialog).val(rowData.type);
                  $("#typeName").omCombo('value',rowData.pinpai);
                $("input[name='salefor']",dialog).val(rowData.salefor);
                $("input[name='heyue']",dialog).val(rowData.heyue);
                $("input[name='lianxi']",dialog).val(rowData.lianxi);
                $("input[name='zuoji']",dialog).val(rowData.zuoji);
                $("input[name='chuanzhen']",dialog).val(rowData.chuanzhen);
                $("input[name='address']",dialog).val(rowData.address);
                $("input[name='baojia']",dialog).val(rowData.baojia);
                $("input[name='wuliu']",dialog).val(rowData.wuliu);
                $("input[name='wuliutel']",dialog).val(rowData.wuliutel);
                $("textarea[name='remark']",dialog).val(rowData.remark);
                $("input[name='type']",dialog).val(rowData.type);
                $("input[name='code']",dialog).val(rowData.code);
                
                	$("input[name='org']",dialog).val(rowData.org);
                 	$("input[name='createPerson']",dialog).val(rowData.createPerson);
                 	$("input[name='createPersonId']",dialog).val(rowData.createPersonId);
                dialog.omDialog("option", "title", title);
                dialog.omDialog("open");//Display dialog
            };
            
            
           
            
            
            /**********************************/
            var dialog = $("#dialog-form").omDialog({
            	height:470,
        		width:600,
                autoOpen : false,
                modal : true,
             //   resizable : false,
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
              var showDialogPinpai = function(title,rowData){
                rowData = rowData || {};
               // $("input[name='pinpai']",dialog).val(rowData.pinpai);
                  $("#pinpai").omCombo('value',rowData.pinpai);
               
                dialog.omDialog("option", "title", title);
                dialog.omDialog("open");//Display dialog
            };
            /*******************/
            //Point when the submit button to submit data to the background in dialog and perform the appropriate Add or modify operation
            function showResponse(data){
            	if(data!=null&&data=="true"){
				 	$("#dialog-form").omDialog("close");  
			 		$('#operation').val("");
				 	$('#CompanyqiyeTable').omGrid('setData','getCompanyqiye.do');
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
	      	
	      	/*****
	      	$('#pinpai').omCombo({
		     width : 150,
		     editable : false,
		   
		     multi : true,
			dataSource :'getPinpaiComboData.do?pinpai=name',
			onValueChange:function(target,newValue,oldValue,event){
				var tt=$('#typeName').val( $('#pinpai').next().val());
			//$('#pinpaiName').val();
			}
			
	
	
			  
		});
		*****/
	      	
	      	
	      $('#modify2').die().live('click', function() {
                var selections=$('#CompanyqiyeTable').omGrid('getSelections',true);
                if (selections.length == 0) {
         			$.omMessageBox.alert({  content:'<fmt:message key='dialog.submit.modify.confirm'/>' });
                             
      				 return false;
                }
                isAdd = false;
                $('#operation').val("update");
                showDialog("查看",selections[0]);//Display dialog
            });
	      	
            var isAdd = true; //Is the add operation in a pop-up window or modify the operation?
            $('#create').die().live('click', function() {
                isAdd = true;
                $('#operation').val("add");
                showDialog("<fmt:message key='dialog.submit.add'/>");//Display dialog
            });
            $('#modify').die().live('click', function() {
                var selections=$('#CompanyqiyeTable').omGrid('getSelections',true);
                if (selections.length == 0) {
         			$.omMessageBox.alert({  content:'<fmt:message key='dialog.submit.modify.confirm'/>' });
                             
      				 return false;
                }
                isAdd = false;
                $('#operation').val("update");
                showDialog("<fmt:message key='dialog.submit.modify'/>",selections[0]);//Display dialog
            });
            
              $('#delete').die().live('click', function(e) {
                var selections=$('#CompanyqiyeTable').omGrid('getSelections',true);
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
		                $.post('companyqiyeOperate.do',{operation:'delete',id:toDeleteRecordID},function(){
		                    $('#CompanyqiyeTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "<fmt:message key='dialog.submit.success'/>", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
             });
             
              $('#delete2').die().live('click', function() {
                var selections=$('#CompanyqiyeTable').omGrid('getSelections',true);
                if (selections.length == 0) {
         			$.omMessageBox.alert({  content:'<fmt:message key='dialog.submit.modify.confirm'/>' });
                             
      				 return false;
                }
                isAdd = false;
                $('#operation').val("lock");
                showDialog("解除授权2",selections[0]);//Display dialog
            });
            
            
            /*
          $('#typeName').omCombo({
			width : 200,
			multi : true,
		    width : '200px',
			multiSeparator : ';',
			dataSource : 'getCompanyqiye4.do?type=type',
			onValueChange:function(target,newValue,oldValue,even){
				//console.log(newValue);
			
				/////$('#typeName').val($('#type').next().val());
			 ////var typeName=$('#typeName').val();
			   //alert(typeName);
			   $("#type").val(newValue);
			 
			    var tt=$(".valid").val();
			    alert(tt);
			         $("#pinpai").val(newValue);
			         alert(newValue);
			}			
		});
		*/
		  
            
             $('#query').bind('click', function(e) {
               var companyqiyename=$('#companyqiyename').val().trim();
                var code=$('#code').val();
               // var qType=$('#qType').val();
                if(companyname==""&&code===""){ //No query criteria to, to display all data
                    $('#CompanyqiyeTable').omGrid("setData", 'getCompanyqiye.do');
                }else{ //Query, display the query data
                    $('#CompanyqiyeTable').omGrid("setData", 'getCompanyqiye.do?companyqiyename='+encodeURI(companyqiyename)+'&code='+encodeURI(code));
                }
                
            });
            
            // Validate form
            var validator = $('#ipForm').validate({
                rules : {
                    companyname : {required : true},
					shengName : {required : true},
                    diqu : {required : true},
                    lianxi : {required : true}
                }, 
                messages : {
                    companyname : {required : "*"},
                    shengName : {required : "*"},
                    diqu : {required : "*"},
                    lianxi : {required : "*"}
                }
            });
			
        });
    </script>
      <script type="text/javascript">
        $(document).ready(function() {
            var timer = '_pinpai_timer';
            var input = $('#pinpai').attr('readonly', 'readOnly')
					                 .focus(function() {
					                     dropList.show();
					                 })
					                 .blur(function() {
					                     window[timer] = setTimeout(function() {
					                        dropList.hide();
					                     }, 500);
					                 });
             var inputOffset=input.offset();
             var dropList = input.next().css({top:inputOffset.top+input.outerHeight(),left:inputOffset.left})
                                        .mousedown(function(e) {
                                            e.stopPropagation();
                                            setTimeout(function() {
                                                clearTimeout(window[timer]);
                                            }, 25);
                                        });
             $(document.body).mousedown(function() {
                dropList.hide();
             });                                        
    		var selectIds = [];
            $('#target-table').omGrid({
                dataSource : 'getBrands.do',
                method : 'POST',
                width : 500,
                height :190,               
                showIndex : false,
                autoFit : true,
                singleSelect : false,
                colModel : [ 
                              {header : '品牌名称', name : 'name', width : 20, align : 'center', sortable : true}
                             
                            ],
                            
                    onRowSelect : function(index, data){
                    alert("onRowselect"+data.id+data.name);
                    var text2="";
                    $.each(selectIds,function(i,item){
        				text2 = text2+ item+",  ";
        				input.val(data.name).attr('_trueValue', data.id);
        			});
        				$("#pinpai").val(text2);
        				
                    
            			if($.inArray(data.name, selectIds)== -1)
                   			selectIds.push(data.name);
            		},
            		onRowDeselect : function(index, data){
            		alert("onRowDeselect");
            			var i = $.inArray(data.name, selectIds);
            			selectIds.splice(i,1);
            		},
                onRowClick : function(event, rowData) {
                alert("onRowClick");
                	var text="";
        			$.each(selectIds,function(i,item){
        				text = text+ item+",  ";
        				input.val(rowData.name).attr('_trueValue', rowData.id);
        			});
        				$("#pinpai").val(text);
                    
                    dropList.hide();
                }
            });
            input.next().hide();
            
           
        });
    </script>
    
    
    
</head>
<fmt:bundle basename="classpath:messages.companyqiye" />
<body style="overflow: hidden">
<div id="panel">
<form id="queryForm">
<table  border="0" cellspacing="0" cellpadding="0" style="float: left">
			<tr height="30" align="left">
				<td>经销商名称:
				<input id="companyqiyename" name="companyqiyename" />
				</td>
				<td text-align:center align="left">
				<input id="query" type="button" class="button09"  value="查询"/></td>
			</tr>
		</table>
</form>
</div>

<div id="companyqiyeDiv" style="width: 'auto'">
<table id="CompanyqiyeTable"></table>
</div>

<div id="dialog-form">
<form id="ipForm" action="companyqiyeOperate.do" method="post"><input name="operation"
	id="operation" style="display: none" /> <input name="id" style="display: none">
<table>
                      <tr>
                 <td colspan="1" rowspan="1" align="right">经销商编号:</td>
				<td colspan="1" rowspan="1"><input id="code" name="code" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"></input></td>
				<td colspan="1" rowspan="1" align="right">经销商名称:</td>
				<td colspan="1" rowspan="1"><input id="companyname" name="companyname"></input></td>
				 </tr>	
		
		         	<tr>
				<td colspan="1" rowspan="1" align="right" >省份:</td>
				<td colspan="1" rowspan="1" style="width:25px"><select id="province" name="shengName" onchange="changeProvince(document.getElementById('province'),document.getElementById('city'));"></select></td>
				<td colspan="1" rowspan="1" align="right">地区:</td>
				<td colspan="1" rowspan="1"><select id="city" name="diqu"></select></td>
		           </tr>	
			
		         	<tr>
				<td colspan="1" rowspan="1" align="right">联系方式:</td>
				<td colspan="1" rowspan="1"><input id="lianxi" name="lianxi" width="120" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"> </input></td>
				<td colspan="1" rowspan="1" align="right">合约:</td>
				<td colspan="1" rowspan="1"><input id="heyue" name="heyue" width="120"> </input></td>
				 </tr>	
			
		         	<tr>
				<td colspan="1" rowspan="1" align="right">销售方式:</td>
				<td colspan="1" rowspan="1"><input id="salefor" name="salefor"></input></td>
				<td colspan="1" rowspan="1" align="right">报价政策:</td>
				<td colspan="1" rowspan="1"><input  id="baojia" name="baojia" width="120"> </input></td>
			   </tr>
			   <!-- gjp 0327  
			   <tr>
			   <td colspan="1" rowspan="1" align="right">授权:</td>
			   <td colspan="2" rowspan="2" align="right"> 
			    <input id="typeName" name="typeName" />
			    <input  id="type" name="type" />
			   </td>
			   </tr>
			   -->
			    <input id="pinpai" name="pinpai" style="display: none;"/>
			   <!-- gjp 0327-  ------->
			   <tr>
			   <td colspan="4" rowspan="1" align="center">授权品牌(选中后即可授权品牌)  </td>
				   <span>				  
			        <div class="omcombo-ct" >
			         
			            <table id="target-table"></table>
			        </div>
			    </span>
			 <td></td>
			 </tr>
			
			
			       <tr style="display: none;">
				<td><input id="org" name="org"></input></td>
				<td><input id="type44" name="type44" value="lock"></input></td>
				<td><input id="createPerson" name="createPerson"></input></td>
				<td><input id="createPersonId" name="createPersonId"></input></td>
			    </tr>
		</table>
</form>
</div>
</body>
	<script language="javascript" type="text/javascript">
	initialize(document.getElementById('province'),document.getElementById('city'),'${prvoince}','${city}');
  </script>
</html>