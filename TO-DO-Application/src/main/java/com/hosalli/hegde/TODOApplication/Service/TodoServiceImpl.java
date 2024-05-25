package com.hosalli.hegde.TODOApplication.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hosalli.hegde.TODOApplication.Dao.TodoDao;
import com.hosalli.hegde.TODOApplication.Models.TodoEntity;
import com.hosalli.hegde.TODOApplication.Models.TodoPojo;

import jakarta.validation.Valid;

@Service
public class TodoServiceImpl implements TodoService {
	private Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
	
	private TodoDao todoDao;
	
	@Autowired
	public void setTodoDao(TodoDao todoDao) {
		this.todoDao = todoDao;
	}

	@Override
	public List<TodoPojo> getTodosForLoggedInUser(String loggedInUser) {
		//Only for dev test
		List<TodoPojo> response = new ArrayList<>();
		Long userId = getUserIdFromName(loggedInUser);
		List<Map<String, Object>> daoResponse = todoDao.getTodoListForUser(userId);
		if (!CollectionUtils.isEmpty(daoResponse))
			response = getResponseJsonFromMap(daoResponse);
		return response;
	}

	private List<TodoPojo> getResponseJsonFromMap(List<Map<String, Object>> daoResponse) {
		List<TodoPojo> response = new ArrayList<>();
		for (Map<String, Object> mapObj : daoResponse) {
			TodoPojo obj = new TodoPojo((String) mapObj.get("task_Name"), (String) mapObj.get("task_Description"),
					(Date) mapObj.get("target_Date"), (boolean) mapObj.get("done"), (Long) mapObj.get("task_id"));
			response.add(obj);
		}
		return response;
	}

	private Long getUserIdFromName(String loggedInUser) {
		// TODO Auto-generated method stub
		//Get userid from username from the table.
		return 1001L;
	}

	@Override
	public void addTodoTask(TodoEntity pojo) {
		// Get the logged in username and id and set it in pojo.
		// Only for devtesting
		//TodoEntity entity = getEntityFromPojo(pojo);
		pojo.setUserId(1001L);
		todoDao.addTodoTask(pojo);
		
	}

	private TodoEntity getEntityFromPojo(TodoPojo pojo) {
		TodoEntity entity = new TodoEntity(pojo.getTaskName(),pojo.getTaskDescription(),pojo.getTargetDate(),pojo.isCompleted());	
		return entity;
	}

	@Override
	public void deleteTodoTask(Long entityId) {
		todoDao.deleteTodoTask(entityId);
		
	}

	@Override
	public void updateTodoTask(@Valid TodoPojo todoPojo) {
		todoDao.updateTodoTask(todoPojo.getTaksId(),todoPojo.getTaskName(),todoPojo.getTaskDescription(),todoPojo.getTargetDate(),todoPojo.isCompleted());
		
	}

}
