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
			   	        id:"grid",
			   	     	header:false,
			   	        region:"center"
			   	    }],
			   	    fit:true,
			   	    spacing:2
	    }); 
			var grid = $('#grid').omGrid({
			    title : '用户列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    		'<a id="addButton"   href="javascript:void(0)"><img src="../../images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		            '<a id="updateButton" href="javascript:void(0)"><img src="../../images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="deleteButton" href="javascript:void(0)"><img src="../../images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		            '</div>',
		        dataSource : 'getPerson.do' ,
		        limit : 10,
		        autoFit : true,
		      //  height:'fit',
		        colModel : [{header :"员工工号", name : 'code', align : 'center'},
		                    {header :"姓名", name : 'name', align : 'center'},
		                    {header : "英文名", name : 'englishName', align : 'center'},
							{header : "登录名", name : 'loginName', align : 'center'}, 
							{header : "组织机构", name : 'organizationName', align : 'center'},
							{header : "部门", name : 'departmentName', align : 'center'},
							{header : "性别", name : 'sex', align : 'center',renderer:function(v){if(v=='M')return '男';else return '女';}},							
							{header : "是否启用", name : 'enable', align : 'center',renderer:function(v){if(v=='false')return '否';else return '是';}}
							],							
				onRowDblClick:function(rowIndex,rowData){
				  //  $('#updateButton').click();
				    isAdd = false;
			        $('#personForm').val("update");
                    showDialog("<fmt:message key='dialog.submit.modify'/>",rowData);//Display dialog
			
				}
	        });
	        
	       
            
	        var dialog = $('#dialog').omDialog({
	           width: 760,
	           autoOpen : false
	        });
	        loadCombo($("select[name='organizationv']")).change(
	           function(){
	               loadCombo($("select[name='departmentv']"),$(this).val());
	           }
	        );
	       
	          loadCombo($("select[name='cardType']"),null,true);
	          loadCombo($("select[name='sex']"),null,true);
	        
	        function loadCombo(selected,id,iscode) {
				var op = '<option ></option>';
				selected.html('').css({width:'140px'});
				$.ajax( {
					type : 'GET',
					url : 'comboData.do',
					async: false,  
					data : {'type':selected.attr('name'),'id':id} ,
					contentType : 'application/json',
					dataType : 'json',
					success : function(data) {
						$.each(data, function(i, item) {
						    if(iscode)value=item.code;
						    else value=item.id;
							op += '<option value=' + value + '>'
							+ item.name + '</option>';
						})
						selected.html(op);
					}
				});
				if(selected.html()=="")selected.html("<option value=''>"+selected.attr('name')+" no set</option>");
			    return selected ;
			};
			var showdialog=function(title,rowdata){
			    var data = rowdata || {} ;
		        $("input[name='id']",dialog).val(data.id);
			    $("input[name='code']",dialog).val(data.code); 
			    $("input[name='name']",dialog).val(data.name); 
			    $("input[name='englishName']",dialog).val(data.englishName); 
			    $("input[name='loginName']",dialog).val(data.loginName); 
	            $("select[name='organizationv']",dialog).val(data.organizationId);
                loadCombo($("select[name='departmentv']"),data.organizationId);
	            $("select[name='departmentv']",dialog).val(data.departmentId);
	            $("select[name='sex']",dialog).val(data.sex);
	            $("select[name='cardType']",dialog).val(data.cardType);
	            $("input[name='cardNumber']",dialog).val(data.cardNumber);  
	            $("input[name='phone']",dialog).val(data.phone);
	            $("input[name='email']",dialog).val(data.email);
	            $("textarea[name='address']",dialog).val(data.address);
	            $("textarea[name='remark']",dialog).val(data.remark);
	            $("input[name='enable']",dialog).val(data.enable=='false'?[false]:[true]);
	           /* if(data.password != null && $.trim(data.password) != ''){
	            	$("input[name='password']",dialog).val(data.password).attr('readonly','readonly');
	            	$("input[name='rePassword']",dialog).attr('readonly','readonly');
	            }else{
	                $("input[name='password']",dialog).removeAttr('readonly');
	                $("input[name='rePassword']",dialog).removeAttr('readonly');
	            }
	            */
	            dialog.omDialog("option", "title",title);
                dialog.omDialog("open");// show the dialog
	        };
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
	        $('#deleteButton').die().live("click",function(e){
	            var selectedRows = $('#grid').omGrid('getSelections',true);
	            if(selectedRows.length<=0){
	                $.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
	                return false;
	            };
	            $.omMessageBox.confirm( {
					title : '提示',
					content : '确定要删除选择行吗？',
					onClose : function(value) {
					    if(value){
						    $.ajax({
			                    url: "deletePerson.do",
			                    type: "post",
			                    data: selectedRows[0],
			                    success: function(result) {
			                        if(result=='true'){
			                            grid.omGrid('reload');
			                            window.parent.$.omMessageTip.show({title: '提示', content:'操作成功', timeout: 3500});
			                        }else{
			                            window.parent.$.omMessageBox.alert({title: '提示', content: '操作失败'});
			                        }
			                    },
			                    error: function(e) {
			                         window.parent.$.omMessageBox.alert({title: '提示', content: '操作失败'});
			                    }
			                });
		                }
					}
				});
	            
	        });
	        function validePersonExsits(data){
	              var istrue = false ;
	              $.ajax({
	                    url: "validePersonExsits.do",
	                    type: "GET",
	                    contentType: "text/html",
	                    data: data,
	                    async: false, 
	                    success: function(result) {
	                        if(result=='true'){
	                            istrue = true;
	                        }else{
	                           istrue = false;
	                        }
	                    },
	                    error: function(e) {
	                         istrue = false ;
	                    }
	                });
	             return  istrue ;
	        }
	        $('#saveButton').bind('click',function(e){
	            $('#personForm').omAjaxSubmit(
	              {
	                method : 'post',
	                url:'savePerson.do',
	                clearForm:true,
	                beforeSubmit : function(data,$form,options){
	                         
	                            if( $("select[name='organizationv']",$form).val()==''){
	                               window.parent.$.omMessageBox.alert({title: '提示', content: '请选择组织机构！'});
	                               return false;
	                            }
	                            if( $("select[name='departmentv']",$form).val()==''){
	                               window.parent.$.omMessageBox.alert({title: '提示', content: '请选择部门！'});
	                               return false;
	                            }
	                            if( $("input[name='name']",$form).val()==''){
	                               window.parent.$.omMessageBox.alert({title: '提示', content: '姓名不能为空！'});
	                               return false;
	                            }
	                            if( $("input[name='loginName']",$form).val()==''){
	                               window.parent.$.omMessageBox.alert({title: '提示', content: '登录名不能为空！'});
	                               return false;
	                            }
	                           var ifvalide = validePersonExsits({
	                                id:$("input[name='id']",$form).val(),
	                                name:$("input[name='name']",$form).val(),
	                                loginName:$("input[name='loginName']",$form).val()
	                            });
	                           if(!ifvalide){
	                                window.parent.$.omMessageBox.alert({title: '提示', content: '此登录名已经存在，请重输！'});
	                                return false;
	                            }
	                            var password =  $("input[name='password']",$form).val();
	                            var rePassword = $("input[name='rePassword']",$form).val();
	                            
	                            var id =  $("input[name='id']",$form).val();
	                            if(password == ''&& id == ''){
	                               window.parent.$.omMessageBox.alert({title: '提示', content: '密码不能为空！'});
	                               return false;
	                            }else{
	                               if($("input[name='id']",$form).val()==''){
	                                   if(password.length<6||password.length>16){
			                               window.parent.$.omMessageBox.alert({title: '提示', content: '密码长度应为6-16之间！'});
			                               return false;
			                           }
		                               if($("input[name='rePassword']",$form).val() != password){
		                                  window.parent.$.omMessageBox.alert({title: '提示', content: '重复密码与密码不一致！'});
		                                  return false ;
		                               }
	                               }
	                            }
	                            if(password != rePassword){
	                                window.parent.$.omMessageBox.alert({title: '提示', content: '两次密码输入不相同！'});
	                                return false;
	                            }
	                          }, 
		        	success : function(responseText) {
					          if(responseText=="true"){
					              dialog.omDialog("close");
					              grid.omGrid('reload');
								  window.parent.$.omMessageTip.show({title: '提示', content:'操作成功', timeout: 3500});
					          }else{
					        	  window.parent.$.omMessageBox.alert({title: '提示', content: '操作失败'});
					          }
					}
	              }
	            );
	        });
	       $('#closeButton').bind('click',function(){dialog.omDialog('close')});
	       $('#searchButton').bind('click',function(){
	            $('#grid').omGrid('setData',encodeURI('getPerson.do?loginName='+ $('#roleLoginName').val()+'&userName='+$('#roleName').val()));
	        })
      }); 
	 //]]>	
	</script>
	</head>
	<fmt:bundle basename="messages.messages_person" />
	<body style="margin: 0px;">
             <div id="panel">
		<table  border="0" cellspacing="0" cellpadding="0">
			<tr height="50" align="left">
				<td height="25" align="right">登录名:
				<input id="roleLoginName" />
				</td>
				<td height="25" align="left">
				用户名:<input id="roleName" />
				<input id="searchButton" type="button" class="button09"  value="查询"/></td>
			</tr>
		</table>
		</div>
		<div id="grid"><table id="grid"></table>
		</div>
		
		
		<div id="dialog">
		<form id="personForm" method="post">
		<table>
			<tr>
				<td align="center">组织机构:</td>
				<td><select name="organizationv">
				</select></td>
				<td align="center">部门:</td>
				<td><select name="departmentv" style="width: 150px">
				</select></td>
				<td align="center">姓名：</td>
				<td>
				<input name="id" style="display: none" /> <input name="name" />
				</td>
			</tr>
			<tr>
				<td>英文名：</td>
				<td><input name="englishName"></input></td>
				<td align="right">员工工号：</td>
				<td><input name="code"> </input></td>
				<td align="center">性别：</td>
				<td><select name="sex" style="width: 120px">
					<option value="M" label="1" />
					<option value="F" label="1" />
				</select></td>
			</tr>
			<tr>
				<td>证件类型:</td>
				<td><select name="cardType">
					<option label="1" value="1" />
					<option label="1" value="2" />
				</select></td>
				<td colspan="1" rowspan="1" align="center">证件号码：</td>
				<td><input name="cardNumber"> </input></td>
				<td align="center">电话：</td>
				<td colspan="1" rowspan="1"><input name="phone" width="120"> </input></td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="center">电子邮箱 :</td>
				<td colspan="1" rowspan="1"><input name="email"></input></td>
				<td colspan="1" rowspan="1" align="center">地址：</td>
				<td colspan="1" rowspan="1"><textarea name="address" width="120" > </textarea>
				</td>
				<td colspan="1" rowspan="1" align="center">登录名：</td>
				<td colspan="1" rowspan="1"><input name="loginName" width="120"> </input></td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="center">密码：</td>
				<td colspan="1" rowspan="1"><input name="password" type="password"> </input></td>
				<td colspan="1" rowspan="1" align="center">重复密码：</td>
				<td colspan="1" rowspan="1"><input name="rePassword" type="password"> </input></td>
				<td colspan="1" rowspan="1" align="center">是否启用：</td>
				<td colspan="3" rowspan="1"><input type="checkbox" name="enable" value="true"
					checked="checked" />
				</td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="center">备注：</td>
				<td colspan="3" rowspan="1" align="left"><textarea name="remark"
					style="width: 100%; height: 46px">
				</textarea>
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td colspan="4" rowspan="1" align="right"><input id="saveButton" type="button"  value="确定"
					class="button09" style="height: 25px" /></td>
				<td colspan="2" rowspan="1"><input id="closeButton" type="reset" class="button09"  value="取消"
					style="height: 25px"  /></td>
			</tr>
		</table>
		</form>
		</div>
	</body>
</html>