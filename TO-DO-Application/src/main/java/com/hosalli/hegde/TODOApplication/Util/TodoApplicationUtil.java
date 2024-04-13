package com.hosalli.hegde.TODOApplication.Util;


import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class TodoApplicationUtil {
	private static Logger logger = LoggerFactory.getLogger(TodoApplicationUtil.class);
	private static final Gson gson = new Gson();

	public static String getJsonFromJava(Object object) {
		return gson.toJson(object);
	}
	
	public static boolean validateBeanContraint(Object obj) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		if (!violations.isEmpty()) {
			logger.info("validation failed");
			for (ConstraintViolation<Object> violation : violations) {
				logger.error(violation.getMessage());
			}
			return false;
		}
		return true;
	}
}
