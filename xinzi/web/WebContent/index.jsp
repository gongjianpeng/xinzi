<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html >
<html>
<title>典尚管理</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" type="text/css" href="themes/apusic/om-all.css" />
<style>
html, body { 
    width: 100%; height: 100%; padding: 0px; margin: 0px;overflow: hidden;
 }
#north-panel {
	overflow:hidden;
	width:100%;
	border:0px;
	padding:0px;
	margin:0px;
	background: url(home/resource/images/title.png) ;
}
#west-panel{
	background : url(themes/apusic/images/bg.jpg);
}
#north-panel a:link, #north-panel a:visited,#north-panel a:active,#north-panel a:hover,.out {
	font-family: "宋体";
	font-size: 12px;
	font-weight: bold;
    color: white;
	font-weight:bold;
	text-decoration:none; 
	outline: 0 !important;
	*outline:0;
}
#buttonbar{
//width:100%;
	height:40px;
	//background: url(home/resource/images/title.png) ;
	//line-height:40px;
	vertical-align: bottom;
	border-bottom:10px solid url(themes/apusic/images/bg.jpg);
	padding:0px; 
	//margin-top:10px;
}
.menu{
   cursor: pointer;
   height : 30px;
   line-height:28px;
   display:inline-block;
   text-align: center;
   background: url(home/resource/images/title1.png);
   color:blue;
   font-weight:bold; 
   padding-left:5px;
   padding-right:5px;
   margin-top:5px
}
.menuFocus{
   cursor: pointer;
   height : 30px;
   line-height:28px;
   display:inline-block;
   text-align: center;
   background: url(themes/apusic/images/bg.jpg);//#15428B;
   color:blue;//#15428B;
   font-weight:bold;
   padding-left:5px;
   padding-right:5px;
   border:1px
}
img,#header-icons {
	border:medium none;
	border-width:0;
	padding:0 0 0 0;	
}

</style> 
<script src="jquery/js/jquery.min.js"></script>
<script src="ui/om-core.js"></script>
<script src="ui/om-mouse.js"></script>
<script src="ui/om-menu.js"></script>
<script src="ui/om-draggable.js"></script>
<script src="ui/om-position.js"></script>
<script src="ui/om-resizable.js"></script>
<script src="ui/om-dialog.js"></script>
<script src="ui/om-panel.js"></script>
<script src="ui/om-combo.js"></script>
<script src="ui/om-tree.js"></script>
<script src="ui/om-borderlayout.js"></script>
<script src="ui/om-messagebox.js"></script>
<script src="ui/om-messagetip.js"></script>
<script src="ui/om-button.js"></script>

