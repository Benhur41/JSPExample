package com.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class DeleteNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeService service = new NoticeServiceImpl();
		int noticeId = Integer.parseInt(req.getParameter("noticeId"));
		boolean result = service.removeNotice(noticeId);
		if(result) {
			return "noticeList.do";
		}else {
			return // 수정 삭제 기능 붙이기 ~
		}
	}

}
