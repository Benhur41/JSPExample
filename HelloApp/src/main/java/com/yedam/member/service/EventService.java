package com.yedam.member.service;

import java.util.List;

import com.yedam.member.domain.EventVO;

public interface EventService {
	public List<EventVO> getEventList();
	public boolean addEvent(EventVO vo);
	public boolean removeEvent(String title);
}
