//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
        $.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            errorElement: "span",
            errorPlacement: function (error, element) {
                if (element.is(":radio") || element.is(":checkbox")) {
                    error.appendTo(element.parent().parent().parent());
                } else {
                    error.appendTo(element.parent());
                }
            },
            errorClass: "help-block m-b-none",
            validClass: "help-block m-b-none"


        });

        //验证手机号码格式
        jQuery.validator.addMethod("phone",function (value,element) {
            var tel=/(^[0-9]{3,4}\-[0-9]{8}$)|(^[0-9]{8}$)|(^[0-9]{3,4}[0-9]{8}$)|(^0{0,1}13[0-9]{9}$)/;
            return this.optional(element) || (tel.test(value));
        },"请填写正确的手机号码");



        //以下为官方示例
        $().ready(function () {
            // validate the comment form when it is submitted
            $("#commentForm").validate();

            // validate signup form on keyup and submit
            //var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({
                rules: {
                    username: {
                        required: true,
                        minlength: 2,
                        remote: {
                            url: "/reg/checkNameIsTrue",
                            type: "get",
                            dataType: "json",
                            data: {
                                username: function() {
                                    return $("#username").val();
                                }
                            }
                        }
                    },
                    password: {
                        required: true,
                        minlength: 5
                    },
                    phone: {
                        required:true,
                        phone:true,
                        remote: {
                            url: "/user/checkPhoneIsTrue",
                            type: "get",
                            dataType: "json",
                            data: {
                                username: function() {
                                    return $("#username").val();
                                },
                                phone: function() {
                                    return $("#phone").val();
                                }
                            }
                        }
                    },
                    proveCode: {
                        required:true,
                        remote: {
                            url: "/user/checkCode",
                            type: "get",
                            dataType: "json",
                            data: {
                                proveCode: function() {
                                    return $("#proveCode").val();
                                }
                            }
                        }
                    }
                },
                messages: {
                    username: {
                        required:  "请输入您的用户名",
                        minlength:  "用户名必须两个字符以上",
                        remote: "用户名不存在"
                    },
                    password: {
                        required: "请输入您的新密码",
                        minlength: "密码必须5个字符以上"
                    },
                    phone: {
                        required:  "请输入您的手机号码",
                        remote: "手机号码与用户名不匹配"
                    },
                    proveCode: {
                        required:  "请输入验证码",
                        remote: "验证码错误"
                    }
                }
            });
        });
