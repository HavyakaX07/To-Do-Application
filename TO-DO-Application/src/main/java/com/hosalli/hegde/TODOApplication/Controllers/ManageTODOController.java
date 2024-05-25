package com.hosalli.hegde.TODOApplication.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hosalli.hegde.TODOApplication.Models.DeleteTodo;
import com.hosalli.hegde.TODOApplication.Models.TodoEntity;
import com.hosalli.hegde.TODOApplication.Models.TodoPojo;
import com.hosalli.hegde.TODOApplication.Service.TodoService;

import jakarta.validation.Valid;

@Controller
public class ManageTODOController {
	private static Logger logger = LoggerFactory.getLogger(LoginRegistrationManagementController.class);
	
	private TodoService todoService;
	
	@Autowired
	public void setTodoService(TodoService todoService) {
		this.todoService = todoService;
	}
	
	
	@RequestMapping(value = "manageTodos", method = RequestMethod.GET)
	public String getTodosPage(ModelMap map) {
		String loggedInUser = getLoggedInUsername();
		logger.info("+++++++++++++++++Logged in user is {}++++++++++++++++",loggedInUser);
		//String loggedInUser = "root";
		List<TodoPojo> listTodos = todoService.getTodosForLoggedInUser(loggedInUser);
		logger.info("List of tods are {}",listTodos.toString());
		map.addAttribute("todos", listTodos);
		map.addAttribute("loggedInUser",loggedInUser);
		return "manageTodosPage";
	}
	
	@RequestMapping(value = "add-todos", method = RequestMethod.POST)
	public String addTodoTask(@Valid @RequestBody TodoEntity todoPojo,BindingResult bindingResult)
	{
		logger.info(todoPojo.toString());
		if(bindingResult.hasErrors()) {
			logger.error("TodoPojo is invalid");
		}
		todoService.addTodoTask(todoPojo);
		logger.info("Todo task added {}",todoPojo);
		//After that need to redirect to again
		return "redirect:manageTodos";
		
	}
	
	@RequestMapping(value = "update-todos",method = RequestMethod.POST)
	public String updateTodos(@Valid @RequestBody TodoPojo todoPojo,BindingResult bindingResult) {
		logger.info("++++++++++++++++++Update pojo{}++++++++++++++++",todoPojo.toString());
		if(bindingResult.hasErrors()) {
			logger.error("TodoPojo is invalid");
		}
		todoService.updateTodoTask(todoPojo);
		return "redirect:manageTodos";
	}
	
	@RequestMapping(value = "delete-todos", method = RequestMethod.POST)
	public String deleteTodos(@Valid @RequestBody DeleteTodo deleteTodo,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			logger.error("TodoPojo is invalid");
		}
		todoService.deleteTodoTask(deleteTodo.getTodoId());
		return "redirect:manageTodos";
	}
	
	private String getLoggedInUsername() {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
