<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
	<div class='modal hide fade' id='modal-Video' role='dialog'
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' id="tatil" data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
<!-- 		<form class='form validate-form' id='submit-form' role="form" -->
<!-- 			style='margin-bottom: 0;'> -->
				<div class='modal-body'  style='padding-bottom: 300px;'>
				<div class='control-group'>
					<label class='control-label'>视频名称</label>
					<input type="hidden" id="id" />
					<div class='controls'>
						<input class='span8' id='video' name="video" placeholder='请输入视频名称' 
							type='text' />
							<span id="video-error" class="help-block error"></span>
					</div>
				</div>
				
				<div class='control-group'>
					<label class='control-label'>视频文件名</label>
					<div class='controls'>
						<input class='span8' id='fileName' name="fileName"
							placeholder='视频文件名' type='text' />
							<br><font color="red">*视频文件名按一定规则命名 </font>
<!-- 						<br>视频文件名按一定规则命名  -->
					</div>
				</div>
				
				<div class='control-group'>
					<label class='control-label'>简介</label>
					<div class='controls'>
						<textarea class='span8' id='digest' name="digest"
							placeholder='简介' type='text' style="height:50px;" />
					</div>
				</div>
			
				
				
			
				<div class='control-group'>
					<label class='control-label'>讲师</label>
					<div class='controls'>
						<input class='span8' id='author' name="author"
							placeholder='讲师' type='text' />
					</div>
				</div>
					
				<div class="col-md-11 col-sm-12">
					<div class="form-group">
						<label class="col-sm-3 control-label">知识点</label>
						<div class="col-sm-9">
							<select id="knowledge" name="knowledge"
								style="width: 337.609px; height:30px"></select>
						</div>
					</div>
				</div>
				
						<div class='control-group'>
					<label class='control-label'>运营商</label>
					<div class='controls'>
						<input class='span8' id='isp' name="isp"
							placeholder='运营商' type='text' />
					</div>
				</div>
				
				
					<div class='control-group'>
					<label class='control-label'>视频url</label>
					<div class='controls'>
						<input class='span8' id='url' name="url" placeholder='请输入视频url' 
							type='text' />
					
<!-- 						<button class="tc-btn" data-id="upload_btn" style="position: relative; z-index: 1;">上传</button> -->
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

			<form action="/cms/VodUpload/upload" method="post" enctype=" multipart/form-data">
				<div class="input_area">
					<span>转 码: </span> 开启<input type="radio" name="transcode" value="1"
						checked=""> &nbsp;&nbsp;&nbsp;禁用<input type="radio"
						name="transcode" value="0">
				</div>
				<div class="input_area">
					<span>水 印: </span> 开启<input type="radio" name="watermark" value="1">
					&nbsp;&nbsp;&nbsp;禁用<input type="radio" name="watermark" value="0"
						checked="">
				</div>
				<div class="input_area">
					<span>分类ID: </span> <input type="input" name="classId" value="">
				</div>
				<div class='controls'>
					<input type="file" id="videoFile" name="file" multiple="multiple"
						class="form-control input-sm mb15" placeholder="请选择视频文件" required>
				</div>
				<button type="submit" id="start">确定</button>
			</form>
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
<!-- 				<div class="input_area"> -->
<!-- 					<span>分类ID: </span> <input type="input" name="classId" value=""> -->
<!-- 				</div> -->
<!-- 				<div class='controls'> -->
<!-- 					<input type="file" id="videoFile" name="file" multiple="multiple" -->
<!-- 						class="form-control input-sm mb15" placeholder="请选择视频文件" required> -->
<!-- 				</div> -->
<!-- 				<button type="submit" id="upload">上传</button> -->
<!-- 				</div> -->
			
			<div class='modal-footer'>
				<msg id='msg'></msg>
				<button type="button" id="btnClose" class='btn'>关闭</button>
				<button  id="btnSubmit" class='btn btn-primary'>保存</button>
			</div>
