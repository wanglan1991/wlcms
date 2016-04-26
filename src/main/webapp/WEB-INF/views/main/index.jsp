<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/include/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>CMS</title>
    <%@include file="/WEB-INF/views/include/baseCSS.jsp" %>
</head>
<body class='contrast-blue'>
<header>
    <div class='navbar'>
        <div class='navbar-inner'>
            <div class='container-fluid'>
                <a class='brand' href='index.html'>
                    <i class='icon-adjust'></i>
                    <span class='hidden-phone'>CMS</span>
                </a>
                <a class='toggle-nav btn pull-left' href='#'>
                    <i class='icon-reorder'></i>
                </a>
                <ul class='nav pull-right'>
                    <li class='dropdown light only-icon'>
                        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>
                            <i class='icon-adjust'></i>
                        </a>
                        <ul class='dropdown-menu color-settings'>
                          <%-- <li class='color-settings-body-color'>
                                <div class='color-title'>Body color</div>
                                <a data-change-to='${ctxAssets}/stylesheets/light-theme.css' href='#'>
                                    Light
                                    <small>(default)</small>
                                </a>
                                <a data-change-to='${ctxAssets}/stylesheets/dark-theme.css' href='#'>
                                    Dark
                                </a>
                                <a data-change-to='${ctxAssets}/stylesheets/dark-blue-theme.css' href='#'>
                                    Dark blue
                                </a>
                            </li>--%>
                            <li class='divider'></li>
                            <li class='color-settings-contrast-color'>
                                <div class='color-title'>主题颜色</div>
                                <a href="#" data-change-to="contrast-blue"><i class='icon-adjust text-blue'></i>
                                        蓝色<small>(default Blue)</small>
                                </a>
                                <a href="#" data-change-to="contrast-red"><i class='icon-adjust text-red'></i>
                                    	红色<small>(Red)</small>
                                </a>
                                <a href="#" data-change-to="contrast-orange"><i class='icon-adjust text-orange'></i>
                                    	橙色<small>(Orange)</small>
                                </a>
                                <a href="#" data-change-to="contrast-purple"><i class='icon-adjust text-purple'></i>
                                    	紫色<small>(Purper)</small>
                                </a>
                                <a href="#" data-change-to="contrast-green"><i class='icon-adjust text-green'></i>
                                    	绿色<small>(Green)</small>
                                </a>
                                <a href="#" data-change-to="contrast-muted"><i class='icon-adjust text-muted'></i>
                                    	灰色<small>(Muted)</small>
                                </a>
                                <a href="#" data-change-to="contrast-dark"><i class='icon-adjust text-dark'></i>
                                    	黑色<small>(Dark)</small>
                                </a>
                                <a href="#" data-change-to="contrast-pink"><i class='icon-adjust text-pink'></i>
                                   		 粉色<small>(Pink)</small>
                                </a>
                                <a href="#" data-change-to="contrast-grass-green"><i class='icon-adjust text-grass-green'></i>
                                    	青草色<small>(Grass green)</small>
                                </a>
                                <a href="#" data-change-to="contrast-sea-blue"><i class='icon-adjust text-sea-blue'></i>
                                    	大海蓝<small>(Sea blue)</small>
                                </a>
                                <a href="#" data-change-to="contrast-banana"><i class='icon-adjust text-banana'></i>
                                    	香蕉色<small>(Banana)</small>
                                </a>
                                <a href="#" data-change-to="contrast-dark-orange"><i class='icon-adjust text-dark-orange'></i>
                                    	暗黄色<small>(Dark orange)</small>
                                </a>
                                <a href="#" data-change-to="contrast-brown"><i class='icon-adjust text-brown'></i>
                                    	棕色<small>(Brown)</small>
                                </a>
                                <a href="#" data-change-to="contrast-fb"><i class='icon-adjust text-fb'></i>
                                    Facebook
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class='dropdown medium only-icon widget'>
                        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>
                            <i class='icon-rss'></i>
                            <div class='label'>5</div>
                        </a>
                        <ul class='dropdown-menu'>
                            <li>
                                <a href='#'>
                                    <div class='widget-body'>
                                        <div class='pull-left icon'>
                                            <i class='icon-user text-success'></i>
                                        </div>
                                        <div class='pull-left text'>
                                            John Doe signed up
                                            <small class='muted'>just now</small>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class='divider'></li>
                            <li>
                                <a href='#'>
                                    <div class='widget-body'>
                                        <div class='pull-left icon'>
                                            <i class='icon-inbox text-error'></i>
                                        </div>
                                        <div class='pull-left text'>
                                            New Order #002
                                            <small class='muted'>3 minutes ago</small>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class='divider'></li>
                            <li>
                                <a href='#'>
                                    <div class='widget-body'>
                                        <div class='pull-left icon'>
                                            <i class='icon-comment text-warning'></i>
                                        </div>
                                        <div class='pull-left text'>
                                            America Leannon commented Flatty with veeery long text.
                                            <small class='muted'>1 hour ago</small>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class='divider'></li>
                            <li>
                                <a href='#'>
                                    <div class='widget-body'>
                                        <div class='pull-left icon'>
                                            <i class='icon-user text-success'></i>
                                        </div>
                                        <div class='pull-left text'>
                                            Jane Doe signed up
                                            <small class='muted'>last week</small>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class='divider'></li>
                            <li>
                                <a href='#'>
                                    <div class='widget-body'>
                                        <div class='pull-left icon'>
                                            <i class='icon-inbox text-error'></i>
                                        </div>
                                        <div class='pull-left text'>
                                            New Order #001
                                            <small class='muted'>1 year ago</small>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class='widget-footer'>
                                <a href='#'>All notifications</a>
                            </li>
                        </ul>
                    </li>
                    <li class='dropdown dark user-menu'>
                        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>
                            <img alt='Mila Kunis' height='23' src='${ctxAssets}/images/avatar.jpg' width='23' />
                            <span class='user-name hidden-phone'>管理员</span>
                            <b class='caret'></b>
                        </a>
                        <ul class='dropdown-menu'>
                            <li>
                                <a href='user_profile.html'>
                                    <i class='icon-user'></i>
                                    	个人信息
                                </a>
                            </li>
                            <li>
                                <a href='user_profile.html'>
                                    <i class='icon-cog'></i>
                                    	我的设置
                                </a>
                            </li>
                            <li class='divider'></li>
                            <li>
                                <a href='${ctx}/user/exit'>
                                    <i class='icon-signout'></i>
                                    	安全退出
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
               <form accept-charset="UTF-8" action="search_results.html"
						id="search" class="navbar-search pull-right hidden-phone"
						method="get" />
					<div style="margin: 0; padding: 0; display: inline">
						<input name="utf8" type="hidden" value="&#x2713;" />
					</div>
					<button class="btn btn-link icon-search" name="button"
						type="submit"></button>
					<input autocomplete="off" class="search-query span2" id="q_header"
						name="q" placeholder="Search..." type="text" value="" />
					</form>

					<shiro:hasPermission name="permission:delete">
						<span id="basejs_permission_del" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="permission:edit">
						<span id="basejs_permission_edit" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="permission:add">
						<span id="basejs_permission_create" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="permission:confine">
						<span id="basejs_permission_confine" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="permission:check">
						<span id="basejs_permission_check" style="display: none;" />
					</shiro:hasPermission>

					<shiro:hasPermission name="menu:edit">
						<span id="basejs_menu_edit" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="menu:delete">
						<span id="basejs_menu_del" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="menu:add">
						<span id="basejs_menu_create" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="menu:check">
						<span id="basejs_menu_check" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="menupermisson:check">
						<span id="basejs_menu_permission_check" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="menu:grant">
						<span id="basejs_menu_grant" style="display: none;" />
					</shiro:hasPermission>


					<shiro:hasPermission name="role:add">
						<span id="basejs_role_create" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="role:delete">
						<span id="basejs_role_del" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="role:edit">
						<span id="basejs_role_edit" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="role:confine">
						<span id="basejs_role_confine" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="role:grant">
						<span id="basejs_role_grant" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="rolepermission:check">
						<span id="basejs_role_permission_check" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="role:check">
						<span id="basejs_role_check" style="display: none;" />
					</shiro:hasPermission>



					<shiro:hasPermission name="account:add">
						<span id="basejs_account_add" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="account:check">
						<span id="basejs_account_check" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="account:edit">
						<span id="basejs_account_edit" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="account:del">
						<span id="basejs_account_del" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="account:confine">
						<span id="basejs_account_confine" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="account:roleEdit">
						<span id="basejs_account_roleEdit" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="account:resetPwd">
						<span id="basejs_account_resetPwd" style="display: none;" />
					</shiro:hasPermission>

					<shiro:hasPermission name="dict:add">
						<span id="basejs_dict_create" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="dict:check">
						<span id="basejs_dict_check" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="dict:edit">
						<span id="basejs_dict_edit" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="dict:delete">
						<span id="basejs_dict_del" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="dict:confine">
						<span id="basejs_dict_confine" style="display: none;" />
					</shiro:hasPermission>

					<shiro:hasPermission name="knowledge:add">
						<span id="basejs_knowledge_create" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="knowledge:check">
						<span id="basejs_knowledge_check" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="knowledge:edit">
						<span id="basejs_knowledge_edit" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="knowledge:delete">
						<span id="basejs_knowledge_del" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="knowledge:confine">
						<span id="basejs_knowledge_confine" style="display: none;" />
					</shiro:hasPermission>
					
					<shiro:hasPermission name="video:add">
					<span id="basejs_video_create" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="video:check">
						<span id="basejs_video_check" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="video:edit">
						<span id="basejs_video_edit" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="video:delete">
						<span id="basejs_video_del" style="display: none;" />
					</shiro:hasPermission>
					<shiro:hasPermission name="video:confine">
						<span id="basejs_video_confine" style="display: none;" />
					</shiro:hasPermission>
            </div>
        </div>
    </div>
