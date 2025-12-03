package com.uvgroup.realworld.springjdbc.data.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.uvgroup.realworld.springjdbc.SpringHsql.SpringHsqlDatabase;
import com.uvgroup.realworld.springjdbc.model.SpringTodo;

class SpringTodoMapper implements RowMapper<SpringTodo> {
	public SpringTodo mapRow(ResultSet rs, int rowNum) throws SQLException {
		SpringTodo SpringTodo = new SpringTodo();
		SpringTodo.setId(rs.getInt(1));
		SpringTodo.setDescription(rs.getString(2));
		SpringTodo.setDone(rs.getBoolean(3));
		return SpringTodo;
	}
}

public class SpringTodoDataServiceSpringJdbc {

	private static final String SELECT_ALL_SpringTodoS = "SELECT * FROM SpringTodo";

	private static final String INSERT_SpringTodo_QUERY = "INSERT INTO SpringTodo(DESCRIPTION,IS_DONE) VALUES(?, ?)";

	private static final String DELETE_SpringTodo_QUERY = "DELETE FROM SpringTodo WHERE ID=?";

	SpringHsqlDatabase db = new SpringHsqlDatabase();
	JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(db.conn, false));

	public static Logger logger = LogManager.getLogger(SpringTodoDataServiceSpringJdbc.class);

	public void insertSpringTodos(List<SpringTodo> SpringTodos) {
		for (SpringTodo SpringTodo : SpringTodos) {
			insertSpringTodo(SpringTodo);
		}
	}

	private void insertSpringTodo(SpringTodo bean) {
		jdbcTemplate.update(INSERT_SpringTodo_QUERY, bean.getDescription(), bean.isDone());
	}

	public void deleteSpringTodo(int id) {
		jdbcTemplate.update(DELETE_SpringTodo_QUERY, id);
	}

	public List<SpringTodo> retrieveAllSpringTodos() throws SQLException {
		return jdbcTemplate.query(SELECT_ALL_SpringTodoS, new SpringTodoMapper());
	}

	public static void main(String[] args) throws SQLException {

		SpringTodoDataServiceSpringJdbc dataservice = new SpringTodoDataServiceSpringJdbc();

		dataservice.insertSpringTodos(Arrays.asList(new SpringTodo(0, "New SpringTodo Spring JDBC", false)));

		dataservice.deleteSpringTodo(1);

		List<SpringTodo> SpringTodos = dataservice.retrieveAllSpringTodos();
		logger.info(SpringTodos);

		dataservice.db.closeConnection();
	}
}