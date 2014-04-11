<!DOCTYPE html >
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<!-- 导入operamasks-ui-要的javascript库 -->
<jsp:include page="home/common/operamasks-ui-include.jsp"></jsp:include>

<style type="text/css">
.img{
	    border: 0;
	    background-position: center;
		background-repeat: no-repeat;
		background-attachment: fixed;
		margin:0 auto;
	    font-size:0;
	    overflow:hidden;   
}
.div {
		background-position: center;
		background-repeat: no-repeat;
		background-attachment: fixed;
		}
.tab1{
		
		background-position: center;
		background-repeat: no-repeat;
		background-attachment: fixed;
		overflow:hidden;   
		}
		
	
</style>


<script type="text/javascript" language="javascript">
	
	//<![CDATA[
	   var pageH = document.documentElement.clientHeight -5;
	   var frameH = pageH - 20 ;
	   //var pageH = window.screen.height;
	   var pageW = document.documentElement.clientWidth;
		$(document).ready(function() {
		/*	$('#main-tab').omTabs( {
			    height: pageH,
			    width: 'fit',
				border : false,
				closable : false,
				tabWidth: 'auto',
				tabMenu : false,
				lazyLoad : true ,
				onActivate : function(n,event) {
			          var tabId = $('#main-tab').omTabs('getAlter',n);
			          document.getElementById(tabId).src=document.getElementById(tabId).src;
			    }

				
				
			});
			*/
			$(window).resize(function() {
			    pageH = document.documentElement.clientHeight - 25;
			 	$('#main-tab').omTabs( {
				    height: pageH,
				    width: 'fit',
					border : true,
					closable : true,
					tabWidth: 'auto',
					tabMenu : true,
					lazyLoad : true 
					
				});
				//var tabId = $('#main-tab').omTabs('getActivated');
				//document.getElementById(tabId).src=document.getElementById(tabId).src;
			/*	$('#main-tab').find('iframe').each(function(){
				    var tabId = $(this).attr('id'); 
				    alert(tabId)
					var src = $(this).attr('src');
					document.getElementById(tabId).src=document.getElementById(tabId).src;
				});
				*/
			});			
			
		});
		
		
	   var addTab = function(vtitl,vurl,tabFlag){
	   	   if(tabFlag != null){
	   	       vtitl = tabFlag+"-"+vtitl;
	   	   }
	       var tabIndex = $('#main-tab').omTabs('getAlter',vtitl);
	       
	       if(tabIndex == null){
			   $('#main-tab').omTabs('add', {
		            height: pageH,
		            tabId: vtitl,
                    title : vtitl,
                    tabMenu : true,
                    border : true,
                    closable : true,
                    lazyLoad : true ,
                    content : "<iframe id='"+vtitl+"' name='"+vtitl+"' frameborder='0' style= 'width:100%;height:"+frameH+"px;border:0px;margin:0px;padding:0px;overflow:hidden;scroll:no' src='"+vurl+"'></iframe>"
	            });
	            $("iframe").parents('div').css({ padding:"0px",margin:"0px",overflow:"hidden"});
            }else{
                $('#main-tab').omTabs('activate',tabIndex);
            }
      };   
      function omConfirm(msg,fn){  
         $( "#dialog-confirm" ).css('padding','20px').text(msg);
         var flag = false;
		 $( "#dialog-confirm" ).omDialog({
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
		
 //]]>
</script>




	
</head>
<body  style="height:100%; width:100%" >
<img src="<%=basePath%>home/resource/images/home001.jpg" width="100%" height="100%"  border="0px" style="margin:0px; padding:0px; border:0px;"/>
<div id="dialog-confirm"></div>
</body>
