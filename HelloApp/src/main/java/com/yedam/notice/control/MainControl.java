package com.yedam.notice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

public class MainControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("myName", "김태연");
		return "notice/welcome.tiles"; // forward 기능을  forontController 에 놔둠
	}

}
