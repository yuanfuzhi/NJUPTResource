package �༭��;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.undo.UndoManager;

public class TestDemo extends JFrame{
	
	private MyTextPanel mytext = new MyTextPanel();  
    private JFileChooser jFileChooser1 = new JFileChooser(new File("."));
///////////////////////////////////////////////////////////////////////���ڸı������С������
    private String[] fontsize = new String [100];
    private GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private String[] fontnames = e.getAvailableFontFamilyNames();
    private Font font = new Font("����",Font.BOLD,20);
    private int fontstyle = Font.PLAIN;
    private int fontofsize = 30;
    private String fontofname = "����";
    private JDialog dialog = new JDialog();
    private JButton button = new JButton("��ȷ����");
    private JCheckBox jchBold = new JCheckBox("�Ӵ�");
    private JCheckBox jchItalic = new JCheckBox("б��");
    private JList list1 = new JList(fontsize);
    private JList list2 = new JList(fontnames);
    
////////////////////////////////////////////////////////////////////////���ڳ���
private UndoManager undomg = new UndoManager();

////////////////////////////////////////////////////////////////////////���ڲ���
private JDialog finddialog = new JDialog();
private JTextField findtextfield = new JTextField();
private JButton findbutton = new JButton("�����ң�");
private int findstart = 0;
private JCheckBox fitBigSmall = new JCheckBox();
boolean fitorunfit = false;

////////////////////////////////////////////////////////////////////////�����滻��һ���ַ�
private JDialog replaceFirstdialog = new JDialog();
private JTextField changedtextfield = new JTextField();
private JTextField destinationtextfield = new JTextField();
private JButton replacebutton = new JButton("���滻��");
private int replacestart = 0;


////////////////////////////////////////////////////////////////////////�����滻���Բ�ѯ�ַ�
private JDialog replacedialog = new JDialog();
private JTextField changedtextfield_all = new JTextField();
private JTextField destinationtextfield_all = new JTextField();
private JButton replacebutton_all = new JButton("���滻��");
private int replacestart_all = 0;///////////////////////////////////////////////////////////////////���ڼ��е�
Toolkit toolkit=Toolkit.getDefaultToolkit();//��ȡĬ�Ϲ��߰���
Clipboard clipBoard = toolkit.getSystemClipboard(); //��ȡϵͳ���а�    
/////
   
private JMenuBar mymenubar = new JMenuBar();
private JMenu[] mymenu = { new JMenu("�ļ�"),
    		               new JMenu("�༭"),
    		               new JMenu("��ʽ"),
    		               new JMenu("�ı�����"),
    		               new JMenu("˵��&&����")
                           };
 
private JMenuItem[] menuitem ={
    		   new JMenuItem("�½�"),//0
    		   new JMenuItem("��"),//1
    		   new JMenuItem("����"),//2  
    		   new JMenuItem("�˳�"),//3
    		   new JMenuItem("ȫѡ"),//4
    		   new JMenuItem("����"),//5
    		   new JMenuItem("����"),//6
    		   new JMenuItem("ճ��"),//7
    		   new JMenuItem("����"),//8
    		   new JMenuItem("����"),//9
    		   new JMenuItem("������ɫ"),//10
    		   new JMenuItem("����ɫ"),//11
    		   new JMenuItem("˵��"),//12
    		   new JMenuItem("����"),//13
    		   new JMenuItem("����"),//14
    		   new JMenuItem("�滻��һ��"),//15
    		   new JMenuItem("�滻����"),//16
    		   new JMenuItem("���뵱ǰʱ��"),//17
    		   new JMenuItem("���س���")//18
};

	static class MyTextPanel extends JPanel//�ڲ���  ����Ϊ��̬��
    {
    	private JTextArea textarea = new JTextArea();
    	private JScrollPane scroll = new JScrollPane(textarea);
    	public MyTextPanel()
    	{
    		textarea.setBackground(Color.WHITE);
    		textarea.setLineWrap(true);
    		textarea.setWrapStyleWord(true);
    		textarea.setEditable(true);
    		this.setLayout(new BorderLayout());
    		this.add(scroll,BorderLayout.CENTER);
    	}
    	public void setTitle(String title)
    	{
    		  textarea.setText(title);
    	}
        
