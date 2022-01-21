

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class clear_inventory_account {
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
    String userName = "user05"; //用户名
    String userPwd = "xyz5678SQL";// 密码
  
    clear_inventory_account(){	
    try{Class.forName (driverName);}
    catch(ClassNotFoundException e1){}
    
try{
    	String update_clear="update clear_list set c_flag='N' where c_flag='Y'";
    	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);
		PreparedStatement ustmt_clear = ccon.prepareStatement(update_clear);
		ustmt_clear.executeUpdate();
    }
    
    catch(SQLException e2){
    	System.out.print(e2);
    }
    
    
    try{
		
    	String update_in="update in_receipt set c_flag='N' where c_flag='Y'";
    	String update_out="update out_receipt set c_flag='N' where c_flag='Y'";
    	String delete_all="delete from inventory_account";
    	
    	
    	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);
        
		PreparedStatement ustmt_in = ccon.prepareStatement(update_in);
		ustmt_in.executeUpdate();
		
		PreparedStatement ustmt_out = ccon.prepareStatement(update_out);
		ustmt_out.executeUpdate();
		
		PreparedStatement ustmt_delete = ccon.prepareStatement(delete_all);
		ustmt_delete.executeUpdate();
		
		JOptionPane.showMessageDialog(new JFrame(),"进出仓清账成功");
    	
       }
    catch(SQLException e2){
    	System.out.print(e2);
    	}
    
    
    }
    
    public static void main(String[] args) {
        new clear_inventory_account(); 
    }
}
