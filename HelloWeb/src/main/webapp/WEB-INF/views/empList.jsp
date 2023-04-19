<%@page import="com.yedam.employee.EmpDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.yedam.employee.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="menu.jsp"></jsp:include>
<jsp:include page="nav.jsp"></jsp:include>
	<%
	/*List<EmpDTO> list = EmpDAO.getInstance().getEmployeesList();*/
	List<EmpDTO> list = (List<EmpDTO>)request.getAttribute("listInfo");//반환 타입이 Object 라서 캐스팅해야한다
	
	String fname = (String)request.getAttribute("reqInfo");
	String lname = (String)session.getAttribute("sesInfo");
	
	%>
	
	<p>Request: <%=fname %></p>
	<p>Session: <%=lname %></p>
	<table class="table"  >
		<thead>
			<tr><th>사원번호</th><th>이름</th><th>이메일</th></tr>
		</thead>
		<tbody>
			<% for(EmpDTO e : list) {%>
			<tr><td><a href="getMember.do?emp_id=<%=e.getEmployeeId()%>"><%=e.getEmployeeId() %></a></td>
				<td><%=e.getLastName()+ " " +e.getFirstName() %></td>
				<td><%=e.getEmail() %></td>
			</tr>
				<%} %>
		</tbody>
	</table>
	
	<a href="addMember.do">사원추가</a>
	<a href="index.jsp">첫 페이지로</a>
<jsp:include page="footer.jsp"></jsp:include>