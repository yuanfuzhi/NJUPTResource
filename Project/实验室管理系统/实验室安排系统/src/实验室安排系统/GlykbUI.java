package 实验室安排系统;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class GlykbUI {
	
	public static void main(String[] args) throws SQLException
	{
		
		new GlykbUI();
		
	}
	public GlykbUI() throws SQLException{
		int ternum=Getternum();
		int timetabenum=Gettimetablenum();
		for(int i=timetabenum+1;i<=ternum;i++){
			String tname=null,roomid=null;
			int tb1=0,tb2=0;
			ResultSet rs1=find(i, "teacher");
			while(rs1.next()){
				tname=rs1.getString(2);
				tb1=rs1.getInt(5);
				tb2=rs1.getInt(6);
	       }
		   if(i<=10){
			   ResultSet rs2=find(i, "administrator");
			   while(rs2.next()){
				   roomid=rs2.getString(2);
			   }
		   }else{
			   ResultSet rs2=find(i-10, "administrator");
			   while(rs2.next()){
				   roomid=rs2.getString(2);
			   }
		   }
		   
		   //System.out.println(tname+roomid);
		   Set<Integer> set=getrandom(i,tb1, tb2);
		   int time[]=new int[6];
		   int j=0;
		   Iterator<Integer> it=set.iterator();
		   while(it.hasNext()){
			   time[j++]=it.next();		   
		   }
		   //System.out.println(Arrays.toString(time));
		   addtimetable(i, tname, roomid, tb1, tb2, time);
		}
		
	} 
	public static int Getternum(){
		Connection con;
		String driver = "com.mysql.jdbc.Driver";      
        String url = "jdbc:mysql://localhost:3306/Demo";
        String user = "root";
        String password = "yfz";
        int rowcount=0;
        try {       	        	
			Class.forName(driver);
			con=(Connection) DriverManager.getConnection(url,user,password);
			
			Statement statement = (Statement) con.createStatement();
			String sql = "select count(*) from teacher";      
            ResultSet rs = statement.executeQuery(sql);            
            while(rs.next()){
            	 rowcount=rs.getInt(1);
            }                     
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return rowcount;
	}
	public static int Gettimetablenum(){
		Connection con;
		String driver = "com.mysql.jdbc.Driver";      
        String url = "jdbc:mysql://localhost:3306/Demo";
        String user = "root";
        String password = "yfz";
        int rowcount=0;
        try {       	        	
			Class.forName(driver);
			con=(Connection) DriverManager.getConnection(url,user,password);
			
			Statement statement = (Statement) con.createStatement();
			String sql = "select count(*) from timetable";      
            ResultSet rs = statement.executeQuery(sql);            
            while(rs.next()){
            	 rowcount=rs.getInt(1);
            }                     
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return rowcount;
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
	
	public static Set<Integer> getrandom(int i,int a,int b) throws SQLException{
		Random ran=new Random(i);
		Set<Integer> set =new TreeSet<>();
		if(i<=10){
			while(true){			
				int r1=ran.nextInt(3)+1;
				if(r1!=a&&r1!=b)
					set.add(r1);
				if(set.size()==1)
					break;
			}
			while(true){			
				int r2=ran.nextInt(3)+4;
				if(r2!=a&&r2!=b)
					set.add(r2);
				if(set.size()==2)
					break;
			}
			while(true){			
				int r3=ran.nextInt(4)+7;
				if(r3!=a&&r3!=b)
					set.add(r3);
				if(set.size()==3)
					break;
			}
			System.out.println(set);
			while(true){			
				int r4=ran.nextInt(3)+11;
				if(r4!=(a+10)&&r4!=(b+10))
					set.add(r4);
				if(set.size()==4)
					break;
			}
			while(true){			
				int r5=ran.nextInt(3)+14;
				if(r5!=(a+10)&&r5!=(b+10))
					set.add(r5);
				if(set.size()==5)
					break;
			}
			while(true){			
				int r6=ran.nextInt(4)+17;
				if(r6!=(a+10)&&r6!=(b+10))
					set.add(r6);
				if(set.size()==6)
					break;
			}	
			System.out.println(set);
			
		}else{
			Connection con;
			String driver = "com.mysql.jdbc.Driver";      
		    String url = "jdbc:mysql://localhost:3306/Demo";
		    String user = "root";
		    String password = "yfz";
		    ResultSet rs=null;
		    int t1=0;
		    int t2=0;
		    int t3=0;
		    int t4=0;
		    int t5=0;
		    int t6=0;
		    try {	    	
				Class.forName(driver);
				con = (Connection)DriverManager.getConnection(url,user,password);				
		        PreparedStatement stmt=null;
		        String sql="select * from timetable where id = "+String.valueOf(i-10);
				stmt=(PreparedStatement) con.prepareStatement(sql);
		        rs = stmt.executeQuery();       	             
			} catch (ClassNotFoundException e) {				
				e.printStackTrace();
			}
		    while(rs.next()){
		    	t1=rs.getInt(6);
		    	t2=rs.getInt(7);
		    	t3=rs.getInt(8);
		    	t4=rs.getInt(9);
		    	t5=rs.getInt(10);
		    	t6=rs.getInt(11);
		    }
		    int n[]=new int[21];
		    int m[]=new int [21];
		    int num=0;
		    for(int j=0;j<20;j++){
		    	n[j]=1;
		    }
		    System.out.println(t1);
		    System.out.println(t2);
		    System.out.println(t3);
		    System.out.println(t4);
		    System.out.println(t5);
		    System.out.println(t6);
		    
		    n[a]=0;n[b]=0;n[t1]=0;n[t2]=0;n[t3]=0;n[t4]=0;n[t5]=0;n[t6]=0;
		    for(int j=0;j<20;j++){
		    	if(n[j]!=0){
		    		m[num++]=j;
		    	}		    		
		    }
		    
		    int r1=ran.nextInt(2)+0;
			set.add(m[r1]);
			int r2=ran.nextInt(2)+2;
			set.add(m[r2]);
			int r3=ran.nextInt(2)+4;
			set.add(m[r3]);
			int r4=ran.nextInt(2)+6;
			set.add(m[r4]);
			int r5=ran.nextInt(2)+8;
			set.add(m[r5]);
			int r6=ran.nextInt(num-10)+10;
			set.add(m[r6]);
		}					
		return set;				
	}

	public static void addtimetable(int id,String name,String room,int bd1,int bd2,int time[]){
		Connection con;
		String driver = "com.mysql.jdbc.Driver";      
        String url = "jdbc:mysql://localhost:3306/Demo";
        String user = "root";
        String password = "yfz";
        
        try {
        	        	
			Class.forName(driver);
			con=(Connection) DriverManager.getConnection(url,user,password);
			
			String sql = "insert into timetable values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt;
            try {
                pstmt = (PreparedStatement) con.prepareStatement(sql);             
                pstmt.setInt(1, id);
                pstmt.setString(2, name);               
                pstmt.setString(3, room);
                pstmt.setInt(4, bd1);
                pstmt.setInt(5, bd2);
                pstmt.setInt(6, time[0]);
                pstmt.setInt(7, time[1]);
                pstmt.setInt(8, time[2]);
                pstmt.setInt(9, time[3]);
                pstmt.setInt(10, time[4]);
                pstmt.setInt(11, time[5]);
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }                    
            con.close();
            
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
 