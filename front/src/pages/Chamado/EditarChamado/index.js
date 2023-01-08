import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';
import api from '../../../services/api';
import './modal.css';

export default function EditarChamado({ setOpenModal, chamadoId, user }) {

  const [clientes, setClientes]               = useState([]);
  const [loadingClientes, setLoadingClientes] = useState(true);

  const [idCliente, setIdCliente]     = useState();
  const [assunto, setAssunto]         = useState();
  const [status, setStatus]           = useState();


  useEffect(() => {
    loadChamado();
    loadClientes();
  }, []);

  async function loadChamado() {
    api
      .get(`/chamados/${chamadoId}`)
      .then(response => {
        setIdCliente(response.data.cliente.id);
        setAssunto(response.data.assunto);
        setStatus(response.data.status);     
      });
  }
  async function loadClientes() {
    api
      .get('/clientes')
      .then(response => {
        setClientes(response.data);
        setLoadingClientes(false);
      });
  }

  async function handleSubmit(e) {
    e.preventDefault();
    updateChamado();
    setOpenModal(false);
  }

  async function updateChamado () {
    let response;

    try {
      response = api.put(`/chamados/${chamadoId}`, {
        clienteId: chamadoId,
        assunto: assunto,
        status: status,
      });
      
    } catch (error) {
      toast('Erro ao atualizar o chamado!');
    }

    if(response.data) {
      toast('Chamado atualizado com sucesso!');
    }
  }

  return (
    <div className="modalBackground-edit-chamado">
      <div className="modalContainer-edit-chamado">
        <div className="titleCloseBtn-edit-chamado">
          <button onClick={ () => setOpenModal(false) }>
            X
          </button>
        </div>
        <div className="title-edit-chamado">
          <h1>Editar Chamado</h1>
        </div>
        <div className="body-edit-chamado">
          <label>Cliente</label>
          {loadingClientes ?
              <input type="text" value="Carregando..." />
              : <select value={idCliente} onChange={(e) => setIdCliente(e.target.value)}>
                  {clientes.map(item => {
                      return (<option key={item.id} value={item.id}>{item.nome}</option>);
                  })}
              </select>
          }

          <label>Assunto</label>
          <select value={assunto} onChange={(e) => setAssunto(e.target.value)}>
              <option value="SUPORTE">Suporte</option>
              <option value="FINANCEIRO">Financeiro</option>
              <option value="RECLAMACAO">Reclamacao</option>
              <option value="DESENVOLVIMENTO">Desenvolvimento</option>
          </select>

          <label>Status</label>
          <div className="status-edit-chamado">
              <input
                  type="radio"
                  name="radio"
                  value="ABERTO"
                  onChange={(e) => setStatus(e.target.value)}
                  checked={status === "ABERTO"} />
              <span>Em Aberto</span>

              <input
                  type="radio"
                  name="radio"
                  value="PENDENTE"
                  onChange={(e) => setStatus(e.target.value)}
                  checked={status === "PENDENTE"} />
              <span>Pendente</span>

              <input
                  type="radio"
                  name="radio"
                  value="DESCONHECIDO"
                  onChange={(e) => setStatus(e.target.value)}
                  checked={status === "DESCONHECIDO"} />
              <span>Desconhecido</span>
          </div>

          <input
                  type="radio"
                  name="radio"
                  value="SUSPENSO"
                  onChange={(e) => setStatus(e.target.value)}
                  checked={status === "SUSPENSO"} />
              <span>Suspenso</span>

          <input
                  type="radio"
                  name="radio"
                  value="FECHADO"
                  onChange={(e) => setStatus(e.target.value)}
                  checked={status === "FECHADO"} />
              <span>Fechado</span>
          
    
        </div>
        <div className="footer-edit-chamado">
          <button onClick={ () => setOpenModal(false) } id="cancelBtn">
            Cancelar
          </button>
          <button onClick={ handleSubmit }>Confirmar</button>
        </div>
      </div>
    </div>
  );
}
