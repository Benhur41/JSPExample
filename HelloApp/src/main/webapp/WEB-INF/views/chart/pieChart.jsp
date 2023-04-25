<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript">
    
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
    	  var result = [
    		  ['dept','cnt']
  			];
    	  
          let xhtp = new XMLHttpRequest(); // 비동기방식
          xhtp.open('GET','chartData.do');
          xhtp.send();
          xhtp.onload=function (){
          	let data = JSON.parse(xhtp.response);
          	for(let dept in data){
          		console.log(dept,data[dept]);
          		result.push([dept,data[dept]]);
          	}
          	console.log(result);
        data = google.visualization.arrayToDataTable(result);
        
        var options = {
          title: 'My Daily Activities'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    }
	
      $(function() {

          $("p").on("click", function() {

             $("p").css("color", "red");

          });

      });
    </script>
  </head>
  <body>
    <div id="piechart" style="width: 900px; height: 500px;"></div>
    <p>ㅋㅋ슨</p>
  </body>
</html>
