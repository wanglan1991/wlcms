define(function(require, exports, module) {

	var base = require('base');
	var E = module.exports = {
		Copy: function (id){ 
		        var e=document.getElementById(id);//对象是content 
		        e.select(); //选择对象 
		        document.execCommand("Copy"); //执行浏览器复制命令 
		    } ,
		/**
		 * TREE
		 */
		CoreTree : function(_treeId, _url, _setting) {
			this.treeId = _treeId;
			this.url = _url;
			this.load = function(_data, _hiddenId) {
			}
			return this;
		},

		setUeditor : function(id,frameHeight,frameWidth){
			var editor = new UE.ui.Editor({
				initialFrameHeight : frameHeight,
				initialFrameWidth : frameWidth,
				});  
			editor.render(id);
			
			return editor;
			
		},
		//去空格
		Strim:function(str){
			return str.replace(/(^\s*)|(\s*$)/g,'');		
		}
		,
		//转换成图片
		ektImageTransformation:function(content){
			var reg= new RegExp('<//','g');
			var reg1= new RegExp('//>','g');
			var image=content.replace(reg,"<img  src='http://ekt.oss-cn-shenzhen.aliyuncs.com/");
			return image.replace(reg1,"' >");
			
		}
		,
		
		// 时间转换器
		dateTimeFormatter : function(date) {
			var t = Date.parse(date);
			if (!isNaN(t)) {
				return new Date(Date.parse(date.replace(/-/g, "/")));
			} else {
				return new Date();
			}
		},
		// 获取带后缀名的文件名
		getFileName : function(o) {
			var pos = o.lastIndexOf("\\");
			return o.substring(pos + 1);
		},
		// 公共字典opitons
		getDictOptions : function(type, dictType, selectId) {
			$(selectId).empty();
			var optionsHtml = "<option value='0'>" + type + "...</option>";
			$.ajax({
				url : '/cms/dict/queryDictByCondition',
				type : "GET",
				data : {
					typeEncoding : dictType
				},
				success : function(data) {
					for (var i = 0; i < data.value.length; i++) {
						optionsHtml += "<option value='" + data.value[i].id
								+ "'>" + data.value[i].value + "</option>"
					}
					$(selectId).append(optionsHtml);
				}
			});
		},
		/**
		 * 去空格
		 */
		trim : function(str, is_global) {
			return str.replace(/(^\s*)|(\s*$)/g, "");
		},
		// 加载编辑字典
		getEditDictOptions : function(type, dictType, selectId, value) {
			$(selectId).empty();
			var optionsHtml = "<option value='0'>" + type + "...</option>";
			$.ajax({
				url : '/cms/dict/queryDictByCondition',
				type : "GET",
				data : {
					typeEncoding : dictType
				},
				success : function(data) {
					for (var i = 0; i < data.value.length; i++) {
						if (data.value[i].id == value) {
							optionsHtml += "<option value='" + data.value[i].id
									+ "' selected='true' >"
									+ data.value[i].value + "</option>"
						} else {
							optionsHtml += "<option value='" + data.value[i].id
									+ "'  >" + data.value[i].value
									+ "</option>"
						}
					}
					$(selectId).append(optionsHtml);
				}
			});
		},
		// 地域字典options
		getRegionList : function(idOrClass, parentCode) {
			var html = "";
			$.ajax({
				url : '/cms/region/getRegionList',
				type : 'POST',
				data : {
					parentCode : parentCode
				},
				success : function(data) {
					for (var i = 0; i < data.value.length; i++) {
						html += "<option value='" + data.value[i].area_code
								+ "'>" + data.value[i].area_name + "</option>"
					}
					$(idOrClass).append(html);
				}
			})

		},
		/**
		 * 获取treeId
		 */
		getTreeIds : function(idOrClass) {
			var treeObj = $.fn.zTree.getZTreeObj(idOrClass);
			var arrId = '';
			var nodes = treeObj.transformToArray(treeObj.getNodes());
			for (var i = 0; i < nodes.length; i++) {
				if (nodes[i].checked == true) {
					if (nodes[i].id == 0||nodes[i].pId==null) {
						continue;
					}
					arrId += nodes[i].id + ',';
				}
			}
			return arrId;
		},
		/**
		 * 获取自定义Tree 主键以及过期时间
		 */
		getDiyTreeResult:function(idOrClass){
			var treeObj = $.fn.zTree.getZTreeObj(idOrClass);
			var nodes = treeObj.transformToArray(treeObj.getNodes());
			var result =new Array();
			for (var i = 0; i < nodes.length; i++) {
				if(nodes[i].checked==true){
					if (nodes[i].id == 0||nodes[i].pId==null) {
						continue;
					}
					var expireTime=$("#diyBtn_" +nodes[i].id+" input").val();
					result.push({id:nodes[i].id,expireTime:expireTime});
				}
			}
			return result;
		},
		
		
		
		/**
		 * 加载编辑状态下的知识点
		 */
		editGetKnoeledgeOption : function(id, gradeNo, subjectNo, knowledgeId) {
			$(id).empty();
			var knowledgesOption = "";
					$.ajax({
						url : "/cms/knowledge/queryByCondition",
						type : "GET",
						data : {
							subjectNo : subjectNo,
							gradeNo : gradeNo
						},
						success : function(data) {
							if (data.value.length < 1) {
								knowledgesOption += "<option value = '0'>-- 无 --</option>";
							}
							for (var i = 0; i < data.value.length; i++) {
								if (data.value[i].id == knowledgeId) {
									knowledgesOption += "<option value = '"+ data.value[i].id+ "' selected='selected' >"
											+ data.value[i].title + "</option>"
								} else {
									knowledgesOption += "<option value = '"+ data.value[i].id + "'>"+ data.value[i].title + "</option>"
								}
							}
							
							$(id).append(knowledgesOption);
						}
					})
		},

		// 学校字典options
		getSchoolList : function(idOrClass, cityCode) {
			var html = "";
			$.ajax({
				url : '/cms/school/schoolList',
				type : 'GET',
				data : {
					cityCode : cityCode
				},
				success : function(data) {
					if (data.value.length < 1) {
						html += "<option value='-1'>该地区学校还暂未录入！</option>"
						$(idOrClass).css('color', 'red');
					}
					for (var i = 0; i < data.value.length; i++) {
						html += "<option value='" + data.value[i].id + "'>"
								+ data.value[i].school_name + "</option>";
						$(idOrClass).css('color', '#3c3c3c');
					}
					$(idOrClass).append(html);
				}
			})

		},
		/**
		 * 获取教材章节树
		 */
		catalogTree : function(_url, textbookId,idOrClass) {
			var setting = {
				check : {
					enable : true,
					chkboxType : {
						"Y" : "ps",
						"N" : "ps"
					}
				},
				view : {
					dblClickExpand : false
				},
				data : {
					simpleData : {
						enable : true
					}
				}
			};
			$.ajax({
				url : _url,
				type : 'GET',
				data : {
					textbookId : textbookId
				},
				success : function(data) {
					$.fn.zTree.init($(idOrClass), setting,
							data.value)
				}
			})

		},

		/**
		 * 加载权限树
		 */
		loadTree : function(_url, _roleId) {
			var setting = {
				check : {
					enable : true,
					chkboxType : {
						"Y" : "ps",
						"N" : "ps"
					}
				},
				view : {
					dblClickExpand : false
				},
				data : {
					simpleData : {
						enable : true
					}
				}
			};
			$.ajax({
				url : _url,
				type : 'GET',
				data : {
					roleId : _roleId
				},
				success : function(data) {
					$.fn.zTree.init($("#distributePermissionTree"), setting,
							data.value)
				}
			})
		},
		/**
		 * 加载知识点树
		 */
		loadKnowledgeTree : function(_url, subjectNo, gradeNo, idOrClass) {
			var setting = {
				check : {
					enable : true,
					chkboxType : {
						"Y" : "ps",
						"N" : "ps"
					}
				},
				view : {
					dblClickExpand : false
				},
				data : {
					simpleData : {
						enable : true
					}
				}
			};
			$.ajax({
				url : _url,
				type : 'GET',
				data : {
					subjectNo : subjectNo,
					gradeNo : gradeNo
				},
				success : function(data) {
					$.fn.zTree.init($(idOrClass), setting, data.value)
				}
			})
		},
		/**
		 * 加载教材知识点树
		 */
		editloadKnowledgeTree : function(_url, gradeNo, subjectNo,
				knowledgePointArr, idOrClass) {
			$(idOrClass).empty();
			var setting = {
				check : {
					enable : true,
					chkboxType : {
						"Y" : "ps",
						"N" : "ps"
					}
				},
				view : {
					dblClickExpand : false
				},
				data : {
					simpleData : {
						enable : true
					}
				}
			};
			$.ajax({
				url : _url,
				type : 'get',
				data : {
					gradeNo : gradeNo,
					subjectNo : subjectNo,
					knowledgePointArr : knowledgePointArr
				},
				success : function(data) {
					$.fn.zTree.init($(idOrClass), setting, data.value)
				}
			})
		},
		/**
		 * 通用tree控件
		 * 
		 */
		commonTree : function(_url, obj, idOrClass) {
			$(idOrClass).empty();
			var setting = {
				edit: {
						enable: true,
						showRemoveBtn: true
					},	
				check : {
					enable : true,
					chkboxType : {
						"Y" : "ps",
						"N" : "ps"
					}
				},
				view : {
					dblClickExpand : false
				},
				data : {
					simpleData : {
						enable : true
					}
				}
			};
			$.ajax({
				url : _url,
				type : 'GET',
				data : obj,
				success : function(data) {
					$.fn.zTree.init($(idOrClass), setting, data.value)
				}
			})
		},
		
		
		
		
		/*
		 * 自定义tree
		 */
		
		diyTree : function(_url, obj, idOrClass) {
			$(idOrClass).empty();
			IDMark_A = "_a";
			//回调事件
			var dealFun={
					//zTree选中回调事件
					zTreeOnCheck:function (event, treeId, treeNode) {  
						if(treeNode.checked){
							$("#diyBtn_" +treeNode.id+" input").removeAttr("disabled");
							$("#diyBtn_" +treeNode.id+" input").val(new Date(treeNode.defaultTime).format("yyyy-MM-dd"));
						}else{
							$("#diyBtn_" +treeNode.id+" input").attr("disabled","disabled");
							$("#diyBtn_" +treeNode.id+" input").val('');
						}
					},
					zTreeOnCheckAll:function(treeId, treeNode,event ){
						if(!treeNode.checked&&treeNode.name=='全部'){
							$("#"+treeId+" input").removeAttr("disabled");
							$("#"+treeId+" input[value='']").val(new Date(treeNode.defaultTime).format("yyyy-MM-dd"));
						}
						if(treeNode.checked&&treeNode.name=='全部'){
							$("#"+treeId+" input").attr("disabled","disabled");
							$("#"+treeId+" input").val('');
						}
					},
					addHoverDom:function (treeId, treeNode) {
						
					},
					removeHoverDom:function(treeId, treeNode) {
						
					},
					addDiyDom:function (treeId, treeNode) {
						if(treeNode.parent=='false'){
							var date=treeNode.expireTime==null?"":new Date(treeNode.expireTime).format("yyyy-MM-dd");
							var aObj = $("#" + treeNode.tId + IDMark_A);
							var editStr = "<span class='demoIcon' id='diyBtn_" +treeNode.id+ 
							"' title='过期时间' onfocus='this.blur();'>" +
							"<input type='date' id='diyInput_"+treeNode.id+"'  class='tree_privte_style' value='"+date+"' "+
							(treeNode.checked==true?"":"disabled='disabled'")+"/></span>";
							aObj.after(editStr);
							var btn = $("#diyInput_"+treeNode.id);
							if (btn) btn.bind("blur", function(){
								var defaultTime=new Date(treeNode.defaultTime);
								if($("#diyInput_"+treeNode.id).val()<=new Date().format("yyyy-MM-dd")){
									$("#diyInput_"+treeNode.id).val(new Date(treeNode.defaultTime).format("yyyy-MM-dd"));
								}
								
							});
						}
						
					}
						
					}
			
			var setting = {
				check : {enable : true,chkboxType : {"Y" : "ps","N" : "ps"}},
				callback: {onCheck: dealFun.zTreeOnCheck,beforeCheck:dealFun.zTreeOnCheckAll },  
				data : {simpleData : {enable : true}},
				view : {dblClickExpand : false,
						addHoverDom: dealFun.addHoverDom,
						removeHoverDom: dealFun.removeHoverDom,
						addDiyDom: dealFun.addDiyDom}
				};
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			$.ajax({
				url : _url,
				type : 'GET',
				data : obj,
				success : function(data) {
					$.fn.zTree.init($(idOrClass), setting, data.value)
				}
			})
		},
		
		/**
		 * 
		 * 赠送用户课程diy tree
		 */
		
		
		diyGiftCourseTree : function(_url, obj, idOrClass) {
			$(idOrClass).empty();
			IDMark_A = "_a";
			//回调事件
			var dealFun={
					//zTree选中回调事件
					zTreeOnCheck:function (event, treeId, treeNode) {  
						if(treeNode.checked){
							$("#diyBtn1_" +treeNode.id+" input").removeAttr("disabled");
							$("#diyBtn1_" +treeNode.id+" input").val(new Date(treeNode.defaultTime).format("yyyy-MM-dd"));
						}else{
							$("#diyBtn1_" +treeNode.id+" input").attr("disabled","disabled");
							$("#diyBtn1_" +treeNode.id+" input").val('');
						}
					},
					zTreeOnCheckAll:function(treeId, treeNode,event ){
						if(!treeNode.checked&&treeNode.name=='全部'){
							$("#"+treeId+" input").removeAttr("disabled");
							$("#"+treeId+" input[value='']").val(new Date(treeNode.defaultTime).format("yyyy-MM-dd"));
						}
						if(treeNode.checked&&treeNode.name=='全部'){
							$("#"+treeId+" input").attr("disabled","disabled");
							$("#"+treeId+" input").val('');
						}
					},
					addHoverDom:function (treeId, treeNode) {
						
					},
					removeHoverDom:function(treeId, treeNode) {
						
					},
					addDiyDom:function (treeId, treeNode) {
						if(treeNode.parent=='false'){
							var date=treeNode.expireTime==null?"":new Date(treeNode.expireTime).format("yyyy-MM-dd");
							var aObj = $("#" + treeNode.tId + IDMark_A);
							var editStr = "<span class='demoIcon' id='diyBtn1_" +treeNode.id+ 
							"' title='过期时间' onfocus='this.blur();'>" +
							"<input type='date' id='diyInput1_"+treeNode.id+"'  class='tree_privte_style' value='"+date+"' "+
							(treeNode.checked==true?"":"disabled='disabled'")+"/></span>";
							aObj.after(editStr);
							var btn = $("#diyInput1_"+treeNode.id);
							if (btn) btn.bind("blur", function(){
								var defaultTime=new Date(treeNode.defaultTime);
								if($("#diyInput1_"+treeNode.id).val()<=new Date().format("yyyy-MM-dd")){
									$("#diyInput1_"+treeNode.id).val(new Date(treeNode.defaultTime).format("yyyy-MM-dd"));
								}
								
							});
						}
						
					}
						
					}
			
			var setting = {
				check : {enable : true,chkboxType : {"Y" : "ps","N" : "ps"}},
				callback: {onCheck: dealFun.zTreeOnCheck,beforeCheck:dealFun.zTreeOnCheckAll },  
				data : {simpleData : {enable : true}},
				view : {dblClickExpand : false,
						addHoverDom: dealFun.addHoverDom,
						removeHoverDom: dealFun.removeHoverDom,
						addDiyDom: dealFun.addDiyDom}
				};
			
			$.ajax({
				url : _url,
				type : 'GET',
				data : obj,
				success : function(data) {
					$.fn.zTree.init($(idOrClass), setting, data.value)
				}
			})
		},
		
		
		/**
		 * MODAL
		 */
		openModel : function(modelId, title, callback) {
			base.validateClear();
			var form = $('.validate-form');
			form.clearForm();
			form.find("input:hidden").each(function(i, item) {
				$(item).val("");
			})
			$('#' + modelId + ' .modal-header h3').html(title);
			if (callback)
				callback();
			$('#' + modelId).modal('show');

		},
		closeModel : function(modelId, callback) {
			$('#' + modelId).modal('hide');
			if (callback)
				;
		},
		/**
		 * Table
		 */
		Table : function(_tableId) {
			this.tableId = _tableId;
			this.init = function(_url, _cols) {
				$('#' + _tableId).bootstrapTable({
					locale : 'zh-CN',
					height : 490,
					striped : true,
					sidePagination : 'server',
					pagination : true,
					pageList : '[10, 20, 30]',
					url : _url,
					method : 'get',
					searchAlign : "left",// 查询框对齐方式
					queryParamsType : "limit",// 查询参数组织方式
					search : false,// 是否显示右上角的搜索框
					searchOnEnterKey : true,// 回车搜索
					showRefresh : false,// 刷新按钮
					columns : _cols
				});

			}

			this.query = function(_query) {
				$('#' + _tableId).bootstrapTable('refresh', _query);
			}
			this.getIdSelections = function() {
				return $.map($('#' + _tableId).bootstrapTable('getSelections'),
						function(row) {
							return row.id
						});
			}

			this.reload = function() {
				$('#' + _tableId).bootstrapTable('refresh', {
					silent : true
				});
			}
		},
		/**
		 * Picture Select
		 */
		PicSelect : function(_hiddenId) {
			var guid = base.guid();
			var _menuId = 'menu_' + guid;
			var _showId = 'show_' + guid;
			var _menuBtn = 'menuBtn_' + guid;
			var icons = base.icons;
			$('#' + _hiddenId).css({
				"display" : "none"
			});
			var icon = "icon-th";
			if ($('#' + _hiddenId).val() != null
					&& $('#' + _hiddenId).val() != "")
				icon = $('#' + _hiddenId).val();
			$("#" + _hiddenId).after(
					'<i id="' + _showId + '" class="' + icon + '"/>'
							+ '<button id="' + _menuBtn
							+ '" type="button" style="margin-left:30px">'
							+ '<i class="icon-building"></i>' + '</button>');
			var pic = '<div id="'
					+ _menuId
					+ '" style="display:none; position: absolute;border: 1px solid #617775;z-index:1051;background: #f0f6e4;width:250px;height:180px;overflow-y:auto;overflow-x:auto;"><table>';
			$.each(icons, function(i, item) {
				if (i % 15 == 0)
					pic += '<tr>';
				pic += '<td> <i class="' + item + '"/></td>';
				if (i % 15 == 14)
					pic += '</tr>';
			});
			pic += '</table></div>';
			$("body").append(pic);

			this.hid = _hiddenId;
			this.sid = _showId;
			this.set = function(hid, sid, pic) {
				if (pic != null && pic != "") {
					$('#' + hid).val(pic);
					$("#" + sid).attr("class", pic);
				} else {
					$('#' + hid).val("");
					$("#" + sid).attr("class", "icon-th");
				}
			}

			$("#" + _menuId).find("i").hover(function() {
				$(this).css("color", "#f34541");
			}, function() {
				$(this).css("color", "black");
			});

			$("#" + _menuId).find("i").click(function() {
				var color = $(this).attr("class");
				$("#" + _showId).attr("class", color);
				$("#" + _hiddenId).val(color);
				hide();
			});

			function hide() {
				$("#" + _menuId).fadeOut("fast");
				$("body").unbind("mousedown", onBodyDown);
			}

			var show = function() {
				var _input = $('#' + _showId);
				var _offset = $('#' + _showId).offset();
				$("#" + _menuId).css({
					left : _offset.left + "px",
					top : _offset.top + _input.outerHeight() + "px"
				}).slideDown("fast");
				$("body").bind("mousedown", onBodyDown);
			}

			var onBodyDown = function(event) {
				if (!(event.target.id == _menuBtn
						|| $(event.target).parents("#" + _menuBtn).length > 0
						|| event.target.id == _menuId || $(event.target)
						.parents("#" + _menuId).length > 0)) {
					hide();
				}
			}

			$('#' + _showId).click(function() {
				show();
			});

			$('#' + _menuBtn).click(function() {
				show();
			});
		}
	}

	// 时间格式化
	Date.prototype.format = function(format) {
		var o = {
			"M+" : this.getMonth() + 1, // month
			"d+" : this.getDate(), // day
			"h+" : this.getHours(), // hour
			"m+" : this.getMinutes(), // minute
			"s+" : this.getSeconds(), // second
			"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
			"S" : this.getMilliseconds()
		// millisecond
		}

		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}

		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}
})
