 <%@ page contentType="text/html;charset=UTF-8" language="java"%>
 <html>
 <body>
 
 <form id="upload" action="/cms/imageTest/upload" method="post" enctype="multipart/form-data">
				<div class='controls'>
					<input type="file" id="imgFile" name="imgFile" multiple="multiple"
						class="form-control input-sm mb15"  required>
						 <span id="videoFile-error" class="help-block error"></span>
						<button type="submit" id ="submitbutton">图片上传</button>
				</div>		
			</form>
	
 </body>
 </html>
 	 