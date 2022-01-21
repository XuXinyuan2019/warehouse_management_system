import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.swing.*;
import javax.swing.table.*;

public class inventory_account_inquire extends Inquire_frame implements ActionListener {
	DefaultTableModel tablemodel;
	JTable table;
	JScrollPane jsp;
	JFrame jf;
	//
	JScrollPane jsp1;
	JTable table1;
	DefaultTableModel model1;
	boolean f=false;
	public inventory_account_inquire(){
		title.setText("台账查询");
		title.setFont(new Font("等线",Font.BOLD,30));
		lb1.setText("");
		lb2.setText("商品代码");
		lb3.setText("");
		lb4.setText("");
		lb5.setText("");
		lb6.setText("");
		lb3.setBounds(80, 200, 0, 0);
		lb4.setBounds(430, 200, 0,0);
		lb6.setBounds(430, 250, 0, 0);
		tf1.setBounds(220, 120, 0, 0);
		tf2.setBounds(220, 160, 200, 30);
		tf3.setBounds(220, 200, 0, 0);
		tf4.setBounds(460, 200, 0, 0);
		tf5.setBounds(220, 250,0, 0);
		tf6.setBounds(460, 250, 0, 0);
		bt1.addActionListener(this);
		bt2.addActionListener(this);
//
		



	}
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		if(cmd.equals("重置")){
			clearText();
		}
		else if(cmd.equals("查询")){
			if(f){
				this.remove(jsp1);
			}
			//String io_id1=tf1.getText();
			String c_id1=tf2.getText();
			//String date1=tf3.getText();
			//String date2=tf4.getText();
			//String date3=tf5.getText();
			//String date4=tf6.getText();
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC驱动
	        String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
	        String userName = "user05"; //用户名
	        String userPwd = "xyz5678SQL"; // 密码
	        
	        try{Class.forName (driverName);}
            catch(ClassNotFoundException e1){}
			String sql="select * from inventory_account where 1=1";
			//if(!(io_id1.equals(""))&&io_id1!=null) sql=sql+" and io_id like '%"+io_id1+"%'";
			if(!(c_id1.equals(""))&&c_id1!=null) sql=sql+" and c_id like '%"+c_id1+"%'";
			//if(!(date1.equals(""))&&date1!=null) sql=sql+" and io_date >= '"+date1+"'";
			//if(!(date2.equals(""))&&date2!=null) sql=sql+" and io_date <= '"+date2+"'";
			//if(!(date3.equals(""))&&date3!=null) sql=sql+" and acc_date >= '"+date3+"'";
			//if(!(date4.equals(""))&&date4!=null) sql=sql+" and acc_date <= '"+date4+"'";
			try{
            	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);//数据源
            	Statement st=ccon.createStatement();
            	
            	
            	//查询结果的表格输出
            	ResultSet rs=st.executeQuery(sql);
            	//tablemodel=new DefaultTableModel();
            	model1=new DefaultTableModel();
            	//创建表头
            	//tablemodel.setColumnIdentifiers(new Object[]{"商品代码c_id","进价in_price","进出清仓日期io_date","进仓数量in_quantity","出仓数量out_quantity","库存量stock_number","单据号io_id","进账日期acc_date","操作员u_id"});
            	model1.setColumnIdentifiers(new Object[]{"商品代码c_id","进价in_price","进出清仓日期io_date","进仓数量in_quantity","出仓数量out_quantity","库存量stock_number","单据号io_id","进账日期acc_date","操作员u_id"});
            	
            	while(rs.next()) {
					String c_id=rs.getString("c_id");
					double in_price=rs.getDouble("in_price");
					Timestamp io_date=rs.getTimestamp("io_date");
					double in_quantity=rs.getDouble("in_quantity");
					double out_quantity=rs.getDouble("out_quantity");
					double stock_number=rs.getDouble("stock_number");
					String io_id=rs.getString("io_id");
					Timestamp acc_date=rs.getTimestamp("acc_date");
					String u_id=rs.getString("u_id");
					//把以上数据添加到表格模型的一行中
					model1.addRow(new Object[]{c_id,in_price,io_date,in_quantity,out_quantity,stock_number,io_id,acc_date,u_id});
					//tablemodel.addRow(new Object[]{c_id,in_price,io_date,in_quantity,out_quantity,stock_number,io_id,acc_date,u_id});

            	}
            	
            	
            	
//            	table=new JTable(tablemodel);
//				jsp=new JScrollPane(table);
//				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//				jf=new JFrame();
//				jf.add(jsp);
//				jf.setSize(500, 500);
//				jf.setLocationRelativeTo(null);	
//				jf.setVisible(true);
			

        		table1=new JTable(model1);
        		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        		table1.setModel(model1);
        		jsp1=new JScrollPane(table1);
        		jsp1.setBounds(80, 400, 600, 200);
        		this.add(jsp1);
        		f=true;
				
				
				
		     }
            catch(SQLException e2){
            	System.out.print(e2);
            }
			
		}
	}
	protected void clearText() {//清空文本框, 密码框的输入
	      tf1.setText("");
	      tf2.setText("");
	      tf3.setText("");
	      tf4.setText("");
	      tf5.setText("");
	      tf6.setText("");
	  }
    public static void main(String[] args) {
    	new inventory_account_inquire().setVisible(true);//创建登录窗口,并可见
    }

}
