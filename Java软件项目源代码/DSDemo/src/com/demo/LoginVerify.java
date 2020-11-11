package com.demo;

import javax.swing.*;
import com.DBUtil.DBConnOperation;
import java.awt.*; //�����Ҫ�İ�
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
	JTextField jTextField;  // �����ı������
	JPasswordField jPasswordField;  // ������������
	JLabel jLabel1, jLabel2;
	JPanel jp1, jp2, jp3;
	JButton jb1, jb2;  // ������ť
	JFrame jf;
	int isadmin;
	String username;
	String password;
	
	public LoginVerify() {
		//��������
		jf = new JFrame();
		jTextField = new JTextField(12);
		jPasswordField = new JPasswordField(13);
		jLabel1 = new JLabel("�û���");
		jLabel2 = new JLabel("����");
		jb1 = new JButton("ȷ��");
		jb2 = new JButton("ȡ��");
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// ���ò���
		jf.setLayout(new GridLayout(3, 1));
		jf.setLayout(new GridBagLayout() {
			private static final long serialVersionUID = 1L;
		});
		
		jp1.add(jLabel1);
		jp1.add(jTextField);// ��һ���������û������ı���

		jp2.add(jLabel2);
		jp2.add(jPasswordField);// �ڶ�����������������������

		//���ð�ť�¼�����
		jb1.addActionListener(new ButtonMonitor1());
		jp3.add(jb1);
		jb2.addActionListener(new ButtonMonitor2());
		jp3.add(jb2); // ������������ȷ�Ϻ�ȡ��

		// jp3.setLayout(new FlowLayout()); //��ΪJPanelĬ�ϲ��ַ�ʽΪFlowLayout�����Կ���ע����δ���.
		jf.add(jp1);
		jf.add(jp2);
		jf.add(jp3); // �����������ӵ���½������
		// ������ʾ
//		jf.setSize(450, 220);
		jf.setSize(550, 250);
		jf.setBackground(new Color(255, 0, 211));
		// jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("��УѧԱ��Ϣ����ϵͳ��¼");
		jf.setLocation(600, 400);
		jf.setVisible(true);
		
		//�������ݿ�
		dbConnOperation = new DBConnOperation();
		conn = dbConnOperation.OprateSqlServerDB();
		sql = "select * from users";
	}

	class ButtonMonitor1 implements ActionListener {
		/**
		   *  ȷ�ϰ�ť��������¼�����
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String username = jTextField.getText();
			String password = jPasswordField.getText();
			try {
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				System.out.println("������֤.....");
				boolean bol = false;
				while (rs.next()) {
					//����������֤��ʽ�Ѿ����Է�ֹSQLע��
					if (rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
						bol = true;
						isadmin = rs.getInt(3);
						username = rs.getString(1);
						password = rs.getString(2);
						break;
					}
				}
				if (bol) {
					System.out.println("��֤�ɹ���");
					jf.dispose();  //������ǰ����,���ͷŵ�ǰ������ʹ�õ���Դ��
					dbConnOperation.closeResultSet(rs);
					dbConnOperation.closeStatement(statement);
					dbConnOperation.closeConnection(conn);
					new DemoFrame(username, password, isadmin);
				} else {
					System.out.println("��֤ʧ�ܡ�");
					String message = "�û�������������������������룡";
					JOptionPane.showMessageDialog(jb1, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
					dbConnOperation.closeResultSet(rs);
					dbConnOperation.closeStatement(statement);
					jPasswordField.setText("");
				}
			} catch (SQLException e1) {
				String message = "���ݿ����Ӵ���������������";
				JOptionPane.showMessageDialog(jb1, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (NullPointerException e2) {
				String message = "���ݿ����Ӵ���������������";
				JOptionPane.showMessageDialog(jb1, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
				e2.printStackTrace();
			}
		}
	}
	
	class ButtonMonitor2 implements ActionListener {
		/**
		   *  ȡ����ť��������¼�����
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

}
