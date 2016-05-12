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
            $.ajax( {});
        }
        ,
        findMenus: function (menus) {}
    };

});
