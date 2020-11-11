package com.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author sky
 *      ���ݿ����ӹ�����
 */
public class DBConnOperation {

	public Connection OprateSqlServerDB() {
		//����SQLServer���ݿ�����
		try {
			System.out.println("���ڼ������ݿ���������....");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("���������ɹ���");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("�����������ݿ�....");
			Connection connection = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;database=DriverSystem;user=sa;password=0725;");
			System.out.println("���ӳɹ���");
			return connection;
		} catch (SQLException e) {
			System.out.println("����ʧ�ܣ�����������");
		}
		return null;
	}
	
	//��װ�ر����ݿ����ӹ���
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
