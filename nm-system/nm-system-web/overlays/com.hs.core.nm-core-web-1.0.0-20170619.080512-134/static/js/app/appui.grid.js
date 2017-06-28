/**
 * Created by Mifeng.He(bee) on 2015/9/22.
 */
(function($, appui){

    var GridGroup = function (element, options){
        this.browser = function(){
            var isIE=!!window.ActiveXObject;
            var isIE10 = isIE && !!window.atob;
            var isIE9 = isIE && document.addEventListener && !window.atob;
            var isIE8 = isIE && document.querySelector && !document.addEventListener;
            var isIE7 = isIE && window.XMLHttpRequest && !document.querySelector;
            var isIE6 = isIE && !window.XMLHttpRequest;

            return {
                isIE: isIE
                , isIE6: isIE6
                , isIE7: isIE7
                , isIE8: isIE8
                , isIE9: isIE9
                , isIE10: isIE10
            };
        }();
        this._id = (((1 + Math.random()) * 0x10000) | 0).toString(16);
        this._loadCount = 0;
        this.opts = options;
        this._initLayout($(element));
        this._initHead();
        this._initOptions();
        this._initEvents();

        //初始化插件
        for(var i=0; i< this.opts.plugins.length; i++){
            var plugin = this.opts.plugins[i];
            plugin.init($.extend(element, this));
        }


        if(options.autoLoad){
            var that = this;
            this.opts = options;
            setTimeout(function(){

                if(options.url){
                    that.load();
                }else{
                    that.load(options.items);
                }
            },0); //chrome style problem
        }
    };

    GridGroup.prototype = {
        _initLayout: function($table){
            var opts = this.opts;
            this.$table = $table;

            this.$table.wrap('<div class="grid-group-container" style="min-height:' + opts.height + '; overflow: visible;"></div>');
            this.$loading = $('<div class="grid-group-loading" style="display: none;"><div class="grid-group-loading-bg"></div><div class="grid-group-loading-title"><i class="fa fa-spinner fa-pulse fa-1x"></i> ' + opts.loadingText + '</div></div>').insertBefore(this.$table);
            this.$table.addClass('grid-group')
                .addClass('table')
                .addClass('table-hover');

            this.$thead = $('<thead></thead>').appendTo($table);
            this.$tbody = $('<tbody></tbody>').appendTo($table);

            this.$tfoot = $('<tfoot></tfoot>').appendTo($table);

            if(opts.checkCol){
                var chkHtml = opts.multiSelect ?  '<input type="checkbox" class="checkAll" >' : '<input type="checkbox" disabled="disabled" class="checkAll">';

                var renderer = function (val) {
                    return '<input type="checkbox" class="gridGroup-check">';
                };

                if(opts.checked.enable) renderer = function (val, item, rowIndex) {
                    return opts.checked.handler(val, item, rowIndex) ? '<input type="checkbox" class="gridGroup-check" checked="checked">' : '<input type="checkbox" class="gridGroup-check">';
                };

                opts.cols.unshift({title:chkHtml, name: opts.checked.key, width: 30, align: 'center' ,lockWidth: true, checkCol: true, renderer: renderer});

            }

            if(opts.indexCol){
                opts.cols.unshift({title:'#',width: opts.indexColWidth, align: 'center' ,lockWidth: true, indexCol:true, renderer: function (val, item, rowIndex) {
                    return '<label class="gridGroup-index">' + (rowIndex+1) + '</label>';
                }});
            }

        }
        ,_initHead: function(){
            var that = this;
            var opts = this.opts;
            //var $table = this.$table;
            var $thead = this.$thead;

            if(opts.cols){
                var theadHtmls = [];

                //获取标题深度
                var titleDeep = that._titleDeep(opts.cols);
                for(var deep=1; deep<= titleDeep; deep++){
                    var cols = that._colsWithTitleDeep(opts.cols, deep);
                    theadHtmls.push('<tr>');
                    for(var colIndex=0; colIndex< cols.length; colIndex++){
                        var col = cols[colIndex];
                        theadHtmls.push(this._titleHtml(col, titleDeep-deep+1));
                    }
                    theadHtmls.push('</tr>');
                }
                $thead.html(theadHtmls.join(''));
            }
            var $ths = this._expandThs();
            var expandCols = this._expandCols(opts.cols);
            $.each($ths,function(index){
                if(!expandCols[index].width){
                    expandCols[index].width = 100;
                }
                $.data(this,'col-width',expandCols[index].width);
                $.data(this,'col',expandCols[index]);
            });


            if(opts.height !== 'auto'){
                //设定高度
            }

            //初始化排序状态
            /* if(opts.sortName){
             for(var colIndex=0; colIndex< expandCols.length; colIndex++){
             var col = expandCols[colIndex];
             if(col.sortName === opts.sortName || col.name === opts.sortName){
             var $th= $ths.eq(colIndex);
             $.data($th.find('.gridGroup-title')[0],'sortStatus',opts.sortStatus);
             $th.find('.gridGroup-sort').addClass('gridGroup-'+opts.sortStatus);
             }
             }
             }*/
        }
        , _initOptions: function(){
            var opts = this.opts;

        }

        , _loadAjax: function(args){
            var that = this;
            var opts = this.opts;
            var params = {};
            //opt的params可以使函数，例如收集过滤的参数
            if($.isFunction(opts.params)){
                var p = opts.params();
                if(!p){
                    return;
                }
                params = $.extend(params, p);
            }else if($.isPlainObject(opts.params)){
                params = $.extend(params, opts.params);
            }



            //插件参数合并
            var pluginParams = {};
            for(var i=0; i< this.opts.plugins.length; i++){
                var plugin = this.opts.plugins[i];
                $.extend(pluginParams, plugin.params());
            }
            params = $.extend(params, pluginParams);

            //合并load的参数
            params = $.extend(params, args);

            that._showLoading();
            $.ajax({
                type: opts.method,
                url: opts.url,
                data: params,
                dataType: 'json',
                cache: opts.cache
            }).done(function(data){
                try{
                	
                    //插件参数加载
                    var pluginParams = {};
                    for(var i=0; i< opts.plugins.length; i++){
                        var plugin = opts.plugins[i];
                        plugin.load( data );
                    }
                	
                    //获得root对象
                    var items = data;
                    if($.isArray(data[opts.root])){
                        items = data[opts.root];
                    }
                    if(!items || items.length == 0){
                        that.$tbody.empty();
                    }
                    
                    that._populate(items);
                    that._hideLoading();

                    if(data && $.isArray(data[opts.root])){
                        data = $.extend(args, data);
                    }
                    
                    that.$table.triggerHandler('loadSuccess', data);


                }catch(e){
                    that._hideLoading();
                    that._showLoadError();
                    throw e;
                }

            }).fail(function(data){
                that._hideLoading();
                that._showLoadError();
                that.$table.triggerHandler('loadError', data);
            });

        }

        , _loadNative: function(args){
            this._populate(args);
            //this._refreshSortStatus();
            this.$table.triggerHandler('loadSuccess', args);
        }

        , load: function(args){
            try{
                var opts = this.opts;
                this._hideMessage();
                this._loadCount = this._loadCount + 1 ;

                if($.isArray(args)){
                    //加载本地数据
                    this._loadNative(args);
                }else if(opts.url){
                    this._loadAjax(args);
                }else if(opts.items){
                    this._loadNative(opts.items);
                }else{
                    this._loadNative([]);
                }
            }catch(e){
                this._showLoadError();
                throw e;
            }
        }

        , _showLoading: function(){
            //if(this.opts.loadingText && typeof appui.message.info != 'undefined') appui.message.info(this.opts.loadingText);
            this.$loading.fadeIn();
        }
        , _hideLoading: function(){
            this.$loading.fadeOut();
        }
        , _showNoData: function(){
            if(this.opts.noDataText && typeof appui.message.info != 'undefined') appui.message.info(this.opts.noDataText);
        }

        , _showLoadError: function(){
            if(this.opts.loadErrorText && typeof appui.message.error != 'undefined') appui.message.error(this.opts.loadErrorText);
        }

        , _showMessage: function(msg){
            if(msg && typeof appui.message.info != 'undefined') appui.message.info(msg);
        }
        , _hideMessage: function(){
        }
        , _initEvents: function(){
            var that = this;
            var opts = this.opts;
            var $table = this.$table;
            var $tbody = this.$tbody;
            var $thead = this.$thead;

            $table.bind('loadSuccess', opts.events.loadSuccess.data, opts.events.loadSuccess.handler);
            $table.bind('loadError', opts.events.loadError.data, opts.events.loadError.handler);

            $table.bind('rowInserted', opts.events.rowInserted.data, opts.events.rowInserted.handler);
            $table.bind('rowUpdated', opts.events.rowUpdated.data, opts.events.rowUpdated.handler);
            $table.bind('rowRemoved', opts.events.rowRemoved.data, opts.events.rowRemoved.handler);

            $table.bind('layoutFinished', opts.events.layoutFinished.data, opts.events.layoutFinished.handler);

            $table.bind('cellClick', opts.events.cellClick.data, opts.events.cellClick.handler);


            //选中事件
            $tbody.on('click','td',function(e){
                var $this = $(this);

                var event = jQuery.Event("cellClick");
                event.target = e.target;
                that.$table.triggerHandler(event, [$.data($this.parent()[0], 'item'), !$this.parent().hasClass('active'), $this.parent().index(), $this.index()]);
                /*if(event.isPropagationStopped()){
                    return;
                }*/
                if(!$this.parent().hasClass('active')){
                    that.select($this.parent().index());
                }else{
                    that.deselect($this.parent().index());
                }


            });

            var customEvent = null, ce_selector, ce_type, ce_data, ce_handler;
            for (var i = 0; i < opts.customEvents.length; i++) {
                customEvent = opts.customEvents[i];

                ce_selector = 'tr td ' + customEvent.target;
                ce_type = 'customEvent' + customEvent.target + i;
                ce_data = customEvent.data;
                ce_handler = customEvent.handler;

                $tbody.on(ce_type, ce_selector, ce_data, ce_handler);

                $tbody.on('click.' + ce_type, ce_selector, {name: ce_type, tbody: $tbody}, function (event) {
                    event.preventDefault();
                    event.stopPropagation();
                    var $this = $(this).parents("td:eq(0)");
                    $(this).trigger(event.data.name, [$.data($this.parent()[0], 'item'), $this.parent().index()]);
                    return false;
                });
            }

            $tbody.on('click','tr > td .gridGroup-check',function(e){
                e.stopPropagation();
                var $this = $(this);
                if(this.checked){
                    that.select($($this.parents('tr')[0]).index());
                }else{
                    that.deselect($($this.parents('tr')[0]).index());
                }
            });

            //checkbox列
            if(opts.checkCol){
                $thead.find('th .checkAll').on('click', function(){
                    if(this.checked){
                        that.select('all');
                    }else{
                        that.deselect('all');
                    }
                });
            }

            //IE6不支持hover
            if (that.browser.isIE6){
                $tbody.on('mouseenter','tr', function () {
                    $(this).toggleClass('hover');
                }).on('mouseleave','tr', function () {
                    $(this).toggleClass('hover');
                });
            };


        }
        //选中
        , select: function(args){
            var opts = this.opts;
            var $tbody = this.$tbody;
            var $thead = this.$thead;

            if(typeof args === 'number'){
                var $tr = $tbody.find('tr').eq(args);
                if(!opts.multiSelect){
                    $tbody.find('tr.active').removeClass('active');
                    if(opts.checkCol){
                        $tbody.find('tr > td').find('.gridGroup-check').prop('checked','');
                    }
                }
                if(!$tr.hasClass('active')){
                    $tr.addClass('active');
                    if(opts.checkCol){
                        $tr.find('td .gridGroup-check').prop('checked','checked');
                    }
                }
            }else if(typeof args === 'function'){
                $.each($tbody.find('tr'), function(index){
                    if(args($.data(this, 'item'), index)){
                        var $this = $(this);
                        if(!$this.hasClass('active')){
                            $this.addClass('active');
                            if(opts.checkCol){
                                $this.find('td .gridGroup-check').prop('checked','checked');
                            }
                        }
                    }
                });
            }else if(args === undefined || (typeof args === 'string' && args === 'all')){
                $tbody.find('tr.active').removeClass('active');
                $tbody.find('tr').addClass('active');
                $tbody.find('tr > td').find('.gridGroup-check').prop('checked','checked');
            }else{
                return;
            }

            if(opts.checkCol){
                var $checks = $tbody.find('tr > td').find('.gridGroup-check');
                if($checks.length === $checks.filter(':checked').length){
                    $thead.find('th .checkAll').prop('checked','checked');
                }
            }


        }
        //取消选中
        , deselect: function(args){
            var opts = this.opts;
            var $tbody = this.$tbody;
            var $thead = this.$thead;
            if(typeof args === 'number'){
                $tbody.find('tr').eq(args).removeClass('active');
                if(opts.checkCol){
                    $tbody.find('tr').eq(args).find('td .gridGroup-check').prop('checked','');
                }
            }else if(typeof args === 'function'){
                $.each($tbody.find('tr'), function(index){
                    if(args($.data(this, 'item'), index)){
                        $(this).removeClass('active');
                        if(opts.checkCol){
                            $(this).find('td .gridGroup-check').prop('checked','');
                        }
                    }
                });
            }else if(args === undefined || (typeof args === 'string' && args === 'all')){
                $tbody.find('tr.active').removeClass('active');
                if(opts.checkCol){
                    $tbody.find('tr > td').find('.gridGroup-check').prop('checked','');
                }
            }else{
                return;
            }

            $thead.find('th .checkAll').prop('checked','');

        }

        , selectedRows: function(){
            var $body = this.$tbody;
            var selected = [];
            $.each($body.find('tr.active'), function(index ,item){
                selected.push($.data(this,'item'));
            });
            return selected;
        }

        , selectedRowsIndex: function(){
            var $body = this.$tbody;
            var $trs = this.$tbody.find('tr');
            var selected = [];
            $.each($body.find('tr.active'), function(index){
                selected.push($trs.index(this));
            });
            return selected;
        }

        , rows: function(){
            var $body = this.$tbody;
            var items = [];
            $.each($body.find('tr'), function(){
                items.push($.data(this,'item'));
            });
            return items;
        }

        , row: function(index){
            var $body = this.$tbody;
            if(index !== undefined && index >= 0){
                var $tr = $body.find('tr').eq(index);
                if($tr.length !== 0){
                    return $.data($tr[0],'item');
                }
            }
        }

        , rowsLength: function(){
            var $body = this.$tbody;
            var length = $body.find('tr').length;
            if(length === 1 && $body.find('tr.emptyRow').length === 1){
                return 0;
            }
            return length;
        }
        //添加数据，第一个参数可以为数组
        , addRow: function(item, index){
            var $tbody = this.$tbody;

            if($.isArray(item)){
                for (var i=0; i<item.length; i++){
                	this.addRow(item[i], index);
                }
                return ;
            }

            if(!$.isPlainObject(item)){
                return ;
            }

            //this._hideMessage();
            //this._removeEmptyRow();

            var $tr;

            if(index === undefined || index < 0){
                $tr = $(this._rowHtml(item, this.rowsLength()));
                $tbody.append($tr);
            }else{
                $tr = $(this._rowHtml(item, index));
                if(index === 0){
                    $tbody.prepend($tr);
                }else{
                    var $before = $tbody.find('tr').eq(index-1);
                    //找不到就插到最后
                    if($before.length === 0){
                        $tbody.append($tr);
                    }else{
                        $before.after($($tr));
                    }
                }
            }
            $tr.data('item', item);
            this._colWordBreakTip();
            //this._setStyle();

            this.$table.triggerHandler('rowInserted', [item, index]);
        }
        //更新行内容，两个参数都必填
        , updateRow: function(item, index){
            var opts = this.opts;
            var $tbody = this.$tbody;
            if(!$.isPlainObject(item)){
                return ;
            }
            var oldItem = this.row(index);

            var $tr = $tbody.find('tr').eq(index);
            var checked = $tr.find('td:first :checkbox').is(':checked');
            $tr.html(this._rowHtml(item, index).slice(4,-5));
            if(opts.checkCol){
                $tr.find('td:first :checkbox').prop('checked',checked);
            }

            $tr.data('item', item);
            this._colWordBreakTip();
            //this._setStyle();
            this.$tbody.triggerHandler('rowUpdated', [oldItem, item, index]);
        }

        //删除行，参数可以为索引数组
        , removeRow: function(index){
            var that = this;
            var $tbody = that.$tbody;

            if ($.isArray(index)) {
                for( var i = index.length-1; i >= 0; i-- ){
                    that.removeRow(index[i]);
                }
                return ;
            }

            if (index === undefined){
                var $trs = $tbody.find('tr');
                for(var i=$trs.length-1; i >= 0; i--){
                    that.removeRow(i);
                }
            } else {
                var item = that.row(index);
                $tbody.find('tr').eq(index).remove();
                this.$table.triggerHandler('rowRemoved', [item, index]);
            }
            if(this.rowsLength() === 0){
                this._showNoData();
                this._insertEmptyRow();
            }
        }
        ,_expandCols: function(cols){
            var newCols = [];
            if(!cols){
                return newCols;
            }
            for(var colIndex=0; colIndex<cols.length; colIndex++){
                var col = cols[colIndex];
                if(col.cols){
                    newCols.push(col);
                    newCols.push.apply(newCols,this._expandCols(col.cols));
                }else{
                    newCols.push(col);
                }
            }
            return newCols;
        }
        ,_leafCols: function(){
            var opts = this.opts;
            var newCols = [];
            var cols = this._expandCols(opts.cols);
            for(var colIndex=0; colIndex<cols.length; colIndex++){
                var col = cols[colIndex];
                if(!col.cols){
                    newCols.push(col);
                }
            }
            return newCols;
        }

        ,_expandThs: function(){
            return this.$thead.find('th').sort(function(a, b){
                return parseInt($(a).data('colindex')) - parseInt($(b).data('colindex'));
            });
        }

        ,_leafThs: function(){
            return this.$thead.find('th').filter(function(){
                return !$.data(this,'col').cols;
            }).sort(function(a, b){
                return parseInt($(a).data('colindex')) - parseInt($(b).data('colindex'));
            });
        }

        ,_colsWithTitleDeep: function(cols,deep){
            var newCols = [];
            if(!cols){
                return newCols;
            }
            for(var colIndex=0; colIndex<cols.length; colIndex++){
                var col = cols[colIndex];
                if(deep === 1){
                    newCols.push(col);
                }else{
                    newCols.push.apply(newCols, this._colsWithTitleDeep(col.cols, deep-1));
                }
            }
            return newCols;
        }

        ,_titleDeep: function(cols){
            var deep = 1;
            for(var colIndex=0; colIndex<cols.length; colIndex++){
                var col = cols[colIndex];
                if(col.cols){
                    var newDeep = 1 + this._titleDeep(col.cols);
                    if(deep < newDeep){
                        deep = newDeep;
                    }
                }
            }
            return deep;
        }

        , _titleHtml: function(col, rowspan){
            var opts = this.opts;

            var titleHtml = [];
            if(!col.cols){
                titleHtml.push('<th class="');
                var colIndex =  $.inArray(col, this._expandCols(opts.cols));
                titleHtml.push(this._genColClass(colIndex));
                titleHtml.push(' " ');
                titleHtml.push(' rowspan="');
                titleHtml.push(rowspan);
                titleHtml.push('" colspan="');
                titleHtml.push(1);
                titleHtml.push('" data-colIndex="');
                titleHtml.push(colIndex);
                titleHtml.push('" style="text-align:');
                titleHtml.push(col.align)
                titleHtml.push(';" >');
                titleHtml.push('<div class="gridGroup-titleWrapper" >');
                titleHtml.push('<span class="gridGroup-title ');
                titleHtml.push('">');
                if(col.titleHtml){
                    titleHtml.push(col.titleHtml);
                }else{
                    titleHtml.push(col.title);
                }
                titleHtml.push('</span><div class="gridGroup-sort"></div>');
                if(!col.lockWidth) titleHtml.push('<div class="gridGroup-colResize"></div>');
                titleHtml.push('</div></th>');
            }else{
                var displayColsLength = col.cols.length;
                $.each(col.cols, function(index, item){
                    if(item.hidden){
                        displayColsLength--;
                    }
                });
                if(displayColsLength === 0){
                    col.hidden = true;
                }
                titleHtml.push('<th class="');
                var colIndex =  $.inArray(col, this._expandCols(opts.cols));
                titleHtml.push(this._genColClass(colIndex));
                titleHtml.push(' gridGroup-groupCol" ');
                titleHtml.push(' rowspan="');
                titleHtml.push(rowspan-1);
                titleHtml.push('" colspan="');
                titleHtml.push(displayColsLength);
                titleHtml.push('" data-colIndex="');
                titleHtml.push(colIndex);
                titleHtml.push('" style="text-align:');
                titleHtml.push(col.align)
                titleHtml.push(';" >');
                titleHtml.push('<div class="gridGroup-titleWrapper" >');
                titleHtml.push('<span class="gridGroup-title ');
                if(col.sortable) titleHtml.push('gridGroup-canSort ');
                titleHtml.push('">');
                if(col.titleHtml){
                    titleHtml.push(col.titleHtml);
                }else{
                    titleHtml.push(col.title);
                }
                titleHtml.push('</span><div class="gridGroup-sort"></div>');
                titleHtml.push('</div></th>');
            }

            return titleHtml.join("");
        }

        , _rowHtml: function(item, rowIndex){
            var opts = this.opts;
            var expandCols = this._expandCols(opts.cols);
            var leafCols = this._leafCols();
            if($.isPlainObject(item)){
                var trHtml = [], span;
                if (opts.checked.enable && opts.checked.handler(item[opts.checked.key], item, rowIndex)) trHtml.push('<tr class="active">');
                else trHtml.push('<tr>');
                for(var colIndex=0; colIndex < leafCols.length; colIndex++){
                    var col = leafCols[colIndex];
                    trHtml.push('<td class="');
                    var index =  $.inArray(col, expandCols);
                    trHtml.push(this._genColClass(index));
                    if(opts.nowrap){
                        trHtml.push(' nowrap');
                    }
                    trHtml.push('" style="text-align:');
                    trHtml.push(col.align);
                    trHtml.push(';"><span class="');
                    if(opts.nowrap){
                        trHtml.push('nowrap');
                    }
                    trHtml.push('">');
                    if(col.renderer){
                        trHtml.push(col.renderer(item[col.name],item,rowIndex));
                    }else{
                        span = item[col.name] || '';
                        if (span.length > opts.wordBreakNum){
                            trHtml.push(span.substring(0, opts.wordBreakNum));
                            trHtml.push('</span>');
                            trHtml.push(appui.fmt('&nbsp;<a href="javascript:;" class="grid-tr-td-tip" style="font-weight: bolder;" ata-toggle="popover" title="{title}" data-content="{content}">…</a>', {
                                title: col.title,
                                content: span
                            }));
                        } else {
                            trHtml.push(span);
                            trHtml.push('</span>');
                        }
                    }

                    trHtml.push('</td>');
                };
                trHtml.push('</tr>');
                return trHtml.join('');
            }
        }


        , _populate: function(items){
            var that = this;
            var opts = this.opts;
            var $tbody = this.$tbody;

            this._hideMessage();
            if(items && items.length !== 0 && opts.cols){

                var tbodyHtmls = [];
                for(var rowIndex=0; rowIndex < items.length; rowIndex++){
                    var item = items[rowIndex];
                    tbodyHtmls.push(this._rowHtml(item, rowIndex));
                }
                $tbody.empty().html(tbodyHtmls.join(''));
                var $trs = $tbody.find('tr');
                for(var rowIndex=0; rowIndex < items.length; rowIndex++){
                    $.data($trs.eq(rowIndex)[0],'item',items[rowIndex]);
                }
            }else{
                this._insertEmptyRow();
                this._showNoData();
            }
            that._colWordBreakTip();
            that.$table.triggerHandler('layoutFinished', [items]);
            //this._setStyle();

           /* if(opts.fullWidthRows && this._loadCount <= 1){
                this._fullWidthRows();
            }*/
        }

        , _insertEmptyRow: function(){
            var $tbody = this.$tbody;
            var len = this.$thead.find('th').length;
            //this.$tfoot.empty().html('<tr class="emptyRow"><td  style="border: 0px;background: none;" colspan="' + len + '">' + this.opts.noDataText + '</td></tr>');
        }
        , _removeEmptyRow: function(){
            var $tbody = this.$tbody;
            $tbody.find('tr.emptyRow').remove();
        }

        /* 生成列类 */
        , _genColClass: function(colIndex){
            return 'gridGroup-'+ this._id +'-col'+colIndex;
        }

        , _colWordBreakTip: function () {
            var $tbody = this.$tbody;
            var $tips = $tbody.find('.grid-tr-td-tip').not('.has-tip-loaded'), $tip;
            $tips.each(function () {
                $(this).addClass('has-tip-loaded').popover({
                    trigger: 'click'
                });
            });
            /*$tips.each(function () {
                $(this).popover();
                $(this).bind('click', {$tips: $tips}, function (event) {
                    event.data.$tips.filter('.popover-show').not(this).each(function () {
                        clearTimeout($(this).attr('interval'));
                        $("#"+$(this).attr('aria-describedby')).fadeOut();
                    });

                    if ($(this))


                    $tip.on('mouseover', function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var $tip = $(this);
                        $tip.attr('interval', setTimeout(function () {
                            clearTimeout($tip.attr('interval'));
                            $tip.popover('hide');
                        }, 3000));
                        return false;
                    })

                });
            });*/
        }

    };

    $.fn.gridGroup = function(){
        if (this.length != 1) throw new Error(this.selector + ' GirdGroup not only one jQuery Element Object!');
        if(arguments.length === 0 || typeof arguments[0] === 'object'){
            var option = arguments[0]
                , data = this.data('gridGroup')
                , options = $.extend(true, {}, $.fn.gridGroup.defaults, appui.templates.gridGroup.defaults,  option);
            if (!data) {
                data = new GridGroup(this, options);
                this.data('gridGroup', data);
            }
            return $.extend(true, this, data);
        }
        if(typeof arguments[0] === 'string'){
            var data = this.data('gridGroup');
            var fn =  data[arguments[0]];
            if(fn){
                var args = Array.prototype.slice.call(arguments);
                return fn.apply(data,args.slice(1));
            }
        }
    };

    $.fn.gridGroup.defaults = {
        width: 'auto'
        , height: '280px'
        , cols: []
        , url: false
        , params: {}
        , method: 'POST'
        , cache: false
        , root: 'list'
        , items: []
        , autoLoad: true
        , loadingText: false
        , noDataText: false
        , loadErrorText: '数据加载出现异常'
        , multiSelect: false
        , checkCol: false
        , indexCol: false
        , indexColWidth: 50
        , fullWidthRows: false
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
    };
//  event : loadSuccess(e,data), loadError(e, data), cellactive(e, item, rowIndex, colIndex)
//          rowInserted(e,item, rowIndex), rowUpdated(e, oldItem, newItem, rowIndex), rowRemoved(e,item, rowIndex)
//

    $.fn.gridGroup.Constructor = GridGroup;



    var GridGroupPaginator = function(element, options){
        this.$el = $(element);
        this.opts = options;

        this.$el.addClass('grid-group-Paginator').addClass("row");
    };

    GridGroupPaginator.prototype = {
        _initLayout: function(){
            var that = this;
            var $el = this.$el;
            var opts = this.opts;

            $el.addClass("GridGroupPaginator");
            var pgHtmls = [
                '<div class="totalCountLabel pagination pull-left"></div>',
                '<div class="limit pagination pagination-sm pull-right input-group-sm"><select class="form-control"></select></div>',
                '<ul class="pageList pagination pagination-sm pull-right"></ul>'
            ];
            $el.append($(pgHtmls.join('')));

            this.$totalCountLabel = $el.find('.totalCountLabel');
            this.$pageList = $el.find('.pageList');
            this.$limitList = $el.find('.limit select');

            var $limitList = this.$limitList;
            $.each(opts.limitList, function(){
                var $option = $('<option></option>')
                    .prop('value',this)
                    .text(that.formatString(opts.limitLabel,[this]));
                $limitList.append($option);
            });

            $limitList.on('change', function(){
                $el.data('page', 1);
                that.$groupGrid.load();
            });

        }

        , _plain: function(page, totalCount, limit){
            var that = this;
            var $el = this.$el;
            var $pageList = this.$pageList;

            var totalPage = totalCount % limit === 0 ? parseInt(totalCount/limit) : parseInt(totalCount/limit) + 1;
            totalPage = totalPage ? totalPage : 0;
            if(totalPage === 0){
                page = 1;
            }else if(page > totalPage){
                page = totalPage;
            }else if(page < 1 && totalPage != 0){
                page = 1;
            }
            //
            var $prev = $('<li class="prev"><a>上一页</a></li>');
            if(page<=1){
                $prev.addClass('disabled');
            }else{
                $prev.find('a').on('click', function(){
                    $el.data('page', page-1);
                    that.$groupGrid.load();
                });
            }
            $pageList.append($prev);
            /////
            var list = [];
           /* if(page > 4 ){
                list.push('...');
            }
            for(var i= 0; i < 5; i++){
                var no = page - 2 + i;
                if(no > 1 && no <= totalPage-1){
                    list.push(no);
                }
            }
            if(page+1 < totalPage-1){
                list.push('...');
            }
            if(totalPage>1){
                list.push(totalPage);
            }
            $.each(list, function(index, item){
                var $li = $('<li><a></a></li>');
                if(item === '...'){
                    $li.addClass('disabled').html('<a>...</a>');
                }else if(item === page){
                    $li.addClass('active').find('a').text(item);
                }else{
                    $li.find('a').text(item).prop('title','第'+item+'页').on('click', function(e){
                        $el.data('page', item);
                        that.$groupGrid.load();
                    });
                }
                $pageList.append($li);
            });*/
            
            var $li = $('<li><a></a></li>');
            $li.addClass('active').find('a').text(page);
            $pageList.append($li);
            
            //
            var $next = $('<li class="next"><a title="下一页">下一页</a></li>');
            if(page>=totalPage){
                $next.addClass('disabled');
            }else{
                $next.find('a').on('click', function(){
                    $el.data('page', page+1);
                    that.$groupGrid.load();
                });
            }
            $pageList.append($next);
        }

        , _search: function(page, totalCount, limit){

        }

        , load: function(params){
            var $el = this.$el;
            var $limitList = this.$limitList;
            var opts = this.opts;

            //var $tbody = this.$tbody;
            //$tbody.empty();

            if(!params){
                params = {};
            }
            
            var page = params[opts.pageParamName];
            if(page === undefined || page === null){
                page = $el.data('page');
            }
            $el.data('page', page);

            var totalCount = params[opts.totalCountName];
            if(totalCount === undefined){
                totalCount = 0;
            }
            $el.data('totalCount', totalCount);

            var limit = params[opts.limitParamName];
            var totalPage = totalCount % limit === 0 ? parseInt(totalCount/limit) : parseInt(totalCount/limit) + 1;
            totalPage = totalPage ? totalPage : 0;
            if(!limit){
                limit = $limitList.val();
            }
            this.$limitList.val(limit);
            var pageFirst = 1 + (page - 1) * limit;
            var pageTotal = page * limit;
            if(totalCount == 0) pageFirst = pageTotal = totalCount;
            else if(pageTotal > totalCount) pageTotal = totalCount;
            this.$totalCountLabel.html(appui.fmt(opts.totalCountLabel, {
            	totalCount: totalCount || '0', 
            	pageFirst: pageFirst || '0', 
            	pageTotal: pageTotal || '0',
            	page: page || '0',
            	totalPage: totalPage || '0'
            }));
            this.$pageList.empty();

            this._plain(page, totalCount, this.$limitList.val());
        }

        , formatString: function(text, args){
            return text.replace(/{(\d+)}/g, function(match, number) {
                return typeof args[number] != 'undefined'
                    ? args[number]
                    : match
                    ;
            });
        }

        , params: function(){
            var opts = this.opts;
            var $el = this.$el;
            var $limitList = this.$limitList;

            var params = {};
            params[opts.pageParamName] = $el.data('page');
            params[opts.limitParamName] = $limitList.val();
            return params;
        }

        , init: function($grid){
            var that = $grid.$paginator = this;
            var opts = that.opts;
            this.$groupGrid = $grid;
            this._initLayout();
            this.$groupGrid.on('loadSuccess', function(e, data){
                that.load(data);
            });
            
            if (  !$grid.parent().hasClass("has-grid-page") ) {
            	$grid.parent().append(this.$el).css({
                	"padding-bottom": "42px"
                }).addClass("has-grid-page");
            }
            
            
            var params = {};
            params[opts.totalCountName] = 0;
            params[opts.pageParamName] = opts.page;
            params[opts.limitParamName] = opts.limit;
            this.load(params);

            if($grid.opts.indexCol){
                var indexCol = $grid.opts.cols[0];
                indexCol.renderer = function(val,item,rowIndex){
                    var params = that.params();
                    return '<label class="gridGroup-index">' +
                        (rowIndex + 1 + ((params[opts.pageParamName]-1) * params[opts.limitParamName])) +
                        '</label>';
                };
            }

        }

    };

    $.fn.gridGroupPaginator = function(){

        if (this.length != 1) throw new Error(this.selector + ' GirdGroupPaginator not only one jQuery Element Object!');

        if(arguments.length === 0 || typeof arguments[0] === 'object'){
            var option = arguments[0]
                , data = this.data('GridGroupPaginator')
                , options = $.extend(true, {}, $.fn.gridGroupPaginator.defaults, option);
            if (!data) {
                data = new GridGroupPaginator(this[0], options);
                this.data('GridGroupPaginator', data);
            }
            return $.extend(true, this, data);
        }
        if(typeof arguments[0] === 'string'){
            var data = this.data('GridGroupPaginator');
            var fn =  data[arguments[0]];
            if(fn){
                var args = Array.prototype.slice.call(arguments);
                return fn.apply(data,args.slice(1));
            }
        }
    };

    $.fn.gridGroupPaginator.defaults = {
        style: 'plain'
        , totalCountName: 'totalSize'
        , page: 1
        , pageParamName: 'pageNo'
        , limitParamName: 'pageSize'
        , limitLabel: '每页{0}条'
        , totalCountLabel: '共{totalCount}条 | 共{totalPage}页'
        , limit: undefined
        , limitList: [10, 20, 30, 40, 50]
    };

    $.fn.gridGroupPaginator.Constructor = GridGroupPaginator;


    var GridGroupQuery = function(element, options){
        this.$el = $(element);
        var opts = this.opts = $.extend( true, {}, this.defaults, options );
        
        if ( opts.isToggle ) {
        	this.$el.slideUp();
        }
        
    };

    GridGroupQuery.prototype = {
        _initLayout: function(){
        },
        _plain: function(page, totalCount, limit){
        },
        _search: function(page, totalCount, limit){
        },
        load: function(params){
        },
        formatString: function(tmpl, args){
            var format = { };
            return tmpl.replace(/{(\w+)}/g, function(m1, m2) {
                if (!m2)return "";
                return (format && format[m2]) ? format[m2](args[m2]) : args[m2];
            });
        },
        params: function(){
            var opts = this.opts;
            var $el = this.$el;
            var formatString = this.formatString;

            var params = {}, n, v;
            var $inputs = $el.find(":input").each(function(){
                n = $(this).attr("name");
                if(n){
                    v = $.trim($(this).val());
                    if (typeof v != "undefined" && v != null && v != '') {
                        n = formatString(opts.param, {0: n});
                        if(!params[n]) params[n] = v;
                        else params[n] += "," + v;
                    }
                }
            });
            return params;
        },
        init: function($grid){
            var that = $grid.$query=this;

            $(this.opts.queryBtn).click(function(){
                //if($grid.$paginator) $grid.$paginator.$el.data("page", 1);
                $grid.load();
            });

            $(this.opts.resetBtn).click(function(){
                var $el = $grid.$query.$el;
                $el.find('input, textarea').not('[type=button], [type=reset], [type=submit], [type=hidden]').valChange('');
                $grid.load();
            });
        }

    };

    $.fn.gridGroupQuery = function(){
        if (this.length != 1) throw new Error(this.selector + ' GirdGroupQuery not only one jQuery Element Object!');
        if(arguments.length === 0 || typeof arguments[0] === 'object'){
            var option = arguments[0]
                , data = this.data('gridGroupQuery')
                , options = $.extend(true, {}, $.fn.gridGroupQuery.defaults, option);
            if (!data) {
                data = new GridGroupQuery(this[0], options);
                this.data('gridGroupQuery', data);
            }
            return $.extend(true, this, data);
        }
        if(typeof arguments[0] === 'string'){
            var data = this.data('gridGroupQuery');
            var fn =  data[arguments[0]];
            if(fn){
                var args = Array.prototype.slice.call(arguments);
                return fn.apply(data,args.slice(1));
            }
        }
    };

    $.fn.gridGroupQuery.defaults = {
		isToggle: false,
        queryBtn: "#btnQuery",
        param:"params[{0}]"
    };

    $.fn.gridGroupQuery.Constructor = GridGroupQuery;


})(jQuery, appui);