package estagioCEPEIN.FixacaoSpring.Models.exceptions.handle;

import estagioCEPEIN.FixacaoSpring.Models.exceptions.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    ///TRATAMENTO DE EXCEÇÃO
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected String handleException() {
        return "ERRO INTERNO NESSE DJANHO";
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleDataNotFoundException(DataNotFoundException exception) {
        return exception.getMessage();
    }
}

