<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>

<style type="text/css">
.noborder{
	border-bottom: 1px thick;
	border-left: none;
	border-right: none;
	border-top: none;
}
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
<script>
//<![CDATA[
var isHeader,parentName, parentId, id, code, name, address, remark, data;
var selectedNode ;

$(document).ready(function() {
	function loadCombo(select,comboType) {
		var op = '<option > </option>';
		select.html('');
		$.ajax( {
			type : 'GET',
			url : '../controlCriterion/getComboData2.do',
			data : {'comboType':comboType} ,
			async: false,
			contentType : 'application/json',
			dataType : 'json',
			success : function(data) {
				$.each(data, function(i, item) {
					op += '<option value=' + item.value + '>'
					+ item.text + '</option>';
				})
				select.html(op);
			}
		});
	};
	
	$('#myTree').omTree(
		{
			showCheckbox : false,
			onSelect : function(nodedata) {
			     selectedNode = nodedata ;
				 if(nodedata.isHeadquarters != null){
					 $('#mytable').omGrid('setData','getOrganizationByTreeId2.do?id=' + nodedata.id);
					 $('#deptTable').omGrid('setData','getDepartmentsByOrg2.do?orgId=' + nodedata.id);
					 $('#menu').omMenu('enableItem','001').omMenu('enableItem','002').omMenu('enableItem','003')
					  .omMenu('enableItem','004').omMenu('disableItem','005').omMenu('disableItem','006');
				 }else if(nodedata.loginName == null){
					 $('#mytable').omGrid('setData','getOrganizationByTreeId2.do');
					 $('#deptTable').omGrid('setData','getDepartmentsByOrg2.do?deptId=' + nodedata.id);
					 $('#menu').omMenu('disableItem','001').omMenu('disableItem','002').omMenu('disableItem','003')
					  .omMenu('enableItem','004').omMenu('enableItem','005').omMenu('enableItem','006');
				 }else{
					 $('#mytable').omGrid('setData','getOrganizationByTreeId2.do');
					 $('#deptTable').omGrid('setData','getDepartmentsByOrg2.do');
					 $('#menu').omMenu('disableItem','001').omMenu('disableItem','002').omMenu('disableItem','003')
					  .omMenu('disableItem','004').omMenu('disableItem','005').omMenu('disableItem','006');
				 }
				var parentNode = $('#myTree').omTree('getParent', nodedata);
				if(parentNode!=null){
				    parentName = parentNode.name;
				    parentId = parentNode.id;
				}else{
					parentName = '';
				    parentId = '';
				}
				id = nodedata.id;
				code = nodedata.code;
				name = nodedata.name;
				address = nodedata.address;
				remark = nodedata.remark;
				data = nodedata;
				isHeader = nodedata.isHeadquarters
				$("#code1").val(code);
				$("#name1").val(name);
				$("#address1").val(address);
				$("#remark1").val(remark);
				$("#parent1").val(parentName);
			},
			onRightClick : function(nodedata, e) {
				$('#myTree').omTree("select", nodedata);
				$('#menu').omMenu('show', e);
				isHeader = false;
			},
			onSuccess : function() {												
				fn = function(nodedata) {
					if (nodedata.id == selectedNode.id && nodedata.isHeadquarters==selectedNode.isHeadquarters){
						return true;
					}else{
						return false;
					}
				 }
				 $('#myTree').omTree('expandAll');
				 if(selectedNode!=null){
					 var currentnode = $('#myTree').omTree('findNodeBy',fn);
					 if(currentnode!=null){
					     $('#myTree').omTree('select',currentnode);
					 }
				 }
			},
			dataSource : "getOrganizationTree2.do"
	}).bind('click',function(e){
		$("#menu").omMenu("hide");
    });
	
	$('#org-tab').omTabs( {
		height : 300,
		border : false,
		closable : false,
		tabWidth: 'auto'
	});
	$("#tab1").css({  padding:"15px"})
	$("#tab2").css({  padding:"0px", border:"0px",margin:"0px"})
	$("#tab3").css({  padding:"0px", border:"0px",margin:"0px"})
	
	$('#mytable').omGrid( {
		height : 200,
		limit : 0 ,
		colModel : [ {
			header : $('#jsTableCode').val(),
			name : 'code',
			width : 100,
			align : 'center'
		}, {
			header : $('#jsTableName').val(),
			name : 'name',
			width : 120,
			align : 'center'
		}, {
			header : $('#jsTableAddress').val(),
			name : 'address',
			align : 'center',
			width : 120
		}, {
			header : $('#jsTableRemark').val(),
			name : 'remark',
			align : 'center',
			width : 120
		}
		]
	});
	
	initDeptGrid();
	
	var menu=$('#menu').omMenu( {
		contextMenu : true,
		maxWidth : 180,
		dataSource : [ {
			id : '001',
			label : $('#jsMenuAdd').val(),
			icon : '../../images/icons/add.gif'
		}, {
			id : '002',
			label : $('#jsMenuUpdate').val(),
			icon : '../../images/icons/edit.png'
		}, {
			id : '003',
			label : $('#jsMenuDelete').val(),
			icon : '../../images/icons/delete.gif',
			seperator : true
		} , {
			id : '004',
			label : $('#jsAdddept').val()
		}, {
			id : '005',
			label :'修改标签数据'
		}, {
			id : '006',
			label : '删除标签数据'
		}],
		onSelect : function(item) {
		    menu.omMenu('hide');
			if (item.id == "001") {
				showAddDialog(item.label);
			} else if (item.id === "002") {
				showUpDialog(item.label);
			} else if (item.id === "003") {
				if (data.hasChildren == true) {
					$.omMessageBox.alert({
			           content:$('#jsAlert').val()
				    });
				    return false;
				} else {
					showConfirm(data.id);
				}
			}else if(item.id == "004"){
				showDeptDialog('新增节点');
			}else if(item.id == "005"){
				modifydepartment() ;
			}else if(item.id == "006"){
				if (data.hasChildren == true) {
					$.omMessageBox.alert({
			           content:$('#jsAlert').val()
				    });
				} else {
					deletedepartment() ;
				}
			}
			return false
		}
	}).css('z-index','100');
	

	var showAddDialog = function(title) {
		validator.resetForm();
		if(selectedNode!=null){
			$("#isHeadquarters").val([false]);
		}else{
			$("#isHeadquarters").val([true]);
		}
		loadCombo($('#par'),'org');
		$("#parent").val(parentName);
		$("#parentId").val(parentId);
		$("#par").val(id);
		$("#id").val('');
		$("#code").val('');
		$("#name").val('');
		$("#address").val('');
		$("#remark").val('');
		$("#par").attr('readonly',true);
		
		dialog.omDialog("open").omDialog("option","title",title);
	};
	function showUpDialog(title) {
		validator.resetForm();
		if(selectedNode!=null){
			$("#isHeadquarters").val([selectedNode.isHeadquarters]);
			loadCombo($('#par'),'org');
			$("#par").val(parentName);
			$("#parentId").val(parentId);
			$("#par").val(parentId);
			$("#id").val(id);
			$("#code").val(code);
			$("#name").val(name);
			$("#address").val(address);
			$("#remark").val(remark);
			$("#par").attr('readonly',false);
			
			dialog.omDialog("open").omDialog("option","title",title);
		}
	};
	function showConfirm(id) { 
		if(parent.omConfirm('确定要删除当前节点吗？',function(){
		    $.ajax({
				url:'organizationDelete2.do?id=' + id,
				success:function(data){
					if(data=='true'){
					   $.omMessageTip.show({title: $('#jsDialogSaveOk').val(), content: $('#jsDialogSaveOk').val(), timeout: 3500});
					   $('#myTree').omTree('setData','getOrganizationTree2.do');
				       $('#myTree').omTree('refresh');
					 }else{
					   $.omMessageTip.show({title: $('#jsDialogOperationFailed').val(), content: data, timeout: 3500});
					}
			     },
			    error:function(xhr){
			    	 alert(xhr.responseText);
			     }
		       
			});
		}));
	}

	var dialog = $("#dialog-form").omDialog( {
		width : 350,
		height : 270,
		autoOpen : false,
		modal : true,
		resizable : true,
		buttons : [ {
			text : $('#jsDialogSubmit').val(),
			click : function() {
				submitDialog();
			}
		}, {
			text : $('#jsDialogCancel').val(),
			click : function() {
				$("#dialog-form").omDialog("close");
			}
		} ]
	});

	var submitDialog = function() {
		if (validator.form()) {
			$("#orgForm").omAjaxSubmit(
				{
					method : 'post',
					url : 'organizationSave2.do',
					success : function(responseText) {
				          if(responseText=="true"){
							  $.omMessageTip.show({title: $('#jsDialogSaveOk').val(), content: $('#jsDialogSaveOk').val(), timeout: 3500});
							  $("#dialog-form").omDialog("close");
							  $('#myTree').omTree('setData','getOrganizationTree2.do');
							  $('#myTree').omTree('refresh');
				          }else{
				        	  $.omMessageTip.show({title: $('#jsDialogOperationFailed').val(), content: data, timeout: 3500});
				          }
					}
				});
		}
	};
	
	var validator = $('#orgForm')
			.validate(
					{
						rules : {
							code : {
								required : true
							},
							name : {
								required : true
							},
							address : {
								required : true
							}
						},
						messages : {
							code : {
								required : $(
										'#jsValidatorCodeNotEmpty')
										.val()
							},
							name : {
								required : $(
										'#jsValidatorNameNotEmpty')
										.val()
							},
							address : {
								required : $(
										'#jsValidatorAddressNotEmpty')
										.val()
							}
						}
					});
	$('#treeDiv').bind('contextmenu',function(e){
		parentId = '';
		parentName = '' ;
		id = ''; 
		code = '';
		name = ''; 
		address = '';
		remark = '';
		if(selectedNode!=null){
		   $('#myTree').omTree('unselect',selectedNode );
		   selectedNode = null ;
		}
		$('#menu').omMenu('enableItem','001').omMenu('disableItem','002').omMenu('disableItem','003')
		.omMenu('disableItem','004').omMenu('disableItem','005').omMenu('disableItem','006');
		$('#menu').omMenu('show',e);
	});
   
	var deptvalidator = $('#deptForm').validate(
		{
			rules : {
				code : {
					required : true
				},
				name : {
					required : true
				}
			},
			messages : {
				code : {
					required : $(
							'#jsValidatorCodeNotEmpty')
							.val()
				},
				name : {
					required : $(
							'#jsValidatorNameNotEmpty')
							.val()
				}
			}
	});
	var deptdialog = $("#dialog-dept").omDialog( {
		width : 350,
		height : 370,
		autoOpen : false,
		modal : true,
		resizable : true,
		buttons : [ {
			text : $('#jsDialogSubmit').val(),
			click : function() {
			   if(deptvalidator.form()){
					$("#deptForm").omAjaxSubmit(
					{
						url : 'departmentSave2.do',
						success : function(responseText) {
						            if(responseText=="true"){
										$.omMessageTip.show({title: $('#jsDialogSaveOk').val(), content: $('#jsDialogSaveOk').val(), timeout: 3500});
										$("#dialog-dept").omDialog("close");
										 $('#myTree').omTree('setData','getOrganizationTree2.do').omTree('refresh');
										 $('#deptTable').omGrid('reload');
						            }else{
						            	$.omMessageTip.show({title: $('#jsDialogOperationFailed').val(), content: data, timeout: 3500});
						            }
						}
					});
				}
				return false;
			}
		}, {
			text : $('#jsDialogCancel').val(),
			click : function() {
				$("#dialog-dept").omDialog("close");
			}
		} ]
	});
	var showDeptDialog = function(title,rowData){
		deptvalidator.resetForm();
		if(rowData == null){
			parentData = $('#myTree').omTree('getSelected');
		}else{
			parentData = $('#myTree').omTree('getParent',rowData);
		}
        rowData = rowData || {};
        $("input[name='id']",deptdialog).val(rowData.id);
        $("input[name='parentId']",deptdialog).val(parentData.id);
        $("input[name='parentName']",deptdialog).val(parentData.name);
        $("input[name='isOrg']",deptdialog).val(parentData.isHeadquarters);
        $("input[name='code']",deptdialog).val(rowData.code);
        $("input[name='name']",deptdialog).val(rowData.name);
        $("input[name='address']",deptdialog).val(rowData.address);
        $("input[name='remark']",deptdialog).val(rowData.remark);
         $("input[name='inputname2']",deptdialog).val(rowData.inputname2);
           $("input[name='inputname3']",deptdialog).val(rowData.inputname3);
        deptdialog.omDialog("option", "title", title);
        deptdialog.omDialog("open");
    };

    var modifydepartment = function(){
		var treeselectrow = $('#myTree').omTree('getSelected');
		if( treeselectrow == null){
			$.omMessageBox.alert({title:"提示",content:"请选择数据行！"});
			return false;
		}
		showDeptDialog('数据列表修改',treeselectrow);
	};
	var deletedepartment = function(){
		var treeselectrow = $('#myTree').omTree('getSelected');
		if(treeselectrow == null){
			$.omMessageBox.alert({title:"提示",content:"请选择数据行！"});
			return false;
		}else{
		    function deletedept(){
			   $.ajax(
					{url:'departmentDelete2.do',
				     data:{id:treeselectrow.id},
				     success:function(data){
						if(data=='true'){
						    $.omMessageTip.show({title: $('#jsDialogSaveOk').val(), content: $('#jsDialogSaveOk').val(), timeout: 3500});
						    $('#myTree').omTree('setData','getOrganizationTree2.do').omTree('refresh');
						}else{
							$.omMessageBox.alert({title: $('#jsDialogOperationFailed').val(), content: data});
						}
					 },
					error:function(jqXHR, textStatus, errorThrown){
						 alert("操作出错:"+jqXHR.responseText);
					}
				});
			}
			parent.omConfirm($('#jsMakeSure').val(),deletedept);
		/*	parent.$.omMessageBox.confirm({
				title:'提示',
				content:'确定要删除吗？',
				onClose:function(v){
					if(v){
						deletedept();
					}
				}
		
			});
			*/
	   }
	   
	};
});
     
