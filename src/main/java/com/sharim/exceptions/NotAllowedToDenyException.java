package com.sharim.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Not allowed to deny")
public class NotAllowedToDenyException extends RuntimeException {
}
