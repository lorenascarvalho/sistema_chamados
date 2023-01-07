package br.ifba.sistema_chamados.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.ifba.sistema_chamados.model.Usuario;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe de arquitetura DTO para os usuarios. Aqui as informações vão da API para o client.
 * Só possuem construtores, getters e setters
 */
public class UsuarioDto {

	private String uid;
	private String nome;
	private String email;

	public UsuarioDto(Usuario usuario) {
		this.uid = usuario.getUid();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

	public UsuarioDto() {
		super();
	}

	public static List<UsuarioDto> converte(List<Usuario> lista) {
		return lista.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
