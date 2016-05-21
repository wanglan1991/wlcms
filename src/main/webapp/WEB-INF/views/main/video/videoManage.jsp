<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script src="http://qzonestyle.gtimg.cn/open/qcloud/video/h5/h5connect.js" charset="utf-8" ></script>

<html>
<head>
<title></title>
<style>
label.error{
color:red;
}
</style>
</head>
<body>
	<div class='box-header' id="video-header">
		<div class='actions'></div>
	</div>
	<div class='box-content box-no-padding'>
		<div class='responsive-table'>
			<div class='scrollable-area-x'>
				<table id="videoTable"></table>
			</div>
		</div>
	</div>
<!-- 新增视频模态框 -->
	<div class='modal hide fade ' id='modal-Video' role='dialog'
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' id="tatil" data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
<!-- 		<form class='form validate-form' id='submit-form' role="form" -->
<!-- 		 action="/cms/VodCloud/upload" method="post" enctype="multipart/form-data" -->
<!-- 			style='margin-bottom: 0;'> -->
				<div class='modal-body'  style="max-height: 800px" >
				
				<div class='control-group'>
					<label class='control-label'>视频名称</label>
					<input type="hidden" id="id" />
					<input type="hidden" id="videoId" />
					<div class='controls'>
						<input class='span8' id='videoName' name="videoName" placeholder='请输入视频名称' 
							type='text' />
							<span id="videoName-error" class="help-block error"></span>
					</div>
				</div>
				<!-- 视频文件名直接从视频信息上取得 -->
				<div class='control-group'>
					<label class='control-label'>视频文件名</label>
					<div class='controls'>
						<input class='span8' id='fileName' name="fileName" placeholder='上传视频成功后自动回填视频文件名'type='text' />
							<span id="fileName-error" class="help-block error"></span>
<!-- 							<br><font color="red">*视频文件名按一定规则命名 </font> -->
					</div>
				</div>
				
				<div class='control-group'>
					<label class='control-label'>简介</label>
					<div class='controls'>
						<textarea class='span8' id='digest' name="digest"
							placeholder='简介' type='text' style="height:50px;" />
							<span id="digest-error" class="help-block error"></span>
					</div>
				</div>
			
				<div class='control-group'>
					<label class='control-label'>讲师</label>
					<div class='controls'>
						<select class='span8' id='author' name="author"
							placeholder='讲师' />
							<span id="author-error" class="help-block error"></span>
					</div>
				</div>
					
						<div class='control-group'>
					<label class='control-label'>年级</label>
					<div class='controls'>
						 <div class='input-append'>
		               		<select id='grade' name='grade' ></select>
		                </div>
					</div>
				</div>
				
				<div class='control-group'>
					<label class='control-label'>科目</label>
					<div class='controls'>
						<div class='input-append'>
		               		<select id='subject' name='subject'>
		               		</select>
		                </div>
					</div>
				</div>
				
				<div class="col-md-11 col-sm-12">
					<div class="form-group">
						<label class="col-sm-3 control-label">知识点</label>
						<div class="col-sm-9">
							<select id="knowledge" name="knowledge"
								style="width: 337.609px; height:30px" multiple="true"></select>
								<span id="knowledge-error" class="help-block error"></span>
						</div>
					</div>
				</div>
				
						<div class='control-group'>
					<label class='control-label'>运营商</label>
					<div class='controls'>
						<input class='span8' id='isp' name="isp"
							placeholder='运营商' type='text' />
							<span id="isp-error" class="help-block error"></span>
					</div>
				</div>
				
					<div class='control-group'>
					<label class='control-label'>视频url</label>
					<div class='controls'>
						<input class='span8' id='url' name="url" placeholder='上传视频成功后自动回填URL' 
							type='text' />
							<span id="url-error" class="help-block error"></span>
					</div>
					</div>
				
				<!-- 新增的时候先不考虑备用URL -->
<!-- 				<div class='control-group'> -->
<!-- 					<label class='control-label'>备用视频url</label> -->
<!-- 					<div class='controls'> -->
<!-- 						<input class='span8' id='urlBak' name="urlBak" placeholder='请输入备用视频url'  -->
<!-- 							type='text' /> -->
<!-- 					</div> -->
<!-- 				</div> -->

			<form id="qupload" action="/cms/VodCloud/upload" method="post" enctype="multipart/form-data">
				<div class='controls'>
					<input type="file" id="videoFile" name="videoFile" multiple="multiple"
						class="form-control input-sm mb15"  required>
						 <span id="qvideoFile-error" class="help-block error"></span>
						<button type="submit" id ="qsubmitbutton">腾讯云上传</button>
				</div>
				
				<!-- 是否转码与水印 暂不使用 -->