<script type="text/javascript" charset="UTF-8">
$(document).ready(function() {
    var element = $('#page').omBorderLayout({
   	   panels:[{
   	        id:"north-panel",
   	        region:"north",
   	        resizable:false,
   	        collapsible:false,
   	        header : false,
   	        height : 100
   	    },{
   	        id:"center-panel",
   	     	header:false,
   	        region:"center"
   	    },{
   	        id:"west-panel", 
   	        resizable:true,
   	        collapsible:false,
   	        title:"功能导航",
   	        region:"west",
   	        width:200
   	    }],
   	    fit : true,
   //	    hideCollapsBtn : true,
   	    spacing:5
    });

    function initMenu(button,ds){
        var menu = $('<div style=" font-size:12px;"></div>');
        $('#page').append(menu);
    	menu.hover(function(){
	        button.attr('inmenu','true');
	    },function(){
	        $(this).omMenu('hide'); 
	        button.removeAttr('inmenu');
	        if(!button.attr('selected')){
	        	button.removeClass('menuFocus').addClass('menu');
	        }
	    });
	    /*
	    .omMenu({
	        minWidth : 200,
	        maxWidth : 200,
	        dataSource : ds,
	        onSelect : function(item,event){
    			document.getElementById('middle').src=item.url;
    			$('.menuFocus').removeClass('menuFocus')
    			               .addClass('menu')
    			               .removeAttr('selected');
    			         
    			button.removeClass('menu')
    			      .addClass('menuFocus')
    			      .attr("selected","true");
    			      
    			setNavTreeData(ds);
	        }
	    })
	    */
	    button.hover(function(){
	    	$(this).removeClass('menu').addClass('menuFocus');
	    //	if(ds.length>0){
	    //		menu.omMenu('show',this);
	    //	}
	    },function(){
	    	setTimeout(function(){
	        	if(!button.attr('inmenu')){
	            	menu.omMenu('hide'); 
	            	if(!button.attr('selected')){
	            		button.removeClass('menuFocus').addClass('menu');
	            	}
	        	}
	        },100);
	    }).click(function(){
	        $('.menuFocus').removeClass('menuFocus')
	                       .addClass('menu')
    			           .removeAttr('selected');
	    	$(this).removeClass('menu')
	    	       .addClass('menuFocus')
	    	       .attr('selected','true');
	    	       
	    	middle.location.href=$(this).attr('url');
	    	setNavTreeData(ds,$(this).html());
	    	$('#page').omBorderLayout('expandRegion', 'west');
	    });
    } 
	$('#bhome').hover(function(){
		$(this).removeClass('menu').addClass('menuFocus');
	},function(){
	    if(!$(this).attr('selected')){
			$(this).removeClass('menuFocus').addClass('menu');
		}
	}).click(function(){
        $('.menuFocus').removeClass('menuFocus')
                       .addClass('menu')
   			           .removeAttr('selected');
    	$(this).removeClass('menu')
    	       .addClass('menuFocus')
    	       .attr('selected','true');
    	
    	setNavTreeData(getNavTreeData(null));
    	middle.location.href=$(this).attr('url'); 
    	$('#page').omBorderLayout('expandRegion', 'west');
    });
    $('#n10').click(function(){
    	setNavTreeData(getNavTreeData("n10"),$(this).html());
    	middle.location.href=$(this).attr('url'); 
    	$('#page').omBorderLayout('expandRegion', 'west');
    	$('.menuFocus').removeClass('menuFocus')
                       .addClass('menu')
   			           .removeAttr('selected');
    });
    
	setNavTreeData(getNavTreeData(null));
	
	$('#menudata').find('.menu').each(function(index,item){
	     var button=$(item).clone();
	     button.html($(item).attr('text')); 
	     $('#buttonbar').append(button).append(' ');
		 initMenu(button,getNavTreeData($(item).attr('id')));
	});
    $('#buttonbar').append('<div style="height:5px;width:100%;background: url(themes/apusic/images/bg.jpg);"/>');
    
    $('#locale').omCombo({
        dataSource:[
        	{text:"<fmt:message key='top.chinese' />",value:'zh_CN'}, 
        	{text:"<fmt:message key='top.english' />",value:'en_US'}
        ],
        width : '80px',
        value:"${loginInfo.locale}",
        editable:false,
        listMaxHeight : 'auto',
        onValueChange:function(target,newValue,oldValue,event){ 
            if(oldValue!=''){
		    	setLocale(newValue);
		    }
		}
    });
    function setLocale(l){
		$.post('setLocale.do',{locale:l},function(){
			window.location.reload();
		});
	    return false;
	}
	
});

function exit(){
	window.location='j_spring_security_logout';
}

