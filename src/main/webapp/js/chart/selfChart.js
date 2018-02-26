var dataList;
/**
 * 获取主页的数据图表
 * @returns
 */
function getIndexChart(url){
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : url,
		data : [],
		success : function(json) {
			var list = ["文章数量","爬虫任务数量","用户数量"]
			getBarChart(list,json.num)
		},
		error : function(data) {
			console.log(data);
		}
	});
}

/**
 * 查看最近几天的趋势
 * @param url
 * @param data
 * @returns
 */
function getCrawlArticle(url,data,docId){
	$.ajax({
		type : "GET",
		dataType : "JSON",
		url : url,
		data : data,
		success : function(json) {
			var list = []
			for(var i = 1 ;i<=data.day ; i++){
				list[i-1] = i;
			}
			getLinkChart(list,json.num,docId)
		},
		error : function(data) {
			console.log(data);
		}
	});
	
}

/**
 * 获取柱状图
 */
function getBarChart(list,dataList){
	var barChartData = {
            labels: list,
            datasets: [{
                label: '',
                backgroundColor:'rgba(77, 201, 246, 0.5)',
                borderColor: 'rgba(77, 201, 246, 0.5)',
                borderWidth: 1,
                data: dataList
            }]
        };
	var ctx = document.getElementById("canvas").getContext("2d");
    window.myBar = new Chart(ctx, {
        type: 'bar',
        data: barChartData,
        options: {
            responsive: true,
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: ''
            }
        }
    });
}
/**
 * 获取条形图
 * @param list 横坐标
 * @param data 纵坐标
 * @returns
 */
function getLinkChart(list,dataList,docId){
	var config = {
            type: 'line',
            data: {
                labels: list,
                datasets: [{
                    label: "",
                    backgroundColor: window.chartColors.red,
                    borderColor: window.chartColors.red,
                    data:dataList,
                    fill: false,
                }]
            },
            options: {
                responsive: true,
                title:{
                    display:true,
                    text:'最近爬取的数量'
                },
                tooltips: {
                    mode: 'index',
                    intersect: false,
                },
                hover: {
                    mode: 'nearest',
                    intersect: true
                },
                scales: {
                    xAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: '天数'
                        }
                    }],
                    yAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: '数值'
                        }
                    }]
                }
            }
        };
	var ctx = document.getElementById(docId).getContext("2d");
	window.myLine = new Chart(ctx, config);
}