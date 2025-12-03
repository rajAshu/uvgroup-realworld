package com.uvgroup.realworld.springjdbc.hsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SpringHsqlDatabase {
	public Connection conn;

	// Connecting to database =>
	// Executing Query
	public SpringHsqlDatabase() {
		try {
			loadJdbcDriverForSpringHsqlDb();
			setupConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void setupConnection() throws SQLException {
		conn = DriverManager.getConnection("jdbc:SpringHsqldb:db_file", "sa", "");
	}

	private void loadJdbcDriverForSpringHsqlDb() throws ClassNotFoundException {
		Class.forName("org.SpringHsqldb.jdbcDriver");
	}

	private void shutdownSpringHsqlDatabase() throws SQLException {
		Statement st = conn.createStatement();
		st.execute("SHUTDOWN");
	}

	public void closeConnection() throws SQLException {
		shutdownSpringHsqlDatabase();
		conn.close(); // if there are no other open connection
	}
}
