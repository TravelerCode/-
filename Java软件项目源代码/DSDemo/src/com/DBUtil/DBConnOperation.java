package com.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author sky
 *      数据库连接工具类
 */
public class DBConnOperation {

	public Connection OprateSqlServerDB() {
		//加载SQLServer数据库驱动
		try {
			System.out.println("正在加载数据库驱动程序....");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("加载驱动成功。");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("正在连接数据库....");
			Connection connection = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;database=DriverSystem;user=sa;password=0725;");
			System.out.println("连接成功。");
			return connection;
		} catch (SQLException e) {
			System.out.println("连接失败，请重启程序。");
		}
		return null;
	}
	
	//封装关闭数据库连接功能
	public void closeConnection(Connection con) {
		try {
			if (!con.isClosed()) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void closeResultSet (ResultSet rs) {
		try {
			if (rs!=null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeStatement(Statement stmt) {
		try {
			if (stmt!=null) stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
