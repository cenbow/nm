<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单管理</title>
    <style type="text/css">
        .otod-system-sysmenu.container-fluid{height: 100%;}
        .otod-system-sysmenu #tree{height: 100%; border-right: 1px dashed #ddd; max-height: 100%;}
        .otod-system-sysmenu .tree-tools {margin-bottom: 10px; padding-bottom: 10px;border-bottom: 1px dashed #ddd;}
        .otod-system-sysmenu .tree-tools a {margin-left: 5px; margin-right: 5px;}
        .otod-system-sysmenu .tree-container{width: 100%; max-width: 100%; overflow: auto;}
        .otod-system-sysmenu .form-tools {padding-top: 10px; border-top: 1px dashed #ddd;}
    </style>
    <script type="text/javascript">

        $(function(){
            app.init();
        });

        app.init('otod.system.sysmenu', {
            //顶层全局变量
            vars: {
                treeVar: false,
                formValidatorVar: false
            },
            //顶层初始化函数
            init: function(){
                var that = this;
                this.global = function () { return that; };

                //获取码表数据
                app.code('menuType, availableStat');

                this.layout(that);
                this.tree.init(that);
                this.form.init(that);

            },
            //顶层布局
            layout: function (that) {
                //$("#tree", this.selector).css('min-height', $(window).height());
            },
            //机构树
            tree: {
                init: function (that){
                    this.global = function () { return that; };

                    this.layout();
                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    $('#addRootBtn', that.selector).bind('click', that, function (event) {
                        var that = event.data.refresh();
                        that.form.handlers.setData({
                            img: 'fa fa-tasks',
                            url: 'javascript:;'
                        });
			
                        event.preventDefault();
                        event.stopPropagation();
                        return false;
                    });

                    $('#addNextBtn', that.selector).bind('click', that, function (event) {
                        var that = event.data.refresh();
                        var items = that.vars.treeVar.selectedNodes();
                        if (items == null || items.length <= 0) {
                            appui.message.info('请选择一条操作数据.');
                            return false;
                        }

                        var item = {
                            sysId: items[0].sysId,
                            parMenuId: items[0].id,
                            img: 'fa fa-tasks',
                            url: 'javascript:;'
                        };


                        that.form.handlers.setData(item);

                        event.preventDefault();
                        event.stopPropagation();
                        return false;
                    });

                    //删除菜单
                    $('#delBtn', that.selector).bind('click', that, function (event) {
                        var that = event.data.refresh();
                        that.tree.handlers.del();

                        event.preventDefault();
                        event.stopPropagation();
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
                            children: 'list',
                            icon: 'img'
                        },
                        show: {
                            level: 0,
                            multi: false,
                            checkbox: false
                        },
                        remote: {
                            url: 'menu/list.json',
                            type: 'POST'
                        },
                        events: {}
                    };

                    //远程请求回调函数
                    config.remote.callback = function (data) {
                        if(!data.success) return appui.message.error('加载菜单连接异常.');
                        var items = app.toTreeJson(data.list, {
                            id: 'id',
                            pid: 'parMenuId',
                            children: 'list'
                        });
                        return items;
                    };

                    //单击事件注册
                    config.events.change = {
                        data: that,
                        handler: function (event, values, items) {
                            var that = event.data.refresh(), item;
                            if (items.length == 1) item = items[0];
                            else item = {};
                            that.form.handlers.setData(item);

                        }
                    };

                    that.vars.treeVar = $("#menuTree", that.selector).treeGroup(config);
                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that; };
                    },
                    del: function () {
                        var that = this.global();

                        var items = that.vars.treeVar.selectedNodes();
                        if (items == null || items.length <= 0) {
                            appui.message.info('请选择一条操作数据.');
                            return false;
                        }

                        var item = { id: items[0].id };

                        $.ajax({
                            url: 'menu/del.json',
                            type: 'POST',
                            data: item,
                            success: function (data) {
                                if (!data.success) appui.message.error('操作失败.');
                                appui.message.success('操作成功！');

                                //刷新机构树
                                that.vars.treeVar.refresh();
                            }
                        });
                    }
                }
            },
            //机构表单
            form: {
                init: function (that) {
                    this.global = function () { return that; };

                    this.layout();
                    this.validator();
                    this.handlers.init(that);
                },
                //表单验证规则
                validator: function() {
                    var that = this.global();
                    that.vars.formValidatorVar = $('form', that.selector).validate({
                        rules: {
                        	sysId: {
                                required: true
                            },
                            menuName:{
                            	required: true,
                            	rangelength:[1,11]
                            },
                            menuOrder:{
                            	required: true,
                            	digits: true,
                            	rangelength:[1,5]
                            },
                        }
                    });
                },
                layout: function () {
                    var that = this.global();

                    $(".inputGroup", that.selector).inputGroup({
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-3 col-sm-2',
                        cssInputGroup: 'col-xs-9 col-sm-10'
                    });

                    $('input[name=menuStat]', that.selector).selectGroup({
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-3 col-sm-2',
                        cssInputGroup: 'col-xs-9 col-sm-10',
                        code: {
                            type: 'availableStat'
                        }
                    });

                    $('input[name=menuTyp]', that.selector).selectGroup({
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-3 col-sm-2',
                        cssInputGroup: 'col-xs-9 col-sm-10',
                        code: {
                            type: 'menuType'
                        }
                    });

                    $('input[name=sysId]', that.selector).selectGroup({
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-3 col-sm-2',
                        cssInputGroup: 'col-xs-9 col-sm-10',
                        itemLabel: 'sysName',
                        itemValue: 'id',
                        remote:{
                            url: "sub/list.json",
                            type: "POST",
                            cache: false,
                            callback: function(items){
                                return items.list;
                            }
                        }
                    });

                    $('#saveBtn', that.selector).bind('click', that, function (event) {
                        var that = event.data.refresh();
                        that.form.handlers.save();
                        return false;
                    });
                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that; };
                    },
                    setData: function (item) {
                        var that = this.global();

                        $('input[name=id]', that.selector).valChange(item['id'] || '');
                        $('input[name=sysId]', that.selector).valChange(item['sysId'] || '');
                        $('input[name=parMenuId]', that.selector).valChange(item['parMenuId'] || '');
                        $('input[name=menuName]', that.selector).valChange(item['menuName'] || '');
                        $('input[name=url]', that.selector).valChange(item['url'] || '');
                        $('input[name=menuTyp]', that.selector).valChange(item['menuTyp'] || '11001001');
//                         $('input[name=menuTyp]', that.selector).readonly(true);
                        $('input[name=img]', that.selector).valChange(item['img'] || '');
                        $('input[name=menuOrder]', that.selector).valChange(item['menuOrder'] || '');
                        $('input[name=menuStat]', that.selector).valChange(item['menuStat'] || '');
                        $('input[name=sysId]', that.selector).readOnly(Boolean(item['sysId']));
                    },
                    save: function () {
                        var that = this.global();

                        if (!that.vars.formValidatorVar.form()) return false;

                        var $saveBtn = $('#saveBtn', that.selector);

                        //$saveBtn.disabled(true);

                        var item = $(that.vars.formValidatorVar.currentForm).serialize();

                        $.ajax({
                            url: 'menu/save.json',
                            data: item,
                            type: 'POST',
                            success: function (data) {
                                $saveBtn.disabled(false);

                                if (!data.success) {
                                    appui.message.error('操作失败.');
                                    return false;
                                }

                                appui.message.success('操作成功.');

                                $('input[type=text], textarea', that.selector).valChange('');

                                that.vars.treeVar.refresh();
                            }
                        });
                    }
                }
            }
        });


    </script>
