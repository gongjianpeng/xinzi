<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../jquery/css/default/om-default.css">
<link rel="stylesheet" type="text/css" href="../css/menus.css" />
<script src="../jquery/js/jquery.min.js"></script>
<script src="../jquery/js/operamasks-ui.min.js"></script>
<!-- view_source_begin -->
<style type="text/css">
  <!--
	html, body { border:0; margin:0; padding:0; }
	.java_icon {
		background: url('../images/icons/post.gif') no-repeat;
	}
	.c_icon {
		background: url('../images/icons/plugin.png') no-repeat;
	}
	.q_icon {
		background: url('../images/icons/q.png') no-repeat;
	}
	.zhi_icon {
		background: url('../images/icons/zhi.png') no-repeat;
	}
	.c_rectification_icon {
		background: url('../images/icons/notes.png') no-repeat;
	}
	.c_plus_plus_icon {
		background: url('../images/icons/seting.png') no-repeat;
	}
	
	.om-panel-body {
		padding: 0px;
	}
	
	.selected {
		font-weight: bold;
		background-color: #0080FF;
		color: white;
	}
	.hover{ 
	    background-color: #ff9900;
	    font-weight: bold;
	    cursor:pointer;
	}
	-->
</style>
<script type="text/javascript">
        var selectedrow ;
        $(document).ready(function(){
        	 $("#accordionId").omAccordion({
                 width : 185
             });
        	 $("td").hover(function (){
                $(this).addClass('hover');
            },function (){
                $(this).removeClass('hover');
               
            });
             $("td").click(function (){
        	    if(selectedrow != null){
        	       selectedrow.removeClass('selected');
        	    }
        	    selectedrow=$(this);
        	    
        	    selectedrow.removeClass('hover').addClass('selected');
        	    
           	   // window.parent.middle.location.href=$(this).attr("url");
           	   var tabFlag = "";
           	   
           	   var parentDivId = this.parentNode.parentNode.parentNode.parentNode.id;
           	   if(parentDivId=="tab1"){
           	   		tabFlag = 'BM';
           	   } else if(parentDivId=="tab2"){
           	   		tabFlag = 'EM';
           	   } else if(parentDivId=="tab3"){
           	   		tabFlag = 'RM';
           	   } else if(parentDivId=="tab4"){
           	   		tabFlag = 'SM';
           	   } else if(parentDivId=="tab5"){
           	   		//tabFlag = 'AE';
           	   } else if(parentDivId=="tab6"){
           	   		//tabFlag = 'FU';
           	   }
           	  
           	   window.parent.middle.addTab($(this).html(),selectedrow.attr("url"));
            });
       });
        	        
       
