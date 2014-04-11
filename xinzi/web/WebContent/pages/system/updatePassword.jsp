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
		<style>
html,body {
	font-size: 14px;
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
	background-image: url(../../images/icons/search.gif) !important;
}

input[type='text'] {
	margin: 0px;
	padding: 0px;
	height: 18px;
	width: 140px
}
</style>
<script>
			//<![CDATA[
		
		$(document).ready(function() {
          
			
	       
	        $('#addButton').die().live("click",function(){
	           showdialog('新增',$(this).val());
	        });
	        $('#updateButton').die().live("click",function(){
	            var selectRows= $('#grid').omGrid('getSelections',true);
	            if(selectRows.length>0){
	              // alert("<fmt:message key='dialog.submit.modify.confirm'/>");
                 // return false;
	              showdialog('修改',selectRows[0]);
                }else{
                  $.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
             return false;
            }
                    
                  
	        });
	      
	       
	        $('#saveButton').bind('click',function(e){
	            $('#upapassForm').omAjaxSubmit(
	              {
	                method : 'post',
	                url:'updatePasswordOperate.do',
	                clearForm:true,
	                beforeSubmit : function(data,$form,options){
	                         
	                            var oldPassword =  $("input[name='oldPassword']",$form).val();
	                            var newPassword = $("input[name='newPassword']",$form).val();
	                              var repeatNewPassword = $("input[name='repeatNewPassword']",$form).val();
	                            var id =  $("input[name='id']",$form).val();
	                           
	                          }, 
		        	success : function(responseText) {
					          if(responseText=="true"){
								  window.parent.$.omMessageTip.show({title: '提示', content:'操作成功', timeout: 3500});
					          }else{
					        	  window.parent.$.omMessageBox.alert({title: '提示', content: '操作失败'});
					          }
					}
	              }
	            );
	        });
	      
      }); 
	 //]]>	
	</script>
	</head>
	<fmt:bundle basename="messages.messages_person" />
	<body style="margin: 0px;">
		<form id="upapassForm" action="updatePasswordOperate.do" method="post"><input name="operation"
	id="operation" style="display: none" /> <input name="id" style="display: none">
		<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FBF9F8">
			<tr>
				<td align="right" width="20%" class="td_borser" >旧密码:</td>
				<td  align="left" width="20%" class="td_borser" ><input   name="oldPassword"  type="password"  width="200" id="oldPassword"/></td>
				</tr><tr>
				<td  align="right" width="20%" class="td_borser" >新密码:</td>
				<td  align="left" width="20%" class="td_borser" ><input id="newPassword"  name="newPassword"   type="password" /></td>
					</tr><tr>
				<td  align="right" width="20%" class="td_borser" >确认新密码：</td>
				<td  align="left" width="20%" class="td_borser" ><input id="repeatNewPassword"  name="repeatNewPassword"   type="password" /></td>
			</tr>
			
			<tr>
				<td colspan="1" rowspan="1"  align="right"><input id="saveButton" type="submit"  value="确定2"
					/></td>
				<td colspan="1" rowspan="1"  align="left"><input id="closeButton" type="reset"  value="重置2"
					style="height: 25px"  /></td>
			</tr>
		</table>
		</form>
		
		</div>
	</body>
</html>