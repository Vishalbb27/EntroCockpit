package com.introcopilot.user.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;

import com.introcopilot.user.model.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> validationErrors= new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName,validationMsg);
        });
        return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
    }
	
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
