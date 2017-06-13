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
<body id="wanglanlan">
	<div class='box-header' id="video-header">
		<div class='actions' id="videoActions"></div>
	</div>
	<div class='box-content box-no-padding'>
		<div class='responsive-table'>
			<div class='scrollable-area-x'>
				<table id="videoTable"></table>
			</div>
		</div>
	</div>
<!-- 新增视频模态框 -->
	<div class='modal hide fade ' id='modal-Video'  z-index='2' role='dialog'
		tabindex='-1'>
	
		<div class='modal-header'>
			<button class='close' id="tatil" data-dismiss='modal'  type='button'>&times;</button>
			<h3></h3>
		</div>
				<div class='modal-body' style="max-height:540px" >
				<div class='control-group'>
					<label class='control-label'>视频名称</label>
					<input type="hidden" id="id" />
					<input type="hidden" id="videoKey" />
					<input type="hidden" id="subVideoKey" />
					<div class='controls'>
						<input class='span8' id='videoName' name="videoName" placeholder='请输入视频名称' 
							type='text' />
					</div>
				</div>
				<!-- 视频文件名直接从视频信息上取得 -->
				<div class='control-group'>
					<div class='controls'>
						<input type="hidden" id="videoFileName" />
					</div>
				</div>
				
				<div class='control-group'>
					<label class='control-label'>简介</label>
					<div class='controls'>
						<textarea class='span8' id='digest' name="digest" placeholder='简介' type='text' style="height:50px;" />
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
							<select id="knowledge"  style="width: 337.609px; height:30px" multiple="true"></select>
						</div>
					</div>
				</div>
			 <div class='control-group'>
		            <label class='control-label'>价格</label>
		            <div class='controls'>
		                <input  id='addPrice'  onkeyup="value=value.replace(/[^\d.]/g,'')"  maxlength='3'  placeholder='￥....' type='text' />
		            </div>
		        </div> 
	        <div class='control-group'>
		            <label class='control-label'>折扣</label>
		            <div class='controls'>
		                <input  id='addDiscount'  onkeyup="value=value.replace(/[^\d.]/g,'')"  maxlength='3'  placeholder='%....' type='text' />
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
				<form id="qupload"  enctype="multipart/form-data">
					<div class='controls'>
						<input type="file" id="videoFile" name="videoFile" accept=".avi,.mp4,.rmvb,.mkv" class="form-control input-sm mb15"  required>
<!-- 							<button type="button"  id ="qsubmitbutton">上传视频</button> -->
							<button type="submit"  id ="qsubmitbutton">上传完整视频</button>
					</div>
				</form>	 
<!-- 				上传试看视频 -->
				<form id="quploadSub"  enctype="multipart/form-data">
					<div class='controls'>
						<input type="file" id="videoFileSub" name="videoFile" accept=".avi,.mp4,.rmvb,.mkv" class="form-control input-sm mb15"  required>
							<button type="submit"  id ="qsubmitbuttonSub" style="color:blue">上传试看视频</button>
					</div>
				</form>	 
				
				 <form id="uploadVideoImg"  enctype="multipart/form-data">
			                <input type="file" id="videoImg" name="imgFile" accept="image/gif,image/jpeg,image/png,image/jpg"
							class="form-control input-sm mb15"  required>
							<input type="text" id="videoImgUrl" style="display: none">
			                <button type="submit" id ="uploadVideoImgButton">上传截图</button>
				</form>	
					
			</div>
			<div class='modal-footer'>
				<msg id='msg' style='color:red'></msg>
				<button type="button" id="btnClose" class='btn'>关闭</button>
				<button   id="btnSubmit" class='btn btn-primary'>保存</button>
			</div>
		
	</div>
	
	
	
	
	
	
	<div class='modal hide fade ' id='modal-createTextbook'  z-index='2' role='dialog'
		tabindex='-1'>
	
		<div class='modal-header'>
			<button class='close' id="tatil" data-dismiss='modal'  type='button'>&times;</button>
			<h3></h3>
		</div>
			<div class='modal-body' >
			 <div class='control-group'>
		            <label class='control-label'>排序类型</label>
		            <div class='controls'>
		                <input  id='courseOrderTypeNo'  onkeyup="value=value.replace(/[^\d]/g,'')"  maxlength='6' value='20' type='text' />
		            </div>
		        </div> 
		         <div class='control-group'>
		            <label class='control-label'>热门度值</label>
		            <div class='controls'>
		                <input  id='courseHotValue'  onkeyup="value=value.replace(/[^\d]/g,'')"  maxlength='6'  type='text' />
		            </div>
		        </div> 
				
			</div>
			<div class='modal-footer'>
				<msg id='createTextbookMsg' style='color:red'></msg>
				<button type="button" data-dismiss='modal' class='btn'>关闭</button>
				<button   id="createTextbookBtnSubmit" class='btn btn-primary'>生成</button>
			</div>
		
	</div>
	
	
	
	
	
	
	
