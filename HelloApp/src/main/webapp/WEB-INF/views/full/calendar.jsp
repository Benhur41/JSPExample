<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src='/fullcal/dist/index.global.js'></script>
<script>
	let showField = ['title','startDate','endDate'];
	let allEvents =[];
    //allEvents 초기.
   
    
	
		document.addEventListener('DOMContentLoaded'/*페이지가 다 로드되면(event)  */, function() {
				
		fetch('eventList.do')
		.then(resolve => resolve.json())
		.then(result => {
			result.forEach(function (event){
				let newEvent= {
						title:event.title,
						start:event.startDate,
						end:event.endDate
				}
				allEvents.push(newEvent);
					})
		
	    var calendarEl = document.getElementById('calendar');
	
	    var calendar = new FullCalendar.Calendar(calendarEl, {
	      headerToolbar: {
	        left: 'prev,next today',
	        center: 'title',
	        right: 'dayGridMonth,timeGridWeek,timeGridDay'
	      },
	      initialDate: new Date(),//오늘날짜
	      navLinks: true, // can click day/week names to navigate views
	      selectable: true,
	      selectMirror: true,
	      select: function(arg) {
	        var title = prompt('이벤트 등록:');
	        
	        if (title) {
	        	
	        	//Ajax 호출
	        	fetch('addEvent.do',{
	        		method:'post',
	        		headers:{'Content-Type' : 'application/x-www-form-urlencoded'},
	        		body:'title='+title+'&startDate='+arg.startStr+'&endDate='+arg.endStr
	        		
	        	})
	        	.then(resolve => resolve.json())
	        	.then(result => {
	        		if(result.retCode == 'Success'){
	        			calendar.addEvent({
			            title: title,
			            start: arg.start,
			            end: arg.end,
			            allDay: arg.allDay
			          })
	        		}else{
	        			alert('실패');
	        		}
	        	})
	        	.catch(err => console.log(err));
	        	
	         
	        }
	        calendar.unselect()
	      },
	      eventClick: function(arg) {
	        if (confirm('삭제하시겠습니까?')) {
	        	console.log(arg.event._def.title);
	        	//Ajax 삭제
	        	fetch('removeEvent.do',{
	        		method:'post',
	        		headers:{'Content-Type' : 'application/x-www-form-urlencoded'},
	        		body:'title='+arg.event._def.title
	        	})
	        	.then(resolve => resolve.json())
	        	.then(result => {
	        		if(result.retCode =='Success'){
	        			 arg.event.remove();
	        		}else{
	        			alert('실패');
	        		}
	        	})
	         
	        }
	      },
	      editable: true,
	      dayMaxEvents: true, // allow "more" link when too many events
	      events: allEvents
	    });
	
	    calendar.render();
	})
	.catch(function (error) { 
		console.log(error);
	});
	
  
  });

</script>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 1100px;
	margin: 0 auto;
}
</style>
</head>
<body>

	<div id='calendar'></div>

</body>
</html>
