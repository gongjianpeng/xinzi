
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><fmt:message key='datadictionary.title' />
		</title>
		<style type="text/css">
html,body {
	font-size: 14px;
	font-family: tahoma;
	border: 0px;
	padding: 0px;
	margin: 0px;
	height: 100%;
	width: 100%;
	overflow: hidden;
}

.om-grid div.bDiv td {
	border-bottom: 1px solid #fff;
	vertical-align: top;
	white-space: nowrap;
	cursor: pointer;
}

.om-grid div.hDiv td {
	text-align: center;
}

.om-grid img {
	cursor: pointer;
	vertical-align: middle !important;
	margin-top: -5px;
	padding: 0px;
	border: 0px;
}

.om-grid a {
	cursor: pointer;
	vertical-align: middle !important;
	font-weight: normal;
	font-size: 12px;
}

.om-grid div.bDiv td {
	vertical-align: middle !important;
	cursor: pointer;
}

.search {
	background-image: url(../../images/icons/search.gif) !important;
}
</style>
		<link rel="stylesheet" type="text/css"
			href="../../themes/apusic/om-all.css" />
		<link href="../../css/css.css" rel="stylesheet" type="text/css" />
		<script src="../../jquery/js/jquery.min.js"></script>
		<script src="../../ui/om-core.js"></script>
		<script src="../../ui/om-mouse.js"></script>
		<script src="../../ui/om-tree.js"></script>
		<script src="../../ui/om-menu.js"></script>
		<script src="../../ui/om-draggable.js"></script>
		<script src="../../ui/om-position.js"></script>
		<script src="../../ui/om-resizable.js"></script>
		<script src="../../ui/om-dialog.js"></script>
		<script src="../../ui/om-messagebox.js"></script>
		<script src="../../ui/om-messagetip.js"></script>
		<script src="../../ui/om-buttonbar.js"></script>
		<script src="../../ui/om-button.js"></script>
		<script src="../../ui/om-ajaxsubmit.js"></script>
		<script src="../../ui/om-grid.js"></script>
		<script src="../../ui/om-grid-headergroup.js"></script>
		<script src="../../ui/om-validate.js"></script>
		<script src="../../ui/om-combo.js"></script>
		<script src="../../ui/om-calendar.js"></script>
		<script src="../../ui/om-panel.js"></script>
		<script src="../../ui/om-tabs.js"></script>
		<script src="../../ui/om-borderlayout.js"></script>
		<script src="../../ui/om-fileupload.js"></script>
		<script type="text/javascript">
        var gridData;
        $(document).ready(function() {
        
            $('body').omBorderLayout({
				   panels:[
				        {
				       		id:"panel", 
				   	        resizable:false,
				   	        collapsible:false,
				   	        title: decodeURI("${param.windowname}"),
				   	        region:"north",
				   	        height:100
				        },
				        {
				   	        id:"dataDictionaryDiv",
				   	        title:"",
				   	     	header:false,
				   	        region:"center"
				   	    }],
				   	    fit : true,
				   	    spacing:2
		    });
		    
                 
           
                
                
            
        
          
             $('#query').bind('click', function(e) {
              
                var qName=$('#qName').val();
                var qType=$('#qType').val();
                
                if(qCode==""&&qName===""&&qType==""){ //No query criteria to, to display all data
                    $('#dataDictionaryTable').omGrid("setData", 'operateDataDictionary.do');
                }else{ //Query, display the query data
                    $('#dataDictionaryTable').omGrid("setData", 'operateDataDictionary.do?qName='+encodeURI(qName)+'&qType='+encodeURI(qType));
                }
                
            });
            /*********************************************************/
              loadCombo($("select[name='cardType']"),null,true);
	          loadCombo($("select[name='sex']"),null,true);
	           loadCombo($("select[name='country2']"),null,true);
	           loadCombo($("select[name='boli']"),null,true);
	            loadCombo($("select[name='biankuang']"),null,true);
	        loadCombo($("select[name='biankuangtou']"),null,true);
	           loadCombo($("select[name='qichuang']"),null,true);
	           loadCombo($("select[name='seban']"),null,true);
	          loadCombo($("select[name='kaixiang']"),null,true);
	            loadCombo($("select[name='bancai']"),null,true);
	             loadCombo($("select[name='bancaihoudu']"),null,true);
	        
	        $('#make-tab').omTabs({});
	        
	        function loadCombo(selected,id,iscode) {
				var op = '<option ></option>';
				selected.html('').css({width:'140px'});
				$.ajax( {
					type : 'GET',
					url : 'comboData.do',
					async: false,  
					data : {'type':selected.attr('name'),'id':id} ,
					contentType : 'application/json',
					dataType : 'json',
					success : function(data) {
						$.each(data, function(i, item) {
						    if(iscode)value=item.code;
						    else value=item.id;
							op += '<option value=' + value + '>'
							+ item.name + '</option>';
						})
						selected.html(op);
					}
				});
				if(selected.html()=="")selected.html("<option value=''>"+selected.attr('name')+" no set</option>");
			    return selected ;
			};
				/*********************************************************/
            
            
            
            
             $('#combo2').omCombo({
                dataSource : [ {text : 'QC08', value : 'QC08'}, 
                               {text : 'QC09', value : 'QC09'}
                              ]
            });

              $('#boli').omCombo({
            dataSource : [ {text : '普通', value : '普通'}, 
                           {text : '艺术', value : '艺术'},
                           {text : '钢花', value : '钢花'}, 
                           {text : '无', value : '无'}  ]
            });
           

                  /******  001  ******/
      function getHoneRecords() {
            var country = $('#country').omCombo('value');
            if(country == 'yahua'){
                return [ {'text' : '普通', 'value' : 'gd1'},{'text' : '深拉伸', 'value' : 'gd2'},
                 {'text' : '反凸', 'value' : 'gd3'},{'text' : '出头反凸', 'value' : 'gd4'}, 
                       {'text' : '广西加长', 'value' : 'gd5'},{'text' : '小门对开', 'value' : 'gd6'},{'text' : '仿反凸','value' : 'gd7'},
                                          {'text' : '冲孔', 'value' : 'gd8'}
                       ];
            }else if(country == 'America'){
                return [ {'text' : '一类花', 'value' : 'ha11'},{'text' : '一类花', 'value' : 'ha12'},
		                {'text' : '二类花', 'value' : 'ha13'},{'text' : '三类花', 'value' : 'ha14'}, 
		                {'text' : '四类花', 'value' : 'ha15'},{'text' : '五类花', 'value' : 'ha16'},
		                {'text' : '六类花', 'value' : 'ha17'},{'text' : '七类花', 'value' : 'ha18'},
		                {'text' : '精品花', 'value' : 'ha19'},{'text' : '铸吕花', 'value' : 'ha20'},
		                {'text' : '铁艺花 ', 'value' : 'ha21'}
                ];
            }else{
                return [];
            }
        }
     
            $('#country').omCombo({
                dataSource : [ {text : '压花款式', value : 'yahua'}, 
                               {text : '花枝款式', value : 'America'} ],
                onValueChange : function(target, newValue, oldValue, event) {
                   //根据第1个combo的当前值算出第2个combo的值
                   var records = getHoneRecords();
                   //将算出的值设置为第2个combo的数据源
                   $('#province').val('').omCombo('setData', records);
                   //清除城市(岛)输入框的值
                    var country = $('#country').omCombo('value');
                      if(country == 'America'){
                       //  $('#boli').val('').omCombo( 'setData', []);
                      }
                
               }
            });
            $('#province').omCombo({
                onValueChange : function(target, newValue, oldValue, event) {
                    //得到第2个combo的当前值
                   
                }
            });


      /********  002**********/
      
        function getZimenRecords() {
            var zimen = $('#zimen').omCombo('value');
            if(zimen == 'zimen1'){
                return [ {'text' : ' 可选', 'value' : 'gd1'},
                                          {'text' : '配套', 'value' : 'gd8'}
                       ];
            }else if(zimen == 'zimen2'){
                return [ {'text' : ' 可选', 'value' : 'gd1'},
                                          {'text' : '配套', 'value' : 'gd8'}
		                
                ];
          }else{
                return [];
            }
        }
     
            $('#zimen').omCombo({
                dataSource : [ {text : '压花款式', value : 'zimen1'}, 
                               {text : '花枝款式', value : 'zimen2'},
                               {text : '配套', value : 'zimen3'} ],
                onValueChange : function(target, newValue, oldValue, event) {
                   //根据第1个combo的当前值算出第2个combo的值
                   var records = getZimenRecords();
                   //将算出的值设置为第2个combo的数据源
                   $('#zimenlo').val('').omCombo('setData', records);
                   //清除城市(岛)输入框的值
                   
               }
            });
            
             function getZimenloRecords() {
            var zimenlo = $('#zimenlo').omCombo('value');
            if(zimenlo == 'yahua'){
                return [ {'text' : ' 子门1', 'value' : 'gd1'},{'text' : '子门2', 'value' : 'gd2'},
                         {'text' : '子门3', 'value' : 'gd3'}
                       ];
            }else if(zimenlo == 'tieyihua'){
                return [ {'text' : '子门1', 'value' : 'ha11'},{'text' : '子门2', 'value' : 'ha12'},
		                {'text' : '子门3', 'value' : 'ha13'} 
                ];
            }else if(zimenlo == 'huazhi'){
                return [ {'text' : 'Z01', 'value' : 'ha11'},{'text' : 'Z02', 'value' : 'ha12'},
		                {'text' : 'Z03', 'value' : 'ha13'} 
                ];
            }
           else{
                return [];
            }
        }
     
            
            $('#zimenlo').omCombo({
                onValueChange : function(target, newValue, oldValue, event) {
                    //得到第2个combo的当前值
                    var zirecords = getZimenloRecords();
                      $('#zimenloend').val('').omCombo('setData', zirecords);
                }
            });
            
            $('#zimenloend').omCombo({
                onValueChange : function(target, newValue, oldValue, event) {
                    //得到第2个combo的当前值
                    
                }
            });
            
            /****************************004*****************/
            
           
   
         
          $('#biank').omCombo({
                dataSource : [ {'text' : '门头1号', 'value' : 'gdww1' },
                         { 'text' : '门头2号' , 'value' : 'gd822' },
                         {'text' : '门头3号', 'value' : 'gdww1' },
                         {'text' : '门头4号', 'value' : 'gdww1' },
                         {'text' : '门头5号', 'value' : 'gdww1' },
                         {'text' : '门头6号', 'value' : 'gdww1' },
                         {'text' : '门头7号', 'value' : 'gdww1' },
                         {'text' : '门头8号', 'value' : 'gdww1' } ],
            });
            
         $('#bianker').omCombo({
                 dataSource : [ {'text' : '门柱1号', 'value' : 'gdww1' },
                         { 'text' : '门柱2号' , 'value' : 'gd822' },
                         {'text' : '门柱3号', 'value' : 'gdww1' },
                         {'text' : '门柱4号', 'value' : 'gdww1' },
                         {'text' : '门柱5号', 'value' : 'gdww1' },
                         {'text' : '门柱6号', 'value' : 'gdww1' },
                         {'text' : '门柱7号', 'value' : 'gdww1' },
                         {'text' : '门柱8号', 'value' : 'gdww1' } ],
            });
            
            
              $('#biankes').omCombo({
                 dataSource : [  {'text' : ' 门框1号', 'value' : 'gdww1' },
                         { 'text' : '门框2号' , 'value' : 'gd822' },
                         {'text' : '门框3号', 'value' : 'gdww1' },
                         {'text' : '门框4号', 'value' : 'gdww1' },
                         {'text' : '门框5号', 'value' : 'gdww1' },
                         {'text' : '门框6号', 'value' : 'gdww1' },
                         {'text' : '门框7号', 'value' : 'gdww1' },
                         {'text' : '门框8号', 'value' : 'gdww1' } ],
            });
            /******************005******************/

   
              function getSebanRecords() {
            var seban = $('#seban').omCombo('value');
            if(seban == 'f001'){
                return [ {'text' : '准铜1号', 'value' : 'gd1'}, {'text' : '准黑铜', 'value' : 'gd1'}, {'text' : '准紫铜', 'value' : 'gd1'},
                 {'text' : '准红铜', 'value' : 'gd1'}, {'text' : '仿真紫铜', 'value' : 'gd1'}
                       ];
            }else if(seban == 'f002'){
                return [ {'text' : ' 深灰色', 'value' : 'ha11'},{'text' : '纯白色', 'value' : 'ha12'},
		                {'text' : '星际绿', 'value' : 'ha13'},{'text' : '金黄色', 'value' : 'ha14'}, 
		                {'text' : ' 电信蓝', 'value' : 'ha11'},{'text' : '洒红', 'value' : 'ha12'},
		                {'text' : '湖蓝', 'value' : 'ha13'},{'text' : '深咖啡2', 'value' : 'ha14'}, 
                ];
            }else{   return []; } }
     
            $('#seban').omCombo({
                dataSource : [ {text : '仿铜漆', value : 'f001'}, 
                               {text : '氟碳漆', value : 'f002'} ],
                onValueChange : function(target, newValue, oldValue, event) {
                   var serecords = getSebanRecords();
                   $('#sebaner').val('').omCombo('setData', serecords);
                 
                
               }
            });
            $('#sebaner').omCombo({
                onValueChange : function(target, newValue, oldValue, event) {
                    //得到第2个combo的当前值
                   
                }
            });

              /******************006******************/
                 $('#kaixiang').omCombo({
            dataSource : [ {text : '外右', value : 'kx01'}, 
                           {text : '外左', value : 'kx02'},
                           {text : '内右', value : 'kx03'}, 
                           {text : '内左', value : 'kx04'}  ]
            });
              
              
                       /******************007******************/
                           $('#bancai').omCombo({
                       dataSource : [ {text : '铁', value : 'kx01'}, 
                           {text : '锌', value : 'kx02'},
                           {text : '锌合金', value : 'kx03'},
                           {text : '锌铁合金', value : 'kx04'},
                           {text : '镀锌', value : 'kx05'}, 
                           {text : '不锈钢', value : 'kx06'}  ]
            });
                    $('#banmenmian').omCombo({
                       dataSource : [ {text : '0.3', value : 'kx01'}, 
                           {text : '0.4', value : 'kx02'},
                           {text : '0.5', value : 'kx03'},
                           {text : '0.6', value : 'kx04'},
                           {text : '0.7', value : 'kx05'}, 
                           {text : '0.8', value : 'kx06'},
                            {text : '0.9', value : 'kx05'}  ]
            });
             $('#banmenhou').omCombo({
                       dataSource : [ {text : '0.3', value : 'kx01'}, 
                           {text : '0.4', value : 'kx02'},
                           {text : '0.5', value : 'kx03'},
                           {text : '0.6', value : 'kx04'},
                           {text : '0.7', value : 'kx05'}, 
                           {text : '0.8', value : 'kx06'},
                            {text : '0.9', value : 'kx05'}  ]
            });

             /******************008******************/
    
                           $('#qita').omCombo({
                         dataSource : [ {text : '气窗立柱', value : 'kx01'}, 
                           {text : '门面立柱', value : 'kx02'},
                           {text : '立柱到顶', value : 'kx03'}
                            ]
                 });
            /******************009******************/
    
                           $('#peijian').omCombo({
                         dataSource : [ {text : '拉手款式', value : 'kx01'}, 
                           {text : '铰链款式', value : 'kx02'},
                           {text : '锁具', value : 'kx03'},
                           {text : '锁芯', value : 'kx03'},
                           {text : '门铃', value : 'kx03'},
                           {text : '猫眼', value : 'kx03'},
                           {text : '下档', value : 'kx03'}
                            ]
                 });

 /******************010******************/

    $("#select_bg").change(function () {
		        var v = $(this).val();
		        alert("----"+v);
		        if (v.length > 0) {
		            $("img.i-img").attr("src", v)
		            $("#card_bg").val(v);
		        }
		    });
        $('#qichuang').omCombo({
  
                onValueChange : function(target, newValue, oldValue, event) {
                    //得到第2个combo的当前值
                    var qichuang = $('#qichuang').omCombo('value');
                    alert(".."+qichuang);
                    
                    //用Ajax方式从后台取出城市(岛)的数据并赋值给第3个combo
                    $('#qichuang2').val('').omCombo('setData','cascadeCombo.do?qichuang=' + 01);
                }
            });

            
       
        });
    </script>
		<script>
      
     
    
    </script>

	</head>
	<fmt:bundle basename="classpath:messages.messages_dataDictionary" />
	<body style="overflow: hidden">
		<div id="panel">
			<form id="queryForm">
				<table style="width: 100%;">
					<tr>
						<td>
							width品牌：
							<input id="qName">
						</td>
						<td>
							产品编号：
							<input id="qType">
						</td>
						<td>
							门型：
							<input id="qType">
						</td>
						<td>
							尺寸：长
							<input id="qType" style="width: 50px;">
							宽：
							<input id="qType" style="width: 50px;">
						</td>
					</tr>
					<tr>
						<td>
							樘数：
							<input id="qName">
						</td>
						<td>
							门体高：
							<input id="qType">
						</td>
						<td>
							墙体厚度：
							<input id="qType">

						</td>
						<td>
							<input id="query" type="button"
								value="<fmt:message key='datadictionary.search'/>"
								class="button09">
						</td>

					</tr>
				</table>
			</form>
		</div>

		<div id="dataDictionaryDiv" style="width: 'auto'">
			<table style="width: 90%;">
				<tr>
					<td>
						001门面款式
						<select name="country2" ></select>
					</td>
					<td>
						玻&nbsp;&nbsp;璃:
						<select name="boli"></select>
					</td>
					<td>
						底部栏展示:
						<select name="dibu1"></select>
					</td>
				</tr>
				<tr>
					<td>
						边框款式
						<select name="biankuang"></select>
					</td>
					<td>
						二级菜单:
						<select name="biankuangtou"></select>
					</td>
					<td>
						底部栏展示:
						<select name="dibu1"></select>
					</td>
				</tr>
				
				<tr>
					<td>
						气窗款式<input id="qichuang" name="qichuang" />
						<select name="qichuang3" id="qichuang3"></select>   <select name="qichuang2" id="qichuang2"></select>
					</td>
					<td>
						色板:
						<select name="seban"></select>
					</td>
					<td>
						开向:
						<select name="kaixiang"></select>
					</td>
				</tr>
				
				<tr>
					<td>
						板材
						<select name="bancai"></select>
					</td>
					<td>
						板材厚度:
						<select name="bancaihoudu"></select>
					</td>
					
				</tr>
				
					<tr>
					<td>
						边框
						<select name="biankuang"></select>
					</td>
					
				</tr>
				
				

				<tr>
					<td>
						<span>001门面款式：</span>
						<input id="country" name="country" />
					</td>
					<td>
						名&nbsp;&nbsp;称：
						<input id="province" />
					</td>
					<td>
						玻&nbsp;&nbsp;璃:
						<input id="boli" />
					</td>
				</tr>
				<tr>
					<td>
						02子门面款式：
						<input id="zimen">
					</td>
					<td>
						名称款式：
						<input id="zimenlo">
					</td>
					<td>
						002子门面款式：
						<input id="zimenloend">
					</td>
				</tr>
				<tr>
					<td>
						003气窗款式：
						<input id="combo2">
					</td>
				</tr>
				<tr>
					<td>
						004边框样式： 门头
						<input id="biank">
					</td>
					<td>
						门柱：
						<input id="bianker">
					</td>
					<td>
						门框：
						<input id="biankes">
					</td>
				</tr>
				<tr>
					<td>
						005色板样式：
						<input id="seban">
					</td>
					<td>
						色板类型：
						<input id="sebaner">
					</td>
					<td>
						色板类型样式：
						<input id="sebanerys">
					</td>
				</tr>
				<tr>
					<td>
						006 开向：
						<input id="kaixiang">
					</td>
				</tr>
				<tr>
					<td>
						007 板材：
						<input id="bancai">
					</td>
					<td>
						门面厚度：
						<input id="banmenmian">
					</td>
					<td>
						门框厚度：
						<input id="banmenhou">
					</td>
				</tr>
				<tr>
					<td>
						008 其他：
						<input id="qita">
					</td>
				</tr>
				<tr>
					<td>
						009 11配件：
						<input id="peijian">
					</td>
				</tr>







			</table>





			<img src="${card.cardbg }" style="width: 30px; height: 40px">

			<img id="logo" width="100" height="100"
				src="../../img/cardbg/card_bg01.png" />
			<br />
			<input id="uploadButton" type="button" value="设置Logo" />

			<select id="select_bg" style="width: 227px">
				<option selected="" value='${card.cardbg }'>
					请选择会员卡背景图
				</option>
				<option value="../../img/cardbg/card_bg01.png">
					01
				</option>
				<option value="../../img/cardbg/card_bg02.png">
					02
				</option>
				<option value="../../img/cardbg/card_bg03.png">
					03
				</option>
				<option value="../../img/cardbg/card_bg04.png">
					04
				</option>
				<option value="../../img/cardbg/card_bg05.png">
					05
				</option>
				<option value="../../img/cardbg/card_bg06.png">
					06
				</option>
				<option value="../../img/cardbg/card_bg07.png">
					07
				</option>
				<option value="../../img/cardbg/card_bg08.png">
					08
				</option>
				<option value="../../img/cardbg/card_bg09.png">
					09
				</option>
				<option value="../../img/cardbg/card_bg10.png">
					10
				</option>
				<option value="../../img/cardbg/card_bg11.png">
					11
				</option>
				<option value="../../img/cardbg/card_bg12.png">
					12
				</option>
				<option value="../../img/cardbg/card_bg13.png">
					13
				</option>
				<option value="../../img/cardbg/card_bg14.png">
					14
				</option>
				<option value="../../img/cardbg/card_bg15.png">
					15
				</option>
				<option value="../../img/cardbg/card_bg16.png">
					16
				</option>
				<option value="../../img/cardbg/card_bg17.png">
					17
				</option>
				<option value="../../img/cardbg/card_bg18.png">
					18
				</option>
				<option value="../../img/cardbg/card_bg19.png">
					19
				</option>
				<option value="../../img/cardbg/card_bg20.png">
					20
				</option>
				<option value="../../img/cardbg/card_bg21.png">
					21
				</option>
				<option value="../../img/cardbg/card_bg22.png">
					22
				</option>
				<option value="../../img/cardbg/card_bg23.png">
					23
				</option>
			</select>
		</div>

	</body>
</html>