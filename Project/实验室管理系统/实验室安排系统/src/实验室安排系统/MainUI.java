package ʵ���Ұ���ϵͳ;  
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

public class MainUI extends JFrame implements ActionListener {  

    //�������   
    JButton jb1,jb2,jb3=null;  
    JRadioButton jrb1,jrb2=null;  
    JPanel jp1,jp2,jp3,jp4=null;  
    JTextField jtf=null;  
    JLabel jlb1,jlb2,jlb3=null;  
    JPasswordField jpf=null;  
    ButtonGroup bg=null;  

    //�趨�û���������  
    final String gly_name="GLY";  
    final String gly_pwd="1"; 
    String tername=null;
    final String tea_pwd="1";  

    public static void main(String[] args) {  

        MainUI mUI=new MainUI();  
    }  
    public MainUI()  
    {  
         //�������  
        jb1=new JButton("��¼");  
        jb1.setBounds(91, 5, 83, 29);
        jb2=new JButton("�˳�");
        jb2.setBounds(225, 5, 83, 29);

        //���ü���  
        jb1.addActionListener(this);  
        jb2.addActionListener(this);

        jrb1=new JRadioButton("��ʦ");  
        jrb1.setBounds(145, 5, 69, 29);
        jrb2=new JRadioButton("\u7BA1\u7406\u5458");  
        jrb2.setBounds(258, 5, 87, 29);
        bg=new ButtonGroup();  
        bg.add(jrb1);  
        bg.add(jrb2);  
        jrb2.setSelected(true);  //��ʼҳ��Ĭ��ѡ��Ȩ��Ϊ����Ա

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
        //���뵽JPanel��  
        jp1.add(jlb1);  
        jp1.add(jtf);  
        jp2.setLayout(null);

        jp2.add(jlb2);  
        jp2.add(jpf);  
        jp3.setLayout(null);

        jp3.add(jlb3);      //��ӱ�ǩ
        jp3.add(jrb1);  
        jp3.add(jrb2);  
        jp4.setLayout(null);

        jp4.add(jb1);       //��Ӱ�ť
        jp4.add(jb2);

        //����JFrame��  
        getContentPane().add(jp1);  
        getContentPane().add(jp2);  
        getContentPane().add(jp3);  
        getContentPane().add(jp4);  

        getContentPane().setLayout(new GridLayout(4,1));            //ѡ��GridLayout���ֹ�����        
        this.setTitle("ʵ���Ұ���ϵͳ");          
        this.setSize(400,200);         
        this.setLocation(600, 300);           
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
        this.setVisible(true);  
        this.setResizable(true);  

    }  

    public void actionPerformed(ActionEvent e) {            //�¼��ж�

        if(e.getActionCommand()=="��¼")  
        {  
            //���ѡ�н�ʦ��¼  
            if(jrb1.isSelected())  
            {  
                  tealogin();                               //���ӵ���ʦ�ķ��� ҳ��
            }else if(jrb2.isSelected()) //ѧ���ڵ�¼ϵͳ  
            {  
                  glylogin();                               //���ӵ�����Ա�ķ��� ҳ��
            }  

        }else if(e.getActionCommand()=="�˳�")  
        {  
                  dispose();  
        }
        

    }  

     //����Ա��¼�жϷ���  
    public void glylogin()  
    {  
        if(gly_name.equals(jtf.getText())&&gly_pwd.equals(jpf.getText()))  
        {            
            JOptionPane.showMessageDialog(null,"��¼�ɹ���","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);           
            dispose();        
            clear();            
            GlyUI ui=new GlyUI();       //�����½���  
        }else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"�������û��������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
        }else if(jtf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"�������û�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
        }else if(jpf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"���������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
        }else  
        {  
            JOptionPane.showMessageDialog(null,"�û��������������\n����������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);  
            //��������  
            clear();  
        }  
    }  
    //��ʦ��¼�жϷ���  
    public void tealogin()  
    {  
        if(tea_pwd.equals(jpf.getText()))  
        {
        	
        	 tername=jtf.getText();
             JOptionPane.showMessageDialog(null,"��¼�ɹ���","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
             clear();         
             dispose();             
             TerUI ui=new TerUI(tername);  //����һ���½���  
        }else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"�������û��������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
        }else if(jtf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"�������û�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
        }else if(jpf.getText().isEmpty())  
        {  
            JOptionPane.showMessageDialog(null,"���������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
        }else  
        {  
            JOptionPane.showMessageDialog(null,"�û��������������\n����������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);  
            clear();  //��������  
        }  
    }  
    //����ı���������  
    public  void clear()  
    {  
        jtf.setText("");  
        jpf.setText("");  
    }  

} 