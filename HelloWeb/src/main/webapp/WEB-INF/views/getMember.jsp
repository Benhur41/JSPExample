<%@page import="com.yedam.employee.EmpDAO"%>
<%@page import="com.yedam.employee.EmpDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="menu.jsp"></jsp:include>
<jsp:include page="nav.jsp"></jsp:include>
	<%
		
		EmpDTO emp =(EmpDTO)request.getAttribute("empInfo");
		/*EmpDTO result = EmpDAO.getInstance().getEmployees(Integer.valueOf(empId));*/
		if(emp != null){
	%>
	<a href="index.jsp">첫페이지로 이동</a>
	<table class="table">
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
	<a href = "modifyMember.do?emp_id=<%=emp.getEmployeeId()%>">수정/삭제</a>
	<%}else{ %>
	<%response.sendRedirect("main.do");} %>
<jsp:include page="footer.jsp"></jsp:include>