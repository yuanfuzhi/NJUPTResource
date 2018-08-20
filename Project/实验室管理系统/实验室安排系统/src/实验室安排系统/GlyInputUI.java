package 实验室安排系统;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.lang.model.type.NullType;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class GlyInputUI extends JFrame  implements ActionListener
{ 
	//定义组件
	JPanel jp1,jp2,jp4=null;
	JButton jb1=null,jb2=null;
	JLabel jlb1,jlb2,jlb3=null; 
	JTextField jtf1,jtf2=null;
	
        
    public static void main(String[] args)  
    {  
        new GlyInputUI();  
    }
    
   public static void addroom(String romid,int romnum){
		
		Connection con;
		String driver = "com.mysql.jdbc.Driver";      
        String url = "jdbc:mysql://localhost:3306/Demo";
        String user = "root";
        String password = "yfz";
        
        try {
        	        	
			Class.forName(driver);
			con=(Connection) DriverManager.getConnection(url,user,password);
			
			Statement statement = (Statement) con.createStatement();
			String sql = "select count(*) from administrator";      
            ResultSet rs = statement.executeQuery(sql);
            int rowcount=0;
            while(rs.next()){
            	 rowcount=rs.getInt(1);
            } 
       
            String sql1 = "insert into administrator values(?,?,?)";
            PreparedStatement pstmt;
            try {
                pstmt = (PreparedStatement) con.prepareStatement(sql1);             
                pstmt.setInt(1, rowcount+1);
                pstmt.setString(2, romid);               
                pstmt.setInt(3, romnum);                
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }          
            rs.close();
            con.close();
            
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
    
    public GlyInputUI()  
    {
    	//创建组件
    	jb1=new JButton("提交");
    	jb1.setBounds(40, 0, 145, 40);
        jb1.addActionListener(this); 
        jb2=new JButton("返回");
    	jb2.setBounds(209, 0, 139, 40);
        jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new GlyUI();
			}
		}); 
        jp1=new JPanel();  
        jp2=new JPanel();
        jp4=new JPanel();                 

        jlb1=new JLabel("\u5B9E\u9A8C\u5BA4ID");  
        jlb1.setBounds(43, 8, 90, 21);
        jlb2=new JLabel(" \u5B9E\u9A8C\u5BA4\u4EBA\u6570");  
        jlb2.setBounds(37, 8, 114, 21);

        jtf1=new JTextField(10);  
        jtf1.setBounds(152, 5, 197, 27);
        jtf2=new JTextField(10);  
        jtf2.setBounds(155, 5, 195, 27);
        
        jp1.setLayout(null);
        //加入到JPanel中  
        jp1.add(jlb1);  
        jp1.add(jtf1); 
        
        jp2.setLayout(null);
        jp2.add(jlb2);  
        jp2.add(jtf2);
        jp4.setLayout(null);
        jp4.add(jb1);       //添加按钮
        jp4.add(jb2);

        //加入JFrame中  
        getContentPane().add(jp1);  
        getContentPane().add(jp2);
        getContentPane().add(jp4);  

        getContentPane().setLayout(new GridLayout(3,1));            //选择GridLayout布局管理器        
        this.setTitle("实验室安排系统");          
        this.setSize(400,200);         
        this.setLocation(400, 200);           
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true);  
    	       
    }
    public void actionPerformed(ActionEvent e) {            //事件判断

        if(e.getActionCommand()=="提交")  
        {  
        	String romid=jtf1.getText();
        	String snum=jtf2.getText();
        	if(jtf1.getText().isEmpty()||jtf2.getText().isEmpty()){
        		JOptionPane.showMessageDialog(null,"无效的输入","提示消息",JOptionPane.WARNING_MESSAGE);
        	}
        	else{
        		int num=Integer.parseInt(snum);
            	addroom(romid, num);
            	JOptionPane.showMessageDialog(null,"添加成功！","提示消息",JOptionPane.WARNING_MESSAGE);
        	}
        	
        }
        
    }  
}  