</head>
<body>
<div class="container-fluid otod-system-sysmenu">
    <div class="col-xs-4" id="tree">
        <h3 class="page-header"><i class="fa fa-th-large"></i> 系统菜单</h3>
        <div class="tree-container">
            <div class="tree-tools">
                <a class="fa fa-plus-square" href="javacript:;" id="addRootBtn"> 添加根菜单</a>
                <a class="fa fa-plus-square" href="javacript:;" id="addNextBtn"> 添加下级菜单</a>
                <a class="fa fa-minus-square text-danger" href="javascript:;" id="delBtn"> 删除菜单</a>
            </div>
            <ul id="menuTree" class="ui-tree"></ul>
        </div>
    </div>
    <div class="col-xs-8">
        <h3 class="page-header"><i class="fa fa-th-list"></i> 菜单明细</h3>
        <div class="form-title" style="display: none;">请选择菜单（点击左边树）</div>
        <form class="form-horizontal">
            <input type="hidden" name="id">
            <input type="hidden" name="parMenuId"/>

            <div class="row">
                <input type="text" class="selectGroup" options="子系统" name="sysId"/>
                <input type="text" class="inputGroup" options="菜单名称" name="menuName"/>
                <input type="text" class="inputGroup" options="菜单URL" name="url"/>
                <input type="text" class="selectGroup" options="菜单类型" name="menuTyp" value="11001001" readonly="readonly"/>
                <input type="text" class="inputGroup" options="菜单图片" name="img" value="fa fa-tasks"/>
                <input type="text" class="inputGroup" options="菜单序号" name="menuOrder"/>
                <input type="text" class="selectGroup" options="菜单状态" name="menuStat"/>
            </div>
        </form>
        <div class="form-tools text-right">
            <button class="btn btn-primary btn-sm" type="button" id="saveBtn"><i class="fa fa-save"></i> 保存菜单</button>
        </div>

    </div>
</div>

</body>
</html>
