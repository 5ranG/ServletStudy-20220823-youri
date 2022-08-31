package com.servlet.study.web.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.servlet.study.web.domain.db.DBConnectionMgr;

public class UserRepository {
	private DBConnectionMgr pool;
	
	public UserRepository() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public List<Map<String, Object>> getUserList() {
		String sql = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			con = pool.getConnection();
			sql = " select * from user_mst ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				
				for(int i = 0; i < rsmd.getColumnCount(); i++) {
					int index = i + 1;
					Object value = null;
					
					if(rsmd.getColumnType(index) == Types.INTEGER) {
						value = rs.getInt(index);
					}else if(rsmd.getColumnType(index) == Types.VARCHAR) {
						value = rs.getString(index);
					}else if(rsmd.getColumnType(index) == Types.TIMESTAMP) {
						value = rs.getTimestamp(index).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
					}
					
					map.put(rsmd.getColumnName(index), value);
				}
				list.add(map);
			}
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			System.out.println(gson.toJson(list));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // 중요! 반납하는 과정
		}
		
		return list;
	}

}
