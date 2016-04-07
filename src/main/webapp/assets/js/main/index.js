// 所有模块都通过 define 来定义
define(function (require, exports, module) {
    var base = require('base');
    base.init()
    // 通过 require 引入依赖
    var F = module.exports = {
        basepath: '',
        icons:base.icons,
        init: function (_basepath) {
            F.basepath = _basepath;
            //菜单导航初始化
            $.ajax( {
                url:F.basepath + '/main/menu/get-show-menus',// 跳转到 action
                type:'post',
                cache:false,
                dataType:'json',
                success:function(data) {
                    if (data.ok) {
                        var menusTxt='';
                        $.each(data.data, function(i, menu) {
                            menusTxt+='<li>';
                            if(menu.url==null||menu.url.length==0){
                                menu.url='#';
                            }else
                            	menu.url=F.basepath+menu.url;
                            if(menu.children.length>0) {
                                menusTxt += '<a id="' + menu.id + '" target="mainFrame" class="dropdown-collapse ';
                                if(i==0)
                                    menusTxt += ' in" href="' + menu.url + '">';
                                else
                                    menusTxt += '" href="' + menu.url + '">';
                            }else
                                menusTxt+='<a id="'+menu.id+'" target="mainFrame" href="'+menu.url+'">';

                            var n = base.random(0,F.icons.length-1);    
                            if(menu.icon!=null&&menu.icon.length>0)
                                menusTxt+='<i class="'+menu.icon+'"></i>';
                            else
                                menusTxt+='<i class="'+F.icons[n]+'"></i>';
                            menusTxt+='<span>'+menu.text+'</span>';

                            if(menu.children.length>0)
                                if(i==0)
                                    menusTxt+='<i class="icon-angle-up angle-up"></i>';
                                else
                                    menusTxt+='<i class="icon-angle-down angle-down"></i>';
                            menusTxt+='</a>';

                            if(menu.children.length>0){
                            	if(i==0)
                            		menusTxt+='<ul class="in nav nav-stacked">';
                            	else
                            		menusTxt+='<ul class="nav nav-stacked">';
                                menusTxt+=F.findMenus(menu.children);
                                menusTxt+='</ul>';
                            }
                            menusTxt+='</li>';
                        });
                        $('ul#navigation-menu').append(menusTxt);
                        $('ul#navigation-menu').find('li a:first').click();
                    }
                },
                error : function() {
                    base.loadError();
                }
            });
        }
        ,
        findMenus: function (menus) {
            var menusTxt='';
            $.each(menus, function(i, menu) {
                menusTxt+='<li>';
                if(menu.url==null||menu.url.length==0){
                    menu.url='#';
                }else
                	menu.url=F.basepath+menu.url;
                if(menu.children.length>0) {
                    menusTxt += '<a id="' + menu.id + '" target="mainFrame" class="dropdown-collapse ';
                    if(i=0)
                        menusTxt += ' in" href="' + menu.url + '">';
                    else
                        menusTxt += '" href="' + menu.url + '">';
                }else
                    menusTxt+='<a id="'+menu.id+'" target="mainFrame" href="'+menu.url+'">';

                var n = base.random(0,F.icons.length-1); 
                if(menu.icon!=null&&menu.icon.length>0)
                    menusTxt+='<i class="'+menu.icon+'"></i>';
                else
                    menusTxt+='<i class="'+F.icons[n]+'"></i>';
                menusTxt+='<span>'+menu.text+'</span>';

                if(menu.children.length>0)
                        menusTxt+='<i class="icon-angle-down angle-down"></i>';
                menusTxt+='</a>';

                if(menu.children.length>0){
                    menusTxt+='<ul class="nav nav-stacked">';
                    menusTxt+=F.findMenus(menu.children);
                    menusTxt+='</ul>';
                }
                menusTxt+='</li>';
            });
            return menusTxt;
        }
    };

});
