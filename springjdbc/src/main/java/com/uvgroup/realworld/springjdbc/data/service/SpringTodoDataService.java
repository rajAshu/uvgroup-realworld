package com.uvgroup.realworld.springjdbc.data.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.uvgroup.realworld.springjdbc.SpringHsql.SpringHsqlDatabase;
import com.uvgroup.realworld.springjdbc.model.SpringTodo;

public class SpringTodoDataService {

	private static final String INSERT_SpringTodo_QUERY = "INSERT INTO SpringTodo(DESCRIPTION,IS_DONE) VALUES(?, ?)";

	private static final String DELETE_SpringTodo_QUERY = "DELETE FROM SpringTodo WHERE ID=?";

	SpringHsqlDatabase db = new SpringHsqlDatabase();

	public static Logger logger = LogManager.getLogger(SpringTodoDataService.class);

	public void insertSpringTodos(List<SpringTodo> SpringTodos) {
		for (SpringTodo SpringTodo : SpringTodos) {
			insertSpringTodo(SpringTodo);
		}
	}

	private void insertSpringTodo(SpringTodo SpringTodo) {
		PreparedStatement st = null;
		try {
			st = db.conn.prepareStatement(INSERT_SpringTodo_QUERY);
			st.setString(1, SpringTodo.getDescription());
			st.setBoolean(2, SpringTodo.isDone());
			st.execute();
		} catch (SQLException e) {
			logger.fatal("Query Failed : " + INSERT_SpringTodo_QUERY, e);
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

	public void deleteSpringTodo(int id) {
		PreparedStatement st = null;
		try {
			st = db.conn.prepareStatement(DELETE_SpringTodo_QUERY);
			st.setInt(1, id);
			st.execute();
		} catch (SQLException e) {
			logger.fatal("Query Failed : " + DELETE_SpringTodo_QUERY, e);
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

	public List<SpringTodo> retrieveAllSpringTodos() throws SQLException {
		List<SpringTodo> SpringTodos = new ArrayList<SpringTodo>();
		Statement st = db.conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM SpringTodo");
		while (rs.next()) {
			SpringTodos.add(new SpringTodo(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
		}
		st.close();
		return SpringTodos;
	}

	public static void main(String[] args) throws SQLException {

		SpringTodoDataService dataservice = new SpringTodoDataService();

		dataservice.insertSpringTodos(Arrays.asList(new SpringTodo(0, "New SpringTodo fasdf1",
				false)));
		dataservice.deleteSpringTodo(1);
		List<SpringTodo> SpringTodos = dataservice.retrieveAllSpringTodos();
		logger.info(SpringTodos);
		dataservice.db.closeConnection();
	}
}