function initDeptGrid(){
	$('#deptTable').omGrid( {
		height : 200,
		limit : 0 ,
		colModel : [ {
			header : "代码",
			name : 'code',
			width : 120,
			align : 'center'
		}, {
			header : "名称",
			name : 'name',
			width : 150,
			align : 'center'
		},{
			header : "图片地址",
			name : 'address',
			width : 150,
			align : 'center'
		}, {
			header :"关键值",
			name : 'remark',
			align : 'center',
			width : 150
		},{
			header :"核对字段",
			name : 'inputname2',
			align : 'center',
			width : 150
		},
		{
			header :"所属厂商编号",
			name : 'inputnam3',
			align : 'center',
			width : 150
		}
		]
		
	});
			
  }
//]]>
</script>
</head>
<body style="overflow: hidden" title="diy树状图">
<div id="treeDiv"
	style="height: 285px; border: 2px solid; border-color: skyblue; cursor: pointer; overflow: auto">
<ul id="myTree"></ul>
<div id="menu"></div>
</div>
<div id="org-tab" style="margin: 0px">
<ul>
	<li>
	<a href="#tab1"> <fmt:message key='organization.page.management.detail2' /> </a>
	</li>
	<li>
	<a href="#tab2"> <fmt:message key='organization.page.management.children2' /> </a>
	</li>
	<li>
	<a href="#tab3"> <fmt:message key='organization.page.management.list' /> </a>
	</li>
