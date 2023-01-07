package br.ifba.sistema_chamados.form;

import br.ifba.sistema_chamados.model.Assunto;
import br.ifba.sistema_chamados.model.Status;

/**
 * 
 * @authors Arthur Martins, Lorena Carvalho e Luis Garrido
 *
 */


/**
 * Classe de arquitetura DTO-FORM para os chamados. Aqui as informações vão do cliente para a API.
 * Só possui getters
 */
public class ChamadoForm {
    
    private Long clienteId;
    
    private Assunto assunto;
    private Status status;

	public Long getClienteId() {
		return clienteId;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public Status getStatus() {
		return status;
	}

	


    
    
}
