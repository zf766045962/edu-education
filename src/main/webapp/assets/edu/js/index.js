var $, tab;
layui.config({
    base: "assets/edu/js/"
}).use(['bodyTab', 'form', 'element', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = layui.layer,
        element = layui.element;
    $ = layui.jquery;
    tab = layui.bodyTab({
        openTabNum: "50",  //最大可打开窗口数量
        url: "json/navs.json" //获取菜单json地址
    });


//隐藏左侧导航
    $(".hideMenu").click(function () {
        $(".layui-layout-admin").toggleClass("showMenu");
        if ($(".layui-layout-admin").hasClass("showMenu")) {
            $(this).find("i").html("&#xecb6;");
        } else {
            $(this).find("i").html("&#xe631;");
        }
//渲染顶部窗口
        tab.tabMove();
    })

//渲染左侧菜单
//tab.render();

//关闭其他
    $(".closePageOther").on("click", function () {
        if ($("#top_tabs li").length > 2 && $("#top_tabs li.layui-this cite").text() != "后台首页") {
            var menu = JSON.parse(window.sessionStorage.getItem("menu"));
            $("#top_tabs li").each(function () {
                if ($(this).attr("lay-id") != '' && !$(this).hasClass("layui-this")) {
                    element.tabDelete("bodyTab", $(this).attr("lay-id")).init();
                }
            })
        } else if ($("#top_tabs li.layui-this cite").text() == "后台首页" && $("#top_tabs li").length > 1) {
            $("#top_tabs li").each(function () {
                if ($(this).attr("lay-id") != '' && !$(this).hasClass("layui-this")) {
                    element.tabDelete("bodyTab", $(this).attr("lay-id")).init();
                }
            })
        } else {
            layer.msg("没有可以关闭的窗口了@_@");
        }
//渲染顶部窗口
        tab.tabMove();
    })
//关闭全部
    $(".closePageAll").on("click", function () {
        if ($("#top_tabs li").length > 1) {
            $("#top_tabs li").each(function () {
                if ($(this).attr("lay-id") != '') {
                    element.tabDelete("bodyTab", $(this).attr("lay-id")).init();
                }
            })
        } else {
            layer.msg("没有可以关闭的窗口了@_@");
        }
//渲染顶部窗口
        tab.tabMove();
    })
});

//打开新窗口
function addTab(_this) {
    tab.tabAdd(_this);
}