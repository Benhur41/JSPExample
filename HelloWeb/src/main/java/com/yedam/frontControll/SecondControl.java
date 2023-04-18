package com.yedam.frontControll;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.servlet.Control;

public class SecondControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("secondControl 실핼");
	}

}