<!-- 		</form> -->
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
				<div class='modal-body' style='padding-bottom: 100px;'>
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
						<input class='span8' id='editAuthor' name="editAuthor"
							placeholder='讲师' type='text' />
					</div>
				</div>
				
<!-- 				<div class='control-group'> -->
<!-- 					<label class='control-label'>知识点</label> -->
<!-- 					<input type="hidden" id="editKnowledgeId" /> -->
<!-- 					<div class='controls'> -->
<!-- 						<div class='input-append'> -->
<!-- 		               		<select id='editKnowledge'> -->
<!-- 		               		</select> -->
<!-- 		                </div> -->
<!-- 				</div>				 -->
<!-- 			</div> -->
			
			
          <div class="col-md-11 col-sm-12">
                <div class="form-group">
            		<label class="col-sm-3 control-label">知识点</label>
            		<div class="col-sm-9">
                    	<select id="editKnowledge"  name="editKnowledge"  style="width:300px;"></select>      
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

	<!-- 导入数据 -->
	<div id="modal_import" class="modal hide fade" tabindex="-1"
		role="dialog" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close"
						type="button">&times;</button>
					<h4 class="modal-title" style="font-family: '微软雅黑'"
						id="modal-title">Excel文件导入</h4>
				</div>
				<div class="modal-body">
					<form id="excelImportForm" enctype="multipart/form-data">
					
				<div class='control-group'>
					<label class=' control-label'>文件类型</label>
					<div class='controls'>
						<input type="radio" name="flag" value="1" id="radioSuccess"
								checked /> <label for="radioSuccess">office2003文件</label>
						<input type="radio" name="flag" value="1" id="radioSuccess"
								checked /> <label for="radioSuccess">office2007文件</label>
					</div>
				</div>
				
				<div class='control-group'>
					<label class=' control-label'>请选择文件：</label>
					<div class='controls'>
						<input type="hidden" id="shopId" name="shopId"	value="${shopId }">
						 <input type="file"id="excelFile" name="file" multiple="multiple"
						class="form-control input-sm mb15" placeholder="请选择excel文件" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"></label>
					<div class="col-sm-9">
						<button class="btn btn-info" id="btnaddGoods">提交</button>
					</div>
				</div>
									
<!-- 						<div class="row"> -->
<!-- 							<div class="col-md-12 col-sm-12"> -->
<!-- 								<div id="info"></div> -->
<!-- 								<div class="col-md-11 col-sm-12"> -->
<!-- 									<div class="form-group"> -->
<!-- 										<label class="col-sm-3 control-label" for="goodsName">文件类型：</label> -->
<!-- 										<div class="col-sm-9"> -->
<!-- 											<div class="col-md-6 col-sm-12"> -->
<!-- 												<div class="rdio rdio-success"> -->
<!-- 													<input type="radio" name="flag" value="1" id="radioSuccess" -->
<!-- 														checked /> <label for="radioSuccess">office2003文件</label> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="col-md-6 col-sm-12"> -->
<!-- 												<div class="rdio rdio-danger"> -->
<!-- 													<input type="radio" name="flag" value="2" id="radioWarning" /> -->
<!-- 													<label for="radioWarning">office2007文件</label> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="form-group"> -->
<!-- 										<label class="col-sm-3 control-label" for="goodsName">选择文件：</label> -->
<!-- 										<div class="col-sm-9"> -->
<!-- 											<input type="hidden" id="shopId" name="shopId" -->
<%-- 												value="${shopId }"> <input type="file" --%>
<!-- 												id="excelFile" name="file" multiple="multiple" -->
<!-- 												class="form-control input-sm mb15" placeholder="请选择excel文件" -->
<!-- 												required> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->

<!-- 								<div class="col-md-11 col-sm-12"> -->
<!-- 									<div class="form-group"> -->
<!-- 										<label class="col-sm-3 control-label"></label> -->
<!-- 										<div class="col-sm-9"> -->
<!-- 											<button class="btn btn-info" id="btnaddGoods">提交</button> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->

<!-- 							</div> -->
<!-- 						</div> -->
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>  
		seajs.use([ 'base', 'main/video/manage' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</body>
</html>
