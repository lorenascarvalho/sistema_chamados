package br.ifba.sistema_chamados.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.ifba.sistema_chamados.model.Cliente;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe de arquitetura DTO para os clientes. Aqui as informações vão da API para o cliente.
 * Só possuem construtores, getters e setters
 */
public class ClienteDto {
	
	
	private Long id;
	private UsuarioDto usuario;
	private String nome;
	private String cnpj;
	private String endereco;
	private LocalDate dataCadastro;

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.usuario = new UsuarioDto(cliente.getUsuario());
		this.nome = cliente.getNome();
		this.cnpj = cliente.getCnpj();
		this.endereco = cliente.getEndereco();
		this.dataCadastro = cliente.getDataCadastro();
	}
	
	public ClienteDto() {
		super();
	}


	public static List<ClienteDto> converte(List<Cliente> lista) {
		return lista.stream().map(ClienteDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
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
