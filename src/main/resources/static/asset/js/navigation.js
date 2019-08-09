var userType;//用户类型
$(function(){
    //获取登陆用户信息
    getUserType();

    if(userType==3){//子账号
        //隐藏公司简介菜单
        $("#companyEdit").hide();
    }
});


/*进后台管理*/
function go_backstage(){
    window.location.href = "index";
}

/*进谈单管理*/
function go_shop(){
    if(userType==2){
        window.location.href = "shopPage";
    }else{
        var storage = window.localStorage;
        if(storage.customer!=null){
            window.location.href = "shopPage";
        }else{
            $("#showModal").modal('show');
        }
    }
}

function submit(){
    var storage = window.localStorage;
    if($("#customerName").val().trim()==""){
        $.alert("客户姓名不能为空！");
        $("#customerName").focus();
        return;
    }
    if($("#phoneNumber").val().trim()==""){
        $.alert("客户电话不能为空！");
        $("#customerName").focus();
        return;
    }
    if($("#phoneNumber").val().length!=11){
        $.alert("客户电话只能为11位数字！");
        $("#customerName").focus();
        return;
    }
    $.ajax({
        type : "POST",
        url : "../../customer/saveOrUpdate",
        data : $("#customerForm").serialize(),
        dataType : "json",
        async : false,
        success : function(result) {
            if (result.status=='000'){
                $("#showModal").modal('hide');
                $.alert("登记成功！");
                storage.setItem("customer",JSON.stringify(result.data));
                window.location.href = "shopPage";
            }else{
                $.alert("登记失败！");
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
            window.localStorage.removeItem("customer");//清除当前顾客信息。
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

/*获取登陆信息*/
function getUserType(){
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
            }else{
                window.top.location.href="login";
            }
        }
    });
}