package br.ifba.sistema_chamados.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe modelo para a entidade de usuários, será usada como modelo para o banco de dados.
 *
 */
@Entity(name = "usuarios")
public class Usuario {

		@Id
	    @NotBlank
	    private String id;

	    @NotBlank
	    private String nome;

	    @NotBlank @Email
	    private String email;

	    public Usuario() {
	    	
	    }
	    
	 	public Usuario(@NotBlank String id, @NotBlank String nome, @NotBlank @Email String email) {
			this.id = id;
			this.nome = nome;
			this.email = email;
		 	}
	 	
	 	public Usuario(@NotBlank String nome, @NotBlank @Email String email) {
			this.nome = nome;
			this.email = email;
		}

		public String getUid() {
			return id;
		}

		public void setUid(String uid) {
			this.id = uid;
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
