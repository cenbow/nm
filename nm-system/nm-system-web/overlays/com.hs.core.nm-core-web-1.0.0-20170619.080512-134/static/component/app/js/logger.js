/**
 * Created by Mifeng.He(bee) on 2015/11/3.
 */
(function () {
	this.logger = {};

	this.logger.isSupport = {};
	this.logger.isSupport.log  = this.console && this.console.log;
	this.logger.isSupport.debug  = this.console && this.console.debug;
	this.logger.isSupport.info  = this.console && this.console.info;
	this.logger.isSupport.warn  = this.console && this.console.warn;
	this.logger.isSupport.error  = this.console && this.console.error;

	this.logger.log = function () {
		var args = []; for (var i = 0; i < arguments.length; i++) args.push(arguments[i])
		if (this.isSupport.log) console.log(args.join(' '));
	};

	this.logger.debug = function () {
		var args = []; for (var i = 0; i < arguments.length; i++) args.push(arguments[i])
		if (this.isSupport.debug) console.debug(args.join(' '));
		else if (this.isSupport.log) console.log('debug:' + args.join(' '));
	};

	this.logger.info = function () {
		var args = []; for (var i = 0; i < arguments.length; i++) args.push(arguments[i])
		if (this.isSupport.info) console.info(args.join(' '));
		else if (this.isSupport.log) console.log('info:' + args.join(' '));
	};

	this.logger.warn = function () {
		var args = []; for (var i = 0; i < arguments.length; i++) args.push(arguments[i])
		if (this.isSupport.warn) console.warn(args.join(' '));
		else if (this.isSupport.log) console.log('warn:' + args.join(' '));
	};

	this.logger.warn = function () {
		var args = []; for (var i = 0; i < arguments.length; i++) args.push(arguments[i])
		if (this.isSupport.error) console.error(args.join(' '));
		else if (this.isSupport.log) console.log('error:' + args.join(' '));
	};
})();