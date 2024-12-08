package br.ifpb.dac.library_web.exception.infra;

import br.ifpb.dac.library_web.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
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

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorMessage> resourceNotFoundException(RuntimeException ex, HttpServletRequest request){
                log.error("API ERROR - ", ex);
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ErrorMessage(request,HttpStatus.NOT_FOUND, ex.getMessage()));
        }

        // Esse método captura as exceções quando os parametros forem passados inválidos
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                            HttpServletRequest request,
                                                                            BindingResult result) {
                log.error("Api Error - ", ex);
                return ResponseEntity
                        .status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, MessageKeyEnum.INVALID_FIELDS.getMessage(), result));
        }
}