function getNavTreeData(pid){
    var menudata ;
    if(pid && pid!=''){
    	menudata = $("#menudata div[pid='"+pid+"']");   
    }else{
    	menudata = $("#menudata div[hide!='true']");   
    }
    return $.map(menudata,function(item){
  	      var newitem={};
  	      newitem.id=$(item).attr('id');
  	       
       	  newitem.pid=$(item).attr('pid');
       	  newitem.url=$(item).attr('url');
  	       
  	   	  newitem.label=$(item).attr('text');
  	   	  newitem.text=$(item).attr('text');
  	   	  
  	   	   return newitem;
    });
}
function setNavTreeData(data,buttonName){
	 if(!buttonName){
	 	buttonName='';
	 }else{
	 	buttonName+=">>";
	 }
     $("#menuTree").omTree({
          dataSource : data,
          simpleDataModel: true,
          onSelect:function(node){
             if(node.url && !node.children){
             	  $('#page').omBorderLayout('collapseRegion', 'west');
             	  var windowname = encodeURI(encodeURI(buttonName + getWindowName(node)));
             	  document.getElementById('middle').src=node.url+'?windowname='+windowname;
             }
          }
     });
}
function getWindowName(node){
	var name = node.text;
	var parentNode = $("#menuTree").omTree("getParent",node);
	if(parentNode != null){
		name = getWindowName(parentNode)+">>"+name;
	}
	return name ;
}
function tickDateTime() {  
	  var hours, minutes, seconds;  
	  var intHours, intMinutes, intSeconds;  
	  var today;  
	  today = new Date();  
	  intYear = today.getFullYear();  
	  intMonth = today.getMonth() + 1;  
	  intDay = today.getDate();  
	  intHours = today.getHours();  
	  intMinutes = today.getMinutes();  
	  intSeconds = today.getSeconds();
	  
	  if (intHours == 0) {  
		  hours = "00:";  
	  }else if(intHours < 10) {   
			hours = "0" + intHours+":";  
	  }else{  
			hours = intHours + ":";  
	  }  
	  
	  if(intMinutes < 10) {  
			minutes = "0"+intMinutes+":";  
	  }else{  
			minutes = intMinutes+":";  
	  }  
	  
	  if(intSeconds < 10) {  
			seconds = "0"+intSeconds+" ";  
	  }else{  
			seconds = intSeconds+" ";  
	  }   
			timeString = intYear + "-" + intMonth +"-"+ intDay  + " " + hours+minutes+seconds;  
			Clock.innerHTML = timeString;  
			window.setTimeout("tickDateTime();", 1000);  //设置一秒
 }  
  // window.onload = tickDateTime; 
 function omConfirm(msg,fn){  
      $( "#dialog-confirm" ).css('padding','20px').text(msg);
      var flag = false;
      $( "#dialog-confirm" ).omDialog({
      	  title : "提示" ,
	      autoOpen : true, 
	      resizable: false,
	      height:140,
	      modal: true,
	      buttons: [{
	          text : "确定", 
	          click : function () {
	              fn();
	              $("#dialog-confirm" ).omDialog("close");
	              return false;
	          }
	      }, {
	          text : "取消", 
	          click : function () {
	              $("#dialog-confirm" ).omDialog("close");
	              return false;
	          }
	      }]
	  });

}
function refreshLogo(){
	$('#logo').removeAttr('src').attr('src','images/logo.png');
}
</script> 

</head>

<div id="page" style="overflow:hidden;">
	<div id="north-panel" >
		<div style="height:60px">
			<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
				<tr>
				    <!-- 
				    <td align="center" width="80" height="50"><img id="logo" width="50" height="50" src="images/logo.png" /></td>
				     -->
					<td scope="row" class="out" ><font size="6" >  &nbsp;典尚管理</font>	</td>
					<td width="150" align="center" scope="row" >
						<b class="out">欢迎登录&nbsp;${loginInfo.loginPerson.name}</b>
					</td>
					<td align="left" class="out" scope="row" style="width: 210px;">
						<a id="n10" url="main.do" href="#" style="width:50px;display:inline-block;text-align:center; border-right:1px inset white" >设置</a>
					  
					    <a  href="#" style="width:50px;display:inline-block;text-align:center;" onclick="exit()" >退出</a>
					</td>
				</tr>
			</table>
		</div>

		
		
		<div id="buttonbar">
			<div id="bhome" class="menuFocus om-corner-top" style="margin-left:5px;width:60px;" selected="true" url="main.do" >首页</div>
		</div> 
		
	</div>
	<div id="west-panel" style="overflow:auto">
	    <ul id="menuTree"></ul>
    </div>
	<div id="center-panel" style="overflow:hidden">
		<iframe id="middle" src="main.do" frameborder='0' scrolling="no" style= 'width:100%;height:100%;border:0px;margin:0px;padding:0px;overflow:hidden;scroll:no' ></iframe>
	</div>
</div>
<div id="dialog-confirm"></div>
<%@ include file="infom/menudata.jsp"%>

</html>