<!-- 				<div class="input_area"> -->
<!-- 					<span>转 码: </span> 开启<input type="radio" name="transcode" value="1" -->
<!-- 						checked=""> &nbsp;&nbsp;&nbsp;禁用<input type="radio" -->
<!-- 						name="transcode" value="0"> -->
<!-- 				</div> -->
<!-- 				<div class="input_area"> -->
<!-- 					<span>水 印: </span> 开启<input type="radio" name="watermark" value="1"> -->
<!-- 					&nbsp;&nbsp;&nbsp;禁用<input type="radio" name="watermark" value="0" -->
<!-- 						checked=""> -->
<!-- 				</div> -->

			</form>			
			
			<div class='modal-footer'>
				<msg id='msg'></msg>
				<button type="button" id="btnClose" class='btn'>关闭</button>
				<button   id="btnSubmit" class='btn btn-primary'>保存</button>
			</div>
<!-- 		</form> -->
	</div>
	</div>
	
	
<!-- 编辑视频模态框 -->
<div class='modal hide fade' id='modal-editVideo' role='dialog'
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' id="tatil" data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
		<form class='form validate-form' id='Editsubmit-form' role="form"
			style='margin-bottom: 0;'>
				<div class='modal-body' style="max-height: 550px">
				<div class='control-group'>
					<label class='control-label'>视频名称</label>
					<input type="hidden" id="editId" />
					<div class='controls'>
						<input class='span8' id='editVideo' name="editVideo" placeholder='请输入视频名称' 
							type='text' />
							<span id="edit-video-error" class="help-block error"></span>
					</div>
				</div>
				
				<div class='control-group'>
					<label class='control-label'>简介</label>
					<div class='controls'>
						<textarea class='span8' id='editDigest' name="editDigest"
							placeholder='简介' type='text' style="height:50px;" />
					</div>
				</div>
			
				<div class='control-group'>
					<label class='control-label'>视频url</label>
					<div class='controls'>
						<input class='span8' id='editUrl' name="editUrl" placeholder='请输入视频url' 
							type='text' />
						
					</div>
				</div>
				
				<div class='control-group'>
					<label class='control-label'>运营商</label>
					<div class='controls'>
						<input class='span8' id='editIsp' name="editIsp"
							placeholder='运营商' type='text' />
					</div>
				</div>
				
				   <div class='control-group'>
					<label class='control-label'>讲师</label>
					<div class='controls'>
						 <div class='input-append'>
		               		<select id='editAuthor' name='editAuthor' ></select>
		                </div>
					</div>
				</div>
				
						<div class='control-group'>
					<label class='control-label'>年级</label>
					<input type="hidden" id="EditGradeNo" />
					<div class='controls'>
						 <div class='input-append'>
		               		<select id='EditGrade'></select>
		                </div>
					</div>
				</div>
				
				<div class='control-group'>
					<label class='control-label'>科目</label>
					<input type="hidden" id="EditSubjectNo" />
					<div class='controls'>
						<div class='input-append'>
		               		<select id='EditSubject'>
		               		</select>
		                </div>
					</div>
				</div>
			

			
          <div class="col-md-11 col-sm-12">
                <div class="form-group">
            		<label class="col-sm-3 control-label">知识点</label>
            		<div class="col-sm-9">
                    	<select id="editKnowledge"  name="editKnowledge"  style="width:300px;" multiple="true"></select>      
            		</div>			
        		</div>
            </div>
            
           
            
			<div class='control-group'>
					<input type="hidden" id="editStatus" />
			</div>
			</div>
			<div class='modal-footer'>
				<msg id='edit-msg'></msg>
				<button type="button" id="EditbtnClose" class='btn'>关闭</button>
				<button  id="EditbtnSubmit" class='btn btn-primary'>保存</button>
			</div>
		</form>
	</div>
		
		
		<!-- 播放器模态框 -->
	<div class='modal hide fade' id='modal-playVideo' role='dialog'
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
		<div class='modal-body'></div>
		<div id="id_video_container" ></div>
		<div class='modal-footer'></div>
	</div>



	<script>
		seajs.use([ 'base', 'main/video/videoManage' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</body>
</html>
