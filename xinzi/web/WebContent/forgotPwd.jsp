<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
	<title>忘记密码</title>
	<jsp:include page="home/common/include.jsp"></jsp:include>
		<style type="text/css">
			.error {
				color: red;
				padding-left: 2px;
			}
		</style>
		<style type="text/css">
			.boxHd {
				height: 33px;
				overflow: hidden;
				margin-bottom: 24px;
				border-bottom: 1px dotted #c8c8c8;
				font-size: 20px;
				font-weight: normal;
				font-family: "Microsoft YaHei", \5fae\8f6f\96c5\9ed1, arial, \5b8b\4f53;
				color: #323232;
			}
		</style>
	</head>
	
	<script>
	
	
	
	/********************************
			 * 经销商提交按钮*Begin*****************
			 * ******************************
			 */
		//	$("#forpass").click(function(){
			  $('#forpass').die().live('click', function(e) {
				alert("...");
				
				var d_email=$("#d_email").val();
				
			});
			
	</script>

	<body class="register">
		<div class="wrapper">
			<div class="reg_header">
				<a href="#"><img src="<%=basePath%>/home/resource/images/register_logo.png" width="99"
						height="69" /> </a>
				<div class="tab_reg company">
					<!--<ul>
	        	<li name="company" class="hover">企业注册</li>
	        	<li name="dealer">经销商注册</li>
	      		</ul>
	    	-->
				</div>
			</div>
			<div class="reg_content">
				<div class="car_nav">
					<ul>
						<li class="cur active">
							<h2>
								<i>1</i>填写邮箱
							</h2>
							<span></span>
						</li>
						<li>
							<h2>
								<i>2</i>等待消息
							</h2>
							<span></span>
						</li>
						<li>
							<h2>
								<i>3</i>找到密码
							</h2>
						</li>
					</ul>
				</div>
				<div class="reg_form">
					<ul class="company">
						<form action="forgotwait.jsp" name="reg_form" method="post"
							id="reg_form">
							<div class="m-box">
								<h1 class="boxHd">
									请输入您要找回密码的邮箱
								</h1>
								<div class="boxBd" style="" align="center" style="height:200px;">
									<br />
									<br />
									<br />

									<div class="m-ipt m-ipt-icon">
										<div class="u-ipt">
											<div class="iptctn">
												<input
													style="width: 316px; font-size: 20px; height: 48px; border: 1px solid #7cbdf5; padding-left: 10px;"
													type="text" name="email" id="email" autocapitalize="off"
													myholder="请输入邮箱" placeholder="请输入邮箱">
												<div class="iptIcon iptIcon-usr"></div>
											</div>
										</div>
										<p class="u-tips">
											<em>&nbsp;</em><span></span>
										</p>
									</div>
									<button type="button" class="u-btn2"  id="forpass"
										style="background: #7cbdf5; width: 328px; font-size: 26px; color: #FFF; height: 48px; border: 0px">
										下一步
									</button>
								</div>
								<div class="ieAlpha"></div>
							</div>

						</form>
					</ul>
				</div>

			</div>
		</div>
	</body>
</html>
