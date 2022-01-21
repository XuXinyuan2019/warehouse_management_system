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
	private JMenuBar menubar;   //�˵���
	private JMenu menu1, menu2, menu3, menu4; //�˵�
	private JMenuItem item11, item12,item13, item21, item22,item31, item32, item33, item34, item41;   //�˵���
	
	public Function(){
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//�Զ����ز��ͷŸô��塣����������Ӧ�ó����ͷ��˴�����ռ�õ���Դ
		
		
       //�˵�
		menubar = new JMenuBar();
	    menu1 = new JMenu("¼��(L)");
	    menu2 = new JMenu("ͳ�ƴ���(W)");
	    menu3 = new JMenu("��ѯ(C)");
	    menu4 = new JMenu("�˳�(T)");
	    menu1.setMnemonic('L');  //���ò˵��ļ��̲�����ʽ��Alt + F��
	    menu2.setMnemonic('W');
	    menu3.setMnemonic('C');
	    menu4.setMnemonic('T');
	    item11 = new JMenuItem("���ֵ�(J)");
	    item11.addActionListener(this);
	    item12 = new JMenuItem("���ֵ�(O)");
	    item12.addActionListener(this);
	    item13 = new JMenuItem("��ֵ�(Q)");
	    item13.addActionListener(this);
	    
	    item21 = new JMenuItem("����");
	    item21.addActionListener(this);
	   
	    
	    item22 = new JMenuItem("�±�ͳ��");
	    item22.addActionListener(this);
	    
	    item31 = new JMenuItem("����");
	    item31.addActionListener(this);
	    item32 = new JMenuItem("����");
	    item32.addActionListener(this);
	    item33 = new JMenuItem("̨��");
	    item33.addActionListener(this);
	    item34 = new JMenuItem("�±�");
	    item34.addActionListener(this);
	    item41 = new JMenuItem("�˳�(T)");
	    item41.addActionListener(this);
	    
	  //���ò˵���ļ��̲�����ʽ
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
        
       //����ͼƬ
        String path="D:\\Eclipse35\\Java_MyFile\\Inventory_5\\src\\";
        ImageIcon image= new ImageIcon(path+"Win.jpg");
        JLabel bg=new JLabel(image);
        bg.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
        this.getLayeredPane().add(bg,new Integer(Integer.MIN_VALUE));
        
        //���ݰ�
        /*labelWel=new JLabel();
        labelWel.setFont(new Font("����",Font.BOLD,45));
        labelWel.setForeground(Color.WHITE);
        jpan=new JPanel();
        jpan.setLayout(new BorderLayout());
        jpan.add(labelWel,BorderLayout.CENTER);
        this.setContentPane(jpan);
        jpan.setOpaque(false);*/
        //jpan.setSize(image.getIconWidth(), image.getIconHeight());
        
        this.setSize(image.getIconWidth(),image.getIconHeight());
        this.setLocationRelativeTo(null);//����
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
