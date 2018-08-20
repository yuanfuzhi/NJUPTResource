package 实验室安排系统;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TerLookUI extends JFrame  
{
	public static void main(String[] args) throws SQLException  
    {
    	String tname=null;
        new TerLookUI(tname);  
    } 

    public TerLookUI(String s) throws SQLException  
    { 
    	String tname=s;
    	int id=0;
    	ResultSet rs=findid(s,"teacher");
    	while(rs.next()){
			id=rs.getInt(1);
      }
    	//System.out.println(id);
    	String roomid=null;
		int t1=0,t2=0,t3=0,t4=0,t5=0,t6=0;
		ResultSet rs1=find(id, "timetable");
		while(rs1.next()){
			roomid=rs1.getString(3);
			t1=rs1.getInt(6);
			t2=rs1.getInt(7);
			t3=rs1.getInt(8);
			t4=rs1.getInt(9);
			t5=rs1.getInt(10);
			t6=rs1.getInt(11);
       }
		//System.out.println(t1);
		//System.out.println(t2);
		//System.out.println(t3);
        intiComponent(s,roomid,t1,t2,t3,t4,t5,t6);  
    }  

    private void intiComponent(String tname,String roomid,int t1,int t2,int t3,int t4,int t5,int t6)  
    {        
        String[] columnNames =  
        { "课节数","星期一", "星期二", "星期三", "星期四", "星期五"};  

        Object[][] obj=new Object[4][6];  
        for (int i=0;i<4;i++)  
        {  
            for(int j=0;j<6;j++)  
            {  obj[i][j]=""; 
            }  
        }
        obj[0][0]="第一周上午";
        obj[1][0]="第一周下午";
        obj[2][0]="第二周上午";
        obj[3][0]="第二周下午";
        int t[]=new int[6];
        t[0]=t1;
        t[1]=t2;
        t[2]=t3;
        t[3]=t4;
        t[4]=t5;
        t[5]=t6;
        for(int i=0;i<6;i++){
        	switch(t[i]){
            case 1:
            	obj[0][1]=roomid+"上课";
            	break;
            case 2:
            	obj[1][1]=roomid+"上课";
            	break;
            case 3:
            	obj[0][2]=roomid+"上课";
            	break;
            case 4:
            	obj[1][2]=roomid+"上课";
            	break;
            case 5:
            	obj[0][3]=roomid+"上课";
            	break;
            case 6:
            	obj[1][3]=roomid+"上课";
            	break;
            case 7:
            	obj[0][4]=roomid+"上课";
            	break;
            case 8:
            	obj[1][4]=roomid+"上课";
            	break;
            case 9:
            	obj[0][5]=roomid+"上课";
            	break;
            case 10:
            	obj[1][5]=roomid+"上课";
            	break;
            case 11:
            	obj[2][1]=roomid+"上课";
            	break;
            case 12:
            	obj[3][1]=roomid+"上课";
            	break;
            case 13:
            	obj[2][2]=roomid+"上课";
            	break;
            case 14:
            	obj[3][2]=roomid+"上课";
            	break;
            case 15:
            	obj[2][3]=roomid+"上课";
            	break;
            case 16:
            	obj[3][3]=roomid+"上课";
            	break;
            case 17:
            	obj[3][4]=roomid+"上课";
            	break;
            case 18:
            	obj[3][4]=roomid+"上课";
            	break;
            case 19:
            	obj[2][5]=roomid+"上课";
            	break;
            case 20:
            	obj[3][5]=roomid+"上课";
            	break;
            }	
        }
        
        JTable table=new JTable(obj, columnNames);
        TableColumn column=null;  
        int colunms = table.getColumnCount();  
        for(int i=0;i<colunms;i++)  
        {  
            column = table.getColumnModel().getColumn(i);             
            column.setPreferredWidth(80); 
        }        
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
        table.setRowHeight(50);
        JScrollPane scroll = new JScrollPane(table);  
        scroll.setSize(300, 50);  

        getContentPane().add(scroll);

        this.setLocation(450, 200); 
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.pack();  
    }  

    public  static  ResultSet find(int i,String s) throws SQLException{
		Connection con;
		String driver = "com.mysql.jdbc.Driver";      
	    String url = "jdbc:mysql://localhost:3306/Demo";
	    String user = "root";
	    String password = "yfz";
	    ResultSet rs=null;
	    try {
	    	
	    	
			Class.forName(driver);
			con = (Connection)DriverManager.getConnection(url,user,password);
			//if(!con.isClosed())
	            //System.out.println("Succeeded connecting to the Database!");
	        PreparedStatement stmt=null;
	        String sql="select * from "+s+" where id = "+String.valueOf(i);
			stmt=(PreparedStatement) con.prepareStatement(sql);
	        rs = stmt.executeQuery();       	             
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return rs;     	
    } 
	
    public  static  ResultSet findid(String name,String s) throws SQLException{
  		Connection con;
  		String driver = "com.mysql.jdbc.Driver";      
  	    String url = "jdbc:mysql://localhost:3306/Demo";
  	    String user = "root";
  	    String password = "yfz";
  	    ResultSet rs=null;
  	    try {
  	    	
  	    	
  			Class.forName(driver);
  			con = (Connection)DriverManager.getConnection(url,user,password); 		
  	        PreparedStatement stmt=null;
  	        String sql="select * from "+s+" where name = '"+name+"'";
  			stmt=(PreparedStatement) con.prepareStatement(sql);
  	        rs = stmt.executeQuery();       	             
  		} catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	    
  		return rs;     	
      } 
  	




}  