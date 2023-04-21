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

public class ModifyMemberControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getMethod().equals("POST")) {
			MemberService service = new MemberServiceImpl();
			MemberVO vo = new MemberVO();
			vo.setEmail(req.getParameter("email"));
			vo.setPassword(req.getParameter("pass"));
			vo.setName(req.getParameter("name"));
			vo.setPhone(req.getParameter("phone"));

			if (service.modifyMember(vo)) {
				HttpSession session = req.getSession();
				session.setAttribute("sesInfo",service.loginCheck(vo));
				return "noticeList.do";
			} else {
				return "members/modifyMember.tiles";
			}
		} else if(req.getMethod().equals("GET")){
			return "members/modifyMember.tiles";
		}
		return "members/modifyMember.tiles";

	}
}