    	public void clear()
    	{
    		  textarea.setText("");
    	}
    	public void settextfont(Font font)
    	{
    	      textarea.setFont(font);	
    	}
    	public void settextcolor(Color color)
    	{
    		  textarea.setForeground(color);
    	}
    	public void settextbackcolor(Color color)
    	{
    		  textarea.setBackground(color);
    	}
    	public void appendtext(String string)
    	{
    		  textarea.append(string);
    	}
    	public String gettext()
    	{
    		  return textarea.getText();
    	}
    	public void selectall()
    	{
    		  textarea.selectAll();
    	}
    	public String getselecedarea()
    	{
    		  return textarea.getSelectedText();
    	}
        public void replaceRange(String e,int i1,int i2)
        {
        	  textarea.replaceRange(e, i1, i2);
        }
        public int getSelectionStart()
        {
              return textarea.getSelectionStart(); 
        }
        public int getSelectionEnd()
        {
              return textarea.getSelectionEnd(); 
        }
        public void select(int start,int end)
        {
        	  textarea.select(start, end);    	
        }
        public  JTextArea getDocument()
        {
        	  return textarea;
        }
    }
    
    public static void main(String[] args)
    {
    	TestDemo frame = new TestDemo();
    	frame.setTitle("�ı��༭��");
    	frame.setSize(500, 500);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    	frame.setLocationRelativeTo(null);	
    	
    }
    public void setfont(Font font)
    {
  	 mytext.setFont(font);
    }
    public void setNewFont()
    {
      	fontstyle  += (jchBold.isSelected()?Font.BOLD:Font.PLAIN);
  	    fontstyle  += (jchItalic.isSelected()?Font.ITALIC:Font.PLAIN);
  	
    }
    private void fileopen()
    {
  	  if(jFileChooser1.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
  	  {
  		  open(jFileChooser1.getSelectedFile());
  	  }
    }
    private void open(File file)
    {
  	  try{
  		  BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
  	      byte [] in =new byte[input.available()];
  	      input.read(in,0,in.length);
  	      mytext.clear();
  	      mytext.appendtext(new String(in,0,in.length));
  	      input.close();
  	  }catch(IOException ex){
  		  System.out.println("Sorry,Error opening"+file.getName());
  	  }
    }
    private void filesave()
    {
  	  if(jFileChooser1.showSaveDialog(this)==JFileChooser.APPROVE_OPTION)
  	  {
  		  save(jFileChooser1.getSelectedFile());
  	  }
    }
    private void save(File file)
    {
  	  try{
  		  BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
  	      byte [] out = (mytext.gettext()).getBytes();
  	      output.write(out,0,out.length);
  	      output.close();
  	  }catch(IOException ex){
  		  System.out.println("Sorry,Error saving"+file.getName());
  	  }
    }
    void setfitorunfit()
    {
  	  if(fitBigSmall.isSelected())
  	  {
  		  fitorunfit = true;
  	  }
  	  else
  	  {
  		  fitorunfit = false;
  	  }
    }
    public TestDemo(){
    	int i;
    	this.setLayout(new BorderLayout());
        this.add(mymenubar,BorderLayout.NORTH);//���ò��ֹ��������˵�ʼ�����ڶԻ�����϶�
        mytext.getDocument().getDocument().addUndoableEditListener(undomg);//Ϊ�ı����������
        mytext.settextfont(font);
        this.add(mytext,BorderLayout.CENTER);
        for(i=0;i<mymenu.length;i++)
        {
        	mymenubar.add(mymenu[i]);
        }
        for(i=0;i<4;i++)
        {
        	mymenu[0].add(menuitem[i]);
        }
        for(i=4;i<9;i++)
        {
        	mymenu[1].add(menuitem[i]);
        }
        mymenu[1].add(menuitem[18]);
        for(i=9;i<12;i++)
        {
            mymenu[2].add(menuitem[i]);
        }
        for(i=12;i<14;i++)
        {
        	mymenu[4].add(menuitem[i]);
        }
        for(i=14;i<18;i++)
        {
        	mymenu[3].add(menuitem[i]);
        }
        menuitem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,ActionEvent.CTRL_MASK));//////////////Ϊ������ӿ�ݼ�
        menuitem[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        menuitem[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        menuitem[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
        menuitem[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        menuitem[5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        menuitem[6].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        menuitem[7].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        menuitem[8].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
        menuitem[9].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
        menuitem[10].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,ActionEvent.CTRL_MASK));
        menuitem[11].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,ActionEvent.CTRL_MASK));
        menuitem[12].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
        menuitem[13].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
        menuitem[14].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
        menuitem[15].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
        menuitem[16].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.CTRL_MASK));
        menuitem[17].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,ActionEvent.CTRL_MASK));
        menuitem[18].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,ActionEvent.CTRL_MASK));
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////�����˵���ļ���      
        menuitem[0].addActionListener(new ActionListener(){//�½�
     	public void actionPerformed(ActionEvent arg0) {
				    mytext.clear();
			}
        });
        
        menuitem[1].addActionListener(new ActionListener(){//��
         	public void actionPerformed(ActionEvent arg0) {
					fileopen();
				}
            });
        menuitem[2].addActionListener(new ActionListener(){//����
         	public void actionPerformed(ActionEvent arg0) {
					filesave();
				}
            });
        menuitem[3].addActionListener(new ActionListener(){//�˳�
         	public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
            });
        menuitem[4].addActionListener(new ActionListener(){//ȫѡ
         	public void actionPerformed(ActionEvent arg0) {
					mytext.selectall();
				}
            });
        
        menuitem[5].addActionListener(new ActionListener(){//copy
         	public void actionPerformed(ActionEvent arg0) {
					String copyarea = mytext.getselecedarea();//��ѡ�е�������ַ�����copyarea��
					StringSelection selection = new StringSelection(copyarea); //StringSelection(String data)
		           
					clipBoard.setContents(selection, null);//�����ݸ��Ƶ����а���
				}
            });
        menuitem[6].addActionListener(new ActionListener(){//cut
         	public void actionPerformed(ActionEvent arg0) {
         		String text = mytext.getselecedarea();
                StringSelection selection = new StringSelection(text);
                clipBoard.setContents(selection, null);
                mytext.replaceRange("", mytext.getSelectionStart(),mytext.getSelectionEnd());
				}
            });
        menuitem[7].addActionListener(new ActionListener(){//paste
         	public void actionPerformed(ActionEvent arg0) {
         		Transferable contents = clipBoard.getContents(this);
                if (contents == null)
                    return;
                String text = "";
                try {
                        text = (String) contents.getTransferData(DataFlavor.stringFlavor);
                } catch (Exception exception) {}
                       mytext.replaceRange(text, mytext.getSelectionStart(),mytext.getSelectionEnd());
                 } 

            });
        
        
        menuitem[8].addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0){
        		if(undomg.canUndo()){
        			undomg.undo();
        		}else{
        			JOptionPane.showMessageDialog(null, "�޷�����","����",JOptionPane.WARNING_MESSAGE);
        		}
        	}
        });
        menuitem[18].addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0){
        		if(undomg.canRedo()){
        			undomg.redo();
        		}else
        			JOptionPane.showMessageDialog(null, "�޷����س���","����",JOptionPane.WARNING_MESSAGE);
        	}
        });
        
