package Connection;

import java.sql.*;

public class MysqlConnection {
	//All static  values for the JDBC/DATABASE
		public static final String DRIVER ="com.mysql.cj.jdbc.Driver";
		public static final String URL ="jdbc:mysql://localhost:3307/miniproject";
	    public static final String USERNAME ="root";
	    public static final String PASSWORD ="xxxxx";
	    
//	    Class.forName(MysqlConnection.DRIVER);
	    public static  Connection connection=null;
	    public static Connection getConnection()
	    {
	    	try {
				 connection = DriverManager.getConnection(MysqlConnection.URL, MysqlConnection.USERNAME,
						MysqlConnection.PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return connection;
				    	
	    }
	    

}
