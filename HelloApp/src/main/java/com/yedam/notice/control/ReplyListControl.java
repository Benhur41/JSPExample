package com.yedam.notice.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

public class ReplyListControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//JSON 데이터 생성
		//[{"replyId":5,"noticeId":99,"reply":"98번댓글",...},...]
		ReplyService service = new ReplyServiceImpl();
		int noticeId = Integer.parseInt(req.getParameter("nid"));
		List<ReplyVO> list = service.getReplies(noticeId);
//		String json = "";
//		Map<String,Object> map = new HashMap<>();
//		if(list != null) {
//			map.put("data",list);
//		}else {
//			map.put("retCode", "Fail");
//		}
//		
//		Gson gson = new GsonBuilder().create();
//		json = gson.toJson(map);
//		return json+".json";
		String json = "[";
		for(int i = 0 ; i < list.size() ; i++) {
			json+="{\"replyId\":"+list.get(i).getReplyId() + ",";
			json+="\"noticeId\":"+list.get(i).getNoticeId() +",";
			json+="\"reply\":"+"\""+list.get(i).getReply()+"\",";
			json+="\"replyWriter\":"+"\""+list.get(i).getReplyWriter()+"\",";
			json+="\"replyDate\":"+"\""+list.get(i).getReplyDate()+"\"}";
			if(i +1 != list.size()) {
				json+=",";
			}
		}
		json +="]";
		return json + ".json";

	}

}
