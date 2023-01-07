package br.ifba.sistema_chamados.form;

import jakarta.validation.constraints.NotBlank;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe de arquitetura DTO-FORM para os clientes. Aqui as informações vão do cliente para a API.
 * Só possui getters
 */
public class ClienteForm {
	
	@NotBlank
	private String usuarioId;

	@NotBlank
	private String nome;

	private String cnpj;

	private String endereco;

	public String getUsuarioId() {
		return usuarioId;
	}

	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

}