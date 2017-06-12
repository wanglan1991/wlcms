
var btHtml ="<div class='bt-cont'><span class='bt-on'>ON</span><span class='bt-circ'></span><span class='bt-off'>OFF</span>";
			$('.bt-on-off').html(btHtml);
			$('.bt-on-off').bind('click',function(){
				if((this.className).search('clicked')<0)
					$(this).addClass('clicked');
				else
					$(this).removeClass('clicked');
			})