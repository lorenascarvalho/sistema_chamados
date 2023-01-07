package br.ifba.sistema_chamados.model;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe usada como modelo para entidade de clientes, será usada como modelo para o banco de dados.
 * Possui uma relação de muitos para um com a entidade usuario.
 */
@Entity(name = "clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Usuario usuario;

	@NotBlank
	private String nome;

	private String cnpj;

	private LocalDate dataCadastro;
	
	private String endereco;
	
	public Cliente() {
		
	}

	public Cliente(Long id, Usuario usuario, @NotBlank String nome, String cnpj, LocalDate dataCadastro,
			String endereco) {
		this.id = id;
		this.usuario = usuario;
		this.nome = nome;
		this.cnpj = cnpj;
		this.dataCadastro = dataCadastro;
		this.endereco = endereco;
	}

	public Cliente(Long id, Usuario usuario, String nome, String cnpj, String endereco, LocalDate dataCadastro) {
		this.id = id;
		this.usuario = usuario;
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.dataCadastro = dataCadastro;
	}

	public Cliente(Usuario usuario, @NotBlank String nome, String cnpj, String endereco, LocalDate dataCadastro) {
		this.usuario = usuario;
		this.nome = nome;
		this.cnpj = cnpj;
		this.dataCadastro = dataCadastro;
		this.endereco = endereco;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}
