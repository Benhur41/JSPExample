package com.yedam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.employee.EmpDAO;
import com.yedam.employee.EmpDTO;

@WebServlet("/employee/modifyMemberServlet")
public class ModifyMemberServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmpDTO emp = new EmpDTO();
		emp.setEmployeeId(Integer.parseInt(req.getParameter("emp_id")));
		emp.setFirstName(req.getParameter("first_name"));
		emp.setLastName(req.getParameter("last_name"));
		emp.setEmail(req.getParameter("email"));
		int result = EmpDAO.getInstance().updateMember(emp, Integer.parseInt(req.getParameter("eid")));
		if(result > 0) {
			resp.sendRedirect("../main.do");
		}else {
			resp.sendRedirect("../modifyMember.do?emp_id="+Integer.parseInt(req.getParameter("emp_id")));
		}
	}
}
