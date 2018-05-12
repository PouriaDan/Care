/**
 * Created by Negin on 2/17/2018.
 */

var logoHeight = $('#header-container.img-size').height();
var containerHeight = $('#header-container ').height();
if (logoHeight < containerHeight) {
    var margintop = (containerHeight - logoHeight) / 2;
    $('#header-container.img-size').css('margin-top', margintop);
}


$(document).ready(function() {
    $(function(){
        $('.menu .item')
            .tab();
        $('.ui .dropdown')
            .dropdown()
        ;
    });

    $('.visible.example .ui.sidebar')
        .sidebar({
            context: '.visible.example .bottom.segment'
        })
        .sidebar('hide')
    ;
});

