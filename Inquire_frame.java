import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.BusinessBlueSteelSkin;
import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;
import java.util.Date;

public class Inquire_frame extends JFrame{
	JLabel title,lb1,lb2,lb3,lb4,lb5,lb6;
	JTextArea tf1,tf2,tf3,tf4,tf5,tf6;
	JButton bt1,bt2;
	
	public Inquire_frame(){
		this.setLayout(null);
		this.setSize(800, 800);
		title=new JLabel("0");
		title.setBounds(140, 25, 200, 40);
		title.setFont(new Font("等线",Font.BOLD,30));
		this.add(title);

		lb1 = new JLabel("1");
		lb1.setBounds(80, 120, 100, 40);
		this.add(lb1);

		lb2 = new JLabel("2");
		lb2.setBounds(80, 160, 100, 40);
		this.add(lb2);

		lb3 = new JLabel("3");
		lb3.setBounds(80, 200, 100, 40);
		this.add(lb3);
		
		lb4 = new JLabel("4");
		lb4.setBounds(410, 200, 20, 40);
		this.add(lb4);
		
		lb5= new JLabel("");
		lb5.setBounds(80, 250, 100, 40);
		this.add(lb5);
		
		lb6 = new JLabel("");
		lb6.setBounds(410, 250, 20, 40);
		this.add(lb6);
	
		bt1=new JButton("查询");
		bt1.setBounds(200, 310, 100, 30);
		this.add(bt1);
		
		bt2=new JButton("重置");
		bt2.setBounds(400, 310, 100, 30);
		this.add(bt2);
		
		tf1 = new JTextArea();
		tf1.setBounds(200, 120, 200, 30);
		this.add(tf1);
		
		tf2 = new JTextArea();
		tf2.setBounds(200, 160, 200, 30);
		this.add(tf2);
		
		tf3 = new JTextArea();
		tf3.setBounds(200, 200, 200, 30);
		this.add(tf3);
		
		tf4 = new JTextArea();
		tf4.setBounds(440, 200, 200, 30);
		this.add(tf4);
		
		tf5 = new JTextArea();
		tf5.setBounds(200, 250, 0, 0);
		this.add(tf5);
		
		tf6 = new JTextArea();
		tf6.setBounds(440, 250, 0, 0);
		this.add(tf6);
		
		
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
	public static void main(String[] args) {
		new Inquire_frame().setVisible(true);
	}

}
