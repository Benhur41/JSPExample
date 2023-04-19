package com.yedam.frontControll;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.servlet.Control;
import com.yedam.servlet.EmpListServlet;

public class FrontController extends HttpServlet {
	
	//key & value 저장할 수 있는 컬렉션 . MAP
	Map<String,Control> map;
	
	public FrontController() {
		System.out.println("FrontController() CALL");
		map = new HashMap<>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() CALL");
		map.put("/main.do",new MainControl());
		map.put("/first.do",new FirstControl());
		map.put("/second.do",new SecondControl());
		//사원정보 상세페이지(getMember.jsp)
		map.put("/getMember.do", new GetMemberControl());
		//수정 페이지
		map.put("/modifyMember.do", new ModiFyMemberControl());
		//등록 페이지
		map.put("/addMember.do", new AddMemberControl());
		//삭제기능
		map.put("/deleteMember.do", new DeleteMemberControl());
		//로그인 기능
		map.put("/loginForm.do",new LoginFormControl() );
		map.put("/login.do", new LoginControl());
		map.put("/logout.do", new logoutControl());
		
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		System.out.println("service() CALL");
		//http://localhost:8081/HelloWeb/first.do
		String uri = req.getRequestURI();//  /HelloWeb/first.do
		String context = req.getContextPath(); // 최상위 폴더(=프로젝트 이름) 가져옴 context: /HelloWeb 
		String page = uri.substring(context.length());
		
		System.out.println(page);
		System.out.println(map.get(page));
		
		Control control = map.get(page);
//		if(page.equals("/first.do"))
//		(initServlet)Object).service(req, resp)
//		else if (page.equals("/second.do"))
//			((EmpListServlet)Object).service(req, resp);
		//하나의 인터페이스로 묶어서 동일한 이름의 기능 사용시 호출된 객체의 기능을 사용할수 있게 만듬
		control.exec(req,resp);
	}	
	
	@Override
	public void destroy() {
		System.out.println("destroy() CALL");
	}
}
