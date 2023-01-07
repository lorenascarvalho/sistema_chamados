package br.ifba.sistema_chamados.controller;

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
import org.springframework.web.bind.annotation.RestController;

import br.ifba.sistema_chamados.dto.ChamadoDto;
import br.ifba.sistema_chamados.form.ChamadoForm;
import br.ifba.sistema_chamados.service.ChamadoService;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe controladora dos endpoints da entidade de chamados
 * Aqui estão os metodos de listar, atualizar, cadastrar e deletar chamados que são acionados através de uma instancia
 * unica de ChamadoService. 
 * Anotação CORS (cross origin) é necessária para que todos os recursos da API sejam disponibilizados em um chamada.
 */
@RestController
@RequestMapping("/chamados")
@CrossOrigin(origins = "*")
public class ChamadoController {

	final ChamadoService chamadoService;

	//Construtor base
    public ChamadoController(ChamadoService chamadoService) {
		super();
		this.chamadoService = chamadoService;
	}

    //metodo para listar todos os chamados
    @GetMapping
    public ResponseEntity<List<ChamadoDto>> listAll() {
        List<ChamadoDto> usuarios = chamadoService.listAll();
        return new ResponseEntity<List<ChamadoDto>>(usuarios, HttpStatus.OK);
    }
    
    //metodo para criar um chamado
    @PostMapping
    public ResponseEntity<ChamadoDto> save(@RequestBody ChamadoForm chamadoform) {
    	ChamadoDto chamadoOut = chamadoService.save(chamadoform);
        return new ResponseEntity<ChamadoDto>(chamadoOut, HttpStatus.CREATED);
    }
    
    //metodo para dar um update em um chamado
    @PutMapping("/{id}")
    @org.springframework.transaction.annotation.Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ChamadoForm chamadoform) {
        chamadoService.update(id, chamadoform);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    //metodo que retorna os chamados de um cliente especifico
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ChamadoDto>> listAll(@PathVariable Long clienteId) {
        List<ChamadoDto> chamados = chamadoService.listAll(clienteId);
        return new ResponseEntity<List<ChamadoDto>>(chamados, HttpStatus.OK);
    }

    
    //metodo que retorna um chamado pelo seu id
    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDto> findById(@Valid @PathVariable Long id) {
    	ChamadoDto usuario = chamadoService.findById(id);
        return new ResponseEntity<ChamadoDto>(usuario, HttpStatus.OK);
    }
    
    /* ERRO NA QUERY DO REPOSITORY, NECESSÁRIA CORREÇÃO
    //metodo que retorna todos os chamados por usuario
    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<List<ChamadoDto>> listAll(@PathVariable String usuarioId) {
        List<ChamadoDto> chamados = chamadoService.listAll(usuarioId);
        return new ResponseEntity<List<ChamadoDto>>(chamados, HttpStatus.OK);
    }
    */


    //metodo que deleta um chamado pelo seu id
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        chamadoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}