package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.member.domain.EventVO;
import com.yedam.member.service.EventService;
import com.yedam.member.service.EventServiceImpl;

public class AddEventControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EventService service = new EventServiceImpl();
		EventVO vo = new EventVO();
		vo.setTitle(req.getParameter("title"));
		vo.setStartDate(req.getParameter("startDate"));
		vo.setEndDate(req.getParameter("endDate"));
		Map<String , Object> map = new HashMap<>();
		if(service.addEvent(vo)) {
			map.put("retCode", "Success");
		}else {
			map.put("retCode","Fail");
		}
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(map);
		
		return json+".json";
	}

}
