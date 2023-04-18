package com.yedam.frontControll;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.servlet.Control;

public class LoginFormControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			req.getRequestDispatcher("WEB-INF/views/loginform.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
