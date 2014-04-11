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
			    title : 'men列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    		'<a id="addButton"   href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		            '<a id="updateButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="deleteButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		            '</div>',
		        dataSource : 'getDoordetail.do' ,
                    limit : 10,
                    autoFit : true ,
                  //  height:'fit',
                    colModel : [{header : "id" , name : 'ID' , align : 'center' },
                                {header : "商品编号" , name : 'number' , align : 'center' },
                                {header : "规格" , name : 'standard' , align : 'center' },
                                          {header : "颜色" , name : 'colour' , align : 'center' },
                                          {header : "品牌" , name : 'brand' , align : 'center' },
                                          {header : "数量" , name : 'amount' , align : 'center' },
                                          {header :"类型" , name : 'type' , align : 'center' },
                                          {header : "尺寸" , name : 'size' , align : 'center' },
                                          {header : "商品图片" , name : 'photo' , align : 'center' },
                                          {header : "开向" , name : 'opento' , align : 'center' },
                                          {header : "门体高" , name : 'menheight;' , align : 'center' },
                                          {header : "墙体厚度" , name : 'wallply' , align : 'center' },
                                          {header : "樘数" , name : 'tcount' , align : 'center' },
       
                                          {header : "panelname" , name : 'panelname' , align : 'center' },
                                           {header : "framename" , name : 'framename' , align : 'center' }
                                         
                                          ],                                        
                        onRowDblClick: function (rowIndex,rowData){
                            $('#updateButton').click();
                            isAdd = false ;
                         // $( '#personForm' ).val("update" );
                    showdialog( "<fmt:message key='dialog.submit.modify'/>",rowData); //Display dialog
                  
                        }
              });
	      
	        
	            var dialog = $("#dialog-form").omDialog({
            	width: 960,
	           height:500,
	           resizable : true,
	           autoOpen : false,
                resizable : false,
                buttons : {
                    "确定" : function(){
		                submitDialog();
		                return false; //Block form's default submit action
		            },
                    "取消" : function() {
                        $("#dialog-form").omDialog("close");//Close dialog
                    }
                }
            });
	      
	       
	         
			var showdialog=function(title,rowdata){
			    var data = rowdata || {} ;
		        $("input[name='id']",dialog).val(data.id);
		       
			    $("input[name='number']",dialog).val(data.number); 
			    $("input[name='standard']",dialog).val(data.standard); 
			      $("input[name='colour']",dialog).val(data.colour);
			    $("input[name='brand']",dialog).val(data.brand); 
			    $("input[name='amount']",dialog).val(data.amount); 
	            $("input[name='type']",dialog).val(data.type);
	            $("input[name='size']",dialog).val(data.size);
	            $("input[name='photo']",dialog).val(data.photo);
	            $("input[name='opento']",dialog).val(data.opento);
	            $("input[name='menheight']",dialog).val(data.menheight);  
	            $("input[name='wallply']",dialog).val(data.wallply);
	            $("input[name='tcount']",dialog).val(data.tcount);
	            $("input[name='enable']",dialog).val(data.enable=='false'?[false]:[true]);
	           
	            
	            dialog.omDialog("option", "title",title);
                dialog.omDialog("open");// show the dialog
	        };
	         function showResponse(data){
            	if(data!=null&&data=="true"){
				 	$("#dialog-form").omDialog("close");  
			 		$('#operation').val("");
				 	$('#jibenqiyeTable').omGrid('setData','getDoordetail.do');
				 	$.omMessageTip.show({title: "提示", content: "操作成功", timeout: 1500});
			 	} else {
				 	$.omMessageTip.show({title: "提示", content: "操作失败", timeout: 1500});
			 	}
			}
            var options = {
				success : showResponse
			  };
	       	var submitDialog = function(){
	       		if(1==1){
	       			$("#ipForm").omAjaxSubmit(options);
	       		}
	       		return false;
	      	};
	        
	         var isAdd = true;
	        $('#addButton').die().live("click",function(){
	          isAdd = true;
	          $('#operation').val("add");
	           showdialog('新增',$(this).val());
	        });
	        
	        $('#updateButton').die().live("click",function(){
	            var selections= $('#grid').omGrid('getSelections',true);
	           if (selections.length == 0) {
                  $.omMessageBox.alert({  content:'<fmt:message key='dialog.submit.modify.confirm'/>' });           
                     return false;
                }
                isAdd = false;
                $('#operation').val("update");
                showdialog("修改",selections[0]);//Display dialog
            });
            
	 $('#deleteButton').die().live('click', function(e) {
                var selections=$('#grid').omGrid('getSelections',true);
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
		                $.post('cdityOperate.do',{operation:'delete',id:toDeleteRecordID},function(){
		                    $('#grid').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "<fmt:message key='dialog.submit.success'/>", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
         
	         
	        
	        //*****  begin******method   001 ***********************//
	              var timer = '_select1_timer' ;
            var input = $('#select1' ).attr( 'readonly', 'readOnly' )
                                               .focus(function () {
                                                   dropList.show();
                                               })
                                               .blur(function () {
                                                   window[timer] = setTimeout(function () {
                                                      dropList.hide();
                                                   }, 450);
                                               });
             var inputOffset=input.offset();
             var dropList = input.next().css({top:inputOffset.top+input.outerHeight(),left:inputOffset.left})
                                        .mousedown( function (e) {
                                            e.stopPropagation();
                                            setTimeout(function () {
                                                clearTimeout(window[timer]);
                                            }, 25);
                                        });
             $(document.body).mousedown( function () {
                dropList.hide();
             });                                       
   
            $( '#target-table' ).omGrid({
                dataSource : 'getDataPanels.do' ,
                method : 'POST' ,
                width : 570,
                height : 200,
                showIndex : true ,
                autoFit : true ,
                colModel : [ 
                              {header : 'panelID' , name : 'id' , width : '80' },
                                          {header : '板材' , name : 'name' , width : '180' },
                                        {header : 'contents' , name : 'contents' },
                                        {header : 'sname' , name : 'sname' },
                             {header : '地址' , name : 'org' , align : 'left' , width : 200, autoExpandMin : 100, sortable : true} ],
                onRowClick : function (event, rowData) {
                 
                    input.val(rowData.id).attr( '_trueValue' , rowData.id);
                   
                          $( "#pan_ID" ).val(rowData.id);
                          $( "#panelname" ).val(rowData.name);
                          $( "#pancontents" ).val(rowData.contents);
                          $( "#pansname" ).val(rowData.sname);
                    
                          $( "#pantype" ).val(rowData.type);
                          $( "#panfield" ).val(rowData.field); 
                          $( "#panfield2" ).val(rowData.field2);
                          $( "#pancrtime" ).val(rowData.crtime);
                          $( "#panorg" ).val(rowData.org);
                          $( "#pancode" ).val(rowData.code);
                          $( "#panremark" ).val(rowData.remark);
                          
                    dropList.hide();
                }
            });
            input.next().hide();
              

	        
	       //*****  end************method   001**************//
	       
	       //*****  begin******method   002 *****  Doorstyle****************//
	              var timer = '_select2_timer' ;
            var input = $('#select2' ).attr( 'readonly', 'readOnly' )
                                               .focus(function () {
                                                   dropList2.show();
                                               })
                                               .blur(function () {
                                                   window[timer] = setTimeout(function () {
                                                      dropList2.hide();
                                                   }, 450);
                                               });
             var inputOffset=input.offset();
             var dropList2 = input.next().css({top:inputOffset.top+input.outerHeight(),left:inputOffset.left})
                                        .mousedown( function (e) {
                                            e.stopPropagation();
                                            setTimeout(function () {
                                                clearTimeout(window[timer]);
                                            }, 25);
                                        });
             $(document.body).mousedown( function () {
                dropList2.hide();
             });                                       
   
            $( '#target-table2' ).omGrid({
                dataSource : 'getDataDoorstyles.do' ,
                method : 'POST' ,
                width : 570,
                height : 200,
                showIndex : true ,
                autoFit : true ,
                colModel : [ 
                              {header : 'doo_ID' , name : 'id' , width : '80' },
                                          {header : '门面样式' , name : 'name' , width : '180' },
                                        {header : 'contents' , name : 'contents' },
                                        {header : 'sname' , name : 'sname' },
                             {header : '地址' , name : 'org' , align : 'left' , width : 200, autoExpandMin : 100, sortable : true} ],
                onRowClick : function (event, rowData) {
                $( "#doorstyleid" ).val(rowData.id); 
                    input.val(rowData.id).attr( '_trueValue' , rowData.id);
                  
	
                          $( "#doo_ID" ).val(rowData.id); 
                          $( "#doorstylename" ).val(rowData.name);
                          $( "#doorstylecontents" ).val(rowData.contents);
                          $( "#doorstylesname" ).val(rowData.sname);
                          $( "#doorstyletype" ).val(rowData.type);
                          $( "#doorstylefield" ).val(rowData.field);
                          $( "#doorstylefield2" ).val(rowData.field2);
                          $( "#doorstylecrtime" ).val(rowData.crtime);
                          $( "#doorstyleorg" ).val(rowData.org);
                          $( "#doorstylecode" ).val(rowData.code);
                          $( "#doorstyleremark" ).val(rowData.remark);
                          
                    
                    dropList2.hide();
                }
            });
            input.next().hide();
              

	        
	       //*****  end************method   002**************//
	       
	        //*****  begin******method   003 *****  Doorstyle****************//
	              var timer = '_select3_timer' ;
            var input = $('#select3' ).attr( 'readonly', 'readOnly' )
                                               .focus(function () {
                                                   dropList3.show();
                                               })
                                               .blur(function () {
                                                   window[timer] = setTimeout(function () {
                                                      dropList3.hide();
                                                   }, 450);
                                               });
             var inputOffset=input.offset();
             var dropList3 = input.next().css({top:inputOffset.top+input.outerHeight(),left:inputOffset.left})
                                        .mousedown( function (e) {
                                            e.stopPropagation();
                                            setTimeout(function () {
                                                clearTimeout(window[timer]);
                                            }, 25);
                                        });
             $(document.body).mousedown( function () {
                dropList3.hide();
             });                                       
   
            $( '#target-table3' ).omGrid({
                dataSource : 'getDataFrames.do' ,
                method : 'POST' ,
                width : 470,
                height : 200,
                showIndex : true ,
                autoFit : true ,
                colModel : [ 
                              {header : 'fra_ID' , name : 'id' , width : '80' },
                                          {header : '门边框样式' , name : 'name' , width : '180' },
                                        {header : 'contents' , name : 'contents' },
                                        {header : 'sname' , name : 'sname' },
                                        {header : 'field' , name : 'field' },
                                        {header : 'field2' , name : 'field2' },
                             {header : '地址' , name : 'org' , align : 'left' , width : 200, autoExpandMin : 100, sortable : true} ],
                onRowClick : function (event, rowData) {
                    input.val(rowData.id).attr( '_trueValue' , rowData.id);
                         
                          $( "#fra_ID" ).val(rowData.id);
                          $( "#framename" ).val(rowData.name);
                          $( "#framecontents" ).val(rowData.contents);
                          $( "#framesname" ).val(rowData.sname);
                          $( "#frametype" ).val(rowData.type);
                            
                          $( "#framefield" ).val(rowData.field);
                          $( "#framefield2" ).val(rowData.field2);
                           $( "#framecrtime" ).val(rowData.crtime);
                          $( "#frameorg" ).val(rowData.org);
                          $( "#framecode" ).val(rowData.code);
                          $( "#frameremark" ).val(rowData.remark);
                    
                    dropList3.hide();
                }
            });
            input.next().hide();
              

	        
	       //*****  end************method   003**************//
	       
	        //*****  begin******method   004 *****  Doorstyle****************//
	              var timer = '_select4_timer' ;
            var input = $('#select4' ).attr( 'readonly', 'readOnly' )
                                               .focus(function () {
                                                   dropList4.show();
                                               })
                                               .blur(function () {
                                                   window[timer] = setTimeout(function () {
                                                      dropList4.hide();
                                                   }, 450);
                                               });
             var inputOffset=input.offset();
             var dropList4 = input.next().css({top:inputOffset.top+input.outerHeight(),left:inputOffset.left})
                                        .mousedown( function (e) {
                                            e.stopPropagation();
                                            setTimeout(function () {
                                                clearTimeout(window[timer]);
                                            }, 25);
                                        });
             $(document.body).mousedown( function () {
                dropList3.hide();
             });                                       
      
            $( '#target-table4' ).omGrid({
                dataSource : 'getDataPalettes.do' ,
                method : 'POST' ,
                width : 470,
                height : 200,
                showIndex : true ,
                autoFit : true ,
                colModel : [ 
                              {header : 'pal_ID' , name : 'id' , width : '80' },
                                        {header : '门色板样式' , name : 'name' , width : '180' },
                                        {header : 'contents' , name : 'contents' },
                                        {header : 'sname' , name : 'sname' },
                             {header : '地址' , name : 'org' , align : 'left' , width : 200, autoExpandMin : 100, sortable : true} ],
                onRowClick : function (event, rowData) {
                    input.val(rowData.id).attr( '_trueValue' , rowData.id);
                         	
	
	
                          $( "#pal_ID" ).val(rowData.id);
                          $( "#palettename" ).val(rowData.name);
                          $( "#palettecontents" ).val(rowData.contents);
                          $( "#palettesname" ).val( rowData.sname);
                           $( "#palettetype" ).val(rowData.type);
                          $( "#palettefield" ).val(rowData.field);
                          $( "#palettefield2" ).val(rowData.field2);
                           $( "#palettecrtime" ).val(rowData.crtime);
                          $( "#paletteorg" ).val(rowData.org);
                          $( "#palettecode" ).val(rowData.code);
                          $( "#paletteremark" ).val(rowData.remark);
                    
                    dropList4.hide();
                }
            });
            input.next().hide();
              

	        
	       //*****  end************method   004**************//
	       
	       //*****  begin******method   005 *****  Doorstyle****************//
	              var timer = '_select5_timer' ;
            var input = $('#select5' ).attr( 'readonly', 'readOnly' )
                                               .focus(function () {
                                                   dropList5.show();
                                               })
                                               .blur(function () {
                                                   window[timer] = setTimeout(function () {
                                                      dropList4.hide();
                                                   }, 450);
                                               });
             var inputOffset=input.offset();
             var dropList5 = input.next().css({top:inputOffset.top+input.outerHeight(),left:inputOffset.left})
                                        .mousedown( function (e) {
                                            e.stopPropagation();
                                            setTimeout(function () {
                                                clearTimeout(window[timer]);
                                            }, 25);
                                        });
             $(document.body).mousedown( function () {
                dropList5.hide();
             });                                       
   
            $( '#target-table5' ).omGrid({
                dataSource : 'getDataScuttles.do' ,
                method : 'POST' ,
                width : 470,
                height : 200,
                showIndex : true ,
                autoFit : true ,
                colModel : [ 
                              {header : 'scu_ID' , name : 'id' , width : '80' },
                                          {header : '门气窗样式' , name : 'name' , width : '180' },
                                        {header : 'contents' , name : 'contents' },
                                        {header : 'sname' , name : 'sname' },
                             {header : '地址' , name : 'org' , align : 'left' , width : 200, autoExpandMin : 100, sortable : true} ],
                onRowClick : function (event, rowData) {
                    input.val(rowData.id).attr( '_trueValue' , rowData.id);
                         
                         $( "#scu_ID" ).val(rowData.id);
                              $( "#scuttlename" ).val(rowData.name);
                          $( "#scuttlecontents" ).val(rowData.contents);
                          $( "#scuttlesname" ).val(rowData.sname);
                           $( "#scuttletype" ).val(rowData.type);
                          $( "#scuttlefield" ).val(rowData.field);
                          $( "#scuttlefield2" ).val(rowData.field2);
                           $( "#scuttlecrtime" ).val(rowData.crtime);
                          $( "#scuttleorg" ).val(rowData.org);
                          $( "#scuttlecode" ).val(rowData.code);
                          $( "#scuttlesemark" ).val(rowData.remark);
                    dropList5.hide();
                }
            });
            input.next().hide();
              

	        
	       //*****  end************method   005**************//
	       
	       //*****  begin******method   006 *****  Doorstyle****************//
	              var timer = '_select6_timer' ;
            var input = $('#select6' ).attr( 'readonly', 'readOnly' )
                                               .focus(function () {
                                                   dropList6.show();
                                               })
                                               .blur(function () {
                                                   window[timer] = setTimeout(function () {
                                                      dropList6.hide();
                                                   }, 450);
                                               });
             var inputOffset=input.offset();
             var dropList6 = input.next().css({top:inputOffset.top+input.outerHeight(),left:inputOffset.left})
                                        .mousedown( function (e) {
                                            e.stopPropagation();
                                            setTimeout(function () {
                                                clearTimeout(window[timer]);
                                            }, 25);
                                        });
             $(document.body).mousedown( function () {
                dropList6.hide();
             });                                       
   
            $( '#target-table6' ).omGrid({
                dataSource : 'getDataAccesss.do' ,
                method : 'POST' ,
                width : 470,
                height : 200,
                showIndex : true ,
                autoFit : true ,
                colModel : [ 
                              {header : 'acc_ID' , name : 'id' , width : '80' },
                                          {header : '门其他样式' , name : 'name' , width : '180' },
                                        {header : 'contents' , name : 'contents' },
                                        {header : 'sname' , name : 'sname' },
                             {header : '地址' , name : 'org' , align : 'left' , width : 200, autoExpandMin : 100, sortable : true} ],
                onRowClick : function (event, rowData) {
                    input.val(rowData.id).attr( '_trueValue' , rowData.id);
                  $( "#acc_ID" ).val(rowData.id);
   
                         $( "#accessoriesname" ).val(rowData.name);
                          $( "#accessoriescontents" ).val(rowData.contents);
                          $( "#accessoriessname" ).val(rowData.sname);
                           $( "#accessoriestype" ).val(rowData.type);
                          $( "#accessoriesfield" ).val(rowData.field);
                          $( "#accessoriesfield2" ).val(rowData.field2);
                           $( "#accessoriescrtime" ).val(rowData.crtime);
                          $( "#accessoriesorg" ).val(rowData.org);
                          $( "#accessoriescode" ).val(rowData.code);
                          $( "#accessoriesremark" ).val(rowData.remark);
                    
                    dropList6.hide();
                }
            });
            input.next().hide();
              

	        
	       //*****  end************method   006**************//
	      
	         //*****  begin******method   007 *****  Doorstyle****************//
	              var timer = '_select7_timer' ;
            var input = $('#select7' ).attr( 'readonly', 'readOnly' )
                                               .focus(function () {
                                                   dropList7.show();
                                               })
                                               .blur(function () {
                                                   window[timer] = setTimeout(function () {
                                                      dropList7.hide();
                                                   }, 450);
                                               });
             var inputOffset=input.offset();
             var dropList7 = input.next().css({top:inputOffset.top+input.outerHeight(),left:inputOffset.left})
                                        .mousedown( function (e) {
                                            e.stopPropagation();
                                            setTimeout(function () {
                                                clearTimeout(window[timer]);
                                            }, 25);
                                        });
             $(document.body).mousedown( function () {
                dropList7.hide();
             });                                       
   
   
   
            $( '#target-table7' ).omGrid({
                dataSource : '' ,
                method : 'POST' ,
                width : 470,
                height : 200,
                showIndex : true ,
                autoFit : true ,
                colModel : [ 
                              {header : 'acc_ID' , name : 'id' , width : '80' },
                                          {header : '门其他样式' , name : 'name' , width : '180' },
                                        {header : 'contents' , name : 'contents' },
                                        {header : 'sname' , name : 'sname' },
                             {header : '地址' , name : 'org' , align : 'left' , width : 200, autoExpandMin : 100, sortable : true} ],
                onRowClick : function (event, rowData) {
                    input.val(rowData.id).attr( '_trueValue' , rowData.id);
                  $( "#else_ID" ).val(rowData.id);
   
                         $( "#elsesname" ).val(rowData.name);
                          $( "#elsescontents" ).val(rowData.contents);
                          $( "#elsessname" ).val(rowData.sname);
                           $( "#elsestype" ).val(rowData.type);
                          $( "#elsesfield" ).val(rowData.field);
                          $( "#elsesfield2" ).val(rowData.field2);
                           $( "#elsescrtime" ).val(rowData.crtime);
                          $( "#elsesorg" ).val(rowData.org);
                          $( "#elsescode" ).val(rowData.code);
                          $( "#elsesremark" ).val(rowData.remark);
                    
                    dropList7.hide();
                }
            });
            input.next().hide();
              });

	        
	       //*****  end************method   007*************//
	      
	        
	                /*******前端测试查询 begin****/
         $('#query').die().live("click",function(e){
         //$('#searchButton').die().live("click",function(){
        //  $('#query').bind('click', function(e) {
         
                    var qbrand=$('#brand').val();
                    var qnumber=$('#number').val();
                    var qdoorstyle=$('#doorstyle').val();
                    var qsize=$('#size').val();
                    var qmenheight=$('#menheight').val();
                    var qwallply=$('#wallply').val();
                 
                 $('#grid').omGrid("setData", 'operateDataDoor.do?qbrand='+encodeURI(qbrand)
                 +'&qnumber='+encodeURI(qnumber)
                 +'&qsize='+encodeURI(qsize)
                 +'&qdoorstyle='+encodeURI(qdoorstyle)
                 +'&qmenheight='+encodeURI(qmenheight)
                 +'&qwallply='+encodeURI(qwallply));
       //$('#dataDictionaryTable').omGrid("setData", 'operateDataDictionary.do?qName='+encodeURI(qName)+'&qType='+encodeURI(qType));
            
              
            
                 
             });
	      
	              /***********/
	   
	      
      }); 
	 //]]>	
	</script>
	