</header>
<div id='wrapper'>
    <div id='main-nav-bg'></div>
    <nav class='' id='main-nav'>
        <div class='navigation'>
            <div class='search'>
                <form accept-charset="UTF-8" action="search_results.html" method="get" /><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /></div>
                <div class='search-wrapper'>
                    <input autocomplete="off" class="search-query" id="q" name="q" placeholder="Search..." type="text" value="" />
                    <button class="btn btn-link icon-search" name="button" type="submit"></button>
                </div>
                </form>
            </div>
            <ul id='navigation-menu' class='nav nav-stacked'>
                <li class=''>
                    <a href='${ctx}/main/welcomePage'>
                        <i class='icon-dashboard'></i>
                        <span>首页</span>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
    <section id='content'>
        <div class='container-fluid'>
            <div id='content-wrapper' class='row-fluid'>
                <div class='span12'>
                    <div class='row-fluid' id='content-header'>
                        <div class='span12'>
                        </div>
                    </div>
                    <div class='row-fluid'id="mainFrame" name="mainFrame">
                       <%-- <iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;" scrolling=no frameborder="no" width="100%" height="100%"></iframe>--%>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<%@include file="/WEB-INF/views/include/baseJS.jsp" %>
<script type="text/javascript">
var menusTxt="";
$(document).ready(function(){ 
	  $.ajax({
		  url:"${ctx}/menu/list",
		  type:"POST",
		  success:function(data){
			  $.each(data, function(i, parentMenu){
				 menusTxt+=" <li class=''><a  target='mainFrame' class='dropdown-collapse  in' href='#'><i class='"+parentMenu.icon+"'></i><span>"+parentMenu.menuName+"</span></a><ul class='nav nav-stacked'>";
					var sonMenuArr = parentMenu.menuList;	
						 for(var i=0;i<sonMenuArr.length;i++){
							 menusTxt+="<li><a  target='mainFrame' href='${ctx}/"+sonMenuArr[i].url+"'><i class='"+sonMenuArr[i].icon+"'></i><span>"+sonMenuArr[i].menuName+"<span></a><li>" 
									
							}
						 menusTxt+="</ul></li>";
						 
				})
				if( data.length==0)
					 menusTxt+=" <li class=''><a  target='mainFrame' class='dropdown-collapse  in' href='#'><i class='icon-frown'></i><span style='color:red'>请 等 待 授 权 !</span></a><ul class='nav nav-stacked'>";
			   $('ul#navigation-menu').append(menusTxt);
		  }
	  })    

})

