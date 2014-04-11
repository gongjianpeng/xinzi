<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
var data2 = [{id:"n1",text:"<fmt:message key='left.baseDataSet'/>",expanded:false},
     				{id:"n11",pid:"n1",text:"<fmt:message key='left.business'/>" ,url:"pages/dataDictionary/businessMode.do"},
		         	{id:"n12",pid:"n1",text:"<fmt:message key='left.targetset' />",url:"pages/dataDictionary/targetAim.do"},
		         	{id:"n13",pid:"n1",text:"<fmt:message key='left.riskControl' />",url:"pages/dataDictionary/riskControl.do"},
		         	{id:"n14",pid:"n1",text:"<fmt:message key='left.controlActivity'/>",url:"pages/dataDictionary/controlActivity.do"},
		         	{id:"n15",pid:"n1",text:"<fmt:message key='left.evaMatirx'/>",url:"pages/dataDictionary/evaluationMatrix.do"},
            {id:"n2",text:"<fmt:message key='left.EvaluationManagement'/>",expanded:false},
                     {id:"n21",pid:"n2",text:"<fmt:message key='left.EvaluationProject'/>",url:"pages/evaluate/evaluationProject.do"},
	        		 {id:"n22",pid:"n2",text:"<fmt:message key='left.EvaluationProjectApproval' />",url:"pages/evaluate/evaluationProjectApprove.do"},
            {id:"n3",text:"<fmt:message key='left.RiskassTest'/>",expanded:false},
                  	{id:"n31",pid:"n3",text:"<fmt:message key='left.RiskassTestPlan' />",url:"pages/evaluate/testRiskControlPlan.do"},
			        {id:"n32",pid:"n3",text:"<fmt:message key='left.RiskassTestPlanAudit' />",url:"pages/evaluate/testRiskControlPlanAudit.do"},
			        {id:"n33",pid:"n3",text:"<fmt:message key='left.riskcontrl' />",url:"pages/evaluate/testRiskControl.do"},
            {id:"n4",text:"<fmt:message key='left.DesignValidTest' />",expanded:false},
                 	{id:"n41",pid:"n4",text:"<fmt:message key='left.designeffectiveTestPlans' />",url:"pages/evaluate/testControlActivityPlan.do"},
			        {id:"n42",pid:"n4",text:"<fmt:message key='left.designeffecttestplanAudit' />",url:"pages/evaluate/testControlActivityPlanAudit.do"},
			        {id:"n43",pid:"n4",text:"<fmt:message key='left.testControlac' />",url:"pages/evaluate/testControlActivity.do"},
            {id:"n5",text:"<fmt:message key='left.effectValidTest' />",expanded:false},
                  	{id:"n51",pid:"n5",text:"<fmt:message key='left.ExecutiveeffTestPlans' />",url:"pages/evaluate/testExecutevalidityPlan.do"},
			        {id:"n52",pid:"n5",text:"<fmt:message key='left.ExcutiveeffTestplansAudit' />",url:"pages/evaluate/testExecutevalidityPlanAudit.do"},
			        {id:"n53",pid:"n5",text:"<fmt:message key='left.validationTests' />",url:"pages/evaluate/testExecutevalidity.do"},
            {id:"n6",text:"<fmt:message key='left.defectLevelsidentified' />",expanded:false},
                  	{id:"n61",pid:"n6",text:"<fmt:message key='left.Riskidentdefectre' />",url:"pages/evaluate/validationRiskControl.do"},
			        {id:"n62",pid:"n6",text:"<fmt:message key='left.DesignFlawsThat' />",url:"pages/evaluate/validationControlActivity.do"},
			        {id:"n63",pid:"n6",text:"<fmt:message key='left.ExcutivedefectRec' />",url:"pages/evaluate/validationEvaluation.do"},
            {id:"n7",text:"<fmt:message key='left.internalControldefect' />",expanded:false},
                  	{id:"n71",pid:"n7",text:"<fmt:message key='left.Riskidentdefect' />",url:"pages/evaluate/riskControlDefectRecitify.do"},
			        {id:"n72",pid:"n7",text:"<fmt:message key='left.Defectrectification' />",url:"pages/evaluate/controlActivityDefectRecitify.do"},
			        {id:"n73",pid:"n7",text:"<fmt:message key='left.ExcutiveRectification' />",url:"pages/evaluate/evaluationDefectRecitify.do"},
            {id:"n8",text:"<fmt:message key='left.internalevaluaReport' />",expanded:false},
                  	{id:"n81",pid:"n8",text:"<fmt:message key='left.evaluationProjectReport' />",url:"pages/evaluate/evaluationProjectReport.do"},
			        {id:"n82",pid:"n8",text:"<fmt:message key='left.evaluationProjectReportEnsure' />",url:"pages/evaluate/evaluationProjectReportEnsure.do"},
			        {id:"n83",pid:"n8",text:"<fmt:message key='left.evaluationProjectReportAudit' />",url:"pages/evaluate/evaluationProjectReportAudit.do"},
            {id:"n9",text:"<fmt:message key='left.systemManagement' />",expanded:false},
                  	{id:"n91",pid:"n9",text:"<fmt:message key='left.organization'/>",url:"pages/agency/organization.do"},
			        {id:"n92",pid:"n9",text:"<fmt:message key='left.userset' />",url:"pages/person/person.xhtml"},
			        {id:"n93",pid:"n9",text:"<fmt:message key='left.roleset' />",url:"pages/system/rolesManager.xhtml"},
		        	{id:"n94",pid:"n9",text:"<fmt:message key='left.authorset' />",url:"pages/author/authorisieskey.xhtml"},
			        {id:"n95",pid:"n9",text:"<fmt:message key='left.authoize' />",url:"pages/person/authoize.xhtml"},
			        {id:"n96",pid:"n9",text:"<fmt:message key='left.password' />",url:"pages/updatePassword/updatePassword.xhtml"},
			        {id:"n97",pid:"n9",text:"<fmt:message key='left.dataDictionary' />",url:"pages/dataDictionary/dataDictionary.do"}
         ];
        function setNavTreeData(data,isroot){
            if(isroot){
	            $("#menuTree").omTree({
		                dataSource : data,
		                simpleDataModel: true,
		                onSelect:function(node){
		                   if(node.url){
		                   	  // window.parent.middle.addTab(node.text,node.url);
		                   	  window.parent.document.getElementById('middle').src=node.url;
		                   }
		                }
	            });
        	}else{
        		$("#menuTree").omTree("setData",data).omTree("refresh");
        	}
        }
    </script>
