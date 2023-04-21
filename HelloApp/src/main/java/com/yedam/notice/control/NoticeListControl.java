package com.yedam.notice.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.common.PageDTO;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class NoticeListControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeService service = new NoticeServiceImpl();
		
		String pageStr = req.getParameter("page");
		pageStr = pageStr == null? "1": pageStr ;//들어오는 값이 없을시 1로 되게 설정
		int page = Integer.parseInt(pageStr);
		int total = service.totalCount();
		
		List<NoticeVO> list = service.noticeList(page);
		PageDTO dto = new PageDTO(page, total);
		req.setAttribute("pageInfo", dto);
		req.setAttribute("List", list);
//		return "WEB-INF/views/notice/noticeList.jsp";
		return "notice/noticeList.tiles";
		}
	}


