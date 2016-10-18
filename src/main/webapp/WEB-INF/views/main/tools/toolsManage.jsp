<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/cms/assets/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/cms/assets/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/cms/assets/diyUpload/js/webuploader.html5only.min.js"></script>
<script type="text/javascript" src="/cms/assets/diyUpload/js/diyUpload.js"></script>
</head>

<body>
<div id="box">
	<div id="batchUpload" ></div>
</div>
<div id="urlInfo"></div>
</body>   
<script>

$('#batchUpload').diyUpload({
	url:"/cms/batch/upload/img",
	success:function( data ) {
		if(data.result==1){
			$("#urlInfo").append("<a href="+data.msg+" target='_blank'><u>"+data.msg+"</u></a></br>");
		}else{
			$("#urlInfo").append("<p>"+data.msg+"</p></br>");
		}
	},
	error:function( err ) {
		console.info( err );	
	},
	buttonText : '选择图片',
	chunked:true,
	// 分片大小
	chunkSize:512 * 1024,
	//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
	fileNumLimit:40,
	fileSizeLimit:5000000 * 1024,
	fileSingleSizeLimit:500000 * 1024,
	accept: {}
});


  seajs.use(['base','main/tools/toolsManage'],function(b,m){
	b.init();
 	m.init('${ctx}');
  });
</script>
</html>
