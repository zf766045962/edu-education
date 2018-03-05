/**
 * 配置文件说明
 * @type {string}
 *
 * ctx: 在本demo中，用于跨域请求远程服务器数据的网址，
 * 在实际应用中，大部分情况下topjui.all.min.js与应用程序在同一个域下，设置为空即可
 *
 * topJUI.language: 消息提示框的中文提示，可根据情况调整
 *
 */

/* 静态演示中获取contextPath，动态演示非必须 开始 */
var contextPath = "";
var remoteHost = "http://localhost:8080";
if (navigator.onLine) {
    remoteHost = "http://demo.ewsd.cn";
    // 百度统计代码开始
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?71559c3bdac3e45bebab67a5a841c70e";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
    // 百度统计代码结束
}
var firstPathName = window.location.pathname.split("/")[1];
if (!(firstPathName == "html" || firstPathName == "json" || firstPathName == "topjui")) {
    contextPath = "/" + firstPathName;
}
/* 静态演示中获取contextPath，动态演示非必须 结束 */

var topJUI = {
    config: {
        pkName: 'uuid',
        // 是否对批量提交表格选中记录的参数值使用单引号，默认为false，true:'123','456'，false:123,456
        singleQuotesParam: true,
        aloneUse: false
    },
    language: {
        message: {
            title: {
                operationTips: "操作提示",
                confirmTips: "确认提示"
            },
            msg: {
                success: "操作成功",
                failed: "操作失败",
                error: "未知错误",
                checkSelfGrid: "请先勾选中要操作的数据前的复选框",
                selectSelfGrid: "请先选中要操作的数据",
                selectParentGrid: "请先选中主表中要操作的一条数据",
                permissionDenied: "对不起，你没有操作权限",
                confirmDelete: "你确定要删除所选的数据吗？",
                confirmMsg: "确定要执行该操作吗？"
            }
        }
    }
}