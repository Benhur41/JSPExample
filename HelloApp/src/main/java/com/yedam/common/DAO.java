package com.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAO {
protected Connection conn = null;
	
	//select(조회) 결과값을 반환 받는 객체
	//select 한 결과를 java 로 전달
	protected ResultSet rs = null;
	
	//Query 문을 (sql) 을 가지고 실행하는 객체
	//1)sql 문 실행해주는 객체
	protected PreparedStatement pstmt = null;
	
	//2)sql 문 실행해주는 객체
	protected Statement stmt = null;
	
	
	//DB접속 정보
	String driver = "oracle.jdbc.driver.OracleDriver";//연결시 어떤 드라이버를 사용하는가
	                              // 접속할 IP :PORT:DB이름(SID) - 포트랑 SID 는 developer 에서 (사용자 > 우클릭 > 속성) 에서 볼수있다.
	String url = "jdbc:oracle:thin:@localhost:1521:xe";//데이터 베이스 접속할수 있는 url
	
	String id = "hr";
	String pw = "hr";
	
	
	//DB 연결
	public void conn() {
		try {
			//1. 드라이버 로딩
			Class.forName(driver);
			//2. DB 연결
			conn = DriverManager.getConnection(url,id,pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//DB 연결 해제
	public void disconn() {
		try {
			//결과조회 객체
			if(rs != null) {
				rs.close();
			}
			//sql 실행객체 (Query 문으로)
			if(stmt != null) {
				stmt.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			//db 연결객체
			if(conn != null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
