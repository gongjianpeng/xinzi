function changbg(menuID,url)
{
	menuID.style.background="URL("+url+")";
}

function hideAll() {
  for(i=0;i<odiv.length;i++) {
    odiv[i].style.display="none";
  }
}

function showObj(menuID,num) {
  
  if (odiv[num].style.display=="none") {
    hideAll();
    odiv[num].style.display="inline";
	menuID.style.background="URL(../images/zhenxin15.jpg)";
	show_Hide_Menu(odiv[num]);

  }
  else {

    odiv[num].style.display="none";

	menuID.style.background="URL(../images/zhenxin14.jpg)";

  }
  }
  
function change(menuID)
{
    menuID.style.background="URL(../images/zhenxin14.jpg)";
}
function show_Hide_Menu(menuID)
{ 
	  
	  var a = menuID.getElementsByTagName("a");
	   
	   for(var i = 0;i<a.length;i++){
		   
		   a[i].onclick=function(){
			     if(window.parent.parent.center.cols=="185,8,*")
			{
			  
			   window.parent.parent.middle1.menuICON.src="../images/CollapseBtn_2.jpg";
			   window.parent.parent.middle1.menuICON.alt="Show Menu";
				window.parent.parent.center.cols="0,8,*";
			}
			else
			{
				 window.parent.parent.middle1.menuICON.src="../images/CollapseBtn_1.jpg";
				 window.parent.parent.middle1.menuICON.alt="Hidden Menu";
		
				 window.parent.parent.center.cols="185,8,*";
			}
			      
	   }
		  
			
		}
	}