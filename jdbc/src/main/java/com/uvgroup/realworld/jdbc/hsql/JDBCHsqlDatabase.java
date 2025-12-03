package com.uvgroup.realworld.jdbc.hsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCHsqlDatabase {
	public Connection conn;

	public JDBCHsqlDatabase() {
		try {
			loadJdbcDriverForJDBCHsqlDb();
			setupConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void setupConnection() throws SQLException {
		conn = DriverManager.getConnection("jdbc:JDBCHsqldb:db_file", "sa", "");
	}

	private void loadJdbcDriverForJDBCHsqlDb() throws ClassNotFoundException {
		Class.forName("org.JDBCHsqldb.jdbcDriver");
	}

	private void shutdownJDBCHsqlDatabase() throws SQLException {
		Statement st = conn.createStatement();
		st.execute("SHUTDOWN");
	}

	public void closeConnection() throws SQLException {
		shutdownJDBCHsqlDatabase();
		conn.close(); // if there are no other open connection
	}
}
