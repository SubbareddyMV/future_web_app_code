package in.ineuron.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcConnection {

	private JdbcConnection() {

	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getJdbcConnection() throws SQLException, IOException {
		String dbPath="D:\\iNeuronProjects\\JDBCCRUDAPPLICATION\\src\\main\\java\\in\\ineuron\\property\\dbCredentials.properties";
		HikariConfig config = new HikariConfig(dbPath);
		HikariDataSource dataSource = new HikariDataSource(config);
		Connection connection = dataSource.getConnection();
		return connection;
	}
	
	
	
	/*
	 * public static Connection getJdbcConnection() throws SQLException, IOException
	 * { String dbLoc =
	 * "D:\\MyWorkspace\\JDBCCRUDAPPUSINGHTML\\src\\main\\java\\in\\ineuron\\property\\dbCredentials.properties";
	 * FileInputStream fis = new FileInputStream(dbLoc); Properties properties = new
	 * Properties(); properties.load(fis); String url =
	 * properties.getProperty("url"); String username =
	 * properties.getProperty("username"); String password =
	 * properties.getProperty("password"); Connection connection =
	 * DriverManager.getConnection(url, username, password); // Connection
	 * connection = DriverManager.getConnection(url, username, password); return
	 * connection; }
	 */
	 
}
