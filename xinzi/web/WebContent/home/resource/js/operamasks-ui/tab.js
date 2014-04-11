function swapTab(obj){
	var tab=obj.parentNode;
	if(typeof tab!="undefined" && tab!=null){
		if(tab.className!="current"){
			var liList=$$("#tabsF li");
			liList.each(function (Element){Element.className="";});
			tab.className="current";
		}
	}
}
