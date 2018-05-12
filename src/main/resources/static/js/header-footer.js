/**
 * Created by Negin on 5/7/2018.
 */

$('.ui.menu a.item').on('click', function() {
    $(this)
        .addClass('active')
        .siblings('.item')
        .removeClass('active');
});