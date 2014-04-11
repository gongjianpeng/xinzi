<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
		       '<sec:authorize ifAnyGranted="ROLE_USERSET_FAIL2">'+  
		    		'<a id="SendEmailFail" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/file_obj.gif" title="发送失败邮件" />&nbsp;发送失败邮件&nbsp;</a>'+
		    		  '</sec:authorize>'+
		    		  	'<sec:authorize ifAnyGranted="ROLE_USERSET_SEND2">'+
		    		'<a id="SendEmailSuccess"   href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/file_obj.gif" title="发送激活邮件"/>&nbsp;发送激活邮件&nbsp;</a>'+
		    	
		    			'</sec:authorize>'+
		    		'<a id="addButton"   href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		            '<a id="updateButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="deleteButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		             '<a id="setTestDate" href="javascript:void(0)"><img src="../../images/calendar.gif" title="设置授权时间" /img>&nbsp;设置授权时间&nbsp;</a>'+
		
		            '</div>',
		        dataSource : 'getPerson.do' ,
		        limit : 10,
		        autoFit : true,
		        showIndex : false,
		       height:'fit',
		        colModel : [{header :"员工工号", name : 'code', align : 'center'},
		                    {header :"姓名", name : 'name', align : 'center'},
		                    {header : "英文名", name : 'englishName', align : 'center'},
							{header : "登录名", name : 'loginName', align : 'center'}, 
							{header : "组织机构", name : 'organizationName', align : 'center'},
							{header : "开始时间", name : 'BeginDate', align : 'center'},
							{header : "结束时间", name : 'EndDate', align : 'center'},
							{header : "性别", name : 'sex', align : 'center',renderer:function(v){if(v=='M')return '男';else return '女';}}	,					
								{header : "是否启用", name : 'enable', align : 'center',renderer:function(v){if(v=='false')return '否';else return '是';}},
						{header : "平台类型", name : 'remark', align : 'center'}
								],							
				onRowDblClick:function(rowIndex,rowData){
				  //  $('#updateButton').click();
				    isAdd = false;
			        $('#personForm').val("update");
                    showDialog("<fmt:message key='dialog.submit.modify'/>",rowData);//Display dialog
			
				}
				//,
				// onRefresh:function(){
                //	setMethod();
                //	if(selectedIndex){
                //		$('#grid').omGrid('setSelections',selectedIndex);
               // 	};
               // },
	        });
	        
	       
            
	        var dialog = $('#dialog').omDialog({
	           width: 760,
	           resizable : false,
	           autoOpen : false,
	           
              
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
	            
	            $("input[name='telphone']",dialog).val(data.telphone);
	            $("input[name='email']",dialog).val(data.email);
	            $("textarea[name='address']",dialog).val(data.address);
	            $("textarea[name='remark']",dialog).val(data.remark);
	            $("input[name='enable']",dialog).val(data.enable=='false'?[false]:[true]);
	           /***** if(data.password != null && $.trim(data.password) != ''){
	            	$("input[name='password']",dialog).val(data.password).attr('readonly','readonly');
	            	$("input[name='rePassword']",dialog).attr('readonly','readonly');
	            }else{
	                $("input[name='password']",dialog).removeAttr('readonly');
	                $("input[name='rePassword']",dialog).removeAttr('readonly');
	            }
	            ******/
	            
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
	          //function setMethod(){
		    	
			  	 $('#setTestDate').click(function(){
			  	 //	 if(validateSelected()==false) return false;
			  	 	 $('#setTestDateDialog').omDialog('open');
			  	 	 $('#setTestDateDialog').omDialog('option','title','设置授权时间');
			  	 });
			  	 
		  	 //}
		  	 
		  	  function validateSelected(){
		  	 	 var selectedRows=$('#grid').omGrid('getSelections',true);
		  	 	 if(selectedRows.length<=0){
		  	 	 	$.omMessageBox.alert({title:'提示',content:'请选择要操作的数据行！'});
		  	 	 	return false;
		  	 	 }else{
		  	 	 	return true;
		  	 	 }
		  	 	 
		  	 }
		  	 
	        	 //设置测试时间
		  	 $('#setTestDateDialog').omDialog({
		  	     autoOpen : false,
				 modal : true,
				 resizable : false,
				 buttons : {
					"<fmt:message key='submit'/>" : function() {
				
					    var beginDate = $('#setTestBeginDateInput').val();
					    var endDate = $('#setTestEndDateInput').val();
					    if(beginDate == '' || endDate == ''){
					    	$.omMessageBox.alert({title:'提示',content:'开始时间和结束时间均不允许为空！'});
					    	return false;
					    }
					    if(beginDate != '' && endDate != '' && beginDate > endDate){
					    	$.omMessageBox.alert({title:'提示',content:'开始时间不能大于结束时间！'});
					    	return false;
					    }
					    saveData('','',$('#setTestBeginDateInput').val(),$('#setTestEndDateInput').val(),'',$('#setTestDateDialog'));
					    
					},
					"<fmt:message key='reset'/>" : function() {
						$('#setTestDateDialog').omDialog("close");
					}
				 }
			 }) ;
			 
			 
			  var selectedIndex;
		    var  operation=$('#operation').val("update");
		    var update="update";
		  	 function saveData(executorId,auditorId,testBeginDate,testEndDate,status,dialog){
		  	    selectedIndex = $('#grid').omGrid('getSelections');
		  	 	var selectedRows=$('#grid').omGrid('getSelections',true);
		  	 	  var id=selectedRows[0].id;
		  	 	if(selectedRows.length>0){
			  	 	$.ajax({
			  	 	       url:'setpersonTimeData.do',
			  	 	       type:'post',
			  	 	       data:{
			  	 	         plans : JSON.stringify(selectedRows),
			  	 	         id:id,
			  	 	         auditorId:auditorId,
			  	 	         testBeginDate:testBeginDate,
			  	 	         testEndDate:testEndDate,
			  	 	         status : status,
			  	 	         operation:update
			  	 	         },
				  	 	  success : 
				  	 	   function(responseText){
				  	 		if(responseText=='true'){
				  	 		    parent.$.omMessageTip.show({title:'提示',content:'执行成功！',timeout:3500});
				  	 			$('#grid').omGrid('reload');
				  	 			if(dialog!=null) dialog.omDialog('close');
				  	 		}
				  	 		
				  	 		else if(responseText=='endDateLessThanBeginDate'){
				  	 			parent.$.omMessageBox.alert({title:'提示',content:'验证失败:测试开始时间不能大于测试结束时间！'});
				  	 		}
			  	 	      },
			  	 	      error:function(xreq,errorMessage,error){
			  	 	      	 parent.$.omMessageBox.alert({title:'提示',content:'执行失败:服务端程序执行出错！'});
			  	 	      }
			  	 	});
		  	 	}
		  	 }
			 
			 $('#setTestBeginDateInput').omCalendar({
			      date : new Date(),
		  	  	  dateFormat : "yy-mm-dd" 
		  	 });
		  	 $('#setTestEndDateInput').omCalendar({
			      date : new Date(),
		  	  	  dateFormat : "yy-mm-dd" 
		  	 });
	        
	          
	         $('#SendEmailSuccess').die().live("click",function(){
	            var selections= $('#grid').omGrid('getSelections',true);
	            if(selections.length>0){
	              $.omMessageBox.confirm({
                   title:'提示',
                   content:'确定要发送吗 ?',
                   onClose:function(value){
                        if(value==false)
                        	return false;
                        $('#operation').val("send");
		                var id=selections[0].id;
		                $.post('sendEmails.do',{operation:'send',id:id},function(){
		                    $('#grid').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "提示", content: "邮件发送成功", timeout: 1500});
		                });
                   	}
                 });
	               
                 // return false;
	             // 这里得到  这个人 的信息，根据信息去 发送给这个人的 email  激活
                	}else{
                  		$.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
             			return false;
            		}
            
             });
	         $('#SendEmailFail').die().live("click",function(){
	            var selections= $('#grid').omGrid('getSelections',true);
	            if(selections.length>0){
	              $.omMessageBox.confirm({
                   title:'提示',
                   content:'确定要发送吗 ?',
                   onClose:function(value){
                        if(value==false)
                        	return false;
                        $('#operation').val("send");
		                var id=selections[0].id;
		                $.post('sendEmailFail.do',{operation:'send',id:id},function(){
		                    $('#grid').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "提示", content: "邮件发送成功", timeout: 1500});
		                });
                   		}
                 	});
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
	                               window.parent.$.omMessageBox.alert({title: '提示', content: '请选择公司！'});
	                               return false;
	                            }
	                            if( $("select[name='departmentv']",$form).val()==''){
	                               window.parent.$.omMessageBox.alert({title: '提示', content: '请选择部门！'});
	                               return false;
	                            }
	                             var telphone = $("input[name='telphone']",$form).val();
	                            var telreg = /^1[3,5,8]\d{9}$/;
	                            if(telphone == ''){
	                            	window.parent.$.omMessageBox.alert({title: '提示', content: '手机不能为空！'});
	                            	return false;
	                            }else{
	                            	if(telphone.length != 11){
	                            		window.parent.$.omMessageBox.alert({title: '提示', content: '手机号码长度是11位！'});
	                            		return false;
	                            	}
	                            }
	                            var phone = $("input[name='phone']",$form).val();            					
	                            if( phone == ''){
	                            	window.parent.$.omMessageBox.alert({title: '提示', content: '电话不能为空！'});
	                            	return false;
	                            }
	                            var email = $("input[name='email']",$form).val();
	                            var reg = /^([a-zA-Z0-9_\.-]+)@(([a-zA-Z0-9_-]+)\.)+[a-zA-Z]{2,3}$/;
	                            if(email == ''){
	                            	window.parent.$.omMessageBox.alert({title: '提示', content: '电子邮箱不能为空！'});
	                            	return false;
	                            }else{
	                            	if( !reg.test(email) ) {
	                            		window.parent.$.omMessageBox.alert({title: '提示', content: '电子邮箱格式有误！'});
 										return false;
 									}
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
	       var LoginName=$('#roleLoginName').val().trim();
	            $('#grid').omGrid('setData','getPerson.do?loginName='+encodeURI(LoginName) );
	          
	        }
	        )
      }); 
	 //]]>	
	</script>
	</head>
	<fmt:bundle basename="messages.messages_person" />
	<body style="margin: 0px;">
             <div id="panel">
		<table  border="0" cellspacing="0" cellpadding="0">
			<tr height="50" align="left">
				<td height="25" >登录名:
				<input id="roleLoginName"  name="roleLoginName" />
				</td>
				<td height="25" align="left">
				
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
				<td align="center">公司:</td>
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
				<td><select name="cardType" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');">
					<option label="1" value="1" />
					<option label="1" value="2" />
				</select></td>
				<td colspan="1" rowspan="1" align="center">证件号码：</td>
				<td><input name="cardNumber" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"> </input></td>
				<td align="center">手机：</td>
				<td colspan="1" rowspan="1"><input name="telphone" width="120" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"> </input></td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="center">电子邮箱 :</td>
				<td colspan="1" rowspan="1"><input name="email"></input></td>
				<td colspan="1" rowspan="1" align="center">地          址：</td>
				<td colspan="1" rowspan="1"><textarea name="address" width="120" > </textarea>
				</td>
				<td align="center">电话：</td>
				<td colspan="1" rowspan="1"><input name="phone" width="120"  onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"> </input></td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="center">登录名：</td>
				<td colspan="1" rowspan="1"><input name="loginName" width="120"> </input></td>
				<td colspan="1" rowspan="1" align="center">密         码：</td>
				<td colspan="1" rowspan="1"><input name="password" type="password"> </input></td>
				<td colspan="1" rowspan="1" align="center">重复密码：</td>
				<td colspan="1" rowspan="1"><input name="rePassword" type="password"> </input></td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="center"></td>
				<td colspan="3" rowspan="1" align="left">
				</td>
				<td colspan="1" rowspan="1" align="center">是否启用：</td>
				<td colspan="3" rowspan="1"><input type="checkbox" name="enable" value="true"
					checked="checked" />
				</td>
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
		 <div id="setTestDateDialog" align="center" >
	   <div style="padding:2px"> 使用开始时间：<input id="setTestBeginDateInput" name="setTestBeginDateInput" type="text" style="width:120px"/></div>
	   <div style="padding:2px"> 使用结束时间：<input id="setTestEndDateInput" name="setTestEndDateInput" type="text" style="width:120px"/></div>
	</div>
		
	</body>
</html>