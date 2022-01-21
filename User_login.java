//�ֿ����ϵͳ��¼���ڣ�����˻����붼��ȷ����ɹ�����ϵͳ����תҳ��
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import org.jvnet.substance.*;
import org.jvnet.substance.painter.border.FlatBorderPainter;
import org.jvnet.substance.painter.border.StandardBorderPainter;
import org.jvnet.substance.painter.gradient.StandardGradientPainter;
import org.jvnet.substance.shaper.ClassicButtonShaper;
import org.jvnet.substance.skin.*;
import org.jvnet.substance.watermark.SubstanceCrosshatchWatermark;
public class User_login  extends JFrame implements ActionListener{
	protected static String Username;
	JButton jb1, jb2;//��ť
    public JTextField jtf1;//�ı���
    JPasswordField jpf1;//�����
    Connection ccon;
    Statement sql; 
    ResultSet rs;
    ImageIcon image;
    JLabel background,label1,label2;
    JPanel jp;
    public User_login () {
    	//���ô���
        setTitle("�ֿ����ϵͳ");
        setResizable(false);//���ڴ�С���ɸı�
        setDefaultCloseOperation(EXIT_ON_CLOSE);// �رմ������˳������
        setLayout(null);// ���ɲ���
        String path="D:\\Eclipse35\\Java_MyFile\\Inventory_5\\src\\";
        image= new ImageIcon(path+"Win.jpg");
        background=new JLabel(image);
        background.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
        this.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));
  
        //������
        label1= new JLabel("�˺� ");
        Font f=new Font("����",Font.BOLD,20);
        label1.setFont(f);
		label2= new JLabel("����");
		label2.setFont(f);
		jtf1 = new JTextField(20);
		jpf1 = new JPasswordField(20);
		jb1 = new JButton("��¼");
		jb1.setFont(f);
        jb2=new JButton("����");
        jb2.setFont(f);
		jp=new JPanel();
		jp.setLayout(null);
		jp.add(label1);
		label1.setBounds(300, 100, 60, 40);
		jp.add(jtf1);
		jtf1.setBounds(360, 100, 200, 40);
		jp.add(label2);
		label2.setBounds(300, 150, 60, 40);
		jp.add(jpf1);
		jpf1.setBounds(360, 150, 200, 40);
		jp.add(jb1);
		jb1.setBounds(360, 210, 80, 40);
		jp.add(jb2);
		jb2.setBounds(450, 210, 80, 40);
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		this.setContentPane(jp);
		this.setBounds(0,0,image.getIconWidth(), image.getIconHeight());
		jp.setOpaque(false); 
		setLocationRelativeTo(null);// ���ھ���
    }

    // ������Ӧ
    public void actionPerformed(ActionEvent e1) {
        String cmd = e1.getActionCommand();// ���ݶ�������,�����зֱ���
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC����
        String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
        String userName = "user05"; //�û���
        String userPwd = "xyz5678SQL"; // ����
        if (cmd.equals("��¼")) {
            Username = jtf1.getText();// ȡ���û���
            String key = new String(jpf1.getPassword());// ȡ������
            if(Username.equals("")||key.equals("")){
            	JOptionPane.showMessageDialog(this, "�û��������붼����Ϊ��.", "֪ͨ", JOptionPane.ERROR_MESSAGE);
            }
            else{
	            try{Class.forName (driverName);}
	            catch(ClassNotFoundException e2){}
	            try{
	            	ccon=DriverManager.getConnection (dbURL,userName,userPwd);//����Դ
	            	sql=ccon.createStatement();
	                rs=sql.executeQuery("SELECT u_name,u_pwd FROM dbo.user_code");
	                boolean f=false;
	                while(rs.next()){
		                String u_name=rs.getString(1);
		                String u_pwd=rs.getString(2);
		                if(u_name.equals(Username)&&u_pwd.equals(key)){
		                	f=true;
		                	break;
		                }
	                }
	                if(f){
	                	setVisible(false);// ����������,
	                    Function functionSelect=new Function();
	                    JLabel label=new JLabel("��ӭ"+Username+"ʹ��ϵͳ",JLabel.CENTER);
	                    label.setFont(new Font("����",Font.BOLD,45));
	                    label.setForeground(Color.WHITE);
	                    JPanel jpan=new JPanel();
	                    jpan.setLayout(new BorderLayout());
	                    jpan.add(label,BorderLayout.CENTER);
	                    functionSelect.setContentPane(jpan);
	                    jpan.setOpaque(false);
	                    
	                    functionSelect.setVisible(true);
	                    dispose();//����������,�ͷ��ڴ���Դ
	                }
	                else {
	                    //�����¼ʧ��  ������ʾ
	                    JOptionPane.showMessageDialog(this, "�û��������������.", "֪ͨ", JOptionPane.ERROR_MESSAGE);
	                    clearText();//����ı��� ����������
	                }
	            }
	            catch(SQLException e3){
	            	System.out.print(e3);
	   
	            	}    
            }
        } 
        else if (cmd.equals("����")) {
            clearText();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

   		 public void run() {
   		  try {


              UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
              JFrame.setDefaultLookAndFeelDecorated(true);
              JDialog.setDefaultLookAndFeelDecorated(true);
              SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
          } catch (Exception e) {
              System.err.println("Something went wrong!");
          }}
   	

   		 });
        
    }
 
    private void clearText() {//����ı���, ����������
        jtf1.setText("");
        jpf1.setText("");
    }
     
    //main����, ��������
    public static void main(String[] args) {
        new User_login().setVisible(true);//������¼����,���ɼ�
    }
 
}

