// 所有模块都通过 define 来定义
define(function (require, exports, module) {
    var base = require('base');
    var core = require('core');
    // 通过 require 引入依赖
    var F = module.exports = {
        basepath: '',
        tree:{},
        radioTree:{},
        websocket:null,
        table:new core.Table('monitorTable'),
        init: function (_basepath) {
            F.basepath = _basepath;
			/**
			 * 请求数据
			 */
	        F.treeLoad();
	        
	        var cols = [
	                    {
	        		        checkbox:true
	        		    }, {
	        		        field: 'hostname',
	        		        title: '主机名'
	        		    }, {
	        		        field: 'cpuCounts',
	        		        title: 'CPU数'
	        		    },{
	    			        field: 'cpuCores',
	    			        title: '内核数'
	    		        },{
	    			        field: 'memTotal',
	    			        title: '内存'
	    		        },{
	    			        field: 'status',
	    			        title: '状态',
	    			        formatter:F.showFormatter
	    		        },{
	    			        field: 'id',
	    			        title: '主键',
	    			        visible:false
	    		        }];
        		
    		F.table.init(F.basepath+'/main/monitor/get-list-monitors',cols);
    		

        },disconnect:function(){
        	if (F.websocket != null) {
        		F.websocket.close();
        		F.websocket=null;
        	}
        },onClick:function(event, treeId, treeNode, clickFlag) {
        	if(treeNode.id==0){
        		$("#monitor-table").css({"display":"block"});
        		$("#monitor-chart").css({"display":"none"});
        		F.table.query();
        	}else{
        		$("#monitor-table").css({"display":"none"});
        		$("#monitor-chart").css({"display":"block"});
        		F.disconnect();
        		var dataCPU = [];
    			var dataMEM = [];
    			var totalPoint=30;
    			
        		if (window['WebSocket'])
        			// ws://host:port/project/websocketpath
        			F.websocket = new WebSocket("ws://" + window.location.host + F.basepath + '/websocket'+'?hostname='+treeNode.name);
        		else
        			F.websocket = new SockJS("http://"+ window.location.host + F.basepath + '/websocket/socketjs'+'?hostname='+treeNode.name);
        		
        		F.websocket.onopen = function(event) {
        			console.log('open', event);
    				dataCPU = [];
        			dataMEM = [];
        		};
        		
        		F.websocket.onmessage = function(event) {
        			console.log('message', event.data);
        			var obj = eval("("+event.data+")");
        			var cpu=[];
        			if(obj.cpu!=null&&obj.memory!=null){
        				dataCPU.push(obj.cpu.toFixed(2));
        				dataMEM.push(obj.memory.toFixed(2));
        			}
        			if(dataCPU.length>15){
        				dataCPU=dataCPU.slice(1);
        				dataMEM=dataMEM.slice(1);
        			}
        			var data = [
    				         	{
    				         		name : 'cpu',
    				         		value:dataCPU,
    				         		color:'#0d8ecf',
    				         		line_width:2
    				         	},{
    				         		name : '内存',
    				         		value:dataMEM,
    				         		color:'#ef7707',
    				         		line_width:2
    				         	}
    				];
        			//var labels = ["2012-12-01","2012-12-02","2012-12-03","2012-12-04","2012-12-05","2012-12-06"];
        			var line = new iChart.LineBasic2D({
    					render : 'monitor-chart',
    					data: data,
    					align:'center',
    					title : '主机（'+treeNode.name+")",
    					subtitle : 'CPU和内存使用率（%）',
						footnote : '数据来源：'+treeNode.name,
    					width : 780,
    					height : 360,
    					sub_option:{
    						smooth : true,//平滑曲线
    						point_size:10
    					},
    					tip:{
    						enable:true,
    						shadow:true
    					},
    					legend : {
    						enable : false
    					},
    					crosshair:{
    						enable:true,
    						line_color:'#62bce9'
    					},
    					coordinate:{
    						width:600,
    						valid_width:500,
    						height:260,
    						axis:{
    							color:'#9f9f9f',
    							width:[0,0,2,2]
    						},
    						grids:{
    							vertical:{
    								way:'share_alike',
    						 		value:12
    							}
    						},
    						scale:[{
    							 position:'left',	
    							 start_scale:0,
    							 end_scale:100,
    							 scale_space:10,
    							 scale_size:2,
    							 scale_color:'#9f9f9f'
    						},{
    							 position:'bottom'	
    							 //labels:labels
    						}]
    					}
    				});
	    			//开始画图
	    			line.draw();
        		};
        	}
		},treeLoad:function(){
			F.tree = core.initTree("monitorTree",F.basepath+'/main/monitor/get-all-monitors',F.onClick);
        	F.tree.load();
        }, showFormatter:function (value, row, index) {
        	if(value==0)
        		return "<span class='label label-success'>正常</span>";
        	else
        		return " <span class='label label-warning'>异常</span>";
        }
    };
});
