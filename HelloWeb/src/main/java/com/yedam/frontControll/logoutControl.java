package com.yedam.frontControll;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.servlet.Control;

public class logoutControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.invalidate(); //세션 객체의 정보를 삭제
		try {
			resp.sendRedirect("loginForm.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
