<%@page import="com.yedam.employee.EmpDAO"%>
<%@page import="com.yedam.employee.EmpDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getMember</title>
</head>
<body>
		
	<%
		
		EmpDTO emp =(EmpDTO)request.getAttribute("empInfo");
		/*EmpDTO result = EmpDAO.getInstance().getEmployees(Integer.valueOf(empId));*/
		if(emp != null){
	%>
	<a href="index.jsp">첫페이지로 이동</a>
	<table border ="1">
		<tr>
			<th>사원번호</th>
			<td><%=emp.getEmployeeId() %>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=emp.getLastName() %>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=emp.getEmail() %>
		</tr>
	</table>
	<a href = "modifyMember.do?emp_id=<%=emp.getEmployeeId()%>">수정</a>
	<%}else{ %>
	<%response.sendRedirect("getMember.jsp");} %>
</body>
</html>