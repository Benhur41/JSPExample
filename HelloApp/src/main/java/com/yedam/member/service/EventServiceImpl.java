package com.yedam.member.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.member.domain.EventVO;
import com.yedam.member.mapper.EventMapper;

public class EventServiceImpl implements EventService {
	
	SqlSession session = DataSource.getInstance().openSession(true);
	EventMapper mapper = session.getMapper(EventMapper.class);
	
	@Override
	public List<EventVO> getEventList() {
		return mapper.getEvent();
	}

	@Override
	public boolean addEvent(EventVO vo) {
		return mapper.insertEvent(vo)==1;
	}

	@Override
	public boolean removeEvent(String title) {
		return mapper.deleteEvent(title)==1;
	}

}
