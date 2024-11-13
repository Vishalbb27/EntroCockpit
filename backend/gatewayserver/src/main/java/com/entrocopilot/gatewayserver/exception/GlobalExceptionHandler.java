package com.entrocopilot.gatewayserver.exception;


import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;



import com.entrocopilot.gatewayserver.model.ErrorDetails;


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
	
	@ExceptionHandler(APIException.class)
    public final ResponseEntity<ErrorDetails> handleAPIException(APIException exception, WebRequest request) throws Exception{
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
}
