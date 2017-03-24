package balancika.ame.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import balancika.ame.configuration.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(value = NullPointerException.class)
    public String handleNullPointerException(Exception e) {
    	System.out.println("NullPointerException");
        return "404";
    }
   
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public String handleResourceNotFoundException() {
    	System.out.println("NOT_FOUND");
        return "404";
    }
    
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String handleAllException(Exception e) {   
    	e.printStackTrace();
    	System.out.println("INTERNAL_SERVER_ERROR");       	
        return "404";
    }
    
}
