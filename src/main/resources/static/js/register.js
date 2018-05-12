/**
 * Created by Negin on 2/9/2018.
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
                        نام: {
                            identifier  : 'firstName',
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'لطفا نام خود را وارد کنید'
                                }
                            ]
                        },
                        familyName: {
                            identifier  : 'lastName',
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'لطفا نام خانوادگی خود را وارد کنید'
                                }
                            ]
                        },
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
                                {
                                    type   :  'minLength[6]',
                                    prompt :  'رمز عبور باید بیشتر از 6 حرف باشد'
                                }
                            ]
                        },
                        number : {
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'لطفا شماره تماسس خود را وارد کنید'
                                }
                            ]
                        },
                        postalCode : {
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'لطفا کد پستی را وارد کنید'
                                }
                            ]
                        },
                        city : {
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'لطفا شهر محل سکونت خود را وارد کنید'
                                }
                            ]
                        },
                        address : {
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'لطفا آدرس خود را وارد کنید'
                                }
                            ]
                        },
                        gender : {
                            rules: [
                                {
                                    type   : 'checked',
                                    prompt : 'لطفا انتخاب کنید'
                                }
                            ]
                        },
                    }
                    })
            ;
            $('form').form(validationRules, { onSuccess: submitForm });
        })
    })
;