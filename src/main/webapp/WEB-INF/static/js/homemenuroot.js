//vi365网站功能引导菜单
jQuery(document).ready(function(e) {
	//菜单加载
	$.cookie('hmenuid',null);
	$.cookie('menulink',null);
	var Url = 'home/common/menuRoot_view.html';
	//首页(默认加载)
	$.cookie('hmenuid','00');
	loadView(Url);
				
	//注册
	jQuery('#vireg').on('click',this,function(){
	$.cookie('hmenuid','01');
	loadView(Url);
	});
	//登录
	jQuery('#vilogin').on('click',this,function(){
	$.cookie('hmenuid','02');
	loadView(Url);
	});
	//众筹小爱
	jQuery('#heartCrowd').on('click',this,function(){
	$.cookie('hmenuid','10');
	loadView(Url);
	});
	//微金理财
	jQuery('#viClaim').on('click',this,function(){
	$.cookie('hmenuid','11');
	loadView(Url);
	});
	//新手指引
	jQuery('#newHelper').on('click',this,function(){
	$.cookie('hmenuid','12');
	loadView(Url);
	});
	//公司简介
	jQuery('#companyinfo').on('click',this,function(){
	$.cookie('hmenuid','131');
	loadView(Url);
	});
	//合作伙伴
	jQuery('#partner').on('click',this,function(){
	$.cookie('hmenuid','132');
	loadView(Url);
	});
	//行业资讯
	jQuery('#tradeNews').on('click',this,function(){
	$.cookie('hmenuid','133');
	loadView(Url);
	});
	//媒体报道
	jQuery('#mediaNews').on('click',this,function(){
	$.cookie('hmenuid','134');
	loadView(Url);
	});

});

function loadView(href){
        	jQuery('#loading').show();
        	jQuery('#mainconcent').empty().load(href,function(){
				success:{jQuery('#loading').fadeOut()};
			});          		
        }