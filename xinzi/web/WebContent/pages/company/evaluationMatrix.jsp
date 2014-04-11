<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
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
.om-grid div.hDiv td {
	text-align: center;
}
.om-grid div.bDiv td {
	vertical-align: middle !important;
	cursor: pointer;
}
.om-grid img{
	cursor:pointer;
	vertical-align: middle !important;
	margin-top:-5px;
	padding:0px;
	border:0px;
}
.om-grid a{
	cursor: pointer;
	vertical-align: middle !important;
	font-weight:normal;
	font-size: 12px;
	color : blue;
}
label.error { float: none; color: red; padding-left: .5em; vertical-align: top; }
div#dialogForm td {text-align: left;}
.search{
    background-image: url(../../images/icons/search.gif) !important;
}
input[type='text']{
	margin:0px;
	padding:0px;
	height:16px;
}
#dialog-form textarea{
	margin:0px;
	padding:0px;
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
<script src="../../ui/om-grid-sort.js"></script>
<script src="../../ui/om-grid-headergroup.js"></script>
<script src="../../ui/om-validate.js"></script>
<script src="../../ui/om-combo.js"></script>
<script src="../../ui/om-combotree.js"></script>
<script src="../../ui/om-calendar.js"></script>
<script src="../../ui/om-panel.js"></script>
<script src="../../ui/om-tabs.js"></script>
<script src="../../ui/om-borderlayout.js"></script>
<script src="../../ui/om-fileupload.js"></script>
<script type="text/javascript">
        //<![CDATA[
        var isSelected = false;
        var bid = "";
        var selectedNode ;
        var isAdd;
        var companyCode;
   		var companyName;
   	    var organizationv;
        $(document).ready(function() {
	    		$('body').omBorderLayout({
				   panels:[
				        {
				       		id:"searchDiv", 
				   	        resizable:false,
				   	        collapsible:false,
				   	        title: decodeURI("${param.windowname}"),
				   	        region:"north",
				   	        height:80
				        },
				        {
				   	        id:"tbdiv",
				   	     	header:false,
				   	        region:"center"
				   	    },{
				   	        id:"treeDiv", 
				   	        resizable:true,
				   	        collapsible:true,
				   	        title:"业务流程",
				   	        region:"east", 
				   	        width:200
				   	    }],
				   	    fit : true,
				   	    spacing:2
			    });
			    
	            /***公司选择下拉框***/	
		        $('#qCompanyCombo').omComboTree({
			            width:'180',
			            dataSource:'../evaluate/getAllCompanyTreeMode.do',
			      		valueField : 'id', 
			            multi: false,
			            forceSelction : true,
			            editable : false,
			            tree:{
				                treeLeafOnly:false
			            },
			            onSuccess:function(data){
			                if(data.length>0){
				                var orgId = "${orgId}";
				 		    	if(orgId==null||orgId==''){
					    	    	orgId = data[0].id;
					    	    }
					    	    this.omComboTree('value',orgId);
			    			}
							
		               },
		               onValueChange:function(target,newvalue,oldvalue,event){
		               		$('#businessTree').omTree('setData','getBusinessMode.do?organizationId='+newvalue).omTree("refresh");
		               }
			    });
			    
			    /*******业务流程树*********/
                $("#businessTree").omTree( {
					//dataSource : "getBusinessMode.do",
					showCheckbox : false,
					onSelect : function(nodedata) {
							   	    selectedNode = nodedata ;
							   	    bid = nodedata.id ;
									bname = nodedata.name ;
									$('#grid').omGrid('setData','getAllEvaluationMatrix.do?bid='+bid);
							    }
			    });
			    /*********表格组件************/
	            $('#grid').omGrid({
	             /******************/
            		title : '评价矩阵数据列表'+
			    		'<div style="float:right;padding-right:20px;padding-bottom:3px">&nbsp;&nbsp;'+
			    		'<sec:authorize ifAnyGranted="ROLE_EVAMATIRX_ADD">'+
			    		'<a id="create" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
			            '<a id="createCopy" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/file_obj.gif" title="复制" />&nbsp;复制&nbsp;</a>'+
			            '</sec:authorize>'+
			            '<sec:authorize ifAnyGranted="ROLE_EVAMATIRX_UPDATE">'+
			            '<a id="modify" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/text_edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
			            '</sec:authorize>'+
			            '<sec:authorize ifAnyGranted="ROLE_EVAMATIRX_DELETE">'+
			            '<a id="delete" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
			            '</sec:authorize>'+
			            '<sec:authorize ifAnyGranted="ROLE_EVAMATIRX_SEARCH">'+
			            '<a id="importExcel" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/import.gif" title="导入" />&nbsp;导入&nbsp;</a>'+
			            '<a id="exportExcel" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/export.gif" title="导出" />&nbsp;导出&nbsp;</a>'+
			            '</sec:authorize>'+
			            '</div>',
			            /***********************/ 
               		width : '100%',
               		method : 'post',
               		autoFit : false,
               		height : 'fit',
               		limit:  15,
               		singleSelect : true,
	                colModel : [ 
	               				 {header : "<fmt:message key='business.companyCode'/>", name : 'companyCode', width : 60,sort:'clientSide'},
                				 {header : "<fmt:message key='business.companyName'/>", name : 'companyName', width : 100},
	                             {header : "<fmt:message key='business.code'/>", name : 'bcode',  width : 100,sort:'clientSide'},
                                 {header : "<fmt:message key='business.name'/>", name : 'bname',  width : 100},
	                			 {header : "<fmt:message key='evaluationMatrix.page.riskNo'/>", name : 'riskNo', width : 100,sort:'clientSide'},
	                			 {header : "<fmt:message key='evaluationMatrix.page.controlObjNo'/>", name : 'controlActiveCode', width : 100,sort:'clientSide'},
	                			
	                			 {header : "<fmt:message key='evaluationMatrix.page.document'/>", name : 'invDocument', width : 100},
	                			 {header : "<fmt:message key='evaluationMatrix.page.testMethods'/>", name : 'testMethods', width : 100},
	                			 {header : "<fmt:message key='evaluationMatrix.page.papersNo'/>", name : 'papersNo', width : 100,sort:'clientSide'},
	                			 {header : "<fmt:message key='evaluationMatrix.page.testDescription'/>", name : 'testDescription', width : 200}
	                			],
	                onRowDblClick : function(rowIndex,rowData) {
	               		isAdd= false;
	                	showDialog('修改',rowData);
	                }
	            });
	            //**Drop table ** start**//
	            var timer = '_select1_timer';
	            var input = $('#controlActiveCode').click(function() {
	                 	var text = this.value;
	                 	text = $.trim(text);
	                 	var qStr = "";
	                	if(text==""){
	               	 		$('#controlActive-table').omGrid("setData", 'getControlActivities.do?bid='+bid);
	               	 	}else{
	               	 		$('#controlActive-table').omGrid("setData", "getControlActivities.do?qControlActiveCode="+encodeURI(text)+"&bid="+bid);
	               	 	}
	                     showCATDropList();
	                 }).blur(function() {
	                     window[timer] = setTimeout(function() {
	                        caTableDropList.hide();
	                     }, 450);
	                 }).keyup(function(e){
	                	var text = this.value;
	                 	text = $.trim(text);
	                 	if(text==""){
	               	 		$('#controlActive-table').omGrid("setData", 'getControlActivities.do?bid='+bid);
	               	 	}else{
	               	 		$('#controlActive-table').omGrid("setData", "getControlActivities.do?qControlActiveCode="+encodeURI(text)+"&bid="+bid);
	               	 	}
	               	 	showCATDropList();
	               	  	riskFormReset();
                 });
			    function riskFormReset(){   
					 	isSelected = false;
					 	$("#riskId").val('');
	                    $("#riskNo").val('');
	                    $("#riskDescription").val('');
	                    $("#controlActiveId").val('');
	                    $("#controlDescription").val('');
	                    $("#controlDocument").val('');
	                    $("#document").val('');
					} 
				
				var caTableDropList = $('#caTableDiv').mousedown(function(e) {
	                                            e.stopPropagation();
	                                            setTimeout(function() {
	                                                clearTimeout(window[timer]);
	                                            }, 25);
	                                        });
				function showCATDropList(){
					var inputOffset=input.offset();
					var dialogOffset = $('#dialogForm').offset();
					$('#dialogForm').css('overflow','visible').parent('div').css("overflow","visible")
					caTableDropList.css({position:'absolute',left:inputOffset.left - dialogOffset.left+"px",top:inputOffset.top - dialogOffset.top+input.outerHeight()+"px"})
										.show();
				}		                 
	             $(document.body).mousedown(function() {
	                caTableDropList.hide();
	             });                                        
	    
	            $('#controlActive-table').omGrid({
	             //   dataSource : 'getControlActivities.do?bid='+bid,
	                method : 'POST',
	                width : 400,
	                height : 300,
	            //    limit : 6, 
	                showIndex : false,
	                colModel : [
		               			{header : '<fmt:message key="controlActivity.table.code"/>', name : 'code', width : '80'},
		               			{header : '控制活动描述', name : 'description', width : '180'},
		               		    {header : '<fmt:message key="evaluationMatrix.page.riskNo"/>', name : 'riskNumber'},
		               		    {header : '<fmt:message key="evaluationMatrix.page.riskDescription"/>', name : 'riskDescription'}
	                            ],
	                onRowClick : function(event, rowData) {
	                    input.val(rowData.code).attr('_trueValue', rowData.id);
	                    $("#riskId").val(rowData.riskId);
	                    $("#riskNo").val(rowData.riskNumber);
	                    $("#riskDescription").val(rowData.riskDescription);
	                    
	                    $("#controlActiveId").val(rowData.id);
	                    $("#controlDescription").val(rowData.description);
	                    $("#controlDocument").val(rowData.involvedDocument);
	                    $("#document").val(rowData.involvedDocument);
	                    
	                    caTableDropList.hide();
	                }
	            });
            //**Drop table ** end**//
	            
	            
	            $('#query').omButton({
	            	width : 60,
	            	onClick : function() {
	            		var qCompanyCombo = $('#qCompanyCombo').omComboTree('value');
		            	var qRiskNo = $('#qRiskNo').val();
		            	var qPapersNo = $('#qPapersNo').val();
		            	$('#grid').omGrid({extraData:{qCompanyCombo:qCompanyCombo,qRiskNo:qRiskNo,qPapersNo:qPapersNo}})
		            	.omGrid('setData','getEvaMatroixByRiskNoOrPapersNo.do');
	            	}
	            });
	            $('#createCopy').bind('click', function() {
	            	//dialog.omDialog("open");
	            	var selections=$('#grid').omGrid('getSelections',true);
	                if (selections.length == 0) {
	                    alert("<fmt:message key='evaluationMatrix.js.alertChose'/>");
	                    return false;
	                }
	                isAdd= true;
	                showDialog($(this).val(),selections[0]);
	              $("input[name='id']",dialog).val(null);
	              //  $("#companyCode").val(companyCode);
                 //   $("#companyName").val(companyName);
	              
	            });
	            $('#create').bind('click', function() {
	            	var treeNode = $("#businessTree").omTree('getSelected');
	                if(treeNode==null){
	                   alert('请选择业务循环后再操作！');
	                   return false;
	                }
	                if(treeNode.hasChildren){
	                   alert('请选择末级业务循环后再操作！');
	                   return false;
	                }
	            	isAdd= true;
	            	showDialog($(this).val());
	            	
	            	var companyStrArray = $('#qCompanyCombo').next().val().split(' ');
	            	
	            	$("#companyCode").val(companyStrArray[0]);
                    $("#companyName").val(companyStrArray[1]);
                    $("input[name='bid']").val(treeNode.id);
                    $("input[name='bname']").val(treeNode.name);
                    $("input[name='bcode']").val(treeNode.code);
	            });
	            
	            var dialog = $("#dialogForm").omDialog({
					height: '400',
        			width: '600',
					autoOpen : false,
					modal : true,
					resizable : false,
					buttons : [ {
						text : "<fmt:message key='evaluationMatrix.js.confirm'/>",
						click : function() {
							submitDialog();
						}
					}, {
						text : "<fmt:message key='evaluationMatrix.js.Cancel'/>",
						click : function() {
							$("#dialogForm").omDialog("close");
						}
					} ]
				});
				var showDialog = function(title,rowData){
	                validator.resetForm();
	                rowData = rowData || {};
	               
	                $("#id").val(rowData.id);
	                $("#bid").val(rowData.firstProcessId);
	                $("#companyCode").val(rowData.companyCode);
                    $("#companyName").val(rowData.companyName);
	                $("#bcode").val(rowData.secondProcess);
	                $("#bname").val(rowData.secondProcessId);
	                
	                $("#riskId").val(rowData.riskId);
	                $("#riskNo").val(rowData.riskNo);
	                $("#riskDescription").val(rowData.riskDescription);
	                
	                $("#controlActiveId").val(rowData.controlActiveId);
	                $("#controlActiveCode").val(rowData.controlActiveCode);
	                $("#controlDescription").val(rowData.controlDescription);
	                $("#controlDocument").val(rowData.controlDocument);
	               
	                $("#document").val(rowData.invDocument);
	                $("#testMethods").omCombo('value',rowData.testMethods);
	                $("#testDescription").val(rowData.testDescription);
	                $("#papersNo").val(rowData.papersNo);
	                $("input[name='bid']").val(rowData.bid);
	                $("input[name='bname']").val(rowData.bname);
	                $("input[name='bcode']").val(rowData.bcode);

					dialog.omDialog("option", "title", title);
	                dialog.omDialog("open");
				};		
						 
						
				var submitDialog = function() {
					if(validator.form()){
						$("#orgForm").omAjaxSubmit({
							url : 'saveEvalMatrix.do',
							success : function(responseText){
								      	   if(responseText==='true'){
								      	  	  $("#dialogForm").omDialog("close");
								      	   	  parent.$.omMessageTip.show({title:"<fmt:message key='evaluationMatrix.js.save'/>", content:"<fmt:message key='evaluationMatrix.js.saveSuccess'/>", timeout: 3500});
								      	   	  $('#grid').omGrid('reload');
	                						  
								      	   }else{
								      	      alert("<fmt:message key='evaluationMatrix.js.saveFailure'/>");
								      	   }
								      }
						});
					};
				};
				
				var validator = $('#orgForm').validate({
									rules : {
										controlActiveCode:{required : true},
										document:{required : true},
										testMethods:{required : true},
										papersNo:{required : true},
										testDescription:{required : true}
									},
									messages :{
										controlActiveCode:{required : "<fmt:message key='evaluationMatrix.js.notEmpty'/>"},
										document:{required : "<fmt:message key='evaluationMatrix.js.notEmpty'/>"},
										testMethods:{required : "<fmt:message key='evaluationMatrix.js.notEmpty'/>"},
										papersNo:{required : "<fmt:message key='evaluationMatrix.js.notEmpty'/>"},
										testDescription:{required : "<fmt:message key='evaluationMatrix.js.notEmpty'/>"}
									}
								});
								
				$('#modify').bind('click', function() {
	                var selections=$('#grid').omGrid('getSelections',true);
	                if (selections.length == 0) {
	                    alert("<fmt:message key='evaluationMatrix.js.alertChose'/>");
	                    return false;
	                }
	                isAdd = false;
	                showDialog($(this).val(),selections[0]);
	            });
	            $('#delete').bind('click',function(e) {
				var selectionRows = $('#grid').omGrid('getSelections', true);
				if (selectionRows.length == 0) {
					parent.$.omMessageBox.alert( {
								title : "alert",
								content : "<fmt:message key='risk.selectNull'/>"
							});
					return false;
				}
				parent.$.omMessageBox.confirm({
					title : 'confirm',
					content : '确定删除当前行吗?',
					onClose : function(value) {
					if(value==false) return false;
			  	 	    var selectedRows=$('#grid').omGrid('getSelections',true);
					    if(selectedRows.length>0){
				  	 	  $.ajax({
				  	 	       url:'deleteEvalMatrix.do',
				  	 	       type:'post',
				  	 	       data:{
				  	 	         params : JSON.stringify(selectedRows),
				  	 	         operation : 'delete'
				  	 	         },
					  	 	  success : function(responseText){
					  	 		if(responseText=='true'){
					  	 			 	$('#grid').omGrid('reload');
					  	 			 	$('#operation').val("");
					  	 	  parent.$.omMessageTip.show({title: "<fmt:message key='dialog.submit.success'/>",content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
					  	 		}
				  	 	      }
				  	 	     
				  	 	});
			  	 	  }
					}
				});

			});
				//以下为上传Excel导入前台代码	
				 $('#file_upload').omFileUpload({	 	
				 	method : 'GET',
				    action : 'evaluationMatrixImportExcel.do',
				    swf : '../../jquery/swf/om-fileupload.swf',
				    fileExt : '*.xls;*.xlsx;',
				  	fileDesc : 'Excel文件',	    
				    onComplete : function(event,ID,fileObj,response,data){	    
				    	var jsonData = eval("("+fileObj+")");
				    	if(jsonData.count > 0){			    	
				    	$("#dialog-upload" ).omDialog("close");
				    	$('#response').html("已导入："+jsonData.count+"条记录");
				    	$('#grid').omGrid('reload');
						alert("执行成功！已导入："+jsonData.count+"条记录");
						}else{
						    $('#response').html("导入失败："+ jsonData.result);
							alert("导入失败："+ jsonData.result);
						}
				    }
		 		 });
	            $( "#dialog-upload").omDialog({
					autoOpen: false,
					width: 420,
					height: 300,
					modal: true,
					buttons: [{
		                text : "<fmt:message key='targetAim.upload'/>", 
		                click : function () {
							$('#file_upload').omFileUpload('upload');
		                }
		            }, {
		                text : "<fmt:message key='dialog.cancel'/>", 
		                click : function () {                    
		                    $("#dialog-upload" ).omDialog("close");
		                }
		            }]
					
				});			
				$('#importExcel').click(function(){
		          	   $( "#dialog-upload").omDialog('open');
		          	   $('#response').html("");
		         });
		         $('#exportExcel').click(function(){            
		       			var treeNode = $("#businessTree").omTree('getSelected');
		                if(treeNode==null){
		                   alert('请选择业务循环后再操作！');
		                   return false;
		                }
		                location.href="evaluationMatrixExportExcel.do?parBid="+treeNode.id;          	   
		         });
		         
		         $('#testMethods').omCombo({
		            width:145,
		         	dataSource:[
		         		{'text':'个人访谈','value':'个人访谈'},
		         		{'text':'调查问卷','value':'调查问卷'},
		         		{'text':'专题讨论','value':'专题讨论'},
		         		{'text':'穿行测试','value':'穿行测试'},
		         		{'text':'实地查验','value':'实地查验'},
		         		{'text':'抽样','value':'抽样'},
		         		{'text':'比较分析','value':'比较分析'},
		         		{'text':'其他','value':'其他'}
		         	],
		         	multi : true
		         
		         });
				
	     });
	        
    //]]>
    </script>
</head>
<body style="overflow: hidden">
<div id="searchDiv">
	<table style="width: 100%;height:100%">
		<tr >
			<td></td>
			<td valign="middle">
			    <font size="2.5px" style="width: 4%"><fmt:message key='organization.page.management'/>:</font> 
				<input id="qCompanyCombo" />
				<font size="2.5px" style="width: 4%"><fmt:message key='evaluationMatrix.page.riskNo'/>:</font> 
				<input id="qRiskNo" />
				<font size="2.5px" style="width: 4%"><fmt:message key='evaluationMatrix.page.papersNo'/>:</font> 
				<input id="qPapersNo" /> 
				<input id="query" type="button" value="<fmt:message key='evaluationMatrix.page.query'/>" />
			</td>
		</tr>
	</table>
</div> 
<div id="treeDiv" >
	<ul id="businessTree" class="tree"></ul>
</div>
<div id="tbdiv" >
    <table id="grid"></table>
</div>
<div id="dialogForm">
	<form id="orgForm" method="post">
		<input id="id" name="id" style="display: none" />
		<input id="riskId" name="riskId" style="display: none" >
		<input id="controlActiveId" name="controlActiveId" style="display: none" >
		<table id="dialogTable" width = '100%' cellspacing="5" border="0">
		   <tr>
				<td><fmt:message key='business.companyCode'/>：</td>
				<td>
   					<input id="companyCode" style="background: silver;"	name="companyCode" readonly="readonly" /></td>
				<td><fmt:message key='business.companyName'/>：</td>
				<td>
					<input id="companyName" style="background: silver;" name="companyName" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td>业务流程编号：</td>
				<td>
   					<input id="bcode" name="bcode" readonly="readonly" style="background: silver;"/>
					<input id="bid" name="bid" style="display:none"/></td>
				<td>业务流程名称：</td>
				<td>
					<input id="bname" name="bname" readonly="readonly" style="background: silver;"/>
				</td>
			</tr>
			<tr>
				<td><fmt:message key='evaluationMatrix.page.controlObjNo'/>：</td>
					<td> 
						<input id="controlDocument" name="controlDocument" style="display:none">
						<input id="controlDescription" name="controlDescription" style="display:none">
					<div id="controlActiveCodeDiv">
						<input id="controlActiveCode" name="controlActiveCode"> <!--If the display is set to none, the grid cannot be the correct height TODO -->
						<div id="caTableDiv" class="omcombo-ct" style="position: absolute; display: none; left: 0;">
						<table id="controlActive-table"></table>
						</div>
					</div>
				</td>
				<td><fmt:message key='evaluationMatrix.page.riskNo'/>：</td>
				<td>
					<input id="riskNo" name="riskNo" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td><fmt:message key='evaluationMatrix.page.document'/>：</td>
				<td><input id="document" name="document" readonly="readonly" /></td>
				<td><fmt:message key='evaluationMatrix.page.riskDescription'/>：</td>
				<td>
					<input id="riskDescription" name="riskDescription" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td><fmt:message key='evaluationMatrix.page.testMethods'/>：</td>
				<td><input id="testMethods" name="testMethods"/></td>
				<td><fmt:message key='evaluationMatrix.page.papersNo'/>：</td>
				<td><input id="papersNo" name="papersNo"/></td>
			</tr>
			<tr>
				<td><fmt:message key='evaluationMatrix.page.testDescription'/>：</td>
				<td colspan="4"><textarea id="testDescription" name="testDescription" rows="7" cols="60"></textarea></td>
			</tr>
		</table>
	</form>
</div>
<div id="dialog-upload" title="<fmt:message key='targetAim.import'/>">
	<p><fmt:message key='evaluationMatrix.uploadMessage' /></p>
	<input id="file_upload" name="file_upload" type="file" />
	<div id="response" style="font-weight: bold; color: red;"></div>
</div>		
		
</body>
</html>