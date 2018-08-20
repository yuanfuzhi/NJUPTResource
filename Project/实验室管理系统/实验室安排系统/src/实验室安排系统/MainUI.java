package 实验室安排系统;  
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

public class MainUI extends JFrame implements ActionListener {  

    //定义组件   
    JButton jb1,jb2,jb3=null;  
    JRadioButton jrb1,jrb2=null;  
    JPanel jp1,jp2,jp3,jp4=null;  
    JTextField jtf=null;  
    JLabel jlb1,jlb2,jlb3=null;  
    JPasswordField jpf=null;  
    ButtonGroup bg=null;  

    //设定用户名和密码  
    final String gly_name="GLY";  
    final String gly_pwd="1"; 
    String tername=null;
    final String tea_pwd="1";  

    public static void main(String[] args) {  

        MainUI mUI=new MainUI();  
    }  
    public MainUI()  
    {  
         //创建组件  
        jb1=new JButton("登录");  
        jb1.setBounds(91, 5, 83, 29);
        jb2=new JButton("退出");
        jb2.setBounds(225, 5, 83, 29);

        //设置监听  
        jb1.addActionListener(this);  
        jb2.addActionListener(this);

        jrb1=new JRadioButton("教师");  
        jrb1.setBounds(145, 5, 69, 29);
        jrb2=new JRadioButton("\u7BA1\u7406\u5458");  
        jrb2.setBounds(258, 5, 87, 29);
        bg=new ButtonGroup();  
        bg.add(jrb1);  
        bg.add(jrb2);  
        jrb2.setSelected(true);  //初始页面默认选择权限为管理员

        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        jp4=new JPanel();                 

        jlb1=new JLabel("\u7528 \u6237 \u540D\uFF1A");  
        jlb1.setBounds(43, 8, 90, 21);
        jlb2=new JLabel(" \u5BC6  \u7801 \uFF1A");  
        jlb2.setBounds(44, 8, 95, 21);
        jlb3=new JLabel("\u6743  \u9650\uFF1A");  
        jlb3.setBounds(54, 9, 72, 21);

        jtf=new JTextField(10);  
        jtf.setBounds(144, 5, 197, 27);
        jpf=new JPasswordField(10);  
        jpf.setBounds(146, 5, 195, 27);
        jp1.setLayout(null);
        //加入到JPanel中  
        jp1.add(jlb1);  
        jp1.add(jtf);  
        jp2.setLayout(null);

        jp2.add(jlb2);  
        jp2.add(jpf);  
        jp3.setLayout(null);

        jp3.add(jlb3);      //添加标签
        jp3.add(jrb1);  
        jp3.add(jrb2);  
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
        this.setLocation(600, 300);           
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true);  

    }  

    public void actionPerformed(ActionEvent e) {            //事件判断

        if(e.getActionCommand()=="登录")  
        {  
            //如果选中教师登录  
            if(jrb1.isSelected())  
            {  
                  tealogin();                               //连接到教师的方法 页面
            }else if(jrb2.isSelected()) //学生在登录系统  
            {  
                  glylogin();                               //连接到管理员的方法 页面
            }  

        }else if(e.getActionCommand()=="退出")  
        {  
                  dispose();  
        }
        

    }  

     //管理员登录判断方法  
    public void glylogin()  
    {  
        if(gly_name.equals(jtf.getText())&&gly_pwd.equals(jpf.getText()))  
        {            
            JOptionPane.showMessageDialog(null,"登录成功！","提示消息",JOptionPane.WARNING_MESSAGE);           
            dispose();        
            clear();            
            GlyUI ui=new GlyUI();       //创建新界面  
        }else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"请输入用户名和密码！","提示消息",JOptionPane.WARNING_MESSAGE);  
        }else if(jtf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"请输入用户名！","提示消息",JOptionPane.WARNING_MESSAGE);  
        }else if(jpf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"请输入密码！","提示消息",JOptionPane.WARNING_MESSAGE);  
        }else  
        {  
            JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);  
            //清空输入框  
            clear();  
        }  
    }  
    //教师登录判断方法  
    public void tealogin()  
    {  
        if(tea_pwd.equals(jpf.getText()))  
        {
        	
        	 tername=jtf.getText();
             JOptionPane.showMessageDialog(null,"登录成功！","提示消息",JOptionPane.WARNING_MESSAGE);  
             clear();         
             dispose();             
             TerUI ui=new TerUI(tername);  //创建一个新界面  
        }else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"请输入用户名和密码！","提示消息",JOptionPane.WARNING_MESSAGE);  
        }else if(jtf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"请输入用户名！","提示消息",JOptionPane.WARNING_MESSAGE);  
        }else if(jpf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"请输入密码！","提示消息",JOptionPane.WARNING_MESSAGE);  
        }else  
        {  
            JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);  
            clear();  //清空输入框  
        }  
    }  
    //清空文本框和密码框  
    public  void clear()  
    {  
        jtf.setText("");  
        jpf.setText("");  
    }  

} 