//初始化fileinput
var ImgInput = function () {
    var oFile = new Object();
    //初始化fileinput控件（第一次初始化）
    oFile.Init = function(ctrlName) {
        var control = $('#' + ctrlName);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: "", //上传的地址。如果设置了，附件框也会出现上传标记。
            allowedFileExtensions: ['jpg', 'png','bmp'],//接收的文件后缀
            showUpload: false, //是否显示上传按钮
            showRemove: true,//是否显示移除按钮
            showCaption: true,//是否文字表述
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            uploadAsync: false,//同步上传
            maxFileCount:1, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            overwriteInitial: true,//覆盖已存在的图片

            initialPreviewAsData: true,
            initialPreview: imgArr,
            initialPreviewConfig: [
                {caption: "",width: "120px", url: "company/deletePicture"}
            ],
            layoutTemplates:{
                actionUpload:'', //设置为空可去掉上传按钮
                actionDelete:'' //设置为空可去掉删除按钮
            }
        }).on('filepredelete', function(event, key, jqXHR, data) {
            //点击删除触发事件。
        });
    };
    return oFile;
};
