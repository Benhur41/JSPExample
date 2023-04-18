<%@page import="com.yedam.employee.EmpDAO"%>
<%@page import="com.yedam.employee.EmpDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyMember.jsp</title>
<style>
	input[type="button"]{
		background-color : yellow;
	}
</style>
</head>
<body>
	<%
		EmpDTO emp = (EmpDTO)request.getAttribute("empInfo");
		
	%>
	<form action="modifyMember.do" method="POST">
		<table border ="1">
			<tr><th>수정될 사원번호</th><td><input type="text" name="eid" value="<%=emp.getEmployeeId() %>" readonly></td></tr>
			<tr><th>수정할 사원번호</th><td><input type="text" name="emp_id" value="<%=emp.getEmployeeId() %>"></td></tr>
			<tr><th>FirstName</th><td><input type ="text" name ="first_name" value="<%=emp.getFirstName() %>"></td></tr>
			<tr><th>LastName</th><td><input type="text" name = "last_name" value="<%=emp.getLastName() %>"></td></tr>
			<tr><th>Email</th><td><input type="text" name = "email" value="<%=emp.getEmail()%>"></td></tr>
			
			<tr><td><input type = "submit" value ="수정"><input type="button" value="삭제"></td></tr>
		</table>
	</form>
	<script>
	let btn = document.querySelector('input[type="button"]');
	function deleteEmp(){
		let frm = document.querySelector('form');
		frm.action = "deleteMember.do?emp_id="+<%=emp.getEmployeeId()%>;// 작동이 아닌 선언임
		frm.submit();// 아무것도 없는 button에 submit 기능추가해서 위에 선언한 action 의 형태로 submit 을 하겠다는것
	}
	btn.addEventListener("click",deleteEmp)
	
	</script>
</body>
</html>