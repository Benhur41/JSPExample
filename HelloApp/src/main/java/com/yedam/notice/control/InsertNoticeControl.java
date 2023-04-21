package com.yedam.notice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.member.domain.MemberVO;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class InsertNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				if (req.getMethod().equals("POST")) {
					// 멀티파트 요청: 요청정보, 저장경로, 최대 파일사이즈, 인코딩, 리네임정책인스턴스.
					String saveDir = req.getServletContext().getRealPath("images");// 저장경로 설정
					int maxSize = 5 * 1024 * 1024;
					String encoding = "UTF-8";
					DefaultFileRenamePolicy rn = new DefaultFileRenamePolicy();
					MultipartRequest multi = new MultipartRequest(req, saveDir, maxSize, encoding, rn);

					NoticeService service = new NoticeServiceImpl();
					NoticeVO vo = new NoticeVO();
					vo.setNoticeWriter(multi.getParameter("writer"));
					vo.setNoticeTitle(multi.getParameter("title"));
					vo.setNoticeSubject(multi.getParameter("subject"));
					vo.setAttachFile(multi.getFilesystemName("file"));
					boolean result = service.addNotice(vo);

					if (result) {
						return "noticeList.do";
					} else {
						return "notice/addNotice.tiles";
					}
				}
				return "notice/addNotice.tiles";
			}

	}
