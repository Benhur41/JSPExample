package com.yedam.frontControll;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.employee.EmpDTO;
import com.yedam.servlet.Control;

public class FirstControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("firstControl 실행");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out =null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<EmpDTO> list = com.yedam.employee.EmpDAO.getInstance().getEmployeesList();
					
					out.print("<table border ='1'>");
					out.print("<thead><tr><th>사원번호</th><th>FristName</th><th>LastName</th><th>email</th><th>job_id</th></tr></thead>");
					out.print("<tbody>");
					
					//데이터 건수가 반복될 것.
					for(EmpDTO emp : list) {
						out.print("<tr><td><a href='getMember.jsp?emp_id="+ emp.getEmployeeId() +"'>" + emp.getEmployeeId()
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
