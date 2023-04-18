package com.yedam.frontControll;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.employee.EmpDAO;
import com.yedam.servlet.Control;

public class DeleteMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		int result = EmpDAO.getInstance().empDelete(Integer.parseInt(req.getParameter("emp_id")));
		if(result >0) {
			try {
				resp.sendRedirect("main.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				resp.sendRedirect("modifyMember.do?emp_id="+Integer.parseInt(req.getParameter("emp_id")));
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
