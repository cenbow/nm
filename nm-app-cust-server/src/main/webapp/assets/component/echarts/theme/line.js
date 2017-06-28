define( [ "echarts" ], function( echarts ) {
	//线形图（单线：黄）-------------------------------------------
	var zdj_line1 = {
			//默认背景色
			//backgroundColor :'#fff',
			// 默认色板
			color: [
			        '#ff9812'
			        ],

			        // 图表标题
			        title: {
			        	left :'left',
				        top:10,
			        	bottom:10,
			        	padding:[1,1,2,2],
			        	backgroundColor :'#eeeeee',
			        	textStyle: {
			        		fontSize: 13,
			        		fontWeight: 'bold',
			        		fontFamily: '微软雅黑',
			        		align: 'center',                   
			        		color: '#333'
			        	}
		        	},
		        	subtextStyle : {
		        		fontSize: 12,
		        		fontWeight: 'normal',
		        		color : 'black' 
		        	},
			        dataRange:{
			        	color:['#1178ad','#72bbd0']
			        },

			        // 工具箱
			        toolbox: {
			        	color : ['#1790cf','#1790cf','#1790cf','#1790cf']
			        },

			        // 提示框
			        tooltip: {
			        	textStyle:{
			        		align: 'left'
			        	},
			        	backgroundColor: 'rgba(0,0,0,0.8)',
			        	axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			        		type : 'line',         // 默认为直线，可选为：'line' | 'shadow'
			        		lineStyle : {          // 直线指示器样式设置
			        			color: '#1790cf',
			        			type: 'dashed'
			        		},
			        		crossStyle: {
			        			color: '#1790cf'
			        		},
			        		shadowStyle : {                     // 阴影指示器样式设置
			        			color: 'rgba(200,200,200,0.3)'
			        		}
			        	}
			        },

			        // 区域缩放控制器
			        dataZoom: {
			        	dataBackgroundColor: '#eee',            // 数据背景颜色
			        	fillerColor: 'rgba(144,197,237,0.2)',   // 填充颜色
			        	handleColor: '#1790cf'     // 手柄颜色
			        },

			        grid: {
			        	borderWidth: 0,
			        	x : 60,
			        	y : 80,
			        	x2 : 20,
			        	y2 : 180
			        },

			        // 类目轴
			        categoryAxis: {
			        	axisLine: {            // 坐标轴线
			        		lineStyle: {       // 属性lineStyle控制线条样式
			        			width: 1,
			        			color: '#8A8DAC'
			        		}
			        	},
			        	splitLine: {           // 分隔线
			        		show: false,
			        		lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			        			color: ['#eee']
			        		}
			        	}
			        },

			        // 数值型坐标轴默认参数
			        valueAxis: {
			        	axisLine: {            // 坐标轴线
			        		lineStyle: {       // 属性lineStyle控制线条样式
			        			width: 1,
			        			color: '#8A8DAC'
			        		}
			        	},
			        	splitArea : {
			        		show : false,
			        		areaStyle : {
			        			color: ['rgba(250,250,250,0.1)','rgba(200,200,200,0.1)']
			        		}
			        	},
			        	splitLine: {           // 分隔线
			        		lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			        			color: ['#eee']
			        		}
			        	}
			        },

			        timeline : {
			        	lineStyle : {
			        		color : '#1790cf'
			        	},
			        	controlStyle : {
			        		normal : { color : '#1790cf'},
			        		emphasis : { color : '#1790cf'}
			        	}
			        },
			        line: {
			        	smooth : false,
			        	symbolSize: 2	           // 拐点图形大小
			        },  

			        textStyle: {
			        	fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
			        }
	};
	//线形图（双线：蓝、黄）-------------------------------------------
	var zdj_line2 = {
			//默认背景色
			//backgroundColor :'#fff',
			// 默认色板
			color: [
			        '#079bef','#ff9812'
			        ],

			        // 图表标题
			        title: {
			        	x :'center',
			        	y :'top',
			        	backgroundColor :'#eeeeee',
			        	padding:[10,1000,10,1000],
			        	textStyle: {
			        		fontSize: 13,
			        		fontWeight: 'bold',
			        		fontFamily: '微软雅黑',
			        		align: 'center',                   
			        		color: '#333'
			        	},
			        	subtextStyle : {
			        		fontSize: 12,
			        		fontWeight: 'normal',
			        		color : 'black'
			        	}   
			        },

			        // 值域
			        dataRange: {
			        	color:['#1178ad','#72bbd0']
			        },

			        // 工具箱
			        toolbox: {
			        	color : ['#1790cf','#1790cf','#1790cf','#1790cf']
			        },

			        // 提示框
			        tooltip: {
			        	textStyle:{
			        		align: 'left'
			        	},
			        	backgroundColor: 'rgba(0,0,0,0.8)',
			        	axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			        		type : 'line',         // 默认为直线，可选为：'line' | 'shadow'
			        		lineStyle : {          // 直线指示器样式设置
			        			color: '#1790cf',
			        			type: 'dashed'
			        		},
			        		crossStyle: {
			        			color: '#1790cf'
			        		},
			        		shadowStyle : {                     // 阴影指示器样式设置
			        			color: 'rgba(200,200,200,0.3)'
			        		}
			        	}
			        },

			        // 区域缩放控制器
			        dataZoom: {
			        	dataBackgroundColor: '#eee',            // 数据背景颜色
			        	fillerColor: 'rgba(144,197,237,0.2)',   // 填充颜色
			        	handleColor: '#1790cf'     // 手柄颜色
			        },

			        grid: {
			        	borderWidth: 0,
			        	x : 60,
			        	y : 60,
			        	x2 : 20,
			        	y2 : 180
			        },

			        // 类目轴
			        categoryAxis: {
			        	axisLine: {            // 坐标轴线
			        		lineStyle: {       // 属性lineStyle控制线条样式
			        			width: 1,
			        			color: '#8A8DAC'
			        		}
			        	},
			        	splitLine: {           // 分隔线
			        		show: false,
			        		lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			        			color: ['#eee']
			        		}
			        	}
			        },

			        // 数值型坐标轴默认参数
			        valueAxis: {
			        	axisLine: {            // 坐标轴线
			        		lineStyle: {       // 属性lineStyle控制线条样式
			        			width: 1,
			        			color: '#8A8DAC'
			        		}
			        	},
			        	splitArea : {
			        		show : false,
			        		areaStyle : {
			        			color: ['rgba(250,250,250,0.1)','rgba(200,200,200,0.1)']
			        		}
			        	},
			        	splitLine: {           // 分隔线
			        		lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			        			color: ['#eee']
			        		}
			        	}
			        },

			        timeline : {
			        	lineStyle : {
			        		color : '#1790cf'
			        	},
			        	controlStyle : {
			        		normal : { color : '#1790cf'},
			        		emphasis : { color : '#1790cf'}
			        	}
			        },
			        line: {
			        	smooth : false,
			        	symbolSize: 2	           // 拐点图形大小
			        },  

			        textStyle: {
			        	fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
			        }
	};

	//线形图（三线：蓝绿黄  四线：蓝绿黄红）------------------------------------------------
	var zdj_line3 = {
			//默认背景色
			//backgroundColor :'#fff',
			// 默认色板
			color: [
			        '#079bef','#42a400','#ff9812','#e8486a'
			        ],

			        // 图表标题
			        title: {
			        	x :'center',
			        	y :'top',
			        	backgroundColor :'#eeeeee',
			        	padding:[10,1000,10,1000],
			        	textStyle: {
			        		fontSize: 13,
			        		fontWeight: 'bold',
			        		fontFamily: '微软雅黑',
			        		align: 'center',                   
			        		color: '#333'
			        	},
			        	subtextStyle : {
			        		fontSize: 12,
			        		fontWeight: 'normal',
			        		color : 'black'
			        	}   
			        },

			        // 值域
			        dataRange: {
			        	color:['#1178ad','#72bbd0']
			        },

			        // 工具箱
			        toolbox: {
			        	color : ['#1790cf','#1790cf','#1790cf','#1790cf']
			        },

			        // 提示框
			        tooltip: {
			        	textStyle:{
			        		align: 'left'
			        	},
			        	backgroundColor: 'rgba(0,0,0,0.8)',
			        	axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			        		type : 'line',         // 默认为直线，可选为：'line' | 'shadow'
			        		lineStyle : {          // 直线指示器样式设置
			        			color: '#1790cf',
			        			type: 'dashed'
			        		},
			        		crossStyle: {
			        			color: '#1790cf'
			        		},
			        		shadowStyle : {                     // 阴影指示器样式设置
			        			color: 'rgba(200,200,200,0.3)'
			        		}
			        	}
			        },

			        // 区域缩放控制器
			        dataZoom: {
			        	dataBackgroundColor: '#eee',            // 数据背景颜色
			        	fillerColor: 'rgba(144,197,237,0.2)',   // 填充颜色
			        	handleColor: '#1790cf'     // 手柄颜色
			        },

			        grid: {
			        	borderWidth: 0,
			        	x : 60,
			        	y : 60,
			        	x2 : 20,
			        	y2 : 180
			        },

			        // 类目轴
			        categoryAxis: {
			        	axisLine: {            // 坐标轴线
			        		lineStyle: {       // 属性lineStyle控制线条样式
			        			width: 1,
			        			color: '#8A8DAC'
			        		}
			        	},
			        	splitLine: {           // 分隔线
			        		show: false,
			        		lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			        			color: ['#eee']
			        		}
			        	}
			        },

			        // 数值型坐标轴默认参数
			        valueAxis: {
			        	axisLine: {            // 坐标轴线
			        		lineStyle: {       // 属性lineStyle控制线条样式
			        			width: 1,
			        			color: '#8A8DAC'
			        		}
			        	},
			        	splitArea : {
			        		show : false,
			        		areaStyle : {
			        			color: ['rgba(250,250,250,0.1)','rgba(200,200,200,0.1)']
			        		}
			        	},
			        	splitLine: {           // 分隔线
			        		lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			        			color: ['#eee']
			        		}
			        	}
			        },

			        timeline : {
			        	lineStyle : {
			        		color : '#1790cf'
			        	},
			        	controlStyle : {
			        		normal : { color : '#1790cf'},
			        		emphasis : { color : '#1790cf'}
			        	}
			        },
			        line: {
			        	smooth : false,
			        	symbolSize: 2	           // 拐点图形大小
			        },  

			        textStyle: {
			        	fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
			        }
	};
	//线形图（六线）------------------------------------------------
	var zdj_line6 = {
			//默认背景色
			//backgroundColor :'#fff',
			// 默认色板
			color: [
			        '#e8486a','#079bef','#2f4196','#ff9812','#096791','#42a400'
			        ],

			        // 图表标题
			        title: {
			        	x :'center',
			        	y :'top',
			        	backgroundColor :'#eeeeee',
			        	padding:[10,1000,10,1000],
			        	textStyle: {
			        		fontSize: 13,
			        		fontWeight: 'bold',
			        		fontFamily: '微软雅黑',
			        		align: 'center',                   
			        		color: '#333'
			        	},
			        	subtextStyle : {
			        		fontSize: 12,
			        		fontWeight: 'normal',
			        		color : 'black'
			        	}   
			        },

			        // 值域
			        dataRange: {
			        	color:['#1178ad','#72bbd0']
			        },

			        // 工具箱
			        toolbox: {
			        	color : ['#1790cf','#1790cf','#1790cf','#1790cf']
			        },

			        // 提示框
			        tooltip: {
			        	textStyle:{
			        		align: 'left'
			        	},
			        	backgroundColor: 'rgba(0,0,0,0.8)',
			        	axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			        		type : 'line',         // 默认为直线，可选为：'line' | 'shadow'
			        		lineStyle : {          // 直线指示器样式设置
			        			color: '#1790cf',
			        			type: 'dashed'
			        		},
			        		crossStyle: {
			        			color: '#1790cf'
			        		},
			        		shadowStyle : {                     // 阴影指示器样式设置
			        			color: 'rgba(200,200,200,0.3)'
			        		}
			        	}
			        },

			        // 区域缩放控制器
			        dataZoom: {
			        	dataBackgroundColor: '#eee',            // 数据背景颜色
			        	fillerColor: 'rgba(144,197,237,0.2)',   // 填充颜色
			        	handleColor: '#1790cf'     // 手柄颜色
			        },

			        grid: {
			        	borderWidth: 0,
			        	x : 60,
			        	y : 80,
			        	x2 : 20,
			        	y2 : 180
			        },

			        // 类目轴
			        categoryAxis: {
			        	axisLine: {            // 坐标轴线
			        		lineStyle: {       // 属性lineStyle控制线条样式
			        			width: 1,
			        			color: '#8A8DAC'
			        		}
			        	},
			        	splitLine: {           // 分隔线
			        		show: false,
			        		lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			        			color: ['#eee']
			        		}
			        	}
			        },

			        // 数值型坐标轴默认参数
			        valueAxis: {
			        	axisLine: {            // 坐标轴线
			        		lineStyle: {       // 属性lineStyle控制线条样式
			        			width: 1,
			        			color: '#8A8DAC'
			        		}
			        	},
			        	splitArea : {
			        		show : false,
			        		areaStyle : {
			        			color: ['rgba(250,250,250,0.1)','rgba(200,200,200,0.1)']
			        		}
			        	},
			        	splitLine: {           // 分隔线
			        		lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			        			color: ['#eee']
			        		}
			        	}
			        },

			        timeline : {
			        	lineStyle : {
			        		color : '#1790cf'
			        	},
			        	controlStyle : {
			        		normal : { color : '#1790cf'},
			        		emphasis : { color : '#1790cf'}
			        	}
			        },
			        line: {
			        	smooth : false,
			        	symbolSize: 2	           // 拐点图形大小
			        },  

			        textStyle: {
			        	fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
			        }
	};

	//线形图（备用：绿蓝黄(收支平衡图)）------------------------------------------------
	var zdj_line3X = {
			//默认背景色
			//backgroundColor :'#fff',
			// 默认色板
			color: [
			        '#42a400','#079bef','#ff9812'
			        ],

			        // 图表标题
			        title: {
			        	x :'center',
			        	y :'top',
			        	backgroundColor :'#eeeeee',
			        	padding:[10,1000,10,1000],
			        	textStyle: {
			        		fontSize: 13,
			        		fontWeight: 'bold',
			        		fontFamily: '微软雅黑',
			        		align: 'center',                   
			        		color: '#333'
			        	},
			        	subtextStyle : {
			        		fontSize: 12,
			        		fontWeight: 'normal',
			        		color : 'black'
			        	}   
			        },

			        // 值域
			        dataRange: {
			        	color:['#1178ad','#72bbd0']
			        },

			        // 工具箱
			        toolbox: {
			        	color : ['#1790cf','#1790cf','#1790cf','#1790cf']
			        },

			        // 提示框
			        tooltip: {
			        	textStyle:{
			        		align: 'left'
			        	},
			        	backgroundColor: 'rgba(0,0,0,0.8)',
			        	axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			        		type : 'line',         // 默认为直线，可选为：'line' | 'shadow'
			        		lineStyle : {          // 直线指示器样式设置
			        			color: '#1790cf',
			        			type: 'dashed'
			        		},
			        		crossStyle: {
			        			color: '#1790cf'
			        		},
			        		shadowStyle : {                     // 阴影指示器样式设置
			        			color: 'rgba(200,200,200,0.3)'
			        		}
			        	}
			        },

			        // 区域缩放控制器
			        dataZoom: {
			        	dataBackgroundColor: '#eee',            // 数据背景颜色
			        	fillerColor: 'rgba(144,197,237,0.2)',   // 填充颜色
			        	handleColor: '#1790cf'     // 手柄颜色
			        },

			        grid: {
			        	borderWidth: 0,
			        	x : 60,
			        	y : 80,
			        	x2 : 20,
			        	y2 : 180
			        },

			        // 类目轴
			        categoryAxis: {
			        	axisLine: {            // 坐标轴线
			        		lineStyle: {       // 属性lineStyle控制线条样式
			        			width: 1,
			        			color: '#8A8DAC'
			        		}
			        	},
			        	splitLine: {           // 分隔线
			        		show: false,
			        		lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			        			color: ['#eee']
			        		}
			        	}
			        },

			        // 数值型坐标轴默认参数
			        valueAxis: {
			        	axisLine: {            // 坐标轴线
			        		lineStyle: {       // 属性lineStyle控制线条样式
			        			width: 1,
			        			color: '#8A8DAC'
			        		}
			        	},
			        	splitArea : {
			        		show : false,
			        		areaStyle : {
			        			color: ['rgba(250,250,250,0.1)','rgba(200,200,200,0.1)']
			        		}
			        	},
			        	splitLine: {           // 分隔线
			        		lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			        			color: ['#eee']
			        		}
			        	}
			        },

			        timeline : {
			        	lineStyle : {
			        		color : '#1790cf'
			        	},
			        	controlStyle : {
			        		normal : { color : '#1790cf'},
			        		emphasis : { color : '#1790cf'}
			        	}
			        },
			        line: {
			        	smooth : false,
			        	symbolSize: 2	           // 拐点图形大小
			        },  

			        textStyle: {
			        	fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
			        }
	};
	
	var zdj_line7 = {
			color:['#57A3F1','#EE582C','#62CD28','#2E87E2','#9E5483','#FF9812','#EE7C19'],
			title: {
		        left :'left',
		        top:10,
	        	bottom:10,
	        	padding:[1,1,2,2],
	        	backgroundColor :'#eeeeee',
	        	textStyle: {
	        		fontSize: 13,
	        		fontWeight: 'bold',
	        		fontFamily: '微软雅黑',
	        		align: 'center',                   
	        		color: '#333'
	        	}
		    },
		    legend: {
			     top: 38,
			     left: 'right'
		    }
	}
	
	echarts.registerTheme( "echarts.theme.line.line1", zdj_line1 );
	echarts.registerTheme( "echarts.theme.line.line2", zdj_line2 );
	echarts.registerTheme( "echarts.theme.line.line3", zdj_line3 );
	echarts.registerTheme( "echarts.theme.line.line4", zdj_line6 );
	echarts.registerTheme( "echarts.theme.line.line5", zdj_line3X );
	echarts.registerTheme( "echarts.theme.line.line6", zdj_line7 );
	
	return {
		line1: zdj_line1 ,  // 线形图（单线：黄）
		line2: zdj_line2 ,  // 线形图（双线：蓝、黄）
		line3: zdj_line3 ,  // 线形图（三线：蓝绿黄  四线：蓝绿黄红）
		line4: zdj_line6 ,  // 线形图（六线）
		line5: zdj_line3X,  // 线形图（备用：绿蓝黄(收支平衡图)）
		line6: zdj_line7    //	
	};
} );