<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script src="http://qzonestyle.gtimg.cn/open/qcloud/js/vod/sdk/uploaderh5.js" charset="utf-8"></script></li>
<html>
<body>
<form action="/cms/VodUpload2/upload2" method="post" enctype="multipart/form-data">
<div class="input_area"><span>secretId : </span> <input type="text" name="secret_id" id="secret_id" value=""></div>
<div class='controls'>
					<input type="file" id="videoFile" name="file" multiple="multiple"
						class="form-control input-sm mb15" placeholder="请选择视频文件" required>
				</div>
				<button type="submit" id="start">确定</button>
    
    
</form>
</body>
</html>