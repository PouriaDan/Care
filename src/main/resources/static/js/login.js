/**
 * Created by Negin on 2/10/2018.
 */


$(document)
    .ready(function() {
        $(function() {
            $('.ui.radio.checkbox')
                .checkbox();
            ;
            $('#submit-btn').click(function () {
                $('form').form('validate form');
                $('form').form('is valid', function () {
                    $('form').form('submit');
                });

            });
            $('form')
                .form({
                    inline : true,
                    on: 'change',
                    fields: {
                        email: {
                            identifier  : 'email',
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'لطفا پست الکترونیکی خود را وارد کنید'
                                },
                                {
                                    type   :  'email',
                                    prompt :  'لطفا پست الکترونیکی را درست وارد کنید'
                                }
                            ]
                        },
                        password: {
                            identifier  : 'password',
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'رمز عبور خالی است'
                                },
                            ]
                        }
                    }
                })
            ;
            $('form').form(validationRules, { onSuccess: submitForm });
        })
    })
;