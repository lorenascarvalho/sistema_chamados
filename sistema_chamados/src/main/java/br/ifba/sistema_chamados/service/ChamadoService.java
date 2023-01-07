package br.ifba.sistema_chamados.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.ifba.sistema_chamados.dto.ChamadoDto;
import br.ifba.sistema_chamados.form.ChamadoForm;
import br.ifba.sistema_chamados.model.Chamado;
import br.ifba.sistema_chamados.model.Cliente;
import br.ifba.sistema_chamados.repository.ChamadoRepository;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe provedora de serviço dos endpoints da entidade de chamados
 * Aqui estão os metodos concretos de listar, atualizar, cadastrar e deletar chamados que são acionados pelo controller 
 */
@Service
public class ChamadoService {
	
	private final ChamadoRepository chamadoRepository;
    private final ClienteService clienteService;
    
    //construtor base
    public ChamadoService(ChamadoRepository chamadoRepository, ClienteService clienteService) {
		super();
		this.chamadoRepository = chamadoRepository;
		this.clienteService = clienteService;
	}

    //Metodo para verificar se o id do chamado existe ou não
    public Chamado findByIdOrThrowNotFoundRequestException(Long id) {
        return chamadoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado não encontrado"));
    }

    //metodo que salva um chamado
    public ChamadoDto save(ChamadoForm chamadoForm) {
        Cliente cliente = clienteService.findByIdOrThrowNotFoundRequestException(chamadoForm.getClienteId());
        Chamado chamado = new Chamado(cliente.getId(), cliente, chamadoForm.getAssunto(), chamadoForm.getStatus(), LocalDate.now());
       
        chamado = chamadoRepository.save(chamado);
        
       return new ChamadoDto(chamado);
    }

    //metodo que encontra um chamado pelo seu Id
    public ChamadoDto findById(Long id) {
        return new ChamadoDto(findByIdOrThrowNotFoundRequestException(id));
    }

    //metodo que lista os chamados pelo clienteid
    public List<ChamadoDto> listAll(Long clienteId) {
        return ChamadoDto.converte(chamadoRepository.findByClienteId(clienteId));
    }

    /* Não está sendo usado, pois é necessária correção da query em repository
    public List<ChamadoDto> listAll(String usuarioId) {
        return ChamadoDto.converte(chamadoRepository.findByUsuarioId(usuarioId));
    }
    */

    
    //metodo que faz um update no chamado
    public void update(Long id, ChamadoForm chamadoForm) {
        Chamado savedChamado = findByIdOrThrowNotFoundRequestException(id);
        Chamado chamado = new Chamado(savedChamado.getId() ,savedChamado.getCliente(), chamadoForm.getAssunto(), chamadoForm.getStatus(), LocalDate.now());
        
        chamadoRepository.save(chamado);
    }

    
    //metodo que deleta um chamado
    public void delete(Long id) {
        chamadoRepository.delete(findByIdOrThrowNotFoundRequestException(id));
    }

    //metodo que lista todos os chamados
	public List<ChamadoDto> listAll() {
        return ChamadoDto.converte(chamadoRepository.findAll());
	}

}