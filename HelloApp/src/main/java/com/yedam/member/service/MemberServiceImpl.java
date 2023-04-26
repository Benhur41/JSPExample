package com.yedam.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.member.domain.MemberVO;
import com.yedam.member.mapper.MemberMapper;

public class MemberServiceImpl implements MemberService {
	SqlSession session = DataSource.getInstance().openSession(true);
	MemberMapper mapper = session.getMapper(MemberMapper.class);
	@Override
	public MemberVO loginCheck(MemberVO vo) {
		return mapper.loginCheck(vo);
	}
	@Override
	public boolean modifyMember(MemberVO vo) {
		return mapper.modifyMember(vo)==1;
	}
	@Override
	public Map<String, Object> memberByDept() {
		Map<String , Object> result = new HashMap<>();
		
		List<Map<String , Object>> list = mapper.memberByDept();
		//[{Adminstration , 1}, {Accounting , 2} ... {}]
		for(Map<String,Object> map : list) {
			Set<String> set = map.keySet();//key 값만 set 형태로 담기
//			for(String key : set)
				result.put((String)map.get("DEPARTMENT_NAME"),map.get("CNT"));
		}
		return result;
	}
}
