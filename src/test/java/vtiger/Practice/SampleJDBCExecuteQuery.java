package vtiger.Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteQuery {

	public static void main(String[] args) throws SQLException {
		
		Driver driverRef = new Driver(); //--- this is mysql driver
		Connection con = null;
		
		try {
		//Step 1: register the driver/database
		DriverManager.registerDriver(driverRef);
		
		//Step 2: get the connection with database - use database name
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "root");
		
		//Step 3: issue create statement
		Statement state = con.createStatement();
		
		//Step 4: execute the query - provide table name
		ResultSet result = state.executeQuery("select * from customerinfo;");
		
		while(result.next())
		{
			System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3));
		}
		}
		finally 
		{
			//Step 5: close the database
			con.close();
		}
}
}