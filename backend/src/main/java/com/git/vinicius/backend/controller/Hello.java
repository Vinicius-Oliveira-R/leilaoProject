package com.git.vinicius.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.git.vinicius.backend.model.Calculadora;
import com.git.vinicius.backend.service.HelloService;

import lombok.val;

@RestController
public class Hello {

    @Autowired // utiliza para fazer a injeção de dependencias no projeto 
    private HelloService helloService;

    @GetMapping("/") // resonde a notação do tipo get
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/somar")
    public Integer somar(@RequestParam("v1") Integer valor1, @RequestParam("v2") Integer valor2) {
        return valor1 + valor2;
    }

    @PostMapping("/somar")
    public Calculadora somar(@RequestBody Calculadora calculadora) {
        
        return helloService.somar(calculadora);

    }
}
