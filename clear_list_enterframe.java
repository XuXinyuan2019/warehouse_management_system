import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class clear_list_enterframe extends second_enterframe{
	  public clear_list_enterframe(){
		  super();
		  lb1.setText("��ֵ���");
		  lb2.setText("�������");
	      lb3.setText("��Ʒ����");
	      lb4.setText("��Ʒ����");
	      lb5.setText("��Ʒ����");
	      title.setText("��ֵ�����");
	      bt1.addActionListener(this);
	      bt2.addActionListener(this);
	  }
	  public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		    String cmd = e.getActionCommand();
			if (cmd.equals("����")) {
		          clearText();
		      }
			else if(cmd.equals("����")){
				String clear_id=tf1.getText();
				Date date=new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				tf2.setText(dateFormat.format(date));
				
				Timestamp t=new Timestamp(date.getTime());
				String c_id=tf3.getText();
				String item_price1=tf4.getText();
				double item_price=Double.parseDouble(item_price1);
				String quantity1=tf5.getText();
				double quantity=Double.parseDouble(quantity1);
				
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC����
		        String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
		        String userName = "user05"; //�û���
		        String userPwd = "xyz5678SQL"; // ����
		        
		        try{Class.forName (driverName);}
	            catch(ClassNotFoundException e1){}
	            
	            try{					
	            	String insert="insert into clear_list(clear_id,clear_date,c_id,item_price,quantity,c_flag,u_id) "+
	            		          "values(?,?,?,?,?,?,?)";
	            	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);//����Դ
	            	PreparedStatement psmt=ccon.prepareStatement(insert);
	            	psmt.setString(1, clear_id);
	            	psmt.setTimestamp(2,t);
	            	psmt.setString(3, c_id);
	            	psmt.setDouble(4, item_price);
	            	psmt.setDouble(5, quantity);
	            	psmt.setString(6, "N");
	            	psmt.setString(7, User_login.Username);
	            	psmt.executeUpdate();
	            	JOptionPane.showMessageDialog(this,"¼��ɹ�");
			       }
	            catch(SQLException e2){
	            	System.out.print(e2);
	            	}
			}
			else if(cmd.equals("�޸�")){
				String clear_id=tf1.getText();
				String clear_date=tf2.getText();
				String c_id=tf3.getText();
				String item_price=tf4.getText();
				String quantity=tf5.getText();
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC����
		        String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
		        String userName = "user05"; //�û���
		        String userPwd = "xyz5678SQL"; // ����
		        
		        try{Class.forName (driverName);}
	            catch(ClassNotFoundException e1){}
	            String sql1="select c_flag from clear_list where clear_id='"+clear_id+"'";
		        String sql="Update clear_list SET c_flag='N'";
				if(!(clear_date.equals(""))&&clear_date!=null) sql=sql+", clear_id=" + clear_date;
				if(!(c_id.equals(""))&&c_id!=null) sql=sql+", c_id='" + c_id+"'";
				if(!(item_price.equals(""))&&item_price!=null) sql=sql+", item_price=" + item_price;
				if(!(quantity.equals(""))&&quantity!=null) sql=sql+", quantity=" + quantity;
				if(clear_id!="") sql=sql+" where clear_id='"+clear_id+"'";
		        try{					
	            	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);//����Դ
	            	Statement st=ccon.createStatement();
	            	if(!(clear_id.equals(""))&&clear_id!=""){
	            		ResultSet rs=st.executeQuery(sql1);
	            		if(rs.next()){
	            			String flag=rs.getString("c_flag");
	            			if(flag.equals("Y")){
	            				JOptionPane.showMessageDialog(this,"�Ѽ��ˣ������޸�");
	            			}
	            			else{
				            	PreparedStatement psmt=ccon.prepareStatement(sql);
				            	psmt.execute();
				            	JOptionPane.showMessageDialog(this,"�޸ĳɹ�");
	            			}
	            		}
	            	}
	            	else{
	            		JOptionPane.showMessageDialog(this,"�������㣬�޷��޸�");
	            	}
			       }
	            catch(SQLException e2){
	            	System.out.print(e2);
	            	}
			}
			else if(cmd.equals("ɾ��")){
				String clear_id=tf1.getText();
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC����
		        String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
		        String userName = "user05"; //�û���
		        String userPwd = "xyz5678SQL"; // ����
		        
		        try{Class.forName (driverName);}
	            catch(ClassNotFoundException e1){}
	            
		        try{					
		        	String delete="DELETE FROM clear_list WHERE clear_id = ?";
	            	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);//����Դ
	            	PreparedStatement psmt=ccon.prepareStatement(delete);
	            	psmt.setString(1, clear_id);
	            	psmt.execute();
	            	JOptionPane.showMessageDialog(this,"ɾ���ɹ�");
			       }
	            catch(SQLException e2){
	            	System.out.print(e2);
	            	}
			}
		}
}
