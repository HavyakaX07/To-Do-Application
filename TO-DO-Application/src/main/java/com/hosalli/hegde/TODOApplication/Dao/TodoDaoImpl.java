package com.hosalli.hegde.TODOApplication.Dao;

import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hosalli.hegde.TODOApplication.Models.TodoEntity;

@Repository
public class TodoDaoImpl implements TodoDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Map<String, Object>> getTodoListForUser(Long userId) {
		String sqlQuery = "Select * from Todo_Entity where user_Id = :userId";
		MapSqlParameterSource mapSql = new MapSqlParameterSource();
		mapSql.addValue("userId", userId,Types.BIGINT);
		NamedParameterJdbcTemplate queryobject = new NamedParameterJdbcTemplate(jdbcTemplate);
		return queryobject.queryForList(sqlQuery, mapSql);
	}

	@Override
	public void addTodoTask(TodoEntity entity) {
		String sqlQuery = "INSERT INTO Todo_Entity (task_Name, task_Description, target_Date, done, user_Id) VALUES (?, ?, ?, ?, ?)";
		Object[] params = { entity.getTaskName(), entity.getTaskDescription(),
		                   entity.getTargetDate(), entity.isCompleted(), entity.getUserId()};
		int affectedRows = jdbcTemplate.update(sqlQuery, params);
		
	}

	@Override
	public void deleteTodoTask(Long entityId) {
		String sqlQuery = "DELETE FROM Todo_Entity WHERE TASK_ID = :taskId";
		MapSqlParameterSource mapSql = new MapSqlParameterSource();
		mapSql.addValue("taskId", entityId,Types.BIGINT);
		NamedParameterJdbcTemplate queryobject = new NamedParameterJdbcTemplate(jdbcTemplate);
		int affectedRows = queryobject.update(sqlQuery, mapSql);
		
	}

	@Override
	public void updateTodoTask(long taksId, String taskName, String taskDescription, Date targetDate,
			boolean completed) {
		String sqlQuery = "update Todo_Entity set task_Name=?, task_Description=?, target_Date=?, done=? where task_id = ?";
		Object[] params = {taskName, taskDescription, targetDate,
				completed, taksId};
		int affectedRows = jdbcTemplate.update(sqlQuery, params);
		
	}

}
