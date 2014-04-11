//拼装门核心
var canvas = {
	demo : null,
	stage : null,
	holder : null,
	id : 0,
	total : 0,
	update : true
};

canvas.init = function() {
	canvas.demo = document.getElementById("demoCanvas");
	canvas.stage = new createjs.Stage(canvas.demo);
	createjs.Ticker.addEventListener("tick", canvas.tick);
	canvas.holder = new createjs.Container();
	canvas.stage.addChild(canvas.holder);

	// data.getCanvasJson();

};

canvas.load = function() {
	canvas.clear();
	$.each(data.canvas, function(i, item) {
		// console.log(item.name,item.img);
		canvas.loadImg(i, item);
	});

};

canvas.loadImg = function(i, item) {
	var image = new Image();
	image.src = 'images/diy/' + item.img;
	image.pid = i;
	// image.layerid = _data.layerid;
	// console.log('id: ',image.id);
	image.type = item.type;
	if (item.x != undefined && item.y != undefined) {
		image.posx = item.x;
		image.posy = item.y;
	} else {
		image.posx = 0;
		image.posy = 0;
	}

	if (item.scale != undefined) {
		image.scale = item.scale;
	} else {
		image.scale = 1;
	}

	// image.holder = _data.holder;
	// image.depth = _data.depth;
	// console.log(item.x,item.y);

	image.onload = function(evt) {
		var image = evt.target;
		// var imgid = evt.target.pid;
		// var imgtype = parseInt(evt.target.type);
		// var holder = evt.target.holder;
		// var color = evt.target.color;
		var bitmap = new createjs.Bitmap(image);
		bitmap.pid = evt.target.pid;
		bitmap.type = parseInt(evt.target.type);
		bitmap.cache(0, 0, image.width, image.height);

		bitmap.x = evt.target.posx;
		bitmap.y = evt.target.posy;
		bitmap.scaleX = bitmap.scaleY = evt.target.scale;

		canvas.holder.addChild(bitmap);
		// console.log(evt.target);

		canvas.id++;
		
		//load completed!
		if (canvas.id >= canvas.total - 1) {
			canvas.fixDepth();
			canvas.updateColors(canvas.toRGB('#4f434b'));
		}
		canvas.update = true;

	};

	image.onerror = function(evt) {
		id++;
		// console.log(evt);

	};
};

canvas.fixDepth = function() {
	canvas.holder.sortChildren(function(x, y) {
		// console.log(x.pid,y.pid);
		var _x = parseInt(x.pid);
		var _y = parseInt(y.pid);
		return _x == _y ? 0 : (_x > _y ? 1 : -1);
	});
};

canvas.updateImages = function(i) {
	
};

canvas.updateColors = function(color) {
	for (var i = 0; i < canvas.holder.getNumChildren(); i++) {
		var obj = canvas.holder.getChildAt(i);
		// console.log(obj.type);
		if (obj.type == 1) {
			canvas.applyEffect(obj, color[0], color[1], color[2]);
		}
	}

};

canvas.applyEffect = function(bmp, r, g, b) {
	if (bmp != null) {
		// console.log(r,g,b);
		var rgbChannelFilter = new createjs.ColorFilter(r / 70, g / 70, b / 70, 1, r * .3, g * .3, b * .3);
		bmp.filters = [rgbChannelFilter];
		bmp.updateCache();

		update = true;

	}
};

canvas.toRGB = function(a)//16进制转换成rgb
{
	var b, c;
	if (a.substr(0, 1) === "#") {
		a = a.substring(1);
	}
	a = a.toLowerCase();
	c = new String(a).split('');
	if (c.length < 3 || c.length > 6)
		return false;
	if (c.length === 3) {
		a = c[0] + c[0] + c[1] + c[1] + c[2] + c[2];
	}
	b = [];
	for ( x = 0; x < 3; x++) {
		b[0] = a.substr(x * 2, 2);
		b[3] = "0123456789abcdef";
		b[1] = b[0].substr(0, 1);
		b[2] = b[0].substr(1, 1);
		b[20 + x] = b[3].indexOf(b[1]) * 16 + b[3].indexOf(b[2]);
	}
	return [b[20], b[21], b[22]];
};

canvas.saveImg = function(){
	canvas.holder.cache(0, 0, 550, 500);
	var imgdata = canvas.holder.getCacheDataURL();
	var b64png = imgdata.substring(22);
	 window.open(imgdata);
};

canvas.clear = function() {
	canvas.holder.removeAllChildren();
	canvas.update = true;

};

canvas.tick = function() {
	if (canvas.update) {
		canvas.update = false;
		canvas.stage.update();
	}
};
