package com.dragon.utils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;

public class JdbcUtils {

	//获取链接
	public static  Connection getConnection() throws SQLException, ClassNotFoundException {
		
		//注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//注册驱动
//				DriverManager.registerDriver(new Driver());
				
		//获取链接
		Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/day07?useUnicode=true&characterEncoding=utf-8", "root", "123");
				
		
		return conn;
	}
	/**
	 * 释放资源
	 */
	public static void closeResource(Connection conn,Statement st,ResultSet rs) {
		
		
	}
	/***
	 * 释放链接
	 * @param conn
	 */
	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		conn = null;
	}
	
	/***
	 * 释放语句执行者
	 * @param st
	 */
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			st = null;
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rs = null;
		}
	}
	
}
