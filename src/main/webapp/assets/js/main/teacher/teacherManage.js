var path = "";
define(function(require, exports, module) {
	var base = require('base');
		core = require('core');
	// 查询参数
	var value = "";
	var type = "";
	
	// 通过 require 引入依赖
	var F = module.exports = {
		table : new core.Table('teacherTable'),
		init : function(_basepath) {
			F.basepath = _basepath;
			/**
			 * 是否具有查询知识点权限
			 */
			 if(base.perList.teacher.check){
			$("#school-header .actions")
					.append("<input autocomplete='off'  id='teacherName'  placeholder='teacher昵称' type='text' />&nbsp;&nbsp;" +
							"<select id='subjectOption' style='width:6%'><select/>&nbsp;&nbsp;<select id='phaseOption' style='width:6%'><select/>" +
							"<a href='#' id='queryByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");
				core.getDictOptions("科目","subject","#subjectOption");
				core.getDictOptions("学段","phase","#phaseOption");
			 }
			 
			 if(base.perList.teacher.add){
			$("#school-header .actions")
				.append("&nbsp;&nbsp;<a href='#' id='addTeacher'  class='btn btn-success btn-small' " +
						"style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
			 }
			 if(base.perList.teacher.del){
			$("#school-header .actions")
			.append("&nbsp;&nbsp;<a href='#' id='delTeacher'  class='btn btn-success btn-small' " +
					"style='margin-left:5px;margin-bottom:11px'>批量删除</a>");
			 }
			
			/**
			 * 打开模态框
			 */
			$('#addTeacher').click(function() {
				core.openModel('addModal-teacher', '添加教师', function() {
					core.getRegionList("#addProvince",0);
					core.getDictOptions("年级","grade","#addGrade");
					core.getDictOptions("科目","subject","#addSubject");
				});
			});
	
			//监听省份选择
			$("#addProvince").change(function(){
				$("#addCity").empty();
				$("#addCounty").empty();
				$("#addSchool").empty();
				var province =$("#addProvince").val();
				$("#addSchool").append("<option value='-1'>..学校..</option>");
				$("#addCounty").append("<option value='-1'>..县/区..</option>");
				if(province==-1){
					$("#addCity").append("<option value='-1'>..市..</option>");
					return;
				}
				core.getRegionList("#addCity",province);
			});
			//监听市选择
			$("#addCity").change(function(){
				$("#addCounty").empty();
				$("#addSchool").empty();
				$("#addSchool").append("<option value='-1'>..学校..</option>");
				var city =$("#addCity").val();
				if(city ==-1){
					$("#addCounty").append("<option value='-1'>..县/区..</option>");
					return;
				}
				core.getRegionList("#addCounty",city);
			});
			//监听区/县选择
			$("#addCounty").change(function(){
				$("#addSchool").empty();
				var county =$("#addCounty").val();
				if(county ==-1){
					$("#addCounty").append("<option value='-1'>..县/区..</option>");
					return;
				}
				core.getSchoolList("#addSchool",county);
			});
			
			//关系添加模态框
			$('#btnClose').click(function(){
				clean()
			});
			//关闭荣誉编辑模态框
			$("#HonourBtnClose").click(function(){
				honourClean();
			});
//			荣誉清理器
			function honourClean(){
				core.closeModel('addModal-Honour');
				$("#honours span").remove();
				$("#addHonour").removeAttr("disabled");	
				$("#honours").removeAttr('teacherId');
				$("#msg").text('');
				tag=1;
				
			}
			
			
			
//			清理器
			function clean(){
				core.closeModel('addModal-teacher');
				$("#addProvince").empty();
				$("#addCity").empty();
				$("#addCounty").empty();
				$("#addSchool").empty();
				$("#addGrade").empty();
				$("#addSubject").empty();
				$("#addCounty").append("<option value='-1'>..县/区..</option>");
				$("#addCity").append("<option value='-1'>..市..</option>");
				$("#addSchool").append("<option value='-1'>..学校..</option>");
				$("#user").val('');
				$("#userId").attr('userId','0');
				$("#submitbutton").removeAttr('imgName');
				$("#headPic").attr('src','#');
				$("#imgFile").val('');
				$("#motto").val('');
				$("#info").val('');
				$("#addName").val('');
				$("#isFamous").removeAttr('checked');
			}
			
			//监听用户名输入
			$("#user").blur(function(){
				var username=$("#user").val();	
				if(username==""){
					$("#user").attr("placeholder","");
					$("#user").attr('userId',0);
					return ;
				}
				
				$.ajax({
					url : F.basepath + '/user/getUser',
					type : 'GET',
					data : {username:username },
					success : function(data){
						
						if(data.result<1){
							$("#user").val('');
							$("#user").attr("placeholder","用户不存在！无法使用");
							$("#user").attr('userId',0);
						}else{
							$("#user").attr('userId',data.value.id);
							$("#user").css('color','blue');
						}
						
						
					}
				});
				
				
			});
			

			// 定义表格的头
			var cols = [ {

				checkbox : true
			}, {
				field : 'id',
				title : '主键',
				visible : false
			}, {
				field : 'name',
				title : '昵称'
			},{
				field : 'userName',
				title : '用户名'
			},{
				field : 'schoolName',
				title : '所在学校'
			},{
				field : 'subject',
				title : '科目'
			}, {
				field : 'phase',
				title : '学段'
			} 	];
			
			
			
			operateEvents={
					'click .editHonour': function(e, value, row, index){
						core.openModel('addModal-Honour', '编辑荣誉', function(){
							var html=''
							$.ajax({
								url : F.basepath + '/teacher/getHonours',
								type : 'GET',
								data : {teacherId:row.id},
								success : function(data) {
									var length = data.value.length;
									tag =length==0?1:length+1;
									if(length>9){$("#addHonour").attr("disabled","disabled");}
									if(length>0){
										$("#delHonour").removeAttr("disabled");
										for(var i=0;i<length;i++){
										html+="&nbsp;&nbsp;<span class='"+(i+1)+"span'>时间：" +
											"<input style='width:124px' type='date' name='dateTime' value='"+data.value[i].time+"'/>" +
											" &nbsp称号：<input type='text' value='"+data.value[i].title+"' onKeypress='javascript:if(event.keyCode == 32)event.returnValue = false' maxlength='100' name='title' />" +
											"&nbsp详情：<input type='text' value='"+data.value[i].content+"' onKeypress='javascript:if(event.keyCode == 32)event.returnValue = false' maxlength='400' name='detail' />" +
											"<br></span>"
										}
										$("#honours").append(html);
									}
								}	
							});
							
							
							
							$("#honours").attr('teacherId',row.id);
						});
	            	},
		
			}
			
			//编辑荣誉
			var tag=1;
			$("#addHonour").click(function(){
				
				var html ="&nbsp;&nbsp;<span class='"+tag+"span'>时间：" +
						"<input style='width:124px' type='date' name='dateTime' />" +
						" &nbsp称号：<input type='text' onKeypress='javascript:if(event.keyCode == 32)event.returnValue = false' maxlength='100' name='title' />" +
						"&nbsp详情：<input type='text' onKeypress='javascript:if(event.keyCode == 32)event.returnValue = false' maxlength='400' name='detail' />" +
						"<br></span>"
				
				if(tag>0){
					$("#delHonour").removeAttr("disabled");	
				}
				if(tag>10){
					$("#addHonour").attr("disabled","disabled");
					return;
				}
				$("#honours").append(html);
				tag+=1;
				
			});
			
			$("#delHonour").click(function(){
				tag-=1
				if(tag==10){
					$("#addHonour").removeAttr("disabled");	
				}
				$("#honours ."+tag+"span").remove();
				if(tag==1){
					$("#delHonour").attr("disabled","disabled");
					return;
				}								
			})	
			
			
			//提交荣誉
			$("#HonourBtnSubmit").click(function(){
			var length =$("#honours span").length;
			var teacherId = $("#honours").attr("teacherid");
			var arr =new Array();
			for(var i=1;i<length+1;i++){
				var dateTime = $("."+i+"span input[name = dateTime]").val();
				var title = $("."+i+"span input[name = title]").val();
				var detail = $("."+i+"span input[name = detail]").val();
				if(dateTime==[]){$("#honourMsg").text("时间不能为空！");return;}
				if(title.length<1){$("#honourMsg").text("称号不能为空！");return;}
				if(detail.length<1){$("#honourMsg").text("详情不能为空！");return;}
				arr.push({dateTime:dateTime,title:title,detail:detail});				
			}
			
			$.ajax({
				url : F.basepath + '/teacher/addHonours',
				type : 'POST',
				data : {teacherId:teacherId,arr:JSON.stringify(arr)},
				success : function(data) {					
						honourClean();					
				}
			
			})
			
			
			
			console.log(JSON.stringify(arr));	
			
			
			});
			
			// 是否需要操作列
//			 if(base.perList.school.confine)
			cols.push({
				align : 'center',
				title : '操作',
			    events: operateEvents,
				formatter : F.operateFormatter
			});

			/**
			 * 用户列表
			 */
			F.table.init(F.basepath + '/teacher/listPage', cols);
			
			/**
			 * 添加学校提交表单
			 */
			
			$("#btnSubmit").click(function(){
			var name = $("#addName").val();
			var sex = $("#addSex").val==1?"男":"女";
			var school = $("#addSchool").val();
			var grade = $("#addGrade").val();
			var subject = $("#addSubject").val();
			var userId = $("#user").val();
			var motto = $("#motto").val();
			var info = $("#info").val();
			var phaseNo = grade==19?61:grade==20?61:grade==21?61:60;
			var isFamous = $("#isFamous").attr('checked')=='checked'?1:0;
			var imgName = $("#submitbutton").attr('imgName');
			var userId = $("#user").attr("userId");
			if(name<1){$("#msg").text("请输入姓名！");return }
			if(grade<1){$("#msg").text("请选择年级！");return }
			if(subject<1){$("#msg").text("请选择学科！");return }
			if(info.length<1){$("#msg").text("请输入教师信息！");return}
			if(school<1){$("#msg").text("请选择学校！");return }
			if(imgName==undefined){$("#msg").text("请上传头像！");return};
			$("#msg").text("");
				var data ={
						name:name,
						sex:sex,
						headPicture:imgName,
						schoolId:school,
						subjectNo:subject,
						gradeNo:grade,
						userId:userId, 
						motto:motto,
						info:info,
						phaseNo:phaseNo,
						isFamous:isFamous
				}
			$.ajax({
				url : F.basepath + '/teacher/add',
				type : 'POST',
				data : data,
				success : function(data) {
					if (data.result>0) {
						clean();
						F.reload();
					}else if(data.result==-1){
						$("#msg").text(data.msg);
					}else {
						alert("异常请稍后再尝试！");
					}
				}
			});
			})
			
			/**
			 * 批量删除教材
			 */
			$('#delTeacher').click(function(){
				var ids = F.table.getIdSelections();
				if(ids!=null&&ids.length>0){
					base.bootConfirm("是否确定删除选定的"+ids.length+"个教师？",function(){
						F.delTeachers(ids);
						F.reload();
					});
				}else{
					base.bootAlert({"ok":false,"msg":"请选择你要删除的教师！"});
				}
			});
					

			/**
			 * 根据条件查询
			 */
			$('#queryByCondition').click(function() {
						var name = $("#teacherName").val();
						var subjectNo = $("#subjectOption").val();
						var phaseNo = $("#phaseOption").val();
						var query_url = F.basepath + '/teacher/listPage?name='
								+name+'&subjectNo='+subjectNo+'&phaseNo='+phaseNo;
						$('#teacherTable').bootstrapTable('refresh', {
							url : query_url
						});
					})

		},
		//根据id删除教师
		delTeachers:function(ids){
        	$.ajax({
        		url:F.basepath+'/teacher/delete',
        		type:'post',
        		data:{ids:ids.toString()},
        		success:function(data){
        			if(data.result>0){
        			}
        		}
        	})
        },
        reload:function(){
        	F.table.reload();
        }
        ,operateFormatter : function(value, row, index) {
			var _btnAction = "";
			 if (base.perList.teacher.editHonour) {
			_btnAction += "<a class='editHonour btn btn-primary btn-small' href='#' title='编辑教师荣誉' style='margin-left:5px'>编辑教师荣誉</a>"
			 }
			return _btnAction;
		},
	
		
	};
	
//	oss上传
	 jQuery(document).ready(function() {
		 $(function() {
				$("#upload").ajaxForm({
					//图片上传的文件夹
					
					data :
					{key:"headPicture/",
					fileName:"imgFile"},
					beforeSend : function() {
					
					$("#submitbutton").attr("disabled","disalbed");
					},
					success : function(data) {
						$("#submitbutton").attr("disabled",false);
						//提交成功后调用
						if (data.value!=null) {
							if(data.value.status == 0){
								var file = $("#imgFile").val();
								var imgName =getFileName(file);
								$("#submitbutton").attr('imgName',imgName);
								$("#headPic").attr('src',"http://ekt.oss-cn-shenzhen.aliyuncs.com/headPicture/"+imgName);
								$("#imgFile").val('');
							}
						} else {
							alert("上传异常 ")
						}
					}
				});
			});
		 
		//获取带后缀名的文件名
			function getFileName(o){
			    var pos=o.lastIndexOf("\\");
			    return o.substring(pos+1);  
			}
		 
	 });
});