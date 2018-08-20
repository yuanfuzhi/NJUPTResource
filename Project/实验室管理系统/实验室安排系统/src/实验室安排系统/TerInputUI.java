package 实验室安排系统;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class TerInputUI extends JFrame  implements ActionListener
{ 
	//定义组件
	JPanel jp1,jp2,jp3,jp4=null;
	JButton jb1=null,jb2=null;
	JLabel jlb1,jlb2,jlb3=null; 
	JTextField jtf1,jtf2,jtf3,jtf4=null;
	
        
    public static void main(String[] args)  
    { 
    	String s=null;
        new TerInputUI(s);  
    }
    
public static void addter(String name,String classid,int classnum,int badtime1,int badtime2){
		
		Connection con;
		String driver = "com.mysql.jdbc.Driver";      
        String url = "jdbc:mysql://localhost:3306/Demo";
        String user = "root";
        String password = "yfz";
        
        try {
        	        	
			Class.forName(driver);
			con=(Connection) DriverManager.getConnection(url,user,password);
			
			Statement statement = (Statement) con.createStatement();
			String sql = "select count(*) from teacher";      
            ResultSet rs = statement.executeQuery(sql);
            int rowcount=0;
            while(rs.next()){
            	 rowcount=rs.getInt(1);
            } 
       
            String sql1 = "insert into teacher values(?,?,?,?,?,?,?)";
            PreparedStatement pstmt;
            try {
                pstmt = (PreparedStatement) con.prepareStatement(sql1);             
                pstmt.setInt(1, rowcount+1);
                pstmt.setString(2, name);
                pstmt.setString(3, classid);
                pstmt.setInt(4,classnum); 
                pstmt.setInt(5,badtime1); 
                pstmt.setInt(6,badtime2);
                pstmt.setInt(7,6); 
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
    
    
    
    public TerInputUI(String s)  
    {
    	//创建组件
    	jb1=new JButton("提交");  
        jb1.setBounds(39, 0, 127, 29);
        jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			if(jtf1.getText().isEmpty()||jtf2.getText().isEmpty()||jtf3.getText().isEmpty()||jtf4.getText().isEmpty()){
				JOptionPane.showMessageDialog(null,"无效的输入","提示消息",JOptionPane.WARNING_MESSAGE);
				
			}else{
				String name=s;
				String clasid=jtf1.getText();
				String cnum=jtf2.getText();
				int clsnum=Integer.parseInt(cnum);
				String b1=jtf3.getText();
				String b2=jtf4.getText();
				int bdtime1=Integer.parseInt(b1);
				int bdtime2=Integer.parseInt(b2);
				addter(name, clasid, clsnum, bdtime1, bdtime2);
				JOptionPane.showMessageDialog(null,"添加成功！","提示消息",JOptionPane.WARNING_MESSAGE);				
				
			}
			
			
			}}); 
        jb2=new JButton("返回");
        jb2.setBounds(224, 0, 127, 29);
        jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new TerUI(s);
				
			}
		});
        
        
        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        jp4=new JPanel();                 

        jlb1=new JLabel("班级ID");  
        jlb1.setBounds(43, 8, 90, 21);
        jlb2=new JLabel(" 班级人数");  
        jlb2.setBounds(37, 8, 95, 21);
        jlb3=new JLabel("\u4E0D\u60F3\u4E0A\u8BFE\u65F6\u95F4");  
        jlb3.setBounds(43, 8, 108, 21);

        jtf1=new JTextField(10);  
        jtf1.setBounds(152, 5, 197, 27);
        jtf2=new JTextField(10);  
        jtf2.setBounds(155, 5, 195, 27);
        jtf3=new JTextField(10);  
        jtf3.setBounds(154, 5, 84, 27);
        jtf4=new JTextField(10);  
        jtf4.setBounds(268, 5, 84, 27);
        
        jp1.setLayout(null);
        //加入到JPanel中  
        jp1.add(jlb1);  
        jp1.add(jtf1); 
        
        jp2.setLayout(null);
        jp2.add(jlb2);  
        jp2.add(jtf2); 
        
        jp3.setLayout(null);
        jp3.add(jlb3);      //添加标签
        jp3.add(jtf3);
        jp3.add(jtf4);  
        
        jp4.setLayout(null);
        jp4.add(jb1);       //添加按钮
        jp4.add(jb2);

        //加入JFrame中  
        getContentPane().add(jp1);  
        getContentPane().add(jp2);  
        getContentPane().add(jp3);  
        getContentPane().add(jp4);  

        getContentPane().setLayout(new GridLayout(4,1));            //选择GridLayout布局管理器        
        this.setTitle("实验室安排系统");          
        this.setSize(400,200);         
        this.setLocation(400, 200);           
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true);  
    	       
    }
    public void actionPerformed(ActionEvent e) {            //事件判断

        
    }  
}  