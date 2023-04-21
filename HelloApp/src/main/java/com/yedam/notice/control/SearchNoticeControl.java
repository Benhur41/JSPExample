package com.yedam.notice.control;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class SearchNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		NoticeService service = new NoticeServiceImpl();
		NoticeVO vo = service.getNotice(Integer.parseInt(req.getParameter("noticeId")));
		req.setAttribute("notice", vo);
		req.setAttribute("pageNum", req.getParameter("pageNum"));//게시판 목록 jsp 에서 받아온 현재페이지번호를 조회페이지로 넘긴다.
		if(vo.getAttachFile() != null) {
			String imgPath = req.getServletContext().getRealPath("images");
			Path file = Paths.get(imgPath + "/" + vo.getAttachFile());
			System.out.println(Files.probeContentType(file));
			//image/jpg , image/png,  text/plain
			String fileType = Files.probeContentType(file);
			req.setAttribute("fileType", fileType.substring(0,fileType.indexOf("/")));
		}
		if(vo != null) {	
			return "notice/searchNotice.tiles";
		}else {
			return "noticeList.do";
		}
	}
}
