package 实验室安排系统;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class terxg extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String str=null;
		new terxg(str);
	}

	/**
	 * Create the frame.
	 */
	public terxg(String s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u4FEE\u6539\u6240\u6559\u73ED\u7EA7");
		lblNewLabel.setBounds(161, 15, 252, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u8F93\u5165\u8981\u4FEE\u6539\u7684\u73ED\u7EA7ID:");
		lblNewLabel_1.setBounds(40, 69, 246, 21);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(301, 66, 96, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u8BF7\u8F93\u5165\u4FEE\u6539\u540E\u7684\u73ED\u7EA7ID\uFF1A");
		lblNewLabel_2.setBounds(40, 133, 234, 21);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(301, 130, 96, 27);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		btnNewButton.setBounds(40, 186, 163, 29);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String fid=textField.getText();
				String sid=textField_1.getText();				
				try {
					xg(fid, sid);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"修改成功！","提示消息",JOptionPane.WARNING_MESSAGE);
				
			}
		});;
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE\u4E0A\u4E00\u7EA7");
		btnNewButton_1.setBounds(252, 186, 145, 29);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new TerUI(s);
				
			}
		});
		contentPane.add(btnNewButton_1);
		this.setVisible(true);
		this.setLocation(500, 300);
	}

	public static void xg(String s1,String s2) throws SQLException{
		Connection con;
		String driver = "com.mysql.jdbc.Driver";      
	    String url = "jdbc:mysql://localhost:3306/Demo";
	    String user = "root";
	    String password = "yfz";
	    int rs=0;
	    try {
	    	
	    	
			Class.forName(driver);
			con = (Connection)DriverManager.getConnection(url,user,password);
	        PreparedStatement stmt=null;
	        String sql="update teacher set classid = "+s2+" where classid = "+s1;
			stmt=(PreparedStatement) con.prepareStatement(sql);
	        rs = stmt.executeUpdate();       	             
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
