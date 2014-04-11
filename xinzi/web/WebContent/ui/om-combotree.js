/*
 * $Id: om-combo.js,v 1.175 2012/06/26 08:39:27  Exp $
 * operamasks-ui omCombotree @VERSION
 *
 * Copyright 2011, AUTHORS.txt (http://ui.operamasks.org/about)
 * Dual licensed under the MIT or LGPL Version 2 licenses.
 * http://ui.operamasks.org/license
 *
 * http://ui.operamasks.org/docs/
 *
 * Depends:
 *  om-core.js
 *  om-combo.js
 *  om-omtree.js
 */
;(function($) {
    
    // Array.prototype.indexOf is added in JavaScript v1.6.
    // IE8 only support JavaScript v1.3. So added it to make this component support IE.
    if(!Array.prototype.indexOf){
        Array.prototype.indexOf=function(item){
            var len=this.length;
            for(var i=0;i<len;i++){
                if(this[i]===item){
                    return i;
                }
            }
            return -1;
        };
    }
	/**
     * @name omComboTree
     * @class 下拉树输入框组件。类似于html中的select，但是可以输入，可以过滤，可以使用远程数据。<br/><br/>
	 * 继承：omCombo组件，引入omtree组件,
     * <b>特点：</b><br/>
     * <ol>
     *      <li>可以使用本地数据源，也可以使用远程数据源dataSource:必须符合omTree的格式</li>
	 *（1）、简单模式，要在tree中设置simpleDataModel: true
			 var data2 = [{id:"n1",text:"品牌",expanded:true},
                     {id:"n2",text:"运营商",expanded:true},
                     {id:"n11",pid:"n1",text:"三星"},
			         {id:"n12",pid:"n1",text:"诺基亚"},
			         {id:"n13",pid:"n1",text:"摩托罗拉"},
			         {id:"n14",pid:"n1",text:"索尼"},
			         {id:"n21",pid:"n2",text:"移动"},
			         {id:"n22",pid:"n2",text:"联通"},
			         {id:"n23",pid:"n2",text:"电信"}];
       （2）、树形数据（带有children).
        (3)、其它功能与omCombo一样
     * </ol>
     * 
     * <b>示例：</b><br/>
     * <pre>
     * &lt;script type="text/javascript" >
     * $(document).ready(function() {
     *     $('#combo').omComboTree({
     *         dataSource:data2
     *          valueField : 'id', 
				multi:true,//树形多选在这里控制
				tree:{
	   			   simpleDataModel: true,//简单数据格式
					treeLeafOnly:true//只选择叶子
					},
               	value : 'n12'
     *     });
     * });
     * &lt;/script>
     * 
     * &lt;input id="combo"/>
     * </pre>
     * 
     * @constructor
     * @description 构造函数. 
     * @param p 标准config对象：{}
     */
	$.omWidget('om.omComboTree',$.om.omCombo, {
	    options: 
	       /** @lends omCombo#*/
	       {
			  /*
			  控制下拦树的参数合并在此中

			  simpleDataModel: true,
					treeLeafOnly:true
			    
			  */
			  tree:null
              
        },
       
        //private
        _bindEvent:function(){
            var self = this, options = self.options,input = self.textInput, 
            valueEl = self.element, dropList = self.dropList,
            expandTrigger = self.expandTrigger, emptyText = options.emptyText;
            var isFocus = false, span = input.parent('span');
            span.mouseenter(function(){   
               if(!options.disabled){
                   span.addClass("om-state-hover");
               }
            }).mouseleave(function(){      
                span.removeClass("om-state-hover");
            }).mousedown(function(e){
                e.stopPropagation(); //document的mousedown会隐藏下拉框，这里要阻止冒泡
            });
            input.focus(function(){
                if(isFocus) 
                    return;
                isFocus = true;
				//qing1000,2012-8-18,检查是否有onBeforeOpen事件处理
				 if(self._trigger("onBeforeOpen")== false) return false;
				//end qing1000
                $('.om-droplist').hide(); //hide all other dropLists
                span.addClass('om-state-focus');
                //input.addClass('om-span-field-focus');
                //input.parent('span').
                //expandTrigger.addClass('om-state-hover');
                self._refeshEmptyText(emptyText);
                if (!self.dataHasLoaded) {
                    if(!expandTrigger.hasClass('om-loading')){
                        self._toggleLoading('add');
                        if (typeof(options.dataSource) == 'object') {
                            self._loadData(options.dataSource);
                            self._toggleLoading('remove');
                        } else if (typeof(options.dataSource) == 'string') {
                            self._ajaxLoad(options.dataSource);
                        } else {
                            //neither records nor remote url was found
                            self.dataHasLoaded = true;
                            self._toggleLoading('remove');
                        }
                    }
                }
                if (!options.disabled && !options.readOnly) {
					if (options.tree)
					{
						self._showDropTree();
					}else{
						self._showDropList();
					}
                }
            }).blur(function(e){
                isFocus = false;
                span.removeClass('om-state-focus');
                input.removeClass('om-combo-focus');
                //expandTrigger.removeClass('om-trigger-hover');
                if (!options.disabled && !options.readOnly && !options.multi) {
                    if (self.hasManualInput) {
                        //如果有手工输入过值，在blur时检查是否是合法的值，如果不是要清除不合法的输入并还原成输入前的值
                        self.hasManualInput = false;
                        var text = input.val();
                        if (text !== '') {
                            var allInputText = $.data(valueEl, 'allInputText');
                            var allValues = $.data(valueEl, 'allValues');
                            var index = allInputText.indexOf(text);
                            if (index > -1) {
                                self._setValue(allValues[index]);
                            } else if(!options.forceSelection){ //如果输入的值在data里面不存在，则设置key和vlue为同一输入的值
                                valueEl.val(input.val());
                            }else{
                            	var value = valueEl.val();
                                index = allValues.indexOf(value);
                                if (index > -1) {
                                    input.val(allInputText[index]);
                                } else {
                                    input.val('');
                                }
                            }
                        }else{
                        	valueEl.val('');
                    	}
                    }
                    self._refeshEmptyText(emptyText);
                }
            }).keyup(function(e){
                var key = e.keyCode,
                    value = $.om.keyCode;
                switch (key) {
                    case value.DOWN:
                        self._selectNext();
                        break;
                    case value.UP: 
                        self._selectPrev();
                        break;
                    case value.ENTER: 
                        self._backfill(self.dropList.find('.om-state-hover'));
                        break;
                    case value.ESCAPE: 
                        dropList.hide();
                        break;
                    case value.TAB:
                        //only trigger the blur event
                        break;
                    default:
                        //fiter功能
                        self.hasManualInput = true;
                        if (!options.disabled && !options.readOnly && options.editable && options.autoFilter) {
                            if (window._omcomboFilterTimer) {
                                clearTimeout(window._omcomboFilterTimer);
                            }
                            window._omcomboFilterTimer = setTimeout(function(){
                                if($(document).attr('activeElement').id == input.attr('id')){//当焦点在当前输入框的时候才显示下拉框，否则隐藏
                                    dropList.show();
                                }
                                self._doFilter(input);
                            }, options.filterDelay);
                        }
               }
            });
            dropList.mousedown(function(e){
                e.stopPropagation(); //document的mousedown会隐藏下拉框，这里要阻止冒泡
            });
            expandTrigger.click(function(){
				//qing1000
				//打开下拉框前事件，可以通过return false来阻止继续操作，利用这个参数可以用来调用其他函数，比如打开一个新窗口来选择值
				if(self._trigger("onBeforeOpen")== false) return false;
				//end qing1000
                !expandTrigger.hasClass('om-loading') && input.focus();
            }).mousedown(function(){
                !expandTrigger.hasClass('om-loading') && span.addClass('om-state-active');
            }).mouseup(function(){
                !expandTrigger.hasClass('om-loading') && span.removeClass('om-state-active');
            });
            $(document).bind('mousedown.omCombo',this.globalEvent=function(){
                dropList.hide();
            });
        },
		//展开所有父节点
		_expandParent:function(target){
			var self = this;
			var parentNode=self.tree.omTree('getParent',target);
			if(parentNode){
				self.tree.omTree('expand',parentNode);
				self._expandParent(parentNode);
			}
		},
        _showDropTree:function(){
        	var self = this, options = self.options, 
        	    inputEl = self.textInput, valueInput = self.element,
          	    dropList = self.dropList.scrollTop(0).css('height','auto'),
         	    valuedItem,
	       	    nowValue = valueInput.val();
			if (nowValue !== undefined && nowValue !== '') {
                var allValues = $.data(valueInput, 'allValues');
                if (options.multi) {
                    var selectedValues = nowValue.split(options.multiSeparator);
                    for (var i=selectedValues.length-1; i>=0; i--) {
     				//for (var i=0; i<selectedValues.length; i++) {
                        var target=self.tree.omTree("findNode",options.valueField,selectedValues[i]);
							self.tree.omTree('check',target);
							self._expandParent(target);//展开选择节点；	
                    }
					self.tree.omTree('select',target);
                    valueItem = selectedValues[0];
                } else {
					var target=self.tree.omTree("findNode",options.valueField,nowValue);
						self.tree.omTree('select',target);
						self._expandParent(target);//展开选择节点；	
                }
            }else{
            	self.tree.omTree('checkAll',false);
            	var selectedNode=self.tree.omTree('getSelected');
            	if(selectedNode){
            		self.tree.omTree('unselect',selectedNode);
            	}
            }
            var dropListContainer = dropList.parent(), span = inputEl.parent();
            if (!options.listAutoWidth) {
                dropListContainer.width(span.outerWidth());
            }else{
            	if($.browser.msie&&($.browser.version == "7.0")&&!$.support.style){
            		dropListContainer.width(dropList.show().outerWidth());
            	}else{
            		dropListContainer.width(dropList.outerWidth());
            	}
            }
            if (options.listMaxHeight != 'auto' && dropList.show().height() > options.listMaxHeight) {
                dropList.height(options.listMaxHeight).css('overflow-y','auto');
            }
            var inputPos = span.offset();
            dropListContainer.css({
                'left': inputPos.left,
                'top': inputPos.top + span.outerHeight()
            });
            self.tree.omTree('expandAll'); //显示下拉框时展开树 hgs add
            dropList.show();
            if (valuedItem) { //自动滚动滚动条到高亮的行
                dropList.scrollTop($(valuedItem).offset().top - dropList.offset().top);
            }
        },
		
       	_transformToList: function(data,r,field) {
			   function getType(o)
				{
			        var _t;
			        return ((_t = typeof(o)) == "object" ? o==null && "null" || Object.prototype.toString.call(o).slice(8,-1):_t).toLowerCase();
			    }
			    function arrayCopy(destination,source)
				{
			        for(var p in source)
			        {
						if(getType(source[p])=="array"||getType(source[p])=="object")
				            {
								destination[p]=getType(source[p])=="array"?[]:{};
				                arguments.callee(destination[p],source[p]);
						}else{
							 destination[p]=source[p];
						}
					 }
				}//复制数据组
			var self = this;
			var i,l,tmpData=[],childData=[];
			if (!data) return [];
			arrayCopy(tmpData,data);
			for (i=0, l=tmpData.length; i<l; i++) {
				if (tmpData[i].children)
				{
					childData=tmpData[i].children;
					tmpData[i].children=[];
					r.push(tmpData[i][field]);
					self._transformToList(childData,r,field);
				
				} else {
					r.push(tmpData[i][field]);
				}
			}
			return r;
		},
        _loadData:function(records){
            var self = this,
				options = this.options,
                valueEl = this.element;
            options.dataSource = records;
            this.dataHasLoaded = true;
            //build all inputText
            var inputField = options.inputField;
            var allInputText = [];
            if (typeof inputField === 'string') {
				 if(options.tree){
					allInputText=self._transformToList(records,allInputText,inputField);
				 }else{
		            $(records).each(function(){
			            allInputText.push(this[inputField]);
				    });
				 }
            } else {
                $(records).each(function(index){
                    allInputText.push(inputField(this, index));
                });
            }
            $.data(valueEl, 'allInputText', allInputText);
            //build all value
            var valueField = options.valueField;
            var allValues = [];
            if (typeof valueField === 'string') {
                if(options.tree){
					allValues=self._transformToList(records,allValues,valueField);
					
				}else{
					$(records).each(function(){
		                allValues.push('' + this[valueField]);
	                });
				}
            } else {
                $(records).each(function(index){
                    allValues.push('' + valueField(this, index));
                });
            }
            $.data(valueEl, 'allValues', allValues);
            //build dropList
            var dropList = this.dropList.empty();
            if (options.listProvider) {
                var selectableOptions = options.listProvider(dropList, records);
                if (selectableOptions) {
                    selectableOptions.each(function(){
                        $(this).addClass('om-combo-list-row');
                    });
                }
            }else if(options.tree){//树形

				 options.tree.dataSource=records;
				 if (options.multi)//多选择
				 {
					 options.tree.showCheckbox=true;
					 options.tree.onCheck=function(node,event){//单选择
						 var nodes=self.tree.omTree('getChecked',true);
						 var value = [];
		                 var text = [];
				         $(nodes).each(function (i, node)
						   {
							 if (options.tree.treeLeafOnly && node.children) return;
						
							 if (options.tree.classes && options.tree.classes != node.classes) return;
								value.push(node[options.valueField]);
								text.push(node[options.optionField]);
							});
							
						   self._setValue(value.join(options.multi ? options.multiSeparator : ''));
						};
					
				 }else{
					  options.tree.onSelect=function(node,event){//单选择
						 if (options.tree.treeLeafOnly && node.children) return;
							 self._setValue(node[options.valueField]);
							 dropList.hide();	   
						};
				 }
				 self.tree=$('<ul></ul>');
				 self.tree.appendTo(dropList);
				 self.tree.omTree(options.tree);
				 
			}else {
                var optionField = options.optionField;
                var innerHtml = '';
                var self = this;
                if (typeof optionField === 'string') {
                    $(records).each(function(index){
                        innerHtml += self._wrapText(this[options.optionField]);
                    });
                } else {
                    $(records).each(function(index){
                        innerHtml += self._wrapText(options.optionField(this, index));
                    });
                }
                if (innerHtml) {
                    $(innerHtml).appendTo(dropList);
                    dropList.show().css('height','auto');
                    if (options.listMaxHeight != 'auto' && dropList.height() > options.listMaxHeight) {
                        dropList.height(options.listMaxHeight).css('overflow-y','auto');
                    }
                    dropList.hide();
                    if(valueEl.parent().hasClass('om-state-hover')){
                        self._showDropList();
                    }
                }
            }
           
            if (options.value) {
                this._setValue('' + options.value);
            }
            this._bindEventsToList();
        }
      
	});
})(jQuery);