var userType;//用户类型
$(function(){
	//获取登陆用户信息
	getLoginInfo();

	var menuPList;//一级(父级)菜单集合
    var menuCList;//二级(子级)菜单集合
    //首先，查出数据库中有权限的且启用的父级菜单。
	$.ajax({
		type : "GET",
		url : "menu/findParentMenu",
		dataType : "json",
		async : false,
		success : function(result) {
			menuPList = eval(result.data);
		}
	});

	var strP;
	if(userType!=1){
        strP = "<li>"
            + "<a href='initPage'>"
            + "<i class='fa fa-home'></i>"
            + "<span class='nav-label'>系统导航</span>"
            + "<span></span>"
            + "</a>"
            + "</li>";
        $("#side-menu").append(strP);
    }
    $.each(menuPList,function(index,data){
        //根据父级菜单查询启用的子菜单
        var menuPid = data.id;
        $.ajax({
            type : "GET",
            url : "menu/findSubMenu",
            data:{menuPid:menuPid},
            dataType : "json",
            async : false,
            success : function(result) {
                menuCList = eval(result.data);
            }
        });
        if(menuCList.length==0 && data.menuUrl!=null){
            strP = "<li>"
                + "<a id='menu"+data.id+"' class='J_menuItem' href='"+data.menuUrl+"'>"
                + "<i class='"+ data.menuIcon +"'></i>"
                + "<span class='nav-label'>"+data.menuName+"</span>"
                + "</a>"
                + "<\/li>";
            $("#side-menu").append(strP);
        }else{
            strP = "<li id='menu"+data.id+"'>"
                + "<a class='menu"+data.id+"'  href='#collapse"+data.id+"'>"
                + "<i class='"+ data.menuIcon +"'></i>"
                + "<span class='nav-label'>"+data.menuName+"</span>"
                + "<span class='fa arrow'></span>"
                + "</a>"
                + "<\/li>";
            $("#side-menu").append(strP);
            if(menuCList.length>0){//如果该父菜单下有子菜单
                var strC ="<ul id='collapse"+menuPid+"' class='nav nav-second-level collapse'>";
                $.each(menuCList,function(indexC,dataC){
                    strC += "<li>"
                        +  "<a class='J_menuItem' href='"+dataC.menuUrl+"' id='menu"+dataC.id+"'>"
                        +  "<i class='"+ dataC.menuIcon +"'></i>"
                        +  "<span>"+dataC.menuName+"</span>"
                        +  "</a>"
                        +  "<\/li>";
                });
                strC += "</ul>";
                $("#menu"+data.id).append(strC);
            }
        }
    });
});

/*获取登陆信息*/
function getLoginInfo(){
    $.ajax({
        type : "GET",
        url : "getLoginInfo",
        data : {},
        dataType : "json",
        async : false,
        cache:false,
        success : function(result) {
            if(result.status=="000"){
                userType = result.data.userType;
                if(result.data.userType==1){
                    $("#name").html("超级管理员");
                }else if(result.data.userType==2){//主账号写公司名字
                    $("#name").html(result.data.companyName);
				}else if(result.data.userType==3){//子账号直接写用户名
                    $("#name").html(result.data.userName);
				}
            }else{
				window.top.location.href="login";
            }
        }
    });
}

/*退出*/
function logout(){
	$.ajax({
		type : "GET",
		url : "logout",
		data : {},
		async : false,
		success : function() {
			window.location.href="login.html";
            window.localStorage.removeItem("customer");//清楚当前顾客信息。
		},
		error : function(){
			$.alert("系统故障，请联系管理员!");
		}
	});
}

Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}
