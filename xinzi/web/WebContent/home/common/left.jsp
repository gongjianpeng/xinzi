<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  	<title>注册成功</title>
    <jsp:include page="include.jsp"></jsp:include>
    
	<script type="text/javascript">
		/************加载产品分类*****************/
	function productClass(){
		$.ajax({
				type: "POST", 
				url:'<%=basePath%>'+"productClassData.do", 
	      		dataType: "json", 
			    success: function(responseText){
					//遍历返回数组的每一个实体
					var jsonArr=responseText.data;
	    	   		$.each(jsonArr,function(index, pro) {  
	    	   		    var img="<span><img src='<%=basePath%>home/resource/images/ico01.png'/></span>";
	    	   		    var str="";
	    	   			var proclassUL=$("#ul_proclass").attr("class","fl_xl");
	    	   		 	var proName="<a href='<%=basePath%>home/product/getProductByType.do?productName="+pro.name+"' id='proName'>"+pro.name+"</a>";
	    	   		 	str=img+proName;
	    	   		 	proclassUL.append("<li>"+str+"</li>");
		           })
			    }
		})
	}
	
	$(function(){
		
		productClass();
		 
	});
	</script>
</head>
	<div class="pro_fl">
     	<h3><em><img src="<%=basePath%>home/resource/images/public/sort_ico.png" /></em>产品分类</h3>
         <ul class="fl_xl" id="ul_proclass"> 
         
         </ul>
     </div>
     
     <div class="share">
        <p>把永康典尚分享给您的朋友</p>
        <em>
         <!-- 分享功能 Button BEGIN -->
		<div class="jiathis_style_24x24" >
			<a class="jiathis_button_qzone"></a>&nbsp;
			<a class="jiathis_button_tsina"></a>&nbsp;
			<a class="jiathis_button_tqq"></a>&nbsp;
			<a class="jiathis_button_weixin"></a>&nbsp;
			<a class="jiathis_button_renren"></a>&nbsp;
			<a href="http://www.jiathis.com/share?uid=1898949" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
		</div>
		<script type="text/javascript">
			var jiathis_config = {data_track_clickback:'true'};
		</script>
		<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1898949" charset="utf-8"></script>
		<!--  分享功能 Button END -->
            </em>
          </div>
            
        <div class="l_ban"><img src="<%=basePath%>home/resource/images/public/ad.png" /></div>