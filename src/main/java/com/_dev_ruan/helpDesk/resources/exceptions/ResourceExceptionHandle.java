package com._dev_ruan.helpDesk.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com._dev_ruan.helpDesk.services.exceptions.DataIntegrityViolationException;
import com._dev_ruan.helpDesk.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;




@ControllerAdvice
public class ResourceExceptionHandle {

	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex,
			HttpServletRequest request) {

		
				StandardError error = new StandardError (System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value(),
				"Object Not Found",
				ex.getMessage(),
				request.getRequestURI());

		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ValidationError> dataIntegrityViolationException(DataIntegrityViolationException ex,
			HttpServletRequest request) {
			
		
		ValidationError error = new ValidationError (System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value(),
				"Integrity Data Violity",
				ex.getMessage(),
				request.getRequestURI());
		
		if(ex.getMessage().contains("EMAIL")) {
			error.addErrors("Email", "Email Invalido ou já existente");
		}
		if(ex.getMessage().contains("CPF")) {
			error.addErrors("Cpf", "Cpf Invalido ou já existente");
		}
		
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		
	}
	
	
}
