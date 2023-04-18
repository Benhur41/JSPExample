package com.yedam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.employee.EmpDAO;
import com.yedam.employee.EmpDTO;

@WebServlet("/employee/addMemberServlet")
public class AddMemberServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmpDTO emp = new EmpDTO();
		emp.setFirstName(req.getParameter("fname"));
		emp.setLastName(req.getParameter("lname"));
		emp.setEmail(req.getParameter("email"));
		emp.setHireDate(req.getParameter("hire"));
		emp.setJobId(req.getParameter("job"));
		
		int result = EmpDAO.getInstance().empAdd(emp);
		if(result >0) {
			resp.sendRedirect("empList.jsp");
		}else {
			resp.sendRedirect("addMember.jsp");
		}
	}
}
