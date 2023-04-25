package com.yedam.notice.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.notice.domain.ReplyVO;
import com.yedam.notice.service.ReplyService;
import com.yedam.notice.service.ReplyServiceImpl;

public class ModifyReplyControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int replyId = Integer.parseInt(req.getParameter("rid"));
		String reply = req.getParameter("reply");
		ReplyVO vo = new ReplyVO();
		vo.setReplyId(replyId);
		vo.setReply(reply);
		String json="";
		Map<String , Object> map = new HashMap<>();
		ReplyService service = new ReplyServiceImpl();
		if(service.modifyReply(vo)) {
			ReplyVO vo2 = service.searchReply(replyId);
			map.put("retCode","Success");
			map.put("data", vo2);
		}else {
			map.put("retCode","Fail");
		}
		
		Gson gson = new GsonBuilder().create();
		json = gson.toJson(map);
		return json+".json";
	}

}
