package com.git.vinicius.backend.exception;

public class NaoEncontradoException extends RuntimeException {
    public NaoEncontradoException(String mensagem){
    super(mensagem);}
}
