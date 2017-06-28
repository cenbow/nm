<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>省市县</title>
    <style type="text/css">
        .otod-system-sysregbelong.container-fluid{height: 100%;}
        .otod-system-sysregbelong #tree{height: 100%; border-right: 1px dashed #ddd; max-height: 100%;}
        .otod-system-sysregbelong .tree-tools {margin-bottom: 10px; padding-bottom: 10px;border-bottom: 1px dashed #ddd;}
        .otod-system-sysregbelong .tree-tools a {margin-left: 5px; margin-right: 5px;}
        .otod-system-sysregbelong .org-tree-container{width: 100%; max-width: 100%; overflow: auto;}
        .otod-system-sysregbelong .form-tools {padding-top: 10px; border-top: 1px dashed #ddd;}
    </style>
    <script type="text/javascript">
        $(function(){
            app.init();
        });

        app.init('otod.system.sysregbelong', {
            //顶层全局变量
            vars: {
                treeVar: false,
                formValidator: false
                /* orgCodPathLimitVar: ',',
                rootOrgItem:{
                    parOrgNo: '00',
                    parOrgCodPath: '00'
                } */
            },
            //顶层初始化函数
            init: function(){
                var that = this;
                this.global = function () { return that; };

                //获取码表数据
                //app.code('areaCode.json');

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
                        that.form.handlers.setData('');
                        $('#notProvDiv').hide();
                        event.preventDefault();
                        event.stopPropagation();
                        return false;
                    });

                    $('#addNextBtn', that.selector).bind('click', that, function (event) {
                        var that = event.data.refresh();
                        var items = that.vars.treeVar.selectedNodes();
                        $('#notProvDiv').show();
                        if (items == null || items.length <= 0) {
                            appui.message.info('请选择一条操作数据.');
                            return false;
                        }

                        var item = {
                        	provNo:items[0].provNo,
                        	provName:items[0].provName,
                        	cityNo:items[0].cityNo,
                        	cityName:items[0].cityName,
                        	areaNo:items[0].areaNo,
                        	areaName:items[0].areaName
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

                    /* var nodeVal = '';
                    var nodes = that.vars.treeVar.selectedNodes();
                    if(nodes!=null && nodes.length>0){
                    	if(nodeVal[0].provNo!=null && nodeVal[0].provNo!=''){
                    		nodeVal = nodeVal[0].provNo;
                    	}else if(nodeVal[0].cityNo!=null && nodeVal[0].cityNo!=''){
                    		nodeVal = nodeVal[0].cityNo;
                    	}else if(nodeVal[0].areaNo!=null && nodeVal[0].areaNo!=''){
                    		nodeVal = nodeVal[0].areaNo;
                    	}
                    } */
                    //树配置
                    var config = {
                        icons: {
                            leafIcon: 'fa-list-alt'
                        },
                        css: {},
                        data: {
                            id: 'id',
                            pid: 'pid',
                            name: 'name',
                            children: 'list'
                        },
                        show: {
                            multi: false,
                            checkbox: false
                        },
                        remote: {
                            url: 'reg/belong/list.json',
                            type: 'POST',
                            otherParam:{prono:''}
                        },
                        events: {}
                    };

                    //远程请求回调函数
                    config.remote.callback = function (data) {
                    	$('#notProvDiv').show();
                        if(!data.success) return appui.message.error('加载省市县连接异常.');
                        var items = data.list, item;
                        for (var i = 0; i < items.length; i++) {
                        	item = items[i];
                        	item.key = item.id;
                        	item.name = item.areaName || item.cityName || item.provName;
                        	item.id = item.areaNo || item.cityNo || item.provNo;
                        	if (item.areaNo) item.pid = item.areaNo.substring(0, 4) + '00';
                        	else if (item.cityNo) item.pid = item.cityNo.substring(0, 2) + '0000';
                        	else item.pid = "";
                        }
                        items = app.toTreeJson(items, {
                            id: 'id',
                            pid: 'pid',
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

                        var item = { id: items[0].key };

                        $.ajax({
                            url: 'reg/belong/del.json',
                            type: 'POST',
                            data: item,
                            success: function (data) {
                                if (!data.success) appui.message.error('操作失败.');
                                appui.message.success('操作成功！');
                                //刷新机构树
                                that.vars.treeVar.refresh();
                                //that.form.handlers.setData(that.vars.rootOrgItem);
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
                            provNo: { required: true},
                            provName: { required: true}
                            /* cityNo: { required: true},
                            cityName: { required: true},
                            areaNo: { required: true},
                            areaName: { required: true} */
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

                    /*$('input[name=stat]').selectGroup({
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-3 col-sm-2',
                        cssInputGroup: 'col-xs-9 col-sm-10',
                        code: { type: 'yon' }
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
                    });*/

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
                        $('input[name=provNo]', that.selector).valChange(item.provNo || '');
                        $('input[name=provName]', that.selector).valChange(item.provName || '');
                        $('input[name=cityNo]', that.selector).valChange(item.cityNo || '');
                        $('input[name=cityName]', that.selector).valChange(item.cityName || '');
                        $('input[name=areaNo]', that.selector).valChange(item.areaNo || '');
                        $('input[name=areaName]', that.selector).valChange(item.areaName || '');

                        //上级机构路径
                       // $('input[name=parOrgCodPath]', that.selector).valChange(item.parOrgCodPath || '');

                        //$('input[name=orgNo]', that.selector).readOnly(Boolean(item.orgNo));

                        that.vars.formValidatorVar.resetForm();
                    },
                    save: function () {
                        var that = this.global();

                        if (!that.vars.formValidatorVar.form()) return false;

                        var $saveBtn = $('#saveBtn', that.selector);

                        $saveBtn.disabled(true);

                        var item = $(that.vars.formValidatorVar.currentForm).serialize();

                        $.ajax({
                            url: 'reg/belong/save.json',
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

                                //that.form.handlers.setData(that.vars.rootOrgItem);
                            }
                        });
                    }
                }
            }
        });
    </script>
</head>
<body>
	<div class="container-fluid otod-system-sysregbelong">
	    <div class="col-xs-4" id="tree">
	        <h3 class="page-header"><i class="fa fa-th-large"></i> 省市县</h3>
	        <div class="tree-container">
	            <div class="tree-tools">
	                <a class="fa fa-plus-square" href="javacript:;" id="addRootBtn"> 添加省</a>
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
	            <!-- <input type="hidden" name="id">
	            <input type="hidden" name="areaNo" />
	            <input type="hidden" name="parOrgCodPath" disabled/> -->
	            <div class="row" id="provDiv">
	                <input type="text" class="inputGroup" options="省编号" name="provNo"/>
	                <input type="text" class="inputGroup" options="省名称" name="provName"/>
	            </div>
	            <div class="row" id="notProvDiv">
	                <input type="text" class="inputGroup" options="市编号" name="cityNo"/>
	                <input type="text" class="inputGroup" options="市名称" name="cityName"/>
	                <input type="text" class="inputGroup" options="区/县编号" name="areaNo"/>
	                <input type="text" class="inputGroup" options="区/县名称" name="areaName"/>
	            </div>
	        </form>
	        <div class="form-tools text-right">
	            <button class="btn btn-primary btn-sm" type="button" id="saveBtn"><i class="fa fa-save"></i> 保存设置</button>
	        </div>
	    </div>
	</div>
</body>
</html>
