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
import com.git.vinicius.backend.model.Perfil;
import com.git.vinicius.backend.repository.perfilRepository;

@Service
public class PerfilService {
    @Autowired
    private perfilRepository PerfilRepository;
    private MessageSource messageSource;

    @Autowired
	private EmailService emailService;

	public Perfil inserir(Perfil Perfil) {
		Perfil perfilCadastrada = perfilRepository.save(Perfil);
		for (int i = 0; i < 100; i++) {
			emailService.enviarEmailSimples(perfilCadastrada.getEmail(), "Opa bão?", "Eu quero fazer um mago de 1.50 que lança uma única magia de fogo" + i);
		}

		enviarEmailSuceeso(perfilCadastrada);

		return perfilCadastrada;
	}

	private void enviarEmailSuceeso(Perfil Perfil) {
		Context context = new Context();
		context.setVariable("nome", Perfil.getNome());
		emailService.emailTemplate(Perfil.getEmail(), "Cadastro Sucesso", context, "cadastroSucesso");
	}

    public Perfil alterar(Perfil Perfil){
        Perfil PerfilBanco = buscarPorId(Perfil.getId());
        PerfilBanco.setNome(Perfil.getNome());
        return perfilRepository.save(PerfilBanco);
    }
    public void excluir(Long id){
        Perfil PerfilBanco = buscarPorId(id);
        PerfilRepository.delete(PerfilBanco);
    }
    public Perfil buscarPorId(Long id){
        return perfilRepository.findById(id).orElseThrow(()->new NaoEncontradoException(messageSource.getMessage("Perfil.not-found",
        new Object[]{id}, LocaleContextHolder.getLocale() )));
    }
    public Page<Perfil> buscarTodos(Pageable pageable){
        return perfilRepository.findAll(pageable);
    }
}
