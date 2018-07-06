package movierent.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.stereotype.Component;

@Component
public class DBManager {

	  private static DBManager instance;
	  private static Connection connection;
	  private static final String DB_IP = "localhost";
	  private static final String DB_PORT = "3306";
	  private static final String DB_NAME = "movierent";
	  private static final String DB_USERNAME = "root";
	  private static final String DB_PASSWORD = "mysqlpassword";
	  private static final String URL = "jdbc:mysql://"+ DB_IP +":" + DB_PORT + "/" + DB_NAME ;
	  
	  private DBManager()
	  {
		  try {
		      Class.forName("com.mysql.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		    	e.getStackTrace();
		      System.out.println(e.getMessage());
		    }
		    try
		    {
		      connection = DriverManager.getConnection(URL, DB_USERNAME,DB_PASSWORD);
		    } catch (SQLException e) {
		    	e.getStackTrace();
		    	 System.out.println(e.getMessage());
		    }		  
	  }
	  	  
	  public static synchronized DBManager getInstance() {
			if(instance == null) {
				instance = new DBManager();
			}
			return instance;
	}
	  
	  public Connection getConnection() {
		 if(connection == null) {
			 try {
			      Class.forName("com.mysql.jdbc.Driver");
			    } catch (ClassNotFoundException e) {
			    	e.getStackTrace();
			      System.out.println(e.getMessage());
			    }
			    try
			    {
			      connection = DriverManager.getConnection(URL, DB_USERNAME,DB_PASSWORD);
			    } catch (SQLException e) {
			    	e.getStackTrace();
			    	 System.out.println(e.getMessage());
			    }		  
		 }
	    return connection;
}
}
