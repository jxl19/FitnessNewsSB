package com.fitnessnews.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{
	public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message){
        super(message);
        
        LOGGER.error("Page not found");
        
    }
}