define( [ "echarts" ], function( echarts ) {
//树（大图）-------------------------------------------------
var zdj_tree1 = {
	 //默认背景色
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
        backgroundColor: 'rgba(0,0,0,0.8)'
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
	
	echarts.registerTheme( "echarts.theme.tree.tree1", zdj_tree1 );
	return {
		tree1: zdj_tree1 //树（大图）
	};

} );

