package com.uvgroup.realworld.springjdbc.data.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.uvgroup.realworld.springjdbc.SpringHsql.SpringHsqlDatabase;
import com.uvgroup.realworld.springjdbc.model.SpringTodo;

public class SpringTodoDataServiceSpringJDBC2 {

	private static final String INSERT_SpringTodo_QUERY = "INSERT INTO SpringTodo(DESCRIPTION,IS_DONE) VALUES(?, ?)";

	private static final String DELETE_SpringTodo_QUERY = "DELETE FROM SpringTodo WHERE ID=?";

	SpringHsqlDatabase db = new SpringHsqlDatabase();

	JdbcTemplate jdbcTemplate = new JdbcTemplate(
			new SingleConnectionDataSource(db.conn, false));

	public static Logger logger = LogManager
			.getLogger(SpringTodoDataServiceSpringJDBC2.class);

	public void insertSpringTodos(List<SpringTodo> SpringTodos) {
		for (SpringTodo SpringTodo : SpringTodos) {
			insertSpringTodo(SpringTodo);
		}
	}

	private void insertSpringTodo(SpringTodo SpringTodo) {
		jdbcTemplate.update(INSERT_SpringTodo_QUERY, SpringTodo.getDescription(),
				SpringTodo.isDone());
	}

	public void deleteSpringTodo(int id) {
		jdbcTemplate.update(DELETE_SpringTodo_QUERY, id);
	}

	public List<SpringTodo> retrieveAllSpringTodos() throws SQLException {
		jdbcTemplate.query("SELECT * FROM SpringTodo",
				new BeanPropertyRowMapper<SpringTodo>(SpringTodo.class));

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

		SpringTodoDataServiceSpringJDBC2 dataservice = new SpringTodoDataServiceSpringJDBC2();

		dataservice.insertSpringTodos(Arrays.asList(new SpringTodo(0, "New SpringTodo fasdf1",
				false)));
		dataservice.deleteSpringTodo(1);
		List<SpringTodo> SpringTodos = dataservice.retrieveAllSpringTodos();
		logger.info(SpringTodos);
		dataservice.db.closeConnection();
	}
}
