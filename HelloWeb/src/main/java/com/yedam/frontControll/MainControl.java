package com.yedam.frontControll;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.employee.EmpDAO;
import com.yedam.employee.EmpDTO;
import com.yedam.servlet.Control;

public class MainControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//페이지 재지정.
			
			List<EmpDTO> list = EmpDAO.getInstance().getEmployeesList();
			
			req.setAttribute("listInfo", list);
			
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/empList.jsp")/*.forward(req, resp)*/;
			rd.forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
