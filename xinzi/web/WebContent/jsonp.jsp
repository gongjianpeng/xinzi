<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>jsonp</title>
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<script type="text/javascript" src="jquery-1.10.2.min.js"></script>
	<script type="text/javascript" >
             //http://60.191.235.254:8080/xinzi/pages/company/biankzhuCombo.do?biank=02&&biankzhu=01
             
        $.ajax({
        //pages/company/DoorJudge.do?dLength=2150&&dWidth=1000
           url:"http://localhost:8080/xinzi/pages/company/getdiyProductaddCat.do" ,
           //
           //sdoorCombo.do?chstyle=qichuang
           type: 'GET',
           dataType: 'JSONP',//注意类型
           success: function(responseText){
              alert("111");
               console.log(responseText);
             
              //    var json=responseText.data;
             //  $.each(json, function(index, node) {
             //        alert("id:"+node.id);
             //        $( 'body').append("编号:" +node.id+"&nbsp;&nbsp;&nbsp;名称:"+node.name+"&nbsp;&nbsp;&nbsp;图片:" +list.img+"<br/>");
                   
            //         $( "#img_p").attr("src" , "http://localhost:8080/xinzi/"+node.img);
            //   });
           }
       });
       </script>
  </head >
 
  <body >
   <img id="img_p"  alt="123213" />
	 
  </body>
</html>
