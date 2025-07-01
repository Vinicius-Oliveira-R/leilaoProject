package com.git.vinicius.backend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.git.vinicius.backend.exception.NaoEncontradoException;
import com.git.vinicius.backend.model.Pessoa;
import com.git.vinicius.backend.repository.pessoaRepository;

@Service
public class PessoaService {
    @Autowired
    private pessoaRepository pessoaRepository;
    private MessageSource messageSource;

    @Autowired
	private EmailService emailService;

	public Pessoa inserir(Pessoa pessoa) {
		Pessoa pessoaCadastrada = pessoaRepository.save(pessoa);
		for (int i = 0; i < 100; i++) {
			emailService.enviarEmailSimples(pessoaCadastrada.getEmail(), "Opa bão?", "Eu quero fazer um mago de 1.50 que lança uma única magia de fogo" + i);
		}

		enviarEmailSuceeso(pessoaCadastrada);

		return pessoaCadastrada;
	}

	private void enviarEmailSuceeso(Pessoa pessoa) {
		Context context = new Context();
		context.setVariable("nome", pessoa.getNome());
		emailService.emailTemplate(pessoa.getEmail(), "Cadastro Sucesso", context, "cadastroSucesso");
	}

    public Pessoa alterar(Pessoa pessoa){
        Pessoa pessoaBanco = buscarPorId(pessoa.getId());
        pessoaBanco.setNome(pessoa.getNome());
        return pessoaRepository.save(pessoaBanco);
    }
    public void excluir(Long id){
        Pessoa pessoaBanco = buscarPorId(id);
        pessoaRepository.delete(pessoaBanco);
    }
    public Pessoa buscarPorId(Long id){
        return pessoaRepository.findById(id).orElseThrow(()->new NaoEncontradoException(messageSource.getMessage("pessoa.not-found",
        new Object[]{id}, LocaleContextHolder.getLocale() )));
    }
    public Page<Pessoa> buscarTodos(Pageable pageable){
        return pessoaRepository.findAll(pageable);
    }
}
