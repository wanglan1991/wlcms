// 所有模块都通过 define 来定义
define(function (require, exports, module) {
    var base = require('base');
    var core = require('core');
    // 通过 require 引入依赖
    var F = module.exports = {
        basepath: '',
        table:new core.Table('logTable'),
        init: function (_basepath) {
            F.basepath = _basepath;
            var cols = [
	                    {
	        		        checkbox:true
	        		    }, {
	        		        field: 'account',
	        		        title: '账号'
	        		    }, {
	        		        field: 'loginIp',
	        		        title: 'IP'
	        		    },{
	    			        field: 'showLoginTime',
	    			        title: '登录时间'
	    		        },{
	    			        field: 'address',
	    			        title: '登录地址'
	    		        },{
	    			        field: 'detailAddress',
	    			        title: '详细地址'
	    		        },{
	    			        field: 'pointX',
	    			        title: '经度'
	    		        },{
	    			        field: 'pointY',
	    			        title: '纬度'
	    		        },{
	    			        field: 'province',
	    			        title: '省'
	    		        },{
	    			        field: 'city',
	    			        title: '城市'
	    		        },{
	    			        field: 'cityCode',
	    			        title: '城市码'
	    		        }];
            F.table.init(F.basepath+'/main/log/query-login',cols);
        }
    };
});
