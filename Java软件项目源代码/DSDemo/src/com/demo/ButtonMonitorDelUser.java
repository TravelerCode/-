package com.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.DBUtil.DBConnOperation;

public class ButtonMonitorDelUser implements ActionListener {
	JFrame jfdel;
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			this.jfdel.dispose();
		} catch (Exception e2) {}
		jfdel = new JFrame("ɾ���û�");
		jfdel.setLayout(new GridLayout(2,2));
		JLabel UserNameL = new JLabel("Ҫɾ�����û�����");
		UserNameL.setFont(new Font(null, Font.TYPE1_FONT, 18));
		JTextField usernameJF = new JTextField(13);
		JPanel jp1 = new JPanel();
		jp1.add(UserNameL);
		jp1.add(usernameJF);
		jp1.setBackground(new Color(100, 200, 150));
		jfdel.add(jp1);
		JButton yes = new JButton("ɾ��");
		yes.setBackground(new Color(222, 255, 211));
		yes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DBConnOperation dbConnOperation = new DBConnOperation();
				Connection conn = dbConnOperation.OprateSqlServerDB();
				//��sqlע��
				if (usernameJF.getText().contains("'") || usernameJF.getText().contains("=") || usernameJF.getText().contains("\"")) {
					String message = "ɾ���Ƿ���";
					JOptionPane.showMessageDialog(yes, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
					usernameJF.setText("");
					dbConnOperation.closeConnection(conn);
				}
				
				String sql = "delete from users where username = '"+usernameJF.getText()+"'";
				try {
					if (conn.createStatement().executeUpdate(sql) > 0) {
							String message = "ɾ���ɹ���";
							JOptionPane.showMessageDialog(yes, message,"��Ϣ�Ի���",JOptionPane.PLAIN_MESSAGE);
							dbConnOperation.closeConnection(conn);
						} else {
							String message = "�û������ڣ�";
							JOptionPane.showMessageDialog(yes, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
							dbConnOperation.closeConnection(conn);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						String message = "ɾ��ʧ�ܣ�";
						JOptionPane.showMessageDialog(yes, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
				}
		});
		JButton no = new JButton("ȡ��");
		no.setBackground(new Color(222, 255, 211));
		no.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfdel.dispose();
			}
		});
		JPanel jp2 = new JPanel();
		jp2.add(yes);
		jp2.add(no);
		jp2.setBackground(new Color(222, 233, 211));
		jfdel.add(jp2);
		jfdel.setBounds(600, 400, 350, 180);
		jfdel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jfdel.setVisible(true);
	}

}
