<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理 | 机构设置</title>
    <style type="text/css">
        .otod-system-sysstaff-role-setting {}
        .otod-system-sysstaff-role-setting .toolbars {margin-top: 10px; padding-top: 10px;border-top: 1px dashed #ddd;}
    </style>
    <script type="text/javascript">
        $(function() { app.init(); });

        app.init('otod.system.sysstaff.role.setting',{
            vars: {
                staffItemVar: false,
                staffTreeVar: false
            },
            init: function () {
                var that = this;
                this.global = function () { return that; };

                if (typeof that.modal != 'undefined') that.vars.staffItemVar = that.modal.param.item;

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
                        icons: {
                            leafIcon: 'fa-list-alt',
                            checkIcon: 'fa-circle-o,fa-check-circle-o'
                        },
                        css: {},
                        data: {
                            id: 'orgNo',
                            pid: 'parOrgNo',
                            name: 'orgName',
                            children: 'list'
                        },
                        show: {
                            multi: false,
                            checkbox: true
                        },
                        remote: {
                            url: 'org/list.json',
                            type: 'POST'
                        },
                        selectedIds: [],
                        events: {}
                    };

                    //设定已保存组织机构
                    if (that.vars.staffItemVar) config.selectedIds.push(that.vars.staffItemVar['belgOrgNo']);

                    //远程请求回调函数
                    config.remote.callback = function (data) {
                       return app.toTreeJson(data.list, {
                            id: 'orgNo',
                            pid: 'parOrgNo',
                            children: 'list'
                        });
                    };

                   that.vars.staffTreeVar = $('#staffOrgTree').treeGroup(config);

                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that; };
                    },
                    save: function () {
                        var that = this.global();
                        var $saveBtn = $("#saveBtn", that.selector);
                        var items = that.vars.staffTreeVar.selectedNodes();
                        $saveBtn.disabled(true);
                        var data = {};
                        if (!that.vars.staffItemVar) throw new Error('required staffItemVar value.');
                        
                        data.staffId = that.vars.staffItemVar.id;
                        data.orgId = items.length != 0 ? items[0]['id'] : '';
                        $.ajax({
                            url: 'staff/org/save.json',
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
                            error: function(data){
                            	$saveBtn.disabled(false);
                            	appui.message.error('操作失败.');
                            }
                        });

                    }
                }
            }

        });
    </script>
</head>
<body>
    <div class="container-fluid otod-system-sysstaff-role-setting">
        <ul id="staffOrgTree" class="ui-tree"></ul>
        <div class="row toolbars">
            <div class="pull-right">
                <button type="button" class="btn btn-primary btn-sm" id="saveBtn"><i class="fa fa-save"></i> 保存</button>
                <button type="button" class="btn btn-danger btn-sm" id="cancelBtn"><i class="fa fa-remove"></i> 取消</button>
            </div>
        </div>
    </div>
</body>
</html>
