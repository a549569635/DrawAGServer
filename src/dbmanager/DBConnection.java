package dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	
	 static String user = "root";
	 static String password = "665588";
	 static String url = "jdbc:mysql://localhost:3306/DrawAGuess";
	 static String driver = "com.mysql.jdbc.Driver";
	 static String tableName = "users";
	 static String sqlstr;
	 static Connection con = null;
	 static PreparedStatement stmt = null;
	 static ResultSet rs = null;
	 
	 public Connection getCon(){
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con=DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return con;
		}

		public void closeAll(){
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		public int update(String sql,String... pras){
			int resu=0;
			con=getCon();
			try {
				stmt=con.prepareStatement(sql);
				for(int i=0;i<pras.length;i++){
					stmt.setString(i+1,pras[i]);
				}
				resu=stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				closeAll();
			}
			return resu;
		}

		public ResultSet query(String sql,String... pras){
			con=getCon();
			try {
				stmt=con.prepareStatement(sql);
				
				if(pras!=null)
					for(int i=0;i<pras.length;i++){
						stmt.setString(i+1, pras[i]);
					}
				rs=stmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rs;
		}
}
