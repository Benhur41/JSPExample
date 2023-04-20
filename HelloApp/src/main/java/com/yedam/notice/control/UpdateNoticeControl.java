package com.yedam.notice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class UpdateNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getMethod().equals("POST")) {
			NoticeVO vo = new NoticeVO();
			vo.setNoticeTitle(req.getParameter("title"));
			vo.setNoticeSubject(req.getParameter("subject"));
			vo.setNoticeId(Integer.parseInt(req.getParameter("noticeId")));
			NoticeService service = new NoticeServiceImpl();
			boolean result = service.modifyNotice(vo);
			if(result) {
				return "noticeList.do";
				
			}else {
				return "updateNotice.do?noticeId="+vo.getNoticeId();
			}
		}else if(req.getMethod().equals("GET")){
		int noticeId = Integer.parseInt(req.getParameter("noticeId"));
		NoticeService service = new NoticeServiceImpl();
		NoticeVO vo = service.getNotice(noticeId);
		req.setAttribute("notice", vo);
		return "notice/updateNotice.tiles";
		}
		
		return "notice/updateNotice.tiles";
		
	}

}
