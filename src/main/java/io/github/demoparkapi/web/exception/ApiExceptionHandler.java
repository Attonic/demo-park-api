package io.github.demoparkapi.web.exception;

import io.github.demoparkapi.exception.UsernameUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroMensage> methodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request,
            BindingResult result){

        log.error("Api error - ", ex);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErroMensage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) invalido(s)", result));
    }

    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity<ErroMensage>  usernameUniqueViolationException(RuntimeException runtimeException, HttpServletRequest request) {

        log.error("Api error - ", runtimeException);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErroMensage(request, HttpStatus.CONFLICT, runtimeException.getMessage()));
    }

    public ResponseEntity<ErroMensage> EntityNotFoundException(RuntimeException runtimeException, HttpServletRequest request) {

        log.error("Api Error - ", runtimeException);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErroMensage(request, HttpStatus.NOT_FOUND, runtimeException.getMessage()));
    }

    public ResponseEntity<ErroMensage> PasswordInvalidException(RuntimeException runtimeException, HttpServletRequest request){

        log.error("Api error - ", runtimeException);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                .body(new ErroMensage(request, HttpStatus.BAD_REQUEST, runtimeException.getMessage()));
    }
}
