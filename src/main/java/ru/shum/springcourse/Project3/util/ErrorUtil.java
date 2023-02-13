package ru.shum.springcourse.Project3.util;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorUtil {
	public static void returnErrors(BindingResult bindingResult) {
		StringBuilder msgError = new StringBuilder();
		
		List<FieldError> errors = bindingResult.getFieldErrors();
		
		for(FieldError error : errors) {
			msgError
			.append(error.getField())
			.append(" - ")
			.append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
			.append(";");
		}

		throw new Exception(msgError.toString());
	}
}
