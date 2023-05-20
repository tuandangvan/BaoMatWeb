<!-- chart.jsp-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


<script type="text/javascript">
	window.onload = function() {

		var dps = [];
		var chart = new CanvasJS.Chart("chartContainer", {
			theme : "light2", // "light1", "dark1", "dark2"
			animationEnabled : true,
			title : {
				text : "Thống kê người dùng mới"
			},
			axisX : {
				valueFormatString : "DD-MM-YYYY"
			},
			axisY : {
				title : "Temperature (in °C)",
				includeZero : true,
				suffix : " °C"
			},
			data : [ {
				type : "line",
				xValueType : "dateTime",
				xValueFormatString : "DD-MM-YYYY",
				yValueFormatString : "#",
				dataPoints : dps
			} ]
		});

		var xValue;
		var yValue;

		<c:forEach items="${listuser}" var="item">
		xValue = Date.parse("${item.createdAt}");
		yValue = parseInt("${item.point}");
		dps.push({
			x : xValue,
			y : yValue
		});
		</c:forEach>
		chart.render();

	}
</script>
</head>
<body>
	<div class="container">
		<canvas id="myChart"></canvas>
	</div>
	<script>
    let myChart = document.getElementById('myChart').getContext('2d');
    // Global Options
    Chart.defaults.global.defaultFontFamily = 'Lato';
    Chart.defaults.global.defaultFontSize = 18;
    Chart.defaults.global.defaultFontColor = '#777';

    let massPopChart = new Chart(myChart, {
      type:'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
      data:{
        labels:[<c:forEach items="${listuser}" var="item">'${item.createdAt}',</c:forEach>],
        datasets:[{
          label:'Population',
          data:[<c:forEach items="${listuser}" var="item">${item.point},</c:forEach>],
          backgroundColor:'green',
          
          borderWidth:1,
          borderColor:'#777',
          hoverBorderWidth:3,
          hoverBorderColor:'#000'
        }]
      },
      options:{
        title:{
          display:true,
          text:'Thống kê người dùng mới',
          fontSize:25
        },
        legend:{
          display:true,
          position:'right',
          labels:{
            fontColor:'#000'
          }
        },
        layout:{
          padding:{
            left:50,
            right:0,
            bottom:0,
            top:0
          }
        },
        tooltips:{
          enabled:true
        }
      }
    });
  </script>
	<c:forEach items="${listuser}" var="item">
		<h1>${item.point}</h1>
	</c:forEach>
	<div id="chartContainer" style="height: 370px; width: 100%;"></div>
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>
