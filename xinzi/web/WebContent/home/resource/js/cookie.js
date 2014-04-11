

//创建Cookie (cookie名,子键名,值)  子键名为空时value表示Cookie的值， 子键存在时，value为子键值。
//setCookie('test','u1','1')  1、存在name为'test'的Cookie，该方法做创建或修改name为u1的子键，子键值为1。 
//2、不存在name为'test'的Cookie，则创建Cookie再执行1、
//setCookie('test','u1') 创建或修改一个子键。有则修改无则创建。 该子键不存在值。
//setCookie('test',null,'c1=1&c2=2&c3&c4')  直接创建或修改Cookie的值， 实际上每个子键用‘&’隔开。子键名个子键值用‘=’隔开，
//没有‘=’表示该子键没有值，只有子键名
        function setCookie(name,key,value) {
            var Days = 2;
            var exp = new Date();
            exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
            if (key == null || key == "") 
            {
                document.cookie = name + "=" + encodeURI(value) + ";expires=" + exp.toGMTString()+";path=/";
            }
            else 
            {
                var nameValue = getCookie(name);
                if (nameValue==null || nameValue == "") 
                {
                    if(value!=null && value!= "")
                        document.cookie = name + "=" + key + "=" + encodeURI(value) + ";expires=" + exp.toGMTString() + ";path=/";
                    else
                        document.cookie = name + "=" + key + ";expires=" + exp.toGMTString() + ";path=/";
                }
                else 
                {
                        var keyValue = getCookie(name, key);
                        if (keyValue!=null && keyValue != ""  ) 
                        {
                            if(value!= "" && value!=null)
                                nameValue = nameValue.replace(keyValue, key + "=" +encodeURI ( value));
                            else
                                nameValue = nameValue.replace(keyValue, key);                      
                            document.cookie = name + "=" + nameValue + ";expires=" + exp.toGMTString() + ";path=/";
                        }
                        else 
                        {   
                            if(value!=null && value!= "")                                       
                                document.cookie = name + "=" + nameValue + "&" + key + "=" + encodeURI(value) + ";expires=" + exp.toGMTString() + ";path=/";
                            else
                                document.cookie = name + "=" + nameValue + "&" + key + ";expires=" + exp.toGMTString() + ";path=/";
                        }
                } 
            }
            getCookie(name);
        }
        
        //读取cookies     (cookie名，子键名) 子键名为空的话返回该cookie值，子键存在的话返回该子键的值
        function getCookie(name,key) {
            var nameValue = "";
            var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
            if (arr = document.cookie.match(reg)) {
                nameValue = decodeURI(arr[2]);
            }
            if (key != null && key != "") {
                reg = new RegExp("([&]?)("+key+"[=]?[^&]*)([&]?)");
                if (arr = nameValue.match(reg)) {
                    //alert(decodeURI(arr[2]));
                    return decodeURI(arr[2]);  
                }
                else return "";
            }
            else 
            {
                //document.getElementById("write").innerText=nameValue;//用于显示测试结果
                return nameValue;
            }
        }
        
        //删除cookies     (cookie名，子键名)  子键名为空的话将删除cookie。反之，删除该子键。
        function delCookie(name,key){
            if(key != null && key != "")
            {//删除子键
                var nameValue = "";
                var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
                if (arr = document.cookie.match(reg)) 
                {
                    nameValue = decodeURI(arr[2]);
                }
                if (key != null && key != "") 
                {
                    reg = new RegExp("([&]?)("+key+"[=]?[^&]*)([&]?)");
                    if (arr = nameValue.match(reg)) 
                    {
                        if(arr[1]==arr[3])
                        {
                            reg2 = new RegExp("([&]?)("+key+"[=]?[^&]*)")
                            var temp=nameValue.replace(reg2,"");
                            setCookie(name,null,temp);
                        }
                        else
                        {
                            var temp= nameValue.replace(reg,"");
                            setCookie(name,null,temp);
                        }
                    }
                }
            }
            else
            {//删除cookie
                var exp = new Date();
                exp.setTime(exp.getTime() - 1);
                var cval = getCookie(name);
                if (cval != null) 
                    document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString() + ";path=/";
            }
            
            getCookie(name);
        }
        
        function crossBorder()
        {//大部分浏览器cookie只能存4K的数据。
            var x="";
            for(i=0;i<5000;i++)
            {
                if(x!="")
                    x+="&x"+i.toString()+"="+i.toString();
                else
                    x+="x"+i.toString()+"="+i.toString();
                
                if(x.length>4000)
                {
                    setCookie("test",null,x);
                    break;
                }
            }
                
        }