package com.pokemon.pokeapi.errohandling;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
@RestController
public class UsuarioNotFoundAdvice {
//	  @ResponseBody
//	  @ExceptionHandler(UsuarioNotFoundException.class)
//	  @ResponseStatus(HttpStatus.NOT_FOUND)
//	  public String usuarioNotFoundHandler(UsuarioNotFoundException ex) {
//	    return ex.getMessage();
//	  }
	  @ExceptionHandler(UsuarioNotFoundException.class)
	  public final ResponseEntity<ErrorDetails> handleUsuarioNotFoundException(UsuarioNotFoundException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	  }
	  
	  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
	  public final ResponseEntity<ErrorDetails> handleTypemismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
}
