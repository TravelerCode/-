package com.demo;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.DBUtil.DBConnOperation;
import com.XueYuan.XueYuanManagement;

/**
 * @author sky
 *
 */
public class DemoFrame {
	JFrame f;
	JTextField jtf;
	Button b1;
	Button b2;
	Button b3;
	Button b4;
	TextArea ta,ta2;
	JFrame f2;
	JFrame f3;
	int time = 0;  //打开次数
	int isadmin;
	String username;
	String password;
	JPasswordField jPasswordField_old;
	JPasswordField jPasswordField_new1;
	JPasswordField jPasswordField_new2;
	JButton jb1;

	public DemoFrame(String username, String password, int isadmin) {
		this.username = username;
		this.password = password;
		this.isadmin = isadmin;
		init();
	}

	public void init() {
		f = new JFrame(); // 创建一个框容器对象
		f.setLayout(new GridLayout(5, 1));
		f.setTitle("驾校学员信息管理系统");
		f.setSize(600, 350); // 设置框容器的大小尺寸
		f.setLocation(600, 200);
		
		ta = new TextArea();
		ta.setFont(new Font(null, Font.PLAIN, 22));
		String ad;
		if (isadmin == 1) {
			ad = "管理员";
		} else {
			ad = "普通用户";
		}
		JLabel jLabel = new JLabel("当前用户："+username+"("+ad+")");
		jLabel.setForeground(new Color(24, 153, 134));
		jLabel.setFont(new Font(null, Font.BOLD, 22));
		b1 = new Button("用户信息管理"); // 创建一个按钮对象
		b1.setBackground(new Color(222, 233, 211));
		b1.setFont(new Font(null, Font.PLAIN, 22));
		b2 = new Button("学员学籍管理");
		b2.setBackground(new Color(222, 233, 211));
		b2.setFont(new Font(null, Font.PLAIN, 22));
		b3 = new Button("退出系统");
		b3.setBackground(new Color(222, 233, 211));
		b3.setFont(new Font(null, Font.PLAIN, 22));
		b4 = new Button("退出当前用户");
		b4.setBackground(new Color(222, 233, 211));
		b4.setFont(new Font(null, Font.PLAIN, 22));
		//添加点击事件处理
		b1.addActionListener(new ButtonMonitor1());
		b3.addActionListener(new ButtonMonitor3(f));
		b4.addActionListener(new ButtonMonitor3(f));
		b2.addActionListener(new XueYuanManagement(f, username, password, isadmin));
		
		f.add(jLabel);
		f.add(b1);
		f.add(b2);
		f.add(b4);
		f.add(b3);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		f.setVisible(true);
	}

