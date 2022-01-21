//仓库管理系统登录窗口，如果账户密码都正确，则成功进入系统，跳转页面
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
	JButton jb1, jb2;//按钮
    public JTextField jtf1;//文本框
    JPasswordField jpf1;//密码框
    Connection ccon;
    Statement sql; 
    ResultSet rs;
    ImageIcon image;
    JLabel background,label1,label2;
    JPanel jp;
    public User_login () {
    	//设置窗口
        setTitle("仓库管理系统");
        setResizable(false);//窗口大小不可改变
        setDefaultCloseOperation(EXIT_ON_CLOSE);// 关闭窗口则退出虚拟机
        setLayout(null);// 自由布局
        String path="D:\\Eclipse35\\Java_MyFile\\Inventory_5\\src\\";
        image= new ImageIcon(path+"Win.jpg");
        background=new JLabel(image);
        background.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
        this.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));
  
        //添加组件
        label1= new JLabel("账号 ");
        Font f=new Font("楷体",Font.BOLD,20);
        label1.setFont(f);
		label2= new JLabel("密码");
		label2.setFont(f);
		jtf1 = new JTextField(20);
		jpf1 = new JPasswordField(20);
		jb1 = new JButton("登录");
		jb1.setFont(f);
        jb2=new JButton("重置");
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
		setLocationRelativeTo(null);// 窗口居中
    }

    // 动作响应
    public void actionPerformed(ActionEvent e1) {
        String cmd = e1.getActionCommand();// 根据动作命令,来进行分别处理
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC驱动
        String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
        String userName = "user05"; //用户名
        String userPwd = "xyz5678SQL"; // 密码
        if (cmd.equals("登录")) {
            Username = jtf1.getText();// 取得用户名
            String key = new String(jpf1.getPassword());// 取得密码
            if(Username.equals("")||key.equals("")){
            	JOptionPane.showMessageDialog(this, "用户名和密码都不能为空.", "通知", JOptionPane.ERROR_MESSAGE);
            }
            else{
	            try{Class.forName (driverName);}
	            catch(ClassNotFoundException e2){}
	            try{
	            	ccon=DriverManager.getConnection (dbURL,userName,userPwd);//数据源
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
	                	setVisible(false);// 本窗口隐藏,
	                    Function functionSelect=new Function();
	                    JLabel label=new JLabel("欢迎"+Username+"使用系统",JLabel.CENTER);
	                    label.setFont(new Font("楷体",Font.BOLD,45));
	                    label.setForeground(Color.WHITE);
	                    JPanel jpan=new JPanel();
	                    jpan.setLayout(new BorderLayout());
	                    jpan.add(label,BorderLayout.CENTER);
	                    functionSelect.setContentPane(jpan);
	                    jpan.setOpaque(false);
	                    
	                    functionSelect.setVisible(true);
	                    dispose();//本窗口销毁,释放内存资源
	                }
	                else {
	                    //如果登录失败  弹出提示
	                    JOptionPane.showMessageDialog(this, "用户名或者密码错误.", "通知", JOptionPane.ERROR_MESSAGE);
	                    clearText();//清空文本框 密码框的输入
	                }
	            }
	            catch(SQLException e3){
	            	System.out.print(e3);
	   
	            	}    
            }
        } 
        else if (cmd.equals("重置")) {
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
 
    private void clearText() {//清空文本框, 密码框的输入
        jtf1.setText("");
        jpf1.setText("");
    }
     
    //main方法, 程序的入口
    public static void main(String[] args) {
        new User_login().setVisible(true);//创建登录窗口,并可见
    }
 
}

