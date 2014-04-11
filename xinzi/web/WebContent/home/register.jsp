<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  	<title>注册</title>
  	  <jsp:include page="common/include.jsp"></jsp:include>
	<script type="text/javascript">
		function refreshCaptcha(){
			$("img[name='CaptchaId']").attr("src",'<%=basePath%>home/common/captcha.jsp?d='+Math.random());
		}
		
		$(function(){
			
			/*********检测用户名是否存在**********/
			$("#com_username").blur(function(){
				 var username=$(this).val();
				$.ajax({
					type: "POST", 
					url:'<%=basePath%>validUserName.do', 
		      		dataType: "json", 
				    data:{name:username},
				    success: function(responseText){
				    	 var flag=responseText.result;
				    	 if(flag=="true"){
				    	 	$("#com_userNameTip").html("<font style='red'>账号已经存在!</font>");
				    	 }else{
				    	 	$("#com_userNameTip").html("<font style='green'>账号可以使用!</font>")
				    	 }
				    }
				});
			});
			
			
			/********************************
			 * 企业提交按钮*****Begin*************
			 * ******************************
			 */
			$("#btnEnterprise").click(function(){
				var company_name=$("#company_name").val();
				var com_businessNumber=$("#com_businessNumber").val();;
				var com_address=$("#com_address").val();
				var com_representative=$("#com_representative").val();
				var com_username=$("#com_username").val();
				var com_email=$("#com_email").val();
				var com_password=$("#com_password").val();
				var com_answer=$("#com_answer").val();
				var com_phone=$("#com_phone").val();
				var com_fax=$("#com_fax").val();
				var com_yzm=$("#com_yzm").val();
				
				 // 参数验证
				var emptyErr = [company_name, com_businessNumber, com_address,com_representative,
								com_username, com_email, com_password,com_phone,com_yzm];
				 
		        var emptyMsg = ['company_name', 'com_businessNumber', 'com_address','com_representative',
		        				'com_username', 'com_email', 'com_password','com_phone','com_yzm'];
		        
		        var emptyMsgTip=['company_nameTip', 'com_businessNumberTip', 'com_addressTip','com_representativeTip',
		        				'com_usernameTip', 'com_emailTip', 'com_passwordTip','com_phoneTip','com_yzmTip'];
		        
		        var emptyShow = ['*企业名称不能为空', '*营业执照注册号不能为空',  '*地址不能为空', '*法定代表人不能为空', '*登录名不能为空', 
		        				'*邮箱地址不能为空', '*密码不能为空','首选电话号码不能为空','验证码不能为空'];
		        /*空值验证*/
		        for(var i = 0; i < emptyErr.length; i ++){
		         	var cEle = emptyErr[i];
		         	if(cEle == '' || cEle == 'undifined' || cEle == null){
		         		 alert(emptyShow[i])
		         		return;
		         	}
		       }  
		       if($("#check_box1")[0].checked){
		        	$.ajax({
						type: "POST", 
						url:'<%=basePath%>enterpriseRegister.do?option=qiye', 
			      		dataType: "json", 
					    data:{
							  company_name:company_name,
							  com_businessNumber:com_businessNumber,
							  com_address:com_address,
							  com_representative:com_representative,
							  com_username:com_username,
							  com_email:com_email,
							  com_password:com_password,
							  com_answer:com_answer,
							  com_phone:com_phone,
							  com_fax:com_fax,
							  captcha:com_yzm
					    },
					    success: function(responseText){
					    	var status=responseText.status;
					    	if(status=="success"){
					    		alert("注册成功!");
					    		window.location.href="<%=basePath%>home/verifyUserInfo.jsp";
					    	} else{
					    		alert("验证码有误!"); 
					    		refreshCaptcha();
					    	}
					    }
					});
		       }else{
		    	   	alert("请选择典尚用户注册协议");
					return false;
		       }
				
			});
			/********************************
			 * 经销商提交按钮*Begin*****************
			 * ******************************
			 */
			$("#btnDealer").click(function(){
				
				var d_userName=$("#d_userName").val();
				var d_company=$("#d_company").val();
				var d_email=$("#d_email").val();
				var d_password=$("#d_password").val();
				var d_answer=$("#d_answer").val();
				var d_fax=$("#d_fax").val();
				var d_phone=$("#d_phone").val();
				var d_address=$("#d_address").val();
				var d_captcha=$("#d_captcha").val();
					 // 参数验证
				var emptyErr = [d_userName, d_company, d_email,d_password,
								d_fax, d_phone, d_address,d_captcha];
				 
		        var emptyMsg = ['d_userName', 'd_company', 'd_email','d_password',
		        				'd_fax', 'd_phone', 'd_address','d_captcha'];
		        
		        var emptyMsgTip=['company_nameTip', 'com_businessNumberTip', 'com_addressTip','com_representativeTip',
		        				'com_usernameTip', 'com_emailTip', 'com_passwordTip','com_phoneTip','com_yzmTip'];
		        
		        var emptyShow = ['*登录名不能为空','*企业名称不能为空', '*邮箱地址不能为空', '*密码不能为空',
		                          '*传真不能为空', '首选电话号码不能为空', '*地址不能为空', 
		        				'验证码不能为空'];
		        /*空值验证*/
		        for(var i = 0; i < emptyErr.length; i ++){
		         	var cEle = emptyErr[i];
		         	if(cEle == '' || cEle == 'undifined' || cEle == null){
		         		 alert(emptyShow[i])
		         		return;
		         	}
		       }  
		      
	        
				if($("#check_box2")[0].checked){
					 $.ajax({
						type: "POST", 
						url:'<%=basePath%>dealerRegister.do?option=jxs', 
			      		dataType: "json", 
					    data:{
							 d_userName:d_userName,
							 d_company:d_company,
							 d_email:d_email,
							 d_password:d_password,
							 d_answer:d_answer,
							 d_fax:d_fax,
							 d_phone:d_phone,
							 d_address:d_address,
							 d_captcha:d_captcha
					    },success: function(responseText){
					    	var status=responseText.status;
					    	if(status=="success"){
					    	   alert("注册成功");
					    	   window.location.href="<%=basePath%>home/verifyUserInfo.jsp";
					    	} else{
					    		alert("验证码有误!");
					    		refreshCaptcha();
					    	}
					    }
					});
				}else{
					alert("请选择典尚用户注册协议");
					return false;
				}
				
			});
			/*****************经销商提交End***************/
		})
		
		
		$(function(){
			
			 
		})
	</script>	
  </head>
  
  <body class="register">
