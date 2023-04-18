package com.yedam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.employee.EmpDAO;

@WebServlet("/employee/delMemberServlet")
public class DelMemberServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = EmpDAO.getInstance().empDelete(Integer.parseInt(req.getParameter("eid")));
		
		if(result >0) {
			resp.sendRedirect("empList.jsp");
		}else {
			resp.sendRedirect("delMember.jsp");
		}
	}
}
