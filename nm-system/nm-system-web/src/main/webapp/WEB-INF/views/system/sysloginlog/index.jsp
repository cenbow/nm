<%--
  User: Mifeng.He(bee)
  Date: 2015/10/25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆日志</title>
    <style type="text/css">
        .otod-system-sysloginlog {}
    </style>
    <script type="text/javascript">
        $(function(){ app.init(); });

        app.init('otod.system.sysloginlog', {
            vars:{
                gridGroupVar: false
            },
            init : function(){
                var that = this;

                this.layout();
                this.grid.init(that);
            },
            layout : function(that) {

            },
            grid : {
                init: function (that) {
                    this.global = function () { return that;};

                    this.layout();
                },
                layout: function () {
                    var that = this.global();

                    $(".inputGroup", that.selector).inputGroup();
                    
                    $('.orgSelectTreeGroup', that.selector).selectTreeGroup({
						itemLabel: 'orgName',
						itemValue: 'orgCodPath',
						data: {
							id: 'orgNo',
							pid: 'parOrgNo',
							name: 'orgName',
							children: 'list'
						},
						show:{
							level: 1,
							multi: false,
							checkbox: true
						},
						remote: {
							url: 'priv/info/org/list.json',
							type: 'POST',
							data: {},
							callback: function (data) {
								return app.toTreeJson(data.list, {
									id: 'orgNo',
									pid: 'parOrgNo',
									children: 'list'
								});
							}
						},
						events: {
							change: {
								handler: function (event, values, items) {
									if(items && items.length > 0)
										$("input[name =orgPathName]", that.selector).valChange(items[0].orgName);
								}
							}
						}
					});

                    $('#startDate, #endDate', that.selector).datetimepicker({
                        viewMode: 'months',
                        locale:'zh_cn',
                        format: 'YYYY-MM-DD'
                    });

                    var cols = [
                        { title:'员工号', name:'staffId' ,width:100, align:'center'},
                        { title:'姓名', name:'staffName' ,width:100, align:'center'},
                        { title:'时间', name:'loginDate' ,width:100, align:'center'},
                        { title:'机构名称', name:'orgName' ,width:100, align:'center'},
                        { title:'登录IP', name:'loginIp' ,width:100, align:'center'}
                    ];
                    that.vars.gridGroup = $('#gridGroup', that.selector).gridGroup({
                        cols: cols,
                        url: 'login/log/list.json',
                        multiSelect: false,
                        checkCol: false,
                        indexCol: true,
                        nowrap :false, //内容不折行
                        plugins: [
                            $('#gridPageGroup', that.selector).gridGroupPaginator({}),
                            $("#gridQueryGroup", that.selector).gridGroupQuery({
                                queryBtn:$("#queryBtn", that.selector),
                                resetBtn: $("#resetBtn", that.selector)
                            })
                        ]
                    });
                }
            }
        });
    </script>
</head>
<body>
    <div class="container-fluid otod-system-sysloginlog">
    	<h3 class="page-header"><i class="fa fa-th"></i> 登录日志</h3>
        <div class="ui-querybars row" id="gridQueryGroup">
            <input type="text" class="inputGroup" name="staffId" options='{txtPrefix: "员工号"}' />
            <input type="text" class="inputGroup" name="staffName" options='{txtPrefix: "姓名"}' />
            <div class="col-xs-6">
                <div class="form-group form-group-sm">
                    <label class="control-label"></label>
                    <div class="input-group">
                        <div class="input-group-addon">时间</div>
                        <input id="startDate" name="startDate" class="form-control" />
                        <div  class="input-group-addon">&nbsp;至</div>
                        <input id="endDate" name="endDate" class="form-control" />
                    </div>
                </div>
            </div>
            <input type="text" class="orgSelectTreeGroup" name="orgId" options='{txtPrefix: "机构名称"}'  /><!--机构下拉树  -->
         	<input type="hidden" name="orgNo">
            <input type="text" id="loginIp" name="loginIp" class="inputGroup"  options='{txtPrefix: "登录IP"}' />
        </div>
        <div class="ui-toolbars row">
            <div  class="pull-right">
                <button  class="btn btn-primary btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
                <button class="btn btn-info btn-danger" id="resetBtn"><i class="fa fa-repeat"></i> 重置</button>
            </div>
        </div>
        <table id="gridGroup"></table>
        <div id="gridPageGroup"></div>
    </div>
</body>
</html>