<fmt:bundle basename="messages.messages_quote" />
<body style="overflow: hidden">
	<div id="panel">
	<form id="queryForm">
		<table  class="table1" style="float: left">
			<tr class="tr1"> 
				<td class="td1">
				品牌:<input id="brand" name="brand" class="input1"/>
				商品编号:<input id="number" name="number" class="input1"/>
				门型:<input id="doorstyle" name="doorstyle" class="input1"/>
				尺寸:<input id="size" name="size" class="input1"/>
				
			</tr>
			<tr class="tr1">
				<td class="td1">
				樘数:<input id="tcount" name="tcount" class="input1"/>
				门体高:<input id="menheight" name="menheight" class="input1"/>
				墙体厚度:<input id="wallply" name="wallply" class="input1"/>
			</td>
			</tr>
				<input id="query" type="button" class="button09"  value="查询"/>
		</table>
	</form>
	</div>
	
	<div id="grid"><table id="grid"></table>
	</div>
		
	<div id="dialog-form">
	<form id="ipForm" action="cdityOperate.do" method="post"><input name="operation" id="operation" style="display: none" /> <input name="id" style="display: none">
		<table>
		
		
		
		
		<tr><td><span>
        <input id="select1" value="点击文本框获得1" style="width:50px"/> <!-- 如果display设置为none，则grid无法得到正确的高度 TODO -->
        <div class="omcombo-ct" style="position: absolute; display: block; left: 0;">
            <table id="target-table"></table>
        </div></span> <input type="button" onclick="$('#result').html($('#select1').attr('_trueValue'));" value="panel设置"/>
       </td>
       
       <td><span>
        <input id="select2" value="点击文本框获得2" style="width:50px" /> <!-- 如果display设置为none，则grid无法得到正确的高度 TODO -->
        <div class="omcombo-ct" style="position: absolute; display: block; left: 0;">
            <table id="target-table2"></table>
        </div></span> <input type="button" onclick="$('#result').html($('#select2').attr('_trueValue'));" value="门Doorstyle设置"/>
       </td>
       
        <td><span>
        <input id="select3" name="select3" value="点击文本框获得3" style="width:50px" /> <!-- 如果display设置为none，则grid无法得到正确的高度 TODO -->
        <div class="omcombo-ct" style="position: absolute; display: block; left: 0;">
            <table id="target-table3"></table>
        </div></span> <input type="button" onclick="$('#result').html($('#select3').attr('_trueValue'));" value="门Frames设置"/>
       </td>
        </tr>
        <tr>
        <td><span>
        <input id="select4" value="点击文本框获得4"  style="width:50px"/> <!-- 如果display设置为none，则grid无法得到正确的高度 TODO -->
        <div class="omcombo-ct" style="position: absolute; display: block; left: 0;">
            <table id="target-table4"></table>
        </div></span> <input type="button" onclick="$('#result').html($('#select4').attr('_trueValue'));" value="门Palettes设置"/>
       </td>
       
         <td><span>
        <input id="select5" value="点击文本框获得5"  style="width:50px"/> <!-- 如果display设置为none，则grid无法得到正确的高度 TODO -->
        <div class="omcombo-ct" style="position: absolute; display: block; left: 0;">
            <table id="target-table5"></table>
        </div></span> <input type="button" onclick="$('#result').html($('#select5').attr('_trueValue'));" value="Scuttles设置"/>
       </td>
       
        <td><span>
        <input id="select6" value="点击文本框获得6"  style="width:50px"/> <!-- 如果display设置为none，则grid无法得到正确的高度 TODO -->
        <div class="omcombo-ct" style="position: absolute; display: block; left: 0;">
            <table id="target-table6"></table>
        </div></span> <input type="button" onclick="$('#result').html($('#select6').attr('_trueValue'));" value="门Accesss设置"/>
       </td>
       
       <td><span>
        <input id="select7"   value="点击文本框获得7"  style="width:50px"  /> <!-- 如果display设置为none，则grid无法得到正确的高度 TODO -->
        <div class="omcombo-ct" style="position: absolute; display: block; left: 0;">
              <table id="target-table7"></table>
        </div></span> <input type="button" onclick="$('#result').html($('#select7').attr('_trueValue'));"  value="门else设置"  />
       </td>
       
       </tr>
       
       
			<tr>
			      <td>商品编号:</td>
				<td><input id="number" name="number" width="120"> </input></td>
				<td>规格:</td>
				<td><input id="standard" name="standard"></input></td>
				<td>颜色:</td>
				<td><input id="colour" name="colour"></input></td>
			</tr> 
			<tr>
			      <td>品牌:</td>
				<td><input id="brand" name="brand" width="120"> </input></td>
				<td>数量:</td>
				<td><input id="amount" name="amount"></input></td>
				<td>类型:</td>
				<td><input id="type" name="type"></input></td>
			</tr> 
			
			<tr>
			      <td>尺寸:</td>
				<td><input id="size" name="size" width="120"> </input></td>
				<td>开向:</td>
				<td><input id="opento" name="opento"></input></td>
				<td>门体高:</td>
				<td><input id="menheight" name="menheight"></input></td>
			</tr> 
			
		 	<tr>
			      <td>墙体厚度:</td>
				<td><input id="wallply" name="wallply" width="120"> </input></td>
				<td>樘数:</td>
				<td><input id="tcount" name="tcount"></input></td>
			</tr> 
		
		
			
			<tr>
				
					<td> 
						<input id="controlDocument" name="controlDocument" style="display:none">
						<input id="controlDescription" name="controlDescription" style="display:none">
					<div id="controlActiveCodeDiv" style="display:none">
						<input id="controlActiveCode" name="controlActiveCode" style="display:none"> <!--If the display is set to none, the grid cannot be the correct height TODO -->
						<div id="caTableDiv" class="omcombo-ct" style="position: absolute; display: none; left: 30;">
						<table id="controlActive-table"></table>
						</div>
					</div>
				</td>
				
				<tr> 
					<td>pan_ID</td><td><input id="pan_ID" name="pan_ID" readonly="readonly"  style="width:65px;"/></td>
					<td>panelname</td><td><input id="panelname" name="panelname" readonly="readonly"  style="width:65px;"/></td>
					<td>pancontents</td><td><input id="pancontents" name="pancontents" readonly="readonly"  style="width:65px;"/></td>
					<td>pansname</td><td><input id="pansname" name="pansname" readonly="readonly"  style="width:65px;"/></td>
					<td><input id="pantype" name="pantype" readonly="readonly"  style="display:none;width:65px;"/></td>
					
					<td><input id="panfield" name="panfield"  style="display:none;"/></td>
					<td><input id="panfield2" name="panfield2"  style="display:none;"/></td>
					<td><input id="pancrtime" name="pancrtime"  style="display:none;"/></td>
					<td><input id="panorg" name="panorg"  style="display:none;"/></td>
					<td><input id="pancode" name="pancode"  style="display:none;"/></td>
				    <td><input id="panremark" name="panremark"  style="display:none;"/></td>
				</tr>
				
					<tr> 
					<td><input id="doo_ID " name="doo_ID" readonly="readonly"  style="width:65px;"/></td>
					<td><input id="doorstylename" name="doorstylename" readonly="readonly"  style="width:65px;"/></td>
					<td><input id="doorstylecontents" name="doorstylecontents" readonly="readonly"  style="width:65px;"/></td>
					<td><input id="doorstylesname" name="doorstylesname" readonly="readonly"  style="width:65px;"/></td>
					<td><input id="doorstyletype" name="doorstyletype" readonly="readonly"  style="display:none;width:65px;"/></td>
					
					<td><input id="doorstylefield" name="doorstylefield"  style="display:none;"/></td>
					<td><input id="doorstylefield2" name="doorstylefield2"  style="display:none;"/></td>
					<td><input id="doorstylecrtime" name="doorstylecrtime"  style="display:none;"/></td>
					<td><input id="doorstyleorg" name="doorstyleorg"  style="display:none;"/></td>
					<td><input id="doorstylecode" name="doorstylecode"  style="display:none;"/></td>
				    <td><input id="doorstyleremark" name="doorstyleremark"  style="display:none;"/></td>
					
				
				</tr>
				
				<tr> 
					<td>fra_ID</td><td><input id="fra_ID" name="fra_ID" readonly="readonly"  style="width:65px;"/></td>
					<td>framename</td><td><input id="framename" name="framename" readonly="readonly"  style="width:65px;"/></td>
					<td>framecontents</td><td><input id="framecontents" name="framecontents" readonly="readonly"  style="width:65px;"/></td>
					<td>framesname</td><td><input id="framesname" name="framesname" readonly="readonly"  style="width:65px;"/></td>
				<td>frametype</td><td><input id="frametype" name="frametype" readonly="readonly"  style="width:65px;"/></td>
				<td><input id="framefield" name="framefield"  style="display:none;"/></td>
					<td>frames-----2</td><td><input id="framefield2" name="framefield2" /></td>
					<td><input id="framecrtime" name="framecrtime"  style="display:none;"/></td>
					<td><input id="frameorg" name="frameorg"  style="display:none;"/></td>
					<td><input id="framecode" name="framecode"  style="display:none;"/></td>
				    <td><input id="frameremark" name="frameremark"  style="display:none;"/></td>
				
				</tr>
				
			
				
				
				<tr> 
					<td>palette</td><td><input id="pal_ID " name="pal_ID" readonly="readonly"  style="width:65px;"/></td>
					<td>palettename</td><td><input id="palettename" name="palettename" readonly="readonly"  style="width:65px;"/></td>
					<td>palettecontents</td><td><input id="palettecontents" name="palettecontents" readonly="readonly"  style="display:none;width:65px;"/></td>
					<td>palettesname</td><td><input id="palettesname" name="palettesname" readonly="readonly"  style="display:none;width:65px;"/></td>
					<td>palettetype</td><td><input id="palettetype" name="palettetype" readonly="readonly"  style="display:none;width:65px;"/></td>
					
					<td>palettefield</td><td><input id="palettefield" name="palettefield"  style="display:none;"/></td>
					<td>palettefield2</td><td><input id="palettefield2" name="palettefield2"  style="display:none;"/></td>
					<td>palettecrtime</td><td><input id="palettecrtime" name="palettecrtime"  style="display:none;"/></td>
					<td>paletteorg</td><td><input id="paletteorg" name="paletteorg"  style="display:none;"/></td>
					<td>palettecode</td><td><input id="palettecode" name="palettecode"  style="display:none;"/></td>
					<td>paletteremark</td><td><input id="paletteremark" name="paletteremark"  style="display:none;"/></td>
					
					
				</tr>
				
                         
				
				
				<tr> 
					<td>scuttles_ID</td><td><input id="scu_ID " name="scu_ID" readonly="readonly"  style="width:65px;"/></td>
					<td>scuttlename</td><td><input id="scuttlename" name="scuttlename" readonly="readonly"  style="width:65px;"/></td>
					<td>scuttlecontents</td><td><input id="scuttlecontents" name="scuttlecontents" readonly="readonly"  style="display:none;width:65px;"/></td>
					<td>scuttlesname</td><td><input id="scuttlesname" name="scuttlesname" readonly="readonly"  style="display:none;width:65px;"/></td>
					<td>scuttletype</td><td><input id="scuttletype" name="scuttletype" readonly="readonly"  style="display:none;width:65px;"/></td>
					
					<td>scuttlefield</td><td><input id="scuttlefield" name="scuttlefield"  style="display:none;"/></td>
					<td>scuttlefield2</td><td><input id="scuttlefield2" name="scuttlefield2"  style="display:none;"/></td>
					<td>scuttlecrtime</td><td><input id="scuttlecrtime" name="scuttlecrtime"  style="display:none;"/></td>
					<td>scuttleorg</td><td><input id="scuttleorg" name="scuttleorg"  style="display:none;"/></td>
					<td>scuttlecode</td><td><input id="scuttlecode" name="scuttlecode"  style="display:none;"/></td>
					<td>scuttleremark</td><td><input id="scuttlesemark" name="scuttleremark"  style="display:none;"/></td>
					
					
				</tr>
				

				
				<tr> 
					<td>1</td><td><input id="acc_ID " name="acc_ID" readonly="readonly"  style="width:65px;"/></td>
					<td>accessoriesname</td><td><input id="accessoriesname" name="accessoriesname" readonly="readonly"  style="width:65px;"/></td>
					<td>accessoriescontents</td><td><input id="accessoriescontents" name="accessoriescontents" readonly="readonly"  style=" display:none;width:65px;"/></td>
					<td>accessoriessname</td><td><input id="accessoriessname" name="accessoriessname" readonly="readonly"  style="display:none;width:65px;"/></td>
					<td>accessoriestype</td><td><input id="accessoriestype" name="accessoriestype" readonly="readonly"   style="display:none;width:65px;"/></td>
					
					<td>accessoriesfield</td><td><input id="accessoriesfield" name="accessoriesfield"  style="display:none;"/></td>
					<td>accessoriesfield2</td><td><input id="accessoriesfield2" name="accessoriesfield2"  style="display:none;"/></td>
					<td>accessoriescrtime</td><td><input id="accessoriescrtime" name="accessoriescrtime"  style="display:none;"/></td>
					<td>accessoriesorg</td><td><input id="accessoriesorg" name="accessoriesorg"  style="display:none;"/></td>
					<td>accessoriescode</td><td><input id="accessoriescode" name="accessoriescode"  style="display:none;"/></td>
					<td>accessoriesremark</td><td><input id="accessoriesremark" name="accessoriesremark"  style="display:none;"/></td>
					
					
				</tr>
				
				<tr> 
					<td>else_ID</td><td><input id="else_ID " name="else_ID" readonly="readonly"  style="width:65px;"/></td>
					<td>elsesname</td><td><input id="elsesname" name="elsesname" readonly="readonly"  style="width:65px;"/></td>
					<td>elsescontents</td><td><input id="elsescontents" name="elsescontents" readonly="readonly"  style=" display:none;width:65px;"/></td>
					<td>elsessname</td><td><input id="elsessname" name="elsessname" readonly="readonly"  style="display:none;width:65px;"/></td>
					<td>elsestype</td><td><input id="elsestype" name="elsestype" readonly="readonly"   style="display:none;width:65px;"/></td>
					
					<td>elsesfield</td><td><input id="elsesfield" name="elsesfield"  style="display:none;"/></td>
					<td>elsesfield2</td><td><input id="elsesfield2" name="elsesfield2"  style="display:none;"/></td>
					<td>elsescrtime</td><td><input id="elsescrtime" name="elsescrtime"  style="display:none;"/></td>
					<td>elsesorg</td><td><input id="elsesorg" name="elsesorg"  style="display:none;"/></td>
					<td>elsescode</td><td><input id="elsescode" name="elsescode"  style="display:none;"/></td>
					<td>elsesremark</td><td><input id="elsesremark" name="elsesremark"  style="display:none;"/></td>
					
					
				</tr>
				
                         
				
			</tr>
			<tr style="display: none;">
				<td><input id="org" name="org"></input></td>
				<td><input id="createPerson" name="createPerson"></input></td>
				<td><input id="createPersonId" name="createPersonId"></input></td>
			</tr>
		</table>
		</form>
		</div>
	</body>
</html>
		