</script>
</head>
<body>
<!-- view_source_begin -->
<div id="accordionId">
	<ul>
		<li><a href="#tab1" iconCls="java_icon"> <fmt:message key="left.baseDataSet" /></a></li>
		<li><a href="#tab2" iconCls="java_icon">  <fmt:message key="left.EvaluationManagement" /></a></li>
		<li><a href="#tab3" iconCls="c_icon">  <fmt:message key="left.RiskassTest" /></a></li>
		<li><a href="#tab4" iconCls="c_icon">  <fmt:message key="left.DesignValidTest" /></a></li>
		<li><a href="#tab5" iconCls="c_icon">  <fmt:message key="left.effectValidTest" /></a></li>
		<li><a href="#tab6" iconCls="q_icon">  <fmt:message key="left.defectLevelsidentified" /></a></li>
		<li><a href="#tab7" iconCls="zhi_icon">  <fmt:message key="left.internalControldefect" /></a></li>
		<li><a href="#tab8" iconCls="zhi_icon"> <fmt:message key='left.internalevaluaReport' /></a></li>
		<li><a href="#tab9" iconCls="c_plus_plus_icon"> <fmt:message key='left.systemManagement' /></a></li>
	</ul>
	<div id="tab1">
		<table cellspacing="1" cellpadding="5" width="100%">
		    <sec:authorize ifAnyGranted="ROLE_BUSINESS">
			<tr>
				<td url="pages/dataDictionary/businessMode.do"><fmt:message key="left.business" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_TARGETSET">
			<tr>
				<td url="pages/dataDictionary/targetAim.do"><fmt:message key="left.targetset" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_RISKCONTROL">
			<tr>
				<td url="pages/dataDictionary/riskControl.do"><fmt:message key="left.riskControl" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_CONTROLACT">
			<tr>
				<td url="pages/dataDictionary/controlActivity.do"><fmt:message key="left.controlActivity" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_EVAMATIRX">
			<tr>
				<td url="pages/dataDictionary/evaluationMatrix.do"><fmt:message key="left.evaMatirx"/></td>
			</tr>
			</sec:authorize>
		</table>
	</div>
	<div id="tab2">
		<table cellspacing="1" cellpadding="5" width="100%">
		    <sec:authorize ifAnyGranted="ROLE_EVALUATIONPRO">
		    <tr>
				<td url="pages/evaluate/evaluationProject.do"><fmt:message key="left.EvaluationProject" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_EVALUATIONPROAPPROVE">
		     <tr>
				<td url="pages/evaluate/evaluationProjectApprove.do"><fmt:message key="left.EvaluationProjectApproval" /></td>
			</tr>
			</sec:authorize>
			
		</table>
	</div>
	<div id="tab3">
		<table cellspacing="1" cellpadding="5" width="100%">
			
			<sec:authorize ifAnyGranted="ROLE_PLANGENERATION">
			<tr>
				<td url="pages/evaluate/testRiskControlPlan.do"><fmt:message key="left.RiskassTestPlan" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_PLANAUDIT">
			<tr>
				<td url="pages/evaluate/testRiskControlPlanAudit.do"><fmt:message key="left.RiskassTestPlanAudit" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_TESTCONTROL">
			<tr>
				<td url="pages/evaluate/testRiskControl.do"><fmt:message key="left.riskcontrl" /></td>
			</tr>
			</sec:authorize>
			</table>
	</div>
	
	<div id="tab4">
		<table cellspacing="1" cellpadding="5" width="100%">
		<sec:authorize ifAnyGranted="ROLE_TESTCOACTIVITYPLAN">
		    <tr>
				<td url="pages/evaluate/testControlActivityPlan.do"><fmt:message key="left.designeffectiveTestPlans" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_TESTCOACTPLANAUDIT">
			<tr>
				<td url="pages/evaluate/testControlActivityPlanAudit.do"><fmt:message key="left.designeffecttestplanAudit" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_TESTCONACTIVITY">
			<tr>
				<td url="pages/evaluate/testControlActivity.do"><fmt:message key="left.testControlac" /></td>
			</tr>
			</sec:authorize>
			
		</table>
	</div>
	<div id="tab5">
		<table cellspacing="1" cellpadding="5" width="100%">
		<sec:authorize ifAnyGranted="ROLE_TESTEXVAPLAN">
		    <tr>
				<td url="pages/evaluate/testExecutevalidityPlan.do"><fmt:message key="left.ExecutiveeffTestPlans" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_TESTEXVAPLANAUDIT">
			<tr>
				<td url="pages/evaluate/testExecutevalidityPlanAudit.do"><fmt:message key="left.ExcutiveeffTestplansAudit" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_TESTEXVALIDITY">
			<tr>
				<td url="pages/evaluate/testExecutevalidity.do"><fmt:message key="left.validationTests" /></td>
			</tr>
			</sec:authorize>
			</table>
	</div>
	<div id="tab6">
		<table cellspacing="1" cellpadding="5" width="100%">
		<sec:authorize ifAnyGranted="ROLE_VALIRISKCON">
		     <tr>
				<td url="pages/evaluate/validationRiskControl.do"><fmt:message key="left.Riskidentdefectre" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_VACONACTIVITY">
			<tr>
				<td url="pages/evaluate/validationControlActivity.do"><fmt:message key="left.DesignFlawsThat" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_VALIDATIONEVAL">
			<tr>
				<td url="pages/evaluate/validationEvaluation.do"><fmt:message key="left.ExcutivedefectRec" /></td>
			</tr>
			</sec:authorize>			
						
		</table>
	</div>
	<div id="tab7">
		<table cellspacing="1" cellpadding="5" width="100%">
		<sec:authorize ifAnyGranted="ROLE_RISKCONDEFECT">
		     <tr>
				<td url="pages/evaluate/riskControlDefectRecitify.do"><fmt:message key="left.Riskidentdefect" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_CONACTIDEFECT">
			<tr>
				<td url="pages/evaluate/controlActivityDefectRecitify.do"><fmt:message key="left.Defectrectification" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_EVALUATEREPORT">
			<tr>
				<td url="pages/evaluate/evaluationDefectRecitify.do"><fmt:message key="left.ExcutiveRectification" /></td>
			</tr>
			</sec:authorize>			
						
		</table>
	</div>
	
		<div id="tab8" >
		<table cellspacing="1" cellpadding="5" width="100%" align="center">
		<sec:authorize ifAnyGranted="ROLE_REPORT_REPORT">
		<tr>
			<td url="pages/evaluate/evaluationProjectReport.do"><fmt:message key="left.evaluationProjectReport" /></td>
		</tr>
		</sec:authorize>
		
		<sec:authorize ifAnyGranted="ROLE_REPORT_SUREDOC">
		<tr>
			<td url="pages/evaluate/evaluationProjectReportEnsure.do"><fmt:message key="left.evaluationProjectReportEnsure" /></td>
		</tr>
		</sec:authorize>
		<sec:authorize ifAnyGranted="ROLE_REPORT_AUDIT">
		<tr>
			<td url="pages/evaluate/evaluationProjectReportAudit.do"><fmt:message key="left.evaluationProjectReportAudit" /></td>
		</tr>
		</sec:authorize>
		</table>
		</div>
	<div id="tab9" >
		<table cellspacing="1" cellpadding="5" width="100%" align="center">
		    <sec:authorize ifAnyGranted="ROLE_ORGANIZATION">
		    <tr>
				<td width="100%" url="pages/agency/organization.do"><fmt:message key="left.organization" /></td>
			</tr>
			</sec:authorize>
		    <sec:authorize ifAnyGranted="ROLE_USERSET">
			<tr>
				<td url="pages/person/person.xhtml"><fmt:message key="left.userset" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_ROLESET">
			<tr>
				<td url="pages/system/rolesManager.xhtml"><fmt:message key="left.roleset" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_AUTHORSET">
			<tr>
				<td url="pages/author/authorisieskey.xhtml"><fmt:message key="left.authorset" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_AUTHOIZE">
			<tr>
				<td url="pages/person/authoize.xhtml"><fmt:message key="left.authoize" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_PASSWORD">
			<tr>
				<td url="pages/updatePassword/updatePassword.xhtml"><fmt:message key="left.password" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_DATADICT">
			<tr>
				<td url="pages/dataDictionary/dataDictionary.do"><fmt:message key="left.dataDictionary" /></td>
			</tr>
			</sec:authorize>
		</table>
	</div> 
	
	
	<div id="tab9" >
		<table cellspacing="1" cellpadding="5" width="100%" align="center">
		    <sec:authorize ifAnyGranted="ROLE_ORGANIZATION">
		    <tr>
				<td width="100%" url="pages/agency/organization.do"><fmt:message key="left.organization" /></td>
			</tr>
			</sec:authorize>
		    <sec:authorize ifAnyGranted="ROLE_USERSET">
			<tr>
				<td url="pages/person/person.xhtml"><fmt:message key="left.userset" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_ROLESET">
			<tr>
				<td url="pages/system/rolesManager.xhtml"><fmt:message key="left.roleset" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_AUTHORSET">
			<tr>
				<td url="pages/author/authorisieskey.xhtml"><fmt:message key="left.authorset" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_AUTHOIZE">
			<tr>
				<td url="pages/person/authoize.xhtml"><fmt:message key="left.authoize" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_PASSWORD">
			<tr>
				<td url="pages/updatePassword/updatePassword.xhtml"><fmt:message key="left.password" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_DATADICT">
			<tr>
				<td url="pages/dataDictionary/dataDictionary.do"><fmt:message key="left.dataDictionary" /></td>
			</tr>
			</sec:authorize>
		</table>
	</div> 
	
	
</div>
<!-- view_source_end -->
<div id="view-desc"></div>
</body>
</html>