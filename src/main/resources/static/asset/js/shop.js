/*获取登陆信息*/
$(function getUserType(){
    $.ajax({
        type : "GET",
        url : "getLoginInfo",
        data : {},
        dataType : "json",
        async : false,
        cache:false,
        success : function(result) {
            if(result.status=="000"){
                if(result.data.userType==2){//如果是主账号，去掉去购物车和加入购物车功能。
                    $("#gouwuche").remove();
                    $("#openShop").remove();
                }
            }else{
                window.top.location.href="login";
            }
        }
    });
});

/*点击变色*/
$(".wtbz_title").click(function(){
    $(".wtbz_title").removeClass("click")
    $(this).addClass("click");
});

//显示详情
function displayDetails(id) {
    $(".bigg_box").css('display','none');
    $(".dgwc").css('display','none');
    $("#shopDiv").css('display','none');
    $("#comboDiv").css('display','none');
    $(".big-boxx").css('display','block');
    $.ajax({
        type : "get",
        url : "../manageShow/list",
        data : {id:id},
        dataType : "json",
        async : false,
        success : function(data) {
            if (data.status=='000'){
                addDetails(data.rows[0]);
            }else{
                $.alert("系统故障，请联系管理员!");
            }
        }
    });
}

/*购物车页面*/
function openShop(){
    $(".bigg_box").css('display','none');
    $(".dgwc").css('display','none');
    $("#tdgl_right").css('display','none');
    $("#shopDiv").css('display','block');
    $(".big-boxx").css('display','none');
    $("#comboDiv").css('display','none');
    loadtable();
}

/*套餐页面*/
function openCombo(){
    $(".bigg_box").css('display','block');
    $(".dgwc").css('display','block');
    $("#tdgl_right").css('display','none');
    $("#shopDiv").css('display','none');
    $(".big-boxx").css('display','none');
    $("#comboDiv").css('display','block');
    comboTable();
}

//谈单管理
$("#wtbz").click();//采用点击事件触发舞台布置列表
//manageList(1);

function manageList(type) {
    $(".bigg_box").css('display','block');
    $(".dgwc").css('display','block');
    $("#tdgl_right").css('display','block');
    $("#comboDiv").css('display','none');
    $(".big-boxx").css('display','none');
    $("#shopDiv").css('display','none');
    $.ajax({
        type : "GET",
        url : "manageShow/list",
        data : {manageType:type,status:1},
        async : false,
        success : function(data) {
            if(data.rows.length>0){
                addManage(data.rows);
            }else{
                $.alert("请上传相关图片信息!");
            }
        },
        error : function(){
            $.alert("系统故障，请联系管理员!");
        }
    });
}

//平铺谈单管理
function addManage(data) {
    var divAdd="";
    for(var i=0,len=data.length;i<len;i++){
        var imgArr = [];
        $.each(data[i].files,function(i,file){
            if(file.fileType==0){
                imgArr = file.fileAliyunUrl.split(",");
            }
        });
        divAdd += '<div class="jstplb"><div class="jstplb_img"><img style="cursor:pointer" src="' + imgArr[0] + '" onclick="displayDetails(' + data[i].id + ')"></div><div class="wzxqjs">' +
            '<div class="xqjss">详情：</div><div class="xqwzms">' + data[i].description + '</div>' +
            '<div class="jgsz">价格：<span>￥' + data[i].price + '</span></div>' +
            '<a><div class="jrgwcan" onclick="updateStatus(' + data[i].id + ')"><div class="gwc_img"><img src="asset/img/navigation/gwc.png"></div><div class="jrgwc">加入购物车</div></div></a></div>' +
            '<div class="clearfix"></div></div>';
    }
    $("#tdgl_right").html(divAdd);
}

//平铺详情显示
function addDetails(data) {
    $("#vedioUrl").empty();
    $("#imgDiv").empty();

    var imgDiv = "";
    var imgArr = [];
    var vedioFlag = false;
    $.each(data.files,function(i,file){
        if(file.fileType==0){
            imgArr = file.fileAliyunUrl.split(",");
        } else if(file.fileType==1){
            vedioFlag = true;
            $("#spbf").css('display','block');
            var sourceDom = $("<source src=\""+ file.fileAliyunUrl +"\">");
            $("#vedioUrl").append(sourceDom);
        }
    });
    if(vedioFlag==false){
        $("#spbf").css("display","none");
    }
    $.each(imgArr,function(i,img){
        imgDiv += '<div style="text-align: center">'
            + '<a href="'+img+'" target="_blank">'
            + '<img class="shopImg" src="'+ img +'">'
            + '</a>'
            + '</div>';
    });
    $("#description_p").html(data.description);
    $("#price_p").html("￥"+data.price);
    $("#imgDiv").append(imgDiv);
    $("#gouwuche").attr("onclick","updateStatus("+data.id+")");
}

