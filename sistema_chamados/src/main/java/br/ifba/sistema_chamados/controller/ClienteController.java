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

import br.ifba.sistema_chamados.dto.ClienteDto;
import br.ifba.sistema_chamados.form.ClienteForm;
import br.ifba.sistema_chamados.service.ClienteService;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe controladora dos endpoints da entidade de clientes
 * Aqui estão os metodos de listar, atualizar, cadastrar e deletar chamados que são acionados através de uma instancia
 * unica de ClienteService. 
 * Anotação CORS (cross origin) é necessária para que todos os recursos da API sejam disponibilizados em um chamada.
 */
@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

	final ClienteService clienteService;
	
	//Construtor base
    public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
    
    //Lista todos os clientes
    @GetMapping
    public ResponseEntity<List<ClienteDto>> listAll() {
        List<ClienteDto> clientes = clienteService.listAll();
        return new ResponseEntity<List<ClienteDto>>(clientes, HttpStatus.OK);
    }
    
    //Lista clientes pelo seu id
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> findById(@Valid @PathVariable Long id) {
    	ClienteDto usuario = clienteService.findById(id);
        return new ResponseEntity<ClienteDto>(usuario, HttpStatus.OK);
    }

    //Cria um novo cliente
    @PostMapping
    public ResponseEntity<ClienteDto> save(@Valid @RequestBody ClienteForm clienteForm) {
    	ClienteDto clienteDto = clienteService.save(clienteForm);
        return new ResponseEntity<ClienteDto>(clienteDto, HttpStatus.CREATED);

    }
   
    //Modifica um cliente 
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClienteForm clienteForm) {
       clienteService.update(id, clienteForm);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Deleta um cliente
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}