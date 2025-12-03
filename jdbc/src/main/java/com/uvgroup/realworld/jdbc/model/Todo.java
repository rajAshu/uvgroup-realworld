package com.uvgroup.realworld.jdbc.model;

public class JDBCTodo {

	private int id;
	private String description;
	private boolean isDone;

	public JDBCTodo() {
		super();
	}

	public JDBCTodo(int id, String description, boolean isDone) {
		this();
		this.id = id;
		this.description = description;
		this.isDone = isDone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public String toString() {
		return "JDBCTodo [id=" + id + ", description=" + description + ", isDone="
				+ isDone + "]";
	}

}