<div class="wrapper">
	<div class="reg_header">
  	<a href="#"><img src="<%=basePath%>home/resource/images/register_logo.png" width="99" height="69" /></a>
    <div class="tab_reg company">
    	<ul>
        <li name="company" class="hover">企业注册</li>
        <li name="dealer">经销商注册</li>
      </ul>
    </div>
  </div>
  <div class="reg_content">
  	<div class="car_nav">
    	<ul>
      	<li class="cur active"><h2><i>1</i>填写账号信息</h2><span></span></li>
        <li><h2><i>2</i>验证账户信息</h2><span></span></li>
        <li><h2><i>3</i>注册成功</h2></li>
        </ul>
    </div>
    <div class="reg_form">
     <!-- ==========企业注册===============Begin -->
	    <ul class="company">
	      	<li><span>企业名称</span><input type="text" class="text" id="company_name" /></li><span id="companyNameTip" style="color: red"></span>
	        <li><span>营业执照注册号</span><input type="text" class="text" name="com_businessNumber" id="com_businessNumber"/>
	        <span id="com_businessNumberTip"></span></li>
	        <li><span>地址</span><input type="text" class="text" name="com_address" id="com_address"/><span id="com_addressTip"></span></li>
	        <li><span>法定代表人</span><input type="text" class="text" name="com_representative" id="com_representative"/><span id="com_representativeTip"></span></li>
	        <li><span>登录名</span><input type="text" class="text" id="com_username" name="com_username"/><span id="com_userNameTip"></span></li>
	        <li><span>密码</span><input type="password" class="text" id="com_password" name="com_password" /><span id="com_passwordTip"></span></li>
	          <li><span>邮箱地址</span><input type="text" class="text" id="com_email" name="com_email"/><span id="com_emailTip"></span></li>
	        <li>
	           <span>安全问题答案</span>
	           <select>
		          	<option>安全问题答案</option>
		            <option  value="1">你的配偶是谁!</option>
		            <option  value="2">你的姓名是什么!</option>
		            <option  value="3">你小学学校是!</option>
		          </select>
	        	<input type="text" class="text" name="com_answer" id="com_answer"/><span id="com_answerTip"></span>
	        </li>
	        <li><span>首选电话号码</span><input type="text" class="text" id="com_phone" name="com_phone" /><span id="com_phoneTip"></span></li>
	        <li><span>传真</span><input type="text" class="text" name="com_fax" id="com_fax"/><span id="com_faxTip"></span></li>
	       <li>
	      		<span>验证码</span> 
	      		<input name="Captcha" type="text" id="com_yzm" maxlength="6" class="NoBorder" style="width:90px; float:left;" />&nbsp;&nbsp;
	      		<img src="<%=basePath%>home/common/captcha.jsp" style="border-top: 0px;cursor: pointer;" 
	      			name="CaptchaId" alt="验证码"  title="看不清？请点击换一张!" onclick="refreshCaptcha()"/>
			  <a href="javascript:refreshCaptcha()"  style="cursor: hand;" id="nextImg"/>换一张</a><span id="com_yzmTip"></span>
			</li>
			<li>
		  		<span style="color: red"></span>
		  		<span style="color: red" id="show_id"></span>
			</li>
	        <li><input type="checkbox" id="check_box1" class="check_box" /><label for="check_box">我已阅读并同意《典尚用户注册协议》</label></li>
	        <li>
	        	<input type="submit" class="button_1" value="同意协议并注册"  id="btnEnterprise"/>
	        </li>
      </ul>
      
      <!-- ==========企业注册===============End -->
      
      <!-- ==========经销商===============Begin -->
      <ul class="dealer">
	        
	        <li><span>公司名称</span><input type="text" class="text" id="d_company" /></li>
	        <li><span>邮箱地址</span><input type="text" class="text" id="d_email" /></li>
	        <li><span>登录名</span><input type="text" class="text" id="d_userName" /></li>
	        <li><span>密码</span><input type="password" class="text" id="d_password" /></li>
	        <li>
	        	<span>安全问题答案</span>
	           <select>
		          	<option>安全问题答案</option>
		            <option  value="1">你的配偶是谁!</option>
		            <option  value="2">你的姓名是什么!</option>
		            <option  value="3">你小学学校是!</option>
		        </select>
	        	<input type="text" class="text" name="d_answer" id="d_answer"/>
	        </li>
	        <li><span>首选电话号码</span><input type="text" class="text" id="d_phone" /></li>
	        <li><span>经销商地址</span><input type="text" class="text" id="d_address" /></li>
	        <li><span>传真</span><input type="text" class="text" name="d_fax" id="d_fax"/></li>
	         <li>
	      		<span>验证码</span> 
	      		<input name="Captcha" type="text" id="d_captcha" maxlength="6" class="NoBorder" style="width:90px; float:left;" />&nbsp;&nbsp;
	      		<img src="<%=basePath%>home/common/captcha.jsp" style="border-top: 0px;cursor: pointer;" name="CaptchaId" alt="验证码" 
	      		title="看不清？请点击换一张!" onclick="refreshCaptcha()"/>
			  <a href="javascript:refreshCaptcha()"  style="cursor: hand;" />换一张</a>
			</li>
	        <li><input type="checkbox" id="check_box2" class="check_box" /><label for="check_box">我已阅读并同意《典尚用户注册协议》</label></li>
	        <li>
	        	<input type="submit" class="button_1" value="同意协议并注册" id="btnDealer"/>
	        </li>
      </ul>
       <!-- ==========经销商==========End -->
      
    </div>
  </div>
</div>
<h4></h4>
</body>
</html>
  
