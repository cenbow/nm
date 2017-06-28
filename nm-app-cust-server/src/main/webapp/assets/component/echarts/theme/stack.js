define( [ "echarts" ], function( echarts ) {
//堆叠图-双色---------------------------------------------------------------------------
var zdj_stack2 = {
	//默认背景色
	//backgroundColor :'#fff',
	// 默认色板
    color: [
        '#079bef','#b8e4fd'
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

//堆叠图-三色---------------------------------------------------------------------------
var zdj_stack3 = {
	//默认背景色
	//backgroundColor :'#fff',
	// 默认色板
    color: [
        '#096791','#079bef','#b8e4fd'
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

//堆叠图-四色---------------------------------------------------------------------------
var zdj_stack4 = {
	//默认背景色
	//backgroundColor :'#fff',
	// 默认色板
    color: [
        '#079bef','#b8e4fd','#ff9812','#fccca7'
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
	
	echarts.registerTheme( "echarts.theme.stack.stack1", zdj_stack2 );
	echarts.registerTheme( "echarts.theme.stack.stack2", zdj_stack3 );
	echarts.registerTheme( "echarts.theme.stack.stack3", zdj_stack4 );

	return {
		stack1: zdj_stack2,  // 堆叠图-双色
		stack2: zdj_stack3,  // 堆叠图-三色
		stack3: zdj_stack4   // 堆叠图-四色
	};
} );