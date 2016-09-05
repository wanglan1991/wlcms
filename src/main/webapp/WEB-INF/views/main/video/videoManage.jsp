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
				<div class='modal-body'  style="max-height: 700px" >
				
				<div class='control-group'>
					<label class='control-label'>视频名称</label>
					<input type="hidden" id="id" />
					<input type="hidden" id="videoKey" />
					<div class='controls'>
						<input class='span8' id='videoName' name="videoName" placeholder='请输入视频名称' 
							type='text' />
							<span id="videoName-error" class="help-block error"></span>
					</div>
				</div>
				<!-- 视频文件名直接从视频信息上取得 -->
				<div class='control-group'>
					<div class='controls'>
						<input type="hidden" id="duration" />
						<input type="hidden" class='span8' id='fileName' name="fileName" placeholder='上传视频成功后自动回填视频文件名'type='text' />
							<span id="fileName-error" class="help-block error"></span>
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
					<div class='controls'>
						<input type="hidden" class='span8' id='url' name="url" placeholder='上传视频成功后自动回填URL' 
							type='text' />
							<span id="url-error" class="help-block error"></span>
					</div>
					</div>
				
				
		<!-- 乐视云点播 
			<form id="leUpload" action="/cms/LetvController/initUpload" method="post" enctype="multipart/form-data">
				<div class='controls'>
					<input type="file" id="videoFile" name="videoFile" multiple="multiple"
						class="form-control input-sm mb15"  required>
						 <span id="qvideoFile-error" class="help-block error"></span>
						<button type="submit" id ="submitbutton">点击上传</button>
				</div>
			</form>	-->
			
			<!-- 腾讯云点播-->	
			 <form id="qupload" action="/cms/vodCloud/upload" method="post" enctype="multipart/form-data">
				<div class='controls'>
					<input type="file" id="videoFile" name="videoFile" multiple="multiple"
						class="form-control input-sm mb15"  required>
						 <span id="qvideoFile-error" class="help-block error"></span>
						<button type="submit" id ="qsubmitbutton">点击上传</button>
				</div>
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
	<div class='modal hide fade' id='modal-playVideo' style="width:690px;height: 490px;" role='dialog' tabindex='-1'>
		<div class='modal-header'>
			<button class='close' data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
		<div class='modal-body'></div>
		<div id="id_video_container" ></div>
		<div class='modal-footer'></div>
	</div>
	
	
	
	
	<!-- 	编辑习题 -->
	<div class='modal hide fade' id='modal-editVideoExercise' role='dialog' style="min-height: 708px;"
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' id="tatil" data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
		<div style="margin-top: 18px;width: 510px;margin-left: 25px;">
				<div class='control-group'>
					<label class='control-label'>年级</label>
					<div class='controls'>
						<select class='span8' id='exerciseGrade' placeholder='年级' ></select>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>学科</label>
					<div class='controls'>
						<select class='span8' id='exerciseSubject' placeholder='学科'>
							<option value='0'>学科----</option>
						</select>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>知识点</label>
					<div class='controls'>
						<select class='span8' id='exerciseKnoeledge' placeholder='学科'>
							<option value='0'>知识点----</option>
						</select>
					</div>
				</div>

				<div class='control-group'>
					<label class='control-label'>可选习题</label>
					<div class='controls'>
						<ul id="exerciseTree" class="ztree" style="background:
						 #f0f6e4;width:100%;height:361px;overflow-y:scroll;overflow-x:auto;"></ul>
					</div>
				</div>
      
       

    



	</div>
		
		<div class='modal-footer'>
				<msg id='edit-msg'></msg>
				<button type="button" id="editVideoExerciseClose" class='btn'>关闭</button>
				<button  id="editVideoExerciseSubmit" class='btn btn-primary'>保存</button>
			</div>		
	</div>



	<script>
		seajs.use([ 'base', 'main/video/videoManage' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</body>
</html>
