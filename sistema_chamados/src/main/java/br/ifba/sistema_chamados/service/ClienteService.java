package br.ifba.sistema_chamados.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import br.ifba.sistema_chamados.dto.ClienteDto;
import br.ifba.sistema_chamados.form.ClienteForm;
import br.ifba.sistema_chamados.model.Cliente;
import br.ifba.sistema_chamados.model.Usuario;
import br.ifba.sistema_chamados.repository.ClienteRepository;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe provedora de serviço dos endpoints da entidade de clientes
 * Aqui estão os metodos concretos de listar, atualizar, cadastrar e deletar chamados que são acionados pelo controller 
 */
@Service
public class ClienteService {
	
	private final ClienteRepository clienteRepository;
    private final UsuarioService usuarioService;

    //construtor base
    public ClienteService(ClienteRepository clienteRepository, UsuarioService usuarioService) {
		super();
		this.clienteRepository = clienteRepository;
		this.usuarioService = usuarioService;
	}

    //Metodo para verificar se um cliente existe ou não pelo seu ID
    public Cliente findByIdOrThrowNotFoundRequestException(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
    }
    
    //Metodo para salvar um client
    public ClienteDto save(ClienteForm clienteForm) {
        Usuario usuario = usuarioService.findByIdOrThrowNotFoundRequestException(clienteForm.getUsuarioId());
        Cliente cliente = new Cliente(usuario, clienteForm.getNome(), clienteForm.getCnpj(), clienteForm.getEndereco(), LocalDate.now());

        cliente = clienteRepository.save(cliente);

        return new ClienteDto(cliente);
    } 

    //Metodo para achar um cliente pelo id
    public ClienteDto findById(Long id) {
        return new ClienteDto(findByIdOrThrowNotFoundRequestException(id));
    }

    //Metodo para listar todos os clientes
    public List<ClienteDto> listAll() {
        return ClienteDto.converte(clienteRepository.findAll());
    }

    
    //Metodo para dar update em um cliente
    public void update(Long id, ClienteForm clienteForm) {
        Cliente savedCliente = findByIdOrThrowNotFoundRequestException(id);
        Cliente cliente = new Cliente(savedCliente.getId(), savedCliente.getUsuario(), clienteForm.getNome(), clienteForm.getCnpj(), clienteForm.getEndereco(), savedCliente.getDataCadastro());
    
        clienteRepository.save(cliente);
    }

    
    //Metodo para deletar um cliente
    public void delete(Long id) {
        clienteRepository.delete(findByIdOrThrowNotFoundRequestException(id));
    }

}