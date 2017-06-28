(function($, app) {

	if (typeof $.data(window, "code") == 'undefined')
		$.data(window, "code", {});

	app.code = function(options) {

		if (typeof options == 'string') {
			var tmps = options.split(',');
			options = [];
			while (tmps.length > 0) options.push({type: $.trim(tmps.shift())});
		}

		//数组转json
		var config = {};
		for(var i=0; i<options.length; i++){
			var item = options[i];
			if (!item || !item.type) continue;
			config[item.type] = item;
		}
		
		var codes = [], codeCache = $.data(window, "code") || {}, jsons = [];
		for(var i=0; i<options.length; i++){
			var item = options[i];
			if(typeof codeCache[item.type] == 'undefined')
				if (item.type.indexOf('.json') != -1)
					jsons.push({
						url: item.url || 'static/js/data/',
						type : item.type,
						names : item.name || item.type,
						group: item.group || 'defaults'
					});
				else
					codes.push({
						type : item.type,
						names : item.name || item.type,
						group: item.group || 'defaults'
					});
		}

		if (codes.length != 0)
			$.ajax({
				url: 'common/code/load',
				type: "POST",
				data: JSON.stringify(codes),
				contentType: "application/json",//Json字符串作为请求参数时设置
				success: function (json) {
					var codeCache = {};
					var cache = $.data(window, "code") || {};
					for (var i = 0; i < json.length; i++) {
						var data = json[i].data;
						codeCache[json[i].type] = data;

						//缓存
						app.code.loaded = true;
						cache = $.extend(true, cache, codeCache);

						$.data(window, "code", cache);
						$(app).triggerHandler(json[i].type + '.app.code');
					}
				}
			});

		for (var i = 0; i < jsons.length; i++) {
			(function(json){
				$.ajax({
					url: json.url + json.type,
					type: 'GET',
					data: JSON.stringify(json),
					success: function (data) {
						var codeCache = {};
						var cache = $.data(window, "code") || {};
						codeCache[json.type] = data;

						//缓存
						app.code.loaded = true;
						cache = $.extend(true, cache, codeCache);

						$.data(window, "code", cache);
						$(app).triggerHandler(json.type + '.app.code');
					}
				})
			})(jsons[i]);
		}
	};

	app.code.getTextFromCache = function(type, value, group){
		if(typeof(type)=="string"){
			return app.code.getTextFromCachePrivate(type, value, group);
		}else if(type instanceof Array){
			for(var i=0;i<type.length;i++){
				var text = app.code.getTextFromCachePrivate(type[i], value, group);
				if(text != value){
					return text;
				}
			}
		}
		
		return value;
	};
	
	app.code.getTextFromCachePrivate = function(type, value, group){
		if(typeof group == 'undefined') group = '__defaultGroup';
		var data = $.data(window, "code");
		if (data) data = data[type];
		if (data) data = data[group];
		if(data == undefined || data == null){
			return value;
		}
		for(var i=0; i<data.length; i++){
			if(data[i]['code'] == value){
				return data[i]['codeName'];
			}
		}
		return value;
	};

	app.code.getText = function(type, value, index, group){
		var data = $.data(window, "code");
		if (data) data = data[type];
		if (data) return '<span>' + (app.code.getTextFromCachePrivate(type, value, group) || '') + '</span>';

		var id = 'code_' + type + '_' + (Math.random().toString().substring(2, 10)) + '_' + index;
		var ecode = type + '.app.code.' + id;
		$(app).bind(ecode, {
			id: id,
			type: type,
			value: value,
			group: group
		}, function (e) {
			var id = e.data.id,
				ecode = e.data.ecode,
				type = e.data.type,
				value = e.data.value;
			//关闭绑定事件
			$(app).unbind(ecode, false);

			$("#" + id).text(app.code.getTextFromCachePrivate(type, value, group));

		});
		return '<span id="' + id + '">-</span>'
	};

	app.code.getData = function(type, group){
		var data = $.data(window, "code");
		if(data) data = data[type];
		if(data && typeof group != 'undefined') data = data[group];
		return data;
	};

	app.code.getDataToCallBack = function (type, callback, group) {
		var data = app.code.getData(type, group);
		if(data) callback(data);

		var ecode = 'app.code.' + type + '.R' + (Math.random().toString().substring(2, 10));
		$().on(ecode, {
			ecode: ecode,
			type: type,
			callback: callback,
			group: group
		}, function (e) {
			var ecode = e.data.ecode,
				type = e.data.type,
				callback = e.data.callback;

			$(app.code).off(ecode);
			callback(app.code.getData(type, group));
		});
	}


})(jQuery, app);