<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>组织机构</title>
    <style type="text/css">
        .otod-system-sysorg.container-fluid{height: 100%;}
        .otod-system-sysorg #tree{height: 100%; border-right: 1px dashed #ddd; max-height: 100%;}
        .otod-system-sysorg .tree-tools {margin-bottom: 10px; padding-bottom: 10px;border-bottom: 1px dashed #ddd;}
        .otod-system-sysorg .tree-tools a {margin-left: 5px; margin-right: 5px;}
        .otod-system-sysorg .org-tree-container{width: 100%; max-width: 100%; overflow: auto;}
        .otod-system-sysorg .form-tools {padding-top: 10px; border-top: 1px dashed #ddd;}
    </style>
    <script type="text/javascript">
        $(function(){
            app.init();
        });

        app.init('otod.system.sysorg', {
            //顶层全局变量
            vars: {
                treeVar: false,
                formValidator: false,
                orgCodPathLimitVar: ',',
                rootOrgItem:{
                    parOrgNo: '00',
                    parOrgCodPath: '00'
                }
            },
            //顶层初始化函数
            init: function(){
                var that = this;
                this.global = function () { return that; };

                //获取码表数据
                app.code('orgAttr, availableStat, areaCode.json');

                this.layout();
                this.tree.init(that);
                this.form.init(that);

            },
            //顶层布局
            layout: function (that) {
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
                        that.form.handlers.setData(that.vars.rootOrgItem);

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
                            parOrgNo: items[0].orgNo,
                            parOrgCodPath: items[0].orgCodPath
                        };


                        that.form.handlers.setData(item);

                        event.preventDefault();
                        event.stopPropagation();
                        return false;
                    });

                    //删除机构
                    $('#delBtn', that.selector).bind('click', that, function (event) {
                        var that = event.data.refresh();
                        that.tree.handlers.del();

                        event.preventDefault();
                        event.stopPropagation();
                        return false;
                    });


                    //树配置
                    var config = {
                        icons: {
                            leafIcon: 'fa-list-alt'
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
                            checkbox: false
                        },
                        remote: {
                            url: 'org/list.json',
                            type: 'POST'
                        },
                        events: {}
                    };

                    //远程请求回调函数
                    config.remote.callback = function (data) {
                        if(!data.success) return appui.message.error('加载机构连接异常.');
                        var items = app.toTreeJson(data.list, {
                            id: 'orgNo',
                            pid: 'parOrgNo',
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

                    that.vars.treeVar = $("#orgTree", that.selector).treeGroup(config);
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
                            url: 'org/del.json',
                            type: 'POST',
                            data: item,
                            success: function (data) {
                                if (!data.success) appui.message.error('操作失败.');
                                appui.message.success('操作成功！');
                                //刷新机构树
                                that.vars.treeVar.refresh();

                                that.form.handlers.setData(that.vars.rootOrgItem);
                            }
                        });
                    }
                }
            },
            //机构表单
            form: {
                init: function (that) {
                    this.global = function () { return that;};

                    this.layout();
                    this.validator();
                    this.handlers.init(that);
                },
                //表单验证规则
                validator: function() {
                    var that = this.global();
                    that.vars.formValidatorVar = $('form', that.selector).validate({
                        rules: {
                            parOrgNo: { required: true,rangelength:[1,40]},
                            orgName: { required: true,rangelength:[1,40]},
                            orgNo: { required: true,rangelength:[1,40]},
                            orgAttr: { required: true,rangelength:[1,40]},
                            stat: { required: true,rangelength:[1,10]},
                            orgCodPath: { required: true,rangelength:[1,1000]},
                            orgPath: { required: true}
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

                    $('input[name=stat]').selectGroup({
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-3 col-sm-2',
                        cssInputGroup: 'col-xs-9 col-sm-10',
                        code: { type: 'availableStat' }
                    });

                    $('input[name=orgAttr]').selectGroup({
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-3 col-sm-2',
                        cssInputGroup: 'col-xs-9 col-sm-10',
                        code: { type: 'orgAttr'}
                    });

                    $('input[name=orgPath]', that.selector).selectAreaGroup({
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-3 col-sm-2',
                        cssInputGroup: 'col-xs-9 col-sm-10',
                        itemLabel: 'name',
                        itemValue: 'code',
                        itemChildren: 'children',
                        levelItemValueHide: false,
                        levelItemValueNames: ['provNo', 'cityNo', 'cntyNo'],
                        code: {
                            type: 'areaCode.json'
                        }
                    });

                    $('input[name=orgNo]', that.selector).bind('keyup', that,function (event) {
                        var that = event.data.refresh();
                        var vals = [];
                        vals.push($('input[name=parOrgCodPath]').val());
                        vals.push( $(this).val());
                        $('input[name=orgCodPath]').val(vals.join(','));
                    });

                    //删除机构
                    $('#saveBtn', that.selector).bind('click', that, function (event) {
                        var that = event.data.refresh();
                        that.form.handlers.save();

                        event.preventDefault();
                        event.stopPropagation();
                        return false;
                    });

                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that};
                    },
                    setData: function (item) {
                        var that = this.global();
                        //表单赋值
                        $('input[name=id]', that.selector).valChange(item.id || '');
                        $('input[name=parOrgNo]', that.selector).valChange(item.parOrgNo || '');
                        $('input[name=orgCodPath]', that.selector).valChange(item.orgCodPath || '');
                        $('input[name=orgName]', that.selector).valChange(item.orgName || '');
                        $('input[name=orgNo]', that.selector).valChange(item.orgNo || '');
                        $('input[name=orgAttr]', that.selector).valChange(item.orgAttr || '');
                        $('input[name=stat]', that.selector).valChange(item.stat || '');
                        if(item.provNo) $('input[name=orgPath]', that.selector).valChange([ item.provNo, item.cityNo, item.cntyNo].join(','));
                        else $('input[name=orgPath]', that.selector).valChange('');
                        //上级机构路径
                        $('input[name=parOrgCodPath]', that.selector).valChange(item.parOrgCodPath || '');

                        $('input[name=orgNo]', that.selector).readOnly(Boolean(item.orgNo));

                        that.vars.formValidatorVar.resetForm();
                    },
                    save: function () {
                        var that = this.global();

                        if (!that.vars.formValidatorVar.form()) return false;

                        var $saveBtn = $('#saveBtn', that.selector);
                        var orgCodPath = that.selector.find('input[name=orgCodPath]').val();
                        var orgNo = that.selector.find('input[name=orgNo]').val();
                        if (',' + orgNo ==  orgCodPath) return appui.message.error('机构路径与机构编号一样，不能保存');

                        $saveBtn.disabled(true);

                        var item = $(that.vars.formValidatorVar.currentForm).serialize();

                        $.ajax({
                            url: 'org/save.json',
                            type: 'POST',
                            data: item,
                            success: function (data) {
                                $saveBtn.disabled(false);
                                if (!data.success){
                                    appui.message.error('操作失败.');
                                    return false;
                                }
                                appui.message.success('操作成功.');
                                //刷新机构树
                                that.vars.treeVar.refresh();

                                that.form.handlers.setData(that.vars.rootOrgItem);
                            }
                        });
                    }
                }
            }
        });
    </script>
