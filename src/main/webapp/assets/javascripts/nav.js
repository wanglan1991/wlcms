(function() {
    $(document).ready(function() {
        var body, content, nav, nav_closed_width, nav_open, nav_toggler;

        nav_toggler = $("header .toggle-nav");
        nav = $("#main-nav");
        content = $("#content");
        body = $("body");
        nav_closed_width = 50;
        nav_open = body.hasClass("main-nav-opened") || nav.width() > nav_closed_width;
        $("#main-nav").on("click",".dropdown-collapse", function(e) {
            var link, list ;

            e.preventDefault();
            link = $(this);
            
            list = link.parent().find("> ul");
            if (list.is(":visible")) {
                if (body.hasClass("main-nav-closed") && link.parents("li").length === 1) {
                    false;
                } else {
                	link.find("> .icon-angle-up").addClass("icon-angle-down").addClass("angle-down").removeClass("angle-up").removeClass("icon-angle-up");
                    link.removeClass("in");
                    list.slideUp(300, function() {
                        return $(this).removeClass("in");
                    });
                }
            } else {
            	link.find("> .icon-angle-down").addClass("icon-angle-up").addClass("angle-up").removeClass("angle-down").removeClass("icon-angle-down");
                link.addClass("in");
                list.slideDown(300, function() {
                    return $(this).addClass("in");
                });
            }
            return false;
        });
        
       $("#main-nav").on("click","a", function(e) {
           var link = $(this).attr('href');
           if(link!=null&&link!=""&&link!="#"){
               var p_text = $(this).find("> span").text();
               var p_cls = $(this).find("> i:first").attr("class");

               var span12 =  "<div class='page-header'>"+
                   "<h1 class='pull-left'>"+
                   "<i class='"+p_cls+"' style='margin:0 6px 0 0'></i>"+
                   "<span>"+p_text+"</span>"+
                   "</h1>"+
                   "<div class='pull-right'>"+
                   "<ul class='breadcrumb'>"+
                   "<li>"+
                   "<a href='index.html'><i class='icon-dashboard'></i>"+
                   "</a>"+
                   "</li>";

               var span12_li='';
               $(this).parents("li").each(function(i,items){
                   var $this = $(this).find("> a")
                   var text = $this.find("> span").text();
                   var cls = $this.find("> i:first").attr("class");
                   var url = $this.attr("href");
                   var activeCls="";
                   if(i==0)
                       activeCls="active";
                   span12_li="<li class='separator'><i class='icon-angle-right' style='margin:0 4px 0 4px'></i></li><li class='"+activeCls+"'><a href='"+url+"'><i class='"+cls+"'></i></a>"+text+"</li>"+span12_li;
               })
               span12+=span12_li;
               span12+="</ul>"+
                   "</div>"+
                   "</div>";
               $('#content-header .span12').empty();
               $('#content-header .span12').html(span12);

               $.get(link,function(data,status){
                  if(data.ok == undefined){
                	  $("#mainFrame").html(data);
                  }else if(!data.ok)
                	  window.location.href = data.url;
               });
           }
           return false;
        });
        
        nav.swiperight(function(event, touch) {
            return $(document).trigger("nav-open");
        });
        nav.swipeleft(function(event, touch) {
            return $(document).trigger("nav-close");
        });
        nav_toggler.on("click", function() {
            if (nav_open) {
                $(document).trigger("nav-close");
            } else {
                $(document).trigger("nav-open");
            }
            return false;
        });
        $(document).bind("nav-close", function(event, params) {
            body.removeClass("main-nav-opened").addClass("main-nav-closed");
            return nav_open = false;
        });
        return $(document).bind("nav-open", function(event, params) {
            body.addClass("main-nav-opened").removeClass("main-nav-closed");
            return nav_open = true;
        });
    });

}).call(this);