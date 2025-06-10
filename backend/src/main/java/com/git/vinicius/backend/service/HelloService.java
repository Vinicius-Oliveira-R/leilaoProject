package com.git.vinicius.backend.service;

import org.springframework.stereotype.Service;

import com.git.vinicius.backend.model.Calculadora;


@Service
public class HelloService{
    public Calculadora somar(Calculadora calculadora){
        calculadora.setResultado(calculadora.getValor1()+calculadora.getValor2());
        return calculadora;
    }
}