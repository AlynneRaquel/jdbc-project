package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;

public class DB {

	private static Connection conn = null;

	//Conex�o banco de dados
	public static Connection getConnection( ) {
		if(conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props); 
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}	
		return conn;
	}

	//Fechando a conex�o
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	//M�todo para carregar as propriedade de Db.properties
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			 Properties props = new Properties();
			 props.load(fs);
			 return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	//fechando o closeStatement
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//fechando o ResultSet -- Evitando exce��es no pg principal, fecha as cone��es aqui na classe de conex�o
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	

}