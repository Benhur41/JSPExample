package com.yedam.domain;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.mapper.NoticeMapper;

public class SampleExe2 {
	public static void main(String[] args) {
		SqlSessionFactory ssf = DataSource.getInstance();
		try (SqlSession session = ssf.openSession(true)) {
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
			  
//			  NoticeVO vo = new NoticeVO();
//			  vo.setNoticeWriter("김태연");
//			  vo.setNoticeTitle("안녕하세요");
//			  vo.setNoticeSubject("ㅎㅇ");
//			  mapper.insertNotice(vo);
			  
			  for(NoticeVO v : mapper.noticeList()) {
				  System.out.println(v);
			  }
			}
	}
}
