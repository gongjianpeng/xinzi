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
		<link rel="stylesheet" type="text/css" href="../../css/css.css" />
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
		<script>
		 	//<![CDATA[
		 	$(document).ready(function(){
		 
          $('body').omBorderLayout({
			   panels:[
			        {
			       		id:"top1", 
			   	        title:"角色",
			   	        expandToBottom:true,
           	            expandToTop:true,
           	            region:"west",
           	            height:600
			        },
			        {
			       		id:"top3", 
			   	        title:"角色权限",
			   	        region:"center",
			   	        height:600
			        }],
			   	    spacing:0
			   	   
	    }); 
	    
              var rolesGrid = $('#rolesGrid').omGrid({
                dataSource : 'getRoles.do' ,
                limit : 0,
		        height : 450,
		        singleSelect:true,
		        autoFit:true,
		        colModel : [
		                   {header : "角色名", name : 'name', align : 'left'}
						   ],
			    onRowDblClick:function(){
			       $('#modfiy').click();
			    }  ,
			    onRowSelect:function(rowIndex,rowData){
			       rolesAuthorGrid.omGrid('setData','getRolesAuthorities.do?rolesId='+rowData.id);

			    }
	          })	;	
	          var rolesAuthorGrid = $('#rolesAuthorGrid').omGrid({
		        limit : 0 ,
		        height : 450 ,
		        singleSelect:false,
		        autoFit:true,
		        colModel : [
		                   {header : "权限名称", name : 'name', align : 'left'}
						   ]
	          });	
	          var rolesDialog = $('#rolesDialog').omDialog({
	             width: 300,
	             autoOpen : false,
	             buttons : {
	                "确定" : function(){
	                     saveRoles();
	                     return false; 
	                },
	                "取消" : function() {
	                    $('#rolesDialog').omDialog("close");
	                }
	             }
	          });
	          var saveRoles = function(){
	              $('#rolesForm').omAjaxSubmit(
	               {
	                    method : 'post',
		                url:'saveRoles.do',
		                clearForm:true,
		                beforeSubmit : function(data,$form,options){
		                  if( $("input[name='name']",$form).val()==''){
                             window.parent.$.omMessageBox.alert({title: '提示', content: '角色名不能为空！'});
                             return false;
                          }
		                },
		                success: function(data){
		                   if(data=='true'){
		                      rolesGrid.omGrid("reload");
		                      rolesDialog.omDialog('close');
		                      window.parent.$.omMessageTip.show({title: '提示', content:'操作成功', timeout: 3500});
		                   }
		                }
	               }
	              )
	          };
	          var showRolesDialog = function(title,data){
	              data=data||{}
	              $("input[name='id']",rolesDialog).val(data.id);
	              $("input[name='name']",rolesDialog).val(data.name);

			    $("input[name='dataLevel']",rolesDialog).each(function(){
				  if(data.dataAuthorLevel && data.dataAuthorLevel.indexOf($(this).val()) != -1){
					$(this).attr("checked","true");
				  }else{
					$(this).removeAttr("checked");
				  }
			    });
			    
			    rolesDialog.omDialog("option", "title",title);
	              rolesDialog.omDialog('open');
	          };
	          
	          var removeRoles = function(){
	             var selectedRoles = rolesGrid.omGrid("getSelections",true);
	             if(selectedRoles.length<=0){
	                window.parent.$.omMessageBox.alert({title:'提示',content:'请选择要删除的角色！'});
	                return false;
	             }
	             parent.omConfirm('确定要删除吗?', function(){
                      $.post("deleteRoles.do",selectedRoles[0],function(responsText){
		                 if(responsText == 'true'){
		                    rolesGrid.omGrid("reload");
		                    window.parent.$.omMessageTip.show({title: '提示', content:'操作成功', timeout: 3500});
		                    return false
		                 }else{
		                    window.parent.$.omMessageBox.alert({title: '提示', content:responsText, timeout: 3500});
		                 }
		             });
                 });
	          }; 
	          $('#add').click(function(){
	              showRolesDialog('添加角色');
	          });
	          $('#modfiy').click(function(){
	             var selectedRoles = rolesGrid.omGrid("getSelections",true);
	             if(selectedRoles.length<=0){
	                window.parent.$.omMessageBox({title:'提示',content:'请选择要修改的行！'});
	                return false;
	             }
	              showRolesDialog('修改角色',selectedRoles[0]);
	          });
	          $('#remove').click(function(){
	             removeRoles();
	          });  
	          $('#addAuthor').click(function(){
	             var selectedRows= $('#rolesGrid').omGrid('getSelections');
		         if(selectedRows.length <= 0){
		             window.parent.$.omMessageBox.alert({title:'提示',content:'请选择角色后再操作!'});
		             return false ;
		         }
	             authorKeyDialog.omDialog("open");
	          });
	          $('#removeAuthor').click(function(){
	             removeAuthor();
	          });
	          
	          $('#rolesAuthorMenu').omMenu({
	             contextMenu : true,
				 maxWidth : 180,
				 dataSource : [ {
					id : '001',
					label : '添加权限',
					icon : '../../images/icons/add.gif'
				 } , {
					id : '002',
					label : '删除权限',
					icon : '../../images/icons/delete.gif'
				 } ],
				 onSelect : function(item) {
					$('#rolesAuthorMenu').omMenu("hide");
					if (item.id == "001") {
						authorKeyDialog.omDialog("open");
					} else if (item.id === "002") {
				        removeAuthor();     
				    }
				    return false
				 }
	          });
	          function removeAuthor(){
	              var selectedRolesAuthors = rolesAuthorGrid.omGrid('getSelections',true);
		             if(selectedRolesAuthors.length <= 0){
		                windon.parent.$.omMessageBox.alert({title:'提示',content:'请选择要删除的权限！'});
		                return false;
		             }
		             var selectedStr = '' ;
			         for(i=0;i<selectedRolesAuthors.length;i++){
			             selectedStr += selectedRolesAuthors[i].id+',';
			         }
			      $.omMessageBox.confirm({
			           title:'确认移除',
			           content:'确定要移除这些权限吗？',
			           onClose:function(v){
			               if(v){
				               $.post("removeRolesAuthorities.do",{selectedStr:selectedStr},function(responsText){
					                 if(responsText == 'true'){
					                    rolesAuthorGrid.omGrid("reload");
					                    window.parent.$.omMessageTip.show({title: '提示', content:'操作成功', timeout: 3500});
					                 }else{
					                    window.parent.$.omMessageTip.show({title: '提示', content:'操作失败', timeout: 3500});
					                 }
					          });
				          }
			           }
			       });
			           
		    /*     window.parent.omConfirm('确定要删除吗？', function(){
                      $.post("removeRolesAuthorities.do",{selectedStr:selectedStr},function(responsText){
		                 if(responsText == 'true'){
		                    rolesAuthorGrid.omGrid("reload");
		                    window.parent.$.omMessageTip.show({title: '提示', content:'操作成功', timeout: 3500});
		                 }else{
		                    window.parent.$.omMessageTip.show({title: '提示', content:'操作失败', timeout: 3500});
		                 }
		          });
                  })
                  */
	          }
	          $('#rolesAuthorDiv').bind('contextmenu',function(e){
		         var selectedRows= $('#rolesGrid').omGrid('getSelections');
		         if(selectedRows.length <= 0){
		             return false ;
		         }
		         var selectedRolesAuthrs = rolesAuthorGrid.omGrid('getSelections');
		         if(selectedRolesAuthrs.length <= 0){
		             $('#rolesAuthorMenu').omMenu('disableItem','002');
		         }else{
		             $('#rolesAuthorMenu').omMenu('enableItem','002');
		         }
		         e.pageY = e.pageY - $(this).offset().top + 25;	             
		         e.pageX = e.pageX - $(this).offset().left;
		         $('#rolesAuthorMenu').omMenu('show',e);
		         return false ;
		      });
	          var authorTree = $('#authorTree').omTree({
	              dataSource : '../system/getAuthoritiesKeyTree.do',
	              showCheckbox : true,
	              cascadeCheck:true 
	             // onSuccess : function(){ authorTree.omTree('expandAll');}
	          });
	          var authorKeyDialog = $('#authorKeyDialog').omDialog({
	              title : "添加权限" ,
	              width: 300,
	              height: 400,
	              autoOpen : false,
	              buttons : {
	                "确定" : function(){
	                     setAuthorKeys();
	                     return false; 
	                },
	                "取消" : function() {
	                    authorKeyDialog.omDialog("close");
	                }
	             }
	          }).css({padding:'0px',magin:'0px',valign:'bottom'});
	          
	         
	         var setAuthorKeys = function(){
		          var selectedRoles =  rolesGrid.omGrid('getSelections',true);
		          var selectedAuthorKeyS = authorTree.omTree("getChecked",true);
		          var selectedStr = '' ;
		          for(i=0;i<selectedAuthorKeyS.length;i++){
		             selectedStr += selectedAuthorKeyS[i].id+',';
		             
		          }
		          
		          rolesId =  selectedRoles[0].id ;
		          $.post("setAuthoritiesKey.do",{rolesId : rolesId, authorKeys : selectedStr},function(responsText){
		                 if(responsText == 'true'){
		                    rolesAuthorGrid.omGrid("reload");
		                    authorKeyDialog.omDialog('close');
		                    authorTree.omTree('checkAll',false);
		                 }else{
		                    window.parent.$.omMessageTip.show({title: '提示', content:'操作失败', timeout: 3500});
					                 }
		          });
		    };
		    $("#search").click(function(){
		        rolesGrid.omGrid('setData','getRoles.do?rolesName='+ $("input[name='rolesName']").val());
		    });
	     }) ;  
	        //]]>
		</script>

	</head>
	<fmt:bundle basename="messages.messages_rolesManager"  />
	
	<body>
	           <div id="top1">
				<div  style="align: left; height: 30px; padding: 5px">
				<input id="rolesName"	name="rolesName" style="height: 20px; left: 0px" /> 
				<input id="search" type="button" class="button09" value="查询" />
				 <input id="add" type="button"	class="button09" value="添加" />
				  <input id="modfiy" type="button"	class="button09" value="修改" /> 
				  <input id="remove" type="button" 	value="删除" class="button09" />
			   	
				</div>
				<div id="top2">
				<table id="rolesGrid"></table>
				</div>
				<div id="rolesDialog">
				<form id="rolesForm" method="post">
				<table>
					<tr>
						<td>角色名:</td>
						<td><input name="id" style="display: none" /> <input name="name" /></td>
					</tr>
					<tr>
						<td>数据权限:</td>
						<td valign="middle">
						 <input type="checkbox" name="dataLevel" value="all"/>全部
						 <input type="checkbox" name="dataLevel" value="org"/>本公司
						 <input type="checkbox" name="dataLevel" value="dept"/>本部门
						</td>
					</tr>
					<tr>
					<td></td>
					<td>
						<input type="checkbox" name="dataLevel" value="project"/>本项目
						<input type="checkbox" name="dataLevel" value="self"/>本人
					</td>
					</tr>
				</table>
				</form>
				</div>
				
				</div>
				
				
				
				
				<div id="top3">
				
				<div style="align: left; height: 30px; padding: 5px">
				<input id="addAuthor" type="button"	class="button09" value="添加" /> 
				<input id="removeAuthor" type="button"	value="移出" class="button09" /></div>
				<div id="rolesAuthorDiv">
				<table id="rolesAuthorGrid"></table>
				<div id="rolesAuthorMenu" />
				</div>
				<div id="authorKeyDialog">
				<ul id="authorTree"></ul>
				</div>
				</div>
				
				</div>
				
				
				
				
				
				
				
				
			
			
	</body>
</html>