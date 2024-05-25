package com.hosalli.hegde.TODOApplication.Dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hosalli.hegde.TODOApplication.Models.TodoEntity;

public interface TodoDao {

	List<Map<String, Object>> getTodoListForUser(Long userId);
	
	void addTodoTask(TodoEntity entity);
	
	void deleteTodoTask(Long entityId);

	void updateTodoTask(long taksId, String taskName, String taskDescription, Date targetDate, boolean completed);
}
