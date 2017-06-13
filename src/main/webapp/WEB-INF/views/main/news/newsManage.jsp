<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title></title>
</head>
<style>


#addNewsContainer{
	display:none;
    overflow:scroll;
}
#container{
	margin-top:20px;
}
</style>
<body>
<div id="newsTableHead">
	<div class='box-header' >
		<div class='actions' id="newsActions">	
		
		</div>
	</div>
	<div class='box-content box-no-padding'>
		<div class='responsive-table'>
			<div class='scrollable-area-x'>
				<table id="newsTable"></table>
			</div>
		</div>
	</div>
</div>	
									
<div id='addNewsContainer' >
<a href="#" id="saveNews" class="btn btn-success btn-small" style="margin-left:5px;">保存</a>
<a class="shutdown btn btn-primary btn-small" href="#"  style="margin-left:5px">关闭</a>
<br>
<br>
<br>

        标题&nbsp;&nbsp;<input  id='title'    type='text' />&nbsp;&nbsp;&nbsp;
        
        
         <form id="upload"  enctype="multipart/form-data">
             标题图片 &nbsp;&nbsp; <input type="file" id="imgFile" name="imgFile" multiple="multiple" accept="image/gif,image/jpeg,image/png,image/jpg"	style='width: 216px;'required>
           	<input type="hidden" id="imgUrl">
              <button type="submit" id ="submitbutton">上传</button>
		</form>
    
 <script id="container" name="content" type="text/plain"></script>



</div>
	
	
	
	

</body>
	
	<script>
		seajs.use([ 'base', 'main/news/newsManage' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</html>
