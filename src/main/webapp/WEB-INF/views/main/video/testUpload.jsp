<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script src="http://qzonestyle.gtimg.cn/open/qcloud/js/vod/sdk/uploaderh5.js" charset="utf-8"></script></li>
<html>
<body>
<form action="/VodDemo/domain" method="post">
<div class="input_area"><span>secretId : </span> <input type="text" name="secret_id" id="secret_id" value=""></div>
<div class="input_area"><span>secret_key : </span> <input type="text" name="secret_key" id="secret_key" value="" placeholder="请不要暴露secret_key给外部用户">
        <a href="/sdk/api.html" target="_blank">如何设置安全签名?</a>
    </div>
    <div class="input_area"><span>转 码: </span>
        开启<input type="radio" name="transcode" value="1" checked="">
        &nbsp;&nbsp;&nbsp;禁用<input type="radio" name="transcode" value="0">
    </div>
    <div class="input_area"><span>水 印: </span>
        开启<input type="radio" name="watermark" value="1">
        &nbsp;&nbsp;&nbsp;禁用<input type="radio" name="watermark" value="0" checked="">
    </div>
    <div class="input_area"><span>分类ID: </span>
        <input type="input" name="classId" value="">
        <br>上传可以指定视频所属分类; 如果不需要指定，请不要设置，错误值会影响上传正确性
    </div>
    <button type="submit" id="start">确定</button>
    
</form>
</body>
</html>