define( [ "echarts" ], function( echarts ) {
	
	//双轴（双柱+单线）-------------------------------------------------
	var zdj_2Y1 = {
		 //默认背景色
		//backgroundColor :'#fff',
		// 默认色板
	    color: [
	        '#b8e4fd','#079bef','#ff9812'
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
	    
	    legend: {
	    	padding: 10
	    },
	    grid: {
	        borderWidth: 0,
		    x : 60,
			y : 85,
			x2 : 60,
			y2 : 200
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
			 
	    textStyle: {
	        fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
	    }
	    
	};


	//双轴（单柱+双线）------------------------------------------------------
	var zdj_2Y2 = {
		//默认背景
		//backgroundColor :'#fff',
		// 默认色板
	    color: [
	        '#079bef','#ff9812','#e8486a'
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
			y : 85,
			x2 : 60,
			y2 : 200
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
			 
	    textStyle: {
	        fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
	    }
	    
	};

	//双轴（有负值：双柱+单线）-------------------------------------------------
	var zdj_2Y3 = {
		//默认背景
		//backgroundColor :'#fff',
	    // 默认色板
	    color: [
	        '#079bef','#fccca7','#ff9812'
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
			y : 85,
			x2 : 60,
			y2 : 200
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
			 
	    textStyle: {
	        fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
	    }
	    
	};

	//双轴（有负值：三柱+单线）-------------------------------------------------
	var zdj_2Y4 = {
		//默认背景
		//backgroundColor :'#fff',
		// 默认色板
	    color: [
	        '#096791','#079bef','#b8e4fd','#ff9812'
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
			y : 85,
			x2 : 60,
			y2 : 200
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
			 
	    textStyle: {
	        fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
	    }
	    
	};

	//双轴（双柱+单线）-------------------------------------------------
	var zdj_GY01 = {
		//默认背景
		//backgroundColor :'#fff',
		// 默认色板
	    color: [
	        '#079bef','#b8e4fd','#ff9812'
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
			y : 85,
			x2 : 60,
			y2 : 200
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
			 
	    textStyle: {
	        fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
	    }
	};
	
	var zdj_dy01 = {
			
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
			     left: 'right',
			     padding:2,
			     itemWidth:12,
			     itemHeight:12
		    },
	};
	
	var zdj_dy02 = {
			
			color:['#57A3F1','#2E87E2','#62CD28','#EE582C','#EE7C19','#9E5483','#FF9812'],
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
			     left: 'right',
			     padding:2,
			     itemWidth:12,
			     itemHeight:12
		    },
	};
	
	var zdj_dy03 = {
				
				color:['#EE582C','#EE7C19','#2E87E2','#57A3F1','#9E5483','#62CD28','#FF9812'],
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
				     left: 'right',
				     padding:2,
				     itemWidth:12,
				     itemHeight:12
			    },
		};
	
	echarts.registerTheme( "echarts.theme.2y.bar1", zdj_2Y1 );
	echarts.registerTheme( "echarts.theme.2y.bar2", zdj_2Y2 );
	echarts.registerTheme( "echarts.theme.2y.bar3", zdj_2Y3 );
	echarts.registerTheme( "echarts.theme.2y.bar4", zdj_2Y4 );
	echarts.registerTheme( "echarts.theme.2y.bar5", zdj_GY01 );
	echarts.registerTheme( "echarts.theme.2y.bar6", zdj_dy01 );
	echarts.registerTheme( "echarts.theme.2y.bar7", zdj_dy02 );
	echarts.registerTheme( "echarts.theme.2y.bar8", zdj_dy03 );
	
	return {
		bar1: zdj_2Y1,  // 双轴（双柱+单线）
		bar2: zdj_2Y2,  // 双轴（单柱+双线）
		bar3: zdj_2Y3,  // 双轴（有负值：双柱+单线）
		bar4: zdj_2Y4,  //  双轴（有负值：三柱+单线）
		bar5: zdj_GY01,  //双轴（双柱+单线）
		bar6: zdj_dy01,   //单轴/双轴+折线
		bar7: zdj_dy02,   //五轴
		bar8: zdj_dy03   //六轴
	};
	
} );

