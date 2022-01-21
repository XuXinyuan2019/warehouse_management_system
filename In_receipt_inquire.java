import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import javax.swing.*;
import javax.swing.table.*;
public class In_receipt_inquire extends Inquire_frame implements ActionListener {
	DefaultTableModel tablemodel;
	JTable table;
	JScrollPane jsp;
	JFrame jf;
	boolean f=false;
	public In_receipt_inquire(){
		title.setText("进仓单查询");
		title.setFont(new Font("等线",Font.BOLD,30));
		lb1.setText("进仓单号");
		lb2.setText("商品代码");
		lb3.setText("进仓日期");
		lb4.setText("至");
		bt1.addActionListener(this);
		bt2.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		if(cmd.equals("重置")){
			clearText();
		}
		else if(cmd.equals("查询")){
			if(f){
				this.remove(jsp);
			}
			String in_id1=tf1.getText();
			String c_id1=tf2.getText();
			String date1=tf3.getText();
			String date2=tf4.getText();
			
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC驱动
	        String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
	        String userName = "user05"; //用户名
	        String userPwd = "xyz5678SQL"; // 密码
	        
	        try{Class.forName (driverName);}
            catch(ClassNotFoundException e1){}
			String sql="select * from in_receipt where 1=1";
			if(!(in_id1.equals(""))&&in_id1!=null) sql=sql+" and in_id like '%"+in_id1+"%'";
			if(!(c_id1.equals(""))&&c_id1!=null) sql=sql+" and c_id like '%"+c_id1+"%'";
			if(!(date1.equals(""))&&date1!=null) sql=sql+" and in_date >= '"+date1+"'";
			if(!(date2.equals(""))&&date2!=null) sql=sql+" and in_date <= '"+date2+"'";
			try{
            	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);//数据源
            	Statement st=ccon.createStatement();
            	
            	//查询结果的表格输出
            	ResultSet rs=st.executeQuery(sql);
            	tablemodel=new DefaultTableModel();
            	//创建表头
            	tablemodel.setColumnIdentifiers(new Object[]{"进仓单号in_id","进仓日期in_id","商品代码c_id","商品价格item_price","商品数量quantity","进账标识c_flag","操作员u_id"});
            	
            	while(rs.next()) {
					String in_id=rs.getString("in_id");
					Timestamp date=rs.getTimestamp("in_date");
					String c_id=rs.getString("c_id");
					double item_price=rs.getDouble("item_price");
					double quantity=rs.getDouble("quantity");
					String c_flag=rs.getString("c_flag");
					String u_id=rs.getString("u_id");
					//把以上数据添加到表格模型的一行中
					tablemodel.addRow(new Object[]{in_id,date,c_id,item_price,quantity,c_flag,u_id});
					
            	}
//            	table=new JTable(tablemodel);
//				jsp=new JScrollPane(table);
//				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//				jf=new JFrame();
//				jf.add(jsp);
//				jf.setSize(500, 500);
//				jf.setLocationRelativeTo(null);	
//				jf.setVisible(true);
//				
        		table=new JTable(tablemodel);
        		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        		table.setModel(tablemodel);
        		jsp=new JScrollPane(table);
        		jsp.setBounds(90, 400, 540, 200);
        		this.add(jsp);
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
	  }
	public static void main(String[] args) {
		

	}

}