//---------------------���������--------------------------------
        System.out.println(fontnames.length);
       	for( i=0;i<fontsize.length;i++)
    	{
    		fontsize[i] = Integer.toString(i);
    	}
    	JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
    	list1.setForeground(Color.black);
        list1.setBackground(Color.WHITE);
        list1.setSelectionBackground(Color.black);
        list1.setSelectionForeground(Color.PINK);
        list1.setVisibleRowCount(4);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	list2.setForeground(Color.black);
        list2.setBackground(Color.WHITE);
        list2.setSelectionBackground(Color.black);
        list2.setSelectionForeground(Color.PINK);
        list2.setVisibleRowCount(4);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        panel1.setLayout(new GridLayout(2,1));
        panel1.add(jchBold);
        panel1.add(jchItalic);                
        panel2.setLayout(new BorderLayout());
        panel2.add(list1,BorderLayout.CENTER);
        JScrollPane scrollofsize = new JScrollPane(list1);
        panel2.add(scrollofsize,BorderLayout.CENTER);
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER,120,60));
        panel3.add(button);
        panel4.setLayout(new BorderLayout());
        panel4.add(list2,BorderLayout.CENTER);  
        JScrollPane scrollofname = new JScrollPane(list2);
        panel4.add(scrollofname,BorderLayout.CENTER);
        dialog.setLayout(new GridLayout(1,4,20,20));
        dialog.add(panel1);
        dialog.add(panel2);
        dialog.add(panel4);
        dialog.add(panel3);
        dialog.setSize(600,150);       
        jchBold.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		setNewFont();
        	}
        });
	    jchItalic.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    	    setNewFont();	
	    	}
	    });
	    button.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		font = new Font(fontofname,fontstyle,fontofsize);
	    		mytext.settextfont(font);
	    	}
	    });           
        list1.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int indice = list1.getSelectedIndex();
				fontofsize = indice;
				
			}
        });
        list2.addListSelectionListener(new ListSelectionListener(){
        	public void valueChanged(ListSelectionEvent e) {
        	    int indice = list2.getSelectedIndex();
        	    fontofname = fontnames[indice];
        	}
        });
        menuitem[9].addActionListener(new ActionListener(){//����
         	public void actionPerformed(ActionEvent arg0) {
                 dialog.setVisible(true);
				}
            });
        
        
        menuitem[10].addActionListener(new ActionListener(){//������ɫ
         	public void actionPerformed(ActionEvent arg0) {
			    Color selectedColor = JColorChooser.showDialog(null, "choose color", mytext.getForeground());
			    if(selectedColor!=null)
			    mytext.settextcolor(selectedColor);
				}
            });
        menuitem[11].addActionListener(new ActionListener(){//������ɫ
         	public void actionPerformed(ActionEvent arg0) {
			    Color selectedColor = JColorChooser.showDialog(null, "choose color", mytext.getForeground());
			    if(selectedColor!=null)
			    mytext.settextbackcolor(selectedColor);
					
				}
            });
        
        menuitem[12].addActionListener(new ActionListener(){//˵��
         	public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null,"����һ��������Windows Notepad�����±������ı��༭��!", "˵��",JOptionPane.INFORMATION_MESSAGE);
				}
            });
        menuitem[13].addActionListener(new ActionListener(){//����
         	public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"��ӭʹ��!", "����",JOptionPane.INFORMATION_MESSAGE);					
				}
            });
         
               
