package com.yedam.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.mapper.NoticeMapper;

public class SampleExe3 {
	public static void main(String[] args) {
		SqlSessionFactory ssf = DataSource.getInstance();
		try (SqlSession session = ssf.openSession(true)) {
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
			List<NoticeVO> list = mapper.noticeWithPage(3);
			for(NoticeVO vo : list) {
				System.out.println(vo);
			}
		}
	}
}
