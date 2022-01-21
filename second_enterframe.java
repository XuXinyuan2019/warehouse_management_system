import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.BusinessBlueSteelSkin;
import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;

public class second_enterframe extends JFrame implements ActionListener{
	JLabel lb1,lb2,lb3,lb4,lb5,title;
	JTextArea tf1,tf2,tf3,tf4,tf5;
	JButton bt1, bt2, bt3, bt4;
	
	public second_enterframe(){
		this.setLayout(null);
		this.setSize(660, 500);
		title=new JLabel("0");
		title.setBounds(80, 25, 200, 40);
		title.setFont(new Font("等线",Font.BOLD,30));
		this.add(title);
		
		lb1 = new JLabel("1");
		lb1.setBounds(80, 80, 100, 40);
		this.add(lb1);

		lb2 = new JLabel("2");
		lb2.setBounds(80, 120, 100, 40);
		this.add(lb2);

		lb3 = new JLabel("3");
		lb3.setBounds(80, 160, 100, 40);
		this.add(lb3);

		lb4 = new JLabel("4");
		lb4.setBounds(80, 200, 100, 40);
		this.add(lb4);
		
		lb5 = new JLabel("5");
		lb5.setBounds(80, 240, 100, 40);
		this.add(lb5);
		

		bt1=new JButton("输入");
		bt1.setBounds(50, 400, 100, 30);
		this.add(bt1);
		
		bt2=new JButton("重置");
		bt2.setBounds(350, 400, 100, 30);
		bt2.addActionListener(this);
		this.add(bt2);
		
		bt3=new JButton("删除");
		bt3.setBounds(200, 400, 100, 30);
		bt3.addActionListener(this);
		this.add(bt3);
		
		bt4=new JButton("修改");
		bt4.setBounds(500, 400, 100, 30);
		bt4.addActionListener(this);
		this.add(bt4);
		
		
		tf1 = new JTextArea();
		tf1.setBounds(280, 80, 150, 30);
		this.add(tf1);
		
		tf2 = new JTextArea();
		tf2.setBounds(280, 120, 150, 30);
		this.add(tf2);
		
		tf3 = new JTextArea();
		tf3.setBounds(280, 160, 150, 30);
		this.add(tf3);
		
		tf4 = new JTextArea();
		tf4.setBounds(280, 200, 150, 30);
		this.add(tf4);
		
		tf5 = new JTextArea();
		tf5.setBounds(280, 240, 150, 30);
		this.add(tf5);
		
		
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);//自动隐藏并释放该窗体。但继续运行应用程序，释放了窗体中占用的资源
		this.setVisible(true);	
		this.setLocationRelativeTo(null);//居中
		java.awt.EventQueue.invokeLater(new Runnable() {
	   		 public void run() { 
	   		  try {
	              UIManager.setLookAndFeel(new SubstanceBusinessBlueSteelLookAndFeel());
	              JFrame.setDefaultLookAndFeelDecorated(true);
	              JDialog.setDefaultLookAndFeelDecorated(true);
	              SubstanceLookAndFeel.setSkin(new BusinessBlueSteelSkin());      
	          } 
	   		  catch (Exception e) {
	              System.err.println("Something went wrong!");
	           }
	         }});
	}
	  protected void clearText() {//清空文本框, 密码框的输入
	      tf1.setText("");
	      tf2.setText("");
	      tf3.setText("");
	      tf4.setText("");
	      tf5.setText("");
	     
	  }

	  public static void main(String[] args) {
		  
	  }
	@Override
	public void actionPerformed(ActionEvent e1) {
		// TODO Auto-generated method stub
	    String cmd = e1.getActionCommand();
		if (cmd.equals("重置")) {
	          clearText();
	      }
	}

}
