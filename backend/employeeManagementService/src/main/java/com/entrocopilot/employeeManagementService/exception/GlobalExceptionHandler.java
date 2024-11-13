package com.entrocopilot.employeeManagementService.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.entrocopilot.employeeManagementService.model.ErrorDetails;



@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception{
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				ex.getMessage(),
				request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleNotFoundException(Exception ex,WebRequest request) throws Exception{
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				ex.getMessage(),
				request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);	
    }
	
	@ExceptionHandler(APIException.class)
	public final ResponseEntity<ErrorDetails> handleApiExceptionException(Exception ex,WebRequest request) throws Exception{
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				ex.getMessage(),
				request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);	
    }
	
	@ExceptionHandler(AlreadyExists.class)
	public final ResponseEntity<ErrorDetails> handleAlreadyExistsException(Exception ex,WebRequest request) throws Exception{
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				ex.getMessage(),
				request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
