define(function (require, exports, module) {
    var E = module.exports = {

    	icons:['icon-adjust','icon-anchor','icon-asterisk','icon-bar-chart','icon-beaker',
    	       'icon-beer','icon-bell-alt','icon-bell','icon-bolt','icon-book',
    	       'icon-bookmark-empty','icon-bookmark','icon-briefcase','icon-bullhorn','icon-bullseye',
    	       'icon-camera-retro','icon-camera','icon-certificate','icon-cloud','icon-coffee',
    	       'icon-cog','icon-cogs','icon-comment-alt','icon-comment','icon-dashboard',
    	       'icon-desktop','icon-download-alt','icon-download','icon-edit-sign','icon-edit',
    	       'icon-envelope-alt','icon-envelope','icon-eye-close','icon-eye-open','icon-facetime-video',
    	       'icon-fighter-jet','icon-film','icon-filter','icon-fire-extinguisher','icon-fire',
    	       'icon-food','icon-frown','icon-gamepad','icon-gift','icon-glass',
    	       'icon-globe','icon-group','icon-headphones','icon-heart-empty','icon-heart',
    	       'icon-home','icon-inbox','icon-key','icon-keyboard','icon-laptop',
    	       'icon-leaf','icon-legal','icon-lemon','icon-lightbulb','icon-location-arrow',
    	       'icon-lock','icon-magic','icon-magnet','icon-microphone-off','icon-microphone',
    	       'icon-money','icon-music','icon-off','icon-phone-sign','icon-phone',
    	       'icon-picture','icon-plane','icon-pushpin','icon-puzzle-piece','icon-qrcode',
    	       'icon-random','icon-refresh','icon-remove','icon-reorder','icon-retweet',
    	       'icon-road','icon-rocket','icon-rss-sign','icon-rss','icon-screenshot',
    	       'icon-search','icon-share-sign','icon-share','icon-shield','icon-shopping-cart',
    	       'icon-sign-blank','icon-signal','icon-signin','icon-signout','icon-sitemap',
    	       'icon-star-empty','icon-star','icon-tablet','icon-tag','icon-tags',
    	       'icon-tasks','icon-thumbs-down','icon-thumbs-up','icon-ticket','icon-time',
    	       'icon-tint','icon-trash','icon-trophy','icon-truck','icon-umbrella',
    	       'icon-unlock-alt','icon-unlock','icon-user-md','icon-user','icon-volume-up',
    	       'icon-volume-down','icon-warning-sign','icon-wrench','icon-zoom-in','icon-zoom-out'],
        init: function () {
            E.validateFormInit();
            bootbox.setLocale("cn");
        },validateFormInit:function(selector) {
	        if (selector == null) {
	            selector = $(".validate-form");
	        }
	        return selector.each(function(i, elem) {
	            return $(elem).validate({
	                errorElement: "span",
	                errorClass: "help-block error",
	                errorPlacement: function(e, t) {
	                    return t.parents(".controls").append(e);
	                },
	                highlight: function(e) {
	                    return $(e).closest(".control-group").removeClass("error success").addClass("error");
	                },
	                success: function(e) {
	                    return e.closest(".control-group").removeClass("error");
	                }
	            });
	        });
	    },validateClear:function(selector){
	        $(".validate-form input").each(function(i, elem) {
	    		$(this).closest(".control-group").removeClass("error");
	    		$(this).next("span").removeClass("help-block");
	        });
	    },guid:function () {
            function S4() {
                return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
             }
             return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
        },random:function(min,max){
	        return Math.floor(min+Math.random()*(max-min));
	    },bootAlert:function(data){
        	if (data.ok) {
        		if (data.msg && $.trim(data.msg).length > 0) {
         		   bootbox.dialog(data.msg, [
 		              {
 		                  label: "确定",
 		                  "class": "btn-success"
 		              }
 		           ]);
         	   }
           }else{
        	   if (data.msg && $.trim(data.msg).length > 0) {
		        	bootbox.dialog(data.msg, [
		              {
		                  label: "确定",
		                  "class": "btn-danger"
		              }
		            ]);
       			}
           }
        },bootConfirm:function(msg,callback){
	    	 msg=msg!=null?msg:"是否确定？";
	    	 bootbox.confirm(msg, function(result) {  
    	        if (result) {  
    	        	callback();
    	        }  
	    	 });  
	    },ajaxRequest: function (url, data, succefunc, failfunc, method, async) {
            if (!url) {
                url = "";
            }
            var _data = {"ajax": "true"};
            if (!data) {data = {};}
            data = $.extend(_data, data);
            if (!async) {async = true;}
            if (!failfunc) {failfunc = E.ajaxError;}
            if (!succefunc) {succefunc = E.ajaxSuccess;}
            if (!method) {method = 'post';}
            $.ajax({
                url: url,
                data: data,
                async: async,
                type: method,
                dataType: 'json',
                error: failfunc,
                success: succefunc
            });
        },
        ajaxError: function (XMLHttpRequest, textStatus, errorThrown) {
            E.loadError("网络异常");
        },
        ajaxSuccess: function (data, textStatus, jqXHR) {
            E.ajaxSuccessData(data);
        },
        ajaxSuccessData: function (data) {
            if (data.ok) {
                if (data.msg && $.trim(data.msg).length > 0) {
                    $.jGrowl(data.msg, { life: 5000 });
                }
                return;
            }
            $.jGrowl(data.msg, { life: 5000 });

        },
        perList: {
            permission: {
                del: false,
                edit: false,
                create: false,
                check: false
            },
            menu: {
                edit: false,
                del: false,
                create: false,
                check:false,
                checkPermission:false,
                grant:false
            } ,
            role: {
                edit: false,
                del: false,
                create: false,
                grant:false,
                checkPermission:false,
                check:false
            } ,
            department: {
                edit: false,
                del: false,
                create: false
            } ,
            user: {
                edit: false,
                del: false,
                create: false,
                check:false,
                distribute_role:false,
                edit_dep:false
            },
            dict: {
                edit: false,
                del: false,
                create: false,
                check:false,
                confine:false
            },
            knowledge: {
                edit: false,
                del: false,
                create: false,
                check:false,
                confine:false
            },
            video: {
                edit: false,
                del: false,
                create: false,
                check:false,
                confine:false,
                play : false,
                transcode : false,
            	editVideoExercise:false,
            	createTextbook:false
            },
            region: {
                check:false,
                confine:false,
            },
            school: {
                check:false,
                confine:false,
                add:false,
                del:false
            },
            textbook: {
	        	 check:false,
	             add:false,
	             del:false,
	             confine:false,
	             edit:false,
	             outline:false,
	             catalog:false,
	         	 split:false,
	             checkCatalog:false,
	             editCatalog:false,
	             delCatalog:false,
	         	 addCatalog:false,
	         	 recommend:false
            },
            exercise:{
            	 check:false,
                 add:false,
                 del:false,
                 import:false,
                 edit:false,
                 confine:false,
                 preview:false
            },
            teacher:{
            	 check:false,
                 add:false,
                 del:false,
          editHonour:false
            },
       quintessence:{
	    	    check:false,
	    	    edit:false,
	            add:false,
	            del:false,
       			},
		ektUser:{
    	    check:false,
    	    confine:false,
    	    permission:false,
    	    generateAccount:false,
    	    transactional:false,
    	    giftCourse:false,
    	    generateUser:false,
    	    batchGenerateUser:false,
    	    editUserInfo:false
   			},
   		news:{
   			check:false,
   			add:false,
   			del:false,
   			edit:false,
   			confine:false
   		}
        }
    };
});