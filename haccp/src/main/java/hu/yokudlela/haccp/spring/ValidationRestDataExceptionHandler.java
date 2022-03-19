package hu.yokudlela.haccp.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @author csabakoos
 */
@ControllerAdvice
@Component
public class ValidationRestDataExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    HttpServletRequest req;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        logger.error(ex.getLocalizedMessage(), ex);
        ApiError res = new ApiError(req.getRequestURI(),"error.validation");

        for(String msg: ex.getMessage().split(",")){
            res.getErrors().add(msg.split(":")[1]);
        }

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getLocalizedMessage(), ex);
        ApiError res = new ApiError(req.getRequestURI(),"error.validation");

        ex.getBindingResult().getFieldErrors().forEach((FieldError error) -> {
            res.getErrors().add(error.getDefaultMessage());
        });

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

}
