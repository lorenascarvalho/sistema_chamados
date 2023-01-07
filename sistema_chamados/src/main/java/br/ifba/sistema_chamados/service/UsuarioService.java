package br.ifba.sistema_chamados.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.ifba.sistema_chamados.dto.UsuarioDto;
import br.ifba.sistema_chamados.form.UsuarioForm;
import br.ifba.sistema_chamados.model.Usuario;
import br.ifba.sistema_chamados.repository.UsuarioRepository;


/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe provedora de serviço dos endpoints da entidade de usuarios
 * Aqui estão os metodos concretos de listar, atualizar, cadastrar e deletar chamados que são acionados pelo controller 
 */
@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	//Construtor base
    public UsuarioService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

    //Metodo para verificar se o usuario existe ou não pelo ID
    public Usuario findByIdOrThrowNotFoundRequestException(String id) {
    	
        return usuarioRepository.findById(id)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
    }
        
    //Metodo para salvar um usuario
    public UsuarioDto save(UsuarioForm usuarioForm) {
        Usuario usuario = new Usuario(usuarioForm.getUid(), usuarioForm.getNome(), usuarioForm.getEmail());
        usuarioRepository.save(usuario);

        return new UsuarioDto(usuario);
    }

    //Metodo para encontrar um usuario pelo seu id
    public UsuarioDto findById(String id) {
        return new UsuarioDto(findByIdOrThrowNotFoundRequestException(id));
    }

    
    //Metodo para listar todos os usuarios
    public List<UsuarioDto> listAll() {
        return UsuarioDto.converte(usuarioRepository.findAll());
    }

    
    //Metodo para atualizar um usuario
    public void update(String id, UsuarioForm usuarioForm) {
        Usuario savedUsuario = findByIdOrThrowNotFoundRequestException(id);
        Usuario usuario = new Usuario(savedUsuario.getUid(), usuarioForm.getNome(), savedUsuario.getEmail());

        usuarioRepository.save(usuario);
    }
    
    public void update(String id, String nome) throws IOException {
        Usuario savedUsuario = findByIdOrThrowNotFoundRequestException(id);
        savedUsuario.setNome(nome);
    }

    //Metodo para deletar um usuario
    public void delete(String id) {
        usuarioRepository.delete(findByIdOrThrowNotFoundRequestException(id));
    }
}