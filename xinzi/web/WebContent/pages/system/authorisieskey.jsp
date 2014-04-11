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
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<link rel="stylesheet" type="text/css" href="../../jquery/css/default/om-all.css" />
		<link rel="stylesheet" type="text/css" href="../../css/css.css" />
		<style type="text/css">
			body {
				font-family: Verdana;
				font-size: 14px;
				margin: 0px;
			}
		    input.omError {
		        border: 1px solid red;
		    }
		    #errorMessages span{
		    	display: block;
		    	background: url(../../images/icons/alert.png) 0 0 no-repeat;
		    	padding-left:20px;
		    }
		    .error{
			    background-color: #fff6bf;
				text-align: left;
				padding: 5px 20px 5px 15px;
				border: 2px solid #ffd324;
				display: none;
				width: 200px;
		    }
		
		</style>
		<script src="../../jquery/js/jquery.min.js"></script>
		<script src="../../jquery/js/operamasks-ui.min.js"></script>
		<script>
		<!--
		//<![CDATA[
		var selectedNode , parentNode ;
      	$(document).ready(function(){
      	
      	       var authorTree = $('#authorTree').omTree({
      	           dataSource : 'getAuthoritiesKeyTree.do',
      	           onRightClick : function(nodedata, e) {
						authorTree.omTree("select", nodedata);
						$('#menu').omMenu('show', e);
				   },
				   onSelect: function(nodedata){
				       selectedNode = nodedata ;
				       parentNode = authorTree.omTree('getParent',nodedata);
				   },
				   onSuccess: function(){
					     fn = function(nodedata) {
							if (nodedata.id == selectedNode.id){
								return true;
							}else{
								return false;
							}
						 }
						// authorTree.omTree('expandAll');
						 if(selectedNode!=null){
							 var currentnode = $('#myTree').omTree('findNodeBy',fn);
							 if(currentnode!=null){
							     $('#myTree').omTree('select',currentnode);
							 }
					    }
				   },
				   onDblClick : function(){
				        showDialog('修改权限',selectedNode,parentNode);
				   }
      	       }).bind('click',function(e){
				    $("#menu").omMenu("hide");
			   });
			   
			   $("#treeDiv").bind('contextmenu',function(e){
			      if(selectedNode){
			         authorTree.omTree("unselect", selectedNode);
			         selectedNode = null;
			         parentNode = null;
			      }
			      $('#menu').omMenu('show', e);
			   });
			   
      	       $('#menu').omMenu( {
						contextMenu : true,
						maxWidth : 180,
						dataSource : [ {
							id : '001',
							label : "新增",
							icon : '../../images/icons/add.gif'
						}, {
							id : '002',
							label : "修改",
							icon : '../../images/icons/edit.png'
						}, {
							id : '003',
							label : "删除",
							icon : '../../images/icons/delete.gif'
						} ],
						onSelect : function(item) {
						    $('#menu').omMenu('hide');
							if (item.id == "001") {
								showDialog(item.label,null,selectedNode);
							} else if(item.id == "002") {
							    showDialog(item.label,selectedNode,parentNode);
							}else if(item.id == "003") {
							    deleteAuthor(selectedNode);
							}
				         }
			   });
			   var dialog = $("#authorDialog").omDialog( {
					width : 350,
					height : 180,
					autoOpen : false,
					modal : true,
					resizable : true,
					buttons : [ {
						text : "保存",
						click : function() {
							submitDialog();
						}
					}, {
						text : "关闭",
						click : function() {
							dialog.omDialog("close");
						}
					} ]
			   });
			   var showDialog = function(title,data,parent){
			      data = data||{};
			      parent = parent||{};
			      $("input[name='id']").val(data.id);
			      $("input[name='name']").val(data.name);
			      if(!data.name){
			         $("input[name='value']").val(parent.value+'_');
			      }else{
			         $("input[name='value']").val(data.value);
			      }
			      $("input[name='parentId']").val(parent.id);
			      $("input[name='parentName']").val(parent.name);
			      
			      dialog.omDialog("option","title",title);
			      dialog.omDialog("open");
			   };
			   $("#authorForm").validate({
                 errorClass : "omError",
				 rules: {
				     name: {
				        required: true
				     },
				     value: {
				        required: true //,
				      //  maxlength:25 
				     }
				 },
				 messages :{
				     name: {
				        required: '必须输入!'
				     },
				     value: {
				        required: '必须输入!' //,
				     //   maxlength:'长度不能大于15位!'
				     }
				 }
			   });
			   var submitDialog = function(){
			      if($("#authorForm").valid()==false)return false;
			      $("#authorForm").omAjaxSubmit({
			          method : 'post',
			          url : 'saveAuthoritiesKey.do',
			          success : function(responseText){
			             if(responseText == 'true'){
			                window.parent.$.omMessageTip.show({title: '提示', content: '操作成功', timeout: 3500});
			                dialog.omDialog('close');
			                $('#authorTree').omTree('setData','getAuthoritiesKeyTree.do');
						    $('#authorTree').omTree('refresh');
			             }else{
			                window.parent.$.omMessageTip.show({title: '提示', content: '操作失败', timeout: 3500});
			             }
			          }
			      });
			   };
			   var deleteAuthor = function(data){
			       if( confirm("确定要删除吗？")){
			          $.post('deleteAuthoritiesKey.do',data,function(responseText){
				          if(responseText == 'true'){
				              window.parent.$.omMessageTip.show({title: '提示', content: '操作成功', timeout: 3500});
				              $('#authorTree').omTree('setData','getAuthoritiesKeyTree.do');
							  $('#authorTree').omTree('refresh');
				          }else{
				              window.parent.$.omMessageTip.show({title: '提示', content: '操作失败', timeout: 3500});
				          }
			          });
			      };
			   }
		  });
	//]]>
	//-->
        </script>
	</head>
	<fmt:bundle basename="messages.messages_authoritiesManager" />
	<body >
		<div id="treeDiv"
			style="height: 500px; font-size: 12px; border:2px solid;width:90%; padding-left:5px;border-color: skyblue; cursor: pointer; overflow: auto">
		<ul id="authorTree"></ul>
		<div id="menu"></div>
		</div>
		<div id="authorDialog">
		<form id="authorForm" method="post">
		<table>
			<tr>
				<td colspan="1" rowspan="1">上级权限：</td>
				<td><input name="parentId" style="display: none" /> <input name="parentName" style="background:; background-color: #D4D4D4" onfocus="blur();" readonly="readonly"/></td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1">权限名称：</td>
				<td colspan="1" rowspan="1"><input name="id" style="display: none" /> <input name="name" />
				</td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1">权限Key值：</td>
				<td colspan="1" rowspan="1"><input name="value" /></td>
			</tr>
		</table>
		 <div id="errorMessages" class="error"></div>
		</form>
		</div>

	</body>
</html>