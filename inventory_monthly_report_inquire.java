import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class inventory_monthly_report_inquire extends Inquire_frame implements ActionListener {
	DefaultTableModel tablemodel;
	JTable table;
	JScrollPane jsp;
	JFrame jf;
	boolean f=false;

	
	public inventory_monthly_report_inquire(){
		title.setText("�±���ѯ");
		title.setFont(new Font("����",Font.BOLD,30));
		lb1.setText("�·�");
		lb2.setText("");
		lb3.setText("");
		lb4.setText("");
		tf2.setBounds(200, 160, 0, 0);
		tf3.setBounds(200, 200, 0, 0);
		tf4.setBounds(440, 200, 0, 0);
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
			String month=tf1.getText();
			
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC����
	        String dbURL =  "jdbc:sqlserver://10.79.255.209; DatabaseName=st_db5";
	        String userName = "user05"; //�û���
	        String userPwd = "xyz5678SQL"; // ����
	        
	        try{Class.forName (driverName);}
            catch(ClassNotFoundException e1){}
			String sql="select year_month,c_id,in_quantity,out_quantity,stock_number from inventory_monthly_report where year_month='"+month+"'";
			
			try{
            	Connection ccon=DriverManager.getConnection (dbURL,userName,userPwd);//����Դ
            	Statement st=ccon.createStatement();
            	
            	//��ѯ����ı�����
            	ResultSet rs=st.executeQuery(sql);
            	tablemodel=new DefaultTableModel();
            	//������ͷ
            	tablemodel.setColumnIdentifiers(new Object[]{"�·�year_month","��Ʒ����c_id","��������in_quantity","��������out_quantity","�����stock_number"});
            	
            	while(rs.next()) {
					String year_month=rs.getString("year_month");
					String c_id=rs.getString("c_id");
					double in_quantity=rs.getDouble("in_quantity");
					double out_quantity=rs.getDouble("out_quantity");
					double stock_number=rs.getDouble("stock_number");
					//������������ӵ����ģ�͵�һ����
					tablemodel.addRow(new Object[]{year_month,c_id,in_quantity,out_quantity,stock_number});
					
            	}
//            	table=new JTable(tablemodel);
//				jsp=new JScrollPane(table);
//				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//				jf=new JFrame();
//				jf.add(jsp);
//				jf.setSize(500, 500);
//				jf.setLocationRelativeTo(null);	
//				jf.setVisible(true);
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
	public boolean addContent(JFrame jf,JScrollPane jp){
		boolean f=false;
		return f;
	}
	public static void main(String args[]){
		
	}
}