	public void Admin() {
		f3 = new JFrame("管理员");
		f3.setLayout(new GridLayout(5, 2));
		f3.setSize(1200, 500); // 设置框容器的大小尺寸
		f3.setLocation(400, 200);
		JButton SeeUsers = new JButton("查看所有用户");
		ButtonMonitorSeeUsers seeusers = new ButtonMonitorSeeUsers();
		SeeUsers.addActionListener(seeusers);
		JButton AddUser = new JButton("增加用户");
		
		JButton DelUser = new JButton("删除用户");
		ButtonMonitorDelUser deluser = new ButtonMonitorDelUser();
		DelUser.addActionListener(deluser);
		JButton AlterUser = new JButton("修改用户");
		JPanel jPanel1 = new JPanel();
		jPanel1.setBackground(new Color(222, 233, 211));
		jPanel1.add(SeeUsers);
		jPanel1.add(AddUser);
		JPanel jPanel2 = new JPanel();
		jPanel2.add(DelUser);
		jPanel2.add(AlterUser);
		jPanel2.setBackground(new Color(222, 233, 211));
		
		JLabel UserName = new JLabel("请输入要增加/修改的用户名:");
		UserName.setForeground(new Color(67, 44, 21));
		UserName.setFont(new Font(null, Font.BOLD, 30));
		JLabel UserPW = new JLabel("请输入要增加/修改成的密码密码:");
		UserPW.setForeground(new Color(67, 44, 21));
		UserPW.setFont(new Font(null, Font.BOLD, 30));
		JLabel UserIsAdmin = new JLabel("请输入要增加/修改的是否是管理员设置?1(是),0(否):");
		UserIsAdmin.setForeground(new Color(67, 44, 21));
		UserIsAdmin.setFont(new Font(null, Font.BOLD, 30));
		
		UserIsAdmin.setBackground(new Color(222, 233, 211));
		UserIsAdmin.setFont(new Font(null, Font.BOLD, 22));
		JTextField UserNameField = new JTextField(10);
		JPasswordField UserPWField = new JPasswordField(10);
		JPasswordField UserIsAdminField = new JPasswordField(1);
		
		JButton Rewrite = new JButton("重置");
		Rewrite.setFont(new Font(null, Font.PLAIN, 22));
		JButton Exit = new JButton("退出用户信息管理");
		Exit.setFont(new Font(null, Font.PLAIN, 22));
		JPanel jPanel7 = new JPanel();
		jPanel7.add(Rewrite);
		jPanel7.add(Exit);
		Rewrite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserNameField.setText("");
				UserPWField.setText("");
				UserIsAdminField.setText("");
			}
		});
		Exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f3.dispose();
				try {
					deluser.jfdel.dispose();
				} catch (Exception e2) {}
				try {
					seeusers.jfsee.dispose();
				} catch (Exception e3) {}
				new DemoFrame(username, password, isadmin);
			}
		});
		
		AddUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//在添加之前先判断用户名是否为空
				if (UserNameField.getText().equals("")) {
					String message = "添加失败，用户名不允许为空！";
					JOptionPane.showMessageDialog(AddUser, message,"消息对话框",JOptionPane.WARNING_MESSAGE);
				} else {
					DBConnOperation dbOperate = new DBConnOperation();
					Connection conn = dbOperate.OprateSqlServerDB();
					try {
						Statement stmt = conn.createStatement();
						
						if(stmt.executeUpdate( "insert into users values("+"'"+UserNameField.getText()+"', '"+UserPWField.getText()+"', '"+UserIsAdminField.getText()+"');" ) > 0) {
							String message = "添加成功！";
							JOptionPane.showMessageDialog(AddUser, message,"消息对话框",JOptionPane.PLAIN_MESSAGE);
							dbOperate.closeConnection(conn);
						} else {
							String message = "添加失败！";
							JOptionPane.showMessageDialog(AddUser, message,"消息对话框",JOptionPane.WARNING_MESSAGE);
							dbOperate.closeConnection(conn);
						}
					} catch (SQLException e1) {
						String message = "添加失败！";
						JOptionPane.showMessageDialog(AddUser, message,"消息对话框",JOptionPane.WARNING_MESSAGE);
						dbOperate.closeConnection(conn);
					}
				}
			}
		});
		AlterUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DBConnOperation dbConnOperation = new DBConnOperation();
				Connection conn = dbConnOperation.OprateSqlServerDB();
				String Alter_username = UserNameField.getText();
				String Alter_password = UserPWField.getText();
				String Alter_isAdmin = UserIsAdminField.getText();
				try {
					Statement stmt = conn.createStatement();
					//判断用户是否存在
					if (!stmt.execute("select * from users where username = '"+Alter_username+"'")) {
						String message = "用户不存在！";
						JOptionPane.showMessageDialog(AlterUser, message,"消息对话框",JOptionPane.WARNING_MESSAGE);
						dbConnOperation.closeConnection(conn);
					} else {
						if(stmt.executeUpdate("update users set password = '"+Alter_password+"',isadmin = '"+Alter_isAdmin+
								"' where username = '"+Alter_username+"'") > 0) {
							if (username.equals(Alter_username)) {
								password = Alter_password;
								isadmin = Integer.parseInt(Alter_isAdmin);
							}
							String message = "修改成功！";
							JOptionPane.showMessageDialog(AlterUser, message,"消息对话框",JOptionPane.PLAIN_MESSAGE);
							dbConnOperation.closeConnection(conn);
						} else {
							String message = "修改失败，请重试！";
							JOptionPane.showMessageDialog(AlterUser, message,"消息对话框",JOptionPane.WARNING_MESSAGE);
							dbConnOperation.closeConnection(conn);
						}
					}
					
				} catch (SQLException e1) {
					String message = "修改失败，请重试！";
					JOptionPane.showMessageDialog(AddUser, message,"消息对话框",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		f3.add(jPanel1);
		f3.add(jPanel2);
		
		f3.add(UserName);
		f3.add(UserNameField);
		f3.add(UserPW);
		f3.add(UserPWField);
		f3.add(UserIsAdmin);
		f3.add(UserIsAdminField);
		f3.add(Rewrite);
		f3.add(Exit);
		
		// 设置显示
		f3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f3.setVisible(true);
	}
	
	public void NotAdmin() {
		f3 = new JFrame("普通用户");
		// 设置布局
		f3.setLayout(new GridLayout(4, 2));
		jPasswordField_old = new JPasswordField(13);
		jPasswordField_new1 = new JPasswordField(13);
		jPasswordField_new2 = new JPasswordField(13);
		JLabel jLabel1 = new JLabel("旧密码");
		jLabel1.setFont(new Font(null, Font.BOLD, 12));
		JLabel jLabel2 = new JLabel("新密码");
		jLabel2.setFont(new Font(null, Font.BOLD, 12));
		JLabel jLabel3 = new JLabel("重输新密码");
		jLabel3.setFont(new Font(null, Font.BOLD, 12));
		jb1 = new JButton("修改密码");
		jb1.setFont(new Font(null, Font.BOLD, 12));
		JButton jb2 = new JButton("退出");
		jb2.setFont(new Font(null, Font.BOLD, 12));
		JPanel jp1 = new JPanel();
		jp1.setBackground(new Color(222, 233, 211));
		JPanel jp2 = new JPanel();
		jp2.setBackground(new Color(222, 233, 211));
		JPanel jp3 = new JPanel();
		jp3.setBackground(new Color(222, 233, 211));
		JPanel jp4 = new JPanel();
		jp4.setBackground(new Color(222, 233, 211));

		jp1.add(jLabel1);
		jp1.add(jPasswordField_old);
		jp2.add(jLabel2);
		jp2.add(jPasswordField_new1);
		jp3.add(jLabel3);
		jp3.add(jPasswordField_new2);
		jp4.add(jb1);  //最后一个面板添加修改密码
		jp4.add(jb2);  //最后一个面板添加退出
		
		//设置按钮事件处理
		//修改密码按钮事件
		jb1.addActionListener(new ButtonMonitor4());
		//退出按钮事件
		jb2.addActionListener(new ButtonMonitor3(f3));
		
		f3.add(jp1);
		f3.add(jp2);
		f3.add(jp3);
		f3.add(jp4);

		// 设置显示
		f3.setSize(500, 300);
		f3.setBackground(new Color(255, 0, 211));
		f3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f3.setLocation(600, 400);
		f3.setVisible(true);
	}
	/**
	   *  用户信息管理按钮被点击的事件处理
	 */
	class ButtonMonitor1 implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			f.dispose();
			//判断是否为管理员
			if (isadmin == 1) {
				Admin();  //进入管理员信息管理界面
			} else {
				NotAdmin();  //进入普通用户信息管理界面
			}

		}
		
	}
	
	/**
	   *  学员学籍管理按钮被点击的事件处理
	 */
	class ButtonMonitor2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
	/**
	   *  退出按钮被点击的事件处理
	 */
	class ButtonMonitor3 implements ActionListener {
		JFrame jframe;
		
		public ButtonMonitor3(JFrame jframe) {
			// TODO Auto-generated constructor stub
			this.jframe = jframe;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			jframe.dispose();  //退出内部系统
			if (e.getActionCommand().equals("退出当前用户")) {
				new LoginVerify();
			} else if (jframe.getTitle().equals("驾校学员信息管理系统")) {
				System.exit(0);
			} else {
				f.show();
			}
			
		}	
		
	}
	
	//修改密码按钮事件
	class ButtonMonitor4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (password.equals(jPasswordField_old.getText()) && jPasswordField_new1.getText().equals(jPasswordField_new2.getText())) {
				DBConnOperation dbConnOperation = new DBConnOperation();
				Connection conn = dbConnOperation.OprateSqlServerDB();  //Connect to sql server
				try {
					Statement statement = conn.createStatement();
					if (statement.executeUpdate("update users set password = "+jPasswordField_new1.getText()+" where username = '"+username+"' and password = '"+password+"'")
							> 0) {
						password = jPasswordField_new1.getText();  //更新系统中记录的密码
						String message = "修改成功！";
						JOptionPane.showMessageDialog(jb1, message,"消息对话框",JOptionPane.PLAIN_MESSAGE);
						dbConnOperation.closeStatement(statement);
						dbConnOperation.closeConnection(conn);
					};
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else if (!password.equals(jPasswordField_old.getText())) {
				String message = "旧密码输入错误，请重新输入旧密码！";
				JOptionPane.showMessageDialog(jb1, message,"消息对话框",JOptionPane.WARNING_MESSAGE);
				jPasswordField_old.setText("");
			} else if (!jPasswordField_new1.getText().equals(jPasswordField_new2.getText())) {
				String message = "新密码输入不一致，请重新输入！";
				JOptionPane.showMessageDialog(jb1, message,"消息对话框",JOptionPane.WARNING_MESSAGE);
				
			}
			
			
		}
		
	}
	

}
