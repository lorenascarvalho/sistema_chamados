package br.ifba.sistema_chamados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ifba.sistema_chamados.model.Chamado;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */

/**
 * Classe usada para ser o repositório da entidade de chamadas, servirá para usar alguns métodos de acesso ao banco.
 *
 *Foi acrescentado os métodos de listar chamados pelo clientId e listar chamados pelo usuarioId, neles utilizamos 
 *a anotação JPA de query, onde ordenamos que aquele método faça determinada consulta a entidade de chamados.
 */
@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long>{
    
    @Query(value = "SELECT * FROM CHAMADOS WHERE CLIENTE_ID = ?", nativeQuery = true)
    List<Chamado> findByClienteId(@Param("clientId") Long clientId);

    @Query(value = "SELECT CHAMADOS.ID, CHAMADOS.ASSUNTO, CHAMADOS.STATUS, CHAMADOS.CLIENTE_ID FROM CHAMADOS INNER JOIN CLIENTES ON CHAMADOS.CLIENTE_ID = CLIENTES.ID WHERE CLIENTES.uid = ?", nativeQuery = true)
    List<Chamado> findByUsuarioId(@Param("usuarioId") String usuarioId);
}