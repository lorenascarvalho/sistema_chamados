package br.ifba.sistema_chamados.form;

import jakarta.validation.constraints.NotBlank;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe de arquitetura DTO-FORM para os usuarios. Aqui as informações vão do cliente para a API.
 * Só possui getters
 */
public class UsuarioForm {

	@NotBlank
	private String uid;

	@NotBlank
	private String nome;

	private String email;


	public String getUid() {
		return uid;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

}