<body >
<div id="menudata" style="display:none">
	<div id="n1" text="<fmt:message key='left.baseDataSet'/>" expanded=false />
	<div id="n11" pid="n1" text="<fmt:message key='left.business'/>"
		url="pages/dataDictionary/businessMode.do" />
	<div id="n12" pid="n1" text="<fmt:message key='left.targetset' />"
		url="pages/dataDictionary/targetAim.do" />
	<div id="n13" pid="n1" text="<fmt:message key='left.riskControl' />"
		url="pages/dataDictionary/riskControl.do" />
	<div id="n14" pid="n1" text="<fmt:message key='left.controlActivity'/>"
		url="pages/dataDictionary/controlActivity.do" />
	<div id="n15" pid="n1" text="<fmt:message key='left.evaMatirx'/>"
		url="pages/dataDictionary/evaluationMatrix.do" />
	<div id="n2" text="<fmt:message key='left.EvaluationManagement'/>" expanded=false />
	<div id="n21" pid="n2" text="<fmt:message key='left.EvaluationProject'/>"
		url="pages/evaluate/evaluationProject.do" />
	<div id="n22" pid="n2" text="<fmt:message key='left.EvaluationProjectApproval' />"
		url="pages/evaluate/evaluationProjectApprove.do" />
	<div id="n3" text="<fmt:message key='left.RiskassTest'/>" expanded=false />
	<div id="n31" pid="n3" text="<fmt:message key='left.RiskassTestPlan' />"
		url="pages/evaluate/testRiskControlPlan.do" />
	<div id="n32" pid="n3" text="<fmt:message key='left.RiskassTestPlanAudit' />"
		url="pages/evaluate/testRiskControlPlanAudit.do" />
	<div id="n33" pid="n3" text="<fmt:message key='left.riskcontrl' />"
		url="pages/evaluate/testRiskControl.do" />
	<div id="n4" text="<fmt:message key='left.DesignValidTest' />" expanded=false />
	<div id="n41" pid="n4" text="<fmt:message key='left.designeffectiveTestPlans' />"
		url="pages/evaluate/testControlActivityPlan.do" />
	<div id="n42" pid="n4" text="<fmt:message key='left.designeffecttestplanAudit' />"
		url="pages/evaluate/testControlActivityPlanAudit.do" />
	<div id="n43" pid="n4" text="<fmt:message key='left.testControlac' />"
		url="pages/evaluate/testControlActivity.do" />
	<div id="n5" text="<fmt:message key='left.effectValidTest' />" expanded=false />
	<div id="n51" pid="n5" text="<fmt:message key='left.ExecutiveeffTestPlans' />"
		url="pages/evaluate/testExecutevalidityPlan.do" />
	<div id="n52" pid="n5" text="<fmt:message key='left.ExcutiveeffTestplansAudit' />"
		url="pages/evaluate/testExecutevalidityPlanAudit.do" />
	<div id="n53" pid="n5" text="<fmt:message key='left.validationTests' />"
		url="pages/evaluate/testExecutevalidity.do" />
	<div id="n6" text="<fmt:message key='left.defectLevelsidentified' />" expanded=false />
	<div id="n61" pid="n6" text="<fmt:message key='left.Riskidentdefectre' />"
		url="pages/evaluate/validationRiskControl.do" />
	<div id="n62" pid="n6" text="<fmt:message key='left.DesignFlawsThat' />"
		url="pages/evaluate/validationControlActivity.do" />
	<div id="n63" pid="n6" text="<fmt:message key='left.ExcutivedefectRec' />"
		url="pages/evaluate/validationEvaluation.do" />
	<div id="n7" text="<fmt:message key='left.internalControldefect' />" expanded=false />
	<div id="n71" pid="n7" text="<fmt:message key='left.Riskidentdefect' />"
		url="pages/evaluate/riskControlDefectRecitify.do" />
	<div id="n72" pid="n7" text="<fmt:message key='left.Defectrectification' />"
		url="pages/evaluate/controlActivityDefectRecitify.do" />
	<div id="n73" pid="n7" text="<fmt:message key='left.ExcutiveRectification' />"
		url="pages/evaluate/evaluationDefectRecitify.do" />
	<div id="n8" text="<fmt:message key='left.internalevaluaReport' />" expanded=false />
	<div id="n81" pid="n8" text="<fmt:message key='left.evaluationProjectReport' />"
		url="pages/evaluate/evaluationProjectReport.do" />
	<div id="n82" pid="n8" text="<fmt:message key='left.evaluationProjectReportEnsure' />"
		url="pages/evaluate/evaluationProjectReportEnsure.do" />
	<div id="n83" pid="n8" text="<fmt:message key='left.evaluationProjectReportAudit' />"
		url="pages/evaluate/evaluationProjectReportAudit.do" />
	<div id="n9" text="<fmt:message key='left.systemManagement' />" expanded=false />
	<div id="n91" pid="n9" text="<fmt:message key='left.organization'/>"
		url="pages/agency/organization.do" />
	<div id="n92" pid="n9" text="<fmt:message key='left.userset' />" url="pages/person/person.xhtml" />
	<div id="n93" pid="n9" text="<fmt:message key='left.roleset' />"
		url="pages/system/rolesManager.xhtml" />
	<div id="n94" pid="n9" text="<fmt:message key='left.authorset' />"
		url="pages/author/authorisieskey.xhtml" />
	<div id="n95" pid="n9" text="<fmt:message key='left.authoize' />" url="pages/person/authoize.xhtml" />
	<div id="n96" pid="n9" text="<fmt:message key='left.password' />"
		url="pages/updatePassword/updatePassword.xhtml" />
	<div id="n97" pid="n9" text="<fmt:message key='left.dataDictionary' />"
		url="pages/dataDictionary/dataDictionary.do" />
</div>

</body>
</html>