// 所有模块都通过 define 来定义
define(function (require, exports, module) {
    // 通过 require 引入依赖
    var base = require("base");
    var E = module.exports = {
            basepath: '',
            init: function (basepath) {//初始化
                E.basepath = basepath;
                $('#verifyCodeImg').click(function () {
                    var $this = $(this);
                    E.changetVerifyCodeImg($this);
                });
                $('#verifyCodeImg').next('a').click(function () {
                        var $this = $(this).prev('img');
                        E.changetVerifyCodeImg($this);
                    }
                );
                $('#submit').click(function(){
                	E.login();
                });

                //表单回车
                $('#login-form input').keydown(function(e){
                    if(e.keyCode==13){
                        E.login();
                    }
                });
            },
            //改变验证码图片
            changetVerifyCodeImg: function ($this) {
                var src = $this.attr('src');
                $this.attr('src', src + '?t=' + new Date().getTime());
            }
            ,
            //登录
            login: function () {
                var options = {
                    //target: '#output',          //把服务器返回的内容放入id为output的元素中
                    beforeSubmit: E.showRequest,  //提交前的回调函数
                    success: E.showResponse,      //提交后的回调函数
                    url: E.basepath + '/user/check-login',                 //默认是form的action， 如果申明，则会覆盖
                    type: 'post',               //默认是form的method（get or post），如果申明，则会覆盖
                    dataType: 'json',           //html(默认), xml, script, json...接受服务端返回的类型
                    //clearForm: true,          //成功提交后，清除所有表单元素的值
                    //resetForm: true,          //成功提交后，重置所有表单元素的值
                    timeout: 30000               //限制请求的时间，当请求大于3秒后，跳出请求
                }
                $('#login-form').ajaxForm(options);
            },
            showResponse:function(data, status){//表单初始化
                base.ajaxSuccessData(data);
                if (data.ok) {
                	 window.location.href = E.basepath + "/main/index";
                } else {
                	//E.changetVerifyCodeImg($('#verifyCodeImg'));
                }
            },
            showRequest:function showRequest(formData, jqForm, options){
                //formData: 数组对象，提交表单时，Form插件会以Ajax方式自动提交这些数据，格式如：[{name:user,value:val },{name:pwd,value:pwd}]
                //jqForm:   jQuery对象，封装了表单的元素
                //options:  options对象
                return true;  //只要不返回false，表单都会提交,在这里可以对表单元素进行验证
            }
        };
})
;
