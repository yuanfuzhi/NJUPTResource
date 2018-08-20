package 实验室安排系统;

import java.awt.*;  
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import 实验室安排系统.MainUI;  
public class GlyUI extends JFrame implements ActionListener  
{   
         //定义组件  
        JButton jb1,jb2,jb3=null;  
        JPanel jp3,jp4=null;  
        JLabel jlb1,jlb3,jlb4,jlb6=null;  

        public static void main(String[] args) {            
          GlyUI  ui=new GlyUI();  
        }    
        public  GlyUI()  
        {
            jp3=new JPanel();
            jp3.setBounds(0, 220, 378, 23);
             getContentPane().setLayout(null);            
            jlb1=new JLabel("\u4F60\u597D\uFF0C\u7BA1\u7406\u5458");
            jlb1.setBounds(68, 15, 231, 49);
            getContentPane().add(jlb1);
            jb2=new JButton("\u4FE1\u606F\u5F55\u5165");
            jb2.setBounds(15, 79, 105, 61);
            getContentPane().add(jb2);
            jb2.setForeground(Color.BLUE);
            jb2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();  
	                new GlyInputUI();
					
				}
			});
            
            //创建组件  
            jb3=new JButton("信息修改");
            jb3.setForeground(Color.BLUE);
            jb3.setBounds(135, 79, 105, 61);
            getContentPane().add(jb3);
            jb3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
					new glyxg();
				}
			});
            
            jb1=new JButton("\u751F\u6210\u8BFE\u8868");
            jb1.setBounds(255, 79, 105, 61);
            getContentPane().add(jb1);
            jb1.setForeground(Color.BLUE);
            jb1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
									
	                try {
						GlykbUI gl=new GlykbUI();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}              //生成课表
	                JOptionPane.showMessageDialog(null,"生成成功","提示消息",JOptionPane.WARNING_MESSAGE);
	                dispose();
	                new GlyUI();
					
				}
			});
                     
            
            getContentPane().add(jp3);
            jlb3=new JLabel("最新公告："); 
            jlb3.setBounds(68, 180, 90, 21);
            getContentPane().add(jlb3);
            jlb3.setForeground(Color.red);
            jlb4=new JLabel("\u8BF7\u5C3D\u5FEB\u5B8C\u5584\u6559\u5B66\u4FE1\u606F");
            jlb4.setBounds(173, 180, 162, 21);
            getContentPane().add(jlb4);
            
            
            this.setTitle("实验室安排系统");  
            this.setSize(400,300);  
            this.setLocation(500, 300);       
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            this.setVisible(true);            
}  
        public void actionPerformed(ActionEvent e) {  
        }  
} 