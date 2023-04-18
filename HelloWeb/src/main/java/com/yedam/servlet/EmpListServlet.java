package com.yedam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.employee.EmpDTO;


@WebServlet("/empList")
public class EmpListServlet extends HttpServlet {
	
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		List<EmpDTO> list = com.yedam.employee.EmpDAO.getInstance().getEmployeesList();
					
					out.print("<table border ='1'>");
					out.print("<thead><tr><th>사원번호</th><th>FristName</th><th>LastName</th><th>email</th><th>job_id</th></tr></thead>");
					out.print("<tbody>");
					
					
					//데이터 건수가 반복될 것.
					for(EmpDTO emp : list) {
						out.print("<tr><td><a href='getMemberServlet?emp_id="+ emp.getEmployeeId() +"'>" + emp.getEmployeeId()
						+ "</a></td><td>"+emp.getFirstName()
						+ "</td><td>"+ emp.getLastName() 
						+ "</td><td>" +emp.getEmail() 
						+ "</td><td>" + emp.getJobId() 
						+ "</td></tr>");
					}
					out.print("</tbody>");
					out.print("</table>");
	}
}