//加入购物车
function updateStatus(id){
    $.ajax({
        type : "post",
        url : "../manageShow/addShopManage",
        data : {id:id,type:0,customerId:JSON.parse(window.localStorage.customer).id},
        dataType : "json",
        async : false,
        success : function(data) {
            if (data.status=='000'){
                $.alert("加入成功！");
            }else{
                $.alert("加入失败！");
            }
        }
    });
}

function comboAddShop(id){
    $.confirm({
        title: '加入购物车!',
        content: '是否加入购物车?',
        buttons: {
            '确定': function () {
                $.ajax({
                    type : "post",
                    url : "../manageShow/addShopManage",
                    data : {id:id,type:1,customerId:JSON.parse(window.localStorage.customer).id},
                    dataType : "json",
                    async : false,
                    success : function(data) {
                        if (data.status=='000'){
                            $.alert("加入成功！");
                        }else{
                            $.alert("加入失败！");
                        }
                    }
                });
            },
            '取消': function () {

            }
        }
    });
}

$("#btn_exit").on("click", function() {
    var storage = window.localStorage;
    storage.clear();//清楚当前顾客信息。
    window.location.href = "initPage";
});

$("#btn_delete").on("click", function() {
    $.confirm({
        title: '清空购物车!',
        content: '是否清空购物车?',
        buttons: {
            '确定': function () {
                deleteShop();
            },
            '取消': function () {

            }
        }
    });
});

$("#btn_edit").on("click", function() {
    var selectRows=$table.bootstrapTable('getAllSelections');
    if(selectRows==null||selectRows.length==0){
        $.alert("请勾选一条数据！");
    }else if(selectRows.length>1){
        $.alert("只能勾选一条数据！");
    }else{
        $.ajax({
            type : "post",
            url : "../manageShow/deleteShop",
            data : {customerId:JSON.parse(window.localStorage.customer).id,manageId:selectRows[0].manageId},
            dataType : "json",
            async : false,
            success : function(data) {
                if (data.status=='000'){
                    $.alert("取消成功！");
                    $table.bootstrapTable('refresh');
                }else{
                    $.alert("取消失败！");
                }
            }
        });
    }
});

$("#btn_read").on("click", function() {
    var selectRows=$table.bootstrapTable('getAllSelections');
    if(selectRows==null||selectRows.length==0) {
        $.alert("请勾选要计算总价的订单！");
        return;
    }
    var prices = 0;
    var i=0;
    while(i<selectRows.length){
        prices += Number(selectRows[i].price);
        i++;
    }
    $("#prices").html("总价为"+prices);
});

function deleteShop(){
    $.ajax({
        type : "post",
        url : "../manageShow/deleteShop",
        data : {customerId:JSON.parse(window.localStorage.customer).id},
        dataType : "json",
        async : false,
        success : function(data) {
            if (data.status=='000'){
                window.localStorage.removeItem("customer");//清楚当前顾客信息。
                $.alert("清空成功！");
                $table.bootstrapTable('refresh');
            }else{
                $.alert("清空失败！");
            }
        }
    });
}

/*加载表格*/
var $table = $("#shopTable");
function loadtable(){
    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        columns : [
            {
                field : 'state',
                checkbox : true,
                align : 'center',
                valign : 'middle'
            },{
                title: '序号',//标题  可不加
                align : 'center',
                valign : 'middle',
                formatter: function (value, row, index) {
                    return index + 1;
                }
            }, {
                title : '信息',
                field : 'fileType',
                align : 'center',
                valign : 'middle',
                formatter:function(value){
                    if(value==3){
                        return "套餐";
                    }else {
                        return "信息";
                    }
                }
            }, {
                title : '信息类型',
                field : 'typeString',
                align : 'center',
                valign : 'middle',
            }, {
                title : '描述',
                field : 'description',
                align : 'center',
                valign : 'middle'
            }, {
                title : '价格',
                field : 'price',
                align : 'center',
                valign : 'middle'
            }, {
                title : '操作',
                field : 'status',
                align : 'center',
                valign : 'middle',
                events: shopEvents,//给按钮注册事件
                formatter:function(value, row){
                    if (row.fileType==3){
                        return '<button type="button" class="btn btn-info" id="shopDetails" >查看详情</button>';
                    } else {
                        return '<button type="button" class="btn btn-info" id="lookDetail" >查看详情</button>';
                    }
                }
            }],
        url : '../manageShow/shop',
        sidePagination : 'server',
        idField : "id",
        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
        toolbar: '#toolbar',                       //工具按钮用哪个容器
        striped: true,                            //是否显示行间隔色
        cache: false,                              //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                          //是否显示分页（*）
        sortable: false,                           //是否启用排序
        clickToSelect: true,						//点击行选中
        method: 'GET',
        //height: 550,
        pageNumber : 1,
        pageSize : 10,
        pageList : [10,20,50,100],
        showFooter : false,
        queryParamsType:'',/* queryParamsType的默认值为 'limit',在默认情况下传给服务端的参数为:offset,limit,sort设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber */
        queryParams : function(params) {
            var storage = window.localStorage;
            var map = {
                pageNumber: params.pageNumber,//当前页码
                pageSize: params.pageSize,//页面大小
                customerId:JSON.parse(storage.customer).id
            };
            $(".manage input,.manage select").each(function(){
                if($(this).val()!=""){
                    map[""+$(this).attr('name')] = $(this).val();
                }
            });
            return map;
        },
    });
}

