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
		title.setText("���ֵ���ѯ");
		title.setFont(new Font("����",Font.BOLD,30));
		lb1.setText("���ֵ���");
		lb2.setText("��Ʒ����");
		lb3.setText("��������");
		lb4.setText("��");
		bt1.addActionListener(this);
		bt2.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		if(cmd.equals("����")){
			clearText();
		}
		else if(cmd.equals("��ѯ")){
			if(f){
				this.remove(jsp);
			}
			String in_id1=tf1.getText();
			String c_id1=tf2.getText();
			String date1=tf3.getText();
			String date2=tf4.getText();
			
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC����
	        String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
	        String userName = "user05"; //�û���
	        String userPwd = "xyz5678SQL"; // ����
	        
	        try{Class.forName (driverName);}
            catch(ClassNotFoundException e1){}
			String sql="select * from in_receipt where 1=1";
			if(!(in_id1.equals(""))&&in_id1!=null) sql=sql+" and in_id like '%"+in_id1+"%'";
			if(!(c_id1.equals(""))&&c_id1!=null) sql=sql+" and c_id like '%"+c_id1+"%'";
			if(!(date1.equals(""))&&date1!=null) sql=sql+" and in_date >= '"+date1+"'";
			if(!(date2.equals(""))&&date2!=null) sql=sql+" and in_date <= '"+date2+"'";
			try{
            	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);//����Դ
            	Statement st=ccon.createStatement();
            	
            	//��ѯ����ı�����
            	ResultSet rs=st.executeQuery(sql);
            	tablemodel=new DefaultTableModel();
            	//������ͷ
            	tablemodel.setColumnIdentifiers(new Object[]{"���ֵ���in_id","��������in_id","��Ʒ����c_id","��Ʒ�۸�item_price","��Ʒ����quantity","���˱�ʶc_flag","����Աu_id"});
            	
            	while(rs.next()) {
					String in_id=rs.getString("in_id");
					Timestamp date=rs.getTimestamp("in_date");
					String c_id=rs.getString("c_id");
					double item_price=rs.getDouble("item_price");
					double quantity=rs.getDouble("quantity");
					String c_flag=rs.getString("c_flag");
					String u_id=rs.getString("u_id");
					//������������ӵ����ģ�͵�һ����
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
	protected void clearText() {//����ı���, ����������
	      tf1.setText("");
	      tf2.setText("");
	      tf3.setText("");
	      tf4.setText("");
	  }
	public static void main(String[] args) {
		

	}

}
