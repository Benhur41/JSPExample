package com.yedam.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.member.domain.MemberVO;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceImpl;

public class LoginControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//MemberService / MemberServiceImpl , MemberMapper.java(interface)
		//service: MemberVO loginCheck(MemberVO vo)
		//mapper: MemberVO loginCheck(MemberVO vo);
		if(req.getMethod().equals("POST")) {
			MemberVO vo = new MemberVO();
			vo.setEmail(req.getParameter("email"));
			vo.setPassword(req.getParameter("pass"));
			MemberService service = new MemberServiceImpl();
			
			if(service.loginCheck(vo)!=null) {
				HttpSession session = req.getSession();
				session.setAttribute("sesInfo", service.loginCheck(vo));
				return "noticeList.do";
			}else {
				return "loginForm.do";
			}
		}
		return "loginForm.do";
	}

}
