package ks52team02.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GrobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionAllHandler(Exception ex, HttpServletRequest request) {
		
		log.error("[url:{}] error message : {}", request.getRequestURI(), ex.getStackTrace());
		
		return "error/500";
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String exceptionNotFoundHandler(NoResourceFoundException ex, HttpServletRequest request) {
		
		log.error("[url:{}] error message : {}", request.getRequestURI(), ex.getMessage());
		
		return "error/404";
	}
	
	@ExceptionHandler({IllegalArgumentException.class, MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String exceptionIllegalArgsHandler(Exception ex, HttpServletRequest request) {
		log.error("[url:{}] error message : {}", request.getRequestURI(), ex.getMessage());
		return "error/400";
	}
	
}