</ul>
<div id="tab1" style="margin: 0px;">
<table style="left: 0px">
	<tr>
		<td><fmt:message key='organization.page.code' />:</td>
		<td><input id="code1" name="code1" readonly="readonly" onfocus="blur();" class="noborder"/></td>
	</tr>
	<tr>
		<td><fmt:message key='organization.page.name' />:</td>
		<td><input id="name1" name="name1" readonly="readonly" onfocus="blur();" class="noborder"/></td>
	</tr>
	<tr>
		<td><fmt:message key='organization.page.parOrganization' />:</td>
		<td><input id="parent1" name="parent1" readonly="readonly" onfocus="blur();" class="noborder"/></td>
	</tr>
	<tr>
		<td><fmt:message key='organization.page.address' />:</td>
		<td><input id="address1" name="address1" readonly="readonly" onfocus="blur();" class="noborder"/></td>
	
	</tr>
	<tr>
		<td><fmt:message key='organization.page.remark' />:</td>
		<td><input id="remark1" name="remark1" readonly="readonly" onfocus="blur();" class="noborder"/></td>
	
	</tr>
	
</table>
</div>

<div id="tab2" style="overflow: hidden; margin-left: 0px; top: 0px">
<table id="mytable"></table>
</div>
<div id="tab3" style="overflow: hidden; left: 0px; top: 0px">
<table id="deptTable"></table>
</div>
</div>

