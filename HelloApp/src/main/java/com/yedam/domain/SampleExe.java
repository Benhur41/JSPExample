package com.yedam.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;

public class SampleExe {
	public static void main(String[] args) {
		SqlSessionFactory ssf = DataSource.getInstance();
		try (SqlSession session = ssf.openSession(true)/*자동 커밋위해 true*/) {
			
			}
	}
}
