package com.servlet.study.web.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.servlet.study.web.domain.db.DBConnectionMgr;

public class UserDBTest {
	public static void main(String[] args) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		try {
			Connection con = pool.getConnection(); //java와 마리아db가 연결하는 지점
			String sql = "select * from user_mst";
			
			PreparedStatement pstmt = con.prepareStatement(sql); //쿼리 실행하게끔 도와주는 것 
			
			ResultSet rs = pstmt.executeQuery(); // result set rs 자바에 담을 수 있는 공간 = pstmt.executeQuery() F9. 실질적 쿼리 실행
			
			while(rs.next()) {
				System.out.print("코드: ");
				System.out.println(rs.getInt(1)); //여기 결과는 0번이 없다
				System.out.print("아이디: ");
				System.out.println(rs.getString(2));
				System.out.print("비밀번호: ");
				System.out.println(rs.getString(3));
				System.out.print("이름: ");
				System.out.println(rs.getString(4));
				System.out.print("이메일: ");
				System.out.println(rs.getString(5));
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace(); // 무슨 로그인지 알려줌
		}
	}
}
