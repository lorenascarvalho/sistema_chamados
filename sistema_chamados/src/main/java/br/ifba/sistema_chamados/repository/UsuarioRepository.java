package br.ifba.sistema_chamados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifba.sistema_chamados.model.Usuario;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe usada para ser o repositório da entidade de Usuarios, servirá para usar alguns métodos de acesso ao banco.
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
