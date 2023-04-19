package com.yedam.notice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class InsertNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getMethod().equals("POST")) {
		NoticeService service = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNoticeWriter(req.getParameter("writer"));
		vo.setNoticeTitle(req.getParameter("title"));
		vo.setNoticeSubject(req.getParameter("subject"));
		boolean result = service.addNotice(vo);
		
			if(result) {
				return "noticeList.do";
			}else {
				return "WEB-INF/views/notice/addNotice.jsp";
			}
		}
			return "WEB-INF/views/notice/addNotice.jsp";
		}
	}