</head>
<body>
<div class="container-fluid otod-system-sysorg">
    <div class="col-xs-4" id="tree">
        <h3 class="page-header"><i class="fa fa-th-large"></i> 组织机构</h3>
        <div class="tree-container">
            <div class="tree-tools">
                <a class="fa fa-plus-square" href="javacript:;" id="addRootBtn"> 添加根机构</a>
                <a class="fa fa-plus-square" href="javacript:;" id="addNextBtn"> 添加下级机构</a>
                <a class="fa fa-minus-square text-danger" href="javascript:;" id="delBtn"> 删除机构</a>
            </div>
            <ul id="orgTree" class="ui-tree">

            </ul>
        </div>
    </div>
    <div class="col-xs-8">
        <h3 class="page-header"><i class="fa fa-th-list"></i> 组织机构明细</h3>
        <form class="form-horizontal">
            <input type="hidden" name="id">
            <input type="hidden" name="areaNo" />
            <input type="hidden" name="parOrgCodPath" disabled/>

            <div class="row">
                <input type="text" class="inputGroup" options="上级机构" name="parOrgNo" readonly/>
                <input type="text" class="inputGroup" options="机构路径" name="orgCodPath" readonly placeholder="自动生成"/>
                <input type="text" class="inputGroup" options="机构编号" name="orgNo"/>
                <input type="text" class="inputGroup" options="机构名称" name="orgName"/>
                <input type="text" class="selectGroup" options="机构属性" name="orgAttr"/>
                <input type="text" class="selectGroup" options="机构状态" name="stat"/>
                <input type="text" class="selectAreaGroup" options="地址" name="orgPath"/>
            </div>
        </form>
        <div class="form-tools text-right">
            <button class="btn btn-primary btn-sm" type="button" id="saveBtn"><i class="fa fa-save"></i> 保存机构</button>
        </div>

    </div>
</div>

</body>
</html>
