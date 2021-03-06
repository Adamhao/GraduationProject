!function (a) {
    "use strict";
    "function" == typeof define && define.amd ? define(["jquery", "../grid.base"], a) : a(jQuery)
}(function (a) {
    a.jgrid = a.jgrid || {}, a.jgrid.hasOwnProperty("regional") || (a.jgrid.regional = []), a.jgrid.regional.en = {
        defaults: {
            recordtext: "View {0} - {1} of {2}",
            emptyrecords: "没有记录",
            loadtext: "加载中...",
            savetext: "保存中...",
            pgtext: "Page {0} of {1}",
            pgfirst: "首页",
            pglast: "末页",
            pgnext: "下一页",
            pgprev: "上一页",
            pgrecs: "每页条数",
            showhide: "Toggle Expand Collapse Grid",
            pagerCaption: "Grid::Page Settings",
            pageText: "Page:",
            recordPage: "Records per Page",
            nomorerecs: "没有更多记录...",
            scrollPullup: "Pull up to load more...",
            scrollPulldown: "Pull down to refresh...",
            scrollRefresh: "释放刷新..."
        },
        search: {
            caption: "查找...",
            Find: "搜索",
            Reset: "重置",
            odata: [{oper: "eq", text: "等于"}, {oper: "ne", text: "不等于"}, {
                oper: "lt",
                text: "小于"
            }, {oper: "le", text: "小于或等于"}, {oper: "gt", text: "大于"}, {
                oper: "ge",
                text: "大于或等于"
            }, {oper: "bw", text: "以...开始"},
                {oper: "ew", text: "以...结束"}],
            groupOps: [{op: "AND", text: "所有"}, {op: "OR", text: "任一"}],
            operandTitle: "Click to select search operation.",
            resetTitle: "重置搜索值"
        },
        edit: {
            addCaption: "Add Record",
            editCaption: "Edit Record",
            bSubmit: "提交",
            bCancel: "取消",
            bClose: "关闭",
            saveData: "数据已改变，是否保存?",
            bYes: "是",
            bNo: "否",
            bExit: "取消",
            msg: {
                required: "Field is required",
                number: "请输入有效数值",
                minValue: "value must be greater than or equal to ",
                maxValue: "value must be less than or equal to",
                email: "不是一个有效的邮箱",
                integer: "请输入有效的整数数值",
                date: "请输入有效的日期数据",
                url: "不是一个有效的URL. 需要前缀 ('http://' or 'https://')",
                nodefined: " is not defined!",
                novalue: " return value is required!",
                customarray: "Custom function should return array!",
                customfcheck: "Custom function should be present in case of custom checking!"
            }
        },
        view: {caption: "View Record", bClose: "关闭"},
        del: {caption: "禁用", msg: "禁用所选?", bSubmit: "禁用", bCancel: "取消"},
        nav: {
            edittext: "",
            edittitle: "编辑所选行",
            addtext: "",
            addtitle: "添加一行数据",
            deltext: "",
            deltitle: "禁用所选",
            searchtext: "",
            searchtitle: "查找",
            refreshtext: "",
            refreshtitle: "刷新",
            alertcap: "警告",
            alerttext: "请至少选择一行",
            viewtext: "",
            viewtitle: "查看所选行",
            savetext: "",
            savetitle: "保存",
            canceltext: "",
            canceltitle: "取消编辑",
            selectcaption: "Actions..."
        },
        col: {caption: "Select columns", bSubmit: "Ok", bCancel: "Cancel"},
        errors: {
            errcap: "Error",
            nourl: "No url is set",
            norecords: "No records to process",
            model: "Length of colNames <> colModel!"
        },
        formatter: {
            integer: {thousandsSeparator: ",", defaultValue: "0"},
            number: {decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: "0.00"},
            currency: {
                decimalSeparator: ".",
                thousandsSeparator: ",",
                decimalPlaces: 2,
                prefix: "",
                suffix: "",
                defaultValue: "0.00"
            },
            date: {
                dayNames: ["Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
                monthNames: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
                AmPm: ["am", "pm", "AM", "PM"],
                S: function (a) {
                    return 11 > a || a > 13 ? ["st", "nd", "rd", "th"][Math.min((a - 1) % 10, 3)] : "th"
                },
                srcformat: "Y-m-d",
                newformat: "n/j/Y",
                parseRe: /[#%\\\/:_;.,\t\s-]/,
                masks: {
                    ISO8601Long: "Y-m-d H:i:s",
                    ISO8601Short: "Y-m-d",
                    ShortDate: "n/j/Y",
                    LongDate: "l, F d, Y",
                    FullDateTime: "l, F d, Y g:i:s A",
                    MonthDay: "F d",
                    ShortTime: "g:i A",
                    LongTime: "g:i:s A",
                    SortableDateTime: "Y-m-d\\TH:i:s",
                    UniversalSortableDateTime: "Y-m-d H:i:sO",
                    YearMonth: "F, Y"
                },
                reformatAfterEdit: !1,
                userLocalTime: !1
            },
            baseLinkUrl: "",
            showAction: "",
            target: "",
            checkbox: {disabled: !0},
            idName: "id"
        }
    }
});