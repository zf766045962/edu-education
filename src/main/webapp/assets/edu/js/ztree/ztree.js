/*
 * JQuery zTree core v3.5.22
 * http://zTree.me/
 *
 * Copyright (c) 2010 Hunter.z
 *
 * Licensed same as jquery - MIT License
 * http://www.opensource.org/licenses/mit-license.php
 *
 * email: hunter.z@263.net
 * Date: 2016-03-01
 */
(function(z) {
    var H = {},
        I = {},
        C = {},
        w = {
            treeId: "",
            treeObj: null,
            view: {
                addDiyDom: null,
                autoCancelSelected: !0,
                dblClickExpand: !0,
                expandSpeed: "fast",
                fontCss: {},
                nameIsHTML: !1,
                selectedMulti: !0,
                showIcon: !0,
                showLine: !0,
                showTitle: !0,
                txtSelectedEnable: !1
            },
            data: {
                key: {
                    children: "children",
                    name: "name",
                    title: "",
                    url: "url",
                    icon: "icon"
                },
                simpleData: {
                    enable: !1,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: null
                },
                keep: {
                    parent: !1,
                    leaf: !1
                }
            },
            async: {
                enable: !1,
                contentType: "application/x-www-form-urlencoded",
                type: "post",
                dataType: "text",
                url: "",
                autoParam: [],
                otherParam: [],
                dataFilter: null
            },
            callback: {
                beforeAsync: null,
                beforeClick: null,
                beforeDblClick: null,
                beforeRightClick: null,
                beforeMouseDown: null,
                beforeMouseUp: null,
                beforeExpand: null,
                beforeCollapse: null,
                beforeRemove: null,
                onAsyncError: null,
                onAsyncSuccess: null,
                onNodeCreated: null,
                onClick: null,
                onDblClick: null,
                onRightClick: null,
                onMouseDown: null,
                onMouseUp: null,
                onExpand: null,
                onCollapse: null,
                onRemove: null
            }
        },
        l = [function(m) {
            var d = m.treeObj,
                b = n.event;
            d.bind(b.NODECREATED, function(d, b, a) {
                h.apply(m.callback.onNodeCreated, [d, b, a])
            });
            d.bind(b.CLICK, function(d, b, a, F, c) {
                h.apply(m.callback.onClick, [b, a, F, c])
            });
            d.bind(b.EXPAND, function(d, b, a) {
                h.apply(m.callback.onExpand, [d, b, a])
            });
            d.bind(b.COLLAPSE, function(d, b, a) {
                h.apply(m.callback.onCollapse, [d, b, a])
            });
            d.bind(b.ASYNC_SUCCESS, function(d, b, a, F) {
                h.apply(m.callback.onAsyncSuccess, [d, b, a, F])
            });
            d.bind(b.ASYNC_ERROR, function(d, b, a, F, c, f) {
                h.apply(m.callback.onAsyncError, [d, b, a, F, c, f])
            });
            d.bind(b.REMOVE, function(d, b, a) {
                h.apply(m.callback.onRemove, [d, b, a])
            });
            d.bind(b.SELECTED, function(d, b, a) {
                h.apply(m.callback.onSelected, [b, a])
            });
            d.bind(b.UNSELECTED, function(d, b, a) {
                h.apply(m.callback.onUnSelected, [b, a])
            })
        }],
        r = [function(m) {
            var d = n.event;
            m.treeObj.unbind(d.NODECREATED).unbind(d.CLICK).unbind(d.EXPAND).unbind(d.COLLAPSE).unbind(d.ASYNC_SUCCESS).unbind(d.ASYNC_ERROR).unbind(d.REMOVE).unbind(d.SELECTED).unbind(d.UNSELECTED)
        }],
        t = [function(m) {
            var d = b.getCache(m);
            d || (d = {}, b.setCache(m, d));
            d.nodes = [];
            d.doms = []
        }],
        u = [function(m, d, a, g, c, f, k) {
            if (a) {
                k = b.getRoot(m);
                var p = m.data.key.children;
                a.level = d;
                a.tId = m.treeId + "_" + ++k.zId;
                a.parentTId = g ? g.tId : null;
                a.open = "string" == typeof a.open ? h.eqs(a.open, "true") : !! a.open;
                a[p] && 0 < a[p].length ? (a.isParent = !0, a.zAsync = !0) : (a.isParent = "string" == typeof a.isParent ? h.eqs(a.isParent, "true") : !! a.isParent, a.open = a.isParent && !m.async.enable ? a.open : !1, a.zAsync = !a.isParent);
                a.isFirstNode = c;
                a.isLastNode = f;
                a.getParentNode = function() {
                    return b.getNodeCache(m, a.parentTId)
                };
                a.getPreNode = function() {
                    return b.getPreNode(m, a)
                };
                a.getNextNode = function() {
                    return b.getNextNode(m, a)
                };
                a.getIndex = function() {
                    return b.getNodeIndex(m, a)
                };
                a.getPath = function() {
                    return b.getNodePath(m, a)
                };
                a.isAjaxing = !1;
                b.fixPIdKeyValue(m, a)
            }
        }],
        v = [function(m) {
            var d = m.target,
                a = b.getSetting(m.data.treeId),
                g = "",
                c = null,
                f = "",
                k = "",
                e = null,
                l = null,
                r = null;
            if (h.eqs(m.type, "mousedown")) k = "mousedown";
            else if (h.eqs(m.type, "mouseup")) k = "mouseup";
            else if (h.eqs(m.type, "contextmenu")) k = "contextmenu";
            else if (h.eqs(m.type, "click")) if (h.eqs(d.tagName, "span") && null !== d.getAttribute("treeNode" + n.id.SWITCH)) g = h.getNodeMainDom(d).id, f = "switchNode";
            else {
                if (r = h.getMDom(a, d, [{
                        tagName: "a",
                        attrName: "treeNode" + n.id.A
                    }])) g = h.getNodeMainDom(r).id, f = "clickNode"
            } else h.eqs(m.type, "dblclick") && (k = "dblclick", r = h.getMDom(a, d, [{
                tagName: "a",
                attrName: "treeNode" + n.id.A
            }])) && (g = h.getNodeMainDom(r).id, f = "switchNode");
            0 < k.length && 0 == g.length && (r = h.getMDom(a, d, [{
                tagName: "a",
                attrName: "treeNode" + n.id.A
            }])) && (g = h.getNodeMainDom(r).id);
            if (0 < g.length) switch (c = b.getNodeCache(a, g), f) {
                case "switchNode":
                    c.isParent ? h.eqs(m.type, "click") || h.eqs(m.type, "dblclick") && h.apply(a.view.dblClickExpand, [a.treeId, c], a.view.dblClickExpand) ? e = p.onSwitchNode : f = "" : f = "";
                    break;
                case "clickNode":
                    e = p.onClickNode
            }
            switch (k) {
                case "mousedown":
                    l = p.onZTreeMousedown;
                    break;
                case "mouseup":
                    l = p.onZTreeMouseup;
                    break;
                case "dblclick":
                    l = p.onZTreeDblclick;
                    break;
                case "contextmenu":
                    l = p.onZTreeContextmenu
            }
            return {
                stop: !1,
                node: c,
                nodeEventType: f,
                nodeEventCallback: e,
                treeEventType: k,
                treeEventCallback: l
            }
        }],
        B = [function(m) {
            var d = b.getRoot(m);
            d || (d = {}, b.setRoot(m, d));
            d[m.data.key.children] = [];
            d.expandTriggerFlag = !1;
            d.curSelectedList = [];
            d.noSelection = !0;
            d.createdNodes = [];
            d.zId = 0;
            d._ver = (new Date).getTime()
        }],
        M = [],
        K = [],
        L = [],
        e = [],
        a = [],
        b = {
            addNodeCache: function(m, d) {
                b.getCache(m).nodes[b.getNodeCacheId(d.tId)] = d
            },
            getNodeCacheId: function(m) {
                return m.substring(m.lastIndexOf("_") + 1)
            },
            addAfterA: function(m) {
                K.push(m)
            },
            addBeforeA: function(m) {
                M.push(m)
            },
            addInnerAfterA: function(m) {
                e.push(m)
            },
            addInnerBeforeA: function(m) {
                L.push(m)
            },
            addInitBind: function(m) {
                l.push(m)
            },
            addInitUnBind: function(m) {
                r.push(m)
            },
            addInitCache: function(m) {
                t.push(m)
            },
            addInitNode: function(m) {
                u.push(m)
            },
            addInitProxy: function(m, d) {
                d ? v.splice(0, 0, m) : v.push(m)
            },
            addInitRoot: function(m) {
                B.push(m)
            },
            addNodesData: function(m, d, a, b) {
                var c = m.data.key.children;
                d[c] ? a >= d[c].length && (a = -1) : (d[c] = [], a = -1);
                0 < d[c].length && 0 === a ? (d[c][0].isFirstNode = !1, f.setNodeLineIcos(m, d[c][0])) : 0 < d[c].length && 0 > a && (d[c][d[c].length - 1].isLastNode = !1, f.setNodeLineIcos(m, d[c][d[c].length - 1]));
                d.isParent = !0;
                0 > a ? d[c] = d[c].concat(b) : (m = [a, 0].concat(b), d[c].splice.apply(d[c], m))
            },
            addSelectedNode: function(m, d) {
                var a = b.getRoot(m);
                b.isSelectedNode(m, d) || a.curSelectedList.push(d)
            },
            addCreatedNode: function(m, d) {
                (m.callback.onNodeCreated || m.view.addDiyDom) && b.getRoot(m).createdNodes.push(d)
            },
            addZTreeTools: function(m) {
                a.push(m)
            },
            exSetting: function(m) {
                z.extend(!0, w, m)
            },
            fixPIdKeyValue: function(m, d) {
                m.data.simpleData.enable && (d[m.data.simpleData.pIdKey] = d.parentTId ? d.getParentNode()[m.data.simpleData.idKey] : m.data.simpleData.rootPId)
            },
            getAfterA: function(m, d, a) {
                for (var b = 0, c = K.length; b < c; b++) K[b].apply(this, arguments)
            },
            getBeforeA: function(m, d, a) {
                for (var b = 0, c = M.length; b < c; b++) M[b].apply(this, arguments)
            },
            getInnerAfterA: function(m, d, a) {
                for (var b = 0, c = e.length; b < c; b++) e[b].apply(this, arguments)
            },
            getInnerBeforeA: function(m, d, a) {
                for (var b = 0, c = L.length; b < c; b++) L[b].apply(this, arguments)
            },
            getCache: function(m) {
                return C[m.treeId]
            },
            getNodeIndex: function(m, d) {
                if (!d) return null;
                for (var a = m.data.key.children, c = d.parentTId ? d.getParentNode() : b.getRoot(m), q = 0, f = c[a].length - 1; q <= f; q++) if (c[a][q] === d) return q;
                return -1
            },
            getNextNode: function(m, d) {
                if (!d) return null;
                for (var a = m.data.key.children, c = d.parentTId ? d.getParentNode() : b.getRoot(m), q = 0, f = c[a].length - 1; q <= f; q++) if (c[a][q] === d) return q == f ? null : c[a][q + 1];
                return null
            },
            getNodeByParam: function(m, d, a, c) {
                if (!d || !a) return null;
                for (var q = m.data.key.children, f = 0, h = d.length; f < h; f++) {
                    if (d[f][a] == c) return d[f];
                    var k = b.getNodeByParam(m, d[f][q], a, c);
                    if (k) return k
                }
                return null
            },
            getNodeCache: function(m, d) {
                if (!d) return null;
                var a = C[m.treeId].nodes[b.getNodeCacheId(d)];
                return a ? a : null
            },
            getNodeName: function(m, d) {
                return "" + d[m.data.key.name]
            },
            getNodePath: function(m, d) {
                if (!d) return null;
                var a;
                (a = d.parentTId ? d.getParentNode().getPath() : []) && a.push(d);
                return a
            },
            getNodeTitle: function(m, d) {
                return "" + d["" === m.data.key.title ? m.data.key.name : m.data.key.title]
            },
            getNodes: function(m) {
                return b.getRoot(m)[m.data.key.children]
            },
            getNodesByParam: function(m, d, a, c) {
                if (!d || !a) return [];
                for (var q = m.data.key.children, f = [], h = 0, k = d.length; h < k; h++) d[h][a] == c && f.push(d[h]), f = f.concat(b.getNodesByParam(m, d[h][q], a, c));
                return f
            },
            getNodesByParamFuzzy: function(m, d, a, c) {
                if (!d || !a) return [];
                var q = m.data.key.children,
                    f = [];
                c = c.toLowerCase();
                for (var h = 0, k = d.length; h < k; h++)"string" == typeof d[h][a] && -1 < d[h][a].toLowerCase().indexOf(c) && f.push(d[h]), f = f.concat(b.getNodesByParamFuzzy(m, d[h][q], a, c));
                return f
            },
            getNodesByFilter: function(m, d, a, c, q) {
                if (!d) return c ? null : [];
                for (var f = m.data.key.children, k = c ? null : [], p = 0, n = d.length; p < n; p++) {
                    if (h.apply(a, [d[p], q], !1)) {
                        if (c) return d[p];
                        k.push(d[p])
                    }
                    var e = b.getNodesByFilter(m, d[p][f], a, c, q);
                    if (c && e) return e;
                    k = c ? e : k.concat(e)
                }
                return k
            },
            getPreNode: function(m, d) {
                if (!d) return null;
                for (var a = m.data.key.children, c = d.parentTId ? d.getParentNode() : b.getRoot(m), q = 0, f = c[a].length; q < f; q++) if (c[a][q] === d) return 0 == q ? null : c[a][q - 1];
                return null
            },
            getRoot: function(m) {
                return m ? I[m.treeId] : null
            },
            getRoots: function() {
                return I
            },
            getSetting: function(m) {
                return H[m]
            },
            getSettings: function() {
                return H
            },
            getZTreeTools: function(m) {
                return (m = this.getRoot(this.getSetting(m))) ? m.treeTools : null
            },
            initCache: function(m) {
                for (var d = 0, a = t.length; d < a; d++) t[d].apply(this, arguments)
            },
            initNode: function(m, d, a, b, c, f) {
                for (var h = 0, k = u.length; h < k; h++) u[h].apply(this, arguments)
            },
            initRoot: function(m) {
                for (var d = 0, a = B.length; d < a; d++) B[d].apply(this, arguments)
            },
            isSelectedNode: function(m, d) {
                for (var a = b.getRoot(m), c = 0, q = a.curSelectedList.length; c < q; c++) if (d === a.curSelectedList[c]) return !0;
                return !1
            },
            removeNodeCache: function(m, d) {
                var a = m.data.key.children;
                if (d[a]) for (var c = 0, q = d[a].length; c < q; c++) arguments.callee(m, d[a][c]);
                b.getCache(m).nodes[b.getNodeCacheId(d.tId)] = null
            },
            removeSelectedNode: function(m, d) {
                for (var a = b.getRoot(m), c = 0, q = a.curSelectedList.length; c < q; c++) d !== a.curSelectedList[c] && b.getNodeCache(m, a.curSelectedList[c].tId) || (a.curSelectedList.splice(c, 1), m.treeObj.trigger(n.event.UNSELECTED, [m.treeId, d]), c--, q--)
            },
            setCache: function(m, d) {
                C[m.treeId] = d
            },
            setRoot: function(m, d) {
                I[m.treeId] = d
            },
            setZTreeTools: function(m, d) {
                for (var b = 0, c = a.length; b < c; b++) a[b].apply(this, arguments)
            },
            transformToArrayFormat: function(m, d) {
                if (!d) return [];
                var a = m.data.key.children,
                    c = [];
                if (h.isArray(d)) for (var q = 0, f = d.length; q < f; q++) c.push(d[q]), d[q][a] && (c = c.concat(b.transformToArrayFormat(m, d[q][a])));
                else c.push(d), d[a] && (c = c.concat(b.transformToArrayFormat(m, d[a])));
                return c
            },
            transformTozTreeFormat: function(a, d) {
                var b, c, q = a.data.simpleData.idKey,
                    f = a.data.simpleData.pIdKey,
                    k = a.data.key.children;
                if (!q || "" == q || !d) return [];
                if (h.isArray(d)) {
                    var p = [],
                        n = [];
                    b = 0;
                    for (c = d.length; b < c; b++) n[d[b][q]] = d[b];
                    b = 0;
                    for (c = d.length; b < c; b++) n[d[b][f]] && d[b][q] != d[b][f] ? (n[d[b][f]][k] || (n[d[b][f]][k] = []), n[d[b][f]][k].push(d[b])) : p.push(d[b]);
                    return p
                }
                return [d]
            }
        },
        c = {
            bindEvent: function(a) {
                for (var d = 0, b = l.length; d < b; d++) l[d].apply(this, arguments)
            },
            unbindEvent: function(a) {
                for (var d = 0, b = r.length; d < b; d++) r[d].apply(this, arguments)
            },
            bindTree: function(a) {
                var d = {
                        treeId: a.treeId
                    },
                    b = a.treeObj;
                a.view.txtSelectedEnable || b.bind("selectstart", p.onSelectStart).css({
                    "-moz-user-select": "-moz-none"
                });
                b.bind("click", d, c.proxy);
                b.bind("dblclick", d, c.proxy);
                b.bind("mouseover", d, c.proxy);
                b.bind("mouseout", d, c.proxy);
                b.bind("mousedown", d, c.proxy);
                b.bind("mouseup", d, c.proxy);
                b.bind("contextmenu", d, c.proxy)
            },
            unbindTree: function(a) {
                a.treeObj.unbind("selectstart", p.onSelectStart).unbind("click", c.proxy).unbind("dblclick", c.proxy).unbind("mouseover", c.proxy).unbind("mouseout", c.proxy).unbind("mousedown", c.proxy).unbind("mouseup", c.proxy).unbind("contextmenu", c.proxy)
            },
            doProxy: function(a) {
                for (var d = [], b = 0, c = v.length; b < c; b++) {
                    var q = v[b].apply(this, arguments);
                    d.push(q);
                    if (q.stop) break
                }
                return d
            },
            proxy: function(a) {
                var d = b.getSetting(a.data.treeId);
                if (!h.uCanDo(d, a)) return !0;
                for (var d = c.doProxy(a), F = !0, g = 0, q = d.length; g < q; g++) {
                    var f = d[g];
                    f.nodeEventCallback && (F = f.nodeEventCallback.apply(f, [a, f.node]) && F);
                    f.treeEventCallback && (F = f.treeEventCallback.apply(f, [a, f.node]) && F)
                }
                return F
            }
        },
        p = {
            onSwitchNode: function(a, d) {
                var c = b.getSetting(a.data.treeId);
                if (d.open) {
                    if (0 == h.apply(c.callback.beforeCollapse, [c.treeId, d], !0)) return !0
                } else if (0 == h.apply(c.callback.beforeExpand, [c.treeId, d], !0)) return !0;
                b.getRoot(c).expandTriggerFlag = !0;
                f.switchNode(c, d);
                return !0
            },
            onClickNode: function(a, d) {
                var c = b.getSetting(a.data.treeId),
                    g = c.view.autoCancelSelected && (a.ctrlKey || a.metaKey) && b.isSelectedNode(c, d) ? 0 : c.view.autoCancelSelected && (a.ctrlKey || a.metaKey) && c.view.selectedMulti ? 2 : 1;
                if (0 == h.apply(c.callback.beforeClick, [c.treeId, d, g], !0)) return !0;
                0 === g ? f.cancelPreSelectedNode(c, d) : f.selectNode(c, d, 2 === g);
                c.treeObj.trigger(n.event.CLICK, [a, c.treeId, d, g]);
                return !0
            },
            onZTreeMousedown: function(a, d) {
                var c = b.getSetting(a.data.treeId);
                h.apply(c.callback.beforeMouseDown, [c.treeId, d], !0) && h.apply(c.callback.onMouseDown, [a, c.treeId, d]);
                return !0
            },
            onZTreeMouseup: function(a, d) {
                var c = b.getSetting(a.data.treeId);
                h.apply(c.callback.beforeMouseUp, [c.treeId, d], !0) && h.apply(c.callback.onMouseUp, [a, c.treeId, d]);
                return !0
            },
            onZTreeDblclick: function(a, d) {
                var c = b.getSetting(a.data.treeId);
                h.apply(c.callback.beforeDblClick, [c.treeId, d], !0) && h.apply(c.callback.onDblClick, [a, c.treeId, d]);
                return !0
            },
            onZTreeContextmenu: function(a, d) {
                var c = b.getSetting(a.data.treeId);
                h.apply(c.callback.beforeRightClick, [c.treeId, d], !0) && h.apply(c.callback.onRightClick, [a, c.treeId, d]);
                return "function" != typeof c.callback.onRightClick
            },
            onSelectStart: function(a) {
                a = a.originalEvent.srcElement.nodeName.toLowerCase();
                return "input" === a || "textarea" === a
            }
        },
        h = {
            apply: function(a, d, b) {
                return "function" == typeof a ? a.apply(y, d ? d : []) : b
            },
            canAsync: function(a, d) {
                var b = a.data.key.children;
                return a.async.enable && d && d.isParent && !(d.zAsync || d[b] && 0 < d[b].length)
            },
            clone: function(a) {
                if (null === a) return null;
                var d = h.isArray(a) ? [] : {},
                    b;
                for (b in a) d[b] = a[b] instanceof Date ? new Date(a[b].getTime()) : "object" === typeof a[b] ? arguments.callee(a[b]) : a[b];
                return d
            },
            eqs: function(a, d) {
                return a.toLowerCase() === d.toLowerCase()
            },
            isArray: function(a) {
                return "[object Array]" === Object.prototype.toString.apply(a)
            },
            $: function(a, d, b) {
                d && "string" != typeof d && (b = d, d = "");
                return "string" == typeof a ? z(a, b ? b.treeObj.get(0).ownerDocument : null) : z("#" + a.tId + d, b ? b.treeObj : null)
            },
            getMDom: function(a, d, b) {
                if (!d) return null;
                for (; d && d.id !== a.treeId;) {
                    for (var c = 0, q = b.length; d.tagName && c < q; c++) if (h.eqs(d.tagName, b[c].tagName) && null !== d.getAttribute(b[c].attrName)) return d;
                    d = d.parentNode
                }
                return null
            },
            getNodeMainDom: function(a) {
                return z(a).parent("li").get(0) || z(a).parentsUntil("li").parent().get(0)
            },
            isChildOrSelf: function(a, d) {
                return 0 < z(a).closest("#" + d).length
            },
            uCanDo: function(a, d) {
                return !0
            }
        },
        f = {
            addNodes: function(a, d, c, g, q) {
                if (!a.data.keep.leaf || !d || d.isParent) if (h.isArray(g) || (g = [g]), a.data.simpleData.enable && (g = b.transformTozTreeFormat(a, g)), d) {
                    var p = k(d, n.id.SWITCH, a),
                        e = k(d, n.id.ICON, a),
                        l = k(d, n.id.UL, a);
                    d.open || (f.replaceSwitchClass(d, p, n.folder.CLOSE), f.replaceIcoClass(d, e, n.folder.CLOSE), d.open = !1, l.css({
                        display: "none"
                    }));
                    b.addNodesData(a, d, c, g);
                    f.createNodes(a, d.level + 1, g, d, c);
                    q || f.expandCollapseParentNode(a, d, !0)
                } else b.addNodesData(a, b.getRoot(a), c, g), f.createNodes(a, 0, g, null, c)
            },
            appendNodes: function(a, d, c, g, q, h, k) {
                if (!c) return [];
                var p = [],
                    n = a.data.key.children,
                    e = (g ? g : b.getRoot(a))[n],
                    l, r;
                if (!e || q >= e.length) q = -1;
                for (var y = 0, u = c.length; y < u; y++) {
                    var t = c[y];
                    h && (l = (0 === q || e.length == c.length) && 0 == y, r = 0 > q && y == c.length - 1, b.initNode(a, d, t, g, l, r, k), b.addNodeCache(a, t));
                    l = [];
                    t[n] && 0 < t[n].length && (l = f.appendNodes(a, d + 1, t[n], t, -1, h, k && t.open));
                    k && (f.makeDOMNodeMainBefore(p, a, t), f.makeDOMNodeLine(p, a, t), b.getBeforeA(a, t, p), f.makeDOMNodeNameBefore(p, a, t), b.getInnerBeforeA(a, t, p), f.makeDOMNodeIcon(p, a, t), b.getInnerAfterA(a, t, p), f.makeDOMNodeNameAfter(p, a, t), b.getAfterA(a, t, p), t.isParent && t.open && f.makeUlHtml(a, t, p, l.join("")), f.makeDOMNodeMainAfter(p, a, t), b.addCreatedNode(a, t))
                }
                return p
            },
            appendParentULDom: function(a, d) {
                var b = [],
                    c = k(d, a);
                !c.get(0) && d.parentTId && (f.appendParentULDom(a, d.getParentNode()), c = k(d, a));
                var q = k(d, n.id.UL, a);
                q.get(0) && q.remove();
                q = f.appendNodes(a, d.level + 1, d[a.data.key.children], d, -1, !1, !0);
                f.makeUlHtml(a, d, b, q.join(""));
                c.append(b.join(""))
            },
            asyncNode: function(a, d, c, g) {
                var q, p;
                if (d && !d.isParent) return h.apply(g), !1;
                if (d && d.isAjaxing) return !1;
                if (0 == h.apply(a.callback.beforeAsync, [a.treeId, d], !0)) return h.apply(g), !1;
                d && (d.isAjaxing = !0, k(d, n.id.ICON, a).attr({
                    style: "",
                    "class": n.className.BUTTON + " " + n.className.ICO_LOADING
                }));
                var e = {};
                q = 0;
                for (p = a.async.autoParam.length; d && q < p; q++) {
                    var l = a.async.autoParam[q].split("\x3d"),
                        r = l;
                    1 < l.length && (r = l[1], l = l[0]);
                    e[r] = d[l]
                }
                if (h.isArray(a.async.otherParam)) for (q = 0, p = a.async.otherParam.length; q < p; q += 2) e[a.async.otherParam[q]] = a.async.otherParam[q + 1];
                else for (var y in a.async.otherParam) e[y] = a.async.otherParam[y];
                var t = b.getRoot(a)._ver;
                z.ajax({
                    contentType: a.async.contentType,
                    cache: !1,
                    type: a.async.type,
                    url: h.apply(a.async.url, [a.treeId, d], a.async.url),
                    data: e,
                    dataType: a.async.dataType,
                    success: function(q) {
                        if (t == b.getRoot(a)._ver) {
                            var k = [];
                            try {
                                k = q && 0 != q.length ? "string" == typeof q ? eval("(" + q + ")") : q : []
                            } catch (p) {
                                k = q
                            }
                            d && (d.isAjaxing = null, d.zAsync = !0);
                            f.setNodeLineIcos(a, d);
                            k && "" !== k ? (k = h.apply(a.async.dataFilter, [a.treeId, d, k], k), f.addNodes(a, d, -1, k ? h.clone(k) : [], !! c)) : f.addNodes(a, d, -1, [], !! c);
                            a.treeObj.trigger(n.event.ASYNC_SUCCESS, [a.treeId, d, q]);
                            h.apply(g)
                        }
                    },
                    error: function(c, g, q) {
                        t == b.getRoot(a)._ver && (d && (d.isAjaxing = null), f.setNodeLineIcos(a, d), a.treeObj.trigger(n.event.ASYNC_ERROR, [a.treeId, d, c, g, q]))
                    }
                });
                return !0
            },
            cancelPreSelectedNode: function(a, d, c) {
                var g = b.getRoot(a).curSelectedList,
                    q, f;
                for (q = g.length - 1; 0 <= q; q--) if (f = g[q], d === f || !(d || c && c === f)) if (k(f, n.id.A, a).removeClass(n.node.CURSELECTED), d) {
                    b.removeSelectedNode(a, d);
                    break
                } else g.splice(q, 1), a.treeObj.trigger(n.event.UNSELECTED, [a.treeId, f])
            },
            createNodeCallback: function(a) {
                if (a.callback.onNodeCreated || a.view.addDiyDom) for (var d = b.getRoot(a); 0 < d.createdNodes.length;) {
                    var c = d.createdNodes.shift();
                    h.apply(a.view.addDiyDom, [a.treeId, c]);
                    a.callback.onNodeCreated && a.treeObj.trigger(n.event.NODECREATED, [a.treeId, c])
                }
            },
            createNodes: function(a, d, c, g, q) {
                if (c && 0 != c.length) {
                    var h = b.getRoot(a),
                        p = a.data.key.children,
                        p = !g || g.open || !! k(g[p][0], a).get(0);
                    h.createdNodes = [];
                    d = f.appendNodes(a, d, c, g, q, !0, p);
                    var e, l;
                    g ? (g = k(g, n.id.UL, a), g.get(0) && (e = g)) : e = a.treeObj;
                    e && (0 <= q && (l = e.children()[q]), 0 <= q && l ? z(l).before(d.join("")) : e.append(d.join("")));
                    f.createNodeCallback(a)
                }
            },
            destroy: function(a) {
                a && (b.initCache(a), b.initRoot(a), c.unbindTree(a), c.unbindEvent(a), a.treeObj.empty(), delete H[a.treeId])
            },
            expandCollapseNode: function(a, d, c, g, q) {
                var p = b.getRoot(a),
                    e = a.data.key.children;
                if (d) {
                    if (p.expandTriggerFlag) {
                        var l = q;
                        q = function() {
                            l && l();
                            d.open ? a.treeObj.trigger(n.event.EXPAND, [a.treeId, d]) : a.treeObj.trigger(n.event.COLLAPSE, [a.treeId, d])
                        };
                        p.expandTriggerFlag = !1
                    }!d.open && d.isParent && (!k(d, n.id.UL, a).get(0) || d[e] && 0 < d[e].length && !k(d[e][0], a).get(0)) && (f.appendParentULDom(a, d), f.createNodeCallback(a));
                    if (d.open == c) h.apply(q, []);
                    else {
                        c = k(d, n.id.UL, a);
                        var p = k(d, n.id.SWITCH, a),
                            r = k(d, n.id.ICON, a);
                        d.isParent ? (d.open = !d.open, d.iconOpen && d.iconClose && r.attr("style", f.makeNodeIcoStyle(a, d)), d.open ? (f.replaceSwitchClass(d, p, n.folder.OPEN), f.replaceIcoClass(d, r, n.folder.OPEN), 0 == g || "" == a.view.expandSpeed ? (c.show(), h.apply(q, [])) : d[e] && 0 < d[e].length ? c.slideDown(a.view.expandSpeed, q) : (c.show(), h.apply(q, []))) : (f.replaceSwitchClass(d, p, n.folder.CLOSE), f.replaceIcoClass(d, r, n.folder.CLOSE), 0 != g && "" != a.view.expandSpeed && d[e] && 0 < d[e].length ? c.slideUp(a.view.expandSpeed, q) : (c.hide(), h.apply(q, [])))) : h.apply(q, [])
                    }
                } else h.apply(q, [])
            },
            expandCollapseParentNode: function(a, d, b, c, q) {
                d && (d.parentTId ? (f.expandCollapseNode(a, d, b, c), d.parentTId && f.expandCollapseParentNode(a, d.getParentNode(), b, c, q)) : f.expandCollapseNode(a, d, b, c, q))
            },
            expandCollapseSonNode: function(a, d, c, g, q) {
                var h = b.getRoot(a),
                    k = a.data.key.children,
                    h = d ? d[k] : h[k],
                    k = d ? !1 : g,
                    p = b.getRoot(a).expandTriggerFlag;
                b.getRoot(a).expandTriggerFlag = !1;
                if (h) for (var e = 0, n = h.length; e < n; e++) h[e] && f.expandCollapseSonNode(a, h[e], c, k);
                b.getRoot(a).expandTriggerFlag = p;
                f.expandCollapseNode(a, d, c, g, q)
            },
            isSelectedNode: function(a, d) {
                if (!d) return !1;
                var c = b.getRoot(a).curSelectedList,
                    g;
                for (g = c.length - 1; 0 <= g; g--) if (d === c[g]) return !0;
                return !1
            },
            makeDOMNodeIcon: function(a, d, c) {
                var g = b.getNodeName(d, c),
                    g = d.view.nameIsHTML ? g : g.replace(/&/g, "\x26amp;").replace(/</g, "\x26lt;").replace(/>/g, "\x26gt;");
                a.push("\x3cspan id\x3d'", c.tId, n.id.ICON, "' title\x3d'' treeNode", n.id.ICON, " class\x3d'", f.makeNodeIcoClass(d, c), "' style\x3d'", f.makeNodeIcoStyle(d, c), "'\x3e\x3c/span\x3e\x3cspan id\x3d'", c.tId, n.id.SPAN, "' class\x3d'", n.className.NAME, "'\x3e", g, "\x3c/span\x3e")
            },
            makeDOMNodeLine: function(a, d, b) {
                a.push("\x3cspan id\x3d'", b.tId, n.id.SWITCH, "' title\x3d'' class\x3d'", f.makeNodeLineClass(d, b), "' treeNode", n.id.SWITCH, "\x3e\x3c/span\x3e")
            },
            makeDOMNodeMainAfter: function(a, d, b) {
                a.push("\x3c/li\x3e")
            },
            makeDOMNodeMainBefore: function(a, d, b) {
                a.push("\x3cli id\x3d'", b.tId, "' class\x3d'", n.className.LEVEL, b.level, "' tabindex\x3d'0' hidefocus\x3d'true' treenode\x3e")
            },
            makeDOMNodeNameAfter: function(a, d, b) {
                a.push("\x3c/a\x3e")
            },
            makeDOMNodeNameBefore: function(a, d, c) {
                var g = b.getNodeTitle(d, c),
                    q = f.makeNodeUrl(d, c),
                    k = f.makeNodeFontCss(d, c),
                    p = [],
                    e;
                for (e in k) p.push(e, ":", k[e], ";");
                a.push("\x3ca id\x3d'", c.tId, n.id.A, "' class\x3d'", n.className.LEVEL, c.level, "' treeNode", n.id.A, ' onclick\x3d"', c.click || "", '" ', null != q && 0 < q.length ? "href\x3d'" + q + "'" : "", " target\x3d'", f.makeNodeTarget(c), "' style\x3d'", p.join(""), "'");
                h.apply(d.view.showTitle, [d.treeId, c], d.view.showTitle) && g && a.push("title\x3d'", g.replace(/'/g, "\x26#39;").replace(/</g, "\x26lt;").replace(/>/g, "\x26gt;"), "'");
                a.push("\x3e")
            },
            makeNodeFontCss: function(a, d) {
                var b = h.apply(a.view.fontCss, [a.treeId, d], a.view.fontCss);
                return b && "function" != typeof b ? b : {}
            },
            makeNodeIcoClass: function(a, b) {
                var c = ["ico"];
                b.isAjaxing || (c[0] = (b.iconSkin ? b.iconSkin + "_" : "") + c[0], b.isParent ? c.push(b.open ? n.folder.OPEN : n.folder.CLOSE) : c.push(n.folder.DOCU));
                return n.className.BUTTON + " " + c.join("_")
            },
            makeNodeIcoStyle: function(a, b) {
                var c = [];
                if (!b.isAjaxing) {
                    var g = b.isParent && b.iconOpen && b.iconClose ? b.open ? b.iconOpen : b.iconClose : b[a.data.key.icon];
                    g && c.push("background:url(", g, ") 0 0 no-repeat;");
                    0 != a.view.showIcon && h.apply(a.view.showIcon, [a.treeId, b], !0) || c.push("width:0px;height:0px;")
                }
                return c.join("")
            },
            makeNodeLineClass: function(a, b) {
                var c = [];
                a.view.showLine ? 0 == b.level && b.isFirstNode && b.isLastNode ? c.push(n.line.ROOT) : 0 == b.level && b.isFirstNode ? c.push(n.line.ROOTS) : b.isLastNode ? c.push(n.line.BOTTOM) : c.push(n.line.CENTER) : c.push(n.line.NOLINE);
                b.isParent ? c.push(b.open ? n.folder.OPEN : n.folder.CLOSE) : c.push(n.folder.DOCU);
                return f.makeNodeLineClassEx(b) + c.join("_")
            },
            makeNodeLineClassEx: function(a) {
                return n.className.BUTTON + " " + n.className.LEVEL + a.level + " " + n.className.SWITCH + " "
            },
            makeNodeTarget: function(a) {
                return a.target || "_blank"
            },
            makeNodeUrl: function(a, b) {
                var c = a.data.key.url;
                return b[c] ? b[c] : null
            },
            makeUlHtml: function(a, b, c, g) {
                c.push("\x3cul id\x3d'", b.tId, n.id.UL, "' class\x3d'", n.className.LEVEL, b.level, " ", f.makeUlLineClass(a, b), "' style\x3d'display:", b.open ? "block" : "none", "'\x3e");
                c.push(g);
                c.push("\x3c/ul\x3e")
            },
            makeUlLineClass: function(a, b) {
                return a.view.showLine && !b.isLastNode ? n.line.LINE : ""
            },
            removeChildNodes: function(a, c) {
                if (c) {
                    var h = a.data.key.children,
                        g = c[h];
                    if (g) {
                        for (var q = 0, p = g.length; q < p; q++) b.removeNodeCache(a, g[q]);
                        b.removeSelectedNode(a);
                        delete c[h];
                        a.data.keep.parent ? k(c, n.id.UL, a).empty() : (c.isParent = !1, c.open = !1, h = k(c, n.id.SWITCH, a), g = k(c, n.id.ICON, a), f.replaceSwitchClass(c, h, n.folder.DOCU), f.replaceIcoClass(c, g, n.folder.DOCU), k(c, n.id.UL, a).remove())
                    }
                }
            },
            setFirstNode: function(a, b) {
                var c = a.data.key.children;
                0 < b[c].length && (b[c][0].isFirstNode = !0)
            },
            setLastNode: function(a, b) {
                var c = a.data.key.children,
                    g = b[c].length;
                0 < g && (b[c][g - 1].isLastNode = !0)
            },
            removeNode: function(a, c) {
                var h = b.getRoot(a),
                    g = a.data.key.children,
                    q = c.parentTId ? c.getParentNode() : h;
                c.isFirstNode = !1;
                c.isLastNode = !1;
                c.getPreNode = function() {
                    return null
                };
                c.getNextNode = function() {
                    return null
                };
                if (b.getNodeCache(a, c.tId)) {
                    k(c, a).remove();
                    b.removeNodeCache(a, c);
                    b.removeSelectedNode(a, c);
                    for (var p = 0, e = q[g].length; p < e; p++) if (q[g][p].tId == c.tId) {
                        q[g].splice(p, 1);
                        break
                    }
                    f.setFirstNode(a, q);
                    f.setLastNode(a, q);
                    var l, p = q[g].length;
                    if (!a.data.keep.parent && 0 == p) q.isParent = !1, q.open = !1, p = k(q, n.id.UL, a), e = k(q, n.id.SWITCH, a), l = k(q, n.id.ICON, a), f.replaceSwitchClass(q, e, n.folder.DOCU), f.replaceIcoClass(q, l, n.folder.DOCU), p.css("display", "none");
                    else if (a.view.showLine && 0 < p) {
                        var r = q[g][p - 1],
                            p = k(r, n.id.UL, a),
                            e = k(r, n.id.SWITCH, a);
                        l = k(r, n.id.ICON, a);
                        q == h ? 1 == q[g].length ? f.replaceSwitchClass(r, e, n.line.ROOT) : (h = k(q[g][0], n.id.SWITCH, a), f.replaceSwitchClass(q[g][0], h, n.line.ROOTS), f.replaceSwitchClass(r, e, n.line.BOTTOM)) : f.replaceSwitchClass(r, e, n.line.BOTTOM);
                        p.removeClass(n.line.LINE)
                    }
                }
            },
            replaceIcoClass: function(a, b, c) {
                if (b && !a.isAjaxing && (a = b.attr("class"), void 0 != a)) {
                    a = a.split("_");
                    switch (c) {
                        case n.folder.OPEN:
                        case n.folder.CLOSE:
                        case n.folder.DOCU:
                            a[a.length - 1] = c
                    }
                    b.attr("class", a.join("_"))
                }
            },
            replaceSwitchClass: function(a, b, c) {
                if (b) {
                    var g = b.attr("class");
                    if (void 0 != g) {
                        g = g.split("_");
                        switch (c) {
                            case n.line.ROOT:
                            case n.line.ROOTS:
                            case n.line.CENTER:
                            case n.line.BOTTOM:
                            case n.line.NOLINE:
                                g[0] = f.makeNodeLineClassEx(a) + c;
                                break;
                            case n.folder.OPEN:
                            case n.folder.CLOSE:
                            case n.folder.DOCU:
                                g[1] = c
                        }
                        b.attr("class", g.join("_"));
                        c !== n.folder.DOCU ? b.removeAttr("disabled") : b.attr("disabled", "disabled")
                    }
                }
            },
            selectNode: function(a, c, h) {
                h || f.cancelPreSelectedNode(a, null, c);
                k(c, n.id.A, a).addClass(n.node.CURSELECTED);
                b.addSelectedNode(a, c);
                a.treeObj.trigger(n.event.SELECTED, [a.treeId, c])
            },
            setNodeFontCss: function(a, b) {
                var c = k(b, n.id.A, a),
                    g = f.makeNodeFontCss(a, b);
                g && c.css(g)
            },
            setNodeLineIcos: function(a, b) {
                if (b) {
                    var c = k(b, n.id.SWITCH, a),
                        g = k(b, n.id.UL, a),
                        q = k(b, n.id.ICON, a),
                        h = f.makeUlLineClass(a, b);
                    0 == h.length ? g.removeClass(n.line.LINE) : g.addClass(h);
                    c.attr("class", f.makeNodeLineClass(a, b));
                    b.isParent ? c.removeAttr("disabled") : c.attr("disabled", "disabled");
                    q.removeAttr("style");
                    q.attr("style", f.makeNodeIcoStyle(a, b));
                    q.attr("class", f.makeNodeIcoClass(a, b))
                }
            },
            setNodeName: function(a, c) {
                var f = b.getNodeTitle(a, c),
                    g = k(c, n.id.SPAN, a);
                g.empty();
                a.view.nameIsHTML ? g.html(b.getNodeName(a, c)) : g.text(b.getNodeName(a, c));
                h.apply(a.view.showTitle, [a.treeId, c], a.view.showTitle) && k(c, n.id.A, a).attr("title", f ? f : "")
            },
            setNodeTarget: function(a, b) {
                k(b, n.id.A, a).attr("target", f.makeNodeTarget(b))
            },
            setNodeUrl: function(a, b) {
                var c = k(b, n.id.A, a),
                    g = f.makeNodeUrl(a, b);
                null == g || 0 == g.length ? c.removeAttr("href") : c.attr("href", g)
            },
            switchNode: function(a, b) {
                b.open || !h.canAsync(a, b) ? f.expandCollapseNode(a, b, !b.open) : a.async.enable ? f.asyncNode(a, b) || f.expandCollapseNode(a, b, !b.open) : b && f.expandCollapseNode(a, b, !b.open)
            }
        };
    z.fn.zTree = {
        consts: {
            className: {
                BUTTON: "button",
                LEVEL: "level",
                ICO_LOADING: "ico_loading",
                SWITCH: "switch",
                NAME: "node_name"
            },
            event: {
                NODECREATED: "ztree_nodeCreated",
                CLICK: "ztree_click",
                EXPAND: "ztree_expand",
                COLLAPSE: "ztree_collapse",
                ASYNC_SUCCESS: "ztree_async_success",
                ASYNC_ERROR: "ztree_async_error",
                REMOVE: "ztree_remove",
                SELECTED: "ztree_selected",
                UNSELECTED: "ztree_unselected"
            },
            id: {
                A: "_a",
                ICON: "_ico",
                SPAN: "_span",
                SWITCH: "_switch",
                UL: "_ul"
            },
            line: {
                ROOT: "root",
                ROOTS: "roots",
                CENTER: "center",
                BOTTOM: "bottom",
                NOLINE: "noline",
                LINE: "line"
            },
            folder: {
                OPEN: "open",
                CLOSE: "close",
                DOCU: "docu"
            },
            node: {
                CURSELECTED: "curSelectedNode"
            }
        },
        _z: {
            tools: h,
            view: f,
            event: c,
            data: b
        },
        getZTreeObj: function(a) {
            return (a = b.getZTreeTools(a)) ? a : null
        },
        destroy: function(a) {
            if (a && 0 < a.length) f.destroy(b.getSetting(a));
            else for (var c in H) f.destroy(H[c])
        },
        init: function(a, d, p) {
            var g = h.clone(w);
            z.extend(!0, g, d);
            g.treeId = a.attr("id");
            g.treeObj = a;
            g.treeObj.empty();
            H[g.treeId] = g;
            "undefined" === typeof document.body.style.maxHeight && (g.view.expandSpeed = "");
            b.initRoot(g);
            a = b.getRoot(g);
            d = g.data.key.children;
            p = p ? h.clone(h.isArray(p) ? p : [p]) : [];
            a[d] = g.data.simpleData.enable ? b.transformTozTreeFormat(g, p) : p;
            b.initCache(g);
            c.unbindTree(g);
            c.bindTree(g);
            c.unbindEvent(g);
            c.bindEvent(g);
            p = {
                setting: g,
                addNodes: function(a, b, c, d) {
                    function p() {
                        f.addNodes(g, a, b, e, 1 == d)
                    }
                    a || (a = null);
                    if (a && !a.isParent && g.data.keep.leaf) return null;
                    var k = parseInt(b, 10);
                    isNaN(k) ? (d = !! c, c = b, b = -1) : b = k;
                    if (!c) return null;
                    var e = h.clone(h.isArray(c) ? c : [c]);
                    h.canAsync(g, a) ? f.asyncNode(g, a, d, p) : p();
                    return e
                },
                cancelSelectedNode: function(a) {
                    f.cancelPreSelectedNode(g, a)
                },
                destroy: function() {
                    f.destroy(g)
                },
                expandAll: function(a) {
                    a = !! a;
                    f.expandCollapseSonNode(g, null, a, !0);
                    return a
                },
                expandNode: function(a, c, d, p, e) {
                    function m() {
                        var b = k(a, g).get(0);
                        if (b && !1 !== p) if (b.scrollIntoView) b.scrollIntoView(!1);
                        else try {
                                b.focus().blur()
                            } catch (c) {}
                    }
                    if (!a || !a.isParent) return null;
                    !0 !== c && !1 !== c && (c = !a.open);
                    if ((e = !! e) && c && 0 == h.apply(g.callback.beforeExpand, [g.treeId, a], !0) || e && !c && 0 == h.apply(g.callback.beforeCollapse, [g.treeId, a], !0)) return null;
                    c && a.parentTId && f.expandCollapseParentNode(g, a.getParentNode(), c, !1);
                    if (c === a.open && !d) return null;
                    b.getRoot(g).expandTriggerFlag = e;
                    !h.canAsync(g, a) && d ? f.expandCollapseSonNode(g, a, c, !0, m) : (a.open = !c, f.switchNode(this.setting, a), m());
                    return c
                },
                getNodes: function() {
                    return b.getNodes(g)
                },
                getNodeByParam: function(a, c, d) {
                    return a ? b.getNodeByParam(g, d ? d[g.data.key.children] : b.getNodes(g), a, c) : null
                },
                getNodeByTId: function(a) {
                    return b.getNodeCache(g, a)
                },
                getNodesByParam: function(a, c, d) {
                    return a ? b.getNodesByParam(g, d ? d[g.data.key.children] : b.getNodes(g), a, c) : null
                },
                getNodesByParamFuzzy: function(a, c, d) {
                    return a ? b.getNodesByParamFuzzy(g, d ? d[g.data.key.children] : b.getNodes(g), a, c) : null
                },
                getNodesByFilter: function(a, c, d, f) {
                    c = !! c;
                    return a && "function" == typeof a ? b.getNodesByFilter(g, d ? d[g.data.key.children] : b.getNodes(g), a, c, f) : c ? null : []
                },
                getNodeIndex: function(a) {
                    if (!a) return null;
                    for (var c = g.data.key.children, d = a.parentTId ? a.getParentNode() : b.getRoot(g), f = 0, p = d[c].length; f < p; f++) if (d[c][f] == a) return f;
                    return -1
                },
                getSelectedNodes: function() {
                    for (var a = [], c = b.getRoot(g).curSelectedList, d = 0, f = c.length; d < f; d++) a.push(c[d]);
                    return a
                },
                isSelectedNode: function(a) {
                    return b.isSelectedNode(g, a)
                },
                reAsyncChildNodes: function(a, c, d) {
                    if (this.setting.async.enable) {
                        var p = !a;
                        p && (a = b.getRoot(g));
                        if ("refresh" == c) {
                            c = this.setting.data.key.children;
                            for (var h = 0, e = a[c] ? a[c].length : 0; h < e; h++) b.removeNodeCache(g, a[c][h]);
                            b.removeSelectedNode(g);
                            a[c] = [];
                            p ? this.setting.treeObj.empty() : k(a, n.id.UL, g).empty()
                        }
                        f.asyncNode(this.setting, p ? null : a, !! d)
                    }
                },
                refresh: function() {
                    this.setting.treeObj.empty();
                    var a = b.getRoot(g),
                        c = a[g.data.key.children];
                    b.initRoot(g);
                    a[g.data.key.children] = c;
                    b.initCache(g);
                    f.createNodes(g, 0, a[g.data.key.children], null, -1)
                },
                removeChildNodes: function(a) {
                    if (!a) return null;
                    var b = a[g.data.key.children];
                    f.removeChildNodes(g, a);
                    return b ? b : null
                },
                removeNode: function(a, b) {
                    a && (b = !! b, b && 0 == h.apply(g.callback.beforeRemove, [g.treeId, a], !0) || (f.removeNode(g, a), b && this.setting.treeObj.trigger(n.event.REMOVE, [g.treeId, a])))
                },
                selectNode: function(a, b) {
                    function c() {
                        var b = k(a, g).get(0);
                        if (b) if (b.scrollIntoView) b.scrollIntoView(!1);
                        else try {
                                b.focus().blur()
                            } catch (d) {}
                    }
                    if (a && h.uCanDo(g)) {
                        b = g.view.selectedMulti && b;
                        if (a.parentTId) f.expandCollapseParentNode(g, a.getParentNode(), !0, !1, c);
                        else try {
                            k(a, g).focus().blur()
                        } catch (d) {}
                        f.selectNode(g, a, b)
                    }
                },
                transformTozTreeNodes: function(a) {
                    return b.transformTozTreeFormat(g, a)
                },
                transformToArray: function(a) {
                    return b.transformToArrayFormat(g, a)
                },
                updateNode: function(a, b) {
                    a && k(a, g).get(0) && h.uCanDo(g) && (f.setNodeName(g, a), f.setNodeTarget(g, a), f.setNodeUrl(g, a), f.setNodeLineIcos(g, a), f.setNodeFontCss(g, a))
                }
            };
            a.treeTools = p;
            b.setZTreeTools(g, p);
            a[d] && 0 < a[d].length ? f.createNodes(g, 0, a[d], null, -1) : g.async.enable && g.async.url && "" !== g.async.url && f.asyncNode(g);
            return p
        }
    };
    var y = z.fn.zTree,
        k = h.$,
        n = y.consts
})(jQuery);
(function(z) {
    var H = {
            event: {
                CHECK: "ztree_check"
            },
            id: {
                CHECK: "_check"
            },
            checkbox: {
                STYLE: "checkbox",
                DEFAULT: "chk",
                DISABLED: "disable",
                FALSE: "false",
                TRUE: "true",
                FULL: "full",
                PART: "part",
                FOCUS: "focus"
            },
            radio: {
                STYLE: "radio",
                TYPE_ALL: "all",
                TYPE_LEVEL: "level"
            }
        },
        I = {
            check: {
                enable: !1,
                autoCheckTrigger: !1,
                chkStyle: H.checkbox.STYLE,
                nocheckInherit: !1,
                chkDisabledInherit: !1,
                radioType: H.radio.TYPE_LEVEL,
                chkboxType: {
                    Y: "ps",
                    N: "ps"
                }
            },
            data: {
                key: {
                    checked: "checked"
                }
            },
            callback: {
                beforeCheck: null,
                onCheck: null
            }
        },
        C = function(e, a) {
            if (!0 === a.chkDisabled) return !1;
            var b = v.getSetting(e.data.treeId),
                c = b.data.key.checked;
            if (0 == r.apply(b.callback.beforeCheck, [b.treeId, a], !0)) return !0;
            a[c] = !a[c];
            u.checkNodeRelation(b, a);
            c = B(a, t.id.CHECK, b);
            u.setChkClass(b, c, a);
            u.repairParentChkClassWithSelf(b, a);
            b.treeObj.trigger(t.event.CHECK, [e, b.treeId, a]);
            return !0
        },
        w = function(e, a) {
            if (!0 === a.chkDisabled) return !1;
            var b = v.getSetting(e.data.treeId),
                c = B(a, t.id.CHECK, b);
            a.check_Focus = !0;
            u.setChkClass(b, c, a);
            return !0
        },
        l = function(e, a) {
            if (!0 === a.chkDisabled) return !1;
            var b = v.getSetting(e.data.treeId),
                c = B(a, t.id.CHECK, b);
            a.check_Focus = !1;
            u.setChkClass(b, c, a);
            return !0
        };
    z.extend(!0, z.fn.zTree.consts, H);
    z.extend(!0, z.fn.zTree._z, {
        tools: {},
        view: {
            checkNodeRelation: function(e, a) {
                var b, c, p, h = e.data.key.children,
                    f = e.data.key.checked;
                b = t.radio;
                if (e.check.chkStyle == b.STYLE) {
                    var l = v.getRadioCheckedList(e);
                    if (a[f]) if (e.check.radioType == b.TYPE_ALL) {
                        for (c = l.length - 1; 0 <= c; c--) b = l[c], b[f] && b != a && (b[f] = !1, l.splice(c, 1), u.setChkClass(e, B(b, t.id.CHECK, e), b), b.parentTId != a.parentTId && u.repairParentChkClassWithSelf(e, b));
                        l.push(a)
                    } else for (l = a.parentTId ? a.getParentNode() : v.getRoot(e), c = 0, p = l[h].length; c < p; c++) b = l[h][c], b[f] && b != a && (b[f] = !1, u.setChkClass(e, B(b, t.id.CHECK, e), b));
                    else if (e.check.radioType == b.TYPE_ALL) for (c = 0, p = l.length; c < p; c++) if (a == l[c]) {
                        l.splice(c, 1);
                        break
                    }
                } else a[f] && (!a[h] || 0 == a[h].length || -1 < e.check.chkboxType.Y.indexOf("s")) && u.setSonNodeCheckBox(e, a, !0), a[f] || a[h] && 0 != a[h].length && !(-1 < e.check.chkboxType.N.indexOf("s")) || u.setSonNodeCheckBox(e, a, !1), a[f] && -1 < e.check.chkboxType.Y.indexOf("p") && u.setParentNodeCheckBox(e, a, !0), !a[f] && -1 < e.check.chkboxType.N.indexOf("p") && u.setParentNodeCheckBox(e, a, !1)
            },
            makeChkClass: function(e, a) {
                var b = e.data.key.checked,
                    c = t.checkbox,
                    p = t.radio,
                    h = "",
                    h = !0 === a.chkDisabled ? c.DISABLED : a.halfCheck ? c.PART : e.check.chkStyle == p.STYLE ? 1 > a.check_Child_State ? c.FULL : c.PART : a[b] ? 2 === a.check_Child_State || -1 === a.check_Child_State ? c.FULL : c.PART : 1 > a.check_Child_State ? c.FULL : c.PART,
                    b = e.check.chkStyle + "_" + (a[b] ? c.TRUE : c.FALSE) + "_" + h,
                    b = a.check_Focus && !0 !== a.chkDisabled ? b + "_" + c.FOCUS : b;
                return t.className.BUTTON + " " + c.DEFAULT + " " + b
            },
            repairAllChk: function(e, a) {
                if (e.check.enable && e.check.chkStyle === t.checkbox.STYLE) for (var b = e.data.key.checked, c = e.data.key.children, p = v.getRoot(e), h = 0, f = p[c].length; h < f; h++) {
                    var l = p[c][h];
                    !0 !== l.nocheck && !0 !== l.chkDisabled && (l[b] = a);
                    u.setSonNodeCheckBox(e, l, a)
                }
            },
            repairChkClass: function(e, a) {
                if (a && (v.makeChkFlag(e, a), !0 !== a.nocheck)) {
                    var b = B(a, t.id.CHECK, e);
                    u.setChkClass(e, b, a)
                }
            },
            repairParentChkClass: function(e, a) {
                if (a && a.parentTId) {
                    var b = a.getParentNode();
                    u.repairChkClass(e, b);
                    u.repairParentChkClass(e, b)
                }
            },
            repairParentChkClassWithSelf: function(e, a) {
                if (a) {
                    var b = e.data.key.children;
                    a[b] && 0 < a[b].length ? u.repairParentChkClass(e, a[b][0]) : u.repairParentChkClass(e, a)
                }
            },
            repairSonChkDisabled: function(e, a, b, c) {
                if (a) {
                    var p = e.data.key.children;
                    a.chkDisabled != b && (a.chkDisabled = b);
                    u.repairChkClass(e, a);
                    if (a[p] && c) for (var h = 0, f = a[p].length; h < f; h++) u.repairSonChkDisabled(e, a[p][h], b, c)
                }
            },
            repairParentChkDisabled: function(e, a, b, c) {
                a && (a.chkDisabled != b && c && (a.chkDisabled = b), u.repairChkClass(e, a), u.repairParentChkDisabled(e, a.getParentNode(), b, c))
            },
            setChkClass: function(e, a, b) {
                a && (!0 === b.nocheck ? a.hide() : a.show(), a.attr("class", u.makeChkClass(e, b)))
            },
            setParentNodeCheckBox: function(e, a, b, c) {
                var p = e.data.key.children,
                    h = e.data.key.checked,
                    f = B(a, t.id.CHECK, e);
                c || (c = a);
                v.makeChkFlag(e, a);
                !0 !== a.nocheck && !0 !== a.chkDisabled && (a[h] = b, u.setChkClass(e, f, a), e.check.autoCheckTrigger && a != c && e.treeObj.trigger(t.event.CHECK, [null, e.treeId, a]));
                if (a.parentTId) {
                    f = !0;
                    if (!b) for (var p = a.getParentNode()[p], l = 0, k = p.length; l < k; l++) if (!0 !== p[l].nocheck && !0 !== p[l].chkDisabled && p[l][h] || (!0 === p[l].nocheck || !0 === p[l].chkDisabled) && 0 < p[l].check_Child_State) {
                        f = !1;
                        break
                    }
                    f && u.setParentNodeCheckBox(e, a.getParentNode(), b, c)
                }
            },
            setSonNodeCheckBox: function(e, a, b, c) {
                if (a) {
                    var p = e.data.key.children,
                        h = e.data.key.checked,
                        f = B(a, t.id.CHECK, e);
                    c || (c = a);
                    var l = !1;
                    if (a[p]) for (var k = 0, n = a[p].length; k < n; k++) {
                        var m = a[p][k];
                        u.setSonNodeCheckBox(e, m, b, c);
                        !0 === m.chkDisabled && (l = !0)
                    }
                    a != v.getRoot(e) && !0 !== a.chkDisabled && (l && !0 !== a.nocheck && v.makeChkFlag(e, a), !0 !== a.nocheck && !0 !== a.chkDisabled ? (a[h] = b, l || (a.check_Child_State = a[p] && 0 < a[p].length ? b ? 2 : 0 : -1)) : a.check_Child_State = -1, u.setChkClass(e, f, a), e.check.autoCheckTrigger && a != c && !0 !== a.nocheck && !0 !== a.chkDisabled && e.treeObj.trigger(t.event.CHECK, [null, e.treeId, a]))
                }
            }
        },
        event: {},
        data: {
            getRadioCheckedList: function(e) {
                for (var a = v.getRoot(e).radioCheckedList, b = 0, c = a.length; b < c; b++) v.getNodeCache(e, a[b].tId) || (a.splice(b, 1), b--, c--);
                return a
            },
            getCheckStatus: function(e, a) {
                if (!e.check.enable || a.nocheck || a.chkDisabled) return null;
                var b = e.data.key.checked;
                return {
                    checked: a[b],
                    half: a.halfCheck ? a.halfCheck : e.check.chkStyle == t.radio.STYLE ? 2 === a.check_Child_State : a[b] ? -1 < a.check_Child_State && 2 > a.check_Child_State : 0 < a.check_Child_State
                }
            },
            getTreeCheckedNodes: function(e, a, b, c) {
                if (!a) return [];
                var p = e.data.key.children,
                    h = e.data.key.checked,
                    f = b && e.check.chkStyle == t.radio.STYLE && e.check.radioType == t.radio.TYPE_ALL;
                c = c ? c : [];
                for (var l = 0, k = a.length; l < k && (!0 === a[l].nocheck || !0 === a[l].chkDisabled || a[l][h] != b || (c.push(a[l]), !f)) && !(v.getTreeCheckedNodes(e, a[l][p], b, c), f && 0 < c.length); l++);
                return c
            },
            getTreeChangeCheckedNodes: function(e, a, b) {
                if (!a) return [];
                var c = e.data.key.children,
                    p = e.data.key.checked;
                b = b ? b : [];
                for (var h = 0, f = a.length; h < f; h++)!0 !== a[h].nocheck && !0 !== a[h].chkDisabled && a[h][p] != a[h].checkedOld && b.push(a[h]), v.getTreeChangeCheckedNodes(e, a[h][c], b);
                return b
            },
            makeChkFlag: function(e, a) {
                if (a) {
                    var b = e.data.key.children,
                        c = e.data.key.checked,
                        p = -1;
                    if (a[b]) for (var h = 0, f = a[b].length; h < f; h++) {
                        var l = a[b][h],
                            k = -1;
                        if (e.check.chkStyle == t.radio.STYLE) if (k = !0 === l.nocheck || !0 === l.chkDisabled ? l.check_Child_State : !0 === l.halfCheck ? 2 : l[c] ? 2 : 0 < l.check_Child_State ? 2 : 0, 2 == k) {
                            p = 2;
                            break
                        } else 0 == k && (p = 0);
                        else if (e.check.chkStyle == t.checkbox.STYLE) if (k = !0 === l.nocheck || !0 === l.chkDisabled ? l.check_Child_State : !0 === l.halfCheck ? 1 : l[c] ? -1 === l.check_Child_State || 2 === l.check_Child_State ? 2 : 1 : 0 < l.check_Child_State ? 1 : 0, 1 === k) {
                            p = 1;
                            break
                        } else if (2 === k && -1 < p && 0 < h && k !== p) {
                            p = 1;
                            break
                        } else if (2 === p && -1 < k && 2 > k) {
                            p = 1;
                            break
                        } else - 1 < k && (p = k)
                    }
                    a.check_Child_State = p
                }
            }
        }
    });
    z = z.fn.zTree;
    var r = z._z.tools,
        t = z.consts,
        u = z._z.view,
        v = z._z.data,
        B = r.$;
    v.exSetting(I);
    v.addInitBind(function(e) {
        e.treeObj.bind(t.event.CHECK, function(a, b, c, p) {
            a.srcEvent = b;
            r.apply(e.callback.onCheck, [a, c, p])
        })
    });
    v.addInitUnBind(function(e) {
        e.treeObj.unbind(t.event.CHECK)
    });
    v.addInitCache(function(e) {});
    v.addInitNode(function(e, a, b, c, p, h, f) {
        b && (a = e.data.key.checked, "string" == typeof b[a] && (b[a] = r.eqs(b[a], "true")), b[a] = !! b[a], b.checkedOld = b[a], "string" == typeof b.nocheck && (b.nocheck = r.eqs(b.nocheck, "true")), b.nocheck = !! b.nocheck || e.check.nocheckInherit && c && !! c.nocheck, "string" == typeof b.chkDisabled && (b.chkDisabled = r.eqs(b.chkDisabled, "true")), b.chkDisabled = !! b.chkDisabled || e.check.chkDisabledInherit && c && !! c.chkDisabled, "string" == typeof b.halfCheck && (b.halfCheck = r.eqs(b.halfCheck, "true")), b.halfCheck = !! b.halfCheck, b.check_Child_State = -1, b.check_Focus = !1, b.getCheckStatus = function() {
            return v.getCheckStatus(e, b)
        }, e.check.chkStyle == t.radio.STYLE && e.check.radioType == t.radio.TYPE_ALL && b[a] && v.getRoot(e).radioCheckedList.push(b))
    });
    v.addInitProxy(function(e) {
        var a = e.target,
            b = v.getSetting(e.data.treeId),
            c = "",
            p = null,
            h = "",
            f = null;
        r.eqs(e.type, "mouseover") ? b.check.enable && r.eqs(a.tagName, "span") && null !== a.getAttribute("treeNode" + t.id.CHECK) && (c = r.getNodeMainDom(a).id, h = "mouseoverCheck") : r.eqs(e.type, "mouseout") ? b.check.enable && r.eqs(a.tagName, "span") && null !== a.getAttribute("treeNode" + t.id.CHECK) && (c = r.getNodeMainDom(a).id, h = "mouseoutCheck") : r.eqs(e.type, "click") && b.check.enable && r.eqs(a.tagName, "span") && null !== a.getAttribute("treeNode" + t.id.CHECK) && (c = r.getNodeMainDom(a).id, h = "checkNode");
        if (0 < c.length) switch (p = v.getNodeCache(b, c), h) {
            case "checkNode":
                f = C;
                break;
            case "mouseoverCheck":
                f = w;
                break;
            case "mouseoutCheck":
                f = l
        }
        return {
            stop: "checkNode" === h,
            node: p,
            nodeEventType: h,
            nodeEventCallback: f,
            treeEventType: "",
            treeEventCallback: null
        }
    }, !0);
    v.addInitRoot(function(e) {
        v.getRoot(e).radioCheckedList = []
    });
    v.addBeforeA(function(e, a, b) {
        e.check.enable && (v.makeChkFlag(e, a), b.push("\x3cspan ID\x3d'", a.tId, t.id.CHECK, "' class\x3d'", u.makeChkClass(e, a), "' treeNode", t.id.CHECK, !0 === a.nocheck ? " style\x3d'display:none;'" : "", "\x3e\x3c/span\x3e"))
    });
    v.addZTreeTools(function(e, a) {
        a.checkNode = function(a, b, h, f) {
            var e = this.setting.data.key.checked;
            !0 !== a.chkDisabled && (!0 !== b && !1 !== b && (b = !a[e]), f = !! f, a[e] === b && !h || f && 0 == r.apply(this.setting.callback.beforeCheck, [this.setting.treeId, a], !0) || !r.uCanDo(this.setting) || !this.setting.check.enable || !0 === a.nocheck || (a[e] = b, b = B(a, t.id.CHECK, this.setting), (h || this.setting.check.chkStyle === t.radio.STYLE) && u.checkNodeRelation(this.setting, a), u.setChkClass(this.setting, b, a), u.repairParentChkClassWithSelf(this.setting, a), f && this.setting.treeObj.trigger(t.event.CHECK, [null, this.setting.treeId, a])))
        };
        a.checkAllNodes = function(a) {
            u.repairAllChk(this.setting, !! a)
        };
        a.getCheckedNodes = function(a) {
            var b = this.setting.data.key.children;
            a = !1 !== a;
            return v.getTreeCheckedNodes(this.setting, v.getRoot(this.setting)[b], a)
        };
        a.getChangeCheckedNodes = function() {
            var a = this.setting.data.key.children;
            return v.getTreeChangeCheckedNodes(this.setting, v.getRoot(this.setting)[a])
        };
        a.setChkDisabled = function(a, b, h, f) {
            b = !! b;
            h = !! h;
            u.repairSonChkDisabled(this.setting, a, b, !! f);
            u.repairParentChkDisabled(this.setting, a.getParentNode(), b, h)
        };
        var b = a.updateNode;
        a.updateNode = function(c, p) {
            b && b.apply(a, arguments);
            if (c && this.setting.check.enable && B(c, this.setting).get(0) && r.uCanDo(this.setting)) {
                var h = B(c, t.id.CHECK, this.setting);
                1 != p && this.setting.check.chkStyle !== t.radio.STYLE || u.checkNodeRelation(this.setting, c);
                u.setChkClass(this.setting, h, c);
                u.repairParentChkClassWithSelf(this.setting, c)
            }
        }
    });
    var M = u.createNodes;
    u.createNodes = function(e, a, b, c, p) {
        M && M.apply(u, arguments);
        b && u.repairParentChkClassWithSelf(e, c)
    };
    var K = u.removeNode;
    u.removeNode = function(e, a) {
        var b = a.getParentNode();
        K && K.apply(u, arguments);
        a && b && (u.repairChkClass(e, b), u.repairParentChkClass(e, b))
    };
    var L = u.appendNodes;
    u.appendNodes = function(e, a, b, c, p, h, f) {
        var l = "";
        L && (l = L.apply(u, arguments));
        c && v.makeChkFlag(e, c);
        return l
    }
})(jQuery);
(function(z) {
    var H = {
            event: {
                DRAG: "ztree_drag",
                DROP: "ztree_drop",
                RENAME: "ztree_rename",
                DRAGMOVE: "ztree_dragmove"
            },
            id: {
                EDIT: "_edit",
                INPUT: "_input",
                REMOVE: "_remove"
            },
            move: {
                TYPE_INNER: "inner",
                TYPE_PREV: "prev",
                TYPE_NEXT: "next"
            },
            node: {
                CURSELECTED_EDIT: "curSelectedNode_Edit",
                TMPTARGET_TREE: "tmpTargetzTree",
                TMPTARGET_NODE: "tmpTargetNode"
            }
        },
        I = {
            onHoverOverNode: function(a, b) {
                var c = t.getSetting(a.data.treeId),
                    p = t.getRoot(c);
                if (p.curHoverNode != b) I.onHoverOutNode(a);
                p.curHoverNode = b;
                r.addHoverDom(c, b)
            },
            onHoverOutNode: function(a, b) {
                var c = t.getSetting(a.data.treeId),
                    p = t.getRoot(c);
                p.curHoverNode && !t.isSelectedNode(c, p.curHoverNode) && (r.removeTreeDom(c, p.curHoverNode), p.curHoverNode = null)
            },
            onMousedownNode: function(a, b) {
                function c(a) {
                    if (0 == n.dragFlag && Math.abs(V - a.clientX) < k.edit.drag.minMoveSize && Math.abs(W - a.clientY) < k.edit.drag.minMoveSize) return !0;
                    var b, c, d, f, h;
                    h = k.data.key.children;
                    B.css("cursor", "pointer");
                    if (0 == n.dragFlag) {
                        if (0 == w.apply(k.callback.beforeDrag, [k.treeId, g], !0)) return p(a), !0;
                        b = 0;
                        for (c = g.length; b < c; b++) 0 == b && (n.dragNodeShowBefore = []), d = g[b], d.isParent && d.open ? (r.expandCollapseNode(k, d, !d.open), n.dragNodeShowBefore[d.tId] = !0) : n.dragNodeShowBefore[d.tId] = !1;
                        n.dragFlag = 1;
                        m.showHoverDom = !1;
                        w.showIfameMask(k, !0);
                        d = !0;
                        f = -1;
                        if (1 < g.length) {
                            var e = g[0].parentTId ? g[0].getParentNode()[h] : t.getNodes(k);
                            h = [];
                            b = 0;
                            for (c = e.length; b < c; b++) if (void 0 !== n.dragNodeShowBefore[e[b].tId] && (d && -1 < f && f + 1 !== b && (d = !1), h.push(e[b]), f = b), g.length === h.length) {
                                g = h;
                                break
                            }
                        }
                        d && (K = g[0].getPreNode(), L = g[g.length - 1].getNextNode());
                        P = u("\x3cul class\x3d'zTreeDragUL'\x3e\x3c/ul\x3e", k);
                        b = 0;
                        for (c = g.length; b < c; b++) d = g[b], d.editNameFlag = !1, r.selectNode(k, d, 0 < b), r.removeTreeDom(k, d), b > k.edit.drag.maxShowNodeNum - 1 || (f = u("\x3cli id\x3d'" + d.tId + "_tmp'\x3e\x3c/li\x3e", k), f.append(u(d, l.id.A, k).clone()), f.css("padding", "0"), f.children("#" + d.tId + l.id.A).removeClass(l.node.CURSELECTED), P.append(f), b == k.edit.drag.maxShowNodeNum - 1 && (f = u("\x3cli id\x3d'" + d.tId + "_moretmp'\x3e\x3ca\x3e  ...  \x3c/a\x3e\x3c/li\x3e", k), P.append(f)));
                        P.attr("id", g[0].tId + l.id.UL + "_tmp");
                        P.addClass(k.treeObj.attr("class"));
                        P.appendTo(B);
                        J = u("\x3cspan class\x3d'tmpzTreeMove_arrow'\x3e\x3c/span\x3e", k);
                        J.attr("id", "zTreeMove_arrow_tmp");
                        J.appendTo(B);
                        k.treeObj.trigger(l.event.DRAG, [a, k.treeId, g])
                    }
                    if (1 == n.dragFlag) {
                        A && J.attr("id") == a.target.id && D && a.clientX + q.scrollLeft() + 2 > z("#" + D + l.id.A, A).offset().left ? (d = z("#" + D + l.id.A, A), a.target = 0 < d.length ? d.get(0) : a.target) : A && (A.removeClass(l.node.TMPTARGET_TREE), D && z("#" + D + l.id.A, A).removeClass(l.node.TMPTARGET_NODE + "_" + l.move.TYPE_PREV).removeClass(l.node.TMPTARGET_NODE + "_" + H.move.TYPE_NEXT).removeClass(l.node.TMPTARGET_NODE + "_" + H.move.TYPE_INNER));
                        D = A = null;
                        I = !1;
                        x = k;
                        d = t.getSettings();
                        for (var y in d) d[y].treeId && d[y].edit.enable && d[y].treeId != k.treeId && (a.target.id == d[y].treeId || 0 < z(a.target).parents("#" + d[y].treeId).length) && (I = !0, x = d[y]);
                        y = q.scrollTop();
                        f = q.scrollLeft();
                        h = x.treeObj.offset();
                        b = x.treeObj.get(0).scrollHeight;
                        d = x.treeObj.get(0).scrollWidth;
                        c = a.clientY + y - h.top;
                        var v = x.treeObj.height() + h.top - a.clientY - y,
                            F = a.clientX + f - h.left,
                            C = x.treeObj.width() + h.left - a.clientX - f;
                        h = c < k.edit.drag.borderMax && c > k.edit.drag.borderMin;
                        var e = v < k.edit.drag.borderMax && v > k.edit.drag.borderMin,
                            O = F < k.edit.drag.borderMax && F > k.edit.drag.borderMin,
                            Q = C < k.edit.drag.borderMax && C > k.edit.drag.borderMin,
                            v = c > k.edit.drag.borderMin && v > k.edit.drag.borderMin && F > k.edit.drag.borderMin && C > k.edit.drag.borderMin,
                            F = h && 0 >= x.treeObj.scrollTop(),
                            C = e && x.treeObj.scrollTop() + x.treeObj.height() + 10 >= b,
                            R = O && 0 >= x.treeObj.scrollLeft(),
                            X = Q && x.treeObj.scrollLeft() + x.treeObj.width() + 10 >= d;
                        if (a.target && w.isChildOrSelf(a.target, x.treeId)) {
                            for (var N = a.target; N && N.tagName && !w.eqs(N.tagName, "li") && N.id != x.treeId;) N = N.parentNode;
                            var S = !0;
                            b = 0;
                            for (c = g.length; b < c; b++) if (d = g[b], N.id === d.tId) {
                                S = !1;
                                break
                            } else if (0 < u(d, k).find("#" + N.id).length) {
                                S = !1;
                                break
                            }
                            S && a.target && w.isChildOrSelf(a.target, N.id + l.id.A) && (A = z(N), D = N.id)
                        }
                        d = g[0];
                        v && w.isChildOrSelf(a.target, x.treeId) && (!A && (a.target.id == x.treeId || F || C || R || X) && (I || !I && d.parentTId) && (A = x.treeObj), h ? x.treeObj.scrollTop(x.treeObj.scrollTop() - 10) : e && x.treeObj.scrollTop(x.treeObj.scrollTop() + 10), O ? x.treeObj.scrollLeft(x.treeObj.scrollLeft() - 10) : Q && x.treeObj.scrollLeft(x.treeObj.scrollLeft() + 10), A && A != x.treeObj && A.offset().left < x.treeObj.offset().left && x.treeObj.scrollLeft(x.treeObj.scrollLeft() + A.offset().left - x.treeObj.offset().left));
                        P.css({
                            top: a.clientY + y + 3 + "px",
                            left: a.clientX + f + 3 + "px"
                        });
                        c = b = 0;
                        if (A && A.attr("id") != x.treeId) {
                            f = function() {
                                A = null;
                                D = "";
                                E = l.move.TYPE_INNER;
                                J.css({
                                    display: "none"
                                });
                                window.zTreeMoveTimer && (clearTimeout(window.zTreeMoveTimer), window.zTreeMoveTargetNodeTId = null)
                            };
                            var G = null == D ? null : t.getNodeCache(x, D),
                                e = (a.ctrlKey || a.metaKey) && k.edit.drag.isMove && k.edit.drag.isCopy || !k.edit.drag.isMove && k.edit.drag.isCopy;
                            h = !(!K || D !== K.tId);
                            Q = !(!L || D !== L.tId);
                            O = d.parentTId && d.parentTId == D;
                            d = (e || !Q) && w.apply(x.edit.drag.prev, [x.treeId, g, G], !! x.edit.drag.prev);
                            h = (e || !h) && w.apply(x.edit.drag.next, [x.treeId, g, G], !! x.edit.drag.next);
                            e = (e || !O) && !(x.data.keep.leaf && !G.isParent) && w.apply(x.edit.drag.inner, [x.treeId, g, G], !! x.edit.drag.inner);
                            if (d || h || e) {
                                if (O = z("#" + D + l.id.A, A), Q = G.isLastNode ? null : z("#" + G.getNextNode().tId + l.id.A, A.next()), v = O.offset().top, F = O.offset().left, C = d ? e ? .25 : h ? .5 : 1 : -1, R = h ? e ? .75 : d ? .5 : 0 : -1, y = (a.clientY + y - v) / O.height(), (1 == C || y <= C && -.2 <= y) && d ? (b = 1 - J.width(), c = v - J.height() / 2, E = l.move.TYPE_PREV) : (0 == R || y >= R && 1.2 >= y) && h ? (b = 1 - J.width(), c = null == Q || G.isParent && G.open ? v + O.height() - J.height() / 2 : Q.offset().top - J.height() / 2, E = l.move.TYPE_NEXT) : e ? (b = 5 - J.width(), c = v, E = l.move.TYPE_INNER) : f(), A) {
                                    J.css({
                                        display: "block",
                                        top: c + "px",
                                        left: F + b + "px"
                                    });
                                    O.addClass(l.node.TMPTARGET_NODE + "_" + E);
                                    if (M != D || T != E) U = (new Date).getTime();
                                    G && G.isParent && E == l.move.TYPE_INNER && (y = !0, window.zTreeMoveTimer && window.zTreeMoveTargetNodeTId !== G.tId ? (clearTimeout(window.zTreeMoveTimer), window.zTreeMoveTargetNodeTId = null) : window.zTreeMoveTimer && window.zTreeMoveTargetNodeTId === G.tId && (y = !1), y && (window.zTreeMoveTimer = setTimeout(function() {
                                        E == l.move.TYPE_INNER && G && G.isParent && !G.open && (new Date).getTime() - U > x.edit.drag.autoOpenTime && w.apply(x.callback.beforeDragOpen, [x.treeId, G], !0) && (r.switchNode(x, G), x.edit.drag.autoExpandTrigger && x.treeObj.trigger(l.event.EXPAND, [x.treeId, G]))
                                    }, x.edit.drag.autoOpenTime + 50), window.zTreeMoveTargetNodeTId = G.tId))
                                }
                            } else f()
                        } else E = l.move.TYPE_INNER, A && w.apply(x.edit.drag.inner, [x.treeId, g, null], !! x.edit.drag.inner) ? A.addClass(l.node.TMPTARGET_TREE) : A = null, J.css({
                            display: "none"
                        }), window.zTreeMoveTimer && (clearTimeout(window.zTreeMoveTimer), window.zTreeMoveTargetNodeTId = null);
                        M = D;
                        T = E;
                        k.treeObj.trigger(l.event.DRAGMOVE, [a, k.treeId, g])
                    }
                    return !1
                }
                function p(a) {
                    window.zTreeMoveTimer && (clearTimeout(window.zTreeMoveTimer), window.zTreeMoveTargetNodeTId = null);
                    T = M = null;
                    q.unbind("mousemove", c);
                    q.unbind("mouseup", p);
                    q.unbind("selectstart", h);
                    B.css("cursor", "auto");
                    A && (A.removeClass(l.node.TMPTARGET_TREE), D && z("#" + D + l.id.A, A).removeClass(l.node.TMPTARGET_NODE + "_" + l.move.TYPE_PREV).removeClass(l.node.TMPTARGET_NODE + "_" + H.move.TYPE_NEXT).removeClass(l.node.TMPTARGET_NODE + "_" + H.move.TYPE_INNER));
                    w.showIfameMask(k, !1);
                    m.showHoverDom = !0;
                    if (0 != n.dragFlag) {
                        n.dragFlag = 0;
                        var b, d, f;
                        b = 0;
                        for (d = g.length; b < d; b++) f = g[b], f.isParent && n.dragNodeShowBefore[f.tId] && !f.open && (r.expandCollapseNode(k, f, !f.open), delete n.dragNodeShowBefore[f.tId]);
                        P && P.remove();
                        J && J.remove();
                        var e = (a.ctrlKey || a.metaKey) && k.edit.drag.isMove && k.edit.drag.isCopy || !k.edit.drag.isMove && k.edit.drag.isCopy;
                        !e && A && D && g[0].parentTId && D == g[0].parentTId && E == l.move.TYPE_INNER && (A = null);
                        if (A) {
                            b = function() {
                                if (I) {
                                    if (!e) for (var b = 0, c = g.length; b < c; b++) r.removeNode(k, g[b]);
                                    E == l.move.TYPE_INNER ? r.addNodes(x, y, -1, v) : r.addNodes(x, y.getParentNode(), E == l.move.TYPE_PREV ? y.getIndex() : y.getIndex() + 1, v)
                                } else if (e && E == l.move.TYPE_INNER) r.addNodes(x, y, -1, v);
                                else if (e) r.addNodes(x, y.getParentNode(), E == l.move.TYPE_PREV ? y.getIndex() : y.getIndex() + 1, v);
                                else if (E != l.move.TYPE_NEXT) for (b = 0, c = v.length; b < c; b++) r.moveNode(x, y, v[b], E, !1);
                                else for (b = -1, c = v.length - 1; b < c; c--) r.moveNode(x, y, v[c], E, !1);
                                r.selectNodes(x, v);
                                if (b = u(v[0], k).get(0)) if (b.scrollIntoView) b.scrollIntoView(!1);
                                else try {
                                        b.focus().blur()
                                    } catch (d) {}
                                k.treeObj.trigger(l.event.DROP, [a, x.treeId, v, y, E, e])
                            };
                            var y = null == D ? null : t.getNodeCache(x, D);
                            if (0 == w.apply(k.callback.beforeDrop, [x.treeId, g, y, E, e], !0)) r.selectNodes(C, g);
                            else {
                                var v = e ? w.clone(g) : g;
                                E == l.move.TYPE_INNER && w.canAsync(x, y) ? r.asyncNode(x, y, !1, b) : b()
                            }
                        } else r.selectNodes(C, g), k.treeObj.trigger(l.event.DROP, [a, k.treeId, g, null, null, null])
                    }
                }
                function h() {
                    return !1
                }
                var f, e, k = t.getSetting(a.data.treeId),
                    n = t.getRoot(k),
                    m = t.getRoots();
                if (2 == a.button || !k.edit.enable || !k.edit.drag.isCopy && !k.edit.drag.isMove) return !0;
                var d = a.target,
                    v = t.getRoot(k).curSelectedList,
                    g = [];
                if (t.isSelectedNode(k, b)) for (f = 0, e = v.length; f < e; f++) {
                    if (v[f].editNameFlag && w.eqs(d.tagName, "input") && null !== d.getAttribute("treeNode" + l.id.INPUT)) return !0;
                    g.push(v[f]);
                    if (g[0].parentTId !== v[f].parentTId) {
                        g = [b];
                        break
                    }
                } else g = [b];
                r.editNodeBlur = !0;
                r.cancelCurEditNode(k);
                var q = z(k.treeObj.get(0).ownerDocument),
                    B = z(k.treeObj.get(0).ownerDocument.body),
                    P, J, A, I = !1,
                    x = k,
                    C = k,
                    K, L, M = null,
                    T = null,
                    D = null,
                    E = l.move.TYPE_INNER,
                    V = a.clientX,
                    W = a.clientY,
                    U = (new Date).getTime();
                w.uCanDo(k) && q.bind("mousemove", c);
                q.bind("mouseup", p);
                q.bind("selectstart", h);
                a.preventDefault && a.preventDefault();
                return !0
            }
        };
    z.extend(!0, z.fn.zTree.consts, H);
    z.extend(!0, z.fn.zTree._z, {
        tools: {
            getAbs: function(a) {
                a = a.getBoundingClientRect();
                return [a.left + (document.body.scrollLeft + document.documentElement.scrollLeft), a.top + (document.body.scrollTop + document.documentElement.scrollTop)]
            },
            inputFocus: function(a) {
                a.get(0) && (a.focus(), w.setCursorPosition(a.get(0), a.val().length))
            },
            inputSelect: function(a) {
                a.get(0) && (a.focus(), a.select())
            },
            setCursorPosition: function(a, b) {
                if (a.setSelectionRange) a.focus(), a.setSelectionRange(b, b);
                else if (a.createTextRange) {
                    var c = a.createTextRange();
                    c.collapse(!0);
                    c.moveEnd("character", b);
                    c.moveStart("character", b);
                    c.select()
                }
            },
            showIfameMask: function(a, b) {
                for (var c = t.getRoot(a); 0 < c.dragMaskList.length;) c.dragMaskList[0].remove(), c.dragMaskList.shift();
                if (b) for (var p = u("iframe", a), h = 0, f = p.length; h < f; h++) {
                    var e = p.get(h),
                        k = w.getAbs(e),
                        e = u("\x3cdiv id\x3d'zTreeMask_" + h + "' class\x3d'zTreeMask' style\x3d'top:" + k[1] + "px; left:" + k[0] + "px; width:" + e.offsetWidth + "px; height:" + e.offsetHeight + "px;'\x3e\x3c/div\x3e", a);
                    e.appendTo(u("body", a));
                    c.dragMaskList.push(e)
                }
            }
        },
        view: {
            addEditBtn: function(a, b) {
                if (!(b.editNameFlag || 0 < u(b, l.id.EDIT, a).length) && w.apply(a.edit.showRenameBtn, [a.treeId, b], a.edit.showRenameBtn)) {
                    var c = u(b, l.id.A, a),
                        e = "\x3cspan class\x3d'" + l.className.BUTTON + " edit' id\x3d'" + b.tId + l.id.EDIT + "' title\x3d'" + w.apply(a.edit.renameTitle, [a.treeId, b], a.edit.renameTitle) + "' treeNode" + l.id.EDIT + " style\x3d'display:none;'\x3e\x3c/span\x3e";
                    c.append(e);
                    u(b, l.id.EDIT, a).bind("click", function() {
                        if (!w.uCanDo(a) || 0 == w.apply(a.callback.beforeEditName, [a.treeId, b], !0)) return !1;
                        r.editNode(a, b);
                        return !1
                    }).show()
                }
            },
            addRemoveBtn: function(a, b) {
                if (!(b.editNameFlag || 0 < u(b, l.id.REMOVE, a).length) && w.apply(a.edit.showRemoveBtn, [a.treeId, b], a.edit.showRemoveBtn)) {
                    var c = u(b, l.id.A, a),
                        e = "\x3cspan class\x3d'" + l.className.BUTTON + " remove' id\x3d'" + b.tId + l.id.REMOVE + "' title\x3d'" + w.apply(a.edit.removeTitle, [a.treeId, b], a.edit.removeTitle) + "' treeNode" + l.id.REMOVE + " style\x3d'display:none;'\x3e\x3c/span\x3e";
                    c.append(e);
                    u(b, l.id.REMOVE, a).bind("click", function() {
                        if (!w.uCanDo(a) || 0 == w.apply(a.callback.beforeRemove, [a.treeId, b], !0)) return !1;
                        r.removeNode(a, b);
                        a.treeObj.trigger(l.event.REMOVE, [a.treeId, b]);
                        return !1
                    }).bind("mousedown", function(a) {
                        return !0
                    }).show()
                }
            },
            addHoverDom: function(a, b) {
                t.getRoots().showHoverDom && (b.isHover = !0, a.edit.enable && (r.addEditBtn(a, b), r.addRemoveBtn(a, b)), w.apply(a.view.addHoverDom, [a.treeId, b]))
            },
            cancelCurEditNode: function(a, b, c) {
                var e = t.getRoot(a),
                    h = a.data.key.name,
                    f = e.curEditNode;
                if (f) {
                    var y = e.curEditInput;
                    b = b ? b : c ? f[h] : y.val();
                    if (!1 === w.apply(a.callback.beforeRename, [a.treeId, f, b, c], !0)) return !1;
                    f[h] = b;
                    u(f, l.id.A, a).removeClass(l.node.CURSELECTED_EDIT);
                    y.unbind();
                    r.setNodeName(a, f);
                    f.editNameFlag = !1;
                    e.curEditNode = null;
                    e.curEditInput = null;
                    r.selectNode(a, f, !1);
                    a.treeObj.trigger(l.event.RENAME, [a.treeId, f, c])
                }
                return e.noSelection = !0
            },
            editNode: function(a, b) {
                var c = t.getRoot(a);
                r.editNodeBlur = !1;
                if (t.isSelectedNode(a, b) && c.curEditNode == b && b.editNameFlag) setTimeout(function() {
                    w.inputFocus(c.curEditInput)
                }, 0);
                else {
                    var e = a.data.key.name;
                    b.editNameFlag = !0;
                    r.removeTreeDom(a, b);
                    r.cancelCurEditNode(a);
                    r.selectNode(a, b, !1);
                    u(b, l.id.SPAN, a).html("\x3cinput type\x3dtext class\x3d'rename' id\x3d'" + b.tId + l.id.INPUT + "' treeNode" + l.id.INPUT + " \x3e");
                    var h = u(b, l.id.INPUT, a);
                    h.attr("value", b[e]);
                    a.edit.editNameSelectAll ? w.inputSelect(h) : w.inputFocus(h);
                    h.bind("blur", function(b) {
                        r.editNodeBlur || r.cancelCurEditNode(a)
                    }).bind("keydown", function(b) {
                        "13" == b.keyCode ? (r.editNodeBlur = !0, r.cancelCurEditNode(a)) : "27" == b.keyCode && r.cancelCurEditNode(a, null, !0)
                    }).bind("click", function(a) {
                        return !1
                    }).bind("dblclick", function(a) {
                        return !1
                    });
                    u(b, l.id.A, a).addClass(l.node.CURSELECTED_EDIT);
                    c.curEditInput = h;
                    c.noSelection = !1;
                    c.curEditNode = b
                }
            },
            moveNode: function(a, b, c, e, h, f) {
                var y = t.getRoot(a),
                    k = a.data.key.children;
                if (b != c && (!a.data.keep.leaf || !b || b.isParent || e != l.move.TYPE_INNER)) {
                    var n = c.parentTId ? c.getParentNode() : y,
                        m = null === b || b == y;
                    m && null === b && (b = y);
                    m && (e = l.move.TYPE_INNER);
                    y = b.parentTId ? b.getParentNode() : y;
                    e != l.move.TYPE_PREV && e != l.move.TYPE_NEXT && (e = l.move.TYPE_INNER);
                    e == l.move.TYPE_INNER && (m ? c.parentTId = null : (b.isParent || (b.isParent = !0, b.open = !! b.open, r.setNodeLineIcos(a, b)), c.parentTId = b.tId));
                    var d;
                    m ? d = m = a.treeObj : (f || e != l.move.TYPE_INNER ? f || r.expandCollapseNode(a, b.getParentNode(), !0, !1) : r.expandCollapseNode(a, b, !0, !1), m = u(b, a), d = u(b, l.id.UL, a), m.get(0) && !d.get(0) && (d = [], r.makeUlHtml(a, b, d, ""), m.append(d.join(""))), d = u(b, l.id.UL, a));
                    var v = u(c, a);
                    v.get(0) ? m.get(0) || v.remove() : v = r.appendNodes(a, c.level, [c], null, -1, !1, !0).join("");
                    d.get(0) && e == l.move.TYPE_INNER ? d.append(v) : m.get(0) && e == l.move.TYPE_PREV ? m.before(v) : m.get(0) && e == l.move.TYPE_NEXT && m.after(v);
                    var g = -1,
                        q = 0,
                        w = null,
                        m = null,
                        z = c.level;
                    if (c.isFirstNode) g = 0, 1 < n[k].length && (w = n[k][1], w.isFirstNode = !0);
                    else if (c.isLastNode) g = n[k].length - 1, w = n[k][g - 1], w.isLastNode = !0;
                    else for (d = 0, v = n[k].length; d < v; d++) if (n[k][d].tId == c.tId) {
                            g = d;
                            break
                        }
                    0 <= g && n[k].splice(g, 1);
                    if (e != l.move.TYPE_INNER) for (d = 0, v = y[k].length; d < v; d++) y[k][d].tId == b.tId && (q = d);
                    e == l.move.TYPE_INNER ? (b[k] || (b[k] = []), 0 < b[k].length && (m = b[k][b[k].length - 1], m.isLastNode = !1), b[k].splice(b[k].length, 0, c), c.isLastNode = !0, c.isFirstNode = 1 == b[k].length) : b.isFirstNode && e == l.move.TYPE_PREV ? (y[k].splice(q, 0, c), m = b, m.isFirstNode = !1, c.parentTId = b.parentTId, c.isFirstNode = !0, c.isLastNode = !1) : b.isLastNode && e == l.move.TYPE_NEXT ? (y[k].splice(q + 1, 0, c), m = b, m.isLastNode = !1, c.parentTId = b.parentTId, c.isFirstNode = !1, c.isLastNode = !0) : (e == l.move.TYPE_PREV ? y[k].splice(q, 0, c) : y[k].splice(q + 1, 0, c), c.parentTId = b.parentTId, c.isFirstNode = !1, c.isLastNode = !1);
                    t.fixPIdKeyValue(a, c);
                    t.setSonNodeLevel(a, c.getParentNode(), c);
                    r.setNodeLineIcos(a, c);
                    r.repairNodeLevelClass(a, c, z);
                    !a.data.keep.parent && 1 > n[k].length ? (n.isParent = !1, n.open = !1, b = u(n, l.id.UL, a), e = u(n, l.id.SWITCH, a), k = u(n, l.id.ICON, a), r.replaceSwitchClass(n, e, l.folder.DOCU), r.replaceIcoClass(n, k, l.folder.DOCU), b.css("display", "none")) : w && r.setNodeLineIcos(a, w);
                    m && r.setNodeLineIcos(a, m);
                    a.check && a.check.enable && r.repairChkClass && (r.repairChkClass(a, n), r.repairParentChkClassWithSelf(a, n), n != c.parent && r.repairParentChkClassWithSelf(a, c));
                    f || r.expandCollapseParentNode(a, c.getParentNode(), !0, h)
                }
            },
            removeEditBtn: function(a, b) {
                u(b, l.id.EDIT, a).unbind().remove()
            },
            removeRemoveBtn: function(a, b) {
                u(b, l.id.REMOVE, a).unbind().remove()
            },
            removeTreeDom: function(a, b) {
                b.isHover = !1;
                r.removeEditBtn(a, b);
                r.removeRemoveBtn(a, b);
                w.apply(a.view.removeHoverDom, [a.treeId, b])
            },
            repairNodeLevelClass: function(a, b, c) {
                if (c !== b.level) {
                    var e = u(b, a),
                        h = u(b, l.id.A, a);
                    a = u(b, l.id.UL, a);
                    c = l.className.LEVEL + c;
                    b = l.className.LEVEL + b.level;
                    e.removeClass(c);
                    e.addClass(b);
                    h.removeClass(c);
                    h.addClass(b);
                    a.removeClass(c);
                    a.addClass(b)
                }
            },
            selectNodes: function(a, b) {
                for (var c = 0, e = b.length; c < e; c++) r.selectNode(a, b[c], 0 < c)
            }
        },
        event: {},
        data: {
            setSonNodeLevel: function(a, b, c) {
                if (c) {
                    var e = a.data.key.children;
                    c.level = b ? b.level + 1 : 0;
                    if (c[e]) {
                        b = 0;
                        for (var h = c[e].length; b < h; b++) c[e][b] && t.setSonNodeLevel(a, c, c[e][b])
                    }
                }
            }
        }
    });
    var C = z.fn.zTree,
        w = C._z.tools,
        l = C.consts,
        r = C._z.view,
        t = C._z.data,
        u = w.$;
    t.exSetting({
        edit: {
            enable: !1,
            editNameSelectAll: !1,
            showRemoveBtn: !0,
            showRenameBtn: !0,
            removeTitle: "remove",
            renameTitle: "rename",
            drag: {
                autoExpandTrigger: !1,
                isCopy: !0,
                isMove: !0,
                prev: !0,
                next: !0,
                inner: !0,
                minMoveSize: 5,
                borderMax: 10,
                borderMin: -5,
                maxShowNodeNum: 5,
                autoOpenTime: 500
            }
        },
        view: {
            addHoverDom: null,
            removeHoverDom: null
        },
        callback: {
            beforeDrag: null,
            beforeDragOpen: null,
            beforeDrop: null,
            beforeEditName: null,
            beforeRename: null,
            onDrag: null,
            onDragMove: null,
            onDrop: null,
            onRename: null
        }
    });
    t.addInitBind(function(a) {
        var b = a.treeObj,
            c = l.event;
        b.bind(c.RENAME, function(b, c, e, l) {
            w.apply(a.callback.onRename, [b, c, e, l])
        });
        b.bind(c.DRAG, function(b, c, e, l) {
            w.apply(a.callback.onDrag, [c, e, l])
        });
        b.bind(c.DRAGMOVE, function(b, c, e, l) {
            w.apply(a.callback.onDragMove, [c, e, l])
        });
        b.bind(c.DROP, function(b, c, e, l, k, n, m) {
            w.apply(a.callback.onDrop, [c, e, l, k, n, m])
        })
    });
    t.addInitUnBind(function(a) {
        a = a.treeObj;
        var b = l.event;
        a.unbind(b.RENAME);
        a.unbind(b.DRAG);
        a.unbind(b.DRAGMOVE);
        a.unbind(b.DROP)
    });
    t.addInitCache(function(a) {});
    t.addInitNode(function(a, b, c, e, h, f, l) {
        c && (c.isHover = !1, c.editNameFlag = !1)
    });
    t.addInitProxy(function(a) {
        var b = a.target,
            c = t.getSetting(a.data.treeId),
            e = a.relatedTarget,
            h = "",
            f = null,
            r = "",
            k = null,
            n = null;
        if (w.eqs(a.type, "mouseover")) {
            if (n = w.getMDom(c, b, [{
                    tagName: "a",
                    attrName: "treeNode" + l.id.A
                }])) h = w.getNodeMainDom(n).id, r = "hoverOverNode"
        } else w.eqs(a.type, "mouseout") ? (n = w.getMDom(c, e, [{
            tagName: "a",
            attrName: "treeNode" + l.id.A
        }]), n || (h = "remove", r = "hoverOutNode")) : w.eqs(a.type, "mousedown") && (n = w.getMDom(c, b, [{
            tagName: "a",
            attrName: "treeNode" + l.id.A
        }])) && (h = w.getNodeMainDom(n).id, r = "mousedownNode");
        if (0 < h.length) switch (f = t.getNodeCache(c, h), r) {
            case "mousedownNode":
                k = I.onMousedownNode;
                break;
            case "hoverOverNode":
                k = I.onHoverOverNode;
                break;
            case "hoverOutNode":
                k = I.onHoverOutNode
        }
        return {
            stop: !1,
            node: f,
            nodeEventType: r,
            nodeEventCallback: k,
            treeEventType: "",
            treeEventCallback: null
        }
    });
    t.addInitRoot(function(a) {
        a = t.getRoot(a);
        var b = t.getRoots();
        a.curEditNode = null;
        a.curEditInput = null;
        a.curHoverNode = null;
        a.dragFlag = 0;
        a.dragNodeShowBefore = [];
        a.dragMaskList = [];
        b.showHoverDom = !0
    });
    t.addZTreeTools(function(a, b) {
        b.cancelEditName = function(a) {
            t.getRoot(this.setting).curEditNode && r.cancelCurEditNode(this.setting, a ? a : null, !0)
        };
        b.copyNode = function(a, b, e, f) {
            if (!b || a && !a.isParent && this.setting.data.keep.leaf && e === l.move.TYPE_INNER) return null;
            var t = this,
                k = w.clone(b);
            a || (a = null, e = l.move.TYPE_INNER);
            e == l.move.TYPE_INNER ? (b = function() {
                r.addNodes(t.setting, a, -1, [k], f)
            }, w.canAsync(this.setting, a) ? r.asyncNode(this.setting, a, f, b) : b()) : (r.addNodes(this.setting, a.parentNode, -1, [k], f), r.moveNode(this.setting, a, k, e, !1, f));
            return k
        };
        b.editName = function(a) {
            a && a.tId && a === t.getNodeCache(this.setting, a.tId) && (a.parentTId && r.expandCollapseParentNode(this.setting, a.getParentNode(), !0), r.editNode(this.setting, a))
        };
        b.moveNode = function(a, b, e, f) {
            function t() {
                r.moveNode(k.setting, a, b, e, !1, f)
            }
            if (!b) return b;
            if (a && !a.isParent && this.setting.data.keep.leaf && e === l.move.TYPE_INNER || a && (b.parentTId == a.tId && e == l.move.TYPE_INNER || 0 < u(b, this.setting).find("#" + a.tId).length)) return null;
            a || (a = null);
            var k = this;
            w.canAsync(this.setting, a) && e === l.move.TYPE_INNER ? r.asyncNode(this.setting, a, f, t) : t();
            return b
        };
        b.setEditable = function(a) {
            this.setting.edit.enable = a;
            return this.refresh()
        }
    });
    var v = r.cancelPreSelectedNode;
    r.cancelPreSelectedNode = function(a, b) {
        for (var c = t.getRoot(a).curSelectedList, e = 0, h = c.length; e < h; e++) if (!b || b === c[e]) if (r.removeTreeDom(a, c[e]), b) break;
        v && v.apply(r, arguments)
    };
    var B = r.createNodes;
    r.createNodes = function(a, b, c, e, h) {
        B && B.apply(r, arguments);
        c && r.repairParentChkClassWithSelf && r.repairParentChkClassWithSelf(a, e)
    };
    var M = r.makeNodeUrl;
    r.makeNodeUrl = function(a, b) {
        return a.edit.enable ? null : M.apply(r, arguments)
    };
    var K = r.removeNode;
    r.removeNode = function(a, b) {
        var c = t.getRoot(a);
        c.curEditNode === b && (c.curEditNode = null);
        K && K.apply(r, arguments)
    };
    var L = r.selectNode;
    r.selectNode = function(a, b, c) {
        var e = t.getRoot(a);
        if (t.isSelectedNode(a, b) && e.curEditNode == b && b.editNameFlag) return !1;
        L && L.apply(r, arguments);
        r.addHoverDom(a, b);
        return !0
    };
    var e = w.uCanDo;
    w.uCanDo = function(a, b) {
        var c = t.getRoot(a);
        if (b && (w.eqs(b.type, "mouseover") || w.eqs(b.type, "mouseout") || w.eqs(b.type, "mousedown") || w.eqs(b.type, "mouseup"))) return !0;
        c.curEditNode && (r.editNodeBlur = !1, c.curEditInput.focus());
        return !c.curEditNode && (e ? e.apply(r, arguments) : !0)
    }
})(jQuery);