import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class inventory_account {
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
    String userName = "user05"; //用户名
    String userPwd = "xyz5678SQL";// 密码
    
    inventory_account(){		
    try{Class.forName (driverName);}
    catch(ClassNotFoundException e1){}
    
    /*try{
		
    	String insert="insert into inventory_account(c_id,in_price,avg_price,io_date,in_quantity,out_quantity,"
    			+ "stock_number,io_id,acc_date,u_id) "+
    		          "values(?,?,null,?,?,?,?,?,?,?)";
    	String update_clear="update clear_list set c_flag='Y' where c_flag='N'";
    	String select_clear="select * from clear_list where c_flag='N' order by c_id,clear_date";
    	
    	
		
    	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);
    	Statement stmt_in = ccon.createStatement();
        ResultSet rs_in = stmt_in.executeQuery(select_clear);
        
		while (rs_in.next()) {
        	String c_id=rs_in.getString("c_id");
        	Double in_price = rs_in.getDouble("item_price");
        	Double avg_price = null;
        	String io_data = rs_in.getString("clear_date");
        	Double in_quantity = null;
        	Double out_quantity = null;
        	Double stock_number = rs_in.getDouble("quantity");
        	String io_id = rs_in.getString("clear_id");
        	//String u_id = rs_in.getString("u_id");
        	
        	Date date=new Date();
    		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Timestamp t=new Timestamp(date.getTime());
    		
        	PreparedStatement psmt=ccon.prepareStatement(insert);
         	psmt.setString(1, c_id); 
        	psmt.setDouble(2, in_price);
        	psmt.setString(3, io_data);
        	psmt.setNull(4, Types.DOUBLE);
        	psmt.setNull(5, Types.DOUBLE);
        	psmt.setDouble(6, stock_number);
        	psmt.setString(7, io_id);
        	psmt.setTimestamp(8, t);
        	psmt.setString(9, User_login.Username);
        	psmt.executeUpdate();
        }
		
		PreparedStatement ustmt_clear = ccon.prepareStatement(update_clear);
		ustmt_clear.executeUpdate();
    }
    
    catch(SQLException e2){
    	System.out.print(e2);
    }*/
    
    
    try{
		
    	String insert="insert into inventory_account(c_id,in_price,avg_price,io_date,in_quantity,out_quantity,"
    			+ "stock_number,io_id,acc_date,u_id) "+
    		          "values(?,?,null,?,?,?,?,?,?,?)";
    	String update_clear="update clear_list set c_flag='Y' where c_flag='N'";
    	String update_in="update in_receipt set c_flag='Y' where c_flag='N'";
    	String update_out="update out_receipt set c_flag='Y' where c_flag='N'";
//    	String in_select="select * from in_receipt where c_flag='N'";
//    	String out_select="select * from out_receipt where c_flag='N'";
    	String select_all="select in_id,in_date as io_date,c_id, item_price,quantity,c_flag,u_id,\r\n" + 
    			"null as out_id  \r\n" + 
    			"from in_receipt\r\n" + 
    			"where c_flag='N'\r\n" +
    			"union all\r\n" + 
    			"select null as in_id, out_date as io_date, c_id, null as item_price, quantity, c_flag, u_id, out_id\r\n" + 
    			"from out_receipt\r\n" +
    			"where c_flag='N'\r\n" +
    			"union all\r\n" + 
    			"select clear_id as in_id, clear_date as io_date, c_id, null as item_price, quantity, c_flag, u_id,null as out_id\r\n" + 
    			"from clear_list\r\n" +
    			"where c_flag='N'\r\n" +
    			"order by c_id,io_date asc";
    	String select_last_line="select top(1) stock_number from  inventory_account where c_id=? order by io_date desc";
    	
    	
    	
    	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);
    	
        
    	Statement stmt_in = ccon.createStatement();
        ResultSet rs_in = stmt_in.executeQuery(select_all);
        
		while (rs_in.next()) {
			if (rs_in.getString("in_id")!=null&&rs_in.getString("in_id").charAt(0)=='c') {
	        	String c_id=rs_in.getString("c_id");
	        	Double in_price = null;
	        	Double avg_price = null;
	        	String io_data = rs_in.getString("io_date");
	        	Double in_quantity = null;
	        	Double out_quantity = null;
	        	String io_id = rs_in.getString("in_id");
	        	//String u_id = rs_in.getString("u_id");
	        	
	        	
	        	Double stock_number = rs_in.getDouble("quantity");
	        	
	        	Date date=new Date();
	    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		Timestamp t=new Timestamp(date.getTime());
	    		
	        	PreparedStatement psmt=ccon.prepareStatement(insert);
	         	psmt.setString(1, c_id); 
	        	psmt.setNull(2, Types.DOUBLE);
	        	psmt.setString(3, io_data);
	        	psmt.setNull(4,Types.DOUBLE );
	        	psmt.setNull(5, Types.DOUBLE);
	        	psmt.setDouble(6, stock_number);
	        	psmt.setString(7, io_id);
	        	psmt.setTimestamp(8, t);
	        	psmt.setString(9, User_login.Username);
	        	psmt.executeUpdate();
			}
			else if (rs_in.getString("in_id")!=null&&rs_in.getString("in_id").charAt(0)=='i') {
	        	String c_id=rs_in.getString("c_id");
	        	Double in_price = rs_in.getDouble("item_price");
	        	Double avg_price = null;
	        	String io_data = rs_in.getString("io_date");
	        	Double in_quantity = rs_in.getDouble("quantity");
	        	Double out_quantity = null;
	        	String io_id = rs_in.getString("in_id");
	        	//String u_id = rs_in.getString("u_id");
	        	
	        	double last_stock=0;
	        	PreparedStatement psmt1=ccon.prepareStatement(select_last_line);
	        	psmt1.setString(1, c_id);
	        	ResultSet rs_stock=psmt1.executeQuery();
	        	if(rs_stock.next()){
	        		last_stock=rs_stock.getDouble("stock_number");
	        	}
	        	Double stock_number = last_stock+in_quantity;
	        	
	        	Date date=new Date();
	    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		Timestamp t=new Timestamp(date.getTime());
	    		
	        	PreparedStatement psmt=ccon.prepareStatement(insert);
	         	psmt.setString(1, c_id); 
	        	psmt.setDouble(2, in_price);
	        	psmt.setString(3, io_data);
	        	psmt.setDouble(4, in_quantity);
	        	psmt.setNull(5, Types.DOUBLE);
	        	psmt.setDouble(6, stock_number);
	        	psmt.setString(7, io_id);
	        	psmt.setTimestamp(8, t);
	        	psmt.setString(9, User_login.Username);
	        	psmt.executeUpdate();
			}
			else {
				String c_id=rs_in.getString("c_id");
	        	Double in_price = null;
	        	Double avg_price = null;
	        	String io_data = rs_in.getString("io_date");
	        	Double in_quantity = null;
	        	Double out_quantity = rs_in.getDouble("quantity");
	        	String io_id = rs_in.getString("out_id");
	        	//String u_id = rs_in.getString("u_id");
	        	
	        	double last_stock=0;
	        	PreparedStatement psmt1=ccon.prepareStatement(select_last_line);
	        	psmt1.setString(1, c_id);
	        	ResultSet rs_stock=psmt1.executeQuery();
	        	if(rs_stock.next()){
	        		last_stock=rs_stock.getDouble("stock_number");
	        	}
	        	Double stock_number = last_stock-out_quantity;
	        	
	        	
	        	PreparedStatement psmt=ccon.prepareStatement(insert);
	         	psmt.setString(1, c_id);

	         	psmt.setNull(2, Types.DOUBLE);

	        	psmt.setString(3, io_data);
	        	
	        	Date date=new Date();
	    		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		Timestamp t=new Timestamp(date.getTime());

	        	psmt.setNull(4, Types.DOUBLE);
	        	psmt.setDouble(5, out_quantity);
	        	psmt.setDouble(6, stock_number);
	        	psmt.setString(7, io_id);
	        	psmt.setTimestamp(8, t);
	        	psmt.setString(9, User_login.Username);
	        	psmt.executeUpdate();
			}
        }
        
		PreparedStatement ustmt_in = ccon.prepareStatement(update_in);
		ustmt_in.executeUpdate();
		
		PreparedStatement ustmt_out = ccon.prepareStatement(update_out);
		ustmt_out.executeUpdate();
		
		PreparedStatement ustmt_clear = ccon.prepareStatement(update_clear);
		ustmt_clear.executeUpdate();
		
		JOptionPane.showMessageDialog(new JFrame(),"记账成功");
    	
       }
    catch(SQLException e2){
    	System.out.print(e2);
    	}
    
    
    }
    
    public static void main(String[] args) {
   new inventory_account(); 
    }
}
