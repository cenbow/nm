<%--
  User: Mifeng.He(bee)
  Date: 2015/10/25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>操作日志</title>
    <style type="text/css">
        .otod-system-sysoperaterLog {}
    </style>
    <script type="text/javascript">
        $(function(){ app.init(); });

        app.init('otod.system.sysoperaterLog', {
            vars:{
                gridGroupVar: null
            },
            init : function(){
                var that = this;
                app.code('oprateType,channlType,subSys,saleChanl');
                this.layout(that);
                this.grid.init(that);
            },
            layout : function(that) {
				
//             	$('#busiTyp', that.selector).selectGroup({
//         			itemLabel: 'sysName',
//         		    itemValue: 'sysCod',
//         		    showItemNum: 4,
//         		    remote: {
//         		        url: 'operater/log/sub/list',
//         		        type: 'POST'
//         		    }
//         		});
			
				$('#busiTyp', that.selector).selectGroup({
				    showItemNum: 4,
				    code:{
						type:'subSys'
					}
				});
            	
            	$('#operateTyp', that.selector).selectGroup({
        		    showItemNum: 4,
        		    code:{
        				type:'oprateType'
        			}
        		});
            	
            },
            grid : {
                init: function (that) {
                    this.global = function () {
                        return that;
                    };

                    this.layout();
                },
                layout: function () {
                    var that = this.global();

                    $(".inputGroup", that.selector).inputGroup();

                    $('#startDate, #endDate', that.selector).datetimepicker({
                        viewMode: 'months',
                        locale: 'zh_cn',
                        format: 'YYYY-MM-DD'
                    });

                    var cols = [
                        { title:'员工号', name:'staffId' ,width:100, align:'center'},
                        { title:'员工名称', name:'staffName' ,width:100, align:'center'},
                        { title:'登陆IP', name:'logIp' ,width:100, align:'center'},
                        { title:'服务器IP', name:'serverIp' ,width:100, align:'center'},
                        { title:'子系统', name:'busiTyp' ,width:100, align:'center',renderer: function(val, item, rowIndex){
                        	return app.code.getText('subSys', val, rowIndex);
                        }},
                        { title:'系统时间', name:'logDate' ,width:100, align:'center'},
                        { title:'操作主题', name:'operateTyp' ,width:100, align:'center',renderer: function(val, item, rowIndex){
                        	return app.code.getText('oprateType', val, rowIndex);
                        }},
                        { title:'操作信息', name:'operateInfo' ,width:100, align:'center'},
                        { title:'系统名', name:'sysName' ,width:100, align:'center'},
                        { title:'渠道', name:'channel' ,width:100, align:'center',renderer: function(val, item, rowIndex){
                        	return app.code.getText('saleChanl', val, rowIndex);
                        }}
                    ];

                    var config = {
                        cols: cols,
                        url: 'operater/log/list.json',
                        params: {
                        },
                        multiSelect: false,
                        checkCol: false,
                        indexCol: false,
                        nowrap :false, //内容不折行
                        plugins: [],
                        events: {},
                        customEvents: []
                    };

                    config.plugins.push($('#gridPageGroup', that.selector).gridGroupPaginator({}));
                    config.plugins.push($("#gridQueryGroup", that.selector).gridGroupQuery({queryBtn: $("#queryBtn", that.selector), resetBtn: $("#resetBtn", that.selector)}));

                    that.vars.gridGroupVar = $('#gridGroup', that.selector).gridGroup(config);


                }
            }
        });
    </script>
</head>
<body>
    <div class="container-fluid otod-system-sysoperaterLog">
    	<h3 class="page-header"><i class="fa fa-th"></i> 操作日志</h3>
        <div class="ui-querybars row" id="gridQueryGroup">
            <form id="logForm">
                <input type="text" id="staffId" class="inputGroup" name="staffId" options='{txtPrefix: "员工号"}' />
                <input type="text" id="staffName" class="inputGroup" name="staffName" options='{txtPrefix: "姓名"}' />
                <div class="col-xs-6 col-sm-3 col-lg-5">
                    <div class="form-group form-group-sm">
                        <label class="control-label"></label>
                        <div class="input-group">
                            <div class="input-group-addon">时间</div>
                            <input id="startDate" name="startDate" class="form-control notAuto" />
                            <div class="input-group-addon">&nbsp;至</div>
                            <input id="endDate" name="endDate" class="form-control notAuto" />
                        </div>
                    </div>
                </div>

                <input type="text" id="operateTyp" class="selectGroup" name="operateTyp" options='{txtPrefix: "操作主题"}' />

                <input type="text" id="busiTyp" class="selectGroup" name="busiTyp" options='{txtPrefix: "子系统"}' />
                <input type="text" id="operateInfo" class="inputGroup" name="operateInfo" options='{txtPrefix: "操作信息"}' />

            </form>
        </div>
        <div class="ui-toolbars row">
            <div class="pull-right">
                <button class="btn btn-primary btn-sm" id="queryBtn" ><i class="fa fa-search"></i> 查询</button>
                <button class="btn btn-info btn-danger" id="resetBtn"><i class="fa fa-repeat"></i> 重置</button>
            </div>
        </div>
        <div id="initList" >
            <table id="gridGroup"></table>
            <div id="gridPageGroup"></div>
        </div>

        <div id="queryList" style="">
            <table id="queryGroup"></table>
            <div id="queryPageGroup"></div>
        </div>
    </div>
</body>
</html>
