package com.hosalli.hegde.TODOApplication.Models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoPojo {

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
	private long taskIdToUpdate;

	public long getTaksId() {
		return taskIdToUpdate;
	}

	@JsonProperty("taskIdToUpdate")
	public void setTaksId(long taksId) {
		this.taskIdToUpdate = taksId;
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
				+ ", isCompleted=" + done + "TaskId"+ taskIdToUpdate +"]";
	}
	
	public TodoPojo(@NotNull @Size(max = 255) String taskName, @NotNull @Size(max = 1028) String taskDescription,
			@NotNull @Future Date targetDate, @NotNull boolean isCompleted,long taskId) {
		super();
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.targetDate = targetDate;
		this.done = isCompleted;
		this.taskIdToUpdate = taskId;
	}
	
	public TodoPojo() {}
	
}
