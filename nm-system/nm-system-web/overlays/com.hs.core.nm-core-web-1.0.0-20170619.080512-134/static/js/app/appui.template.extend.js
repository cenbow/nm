(function($, appui){

    /*模板定义*/
    var templates = {};

    /**
     * @desc loading wrapper tempalte
     */
    templates.loading = {
        template: [
            '<div style="" class="ui-main-loading" id="loading"><div class="ui-loading-con"><div class="ui-loading-circle"></div><img src="static/images/loading_logo.png" alt=""/></div></div>'
        ]
    };

    /**
     * @desc modal wrapper template
     * @param name（可选）保留字段 识别窗体
     * @param title（可选）tilte
     * @param url (必选) url
     * @param body (可选) 上下文， 动态加载html 位置
     * @param isWindow (可选) 是否是一个窗口对象，ture: iframe 新页面新window false: 页面加载, 默认false
     * @param cssModal（可选）modal-size (modal-sm, modal-md, modal-lg, modal-fl) 默认modal-md
     * @param styleBody（可选）modal-body 加上自定义样式 style
     * @param width（可选）modal iframe宽度 设定值后 cssModal 没有实际作用
     * @param height（可选）modal height高度 设定值后 modal不会自动计算高度+50
     * @param top（可选）窗口显示区域在哪一层
     * @param parent（可选) iframe 的父窗口 callback回调函数作用域 默认window
     * @param param（可选） 约定 父向子传值
     * @param（可选）data 约定 子向父传值
     * @param config （可选）
     * {
     *  @param backdrop 默认true boolean 或 字符串 'static'
     *  @param keyboard 默认false boolean 键盘上的 esc 键被按下时关闭模态框。
     *  @param show 默认true boolean 模态框初始化之后就立即显示出来。
     *  @param remote 默认 false path
     * }
     * @param inteval 定时计算高度（height为默认值auto 才有效）
     * {
     *  @times 次数
     *  @time 间隔时间
     * }
     * @param callback（可选）回调函数
     * {
     *  @param show（可选）打开中
     *  @param shown（可选）打开后
     *  @param hide（可选）关闭中
     *  @param hidden（可选）关闭后
     *  @param loaded（可选）异步加载（保留接口）
     * }
     */
    templates.modal = {
        template: [
            '<div class="modal fade {name}Modal" id="{name}Modal" tabindex="-1" role="dialog"><div class="modal-dialog {cssModal}" role="document"><div class="modal-content"><div class="modal-header"><button class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button><h4 class="modal-title {cssTitle}"><i class="{cssTitleIcon}"></i> {title}</h4></div><div class="modal-body" style="{styleBody}"></div></div></div></div>',
            '<iframe src="{url}" class="embed-responsive" id="{name}IFrame" name="{name}IFrame" style="width: {width};height: {height};" frameborder="0" />',
            '<div class="modal-footer"></div>'
        ],
        defaults: {
            name: "",
            title: "",
            url: "",
            body: 'body',
            isWindow: false,
            cssTilte: '',
            cssTitleIcon: 'fa fa-list',
            cssModal: 'modal-md',
            styleBody: '',
            width: 'auto',
            height: 'auto',
            top: top,
            parent: window,
            config:{
                backdrop: 'static',
                keyboard: true,
                show: true,
                remote: false
            },
            interval: {
                times: 5,
                time: 200
            },
            param: {},
            data: {},
            callback: {
                show: function(options){},
                shown: function(options){},
                hide: function(options){},
                hidden: function(options){},
                loaded: function(options){}
            }
        }
    };

    /**
     * @desc input wrapper template
     * @param cssCol（可选）bootstrap 四种默认样式 格式为col-*-* 默认值：col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sg-3 col-mg-2
     * @param cssFormGroupSize (可选) bootstrap 大小 默认form-group-sm
     * @param txtLabel (可选) label text
     * @param cssPreix (可选) prefix class
     * @param txtPrefix (可选) prefix text
     * @param cssPrefixIcon (可选) bootstrap 图标样式 一般用于后缀
     * @param cssSuffix （可选） suffix class
     * @param txtSuffix (可选) 后缀文本
     * @param cssSuffixIcon (可选) bootstrap 图标样式 一般用于后缀
     * @param _before(不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param before (可重写) 建议调用之前执行 options._before.call(this, options);
     * @param _after (不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param after (可重写) 默认回调函数 options._after.call(this, options);
     **/
    templates.inputGroup = {
        template: [
            '<div class="{cssCol}"><div class="form-group {cssFormGroupSize} ui-input-group"><label class="control-label {cssLabel}">{txtLabel}</label><span class="target"></span></div></div>',
            '<div class="input-group"></div>',
            '<div class="{cssPrefix}">{txtPrefix}</div>',
            '<div class="{cssSuffix}"><i class="{cssSuffixIcon}"></i>{txtSuffix}</div>',
            '<div class="{cssInputGroup}"></div>'
        ],
        defaults: {
            cssCol: "col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sg-3 col-mg-2",
            cssFormGroupSize: "form-group-sm",
            cssLabel: "",
            txtLabel: "",
            cssInputGroup: "",
            cssPrefix: "",
            cssPrefixIcon: "",
            txtPrefix: "",
            cssSuffix: "",
            txtSuffix: "",
            cssSuffixIcon: "",
            $target: null,
            _before: function(options){
                var that = options;
                if(!that.cssPrefix)
                    that.cssPrefix = Boolean(that.cssPrefixIcon || that.txtPrefix) ? 'input-group-addon' : 'hide';

                if(!that.cssSuffix)
                    that.cssSuffix = Boolean(that.cssSuffixIcon || that.txtSuffix) ? 'input-group-addon' : 'hide';

            },
            before: function(options){
                options._before.call(this, options);

            },
            after: function(options){

            }
        }
    };


    /**
     * @desc inputButtonGroup wrapper template
     * @param cssCol（可选）bootstrap 四种默认样式 格式为col-*-* 默认值：col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sg-3 col-mg-2
     * @param cssFormGroupSize (可选) bootstrap 大小 默认form-group-sm 注意：此地方没有实际作用
     * @param txtLabel (可选) label text
     * @param cssInputGroupSize (可选) 按钮组的大小 默认btn-group-sm
     * @param cssActive (可选) 按钮选中样式
     * @param cssPreix (可选) prefix class
     * @param txtPrefix (可选) prefix text
     * @param cssPrefixIcon (可选) bootstrap 图标样式 一般用于后缀
     * @param cssSuffix （可选） suffix class 默认 input-group-btn
     * @param cssSuffixBtn (可选) 默认 btn-default
     * @param cssSuffixIcon (可选) 默认  caret
     * @param txtSuffix (可选) 后缀文本
     * @param itemLable (可选) item display label 默认 label
     * @param itemValue (可选) item realy value 默认 value
     * @param _before(不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param before (可重写) 建议调用之前执行 options._before.call(this, options);
     * @param _after (不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param after (可重写) 默认回调函数 options._after.call(this, options);
     * @param events (可选)事件注册
     * {
     *  @param btnClick (可选) 按钮事件
     *  {
     *   @param data (可选) 参数，绑定参数 在回调事件中调用
     *   @param handler (可重写) 事件回调函数 handler 获取参数用 event.data
     *  }
     *  @param change (可选) 值改变事件
     *  {
     *   @param data (可选) 参数，绑定参数 在回调事件中调用
     *   @param handler (可重写) 事件回调函数 handler 获取参数用 event.data
     *  }
     * }
     **/
    templates.inputButtonGroup = {
        template: [
            '<div class="{cssCol}"><div class="form-group ui-input-button-group"><label class="control-label {cssLabel}">{txtLabel}</label><div class="input-group {cssInputGroupSize}"><span class="target"></span><div class="{cssSuffix}"><button value="" type="button" class="btn {cssSuffixBtn}"><i class="{cssSuffixIcon}"></i> {txtSuffix}</button></div></div></div>',
            '<div class="{cssPrefix}">{txtPrefix}</div>',
            '<div class="{cssInputGroup}"></div>',
            '<input class="form-control" readOnly="readOnly" />'
        ],
        defaults: {
            readOnly: true,
            cssCol: 'col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sg-3 col-mg-2',
            cssFormGroupSize: 'form-group-sm',
            cssLabel: '',
            txtLabel: '',
            cssInputGroup:'',
            cssInputGroupSize: 'input-group-sm',
            cssPrefix: "",
            cssPrefixIcon: "",
            txtPrefix: "",
            cssSuffix: "input-group-btn",
            txtSuffix: "",
            cssSuffixBtn: "btn-default",
            cssSuffixIcon: "fa fa-search",
            itemLabel: 'label',
            itemValue: 'value',
            _before: function(options){
                var that = options;
                if(!that.cssPrefix)
                    that.cssPrefix = Boolean(that.cssPrefixIcon || that.txtPrefix) ? 'input-group-addon' : 'hide';
                if(that.cssShowMaxHeight == 'auto' && ((that.showItemHeight | 0) * (that.showItemNum | 0)) > 0 ) that.cssShowMaxHeight = (that.showItemHeight | 0) * (that.showItemNum | 0);

                that.cssShowMaxHeight = that.cssShowMaxHeight + 10 + "px";
            },
            before: function(options){
                options._before.call(this, options);
                //add other call js
            },
            _after: function(options){},
            after: function(options){
                options._after.call(this, options);
                //add other call js
            },
            events: {
                btnClick: {
                    data: {},
                    handler: function (event) {
                        var data = event.data;
                    }
                },
                change: {
                    data: {},
                    handler: function(event) {
                        var data = event.data;
                    }
                }
            }
        }
    };

    /**
     * @desc radio wrapper template
     * @param cssCol（可选）bootstrap 四种默认样式 格式为col-*-* 默认值：col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sg-3 col-mg-2
     * @param cssSize (可选) bootstrap 大小 默认form-group-sm 注意：此地方没有实际作用
     * @param txtLabel (可选) label text
     * @param cssPreix (可选) prefix class
     * @param txtPrefix (可选) prefix text
     * @param cssPrefixIcon (可选) bootstrap 图标样式 一般用于后缀
     * @param cssSuffix （可选） suffix class 默认 input-group-btn
     * @param cssSuffixBtn (可选) 默认 btn-default
     * @param cssSuffixIcon (可选) 默认  caret
     * @param txtSuffix (可选) 后缀文本
     * @param cssInputGroupSize (可选) 前缀的大小 默认input-group-sm
     * @param cssBtnGroupSize (可选) 按钮组的大小 默认btn-group-sm
     * @param cssBtn (可选) 按钮统一样式 默认btn-default
     * @param cssActive (可选) 按钮选中样式
     * @param itemLable (可选) item display label 默认 label
     * @param itemValue (可选) item realy value 默认 value
     * @param items (与 remote,code 三选一) static items array
     * @param remote(与 items,code 三选一) {@param url (必选）, @param: type（必选）, @param callback (可重写) 返回items}
     * * @param code (与 items，remote 三选一)
     * {
     *  @param type （必选）分组列表
     *  @param group (可选) 分组名称 默认__defaultGroup
     *  @param itemLabel 同上itemLabel, 默认codeName, 以该字段为准
     *  @param itemValue 同上itemValue, 默认code, 以该字段为准
     *  @param enable (可选)状态设置
     *  {
     *      @param status (可选) 默认ture 是否启用状态数据过滤
     *      @param key (可选) 默认isEnable 过滤数据属性名
     *  }
     *  @param callback 回调函数
     * }
     * @param _before(不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param before (可重写) 建议调用之前执行 options._before.call(this, options);
     * @param _after (不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param after (可重写) 默认回调函数 options._after.call(this, options);
     **/
    templates.radioGroup = {
        template: [
            '<div class="{cssCol}"><div class="form-group ui-radio-group"><label class="control-label {cssLabel}">{txtLabel}</label><span class="target"></span><div class="input-group {cssInputGroupSize}"><div class="btn-group {cssBtnGroupSize}"></div></div></div></div>',
            '<div class="{cssPrefix}">{txtPrefix}</div>',
            '<a class="btn {cssBtn} fa {cssBtnIcon}" href="javascript:;" value="{txtValue}">&nbsp;{txtLabel}</a>',
            '<div class="{cssInputGroup}"></div>'
        ],
        defaults: {
            cssCol: 'col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sg-3 col-mg-2',
            cssFormGroupSize: 'form-group-sm',
            cssBtnGroupSize: 'btn-group-sm',
            cssLabel: '',
            txtLabel: '',
            cssPrefix: "",
            cssPrefixIcon: "",
            txtPrefix: "",
            cssSuffix: "",
            txtSuffix: "",
            cssSuffixIcon: "",
            cssInputGroup: '',
            cssInputGroupSize: 'input-group-sm',
            cssBtnIcon: 'fa-circle-o,fa-dot-circle-o',
            cssBtn: 'btn-default',
            cssActive: 'active',
            itemLabel: 'label',
            itemValue: 'value',
            items: null,// items:[{label: "", value: ""}]
            remote:{
                url: "",
                type: "POST",
                cache: false,
                data: {},
                root: 'list',
                callback: function(items){
                    return items;
                }
            },
            code: {
                type: '',
                group: '__defaultGroup',
                itemLabel: 'codeName',
                itemValue: 'code',
                enable:{
                    status: true,
                    key: 'isEnable'
                },
                callback: function (items) {
                    return items;
                }
            },
            _before: function(options){
                var that = options;
                if(!that.cssPrefix)
                    that.cssPrefix = Boolean(that.cssPrefixIcon || that.txtPrefix) ? 'input-group-addon' : 'hide';
            },
            before: function(options){
                options._before.call(this, options);
                //add other call js
            },
            _after: function(options){},
            after: function(options){
                options._after.call(this, options);
                //add other call js
            },
            events: {
                change: {
                    data: {},
                    handler: function(event, value, item) {
                        var data = event.data;
                    }
                }
            }
        }
    };

    /**
     * @desc checkbox wrapper template
     * @param cssCol（可选）bootstrap 四种默认样式 格式为col-*-* 默认值：col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sg-3 col-mg-2
     * @param cssFormGroupSize (可选) bootstrap 大小 默认form-group-sm 注意：此地方没有实际作用
     * @param txtLabel (可选) label text
     * @param cssPreix (可选) prefix class
     * @param txtPrefix (可选) prefix text
     * @param cssPrefixIcon (可选) bootstrap 图标样式 一般用于后缀
     * @param cssSuffix （可选） suffix class 默认 input-group-btn
     * @param cssSuffixBtn (可选) 默认 btn-default
     * @param cssSuffixIcon (可选) 默认  caret
     * @param txtSuffix (可选) 后缀文本
     * @param cssInputGroupSize (可选) 前缀的大小 默认input-group-sm
     * @param cssBtnGroupSize (可选) 按钮组的大小 默认btn-group-sm
     * @param cssBtn (可选) 按钮统一样式 默认btn-default
     * @param cssActive (可选) 按钮选中样式
     * @param itemLable (可选) item display label 默认 label
     * @param itemValue (可选) item realy value 默认 value
     * @param itemValueList (可选) value limit 默认 “,”
     * @param items (与 remote, code 三选一) static items array
     * @param remote(与 items, code 三选一) {@param url (必选）, @param: type（必选）, @param callback (可重写) 返回items}
     * * @param code (与 items，remote 三选一)
     * {
     *  @param type （必选）分组列表
     *  @param group (可选) 分组名称 默认__defaultGroup
     *  @param itemLabel 同上itemLabel, 默认codeName, 以该字段为准
     *  @param itemValue 同上itemValue, 默认code, 以该字段为准
     *  @param enable (可选)状态设置
     *  {
     *      @param status (可选) 默认ture 是否启用状态数据过滤
     *      @param key (可选) 默认isEnable 过滤数据属性名
     *  }
     *  @param callback 回调函数
     * }
     * @param _before(不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param before (可重写) 建议调用之前执行 options._before.call(this, options);
     * @param _after (不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param after (可重写) 默认回调函数 options._after.call(this, options);
     * @param events (可选)事件注册
     * {
     *  @param change (可选) 值改变事件
     *  {
     *   @param data (可选) 参数，绑定参数 在回调事件中调用
     *   @param handler (可重写) 事件回调函数 handler 获取参数用 event.data
     *  }
     * }
     **/
    templates.checkboxGroup = {
        template: [
            '<div class="{cssCol}"><div class="form-group ui-checkbox-group"><label class="control-label {cssLabel}">{txtLabel}</label><span class="target"></span><div class="input-group {cssInputGroupSize}"><div class="btn-group {cssBtnGroupSize}"></div></div></div></div>',
            '<div class="{cssPrefix}">{txtPrefix}</div>',
            '<a class="btn {cssBtn} fa {cssBtnIcon}" href="javascript:;" value="{txtValue}">&nbsp; {txtLabel}</a>',
            '<div class="{cssInputGroup}"></div>'
        ],
        defaults: {
            cssCol: 'col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sg-3 col-mg-2',
            cssFormGroupSize: 'form-group-sm',
            cssLabel: '',
            txtLabel: '',
            cssPrefix: "",
            cssPrefixIcon: "",
            txtPrefix: "",
            cssSuffix: "",
            txtSuffix: "",
            cssSuffixIcon: "",
            cssInputGroup: '',
            cssInputGroupSize: 'input-group-sm',
            cssBtnGroupSize: 'btn-group-sm',
            cssBtnIcon: 'fa-square-o,fa-check-square-o',
            cssBtn: 'btn-default',
            cssActive: 'active',
            itemLabel: 'label',
            itemValue: 'value',
            itemValueLimit: ',',
            code: {
                type: '',
                group: '__defaultGroup',
                itemLabel: 'codeName',
                itemValue: 'code',
                enable:{
                    status: true,
                    key: 'isEnable'
                },
                callback: function (items) {
                    return items;
                }
            },
            items: null,// items:[{label: "", value: ""}]
            remote:{
                url: "",
                type: "",
                root: 'list',
                cache: false,
                callback: function(items){
                    return items;
                }
            },
            _before: function(options){
                var that = options;
                if(!that.cssPrefix)
                    that.cssPrefix = Boolean(that.cssPrefixIcon || that.txtPrefix) ? 'input-group-addon' : 'hide';
            },
            before: function(options){
                options._before.call(this, options);
                //add other call js
            },
            _after: function(options){},
            after: function(options){
                options._after.call(this, options);
                //add other call js
            },
            events: {
                change: {
                    data: {},
                    handler: function(event, values, items) {
                        var data = event.data;
                    }
                }
            }
        }
    };

    /**
     * @desc select wrapper template
     * @param cssCol（可选）bootstrap 四种默认样式 格式为col-*-* 默认值：col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sg-3 col-mg-2
     * @param cssFormGroupSize (可选) bootstrap 大小 默认form-group-sm 注意：此地方没有实际作用
     * @param txtLabel (可选) label text
     * @param cssInputGroupSize (可选) 按钮组的大小 默认btn-group-sm
     * @param cssActive (可选) 按钮选中样式
     * @param cssPreix (可选) prefix class
     * @param txtPrefix (可选) prefix text
     * @param cssPrefixIcon (可选) bootstrap 图标样式 一般用于后缀
     * @param cssSuffix （可选） suffix class 默认 input-group-btn
     * @param cssSuffixBtn (可选) 默认 btn-default
     * @param cssSuffixIcon (可选) 默认  caret
     * @param txtSuffix (可选) 后缀文本
     * @param itemLable (可选) item display label 默认 label
     * @param itemValue (可选) item realy value 默认 value
     * @param showItemNum (可选) 显示最大的数量， 超过出现滚动条 默认5
     * @param showItemHeight (可选) 每项的高度 默认26
     * @param showStyleMenu (可选) dorpmenu 的style样式
     * @param cssShowMaxHeight (可选) 设置该值（不等于auto）就放弃了showItemNum * showItemHeight 设定 默认auto, 高度来至于showItemNum * showItemHeight
     * @param itemValueList (可选) value limit 默认 “,”
     * @param items (与 remote，code 三选一) static items array
     * @param remote(与 items，code 三选一) {@param url (必选）, @param: type（必选）, @param callback (可重写) 返回items}
     * @param code (与 items，remote 三选一)
     * {
     *  @param type （必选）分组列表
     *  @param group (可选) 分组名称 默认__defaultGroup
     *  @param itemLabel 同上itemLabel, 默认codeName, 以该字段为准
     *  @param itemValue 同上itemValue, 默认code, 以该字段为准
     *  @param enable (可选)状态设置
     *  {
     *      @param status (可选) 默认ture 是否启用状态数据过滤
     *      @param key (可选) 默认isEnable 过滤数据属性名
     *  }
     *  @param callback 回调函数
     * }
     * @param _before(不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param before (可重写) 建议调用之前执行 options._before.call(this, options);
     * @param _after (不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param after (可重写) 默认回调函数 options._after.call(this, options);
     * @param events (可选)事件注册
     * {
     *  @param change (可选) 值改变事件
     *  {
     *   @param data (可选) 参数，绑定参数 在回调事件中调用
     *   @param handler (可重写) 事件回调函数 handler 获取参数用 event.data
     *  }
     * }
     **/
    templates.selectGroup = {
        template: [
            '<div class="{cssCol}"><div class="form-group ui-select-group"><label class="control-label {cssLabel}">{txtLabel}</label><span class="target"></span><div class="input-group {cssInputGroupSize}"><input type="text" class="form-control" readonly/><div class="{cssSuffix}"><button value="" type="button" class="btn {cssSuffixBtn} dropdown-toggle" data-toggle="dropdown"><i class="{cssSuffixIcon}"></i> {txtSuffix}</button><ul class="dropdown-menu dropdown-menu-right" style="max-height:{cssShowMaxHeight};overflow-y:auto;{showStyleMenu}"></ul></div></div></div>',
            '<div class="{cssPrefix}">{txtPrefix}</div>',
            '<div class="{cssSuffix}"><i class="{cssSuffixIcon}"></i>{txtSuffix}</div>',
            '<li class="{cssItemActive}" value="{txtValue}"><a href="javascript:;">{txtLabel}</a></li>',
            '<div class="{cssInputGroup}"></div>'
        ],
        defaults: {
            cssCol: 'col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sg-3 col-mg-2',
            cssFormGroupSize: 'form-group-sm',
            cssLabel: '',
            txtLabel: '',
            cssInputGroup:'',
            cssInputGroupSize: 'input-group-sm',
            cssPrefix: "",
            cssPrefixIcon: "",
            txtPrefix: "",
            cssSuffix: "input-group-btn",
            txtSuffix: "",
            cssSuffixBtn: "btn-default",
            cssSuffixIcon: "caret",
            cssActive: 'active',
            itemLabel: 'label',
            itemValue: 'value',
            showItemNum: 5,
            showItemHeight: 26,
            showStyleMenu: "",
            cssShowMaxHeight: "auto",
            cssMultiIcon: 'fa-square-o,fa-check-square-o',
            selectedMulti: false,
            itemValueLimit: ',',
            code: {
                type: '',
                group: '__defaultGroup',
                itemLabel: 'codeName',
                itemValue: 'code',
                enable:{
                    status: true,
                    key: 'isEnable'
                },
                callback: function (items) {
                    return items;
                }
            },
            items: null,// items:[{label: "", value: ""}]
            remote:{
                url: "",
                type: "",
                root: 'list',
                cache: false,
                callback: function(items){
                    return items;
                }
            },
            _before: function(options){
                var that = options;
                if(!that.cssPrefix)
                    that.cssPrefix = Boolean(that.cssPrefixIcon || that.txtPrefix) ? 'input-group-addon' : 'hide';
                if(that.cssShowMaxHeight == 'auto' && ((that.showItemHeight | 0) * (that.showItemNum | 0)) > 0 ) that.cssShowMaxHeight = (that.showItemHeight | 0) * (that.showItemNum | 0);

                that.cssShowMaxHeight = that.cssShowMaxHeight + 10 + "px";
            },
            before: function(options){
                options._before.call(this, options);
                //add other call js
            },
            _after: function(options){},
            after: function(options){
                options._after.call(this, options);
                //add other call js
            },
            events: {
                change: {
                    data: {},
                    handler: function(event) {
                        var data = event.data;
                    }
                }
            }
        }
    };

    /**
     * @desc selectArea wrapper template
     * @param cssCol（可选）bootstrap 四种默认样式 格式为col-*-* 默认值：col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sg-3 col-mg-2
     * @param cssFormGroupSize (可选) bootstrap 大小 默认form-group-sm 注意：此地方没有实际作用
     * @param txtLabel (可选) label text
     * @param cssInputGroupSize (可选) 按钮组的大小 默认btn-group-sm
     * @param cssActive (可选) 按钮选中样式
     * @param cssPreix (可选) prefix class
     * @param txtPrefix (可选) prefix text
     * @param cssPrefixIcon (可选) bootstrap 图标样式 一般用于后缀
     * @param cssSuffix （可选） suffix class 默认 input-group-btn
     * @param cssSuffixBtn (可选) 默认 btn-default
     * @param cssSuffixIcon (可选) 默认  caret
     * @param txtSuffix (可选) 后缀文本
     * @param itemLable (可选) item display label 默认 label
     * @param itemValue (可选) item realy value 默认 value
     * @param itemValueList (必选) value limit 默认 “-”
     * @param itemChildren (可选) item realy children 默认 children
     * @param showChildrenLevel (可选) 级联级别 默认3
     * @param showChildrenLabel (可选) 级联提示字符串 默认：请选择...
     * @param showItemNum (可选) 显示最大的数量， 超过出现滚动条 默认5
     * @param showItemHeight (可选) 每项的高度 默认26
     * @param showStyleMenu (可选) dorpmenu 的style样式
     * @param levelItemValueHide (可选) 是否隐藏（不生成）级联input name 提交文本input
     * @param levelItemValueNames (levelItemValueHide 为false 必选) input name
     * @param cssShowMaxHeight (可选) 设置该值（不等于auto）就放弃了showItemNum * showItemHeight 设定 默认auto, 高度来至于showItemNum * showItemHeight
     * @param items (与 remote，code 三选一) static items array
     * @param remote(与 items，code 三选一)
     * {
     *  @param url (必选）
     *  @param: type（必选）
     *  @param isNUll 是否允许没有下级节点 默认true 允许
     *  @param callback (可重写) 返回items
     * }
     *
     * @param code (与 items，remote 三选一)
     * {
     *  @param type （必选）分组列表
     *  @param isNUll 是否允许没有下级节点 默认false 不允许， 请注意设定true,异步节点加载数据不会实现
     *  @param callback 回调函数
     * }
     *
     * @param _before(不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param before (可重写) 建议调用之前执行 options._before.call(this, options);
     * @param _after (不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param after (可重写) 默认回调函数 options._after.call(this, options);
     * @param events (可选)事件注册
     * {
     *  @param change (可选) 值改变事件
     *  {
     *   @param data (可选) 参数，绑定参数 在回调事件中调用
     *   @param handler (可重写) 事件回调函数 handler 获取参数用 event.data
     *  }
     * }
     **/
    templates.selectAreaGroup = {
        template: [
            '<div class="{cssCol}"><div class="form-group"><label class="control-label {cssLabel}">{txtLabel}</label><span class="target"></span><div class="input-group {cssInputGroupSize}"></div></div></div>',
            '<div class="{cssPrefix}">{txtPrefix}</div>',
            '<input type="text" class="form-control" placeholder="{placeholder}" readonly/><div class="input-group-btn"><button class="btn {cssSuffixBtn} dropdown-toggle" data-toggle="dropdown"><i class="{cssSuffixIcon}"></i> {txtSuffix}</button><ul class="dropdown-menu dropdown-menu-right" style="overflow-y: auto;max-height: {cssShowMaxHeight};{showStyleMenu}"></ul></div>',
            '<li class="{cssItemActive}" value="{txtValue}"><a href="javascript:;">{txtLabel}</a></li>',
            '<div class="{cssInputGroup}"></div>',
        ],
        defaults: {
            cssCol: 'col-xs-9 col-sm-7 col-md-6 col-lg-5',
            cssFormGroupSize: 'form-group-sm',
            cssLabel: '',
            txtLabel: '',
            cssInputGroup: '',
            cssInputGroupSize: 'input-group-sm',
            cssPrefix: "",
            cssPrefixIcon: "",
            txtPrefix: "",
            cssSuffix: "input-group-btn",
            txtSuffix: "",
            cssSuffixBtn: "btn-default",
            cssSuffixIcon: "caret",
            cssActive: 'active',
            itemLabel: 'label',
            itemValue: 'value',
            itemValueLimit: ',',
            itemChildren: 'children',
            requiredLevel: 'all',
            showChildrenLevel: 3,
            showChildrenLabels: ["请选择...", "请选择...", "请选择..."],
            showItemNum: 5,
            showItemHeight: 26,
            showStyleMenu: "",
            cssShowMaxHeight: "auto",
            levelItemValueHide: true,
            levelItemValueNames: ['name1', 'name2', 'name3'],
            code: {
                type: '',
                isNull: true,
                callback: function (items) {
                    return items;
                }
            },
            items:null, //items: [{ label: 'label', value: 'value', children: [{label: 'label', value: 'value', children: null}]}],
            remote:{
                url: "",
                isNull: false,
                type: "GET",
                cache: false,
                root: 'list',
                callback: function(items){
                    return items;
                }
            },
            _before: function(options){
                var that = options;
                if(!that.cssPrefix)
                    that.cssPrefix = Boolean(that.cssPrefixIcon || that.txtPrefix) ? 'input-group-addon' : 'hide';
                if(that.cssShowMaxHeight == 'auto' && ((that.showItemHeight | 0) * (that.showItemNum | 0)) > 0 ) that.cssShowMaxHeight = (that.showItemHeight | 0) * (that.showItemNum | 0);

                that.cssShowMaxHeight = that.cssShowMaxHeight + 10 + "px";
            },
            before: function(options){
                options._before.call(this, options);
                //add other call js
            },
            _after: function(options){},
            after: function(options){
                options._after.call(this, options);
                //add other call js
            },
            events: {
                change: {
                    data: {},
                    handler: function(event, values, items) {
                        var data = event.data;
                    }
                }
            }
        }
    };

    /**
     * @desc selectTree wrapper template
     * @param cssCol（可选）bootstrap 四种默认样式 格式为col-*-* 默认值：col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sg-3 col-mg-2
     * @param cssFormGroupSize (可选) bootstrap 大小 默认form-group-sm 注意：此地方没有实际作用
     * @param txtLabel (可选) label text
     * @param cssInputGroupSize (可选) 按钮组的大小 默认btn-group-sm
     * @param cssActive (可选) 按钮选中样式
     * @param cssPreix (可选) prefix class
     * @param txtPrefix (可选) prefix text
     * @param cssPrefixIcon (可选) bootstrap 图标样式 一般用于后缀
     * @param cssSuffix （可选） suffix class 默认 input-group-btn
     * @param cssSuffixBtn (可选) 默认 btn-default
     * @param cssSuffixIcon (可选) 默认  caret
     * @param txtSuffix (可选) 后缀文本
     * @param itemLable (可选) item display label 默认 label
     * @param itemValue (可选) item realy value 默认 value
     * @param itemValueList (可选) value limit 默认 “,”
     * @param itemValueNames (可选) 生成其他数据input标签，以便获取或保存数据 格式 ['NAME1: ID1', 'NAME2: ID2']
     * @param showItemNum (可选) 显示最大的数量， 超过出现滚动条 默认5
     * @param showItemHeight (可选) 每项的高度 默认26
     * @param showStyleMenu (可选) dorpmenu 的style样式
     * @param cssShowMaxHeight (可选) 设置该值（不等于auto）就放弃了showItemNum * showItemHeight 设定 默认auto, 高度来至于showItemNum * showItemHeight
     * @param items (与 remote，code 三选一) static items array
     * @param remote(与 items，code 三选一) {@param url (必选）, @param: type（必选）, @param callback (可重写) 返回items}
     * @param code (与 items，remote 三选一)
     * {
     *  @param type （必选）分组列表
     *  @param group (可选) 分组名称 默认__defaultGroup
     *  @param itemLabel 同上itemLabel, 默认codeName, 以该字段为准
     *  @param itemValue 同上itemValue, 默认code, 以该字段为准
     *  @param enable (可选)状态设置
     *  {
     *      @param status (可选) 默认ture 是否启用状态数据过滤
     *      @param key (可选) 默认isEnable 过滤数据属性名
     *  }
     *  @param callback 回调函数
     * }
     * @param _before(不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param before (可重写) 建议调用之前执行 options._before.call(this, options);
     * @param _after (不可重写) 内部执行补丁函数 或者 内部预留函数
     * @param after (可重写) 默认回调函数 options._after.call(this, options);
     * @param events (可选)事件注册
     * {
     *  @param change (可选) 值改变事件
     *  {
     *   @param data (可选) 参数，绑定参数 在回调事件中调用
     *   @param handler (可重写) 事件回调函数 handler 获取参数用 event.data
     *  }
     * }
     **/
    templates.selectTreeGroup = {
        template: [
            '<div class="{cssCol}"><div class="form-group ui-selecttree-group"><label class="control-label {cssLabel}">{txtLabel}</label><span class="target"></span><div class="input-group {cssInputGroupSize}"><input type="text" class="form-control" readonly/><div class="{cssSuffix}"><button value="" type="button" class="btn {cssSuffixBtn} dropdown-toggle" data-toggle="dropdown"><i class="{cssSuffixIcon}"></i> {txtSuffix}</button><div class="dropdown-menu dropdown-menu-right" style="max-width:{cssShowMaxWidth};max-height:{cssShowMaxHeight};overflow-y:auto;{showStyleMenu}"><div class="ul-select-tree-container"><ul class="ui-tree"></ul></div></div></div></div></div>',
            '<div class="{cssPrefix}">{txtPrefix}</div>',
            '<div class="{cssSuffix}"><i class="{cssSuffixIcon}"></i>{txtSuffix}</div>',
            '<li class="{cssItemActive}" value="{txtValue}"><a href="javascript:;">{txtLabel}</a></li>',
            '<div class="{cssInputGroup}"></div>'
        ],
        defaults: {
            cssCol: 'col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sg-3 col-mg-2',
            cssFormGroupSize: 'form-group-sm',
            cssLabel: '',
            txtLabel: '',
            cssInputGroup:'',
            cssInputGroupSize: 'input-group-sm',
            cssPrefix: "",
            cssPrefixIcon: "",
            txtPrefix: "",
            cssSuffix: "input-group-btn",
            txtSuffix: "",
            cssSuffixBtn: "btn-default",
            cssSuffixIcon: "caret",
            cssActive: 'active',
            cssShowMaxWidth: "auto",
            cssShowMaxHeight: "300px",
            itemLabel: 'label',
            itemValue: 'value',
            itemValueLimit: ',',
            itemValueNames: [],
            items: null,// items:[{label: "", value: ""}]
            code: {
                type: '',
                group: '__defaultGroup',
                itemLabel: 'codeName',
                itemValue: 'code',
                enable:{
                    status: true,
                    key: 'isEnable'
                },
                callback: function (items) {
                    return items;
                }
            },
            icons: {
                asycnIcon: 'fa-spinner',
                treeIcon: 'fa-folder,fa-folder-open',
                leafIcon: 'fa-file',
                hideIcon: 'fa-plus-square',
                showIcon: 'fa-minus-square',
                checkIcon: 'fa-square-o,fa-check-square-o,fa-check-square'
            },
            show: {
                level: 1,
                multi: true,
                checkbox: true,
                autoLoad: true,//自动加载
                relCheck: false,//关联选择
                checked: {
                    enable: false,
                    key: 'isChecked',
                    handler: function (val, item) {
                        return Boolean(val);
                    }
                }
            },
            data: {
                pid: 'pid',
                id: 'id',
                name: 'name',
                children: 'children',
                isLeaf: 'isLeaf',
                icon: ''
            },
            items: null,
            remote: {
                url: '',
                type: 'POST',
                root: 'list',
                autoParam: ["id"],
                otherParam: {},
                callback: function (items) {
                    return items;
                }
            },
            css: {
                fontSize: '14px',
                activeFontColor: '#459c98',
                fontColor: '#3f3f3f',
                plusIconColor: '#989898',
                checkboxIconColor: '#aaa',
                nodeIconColor: '#bebebe'
            },
            _before: function(options){
                var that = options;
                if(!that.cssPrefix)
                    that.cssPrefix = Boolean(that.cssPrefixIcon || that.txtPrefix) ? 'input-group-addon' : 'hide';

            },
            before: function(options){
                options._before.call(this, options);
                //add other call js
            },
            _after: function(options){},
            after: function(options){
                options._after.call(this, options);
                //add other call js
            },
            events: {
                click: {
                    data:{},
                    handler: function (event, value, item) {

                    }
                },
                change: {
                    data: {},
                    handler: function(event, values , items) {
                        var data = event.data;
                    }
                }
            }
        }
    };

    /**
     * @desc Tree列表
     * @parma
     **/
    templates.treeGroup = {
        template: [],
        defaults: {
            icons: {
                asycnIcon: 'fa-spinner',
                treeIcon: 'fa-folder,fa-folder-open',
                leafIcon: 'fa-file',
                hideIcon: 'fa-plus-square',
                showIcon: 'fa-minus-square',
                checkIcon: 'fa-square-o,fa-check-square-o,fa-check-square'
            },
            show: {
                level: 1,
                multi: true,
                checkbox: true,
                autoLoad: true,//自动加载
                relCheck: false,//关联选择
                checked: {
                    enable: false,
                    key: 'isChecked',
                    handler: function (val, item) {
                        return Boolean(val);
                    }
                }
            },
            data: {
                pid: 'pid',
                id: 'id',
                name: 'name',
                children: 'children',
                isLeaf: 'isLeaf',
                icon: ''
            },
            items: null,
            selectedIds: [],
            remote: {
                url: '',
                type: 'POST',
                root: 'list',
                selectedIds: 'selectedIds',
                autoParam: ["id"],
                otherParam: {},
                callback: function (items) {
                    return items;
                }
            },
            css: {
                fontSize: '14px',
                activeFontColor: '#459c98',
                fontColor: '#3f3f3f',
                plusIconColor: '#989898',
                checkboxIconColor: '#aaa',
                nodeIconColor: '#bebebe'
            },
            events: {
                click: {
                    data:{},
                    handler: function (event, value, item) {

                    }
                },
                change: {
                    data: {},
                    handler: function(event, values , items) {
                        var data = event.data;
                    }
                }
            }
        }
    };

    /**
     * @desc Grid列表
     * @param width
     * @param cols
     * {
     *  @param title
     *  @param name
     *  @param align
     *  @param renderer
     *      function (val, item, rowIndex)
     *      @param val
     *      @param item
     *      @param rowIndex
     * }
     * @param url
     * @param method
     * @param cache
     * @param root
     * @param root
     * @param items
     * @param autoLoad
     * @param loadingText
     * @param noDataText
     * @param loadErrorText
     * @param multiSElect
     * @param checkCol
     * @param indexCol
     * @param indexColWidth
     * @param fullWidthRows
     * @param wordBreakNum 字段截取显示， 默认15
     * @param nowrap
     * @param plulgins
     * @param events
     * {
     *  @param loadSuccess
     *       {
     *          @param data
     *          @param handler
     *              function (event, loadData)
     *              @param event
     *              @param loadData
     *       }
     *  @param loadError
     *       {
     *          @param data
     *          @param handler
     *              function (event, loadData)
     *              @param event
     *              @param loadData
     *       }
     *  @param layoutFinished
     *       {
     *          @param data
     *          @param handler
     *              function (event, loadData)
     *              @param event
     *              @param loadData
     *       }
     *  @param cellClick
     *       {
     *          @param data
     *          @param handler
     *              function (event, loadData)
     *              @param event
     *              @param loadData
     *       }
     * }
     */
    appui.templates.gridGroup = {
        template: [],
        defaults: {
            height: "350px",
            width: 'auto'
            , cols: []
            , url: false
            , params: {}
            , method: 'POST'
            , cache: false
            , root: 'list'
            , items: []
            , autoLoad: true
            , loadingText: '数据加载中...'
            , noDataText: false
            , loadErrorText: false
            , multiSelect: false
            , checkCol: false
            , indexCol: false
            , indexColWidth: 50
            , fullWidthRows: false
            , wordBreakNum: 15
            , checked:{
                enable: false,
                key: 'isChecked',
                handler: function (val, item, rowIndex) {
                    return Boolean(val);
                }
            }
            , nowrap: false
            , plugins: [] //插件 插件必须实现 init($groupGrid)和params()方法，参考GridGroupPaginator
            , events: {
                loadSuccess: {
                    data: {},
                    handler: function (event, loadData) {
                        var data = event.data;
                    }
                },
                loadError: {
                    data: {},
                    handler: function (event, loadData) {
                    }
                },
                layoutFinished: {
                    data: {},
                    handler: function (event, loadData) {
                    }
                },
                cellClick: {
                    data: {},
                    handler: function (event, item, isSelected, rowIndex, colIndex) {
                    }
                },
                rowInserted: {
                    data: {},
                    handler: function (event,item, rowIndex){
                    }
                },
                rowUpdated: {
                    data: {},
                    handler: function (event, oldItem, newItem, rowIndex) {
                    }
                },
                rowRemoved: {
                    data: {},
                    handler: function (event, item, rowIndex){
                    }
                }
            }
            , customEvents: [
                /*{
                 target: '.add',
                 data: {},
                 handler: function () {}
                 }*/
            ]
        }
    };

    /**
     * @desc grid列表分页插件
     * @param style
     * @Param page
     * @param pageParamName
     * @param limitParamName
     * @param limitLabel
     * @param totalCountLabel
     * @param limit
     * @param limitList
     */
    appui.templates.gridGroupPaginator = {
        template: [],
        defaults: {
            style: 'plain'
            , totalCountName: 'totalSize'
            , page: 1
            , pageParamName: 'pageNo'
            , limitParamName: 'pageSize'
            , limitLabel: '每页{0}条'
            , totalCountLabel: '当前显示 {1} 到 {2} 条，共 {0} 条记录 '
            , limit: undefined
            , limitList: [10, 20, 30, 40, 50]
        }
    };

    /**
     * @desc grid列表数据过滤插件
     * @param queryBtn
     * @param resetBtn
     * @param param
     */
    appui.templates.gridGroupQuery = {
        template: [],
        defaults: {
            queryBtn: "#queryBtn",
            resetBtn: "#restBtn",
            param:"params[{0}]"
        }
    };

    $.extend(appui.templates, templates);

})(jQuery, appui);
