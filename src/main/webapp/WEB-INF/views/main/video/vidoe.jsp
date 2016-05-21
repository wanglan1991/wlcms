<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>乐视云 表单提交 上传DEMO</title>
    
</head>
<body>
    <table class="hrPageTable table table-hover table-bordered">
        <thead>
            <tr>
                <th>视频名称</th>
                <th>上传进度</th>
                <th>上传速度</th>
                <th>上传状态</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td id="videoId"></td>
                <td id="videoProgress"></td>
                <td id="videoSpeed"></td>
                <td id="videoStatus"></td>
            </tr>
        </tbody>
    </table>
    <div>
        <form id="upload" action="/cms/letvUpload/html5UploadInit" method="post" enctype="multipart/form-data">
				<div class='controls'>
					<input type="file" id="videoFile" name="video_file" multiple="multiple"
						class="form-control input-sm mb15"  required>
						 <span id="videoFile-error" class="help-block error"></span>
						<button type="submit" id ="submitbutton">sdk上传</button>
				</div>		
			</form>
			
			
		 <form id="upload" action="/cms/LetvController/initUpload" method="post" enctype="multipart/form-data">
				<div class='controls'>
					<input type="file" id="videoFile" name="video_file" multiple="multiple"
						class="form-control input-sm mb15"  required>
						 <span id="videoFile-error" class="help-block error"></span>
						<button type="submit" id ="submitbutton">letv上传</button>
				</div>		
			</form>
	

    </div>
    <!--使用例子-->
    <script type="text/javascript">
//         $(function () {
//             //添加视频
//             $("#fileSelecter").selectUpload({
//                 maxFileSize: 1024 * 1024 * 1024 * 2, //允许上传的最大值，单位是字节
//                 addFiles: function (data) { //添加文件时的回调
//                     $("#videoId").html(data.fileName);
//                     $("#videoProgress").html("0");
//                     $("#videoSpeed").html("0");
//                     $("#videoStatus").html("等待上传");
//                 },
//                 errorCallback: function (data) { //添加文件时发生错误的回调
//                     alert("错误码：" + data.code + ";错误消息：" + data.msg);
//                 }
//             });

//             //开始上传
//             $("#uploadBtn").upload({
//                 initUrl: "/cms/letvUpload/html5UploadInit",//初始化上传地址
//                 uploadProgress: function (progress, rate) { //进度回调
//                     $("#videoProgress").html(progress);
//                     $("#videoSpeed").html(rate);
//                     $("#videoStatus").html("上传中");
//                 },
//                 finishCallback: function (data) { //上传完成回调
//                     $("#videoProgress").html("100%");
//                     $("#videoSpeed").html("0");
//                     $("#videoStatus").html("已上传");
//                 },
//                 errorCallback: function (data) { //上传错误回调
//                     $("#videoProgress").html("0");
//                     $("#videoSpeed").html("0");
//                     $("#videoStatus").html("上传错误！错误码：" + data.code + ";错误消息：" + data.msg);
//                 }
//             });
//         });
        
		$(function() {
			$("#upload").ajaxForm({
				//定义返回JSON数据，还包括xml和script格式
//				dataType : 'json',
				beforeSend : function() {
					//表单提交前做表单验证
					if($("#videoFile").val()=="")
					{
					alert("请先上传文件");
					return;
					}
				
				$("#submitbutton").attr("disabled","disalbed");
				},
				success : function(data) {
					$("#submitbutton").attr("disabled",false);
					//提交成功后调用
					if (data.value!=null) {
						var fileId= data.value;
						getVideoInfo(fileId);
//						
					} else {
						alert(data.msg);
					}
				}
			});
		});
        
    </script>
</body>
</html>
