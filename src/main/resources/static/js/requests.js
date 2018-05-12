/**
 * Created by Negin on 4/15/2018.
 */


$(document).ready(function() {

    $('.ui .yellow .menu').tab();

    $('#submit-btn').click(function(){
        $(".modal-1").modal('show');
        $('.coupled.modal')
            .modal({
                allowMultiple: false
            })
        ;
        // attach events to buttons
        $('.second.modal')
            .modal('attach events', '.first.modal .tel-modal')
        ;
        $('.third.modal')
            .modal('attach events', '.first.modal .tea-modal')
        ;
        $('.success-modal.modal')
            .modal('attach events', '.second.modal .suc-btn')
        ;
        $('.success-modal.modal')
            .modal('attach events', '.third.modal .suc-btn')
        ;
        // show first now
        $('.first.modal')
            .modal('show')
        ;
    });
    $(".modal-1").modal({
        closable: true
    });

});