window.shopEvents = {
    "click #lookDetail" : function (e,value,row,index) {
        displayDetails(row.manageId);
    },
    "click #shopDetails" : function (e,value,row,index) {
        $("#manageTable .del").remove();
        $.ajax({
            type : "get",
            url : "../combo/getComboById",
            data : {id:row.manageId},
            async : false,
            success : function(data) {
                if (data.status=='000'){
                    $.each(data.data.manageShowList,function(i,manage){
                        addManage1(manage);
                    });
                }else{
                    $.alert("打开失败,请联系管理员!");
                }
            }
        });
        $("#manageModal").modal('show');
    }
}

/*加载表格*/
var $comboTable = $("#comboTable");
function comboTable(){
    $comboTable.bootstrapTable('destroy');
    $comboTable.bootstrapTable({
        columns : [
            {
                title: '序号',//标题  可不加
                align : 'center',
                valign : 'middle',
                formatter: function (value, row, index) {
                    return index + 1;
                }
            }, {
                title : '标题',
                field : 'name',
                align : 'center',
                valign : 'middle',
            }, {
                title : '套餐描述',
                field : 'description',
                align : 'center',
                valign : 'middle'
            }, {
                title : '套餐价格',
                field : 'price',
                align : 'center',
                valign : 'middle'
            }, {
                title : '操作',
                field : 'status',
                align : 'center',
                valign : 'middle',
                events: operateEvents,//给按钮注册事件
                formatter: addFunctionAlty,//表格中添加按钮
            }],
        url : '../combo/list?enable=1',
        sidePagination : 'server',
        idField : "id",
        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
        toolbar: '#toolbar',                       //工具按钮用哪个容器
        striped: true,                            //是否显示行间隔色
        cache: false,                              //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                          //是否显示分页（*）
        sortable: false,                           //是否启用排序
        clickToSelect: true,						//点击行选中
        method: 'GET',
        //height: 550,
        pageNumber : 1,
        pageSize : 10,
        pageList : [10,20,50,100],
        showFooter : false,
        queryParamsType:'',/* queryParamsType的默认值为 'limit',在默认情况下传给服务端的参数为:offset,limit,sort设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber */
        queryParams : function(params) {
            var map = {
                pageNumber: params.pageNumber,//当前页码
                pageSize: params.pageSize,//页面大小
            };
            return map;
        },
    });
}

window.operateEvents = {
    "click #comboAddShop" : function (e,value,row,index) {
        comboAddShop(row.id);
    },
    "click #lookDetails" : function (e,value,row,index) {
        $("#manageModal").modal('show');
        $("#manageTable .del").remove();
        $.each(row.manageShowList,function(i,manage){
            addManage1(manage);
        });
    }
}

function addManage1(row) {
    var strVar = '<tr class="del"><td>' + row.typeString + '</td><td>' + row.description + '</td><td>' + row.price + '</td>' +
        '<td><button type="button" class="btn btn-danger btn-block" onclick="delatils(' + row.id + ')">查看详情</button></td></tr>';
    $("#manageTable").append(strVar);
}

function delatils(id) {
    $("#manageModal").modal('hide');
    displayDetails(id);
}

function addFunctionAlty(value, row){
    return '<button type="button" class="btn btn-info" id="comboAddShop" >加入购物车</button>' +
        '<button type="button" class="btn btn-info" id="lookDetails" >查看详情</button>';
}