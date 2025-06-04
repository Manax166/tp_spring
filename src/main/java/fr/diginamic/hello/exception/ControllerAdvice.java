package fr.diginamic.hello.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ControllerAdvice {
    @ExceptionHandler({InvalidRequestParameterException.class})
    public ResponseEntity<String> traiterErreurs(InvalidRequestParameterException irp){
        return ResponseEntity.badRequest().body(irp.getMessage());
    }
}
