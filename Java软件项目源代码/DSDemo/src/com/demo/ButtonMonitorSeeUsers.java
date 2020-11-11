package com.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.DBUtil.DBConnOperation;

public class ButtonMonitorSeeUsers implements ActionListener {
	JFrame jfsee;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.jfsee.dispose();
		} catch (Exception e2) {}
		jfsee = new JFrame("所有用户");
		String[] lie = {"用户名","密码","是否管理员"};
		
		ArrayList<String> st1 = new ArrayList<String>();
		ArrayList<String> st2 = new ArrayList<String>();
		ArrayList<String> st3 = new ArrayList<String>();
		
		DBConnOperation dbOperate = new DBConnOperation();
		Connection conn = dbOperate.OprateSqlServerDB();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users");
			while (rs.next()) {
				st1.add(rs.getString(1));
				st2.add(rs.getString(2));
				st3.add(rs.getString(3));
			}
			dbOperate.closeResultSet(rs);
			dbOperate.closeStatement(stmt);
			dbOperate.closeConnection(conn);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String[][] hang = new String[st1.size()][3];
		for (int j = 0; j < st1.size(); j++) {
			hang[j][0] = st1.get(j);
			hang[j][1] = st2.get(j);
			hang[j][2] = st3.get(j);
		}
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(hang,lie);
		JTable jTable = new JTable(defaultTableModel);
		jTable.setBackground(new Color(222, 233, 211));
		jTable.setFont(new Font(null, Font.BOLD, 15));
		jfsee.getContentPane().add(jTable.getTableHeader(), BorderLayout.NORTH);//添加表头进容器
		Container fContainer = jfsee.getContentPane();
		fContainer.setFont(new Font(null, Font.BOLD, 15));
//		fContainer.setSize(jTable.getSize());
		fContainer.add(jTable);
		jfsee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置窗口关闭规则
		jfsee.setBounds(600,350,400,400);//设置窗口初始大小
		jfsee.setVisible(true);//窗口可见
	}

}