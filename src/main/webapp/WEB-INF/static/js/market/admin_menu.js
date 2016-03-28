/**
 * 菜单js
 */
jQuery(document).ready(function (e) {
	
});

/**
 * 菜单事件
 * @param text
 * @param url
 */
function menuClick(flag){
	var text = '';
	var url = '';
	var class_name = '';
	if(flag==1){
		text='商铺查询';
		url='shop/toShopPage';
		class_name='fa fa-info';
	}else if(flag==2){
		text='地域管理';
		url='region/toListRegion';
		class_name='fa fa-info';
	}else if(flag==3){
		text='商品类型';
		url='goodstype/toListGoodstype';
		class_name='fa fa-info';
	}else if(flag==4){
		text='商品库';
		url='goods/toListGoods';
		class_name='fa fa-info';
	}else if(flag==5){
		text='商家商品管理';
		url='shopgoods/toListShopGoods';
		class_name='fa fa-info';
	}
	loadView(url,text,class_name);
}

/**
 * 加载内容
 * @param href：内容链接
 */
function loadView(url,text,class_name){
	jQuery('div.media-body li.active').text(text);
	jQuery('div.media-body h4#menuTitle').text(text);
	jQuery('div.media i#navIcon').addClass(class_name);
	
	jQuery('#loading').show();
	//清空mainView内容，加载新内容
	jQuery('#mainView').empty().load(url,null,function(data,status,xhr){ 
			jQuery('#loading').fadeOut();  				
	});          		
}