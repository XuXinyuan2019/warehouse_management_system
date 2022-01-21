import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jvnet.substance.*;
import org.jvnet.substance.painter.border.FlatBorderPainter;
import org.jvnet.substance.painter.border.StandardBorderPainter;
import org.jvnet.substance.painter.gradient.StandardGradientPainter;
import org.jvnet.substance.shaper.ClassicButtonShaper;
import org.jvnet.substance.skin.*;
import org.jvnet.substance.watermark.SubstanceCrosshatchWatermark;

public class Function extends JFrame implements ActionListener{
	private JMenuBar menubar;   //菜单条
	private JMenu menu1, menu2, menu3, menu4; //菜单
	private JMenuItem item11, item12,item13, item21, item22,item31, item32, item33, item34, item41;   //菜单项
	
	public Function(){
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//自动隐藏并释放该窗体。但继续运行应用程序，释放了窗体中占用的资源
		
		
       //菜单
		menubar = new JMenuBar();
	    menu1 = new JMenu("录入(L)");
	    menu2 = new JMenu("统计处理(W)");
	    menu3 = new JMenu("查询(C)");
	    menu4 = new JMenu("退出(T)");
	    menu1.setMnemonic('L');  //设置菜单的键盘操作方式是Alt + F键
	    menu2.setMnemonic('W');
	    menu3.setMnemonic('C');
	    menu4.setMnemonic('T');
	    item11 = new JMenuItem("进仓单(J)");
	    item11.addActionListener(this);
	    item12 = new JMenuItem("出仓单(O)");
	    item12.addActionListener(this);
	    item13 = new JMenuItem("清仓单(Q)");
	    item13.addActionListener(this);
	    
	    item21 = new JMenuItem("记账");
	    item21.addActionListener(this);
	   
	    
	    item22 = new JMenuItem("月报统计");
	    item22.addActionListener(this);
	    
	    item31 = new JMenuItem("进仓");
	    item31.addActionListener(this);
	    item32 = new JMenuItem("出仓");
	    item32.addActionListener(this);
	    item33 = new JMenuItem("台账");
	    item33.addActionListener(this);
	    item34 = new JMenuItem("月报");
	    item34.addActionListener(this);
	    item41 = new JMenuItem("退出(T)");
	    item41.addActionListener(this);
	    
	  //设置菜单项的键盘操作方式
        KeyStroke Ctrl_cutKey = 
                KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_MASK);
        item11.setAccelerator(Ctrl_cutKey);
        Ctrl_cutKey = 
                KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK);
        item12.setAccelerator(Ctrl_cutKey);
        Ctrl_cutKey = 
            KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK);
        item13.setAccelerator(Ctrl_cutKey);
        Ctrl_cutKey = 
                KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK);
        item41.setAccelerator(Ctrl_cutKey);

	    menu1.add(item11);
        menu1.add(item12);
        menu1.add(item13);
        menu2.add(item21);
        menu2.add(item22);
        menu3.add(item31);
        menu3.add(item32);
        menu3.add(item33);
        menu3.add(item34);
        menu4.add(item41);
        menubar.add(menu1);  
        menubar.add(menu2);
        menubar.add(menu3);
        menubar.add(menu4);
        this.setJMenuBar(menubar);
        
       //背景图片
        String path="D:\\Eclipse35\\Java_MyFile\\Inventory_5\\src\\";
        ImageIcon image= new ImageIcon(path+"Win.jpg");
        JLabel bg=new JLabel(image);
        bg.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
        this.getLayeredPane().add(bg,new Integer(Integer.MIN_VALUE));
        
        //内容板
        /*labelWel=new JLabel();
        labelWel.setFont(new Font("楷体",Font.BOLD,45));
        labelWel.setForeground(Color.WHITE);
        jpan=new JPanel();
        jpan.setLayout(new BorderLayout());
        jpan.add(labelWel,BorderLayout.CENTER);
        this.setContentPane(jpan);
        jpan.setOpaque(false);*/
        //jpan.setSize(image.getIconWidth(), image.getIconHeight());
        
        this.setSize(image.getIconWidth(),image.getIconHeight());
        this.setLocationRelativeTo(null);//居中
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==item11){
			//this.setVisible(false);
			new in_receipt_enterframe().setVisible(true);
			//dispose();
		}
		else if(e.getSource()==item12){
			//this.setVisible(false);
			new out_receipt_enterframe().setVisible(true);
			//dispose();
		}
		else if(e.getSource()==item13){
			//this.setVisible(false);
			new clear_list_enterframe().setVisible(true);
			//dispose();
		}
		else if(e.getSource()==item21){
			new inventory_account();
		}
		else if(e.getSource()==item22){
			new inventory_mothly_rerport_statistics();
		}
		else if(e.getSource()==item31){
			new In_receipt_inquire().setVisible(true);
		}
		else if(e.getSource()==item32){
			new out_receipt_inquire().setVisible(true);
		}
		else if(e.getSource()==item33){
			new inventory_account_inquire().setVisible(true);
		}
		else if(e.getSource()==item34){
			new inventory_monthly_report_inquire().setVisible(true);
		}
		else if(e.getSource()==item41){
			System.exit(0);
		}
		else{
			
		}	
	}
	public static void main(String[] args) {
		
	}
}