//---------------�����������--------------------------------------------
        finddialog.setSize(400,200);
        finddialog.setLocationRelativeTo(null);
        finddialog.setTitle("����");
        finddialog.setVisible(false);
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        panel5.setLayout(new GridLayout(1,2));
        panel5.add(new JLabel("�����ַ���"));
        panel5.add(new JLabel());
        panel6.setLayout(new GridLayout(1,2));
        panel6.add(findtextfield);
        panel6.add(findbutton);
        panel7.setLayout(new GridLayout(1,2));
        panel7.add(fitBigSmall);
        fitBigSmall.setText("�Ƿ�ƥ���Сд");
        panel7.add(new JLabel());
        finddialog.setLayout(new GridLayout(3,1));
        finddialog.add(panel5);
        finddialog.add(panel6);
        finddialog.add(panel7);
        fitBigSmall.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		setfitorunfit();
        	}
        });
        findbutton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
         		if(fitorunfit==false)//��Сд��ƥ��
         		{
        		String findstring = findtextfield.getText();//Ѱ�ҵ��ַ���
        		String allstring = mytext.gettext();
                System.out.println(allstring.indexOf(findstring, findstart));
         		mytext.select(allstring.indexOf(findstring, findstart), allstring.indexOf(findstring, findstart)+findstring.length());//ѡ��Ŀ��
         		findstart = allstring.indexOf(findstring, findstart)+1;  //findstart����Ӧ�ĵ���
         		}
         		else//��Сдƥ��
         		{
            		String findstring = findtextfield.getText();//
            		String allstring = mytext.gettext();
            		
            		String smallfindstring = findstring.toLowerCase();
            		String smallallstring = allstring.toLowerCase();
                    System.out.println(smallallstring.indexOf(smallfindstring, findstart));
             		mytext.select(smallallstring.indexOf(smallfindstring, findstart), smallallstring.indexOf(smallfindstring, findstart)+smallfindstring.length());
             		findstart = smallallstring.indexOf(smallfindstring, findstart)+1;  
         		}
        	}
        });      
        menuitem[14].addActionListener(new ActionListener(){//����
         	public void actionPerformed(ActionEvent arg0) {
				finddialog.setVisible(true);
				}
            });
        
