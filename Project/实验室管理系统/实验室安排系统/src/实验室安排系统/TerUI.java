package 实验室安排系统;

import java.awt.*;  
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;  
import javax.swing.JButton;
import 实验室安排系统.MainUI;  
public class TerUI extends JFrame implements ActionListener  
{   
         //定义组件  
        JButton jb1,jb2,jb3=null;  
        JPanel jp3,jp4=null;  
        JLabel jlb1,jlb3,jlb4,jlb6=null;  

        public static void main(String[] args) {            
          String tername = null;
		TerUI  ui=new TerUI(tername);  
        }    
        public  TerUI(String s)  
        {
        	String tname=s;
            jp3=new JPanel();
            jp3.setBounds(0, 220, 378, 23);
            getContentPane().setLayout(null); 
            String welcome=tname+"老师好，欢迎使用本系统";
            jlb1=new JLabel(welcome);
            jlb1.setBounds(68, 15, 231, 49);
            getContentPane().add(jlb1);
            jb3=new JButton("修改信息");           
            jb3.setBounds(135, 79, 105, 47);
            getContentPane().add(jb3);
            jb3.setForeground(Color.BLUE);
            jb3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new terxg(s);
				}
			});
            
            jb2=new JButton("\u4FE1\u606F\u5F55\u5165");
            jb2.setBounds(15, 79, 105, 47);
            getContentPane().add(jb2);
            jb2.setForeground(Color.BLUE);
            jb2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();  
	                new TerInputUI(tname); 
				}
			});
            //创建组件  
            jb1=new JButton("\u67E5\u770B\u8BFE\u8868");
            jb1.setBounds(253, 78, 120, 48);
            getContentPane().add(jb1);
            jb1.setForeground(Color.BLUE);
            //jb1.addActionListener(this);
            jb1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();  
	                try {
						new TerLookUI(tname);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
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
            this.setLocation(700, 300);       
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            this.setVisible(true);             
        }  
        
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub			
		} 
} 