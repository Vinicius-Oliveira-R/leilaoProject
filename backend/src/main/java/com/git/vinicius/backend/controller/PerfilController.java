package com.git.vinicius.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.git.vinicius.backend.model.Perfil;
import com.git.vinicius.backend.repository.perfilRepository;
import com.git.vinicius.backend.service.EmailService;
import com.git.vinicius.backend.service.PerfilService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

import java.util.List;

@RestController
@RequestMapping("/Perfil")
    public class PerfilController {

    @Autowired
    private PerfilService PerfilService;
    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<Page<Perfil>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(PerfilService.buscarTodos(pageable));
    }

    @PostMapping
    public ResponseEntity<Perfil> inserir(@Valid @RequestBody Perfil Perfil) {
        return ResponseEntity.ok(PerfilService.inserir(Perfil));
    }

    @PutMapping
    public ResponseEntity<Perfil> alterar(@Valid @RequestBody Perfil Perfil) {
        return ResponseEntity.ok(PerfilService.alterar(Perfil));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathParam("id") Long id) {
        PerfilService.excluir(id);
        return ResponseEntity.ok("Excluindo");
    }
}

