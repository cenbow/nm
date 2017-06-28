define( [ "echarts" ],function( echarts ) {
	//饼图（蓝-橙-绿（按顺序从浅到深））-------------------------------------------------
	var zdj_pie1 = {
			//默认背景色
			//backgroundColor :'#fff',
			// 默认色板
			color: [
			        '#a5daf2','#14abe0','#096791','#fccca7','#ff9812','#9cd8b0','#42a400','#a5b9ef'
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

			        //拖重计算
			        calculable : false,

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


			        grid: {
			        	borderWidth: 0,
			        	x : 40,
			        	y : 60,
			        	x2 : 60,
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

			        itemStyle : {                
			        	normal : {                    
			        		label : {                        
			        			position : 'inner' ,
			        			show:true,
			        			textStyle:{
			        				color:'#3e3e3f'
			        			},
			        			formatter: function(params){
			        				if(params.name.length>2){
			        					return params.name.substr(0,2) + '...';	
			        				}else {
			        					return params.name;
			        				}
			        			}							                   
			        		},                    
			        		labelLine : {                        
			        			show : false                    
			        		}                    					
			        	},
			        	emphasis : {                    
			        		label : {                        
			        			show:false,
			        			formatter: function(params){
			        				if(params.name.length>2){
			        					return params.name.substr(0,2) + '...';	
			        				}else {
			        					return params.name;
			        				}
			        			}							              
			        		},                    
			        		labelLine : {                        
			        			show : false                    
			        		}                    					
			        	}                    
			        },

			        textStyle: {
			        	fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
			        }

	};

	//饼图（9-10个颜色用此配色方案（按顺序从浅到深））-------------------------------------------------
	var zdj_pie2 = {
			//默认背景色
			//backgroundColor :'#fff',
			// 默认色板
			color: [
			        '#a7e0f2','#a79bef','#fccca7','#ff9812','#9cd8b0','#42a400','#a5b9ef','#2f4196','#f4bad2','#e8486a'
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

			        //拖重计算
			        calculable : false,

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


			        grid: {
			        	borderWidth: 0,
			        	x : 40,
			        	y : 60,
			        	x2 : 60,
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

			        textStyle: {
			        	fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
			        }

	};

	//饼图（蓝-橙-绿（按顺序从浅到深））-------------------------------------------------
	var zdj_pie3 = {
			//默认背景色
			//backgroundColor :'#fff',
			// 默认色板
					//color: [ '#a5daf2','#14abe0','#096791','#fccca7','#ff9812','#9cd8b0','42a400'],

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

			        grid: {
			        	borderWidth: 0,
			        	x : '20%',
			        	y : '20%',
			        	x2 : '20%',
			        	y2 : '10%'
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

			        textStyle: {
			        	fontFamily: '微软雅黑, Arial, Verdana, sans-serif'
			        }

	} ;

	var colorPalette = ['#d87c7c','#919e8b', '#d7ab82',  '#6e7074','#61a0a8','#efa18d', '#787464', '#cc7e63', '#724e58', '#4b565b'];
	var zdj_pie4 = {
			color: colorPalette,
			backgroundColor: '#fef8ef',
			graph: {
				color: colorPalette
			}
	};
	
	
	var zdj_pie5 = {
			color:['#57A3F1','#2E87E2','#62CD28','#9E5483','#FF9812','#EE7C19','#EE582C'],
			title : {
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
		    	top: "38",
			    left: 'right'
		    }
	};
	
	var zdj_pie6 = {
			color:['#57A3F1','#2E87E2','#EE582C','#FF9812','#65CC38','#62CD28','#9E5483','#EE7C19'],
			title : {
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
		    	top: "38",
			    left: 'right'
		    }
	};
	
	echarts.registerTheme( "echarts.theme.pie.pie1", zdj_pie1 );
	echarts.registerTheme( "echarts.theme.pie.pie2", zdj_pie2 );
	echarts.registerTheme( "echarts.theme.pie.pie3", zdj_pie3 );
	echarts.registerTheme( "echarts.theme.pie.pie4", zdj_pie4 );

	echarts.registerTheme( "echarts.theme.pie.pie5", zdj_pie5 );
	echarts.registerTheme( "echarts.theme.pie.pie6", zdj_pie6 );
	
	return {
		pie1: zdj_pie1,
		pie2: zdj_pie2,
		pie3: zdj_pie3,
		pie4: zdj_pie4,
		pie5: zdj_pie5,  //饼图+ 七图例
		pie6: zdj_pie6   //双环 + 五图例
	};

} );