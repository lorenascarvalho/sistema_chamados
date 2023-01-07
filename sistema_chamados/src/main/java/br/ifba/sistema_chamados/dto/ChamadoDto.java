package br.ifba.sistema_chamados.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.ifba.sistema_chamados.model.Assunto;
import br.ifba.sistema_chamados.model.Chamado;
import br.ifba.sistema_chamados.model.Status;


/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe de arquitetura DTO para os chamados. Aqui as informações vão da API para o client.
 * Só possuem construtores, getters e setters
 */

public class ChamadoDto {

	private Long id;
	private ClienteDto cliente;
	private Assunto assunto;
	private Status status;
	private LocalDate dataCadastro;

	public ChamadoDto(Chamado chamado) {
		this.id = chamado.getId();
		this.cliente = new ClienteDto(chamado.getCliente());
		this.assunto = chamado.getAssunto();
		this.status = chamado.getStatus();
		this.dataCadastro = chamado.getDataDeCadastro();
	}

	public ChamadoDto() {
		super();
	}

	public static List<ChamadoDto> converte(List<Chamado> lista) {
		return lista.stream().map(ChamadoDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}