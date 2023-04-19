package com.yedam.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;

public class SampleExe {
	public static void main(String[] args) {
		SqlSessionFactory ssf = DataSource.getInstance();
		try (SqlSession session = ssf.openSession(true)/*자동 커밋위해 true*/) {
			//단일조회                                                            //parameterType=int
//			 Employee emp = session.selectOne("com.yedam.common.noticeMapper.getEmp", 100);
			//삭제                                                         //parameterType=int
//			 int result = session.delete("com.yedam.common.noticeMapper.delEmp",222);
			 
//			 Employee empl = new Employee();
//			 empl.setEmployeeId(300);
//			 empl.setLastName("kim");
//			 empl.setEmail("hbj0400033");
//			 empl.setJobId("IT_PROG");                           //parameterType=Employee
//			 session.insert("com.yedam.common.noticeMapper.addEmp",empl);
			 List<Employee> emp = session.selectList("com.yedam.common.NoticeMapper.empList");//파라미터 필요없음
			 System.out.println(emp);
			 
			}
	}
}
