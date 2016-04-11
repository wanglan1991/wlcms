(function() {
    $(document).ready(function() {
        if (localStorage.getItem("content") !== null) {
            $("#color-settings-body-color").attr("href", localStorage.getItem("content"));
        }
        if ((localStorage.getItem("contrast") !== null) ) {
            var islogin=false;
            if($("body").hasClass("contrast-background"))
                islogin=true;
            $('body')[0].className = $('body')[0].className.replace(/(^|\s)contrast.*?(\s|$)/g, ' ').replace(/\s\s+/g, ' ').replace(/(^\s|\s$)/g, '');
            $('body').addClass(localStorage.getItem("contrast"));
            if(islogin)
                $('body').addClass("contrast-background");
        }
        $(".color-settings-body-color > a").hover(function() {
            $("#color-settings-body-color").attr("href", $(this).data("change-to"));
            return localStorage.setItem("content", $(this).data("change-to"));
        });
        var themecolor;
        $(".color-settings-contrast-color > a").hover(function() {
            themecolor = $('body')[0].className;
            $('body')[0].className = $('body')[0].className.replace(/(^|\s)contrast.*?(\s|$)/g, ' ').replace(/\s\s+/g, ' ').replace(/(^\s|\s$)/g, '');
            $('body').addClass($(this).data("change-to"));
        },function() {
            if(themecolor){
                $('body')[0].className = $('body')[0].className.replace(/(^|\s)contrast.*?(\s|$)/g, ' ').replace(/\s\s+/g, ' ').replace(/(^\s|\s$)/g, '');
                $('body').addClass(themecolor);
            }
        });

        return  $(".color-settings-contrast-color > a").click(function() {
            themecolor=null;
            $('body')[0].className = $('body')[0].className.replace(/(^|\s)contrast.*?(\s|$)/g, ' ').replace(/\s\s+/g, ' ').replace(/(^\s|\s$)/g, '');
            $('body').addClass($(this).data("change-to"));
            return localStorage.setItem("contrast", $(this).data("change-to"));
        })
    });
}).call(this);