//--------------------�滻��һ���������--------------             
        replaceFirstdialog.setSize(400,200);
        replaceFirstdialog.setLocationRelativeTo(null);
        replaceFirstdialog.setTitle("�滻��һ��");
        replaceFirstdialog.setVisible(false);
        JPanel panel8 = new JPanel();
        JPanel panel9 = new JPanel();
        JPanel panel10 = new JPanel();
        panel8.setLayout(new GridLayout(1,3));
        panel8.add(new JLabel("�����ַ���"));
        panel8.add(new JLabel("�滻�ַ���"));
        panel8.add(new JLabel());
        panel9.setLayout(new GridLayout(1,3));
        panel9.add(changedtextfield);
        panel9.add(destinationtextfield);
        panel9.add(replacebutton);
        replaceFirstdialog.setLayout(new GridLayout(3,1));
        replaceFirstdialog.add(panel8);
        replaceFirstdialog.add(panel9);
        replaceFirstdialog.add(panel10);
        replacebutton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		String changedstring = changedtextfield.getText();
        		String destinationstring = destinationtextfield.getText();
        		String allstring = mytext.gettext();
        		if(allstring.indexOf(changedstring, replacestart)!=-1)//�ҵ�
        		{                  
        			mytext.select(allstring.indexOf(changedstring, replacestart), allstring.indexOf(changedstring, replacestart)+changedstring.length());
        		    mytext.replaceRange(destinationstring, allstring.indexOf(changedstring, replacestart), allstring.indexOf(changedstring, replacestart)+changedstring.length());
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(null, "û���ҵ�","����",JOptionPane.WARNING_MESSAGE);
        		}
        	    replacestart = 0;
        	}
        });
        menuitem[15].addActionListener(new ActionListener(){//�滻��һ
         	public void actionPerformed(ActionEvent arg0) {
			    replaceFirstdialog.setVisible(true);           
				}
            });
//-------------------------�滻����  �������-----------------------------------------
        replacedialog.setSize(400,200);
        replacedialog.setLocationRelativeTo(null);
        replacedialog.setTitle("�滻");
        replacedialog.setVisible(false);
        JPanel panel11 = new JPanel();
        JPanel panel12 = new JPanel();
        JPanel panel13 = new JPanel();
        panel11.setLayout(new GridLayout(1,3));
        panel11.add(new JLabel("�����ַ���"));
        panel11.add(new JLabel("�滻�ַ���"));
        panel11.add(new JLabel());
        panel12.setLayout(new GridLayout(1,3));
        panel12.add(changedtextfield_all);
        panel12.add(destinationtextfield_all);
        panel12.add(replacebutton_all);
        replacedialog.setLayout(new GridLayout(3,1));
        replacedialog.add(panel11);
        replacedialog.add(panel12);
        replacedialog.add(panel13);
        replacebutton_all.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		String changedstring = changedtextfield_all.getText();
        		String destinationstring = destinationtextfield_all.getText();
        		String allstring = mytext.gettext();
        		boolean temp = true;
        		while(allstring.indexOf(changedstring, replacestart_all)!=-1)
        		{   temp = false;
        			allstring = mytext.gettext();
        			mytext.select(allstring.indexOf(changedstring, replacestart_all), allstring.indexOf(changedstring, replacestart_all)+changedstring.length());
        		    int i = allstring.indexOf(changedstring, replacestart_all);
        			mytext.replaceRange(destinationstring, i, i+changedstring.length());
        		    replacestart_all = i+1;  
        		}
        		if(temp == true)
        		{
        			JOptionPane.showMessageDialog(null, "û���ҵ�","����",JOptionPane.WARNING_MESSAGE);
        		}
        	    replacestart_all = 0;
        	}
        });
        menuitem[16].addActionListener(new ActionListener(){//�滻����
         	public void actionPerformed(ActionEvent arg0) {
         		replacedialog.setVisible(true);	
				}
            });
    
//-----------------------------------���뵱ǰʱ������-------------------------
        menuitem[17].addActionListener(new ActionListener(){//���뵱ǰʱ��
         	public void actionPerformed(ActionEvent arg0) {
         		//��Ҫ��ȡ��ǰ������
                Date date=new Date();
                //����Ҫ��ȡ��ʲô����ʱ��
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //��ȡString���͵�ʱ��
                String createdate = sdf.format(date);
         		mytext.appendtext(createdate);
				}
            });
      }     

}