<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>电子画册</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>home/resource/js/gallery/style.css">

    <!-- Example assets -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>home/resource/js/gallery/jcarousel.basic.css">

    <script type="text/javascript" src="<%=basePath%>home/resource/js/gallery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>home/resource/js/gallery/jquery.jcarousel.min.js"></script>

    <script type="text/javascript" src="<%=basePath%>home/resource/js/gallery/jcarousel.basic.js"></script>
</head>

<body>
    <div class="wrapper">
        <div class="jcarousel-wrapper">
                <div class="jcarousel">
                    <ul>
                        <li><img src="<%=basePath%>home/resource/js/gallery/img1.jpg" width="600" height="400" alt=""></li>
                        <li><img src="<%=basePath%>home/resource/js/gallery/img2.jpg" width="600" height="400" alt=""></li>
                        <li><img src="<%=basePath%>home/resource/js/gallery/img3.jpg" width="600" height="400" alt=""></li>
                        <li><img src="<%=basePath%>home/resource/js/gallery/img4.jpg" width="600" height="400" alt=""></li>
                        <li><img src="<%=basePath%>home/resource/js/gallery/img5.jpg" width="600" height="400" alt=""></li>
                        <li><img src="<%=basePath%>home/resource/js/gallery/img6.jpg" width="600" height="400" alt=""></li>
                    </ul>
                </div>

                <a href="#" class="jcarousel-control-prev">&lsaquo;</a>
                <a href="#" class="jcarousel-control-next">&rsaquo;</a>
                
                <p class="jcarousel-pagination">
                    
                </p>
            </div>
    </div>
</body>

</html>