<div id="dialog-form">
<form id="orgForm" method="post">
<input id="parentId" name="parentId" style="display: none" /> <input id="id" name="id"
	style="display: none" />
<table>
	<tr>
		<td>是否父节点:</td>
		<td><input type="checkbox" id="isHeadquarters" name="isHeadquarters" value="true" /></td>
	</tr>
	<tr>
		<td><fmt:message key='organization.page.code' /></td>
		<td><input id="code" name="code" /></td>
	</tr>
	<tr>
		<td><fmt:message key='organization.page.name' /></td>
		<td><input id="name" name="name" /></td>
	</tr>
	<tr>
		<td><fmt:message key='organization.page.parOrganization' /></td>
		<td>
		<select id="par" name="par" style="width: 135px">
		</select>
		</td>
	</tr>
	<tr>
		<td><fmt:message key='organization.page.address' /></td>
		<td><input id="address" name="address" /></td>
	</tr>
	<tr>
		<td><fmt:message key='organization.page.remark' /></td>
		<td><input id="remark" name="remark" /></td>
	</tr>
	
</table>
</form>
</div>
<div id="dialog-dept">
<form id="deptForm" method="post">
<input name="id" style="display: none" />
<table>
	<tr>
		<td>所属上级</td>
		<td><input name="isOrg" style="display: none" /> <input name="parentId"
			style="display: none" /> <input name="parentName" readonly="readonly"
			onfocus="javascript:this.blur()" /></td>
	</tr>
	<tr>
		<td>代码</td>
		<td><input name="code" /></td>
	</tr>
	<tr>
		<td>名称</td>
		<td><input name="name" /></td>
	</tr>
	<tr>
		<td>地址</td>
		<td><input name="address" /></td>
	</tr>
	<tr>
		<td>关键值</td>
		<td><input name="remark" /></td>
	</tr>
	<tr>
		<td>核对字段</td>
		<td><input id="inputname2" name="inputname2" /></td>
	</tr>
	<tr>
		<td>所属厂商 编号</td>
		<td><input id="inputname3" name="inputname3"  maxlength="8"/></td>
	</tr>
	
