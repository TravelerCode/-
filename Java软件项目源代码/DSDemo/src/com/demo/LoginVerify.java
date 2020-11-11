package com.demo;

import javax.swing.*;
import com.DBUtil.DBConnOperation;
import java.awt.*; //导入必要的包
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginVerify {
	DBConnOperation dbConnOperation;
	Connection conn;
	String sql;
	JTextField jTextField;  // 定义文本框组件
	JPasswordField jPasswordField;  // 定义密码框组件
	JLabel jLabel1, jLabel2;
	JPanel jp1, jp2, jp3;
	JButton jb1, jb2;  // 创建按钮
	JFrame jf;
	int isadmin;
	String username;
	String password;
	
	public LoginVerify() {
		//创建框体
		jf = new JFrame();
		jTextField = new JTextField(12);
		jPasswordField = new JPasswordField(13);
		jLabel1 = new JLabel("用户名");
		jLabel2 = new JLabel("密码");
		jb1 = new JButton("确认");
		jb2 = new JButton("取消");
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// 设置布局
		jf.setLayout(new GridLayout(3, 1));
		jf.setLayout(new GridBagLayout() {
			private static final long serialVersionUID = 1L;
		});
		
		jp1.add(jLabel1);
		jp1.add(jTextField);// 第一块面板添加用户名和文本框

		jp2.add(jLabel2);
		jp2.add(jPasswordField);// 第二块面板添加密码和密码输入框

		//设置按钮事件处理
		jb1.addActionListener(new ButtonMonitor1());
		jp3.add(jb1);
		jb2.addActionListener(new ButtonMonitor2());
		jp3.add(jb2); // 第三块面板添加确认和取消

		// jp3.setLayout(new FlowLayout()); //因为JPanel默认布局方式为FlowLayout，所以可以注销这段代码.
		jf.add(jp1);
		jf.add(jp2);
		jf.add(jp3); // 将三块面板添加到登陆框上面
		// 设置显示
//		jf.setSize(450, 220);
		jf.setSize(550, 250);
		jf.setBackground(new Color(255, 0, 211));
		// jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("驾校学员信息管理系统登录");
		jf.setLocation(600, 400);
		jf.setVisible(true);
		
		//连接数据库
		dbConnOperation = new DBConnOperation();
		conn = dbConnOperation.OprateSqlServerDB();
		sql = "select * from users";
	}

	class ButtonMonitor1 implements ActionListener {
		/**
		   *  确认按钮被点击的事件处理
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String username = jTextField.getText();
			String password = jPasswordField.getText();
			try {
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				System.out.println("正在验证.....");
				boolean bol = false;
				while (rs.next()) {
					//下面这种验证方式已经可以防止SQL注入
					if (rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
						bol = true;
						isadmin = rs.getInt(3);
						username = rs.getString(1);
						password = rs.getString(2);
						break;
					}
				}
				if (bol) {
					System.out.println("验证成功。");
					jf.dispose();  //撤销当前窗口,并释放当前窗口所使用的资源。
					dbConnOperation.closeResultSet(rs);
					dbConnOperation.closeStatement(statement);
					dbConnOperation.closeConnection(conn);
					new DemoFrame(username, password, isadmin);
				} else {
					System.out.println("验证失败。");
					String message = "用户名或密码输入错误，请重新输入！";
					JOptionPane.showMessageDialog(jb1, message,"消息对话框",JOptionPane.WARNING_MESSAGE);
					dbConnOperation.closeResultSet(rs);
					dbConnOperation.closeStatement(statement);
					jPasswordField.setText("");
				}
			} catch (SQLException e1) {
				String message = "数据库连接错误，请重新启动。";
				JOptionPane.showMessageDialog(jb1, message,"消息对话框",JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (NullPointerException e2) {
				String message = "数据库连接错误，请重新启动。";
				JOptionPane.showMessageDialog(jb1, message,"消息对话框",JOptionPane.WARNING_MESSAGE);
				e2.printStackTrace();
			}
		}
	}
	
	class ButtonMonitor2 implements ActionListener {
		/**
		   *  取消按钮被点击的事件处理
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

}
