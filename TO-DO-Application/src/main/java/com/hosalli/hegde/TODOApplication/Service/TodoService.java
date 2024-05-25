package com.hosalli.hegde.TODOApplication.Service;

import java.util.List;

import com.hosalli.hegde.TODOApplication.Models.TodoEntity;
import com.hosalli.hegde.TODOApplication.Models.TodoPojo;

import jakarta.validation.Valid;

public interface TodoService {

	List<TodoPojo> getTodosForLoggedInUser(String loggedInUser);

	void addTodoTask(@Valid TodoEntity todoPojo);

	void deleteTodoTask(Long entityId);

	void updateTodoTask(@Valid TodoPojo todoPojo);
}
