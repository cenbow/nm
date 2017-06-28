<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色管理 | 菜单设置</title>
    <style type="text/css">
        .otod-system-sysrole-menu-setting {}
        .otod-system-sysrole-menu-setting .menu-container {padding-top: 10px; min-height: 250px; border-top: 1px dashed #ddd;}
        .otod-system-sysrole-menu-setting .toolbars {margin-top: 10px; padding-top: 10px; border-top: 1px dashed #ddd;}
    </style>
    <script type="text/javascript">
        $(function() { app.init(); });

        app.init('otod.system.sysrole.menu.setting',{
            vars: {
                roleItemVar: false,
                menuTreeVar: false
            },
            init: function () {
                var that = this;
                this.global = function () { return that; };

                if (typeof that.modal != 'undefined') that.vars.roleItemVar = that.modal.param.item;

                this.layout();
                this.tree.init(that);
            },
            layout: function () {
                var that = this.global();
            },
            tree: {
                init: function (that) {
                    this.global = function () {
                        return that;
                    };

                    this.layout();
                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    $('input[name=sysId]', that.selector).selectGroup({
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-3',
                        cssInputGroup: 'col-xs-8',
                        itemLabel: 'sysName',
                        itemValue: 'id',
                        remote: {
                            url: 'role/menu/sub/list.json',
                            type: 'POST',
                            callback: function (data) {
                                if (!data.success) {
                                    appui.message.error('加载子系统数据异常.');
                                    return [];
                                }
                                return data.list;
                            }
                        },
                        events: {
                            change: {
                                data: that,
                                handler: function (event, item) {
                                    event.preventDefault();
                                    event.stopPropagation();
                                    that.vars.menuTreeVar.options.remote.otherParam['params[sysId]'] = item['id'];
                                    that.vars.menuTreeVar.refresh();
                                    return false;
                                }
                            }
                        }
                    });

                    $("#saveBtn", that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();

                        var that = event.data.refresh();
                        that.tree.handlers.save();

                        return false;
                    });

                    $("#cancelBtn", that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();

                        appui.modal.close({closed: false});

                        return false;
                    });


                    //树配置
                    var config = {
                        icons: {},
                        css: {},
                        data: {
                            id: 'id',
                            pid: 'parMenuId',
                            name: 'menuName',
                            children: 'list'
                        },
                        show: {
                            multi: true,
                            checkbox: true,
                            autoLoad: false,//自动加载
                            relCheck: true//关联选择
                        },
                        remote: {
                            url: 'role/menu/had/list.json',
                            type: 'POST',
                            otherParam: {}
                        },
                        selectedIds: [],
                        events: {}
                    };

                    //设定已保存组织机构
                    //if (that.vars.roleItemVar) config.selectedIds.push(that.vars.roleItemVar['belgOrgNo']);
                    if (that.vars.roleItemVar) config.remote.otherParam['params[roleId]'] = that.vars.roleItemVar.id;

                    //远程请求回调函数
                    config.remote.callback = function (data) {
                        if (!data.success) return false;
                        return {
                            list: app.toTreeJson(data['retMap']['having'], {
                                id: 'id',
                                pid: 'parMenuId',
                                children: 'list'
                            }),
                            selectedIds: data['retMap']['had']
                        };
                    };
                    that.vars.menuTreeVar = $('#staffOrgTree').treeGroup(config);
                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that; };
                    },
                    save: function () {
                   	 	if($('#staffOrgTree').children()==null || $('#staffOrgTree').children().length==0){
                   		 	appui.message.warning('该子系统没有菜单可设置!');
                        	return;
                        }
                        var that = this.global();
                       
                        var $saveBtn = $("#saveBtn", that.selector);
                        
                        var items = that.vars.menuTreeVar.selectedNodes();

                        //$saveBtn.disabled(true);

                        var data = {
                            sysId: $('input[name=sysId]', that.selector).val(),
                            roleId: that.vars.roleItemVar.id,
                            menuIds: []
                        };

                        for (var i = 0; i < items.length; i++) data.menuIds.push(items[i].id)

                        $.ajax({
                            url: 'role/menu/had/save.json',
                            type: 'POST',
                            data: data,
                            success: function (data) {
                                $saveBtn.disabled(false);
                                if (!data.success) {
                                    appui.message.error('操作失败');
                                    return false;
                                }
                                appui.modal.close(data);
                            },
                            error: function(data) {
                            	$saveBtn.disabled(false);
                            	appui.message.error('操作失败');
                            }
                        });

                    }
                }
            }

        });
    </script>
</head>
<body>
<div class="container-fluid otod-system-sysrole-menu-setting">
    <form class="form-horizontal">
        <div class="row">
            <input type="text" class="selectGroup" name="sysId" options="子系统名称"/>
        </div>
    </form>
    <div class="menu-container">
        <ul id="staffOrgTree" class="ui-tree"></ul>
    </div>
    <div class="row toolbars">
        <div class="pull-right">
            <button type="button" class="btn btn-primary btn-sm" id="saveBtn"><i class="fa fa-save"></i> 保存</button>
            <button type="button" class="btn btn-danger btn-sm" id="cancelBtn"><i class="fa fa-remove"></i> 取消</button>
        </div>
    </div>
</div>
</body>
</html>