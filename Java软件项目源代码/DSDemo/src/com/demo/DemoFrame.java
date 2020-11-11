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
	int time = 0;  //�򿪴���
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
		f = new JFrame(); // ����һ������������
		f.setLayout(new GridLayout(5, 1));
		f.setTitle("��УѧԱ��Ϣ����ϵͳ");
		f.setSize(600, 350); // ���ÿ������Ĵ�С�ߴ�
		f.setLocation(600, 200);
		
		ta = new TextArea();
		ta.setFont(new Font(null, Font.PLAIN, 22));
		String ad;
		if (isadmin == 1) {
			ad = "����Ա";
		} else {
			ad = "��ͨ�û�";
		}
		JLabel jLabel = new JLabel("��ǰ�û���"+username+"("+ad+")");
		jLabel.setForeground(new Color(24, 153, 134));
		jLabel.setFont(new Font(null, Font.BOLD, 22));
		b1 = new Button("�û���Ϣ����"); // ����һ����ť����
		b1.setBackground(new Color(222, 233, 211));
		b1.setFont(new Font(null, Font.PLAIN, 22));
		b2 = new Button("ѧԱѧ������");
		b2.setBackground(new Color(222, 233, 211));
		b2.setFont(new Font(null, Font.PLAIN, 22));
		b3 = new Button("�˳�ϵͳ");
		b3.setBackground(new Color(222, 233, 211));
		b3.setFont(new Font(null, Font.PLAIN, 22));
		b4 = new Button("�˳���ǰ�û�");
		b4.setBackground(new Color(222, 233, 211));
		b4.setFont(new Font(null, Font.PLAIN, 22));
		//��ӵ���¼�����
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
		f3 = new JFrame("����Ա");
		f3.setLayout(new GridLayout(5, 2));
		f3.setSize(1200, 500); // ���ÿ������Ĵ�С�ߴ�
		f3.setLocation(400, 200);
		JButton SeeUsers = new JButton("�鿴�����û�");
		ButtonMonitorSeeUsers seeusers = new ButtonMonitorSeeUsers();
		SeeUsers.addActionListener(seeusers);
		JButton AddUser = new JButton("�����û�");
		
		JButton DelUser = new JButton("ɾ���û�");
		ButtonMonitorDelUser deluser = new ButtonMonitorDelUser();
		DelUser.addActionListener(deluser);
		JButton AlterUser = new JButton("�޸��û�");
		JPanel jPanel1 = new JPanel();
		jPanel1.setBackground(new Color(222, 233, 211));
		jPanel1.add(SeeUsers);
		jPanel1.add(AddUser);
		JPanel jPanel2 = new JPanel();
		jPanel2.add(DelUser);
		jPanel2.add(AlterUser);
		jPanel2.setBackground(new Color(222, 233, 211));
		
		JLabel UserName = new JLabel("������Ҫ����/�޸ĵ��û���:");
		UserName.setForeground(new Color(67, 44, 21));
		UserName.setFont(new Font(null, Font.BOLD, 30));
		JLabel UserPW = new JLabel("������Ҫ����/�޸ĳɵ���������:");
		UserPW.setForeground(new Color(67, 44, 21));
		UserPW.setFont(new Font(null, Font.BOLD, 30));
		JLabel UserIsAdmin = new JLabel("������Ҫ����/�޸ĵ��Ƿ��ǹ���Ա����?1(��),0(��):");
		UserIsAdmin.setForeground(new Color(67, 44, 21));
		UserIsAdmin.setFont(new Font(null, Font.BOLD, 30));
		
		UserIsAdmin.setBackground(new Color(222, 233, 211));
		UserIsAdmin.setFont(new Font(null, Font.BOLD, 22));
		JTextField UserNameField = new JTextField(10);
		JPasswordField UserPWField = new JPasswordField(10);
		JPasswordField UserIsAdminField = new JPasswordField(1);
		
		JButton Rewrite = new JButton("����");
		Rewrite.setFont(new Font(null, Font.PLAIN, 22));
		JButton Exit = new JButton("�˳��û���Ϣ����");
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
				//�����֮ǰ���ж��û����Ƿ�Ϊ��
				if (UserNameField.getText().equals("")) {
					String message = "���ʧ�ܣ��û���������Ϊ�գ�";
					JOptionPane.showMessageDialog(AddUser, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
				} else {
					DBConnOperation dbOperate = new DBConnOperation();
					Connection conn = dbOperate.OprateSqlServerDB();
					try {
						Statement stmt = conn.createStatement();
						
						if(stmt.executeUpdate( "insert into users values("+"'"+UserNameField.getText()+"', '"+UserPWField.getText()+"', '"+UserIsAdminField.getText()+"');" ) > 0) {
							String message = "��ӳɹ���";
							JOptionPane.showMessageDialog(AddUser, message,"��Ϣ�Ի���",JOptionPane.PLAIN_MESSAGE);
							dbOperate.closeConnection(conn);
						} else {
							String message = "���ʧ�ܣ�";
							JOptionPane.showMessageDialog(AddUser, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
							dbOperate.closeConnection(conn);
						}
					} catch (SQLException e1) {
						String message = "���ʧ�ܣ�";
						JOptionPane.showMessageDialog(AddUser, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
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
					//�ж��û��Ƿ����
					if (!stmt.execute("select * from users where username = '"+Alter_username+"'")) {
						String message = "�û������ڣ�";
						JOptionPane.showMessageDialog(AlterUser, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
						dbConnOperation.closeConnection(conn);
					} else {
						if(stmt.executeUpdate("update users set password = '"+Alter_password+"',isadmin = '"+Alter_isAdmin+
								"' where username = '"+Alter_username+"'") > 0) {
							if (username.equals(Alter_username)) {
								password = Alter_password;
								isadmin = Integer.parseInt(Alter_isAdmin);
							}
							String message = "�޸ĳɹ���";
							JOptionPane.showMessageDialog(AlterUser, message,"��Ϣ�Ի���",JOptionPane.PLAIN_MESSAGE);
							dbConnOperation.closeConnection(conn);
						} else {
							String message = "�޸�ʧ�ܣ������ԣ�";
							JOptionPane.showMessageDialog(AlterUser, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
							dbConnOperation.closeConnection(conn);
						}
					}
					
				} catch (SQLException e1) {
					String message = "�޸�ʧ�ܣ������ԣ�";
					JOptionPane.showMessageDialog(AddUser, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
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
		
		// ������ʾ
		f3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f3.setVisible(true);
	}
	
	public void NotAdmin() {
		f3 = new JFrame("��ͨ�û�");
		// ���ò���
		f3.setLayout(new GridLayout(4, 2));
		jPasswordField_old = new JPasswordField(13);
		jPasswordField_new1 = new JPasswordField(13);
		jPasswordField_new2 = new JPasswordField(13);
		JLabel jLabel1 = new JLabel("������");
		jLabel1.setFont(new Font(null, Font.BOLD, 12));
		JLabel jLabel2 = new JLabel("������");
		jLabel2.setFont(new Font(null, Font.BOLD, 12));
		JLabel jLabel3 = new JLabel("����������");
		jLabel3.setFont(new Font(null, Font.BOLD, 12));
		jb1 = new JButton("�޸�����");
		jb1.setFont(new Font(null, Font.BOLD, 12));
		JButton jb2 = new JButton("�˳�");
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
		jp4.add(jb1);  //���һ���������޸�����
		jp4.add(jb2);  //���һ���������˳�
		
		//���ð�ť�¼�����
		//�޸����밴ť�¼�
		jb1.addActionListener(new ButtonMonitor4());
		//�˳���ť�¼�
		jb2.addActionListener(new ButtonMonitor3(f3));
		
		f3.add(jp1);
		f3.add(jp2);
		f3.add(jp3);
		f3.add(jp4);

		// ������ʾ
		f3.setSize(500, 300);
		f3.setBackground(new Color(255, 0, 211));
		f3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f3.setLocation(600, 400);
		f3.setVisible(true);
	}
	/**
	   *  �û���Ϣ����ť��������¼�����
	 */
	class ButtonMonitor1 implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			f.dispose();
			//�ж��Ƿ�Ϊ����Ա
			if (isadmin == 1) {
				Admin();  //�������Ա��Ϣ�������
			} else {
				NotAdmin();  //������ͨ�û���Ϣ�������
			}

		}
		
	}
	
	/**
	   *  ѧԱѧ������ť��������¼�����
	 */
	class ButtonMonitor2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
	/**
	   *  �˳���ť��������¼�����
	 */
	class ButtonMonitor3 implements ActionListener {
		JFrame jframe;
		
		public ButtonMonitor3(JFrame jframe) {
			// TODO Auto-generated constructor stub
			this.jframe = jframe;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			jframe.dispose();  //�˳��ڲ�ϵͳ
			if (e.getActionCommand().equals("�˳���ǰ�û�")) {
				new LoginVerify();
			} else if (jframe.getTitle().equals("��УѧԱ��Ϣ����ϵͳ")) {
				System.exit(0);
			} else {
				f.show();
			}
			
		}	
		
	}
	
	//�޸����밴ť�¼�
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
						password = jPasswordField_new1.getText();  //����ϵͳ�м�¼������
						String message = "�޸ĳɹ���";
						JOptionPane.showMessageDialog(jb1, message,"��Ϣ�Ի���",JOptionPane.PLAIN_MESSAGE);
						dbConnOperation.closeStatement(statement);
						dbConnOperation.closeConnection(conn);
					};
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else if (!password.equals(jPasswordField_old.getText())) {
				String message = "���������������������������룡";
				JOptionPane.showMessageDialog(jb1, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
				jPasswordField_old.setText("");
			} else if (!jPasswordField_new1.getText().equals(jPasswordField_new2.getText())) {
				String message = "���������벻һ�£����������룡";
				JOptionPane.showMessageDialog(jb1, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
				
			}
			
			
		}
		
	}
	

}
