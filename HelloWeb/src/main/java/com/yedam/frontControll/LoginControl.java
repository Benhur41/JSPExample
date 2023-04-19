package com.yedam.frontControll;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.employee.EmpDAO;
import com.yedam.employee.EmpDTO;
import com.yedam.servlet.Control;

public class LoginControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		//사원번호 이메일 정보 넘어온것 받기
		int eid = Integer.parseInt(req.getParameter("uname"));
		String email = req.getParameter("psw");
		email = email.toUpperCase();
		
		EmpDTO emp = EmpDAO.getInstance().loginCheck(eid,email);
		
		if(emp != null) {
			//request객체 / session 객체 
			req.setAttribute("reqInfo", emp.getFirstName());
			HttpSession session = req.getSession();
			session.setAttribute("sesInfo", emp.getLastName());
			
			try {
				req.getRequestDispatcher("main.do").forward(req, resp); // resp.sendRedirext 는 못
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				resp.sendRedirect("loginForm.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
