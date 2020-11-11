package com.XueYuan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.DBUtil.DBConnOperation;
import com.demo.DemoFrame;

public class XueYuanManagement implements ActionListener {
	JFrame f;
	String username;
	String password;
	int isadmin;
	JFrame jf;
	JFrame searchjf;
	
	public XueYuanManagement(JFrame f, String username, String password, int isadmin) {
		// TODO Auto-generated constructor stub
		this.f = f;
		this.username = username;
		this.password = password;
		this.isadmin = isadmin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		f.dispose();
		JFrame jfXY = new JFrame("ѧԱѧ������");
		jfXY.setLayout(new GridLayout(5,1));
		
		JButton SearchXueYuanInformation = new JButton("��ѯѧԱ��Ϣ");
		SearchXueYuanInformation.setBackground(new Color(100, 200, 150));
		SearchXueYuanInformation.setFont(new Font(null, Font.PLAIN, 22));
		JButton AlterXueYuanInformation = new JButton("�޸�ѧԱ��Ϣ");
		AlterXueYuanInformation.setBackground(new Color(100, 200, 150));
		AlterXueYuanInformation.setFont(new Font(null, Font.PLAIN, 22));
		JButton DeleteXueYuanInformation = new JButton("ɾ��ѧԱ");
		DeleteXueYuanInformation.setBackground(new Color(100, 200, 150));
		DeleteXueYuanInformation.setFont(new Font(null, Font.PLAIN, 22));
		JButton AddXueYuanInformation = new JButton("����ѧԱ");
		AddXueYuanInformation.setBackground(new Color(100, 200, 150));
		AddXueYuanInformation.setFont(new Font(null, Font.PLAIN, 22));
		JButton Exit = new JButton("�˳�ѧԱ��Ϣ����");
//		.setBackground(new Color(222, 233, 211));
		Exit.setBackground(new Color(222, 233, 211));
		Exit.setFont(new Font(null, Font.PLAIN, 22));
		
		SearchXueYuanInformation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					searchjf.dispose();
				} catch (Exception e2) {}
				searchjf = new JFrame("��ѯѧԱ��Ϣ");
				ArrayList<String> RowID = new ArrayList<String>();
				DBConnOperation dbConnOperation = new DBConnOperation();
				Connection conn = dbConnOperation.OprateSqlServerDB();
				try {
					Statement stmt = conn.createStatement();
//					ResultSet rs1 = stmt.executeQuery("select * from studentinfo a left outer join healthinfo on studentinfo.sno = healthinfo.sno");
					ResultSet rs1 = stmt.executeQuery("select * from studentinfo");
					while (rs1.next()) {
						for (int i = 1; i <= rs1.getRow(); i++) {
							RowID.addAll((Collection<? extends String>) rs1.getRowId(i));
						}
					}
					ResultSet rs2 = stmt.executeQuery("select * from healthinfo");
					while (rs2.next()) {
						for (int i = 1; i <= rs2.getRow(); i++) {
							if (!RowID.contains(rs2.getString(i))) {
								RowID.add(rs2.getString(i));
							}
						}
					}
					ResultSet rs3 = stmt.executeQuery("select * from licenseinfo");
					while (rs3.next()) {
						for (int i = 1; i <= rs3.getRow(); i++) {
							if (!RowID.contains(rs3.getString(i))) {
								RowID.add(rs3.getString(i));
							}
						}
					}
					rs3.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String[] lie = new String[RowID.size()];
				for (int i = 0; i < RowID.size(); i++) {
					lie[i] = RowID.get(i);
				}
				for (String string : lie) {
					System.out.println(string);
				}
				String[][] hang = new String[3][];
				DefaultTableModel defaultTableModel = new DefaultTableModel(hang,lie);
				JTable jTable = new JTable(defaultTableModel);
				jTable.setBackground(new Color(222, 233, 211));
				jTable.setFont(new Font(null, Font.BOLD, 15));
				searchjf.getContentPane().add(jTable.getTableHeader(), BorderLayout.NORTH);//��ӱ�ͷ������
				Container fContainer =  searchjf.getContentPane();
				fContainer.setFont(new Font(null, Font.BOLD, 15));
				fContainer.setSize(jTable.getSize());
				fContainer.add(jTable);
				searchjf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//���ô��ڹرչ���
				searchjf.setBounds(600,350,400,400);//���ô��ڳ�ʼ��С
				searchjf.setVisible(true);//���ڿɼ�
			}
		});
		DeleteXueYuanInformation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					jf.dispose();
				} catch (Exception e2) {}
				jf = new JFrame("ɾ��ѧԱ");
				jf.setLayout(new GridLayout(2,1));
				JLabel UserNameL = new JLabel("Ҫɾ����ѧԱ��ѧ�ţ�");
				UserNameL.setFont(new Font(null, Font.TYPE1_FONT, 18));
				JTextField sno = new JTextField(13);
				JPanel jp1 = new JPanel();
				jp1.add(UserNameL);
				jp1.add(sno);
				jp1.setBackground(new Color(100, 200, 150));
				jf.add(jp1);
				JButton yes = new JButton("ɾ��");
				yes.setBackground(new Color(222, 255, 211));
				yes.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						DBConnOperation dbConnOperation = new DBConnOperation();
						Connection conn = dbConnOperation.OprateSqlServerDB();
						//��sqlע��
						if (sno.getText().contains("'") || sno.getText().contains("=") || sno.getText().contains("\"")) {
							String message = "ɾ���Ƿ���";
							JOptionPane.showMessageDialog(yes, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
							sno.setText("");
						} else {
							String[] sqllist = {"delete from healthinfo where sno = '"+sno.getText()+"'",
									"delete from licenseinfo where sno = '"+sno.getText()+"'",
									"delete from gradeinfo where sno = '"+sno.getText()+"'",
									"delete from studentinfo where sno = '"+sno.getText()+"'"
							};
							boolean[] bollist = {false, false, false, false};
							int index = 0;
							for (String sql : sqllist) {
								try {
									if (conn.createStatement().executeUpdate(sql) > 0) {
										bollist[index] = true;
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								index++;
							}
							if (bollist[0] || bollist[1] || bollist[2] || bollist[3]) {
								String message = "ɾ���ɹ���";
								JOptionPane.showMessageDialog(yes, message,"��Ϣ�Ի���",JOptionPane.PLAIN_MESSAGE);
								dbConnOperation.closeConnection(conn);
							} else {
								String message = "ѧԱ�����ڣ�";
								JOptionPane.showMessageDialog(yes, message,"��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
								dbConnOperation.closeConnection(conn);
							}
						}
					}
				});
				JButton no = new JButton("ȡ��");
				no.setBackground(new Color(222, 255, 211));
				no.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						jf.dispose();
					}
				});
				JPanel jp2 = new JPanel();
				jp2.add(yes);
				jp2.add(no);
				jp2.setBackground(new Color(222, 233, 211));
				jf.add(jp2);
				jf.setBounds(600, 400, 350, 180);
				jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				jf.setVisible(true);
				
			}
		});
		Exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					searchjf.dispose();
					jf.dispose();
				} catch (Exception e2) {}
				jfXY.dispose();
				new DemoFrame(username, password, isadmin);
			}
		});
		
		jfXY.add(SearchXueYuanInformation);
		jfXY.add(AlterXueYuanInformation);
		jfXY.add(DeleteXueYuanInformation);
		jfXY.add(AddXueYuanInformation);
		jfXY.add(Exit);
		
		jfXY.setSize(600, 350); // ���ÿ������Ĵ�С�ߴ�
		jfXY.setLocation(600, 200);
		jfXY.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jfXY.setVisible(true);
		
		
	}

}
