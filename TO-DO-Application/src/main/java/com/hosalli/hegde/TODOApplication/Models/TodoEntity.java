package com.hosalli.hegde.TODOApplication.Models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class TodoEntity {

	private long taskId;
	@NotNull
	@Size(max = 255)
	private String taskName;
	
	@NotNull
	@Size(max = 1028)
	private String taskDescription;
	
	@NotNull
	@Future
	private Date targetDate;
	
	@NotNull
	private boolean done;
	
	@NotNull
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTaskName() {
		return taskName;
	}

	@JsonProperty("taskName")
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	@JsonProperty("taskDescription")
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	@JsonProperty("targetDate")
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isCompleted() {
		return done;
	}

	@JsonProperty("isCompleted")
	public void setCompleted(boolean isCompleted) {
		this.done = isCompleted;
	}

	@Override
	public String toString() {
		return "TodoPojo [taskName=" + taskName + ", taskDescription=" + taskDescription + ", targetDate=" + targetDate
				+ ", isCompleted=" + done + ",TaskId"+taskId+"]";
	}
	
	public TodoEntity(@NotNull @Size(max = 255) String taskName, @NotNull @Size(max = 1028) String taskDescription,
			@NotNull @Future Date targetDate, @NotNull boolean isCompleted) {
		super();
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.targetDate = targetDate;
		this.done = isCompleted;
	}
	
	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public TodoEntity() {}

}
