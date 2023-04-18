package com.yedam.frontControll;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.employee.EmpDAO;
import com.yedam.employee.EmpDTO;
import com.yedam.servlet.Control;

public class GetMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		//매개값으로 사원번호.받기
		EmpDTO emp = EmpDAO.getInstance().getEmployees(Integer.parseInt(req.getParameter("emp_id")));
		req.setAttribute("empInfo", emp);
		
		//페이지 재지정.control -> getMember.jsp
		try {
			req.getRequestDispatcher("WEB-INF/views/getMember.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
