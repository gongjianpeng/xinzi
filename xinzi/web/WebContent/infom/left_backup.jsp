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
		background: url('images/java.png') no-repeat;
	}
	.c_icon {
		background: url('images/c.png') no-repeat;
	}
	
	.c_plus_plus_icon {
		background: url('images/c_plus_plus.png') no-repeat;
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
            }).mouseout(function (){
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
           	   		tabFlag = 'SM';
           	   } else if(parentDivId=="tab4"){
           	   		//tabFlag = 'RP';
           	   		return;
           	   } else if(parentDivId=="tab5"){
           	   		//tabFlag = 'AE';
           	   		return;
           	   } else if(parentDivId=="tab6"){
           	   		//tabFlag = 'FU';
           	   		return;
           	   }
           	   window.parent.middle.addTab($(this).html(),selectedrow.attr("url"),tabFlag);
            });
       });
        	        
       
</script>
</head>
<body>
<!-- view_source_begin -->
<div id="accordionId">
	<ul>
		<li><a href="#tab1" iconCls="c_plus_plus_icon"> <fmt:message key="left.baseDataSet" />(BM)
		</a></li>
		<li><a href="#tab2" iconCls="java_icon"> <fmt:message key="left.evaluateSet" />(EM)
		</a></li>
	<!-- <li><a href="#tab4" iconCls="c_plus_plus_icon"> <fmt:message key='left.rectifyPlan' />(RP)
		</a></li>
		<li><a href="#tab5" iconCls="java_icon"> <fmt:message key='left.analysisEvaluation' />(AE)
		</a></li>
		<li><a href="#tab6" iconCls="c_icon"> <fmt:message key='left.follow-up' />(FU)
		</a></li> -->	
		 <li><a href="#tab3" iconCls="c_icon"> <fmt:message key='left.systemManagement' />(SM)
		</a></li>
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
		    <tr>
				<td url="pages/evaluate/evaluationProject.do">评价项目立项</td>
			</tr>
			<tr>
				<td url="pages/evaluate/evaluationProjectApprove.do">评价项目审批</td>
			</tr>
			<sec:authorize ifAnyGranted="ROLE_PLANGENERATION">
			<tr>
				<td url="pages/evaluate/evaluationPlanGeneration.do"><fmt:message key="left.planGeneration" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_PLANAUDIT">
			<tr>
				<td url="pages/evaluate/evaluationPlanAudit.do"><fmt:message key="left.planAudit" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_EVALUATETEST">
			<tr>
				<td url="pages/evaluate/validationTests.do"><fmt:message key="left.validationTests" /></td>
			</tr>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_EVALUATEREPORT">
			<tr>
				<td url="pages/evaluate/validationEvaluation.do"><fmt:message key="left.validationEvaluation" /></td>
			</tr>
			</sec:authorize>
		</table>
	</div>
	<!--<div id="tab4">
		<table cellspacing="1" cellpadding="5" width="100%" align="center">
			
			<tr>
				<td url="pages/rectifyPlan/provideRecommendMeasures.jsp"><fmt:message key="left.provideRecommendMeasures" /></td>
			</tr>
			
			<tr>
				<td url="pages/rectifyPlan/recommendMeasuresConfirm.jsp"><fmt:message key="left.recommendMeasuresConfirm" /></td>
			</tr>
			
			<tr>
				<td url="pages/rectifyPlan/reportRecommendAchievements.jsp"><fmt:message key="left.reportRecommendAchievements" /></td>
			</tr>
			
		</table>
	</div>
	<div id="tab5">
		<table cellspacing="1" cellpadding="5" width="100%" align="center">
			<tr>
				<td url="pages/analysisEvaluation/summaryEvaluationReport.jsp"><fmt:message key="left.summaryEvaluationReport" /></td>
			</tr>
			<tr>
				<td url="pages/analysisEvaluation/analysisEvaluationResults.jsp"><fmt:message key="left.analysisEvaluationResults" /></td>
			</tr>
		</table>
	</div>
	<div id="tab6">
		<table cellspacing="1" cellpadding="5" width="100%" align="center">
			<tr>
				<td url="pages/followUp/internalControlNotice.jsp"><fmt:message key="left.internalControlNotice" /></td>
			</tr>
		</table>
	</div>-->
	<div id="tab3" >
		<table cellspacing="1" cellpadding="5" width="100%" align="center">
		   <sec:authorize ifAnyGranted="ROLE_ORGANIZATION">
		    <tr>
				<td width="100%" url="pages/agency/organization.xhtml"><fmt:message key="left.organization" /></td>
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