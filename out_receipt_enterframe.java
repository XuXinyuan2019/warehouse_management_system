import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class out_receipt_enterframe extends second_enterframe{
	  public out_receipt_enterframe(){
		  super();
		  lb1.setText("出仓单号");
		  lb2.setText("出仓日期");
	      lb3.setText("商品代码");
	      lb4.setText("商品数量");
	      lb5.setText("");
	      title.setText("出仓单录入");
		  tf5.setBounds(280, 320, 0, 0);
		  bt1.addActionListener(this);
	      bt2.addActionListener(this);
		  
	  }
	  public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		    String cmd = e.getActionCommand();
			if (cmd.equals("重置")) {
		          clearText();
		      }
			else if(cmd.equals("输入")){
				String out_id=tf1.getText();
				Date date=new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				tf2.setText(dateFormat.format(date));
				Timestamp t=new Timestamp(date.getTime());
				
				String c_id=tf3.getText();
				String quantity1=tf4.getText();
				double quantity=Double.parseDouble(quantity1);
				
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC驱动
		        String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
		        String userName = "user05"; //用户名
		        String userPwd = "xyz5678SQL"; // 密码
		        
		        try{Class.forName (driverName);}
	            catch(ClassNotFoundException e1){}
	            
	            try{
					
	            	String insert="insert into out_receipt(out_id,out_date,c_id,quantity,c_flag,u_id) "+
	            		          "values(?,?,?,?,?,?)";
	            	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);//数据源
	            	PreparedStatement psmt=ccon.prepareStatement(insert);
	            	psmt.setString(1, out_id);
	            	psmt.setTimestamp(2, t);
	            	psmt.setString(3, c_id);
	            	psmt.setDouble(4, quantity);
	            	psmt.setString(5, "N");
	            	psmt.setString(6, User_login.Username);
	            	psmt.executeUpdate();
	            	JOptionPane.showMessageDialog(this,"录入成功");
			       }
	            catch(SQLException e2){
	            	System.out.print(e2);
	            	}
			}
			else if(cmd.equals("修改")){
				String out_id=tf1.getText();
				String out_date=tf2.getText();
				String c_id=tf3.getText();
				String quantity=tf4.getText();
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC驱动
		        String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
		        String userName = "user05"; //用户名
		        String userPwd = "xyz5678SQL"; // 密码
		        
		        try{Class.forName (driverName);}
	            catch(ClassNotFoundException e1){}
	            String sql1="select c_flag from out_receipt where out_id='"+out_id+"'";
		        String sql="Update out_receipt SET c_flag='N'";
				if(!(out_date.equals(""))&&out_date!=null) sql=sql+", out_id=" + out_date;
				if(!(c_id.equals(""))&&c_id!=null) sql=sql+", c_id='" + c_id+"'";
				if(!(quantity.equals(""))&&quantity!=null) sql=sql+", quantity=" + quantity;
				if(out_id!="") sql=sql+" where out_id='"+out_id+"'";
		        try{					
	            	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);//数据源
	            	Statement st=ccon.createStatement();
	            	if(!(out_id.equals(""))&&out_id!=""){
	            		ResultSet rs=st.executeQuery(sql1);
	            		if(rs.next()){
	            			String flag=rs.getString("c_flag");
	            			if(flag.equals("Y")){
	            				JOptionPane.showMessageDialog(this,"已记账，不能修改");
	            			}
	            			else{
				            	PreparedStatement psmt=ccon.prepareStatement(sql);
				            	psmt.execute();
				            	JOptionPane.showMessageDialog(this,"修改成功");
	            			}
	            		}
	            	}
	            	else{
	            		JOptionPane.showMessageDialog(this,"条件不足，无法修改");
	            	}
			       }
	            catch(SQLException e2){
	            	System.out.print(e2);
	            	}
			}
			else if(cmd.equals("删除")){
				String out_id=tf1.getText();
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC驱动
		        String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
		        String userName = "user05"; //用户名
		        String userPwd = "xyz5678SQL"; // 密码
		        
		        try{Class.forName (driverName);}
	            catch(ClassNotFoundException e1){}
	            
		        try{					
		        	String delete="DELETE FROM out_receipt WHERE out_id = ?";
	            	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);//数据源
	            	PreparedStatement psmt=ccon.prepareStatement(delete);
	            	psmt.setString(1, out_id);
	            	psmt.execute();
	            	JOptionPane.showMessageDialog(this,"删除成功");
			       }
	            catch(SQLException e2){
	            	System.out.print(e2);
	            	}
			}
			
		}

}