$(".icon-adjust").click(function(){
	$(".icon-adjust").attr("class","icon-globe");
})




    seajs.config({
        base: "${ctxAssets}/js/",
       /*  alias: {
            'baidumap1':'http://api.map.baidu.com/api?v=2.0&ak=f0EcGjLxOTFf0q3ngvQiP96Z',
            'baidumap2':'http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js',
            'baidumap3':'http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js'
        } */
    });
	seajs.use([ 'base', 'main/index' ], function(base, index) {
		if ($('#basejs_permission_del').length > 0) {
			base.perList.permission.del = true;
		}
		if ($('#basejs_permission_edit').length > 0) {
			base.perList.permission.edit = true;
		}
		if ($('#basejs_permission_create').length > 0) {
			base.perList.permission.create = true;
		}
		if ($('#basejs_permission_confine').length > 0) {
			base.perList.permission.confine = true;
		}
		if ($('#basejs_permission_check').length > 0) {
			base.perList.permission.check = true;
		}
		if ($('#basejs_menu_edit').length > 0) {
			base.perList.menu.edit = true;
		}
		if ($('#basejs_menu_del').length > 0) {
			base.perList.menu.del = true;
		}
		if ($('#basejs_menu_create').length > 0) {
			base.perList.menu.create = true;
		}
		if ($('#basejs_role_create').length > 0) {
			base.perList.role.create = true;
		}
		if ($('#basejs_role_del').length > 0) {
			base.perList.role.del = true;
		}
		if ($('#basejs_role_confine').length > 0) {
			base.perList.role.confine = true;
		}
		if ($('#basejs_role_edit').length > 0) {
			base.perList.role.edit = true;
		}
		if ($('#basejs_department_create').length > 0) {
			base.perList.department.create = true;
		}
		if ($('#basejs_department_del').length > 0) {
			base.perList.department.del = true;
		}
		if ($('#basejs_department_edit').length > 0) {
			base.perList.department.edit = true;
		}
		if ($('#basejs_role_grant').length > 0) {
			base.perList.role.grant = true;
		}
		if ($('#basejs_role_permission_check').length > 0) {
			base.perList.role.checkPermission = true;
		}
		if ($('#basejs_role_check').length > 0) {
			base.perList.role.check = true;
		}
		if ($('#basejs_menu_check').length > 0) {
			base.perList.menu.check = true;
		}
		if ($('#basejs_menu_permission_check').length > 0) {
			base.perList.menu.checkPermission = true;
		}
		if ($('#basejs_menu_grant').length > 0) {
			base.perList.menu.grant = true;
		}
	
		if ($('#basejs_account_add').length > 0) {
			base.perList.user.add = true;
		}
		if ($('#basejs_account_check').length > 0) {
			base.perList.user.check = true;
		}
		if ($('#basejs_account_edit').length > 0) {
			base.perList.user.edit = true;
		}
		if ($('#basejs_account_del').length > 0) {
			base.perList.user.del = true;
		}
		if ($('#basejs_account_confine').length > 0) {
			base.perList.user.confine = true;
		}
		if ($('#basejs_account_roleEdit').length > 0) {
			base.perList.user.roleEdit = true;
		}
		if ($('#basejs_account_resetPwd').length > 0) {
			base.perList.user.resetPwd = true;
		}
	
		if ($('#basejs_dict_create').length > 0) {
			base.perList.dict.create = true;
		}
		if ($('#basejs_dict_check').length > 0) {
			base.perList.dict.check = true;
		}
		if ($('#basejs_dict_edit').length > 0) {
			base.perList.dict.edit = true;
		}
		if ($('#basejs_dict_del').length > 0) {
			base.perList.dict.del = true;
		}
		if ($('#basejs_dict_confine').length > 0) {
			base.perList.dict.confine = true;
		}
		
		if ($('#basejs_knowledge_create').length > 0) {
			base.perList.knowledge.create = true;
		}
		if ($('#basejs_knowledge_check').length > 0) {
			base.perList.knowledge.check = true;
		}
		if ($('#basejs_knowledge_edit').length > 0) {
			base.perList.knowledge.edit = true;
		}
		if ($('#basejs_knowledge_del').length > 0) {
			base.perList.knowledge.del = true;
		}
		if ($('#basejs_knowledge_confine').length > 0) {
			base.perList.knowledge.confine = true;
		}
		
		if ($('#basejs_video_create').length > 0) {
			base.perList.video.create = true;
		}
		if ($('#basejs_video_check').length > 0) {
			base.perList.video.check = true;
		}
		if ($('#basejs_video_edit').length > 0) {
			base.perList.video.edit = true;
		}
		if ($('#basejs_video_del').length > 0) {
			base.perList.video.del = true;
		}
		if ($('#basejs_video_confine').length > 0) {
			base.perList.video.confine = true;
		}
		
		index.init('${ctx}');
	});

</script>
<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=cTYtHPV1spdnWgFSeKfXqjTh"></script> -->
</body>
</html>