</table>
</form>
</div>
<input id="jsTableCode" value="<fmt:message key='organization.page.code'/>" style="display: none" />
<input id="jsTableName" value="<fmt:message key='organization.page.name'/>" style="display: none" />
<input id="jsTableAddress" value="<fmt:message key='organization.page.address'/>"
	style="display: none" /> <input id="jsTableRemark"
	value="<fmt:message key='organization.page.remark'/>" style="display: none" /> <input
	id="jsMenuAdd" value="<fmt:message key='organization.js.addOrganization2'/>" style="display: none" />
<input id="jsMenuUpdate" value="<fmt:message key='organization.js.delOrganization2'/>"
	style="display: none" /> <input id="jsMenuDelete"
	value="<fmt:message key='organization.js.upOrganization2'/>" style="display: none" /> <input
	id="jsAlert" value="<fmt:message key='organization.js.hasParentOrganization'/>"
	style="display: none" /> <input id="jsConfirmDel"
	value="<fmt:message key='organization.js.confirmDel'/>" style="display: none" /> <input
	id="jsMakeSure" value="<fmt:message key='organization.js.makeSure'/>" style="display: none" /> <input
	id="jsDialogSubmit" value="<fmt:message key='organization.js.Submit'/>" style="display: none" /> <input
	id="jsDialogCancel" value="<fmt:message key='organization.js.Cancel'/>" style="display: none" /> <input
	id="jsDialogSaveOk" value="<fmt:message key='organization.js.operationOk'/>" style="display: none" />
<input id="jsDialogOperationFailed" value="<fmt:message key='organization.js.operationFailed'/>"
	style="display: none" /> <input id="jsValidatorCodeNotEmpty"
	value="<fmt:message key='organization.js.codeNotEmpty'/>" style="display: none" /> <input
	id="jsValidatorNameNotEmpty" value="<fmt:message key='organization.js.nameNotEmpty'/>"
	style="display: none" /> <input id="jsValidatorParentOrgNotEmpty"
	value="<fmt:message key='organization.js.parentOrgNotEmpty'/>" style="display: none" /> <input
	id="jsValidatorAddressNotEmpty" value="<fmt:message key='organization.js.addressNotEmpty'/>"
	style="display: none" /> <input id="jsAdddept"
	value="<fmt:message key='organization.page.adddept2'/>" style="display: none" />
</body>
</html>