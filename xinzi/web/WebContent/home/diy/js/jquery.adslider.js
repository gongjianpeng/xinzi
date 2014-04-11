jQuery.fn.imageScroller = function(params){
	var p = params || {
		next:"buttonNext",
		prev:"buttonPrev",
		frame:"viewerFrame",
		width:91,
		child:"a",
		auto:true
	}; 
	var _btnNext = $("#"+ p.next);
	var _btnPrev = $("#"+ p.prev);
	var _imgFrame = $("#"+ p.frame);
	var _width = p.width;
	var _child = p.child;
	var _auto = p.auto;
	var _itv;
	
	var orgX = 0;
	// console.log(_imgFrame.css("marginLeft"));
	//>>
	var turnLeft = function(){
		var cx = parseInt(_imgFrame.css("marginLeft"));
		var maskw = parseInt($('#View_list').css("width"));
		//var w = parseInt(_imgFrame.css("width"));
		var w = _imgFrame.find('li').length*_width;
		
		if(cx>maskw-w){
			_btnPrev.unbind("click",turnLeft);
		//if(_auto) autoStop();
		
			_imgFrame.animate( {marginLeft:cx-_width}, 'fast', '', function(){
				//_imgFrame.find(_child+":first").appendTo( _imgFrame );
				//_imgFrame.css("marginLeft",0);
				_btnPrev.bind("click",turnLeft);
				//if(_auto) autoPlay();
			});
		}
		
	};
	
	//<<
	var turnRight = function(){
		var cx = parseInt(_imgFrame.css("marginLeft"));
		
		if(cx<0){
			_btnNext.unbind("click",turnRight);
			var px = parseInt(_imgFrame.css("marginLeft"));
			_imgFrame.animate( {marginLeft:px+_width}, 'fast' ,'', function(){
				//_imgFrame.find(_child+":last").prependTo();
				_btnNext.bind("click",turnRight);
				//if(_auto) autoPlay(); 
			});
		}
		
		//if(_auto) autoStop();
		//_imgFrame.find(_child+":last").prependTo( _imgFrame );
		//_imgFrame.css("marginLeft",-_width);
		
	};
	
	_btnNext.css("cursor","hand").click( turnRight );
	_btnPrev.css("cursor","hand").click( turnLeft );
	
	var autoPlay = function(){
	  _itv = window.setInterval(turnLeft, 3000);
	};
	var autoStop = function(){
		window.clearInterval(_itv);
	};
	//if(_auto)	autoPlay();
};

