package com.hospital.exception;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomEcxeptionHandler {

	String error = "error";

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handelConstraintViolationException(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put(error, ex.getMessage());
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserAlreadyExist.class)
	public Map<String, String> handelUserAlredyExist(UserAlreadyExist ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put(error, ex.getMessage());
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserNotExist.class)
	public Map<String, String> handelUserNotExist(UserNotExist ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put(error, ex.getMessage());
		return errors;
	}

}
