package com.yedam.frontControll;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.employee.EmpDAO;
import com.yedam.employee.EmpDTO;
import com.yedam.servlet.Control;

public class ModiFyMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {

		// View(jsp)에서 요청한 method를 구분. GET/POST 구분

		if (req.getMethod().equals("GET")) {
			// emp_id 파라미터
			// MVC 패턴 컨트롤러에서 DB의 처리 . view 화면으로 정보를 전송
			// emp변수에 조회 결과를 담아서 empInfo의 속성으로 modifyMember.jsp 재지정.
			EmpDTO emp = EmpDAO.getInstance().getEmployees(Integer.parseInt(req.getParameter("emp_id")));
			req.setAttribute("empInfo", emp);

			try {
				req.getRequestDispatcher("WEB-INF/views/modifyMember.jsp").forward(req, resp);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (req.getMethod().equals("POST")) {
			//db 업데이트 처리. 정상 처리시 목록으로 이동.
			EmpDTO emp = new EmpDTO();
			emp.setFirstName(req.getParameter("first_name"));
			emp.setLastName(req.getParameter("last_name"));
			emp.setEmail(req.getParameter("email"));
			emp.setHireDate(req.getParameter("hire"));
			emp.setEmployeeId(Integer.parseInt(req.getParameter("emp_id")));
			int result = EmpDAO.getInstance().updateMember(emp, Integer.parseInt(req.getParameter("eid")));
			if(result >0) {
				try {
					resp.sendRedirect("main.do");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				try {
					resp.sendRedirect("modifyMember.do?emp_id="+Integer.parseInt(req.getParameter("eid")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
