<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" >
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link  href="<%=basePath%>themes/default/om-all.css"  rel="stylesheet" type="text/css"/>

<script src="<%=basePath%>jquery/js/jquery.min.js"></script>
<script src="<%=basePath%>ui/om-core.js"></script>
<script src="<%=basePath%>ui/om-mouse.js"></script>
<script src="<%=basePath%>ui/om-tree.js"></script>
<script src="<%=basePath%>ui/om-menu.js"></script>
<script src="<%=basePath%>ui/om-draggable.js"></script>
<script src="<%=basePath%>ui/om-position.js"></script>
<script src="<%=basePath%>ui/om-resizable.js"></script>
<script src="<%=basePath%>ui/om-dialog.js"></script>
<script src="<%=basePath%>ui/om-messagebox.js"></script>
<script src="<%=basePath%>ui/om-messagetip.js"></script>
<script src="<%=basePath%>ui/om-buttonbar.js"></script>
<script src="<%=basePath%>ui/om-button.js"></script>
<script src="<%=basePath%>ui/om-ajaxsubmit.js"></script>
<script src="<%=basePath%>ui/om-grid.js"></script>
<script src="<%=basePath%>ui/om-grid-headergroup.js"></script>
<script src="<%=basePath%>ui/om-validate.js"></script>
<script src="<%=basePath%>ui/om-combo.js"></script>
<script src="<%=basePath%>ui/om-calendar.js"></script>
<script src="<%=basePath%>ui/om-panel.js"></script>
<script src="<%=basePath%>ui/om-tabs.js"></script>
<script src="<%=basePath%>ui/om-borderlayout.js"></script>
<script src="<%=basePath%>ui/om-fileupload.js"></script>
 
 