<!-- 编辑视频模态框 -->
<div class='modal hide fade' id='modal-editVideo' role='dialog'
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' id="tatil" data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
				<div class='modal-body'  style="max-height:540px">
				<div class='control-group'>
					<label class='control-label'>视频名称</label>
					<input type="hidden" id="editId" />
					<div class='controls'>
						<input class='span8' id='editVideo'  placeholder='请输入视频名称' type='text' />
							<span id="edit-video-error" class="help-block error"></span>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>视频文件名</label>
					<div class='controls'>
						<input class='span8' id='editVideoFileName'  placeholder='请输入视频文件名' type='text' />
						<input type="hidden" id="editOldVideoFileName"> 
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>简介</label>
					<div class='controls'>
						<textarea class='span8' id='editDigest' placeholder='简介' type='text' style="height:50px;" />
					</div>
				</div>
				
				<div class='control-group'>
					<label class='control-label'>年级</label>
					<input type="hidden" id="EditGradeNo" />
					<div class='controls'>
						 <div class='input-append'>
		               		<select id='editGrade'></select>
		                </div>
					</div>
				</div>
				
				<div class='control-group'>
					<label class='control-label'>科目</label>
					<input type="hidden" id="EditSubjectNo" />
					<div class='controls'>
						<div class='input-append'>
		               		<select id='editSubject'>
		               		</select>
		                </div>
					</div>
				</div>

          <div class="col-md-11 col-sm-12">
                <div class="form-group">
            		<label class="col-sm-3 control-label">知识点</label>
            		<div class="col-sm-9">
                    	<select id="editKnowledge"    style="width:300px;" multiple="true"></select>      
            		</div>			
        		</div>
            </div>
            
             <div class='control-group'>
		            <label class='control-label'>价格</label>
		            <div class='controls'>
		                <input  id='editPrice'  onkeyup="value=value.replace(/[^\d.]/g,'')"  maxlength='10'  placeholder='￥....' type='text' />
		            </div>
		        </div> 
	        <div class='control-group'>
		            <label class='control-label'>折扣</label>
		            <div class='controls'>
		                <input  id='editDiscount'  onkeyup="value=value.replace(/[^\d.]/g,'')"  maxlength='10'  placeholder='%....' type='text' />
		            </div>
	        </div> 
	        <!-- 腾讯云点播-->
				<form id="editQupload"  enctype="multipart/form-data">
					<div class='controls'>
						<input type="file" id="editVideoFile" name="videoFile" accept=".avi,.mp4,.rmvb,.mkv" class="form-control input-sm mb15"  required>
							<input type="hidden" id="editVideoKey">
							<button type="submit"  id ="editQsubmitbutton">修改完整视频</button>
					</div>
				</form>	 
<!-- 				上传试看视频 -->
				<form id="editQuploadSub"  enctype="multipart/form-data">
					<div class='controls'>
						<input type="file" id="editVideoFileSub" name="videoFile" accept=".avi,.mp4,.rmvb,.mkv" class="form-control input-sm mb15"  required>
							<input type="hidden" id="editSubVideoKey">
							<button type="submit"  id ="EditSubmitbuttonSub" style="color:blue">修改试看视频</button>
					</div>
				</form>	 
	        
	        
	         <div class='control-group'>
		            <label class='control-label'>截图</label>
		            <div class='controls'>
		            	<input type="text" id="editVideoImgUrl"  placeholder='视频截图URL....'>
		            </div>
	        </div> 
	        
	         <form id="editUploadVideoImg"  enctype="multipart/form-data">
			                <input type="file" id="editVideoImg" name="imgFile" accept="image/gif,image/jpeg,image/png,image/jpg"
							class="form-control input-sm mb15"  required>
			                <button type="submit" id ="editUploadVideoImgButton">修改截图</button>
				</form>	
           
            
			<div class='control-group'>
					<input type="hidden" id="editStatus" />
			</div>
			</div>
			<div class='modal-footer'>
				<msg id='edit-msg' style='color:red'></msg>
				<button type="button" id="EditbtnClose" class='btn'>关闭</button>
				<button  id="editbtnSubmit" class='btn btn-primary'>保存</button>
			</div>
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
