// 所有模块都通过 define 来定义
define(function(require, exports, module) {
	var base = require('base');
	var core = require('core');
	var obj = module.exports = {			
			init : function(_basepath) {
				$("#copyUrl").click(function(){
					core.Copy("serverUrl");
				})
				
			}
	}
	
	});