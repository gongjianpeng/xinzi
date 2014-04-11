<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="../themes/apusic/om-all.css" />
		<link href="../css/css.css" rel="stylesheet" type="text/css" />
		
		<!-- by make start -->
		<link href="../css/all_css.css" rel="stylesheet" type="text/css" />
		
		<!-- by make end -->
		<script src="../jquery/js/jquery.min.js"></script>
		<script src="../ui/om-core.js"></script>
		<script src="../ui/om-mouse.js"></script>
		<script src="../ui/om-tree.js"></script>
		<script src="../ui/om-menu.js"></script>
		<script src="../ui/om-draggable.js"></script> 
		<script src="../ui/om-position.js"></script>
		<script src="../ui/om-resizable.js"></script>
		<script src="../ui/om-dialog.js"></script>
		<script src="../ui/om-messagebox.js"></script>
		<script src="../ui/om-messagetip.js"></script>
		<script src="../ui/om-buttonbar.js"></script>
		<script src="../ui/om-button.js"></script>
		<script src="../ui/om-ajaxsubmit.js"></script>
		<script src="../ui/om-grid.js"></script>
		<script src="../ui/om-grid-headergroup.js"></script>
		<script src="../ui/om-validate.js"></script>
		<script src="../ui/om-combo.js"></script>
		<script src="../ui/om-calendar.js"></script>
		<script src="../ui/om-panel.js"></script>
		<script src="../ui/om-tabs.js"></script>
		<script src="../ui/om-borderlayout.js"></script>
		<script src="../ui/om-fileupload.js"></script>
		<style>
html,body {
	font-size: 16px;
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
	background-image: url(../images/icons/search.gif) !important;
}

input[type='text'] {
	margin: 0px;
	padding: 0px;
	height: 18px;
	width: 140px
}
</style>
		<script>
		
		$(document).ready(function() {
          $('body').omBorderLayout({
			   panels:[
			        {
			       		id:"panel", 
			   	        resizable:false,
			   	        collapsible:false,
			   	        title: decodeURI("${param.windowname}"),
			   	        region:"north",
			   	        height:57
			        },
			        {
			   	        id:"doorDiv",
			   	     	header:false,
			   	        region:"center"
			   	    }],
			   	    fit:true,
			   	    spacing:2
	    }); 
	    var id =<%=request.getParameter("id")%>
	    
			$('#doorTable').omGrid({
		        dataSource : 'getDoors.do?id='+id ,
		        limit : 15,
		        showIndex : false,
		        autoFit : true,
		        height:'fit',
		        colModel : [{header : "门类名称", name : 'name', align : 'center'},                              
                              {header : "门类图片", name : 'reality', align : 'center'}
                             
               			 ],
               	onRowDblClick:function(rowIndex,rowData){
					//window.parent.open("doordetail.do?id="+rowData.id);
					window.open( "doordetail.do?id="+rowData.id , "_self");
				}
	        });
			
        });
    </script>
    
</head>
<fmt:bundle basename="messages.messages_doors" />
<body style="overflow: hidden">
	
	<div id="doorDiv" class="div1">
		<table id="doorTable" class="table2"></table>
	</div>
	</body>
</html>