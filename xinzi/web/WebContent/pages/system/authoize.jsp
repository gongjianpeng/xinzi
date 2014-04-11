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
		<script charset="UTF-8">
		//<![CDATA[
		$(document).ready(function(){
		
		 $('body').omBorderLayout({
			   panels:[
			        {
			       		id:"toptree", 
			   	        title:"用户",
			   	        region:"north",
			   	        expandToTop:true,
			   	           height:300
			        },
			        {
			       		id:"downtable", 
			   	        title:"用户角色",
			   	        region:"center",
			   	        expandToBottom:true
			        }],
			   	    spacing:0
			   	   
	    }); 
	    
	     
	     
		    var selectedNode;
		     $('#myTree').omTree(
				{
					showCheckbox : false,
					onSelect : function(nodedata) {
					   selectedNode = nodedata ;
					   var url = 'getPersonsByParent.do?parentId='+nodedata.id;
					   if(nodedata.isHeadquarters != null ){
					       url += '&isorg='+nodedata.isHeadquarters ;
					   }
					   personGrid.omGrid('setData',url);
					},
					onSuccess : function() {												
						fn = function(nodedata) {
							if (nodedata!=null && nodedata.id == selectedNode.id){
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
					dataSource : "getPersonTree.do",
					onSelect:function(rowData){
					    if(rowData.loginName != null){
					   	   rolesGrid.omGrid("setData","getRolesByPersonId.do?personId="+rowData.id);
					    }else{
					     rolesGrid.omGrid("setData","getRolesByPersonId.do?personId=no");
					    }
					}
			});
	       var rolesGrid = $('#rolesGrid').omGrid({
		        limit : 0,
		        //height : 195,
		        singleSelect:false,
		        colModel : [
		                   {header : "角色名", name : 'name',width : '300px', align : 'center'}
						   ]
	        }).bind('contextmenu',function(e){
		         var selectedRows= $(this).omGrid('getSelections');
		         if(selectedRows.length > 0){
		           menuRoles.omMenu('enableItem','002');
		         }
		       //  e.pageY = e.pageY - $(this).offset().top + 50;	             
		       //  e.pageX = e.pageX - $(this).offset().left;
		         menuRoles.omMenu('show',e);
		         return false ;
		      });
		      $('#rolesDiv').bind('contextmenu',function(e){
		          var selectedRow= $('#myTree').omTree('getSelected');
		          if(selectedRow == null || selectedRow.loginName == null){
		             return false ;
		          }
		          if(rolesGrid.omGrid('getSelections').length <=0){
		          	  menuRoles.omMenu('disableItem','002');
		          }else{
		              menuRoles.omMenu('enableItem','002');
		          }
		       //  e.pageY = e.pageY - $(this).offset().top + 25;	             
		       //  e.pageX = e.pageX - $(this).offset().left;
		         menuRoles.omMenu('show',e);
		         return false ;
		      });
	         var rolesGridItems = $('#rolesGridItems').omGrid({
	            dataSource : 'getRolesByPersonId.do' ,
		        limit : 10,
		        height : 300,
		        width : 298 ,
		        autoFit : true,
		        singleSelect:false,
		        colModel : [
		                   {header : "角色名", name : 'name', align : 'center'}
						   ]
	        });
	        var dialog = $('#dialog').omDialog({
	             title : "添加角色" ,
	             width: 320,
	             autoOpen : false,
	             buttons : {
	                "确定" : function(){
	                     setRoles();
	                     return false; 
	                },
	                "取消" : function() {
	                    dialog.omDialog("close");
	                }
	            }
	       }).css({padding:'0px',magin:'0px',valign:'bottom'});
	       
	       var setRoles = function(){
	          var selectedPerson =  $('#myTree').omTree('getSelected');
	          var selectedRoles = rolesGridItems.omGrid("getSelections",true);
	          var selectedStr = '' ;
	          for(i=0;i<selectedRoles.length;i++){
	             selectedStr += selectedRoles[i].id+',';
	          }
	          personId =  selectedPerson.id ;
	          $.post("setRoles.do",{personId : personId, rolesStr : selectedStr},function(responsText){
	                 if(responsText == 'true'){
	                    rolesGrid.omGrid("reload");
	                    dialog.omDialog('close');
	                 }
	          });
	          
	      };
	      var removeRoles = function(){
	          var selectedPerson =  $('#myTree').omTree('getSelected');
	          var selectedRoles = rolesGrid.omGrid("getSelections",true);
	          var selectedStr = '' ;
	          for(i=0;i<selectedRoles.length;i++){
	             selectedStr += selectedRoles[i].id+',';
	          }
	          personId =  selectedPerson.id ;
	          window.parent.omConfirm('确定要删除吗？',function(){
	              $.post("removeRoles.do",{personId : personId, rolesStr : selectedStr},function(responsText){
		                 if(responsText == 'true'){
		                    rolesGrid.omGrid("reload");
		                 }
		          });
	          });
	              
	      };
		 var menuRoles = $('#menuRoles').omMenu( {
				contextMenu : true,
				maxWidth : 180,
				dataSource : [ {
					id : '001',
					label : '添加角色',
					icon : '../../images/icons/add.gif'
				} , {
					id : '002',
					label : '删除角色',
					icon : '../../images/icons/delete.gif'
				} ],
				onSelect : function(item) {
					$("#menuRoles").omMenu("hide");
					if (item.id == "001") {
					      rolesGridItems.omGrid('refresh');
						  dialog.omDialog("open");
					} else if (item.id === "002") {
						 removeRoles();
					} 
				}
			});
      }); 
	 //]]>
	</script>
	</head>
	<fmt:bundle basename="messages.messages_audthoize" />
	<fmt:bundle basename="messages.messages_person"  />

	<body>
			<div id="toptree">
				<ul id="myTree"></ul>
			</div>
			
			<div id="downtable">
			    <div id="rolesDiv">
			    
			    <table id="rolesGrid"></table>
			    <div id="menuRoles"></div>
			    </div>
			    <div id="dialog">
				<table id="rolesGridItems"></table>
			    </div>
				</div>
	</body>
</html>