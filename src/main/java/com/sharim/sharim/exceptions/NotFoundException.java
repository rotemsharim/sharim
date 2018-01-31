package com.sharim.sharim.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND,reason = "resource not found")
public class NotFoundException extends RuntimeException{

}
