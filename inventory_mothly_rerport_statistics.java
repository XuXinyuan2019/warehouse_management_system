import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class inventory_mothly_rerport_statistics {
	public inventory_mothly_rerport_statistics(){
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC驱动
        String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
        String userName = "user05"; //用户名
        String userPwd = "xyz5678SQL"; // 密码
        
        try{Class.forName (driverName);}
        catch(ClassNotFoundException e1){}
		String month_inventory="select year(io_date) as y,month(io_date) as m,c_id,sum(in_quantity) as sum_in_quantity,sum(out_quantity) as sum_out_quantity from inventory_account group by year(io_date),month(io_date),c_id";//月查询
		String de_moth_report="delete from inventory_monthly_report";
		String select_last_line="select top(1) year(io_date),month(io_date),c_id,stock_number from inventory_account where year(io_date)=? and month(io_date)=? and c_id=? group by year(io_date),month(io_date),c_id,stock_number,acc_date order by acc_date desc";
		String insert_month_report="insert into inventory_monthly_report values(?,?,?,?,?,?,?,?,?)";
		try{
        	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);//数据源
        	Statement st=ccon.createStatement();
        	st.executeUpdate(de_moth_report);
        	ResultSet rs_month1=st.executeQuery(month_inventory);
        	while(rs_month1.next()){
        		String year=rs_month1.getString("y");
        		String month=rs_month1.getString("m");
        		String c_id=rs_month1.getString("c_id");
        		//String u_id=rs_month1.getString("u_id");
        		double sum_in_quantity=rs_month1.getDouble("sum_in_quantity");
        		double sum_out_quantity=rs_month1.getDouble("sum_out_quantity");
        		
        		double stock_number=0;
        		PreparedStatement psmt1=ccon.prepareStatement(select_last_line);
            	psmt1.setString(1, year);
            	psmt1.setString(2, month);
            	psmt1.setString(3, c_id);
            	ResultSet rs_stock=psmt1.executeQuery();
            	if(rs_stock.next()){
            		stock_number=rs_stock.getDouble("stock_number");
            	}
            	
            	PreparedStatement psmt2=ccon.prepareStatement(insert_month_report);
            	psmt2.setString(1, year+""+month);
            	psmt2.setString(2, c_id);
            	psmt2.setNull(3, Types.DOUBLE);
            	psmt2.setDouble(4, sum_in_quantity);
            	psmt2.setDouble(5, sum_out_quantity);
            	psmt2.setDouble(6, stock_number);
            	psmt2.setNull(7, Types.DOUBLE);
            	psmt2.setString(8, User_login.Username);
            	psmt2.setString(9, null);
            	psmt2.executeUpdate();
            	
        		//System.out.println(year+""+month+" "+c_id+" "+sum_in_quantity+" "+sum_out_quantity+" "+stock_number);
        	}
        	JOptionPane.showMessageDialog(new JFrame(),"月报统计成功");
	    }
		catch(SQLException e2){
        	System.out.print(e2);
        }
	}
	public static void main(String args[]){
		//new inventory_mothly_rerport_statistics();
	}
}
