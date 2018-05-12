/**
 * Created by Negin on 2/26/2018.
 */

$(document)
    .ready(function() {
        $(function() {
            $('.ui .dropdown')
                .dropdown()
            ;
        });

        // hide when the page loads
       /*
       $('#oldies').hide();
        $('#children').hide();
        $('#housework').hide();
        */
        // change with the radio button change
        $('input[type=radio][name=job]').on('change' , function() {
            switch($(this).val()){
                case 'old' :
                    $('#oldies').toggle("fast");
                    $('#children').hide("fast");
                    $('#housework').hide("fast");

                    $('.form2').form('reset');
                    $('.form3').form('reset');
                    $('#submit-old').click(function () {
                        $('.form1').form('validate form');
                        $('.form1').form('is valid', function () {
                            $('.form1').form('submit');
                        });
                    });
                    $('.form1')
                        .form({
                            inline : true,
                            on: 'change',
                            fields: {
                                calender: {
                                    rules: [
                                        {
                                            type   : 'regExp',
                                            value : /rgb\((\d{1,3}), (\d{1,3}), (\d{1,3})\)/i,
                                            prompt : 'لطفا تاریخ را مانند مثال وارد کنید : مثال 97/08/10'
                                        },
                                        {
                                            type   :  'empty',
                                            prompt :  'لطفا تاریخ را وارد کنید'
                                        }
                                    ]
                                },
                                toTime: {
                                    rules: [
                                        {
                                            type   : 'empty',
                                            prompt : 'ساعت اتمام را وارد نمایید'
                                        },
                                    ]
                                },
                                fromTime : {
                                    rules: [
                                        {
                                            type   : 'empty',
                                            prompt : 'ساعت شروع را وارد نمایید'
                                        },
                                    ]
                                },
                                sexuality1 : {
                                    rules: [
                                        {
                                            type   : 'checked',
                                            prompt : 'لطفا انتخاب کنید'
                                        }
                                    ]
                                },
                                sexuality2 : {
                                    rules: [
                                        {
                                            type   : 'checked',
                                            prompt : 'لطفا انتخاب کنید'
                                        }
                                    ]
                                },
                                sickArea : {
                                    depends    : 'isSick',
                                    rules: [
                                        {
                                            type   : 'empty',
                                            prompt : 'لطفا نام بیماری را در این قسمت وارد کنید'
                                        }
                                    ]
                                },
                                amount : {
                                    rules : [
                                        {
                                            type   : 'empty',
                                            prompt : 'لطفا حقوق درخواستی خود را وارد کنید'
                                        }
                                    ]
                                },
                                title : {
                                    rules : [
                                        {
                                            type   : 'empty',
                                            prompt : 'تیتر درخواستی شما نمی تواند خالی باشد'
                                        }
                                    ]
                                }
                            }
                        })
                    ;
                    $('.form1').form(validationRules, { onSuccess: submitForm });
                    break;
                case 'child' :
                    $('#oldies').hide("fast");
                    $('#children').toggle("fast");
                    $('#housework').hide("fast");

                    $('.form1').form('reset');
                    $('.form3').form('reset');

                    $('#submit-child').click(function () {
                        $('.form2').form('validate form');
                        $('.form2').form('is valid', function () {
                            $('.form2').form('submit');
                        });
                    });
                    $('.form2')
                        .form({
                            inline : true,
                            on: 'change',
                            fields: {
                                calender: {
                                    rules: [
                                        {
                                            type   : 'regExp',
                                            value : /rgb\((\d{1,3}), (\d{1,3}), (\d{1,3})\)/i,
                                            prompt : 'لطفا تاریخ را مانند مثال وارد کنید : مثال 97/08/10'
                                        },
                                        {
                                            type   :  'empty',
                                            prompt :  'لطفا تاریخ را وارد کنید'
                                        }
                                    ]
                                },
                                toTime: {
                                    rules: [
                                        {
                                            type   : 'empty',
                                            prompt : 'ساعت اتمام را وارد نمایید'
                                        },
                                    ]
                                },
                                fromTime : {
                                    rules: [
                                        {
                                            type   : 'empty',
                                            prompt : 'ساعت شروع را وارد نمایید'
                                        },
                                    ]
                                },
                                amount : {
                                    identifier : 'amount',
                                    rules : [
                                        {
                                            type   : 'empty',
                                            prompt : 'لطفا حقوق درخواستی خود را وارد کنید'
                                        }
                                    ]
                                },
                                title : {
                                    rules : [
                                        {
                                            type   : 'empty',
                                            prompt : 'تیتر درخواستی شما نمی تواند خالی باشد'
                                        }
                                    ]
                                }
                            }
                        })
                    ;
                    $('.form2').form(validationRules, { onSuccess: submitForm });

                    break;
                case 'home' :
                    $('#oldies').hide("fast");
                    $('#children').hide("fast");
                    $('#housework').toggle("fast");

                    $('.form1').form('reset');
                    $('.form2').form('reset');

                    $('#submit-home').click(function () {
                        $('.form3').form('validate form');
                        $('.form3').form('is valid', function () {
                            $('.form3').form('submit');
                        });
                    });
                    $('.form3')
                        .form({
                            inline : true,
                            on: 'change',
                            fields: {
                                calender: {
                                    rules: [
                                        {
                                            type   : 'regExp',
                                            value : /rgb\((\d{1,3}), (\d{1,3}), (\d{1,3})\)/i,
                                            prompt : 'لطفا تاریخ را مانند مثال وارد کنید : مثال 97/08/10'
                                        },
                                        {
                                            type   :  'empty',
                                            prompt :  'لطفا تاریخ را وارد کنید'
                                        }
                                    ]
                                },
                                toTime: {
                                    rules: [
                                        {
                                            type   : 'empty',
                                            prompt : 'ساعت اتمام را وارد نمایید'
                                        },
                                    ]
                                },
                                fromTime : {
                                    rules: [
                                        {
                                            type   : 'empty',
                                            prompt : 'ساعت شروع را وارد نمایید'
                                        },
                                    ]
                                },
                                sexuality : {
                                    rules: [
                                        {
                                            type   : 'checked',
                                            prompt : 'لطفا انتخاب کنید'
                                        }
                                    ]
                                },
                                animal : {
                                    rules: [
                                        {
                                            type   : 'checked',
                                            prompt : 'لطفا انتخاب کنید'
                                        }
                                    ]
                                },
                                services : {
                                    rules: [
                                        {
                                            type   : 'empty',
                                            prompt : 'لطفا خدمات مورد نیاز خود را در این قسمت وارد کنید'
                                        }
                                    ]
                                },
                                amount : {
                                    rules : [
                                        {
                                            type   : 'empty',
                                            prompt : 'لطفا حقوق درخواستی خود را وارد کنید'
                                        }
                                    ]
                                },
                                title : {
                                    rules : [
                                        {
                                            type   : 'empty',
                                            prompt : 'تیتر درخواستی شما نمی تواند خالی باشد'
                                        }
                                    ]
                                }
                            }
                        })
                    ;
                    $('.form3').form(validationRules, { onSuccess: submitForm });
                    break;
            }
        });
    })
;
