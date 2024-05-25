package com.hosalli.hegde.TODOApplication.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteTodo {

	@NotNull
	private long todoId;

	public Long getTodoId() {
		return todoId;
	}

	@JsonProperty("todoId")
	public void setTodoId(Long todoId) {
		this.todoId = todoId;
	}
	
}
