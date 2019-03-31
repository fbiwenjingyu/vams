/*  -----------   左侧手拉收缩   --------------   */
var accordion=function(){		
		var tm=sp=10;	
		function slider(n){
			this.nm=n; this.arr=[]
		}
		slider.prototype.init=function(t,c,k){
			var a,h,s,l,i;
			a=document.getElementById(t);
			this.sl=k?k:'';
			h=a.getElementsByTagName('dt');
			s=a.getElementsByTagName('dd'); this.l=h.length;
			for(i=0;i<this.l;i++){
				var d=h[i];
				this.arr[i]=d;
				d.onclick=new Function(this.nm+'.pro(this)');
				if(c==i){
					d.className=this.sl
				}
			}
			l=s.length;
			for(i=0;i<l;i++){
				var d=s[i];
				d.mh=d.offsetHeight;
				if(c!=i){
					d.style.height=0;
					d.style.display='none'
				}
			}
		}
		slider.prototype.pro=function(d){
			for(var i=0;i<this.l;i++){
				var h=this.arr[i], s=h.nextSibling;
				s=s.nodeType!=1?s.nextSibling:s;
				clearInterval(s.tm);
				if((h==d&&s.style.display=='none') || (h==d&&s.style.display=='')){
					s.style.display='';
					su(s,1);
					h.className=this.sl
				}else if(s.style.display==''){
					su(s,-1);
					h.className=''
				}
			}
		}
		function su(c,f){
			c.tm=setInterval(function(){
				sl(c,f)
			},tm)
		}
		function sl(c,f){
			var h=c.offsetHeight, m=c.mh, d=f==1?m-h:h;
			c.style.height=h+(Math.ceil(d/sp)*f)+'px';
			c.style.opacity=h/m;
			c.style.filter='alpha(opacity='+h*100/m+')';
			if(f==1&&h>=m){
				clearInterval(c.tm)
			}else if(f!=1&&h==1){
				c.style.display='none';
				clearInterval(c.tm)
			}
		}
		return{slider:slider}
		
	}();
	
	
/*    main.jsp     */	

    //主功能菜单选中背景
	var newsTop=document.getElementById('NewsTop');
	if(null!=newsTop){
		var Tags=newsTop.getElementsByTagName('li');    
		var len=Tags.length; 
		var flag=0;//修改默认值
		for(i=0;i<len;i++){
			Tags[i].value = i;
			Tags[i].onclick=function(){changeNav(this.value)}; 
		}
		Tags[flag].className='topC1';    
		function changeNav(v){ 
			Tags[flag].className='topC0';    
			flag=v; 
			Tags[v].className='topC1';
		}
	}
	
	//退出登陆
	function exit(){
		top.Dialog.confirm("确定要退出系统吗",function(){
		    window.parent.location='login!logout.action';
		});
	}

	function newBg(obj){
	   $(".currentBk").attr("class","title");
       obj.className="currentBk";
       $("#currentBk").attr("id","tak");
	}
	
	function newBgTwo(obj){
	     $(".currentBk").attr("class","title"); //清除父节点
	     $("#currentBk").attr("id","tak");
	     obj.id="currentBk";
	  
	}
	
	//刷新左侧菜单
	function refreshRes(obj,res) {
		var s="<IFRAME scrolling='no' width='100%' height='100%' frameBorder='0' id='frmright' name='frmright' src='system/layout/open.jsp'  allowTransparency='true'></IFRAME>";
		document.getElementById("bs_right").innerHTML=s;
		var reshtml = "";
		$('#res').html(res);
		$.ajax({
	    	type:"POST",
	    	url:"roleRes!getRes.action?upresid="+obj,
	    	dataType:"json",
	    	success:function(data){
	    		$.each(data,function(i,n){
		    		if(typeof(n.resurl) =="undefined"){
		    			reshtml += "<div onclick='newBg(this)'  class='title'><a><span class='leftSpan'  style='background:url("+n.icon+") no-repeat 20px 4px;' onclick=show('"+n.resid+"') >"+n.resname+"</span><span class=rightSpan'><img style='margin-top:5px;margin-bottom:7px;' src='system/layout/skin/btn_right.gif' onclick=show('"+n.resid+"') id='img"+n.resid+"'/></span></a><input style='width:1px;' type='hidden' id='"+n.resid+"' value='"+n.resid+"'/></div>";
			    	}else if(n.openmode == "4"){
			    		reshtml += "<ul class='"+n.upresid+"' style='display:none'>";
			    		reshtml += "<li id='tak' onclick='newBgTwo(this)' ><a href='"+n.resurl+"' target='frmright'><span class='imgBkLeft'><img  src='"+n.icon+"'/></span>"+n.resname+"</a></li>";  
		    			reshtml += "</ul>";
		    			$("ul."+n.upresid).hide();
		    			
		    		}else{
			    		reshtml += "<div  onclick='newBg(this)'  class='title'><a href='"+n.resurl+"'   target='frmright'><span class='noHrefSpan'  style='background:url("+n.icon+") no-repeat 20px 6px;' >"+n.resname+"</span></a></div>	";
				    }
	    		});     
	    		//$('#ii').val(reshtml);
	    		$('#slider2').html(reshtml);
	    	}
	    });
	   
	}


	//初始化登录左侧菜单
	function initRes() {
		var resid = $("#resid").val();
		var reshtml = "";
		$.ajax({
	    	type:"POST",
	    	url:"roleRes!getRes.action?upresid="+resid,
	    	dataType:"json",
	    	success:function(data){
	    		$.each(data,function(i,n){
		    		if(typeof(n.resurl) =="undefined"){
		    			reshtml += "<div onclick='newBg(this)'  class='title'><a><span class='leftSpan'  style='background:url("+n.icon+") no-repeat 20px 4px;' onclick=show('"+n.resid+"') >"+n.resname+"</span><span class=rightSpan'><img style='margin-top:5px;margin-bottom:7px;' src='system/layout/skin/btn_right.gif' onclick=show('"+n.resid+"') id='img"+n.resid+"'/></span></a><input style='width:1px;' type='hidden' id='"+n.resid+"' value='"+n.resid+"'/></div>";
			    	}else if(n.openmode == "4"){
			    		reshtml += "<ul class='"+n.upresid+"' style='display:none'>";
			    		reshtml += "<li id='tak' onclick='newBgTwo(this)' ><a href='"+n.resurl+"' target='frmright'><span class='imgBkLeft'><img  src='"+n.icon+"'/></span>"+n.resname+"</a></li>";  
		    			reshtml += "</ul>";
		    			$("ul."+n.upresid).hide();
		    			
		    		}else{
			    		reshtml += "<div  onclick='newBg(this)'  class='title'><a href='"+n.resurl+"'   target='frmright'><span class='noHrefSpan'  style='background:url("+n.icon+") no-repeat 20px 6px;' >"+n.resname+"</span></a></div>	";
				    }
	    		});     
	    		//$('#ii').val(reshtml);
	    		$('#slider2').html(reshtml);
	    	}
	    });
	   
	}
	
	//修改密码
	function updatePwd(){
        var url="right/pwd_update.jsp";
		top.Dialog.open({URL:url,Title:"密码修改",Width:400,Height:200});
   	}
	
	//当前用户设置
	function userParam(){
		var url="right/user_param.jsp";
		top.Dialog.open({URL:url,Title:"${user.userCode}设置",Width:400,Height:200});
	}
	
	