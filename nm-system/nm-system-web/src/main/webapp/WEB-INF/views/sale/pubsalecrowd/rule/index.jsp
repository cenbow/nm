<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>规则管理</title>
    <style type="text/css">
        .otod-system-sale-pubsalecrowd-rule {}
        .otod-system-sale-pubsalecrowd-rule .query-group {width: 200px;}
    </style>
    <script type="text/javascript">
        $(function () {
            app.init();
        });

        app.init('otod.system.sale.pubsalecrowd.rule', {
            vars: {
                ruleGridVar: false,
                itemVar: false
            },
            init: function () {
                var that = this;
                this.global = function () { return that;};

                if (typeof that.modal != 'undefined') that.vars.itemVar = that.modal.param.item;

                that.layout(that);
                that.ruleGrid.init(that);

            },
            layout: function (that) {
            },
            ruleGrid: {
                init: function (that) {
                    this.global = function () { return that;};
                    this.layout();
                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    if (that.vars.itemVar) {
                    	$('#crowdNo', that.selector).val(that.vars.itemVar.crowdNo);
                    }


                    var cols = [
                        {title:'编号',name: 'staffNo',align: 'center', width: '100'},
		                { title: '销售员姓名', name: 'staffName', align: 'center', width: '100'}
                    ];

                    var config = {
                        cols: cols,
                        url: 'sale/crowd/rule/exec.json',
                        checkCol: false,
                        indexCol: true,
                        plugins:[],
                        events: {},
                        customEvents: []
                    };

                    config.plugins.push($("#ruleGridQuery", that.selector).gridGroupQuery({ queryBtn: $('#ruleGridQuery #queryBtn', that.selector)}));
                    config.plugins.push($("#ruleGridPage", that.selector).gridGroupPaginator());

                    that.vars.ruleGridVar = $("#ruleGrid", that.selector).gridGroup(config);
                },
                handlers: {
                    init: function (that){
                        this.global = function () { return that;}
                    }
                }
            }
        });

    </script>
</head>
<body>
<div class="container-fluid otod-system-sale-pubsalecrowd-rule">
        <h4 class="page-header"><i class="fa fa-th-large"></i> 规则</h4>
        <div class="row ui-toolbars typeToolBars">
            <div class="pull-right" id="ruleGridQuery">
                <div class="control-group query-group">
                    <input type="hidden" id="crowdNo" name="crowdNo"/>
                    <div class="input-group input-group-sm">
                        <input class="form-control" id="staffName" type="text" name="staffName" placeholder="人员名称"/>
                        <div class="input-group-btn">
                            <button class="btn btn-primary btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--ruleGrid-->
        <table id="ruleGrid"></table>
        <div id="ruleGridPage"></div>
</div>
</body>
</html>
