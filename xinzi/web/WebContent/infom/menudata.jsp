<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<div id="menudata" style="display:none">


     
       <sec:authorize ifAnyGranted="ROLE_QIYE">
             <div id= "n2" class ="menu om-corner-top" url ="main.do" text="企业管理" expanded=false />
            
               <sec:authorize ifAnyGranted="ROLE_QIYE_COMPANY" >
                         <div id= "n21" pid ="n2" url="pages/company/qiyeManager.do" text="企业管理" /> />
                             </sec:authorize>   
             
                
                               <sec:authorize ifAnyGranted="ROLE_QIYE_JIBEN"> 
                         <div id= "n23" pid ="n2" url="pages/company/jibenqiye.do" text="基本参数设置" /> />
                         </sec:authorize>   
                               <sec:authorize ifAnyGranted="ROLE_QIYE_BAOJIA"> 
                         <div id= "n24" pid ="n2" url="pages/company/baojiaqiye.do" text="报价系统设置" /> />
                         </sec:authorize>   
                               <sec:authorize ifAnyGranted="ROLE_QIYE_DINGDAN"> 
                         <div id= "n25" pid ="n2" url="pages/company/dingdanqiye.do" text="订单管理" /> />
                         </sec:authorize>   
                               <sec:authorize ifAnyGranted="ROLE_QIYE_PINPAI"> 
                         <div id= "n26" pid ="n2" url="pages/company/pinpaiqiye.do" text="品牌管理" /> />
                         </sec:authorize>   
                              
                                  
                               <sec:authorize ifAnyGranted="ROLE_QIYE_JXS"> 
                         <div id= "n29" pid ="n2" url="pages/company/qiyemanjxs.do" text="经销商管理" /> />
                         </sec:authorize>   
                         
                          
                            
                            
                            
                             <div id= "n41" pid ="n2"   url="pages/company/Commodity.do" text="--网上商城--" /> />
                    
                          	 
                            <div id= "n42" pid ="n2" url="pages/company/panel.do" text="--板材管理--" /> />
                         
                         
                          <div id= "n43" pid ="n2" url="pages/company/doorstyle.do" text="--门面管理--" /> />
                          
                          <div id= "n44" pid ="n2" url="pages/company/elses.do" text="--其他管理--" /> />
                          
                              
                          <div id= "n45" pid ="n2" url="pages/company/frame.do" text="--边框管理--" /> />
                        
                          <div id= "n46" pid ="n2" url="pages/company/palette.do" text="--色板管理--" /> />
                    
                      <div id= "n47" pid ="n2" url="pages/company/accessories.do" text="-- 配件管理--" /> />
                    
                      <div id= "n48" pid ="n2" url="pages/company/scuttle.do" text="--气窗管理--" /> />
                       
                          
               	</sec:authorize>          
               	
               	
               <sec:authorize ifAnyGranted="ROLE_JXS">           
             <div id= "n3" class ="menu om-corner-top" url ="main.do" text="经销商管理" expanded=false />
                           <sec:authorize ifAnyGranted="ROLE_JXS_COMPANY" >   
                         <div id= "n31" pid ="n3" url="pages/company/jxsManager.do" text="经销商管理" /> />
                               </sec:authorize>
                         <!-- 
                          	 <sec:authorize ifAnyGranted="ROLE_JXS_JIBEN">    
                         <div id= "n33" pid ="n3" url="pages/company/jibenjxs.do" text="基本参数设置" /> />
                         </sec:authorize> 
                          -->
                          	 <sec:authorize ifAnyGranted="ROLE_JXS_BAOJIA">    
                         <div id= "n34" pid ="n3" url="pages/company/baojiajxs.do" text="报价系统设置" /> />
                         </sec:authorize> 
                          	 <sec:authorize ifAnyGranted="ROLE_JXS_DINGDAN">    
                         <div id= "n35" pid ="n3" url="pages/company/dingdanjxs.do" text="订单管理" /> />
                         </sec:authorize> 
                          	 <sec:authorize ifAnyGranted="ROLE_JXS_PINPAI">    
                         <div id= "n36" pid ="n3" url="pages/company/pinpaijxs.do" text="品牌管理" /> />
                       
                         </sec:authorize> 
                           
                    
                          	 
                            
               	</sec:authorize>   
               	 	
               
   
	
	<sec:authorize ifAnyGranted="ROLE_ADMIN">
		<div id="n9" class="menu om-corner-top" url="main.do" style="width:80px" text="系统管理" expanded=false />
		<!-- 
		      <div id= "n91" pid ="n9" width="100%" url="pages/system/organization.do" text="组织机构" /> />
		      
		      <sec:authorize ifAnyGranted="ROLE_DIYSET">
		       </sec:authorize>
		      --> 
			 <div id= "n99" pid ="n9" width="100%" url="pages/system/organization2.do" text="DIY菜单设置" /> />
			
		    <sec:authorize ifAnyGranted="ROLE_USERSET">
		      <div id="n92" pid="n9" url="pages/system/person.do" text="用户管理"/> />
			</sec:authorize>
		
		 <div id="n96" pid="n9" url="pages/system/dataDictionary.do" text="基础数据" /> />
			
			
			<sec:authorize ifAnyGranted="ROLE_ROLESET">
			<div id="n93" pid="n9" url="pages/system/rolesManager.do" text="角色管理" /> />
			</sec:authorize>
		 
			<sec:authorize ifAnyGranted="ROLE_AUTHORSET">
			 <div id="n94" pid="n9" url="pages/system/authorisieskey.do" text="管理" /> />
			</sec:authorize>
			
			<sec:authorize ifAnyGranted="ROLE_AUTHOIZE">
			<div id="n95" pid="n9" url="pages/system/authoize.do" text="授权管理" /> />
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_DATADICT">
			<!-- 
				<div id="n97" pid="n9" url="pages/system/logoupload.do" text=<fmt:message key="usersetup.logoupload" /> />
						 
				<div id="n98" pid="n9" url="pages/system/lhomeupload.do" text=<fmt:message key="usersetup.homeupload"/> /> 
		
			 --> 
			 
           </sec:authorize> 
           <sec:authorize ifAnyGranted="ROLE_PLI">
              <div id= "n97" pid ="n9" url="pages/company/Plicalbum.do" text="电子画册" /> />
                </sec:authorize> 
               
           <sec:authorize ifAnyGranted="ROLE_DIY">
           	  <div id= "n98" pid ="n9" url="pages/company/diyupload.do" text="diy图片上传管理" /> />
              </sec:authorize> 
          
           
	</sec:authorize>
	<sec:authorize ifAnyGranted="ROLE_PASSWORD">
			<!-- 
			<div id="n10"  url="main.do" style="width:80px" text="设置' />" hide="true"/>
			 --> 
		<div id="n101" pid="n10" url="pages/updatePassword/updatePassword.xhtml" text="更改密码"/> hide="true"/>
	</sec:authorize>
	
	
	
	

</div>

</html>