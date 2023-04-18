package com.yedam.frontControll;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.employee.EmpDAO;
import com.yedam.employee.EmpDTO;
import com.yedam.servlet.Control;

public class AddMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		if(req.getMethod().equals("GET")) {
			try {
				req.getRequestDispatcher("WEB-INF/views/addMember.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(req.getMethod().equals("POST")) {
			EmpDTO emp = new EmpDTO();
			emp.setFirstName(req.getParameter("fname"));
			emp.setLastName(req.getParameter("lname"));
			emp.setEmail(req.getParameter("email"));
			emp.setHireDate(req.getParameter("hire"));
			emp.setJobId(req.getParameter("job"));
			int result = EmpDAO.getInstance().empAdd(emp);
			
			if(result >0) {
				try {
					resp.sendRedirect("main.do");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					resp.sendRedirect("addMember.do");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
