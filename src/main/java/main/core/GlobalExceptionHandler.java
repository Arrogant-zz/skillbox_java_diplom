package main.core;

import main.data.response.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {BadCredentialsException.class})
    protected ResponseEntity<Object> handleUserNotAuthorizedException(
            BadCredentialsException ex, WebRequest req) {
        return handleExceptionInternal(ex, new LoginResponse(false, null), null, HttpStatus.OK, req);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotAuthorizedException(
            UsernameNotFoundException ex, WebRequest req) {
        return handleExceptionInternal(ex, new LoginResponse(false, null), null, HttpStatus.OK, req);
    }
}
