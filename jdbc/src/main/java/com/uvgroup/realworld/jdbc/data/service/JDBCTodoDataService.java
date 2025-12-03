package com.uvgroup.realworld.jdbc.data.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.uvgroup.realworld.jdbc.hsql.HsqlDatabase;
import com.uvgroup.realworld.jdbc.model.JDBCTodo;

public class JDBCTodoDataService {

	private static final String INSERT_JDBCTodo_QUERY = "INSERT INTO JDBCTodo(DESCRIPTION,IS_DONE) VALUES(?, ?)";

	private static final String DELETE_JDBCTodo_QUERY = "DELETE FROM JDBCTodo WHERE ID=?";

	HsqlDatabase db = new HsqlDatabase();

	public Logger logger = LogManager.getLogger(this.getClass());

	public void insertJDBCTodos(List<JDBCTodo> JDBCTodos) {
		for (JDBCTodo JDBCTodo : JDBCTodos) {
			insertJDBCTodo(JDBCTodo);
		}
	}

	private void insertJDBCTodo(JDBCTodo JDBCTodo) {
		PreparedStatement st = null;
		try {
			st = db.conn.prepareStatement(INSERT_JDBCTodo_QUERY);
			st.setString(1, JDBCTodo.getDescription());
			st.setBoolean(2, JDBCTodo.isDone());
			st.execute();
		} catch (SQLException e) {
			logger.fatal("Query Failed : " + INSERT_JDBCTodo_QUERY, e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// Ignore - nothing we can do..
				}
			}
		}
	}

	public void deleteJDBCTodo(int id) {
		PreparedStatement st = null;
		try {
			st = db.conn.prepareStatement(DELETE_JDBCTodo_QUERY);
			st.setInt(1, id);
			st.execute();
		} catch (SQLException e) {
			logger.fatal("Query Failed : " + DELETE_JDBCTodo_QUERY, e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// Ignore - nothing we can do..
				}
			}
		}
	}

	public void listAllRows(String expression) throws SQLException {
		Statement st = db.conn.createStatement();
		ResultSet rs = st.executeQuery(expression);
		printResultSet(rs);
		st.close();
	}

	public void printResultSet(ResultSet rs) throws SQLException {
		int colmax = rs.getMetaData().getColumnCount();
		while (rs.next()) {
			for (int i = 0; i < colmax; ++i) {
				Object o = rs.getObject(i + 1);
				logger.info(o.toString() + " ");
			}
			logger.info(" ");
		}
	}

	public static void main(String[] args) throws SQLException {

		JDBCTodoDataService dataservice = new JDBCTodoDataService();

		dataservice.insertJDBCTodos(Arrays.asList(new JDBCTodo(0, "New JDBCTodo fasdf",
				false)));
		dataservice.deleteJDBCTodo(1);
		dataservice.listAllRows("SELECT * FROM JDBCTodo");

		dataservice.db.closeConnection();
	}
}