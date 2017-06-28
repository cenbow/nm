<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <title>用户管理 | 角色查询</title>
  <style type="text/css">
      .otod-system-sysstaff-role-list{}
      .otod-system-sysstaff-role-list .ui-querybars {padding-bottom: 5px;}
  </style>
  <script type="text/javascript">
      $(function() { app.init(); });

      app.init('otod.system.sysstaff.role.list', {
          vars: {
              gridVar: false
          },
          init: function () {
              var that = this;
              this.global = function () { return that; };

              this.layout();
              this.grid.init(that);
          },
          layout: function () {
              var that = this.global();
          },
          grid: {
              init: function (that) {
              this.global = function () { return that; };

              this.layout();
              this.handlers.init(that);
          },
          layout: function () {
              var that = this.global();

              $('#okBtn', that.selector).bind('click', that, function (event) {
                  event.preventDefault();
                  event.stopPropagation();
                  var that = event.data.refresh();
                  that.grid.handlers.selected();
                  return false;
              });

              //定义列
              var cols = [
                { title:'角色编号', name:'roleNo' ,width:100, align:'center'},
                { title:'角色名称', name:'roleName' ,width:100, align:'center'}
              ];

              var config = {
                cols: cols,
                url: 'staff/role/list.json',
                params: {},
                noDataText: '没有数据',
                multiSelect: false,
                checkCol: true,
                indexCol: true,
                nowrap :false, //内容不折行
                plugins: [],
                events:{},
                customEvents: []
              };

              config.plugins.push($('#roleGridPage', that.selector).gridGroupPaginator());
              config.plugins.push($("#roleGridQuery", that.selector).gridGroupQuery({queryBtn: $("#queryBtn", that.selector)}));

              that.vars.gridVar = $('#roleGrid', that.selector).gridGroup(config);
          },
          handlers: {
              init: function (that) {
                  this.global = function () { return that; };
              },
              selected: function () {
                  var that = this.global();
                  var items = that.vars.gridVar.selectedRows();
                  var item = {};
                  if (items.length != 0) item = items[0];
                  appui.modal.close({item: item});
              }
          }
      }
    });
  </script>
</head>
<body>
<div class="container-fluid otod-system-sysstaff-role-list">
    <div class="row ui-querybars">
        <div class="pull-left">
            <div class="input-group input-group-sm" id="roleGridQuery">
                <input type="text" class="form-control" name="roleName" placeholder="角色名称"/>
                <div class="input-group-btn">
                    <button class="btn btn-default btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
                </div>
            </div>
        </div>
        <div class="pull-right">
            <button class="btn btn-primary btn-sm" id="okBtn"><i class="fa fa-check fa-lg"></i> 确定选择</button>
        </div>
    </div>
    <table id="roleGrid"></table>
    <div id="roleGridPage"></div>
</div>
</body>
</html>
