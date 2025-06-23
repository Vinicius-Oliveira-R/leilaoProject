package com.git.vinicius.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.git.vinicius.backend.model.Pessoa;

public interface pessoaRepository extends JpaRepository<Pessoa,Long> {

    
}