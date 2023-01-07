package br.ifba.sistema_chamados.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ifba.sistema_chamados.dto.UsuarioDto;
import br.ifba.sistema_chamados.form.UsuarioForm;
import br.ifba.sistema_chamados.service.UsuarioService;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe controladora dos endpoints da entidade de usuarios
 * Aqui estão os metodos de listar, atualizar, cadastrar e deletar chamados que são acionados através de uma instancia
 * unica de UsuarioService. 
 * Anotação CORS (cross origin) é necessária para que todos os recursos da API sejam disponibilizados em um chamada.
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {


	final UsuarioService usuarioService;
	
	//Construtor Base
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	//Lista todos os usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listAll() {
        List<UsuarioDto> usuarios = usuarioService.listAll();
        return new ResponseEntity<List<UsuarioDto>>(usuarios, HttpStatus.OK);
    }

    //Metodo para listar usuario pelo id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@Valid @PathVariable String id) {
    	UsuarioDto usuario = usuarioService.findById(id);
        return new ResponseEntity<UsuarioDto>(usuario, HttpStatus.OK);
    }

    //Cria um novo usuario
    @PostMapping
    public ResponseEntity<UsuarioDto> save(@Valid @RequestBody UsuarioForm usuarioForm) {
    	UsuarioDto usuarioDto = usuarioService.save(usuarioForm);
        return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.CREATED);

    }
    
    //Atualiza um usuario
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable String id,  @RequestParam("nome") String nome) throws IOException {
       usuarioService.update(id, nome);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Deleta um usuario
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable String id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}