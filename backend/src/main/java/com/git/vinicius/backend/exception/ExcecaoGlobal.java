package com.git.vinicius.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.git.vinicius.backend.exception.NaoEncontradoException;
import com.git.vinicius.backend.dto.RespostaErro;

@RestControllerAdvice
public class ExcecaoGlobal {
    @ExceptionHandler(NaoEncontradoException.class)
     public ResponseEntity<RespostaErro> NaoEncontrada(NaoEncontradoException ex, WebRequest request){
        RespostaErro respostaErro = new RespostaErro(HttpStatus.NOT_FOUND.value(), "Não Encontrado", ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(respostaErro,HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<RespostaErro> global(Exception ex, WebRequest request){
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST.value(), "Erro Interno", ex.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(respostaErro,HttpStatus.BAD_REQUEST);
    }
}
