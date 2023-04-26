package com.yedam.member.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.member.domain.EventVO;

public interface EventMapper {
	//이벤트 목록
	public List<EventVO> getEvent();
	//이벤트 등록
	public int insertEvent(EventVO vo);
	//이벤트 삭제
	public int deleteEvent(String title);
}
