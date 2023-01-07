package br.ifba.sistema_chamados.model;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe modelo para a entidade de chamados, será usada como modelo para o banco de dados.
 * Possui uma relação de muitos para um com a entidade cliente.
 *
 */
@Entity(name = "chamados")
public class Chamado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	private Assunto assunto;

	@Enumerated(EnumType.STRING)
	private Status status;

	private LocalDate dataDeCadastro;
	
	public Chamado() {
		
	}
	public Chamado(Long id, Cliente cliente, Assunto assunto, Status status, LocalDate dataCadastro) {
		this.id = id;
		this.cliente = cliente;
		this.assunto = assunto;
		this.status = status;
		this.dataDeCadastro = dataCadastro;
	}
	
	public Chamado(Cliente cliente, Assunto assunto, Status status, LocalDate dataDeCadastro) {
		this.cliente = cliente;
		this.assunto = assunto;
		this.status = status;
		this.dataDeCadastro = dataDeCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
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

	public LocalDate getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(LocalDate dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

}
