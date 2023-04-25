package br.com.lanchonete.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;

import br.com.lanchonete.dto.ClienteDto;
import br.com.lanchonete.service.ClienteService;
import br.com.lanchonete.utils.BuscaCep;
import br.com.lanchonete.utils.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "clientebean")
@RequestScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ClienteDto[] listaClientes;

	@Inject
	private ClienteDto cliente;

	@Inject
	private ClienteService clienteService;

	// cadastrar e atualizar Cliente no banco de dados
	public String cadastrar() {
		if (cliente.getId() == null) {
			clienteService.cadastrarClienteNoSpring(cliente);
			Message.info("Cadastro", "Cliente cadastrado com Sucesso!!!");
		} else {
			clienteService.atualizarrClienteNoSpring(cliente);
			Message.info("Atualizar", "Cliente atualizado com Sucesso!!!");
		}
		listarClientes();
		limpar();
		return null;
	}

	// lista cliente vindo do banco de dados
	@PostConstruct
	public void listarClientes() {
		String json = clienteService.listaCliente();
		Gson gson = new Gson();
		listaClientes = gson.fromJson(json, ClienteDto[].class);
	}

	// excluir cliente no banco de dados
	public void excluirCliente(ClienteDto clienteDto) {
		clienteService.excluirClienteNoSpring(clienteDto.getId());
		listarClientes();
		limpar();
		Message.info("Excluir", "Cliente Excluido com sucesso!!!");
	}

	// busca Cep do cliente
	public void encontraCEP() {
		BuscaCep buscaCep = new BuscaCep(cliente.getEndereco().getCep());
		if (buscaCep.getResultado() == 1) {
			cliente.getEndereco().setRua(buscaCep.getTipoLogradouro() + " " + buscaCep.getLogradouro());
			cliente.getEndereco().setEstado(buscaCep.getEstado());
			cliente.getEndereco().setCidade(buscaCep.getCidade());
			cliente.getEndereco().setBairro(buscaCep.getBairro());
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "CEP não foi encontrado", "CEP não foi encontrado"));
		}
	}
	// limpar dados da tela
	public void limpar() {
		cliente = new ClienteDto();
	}
}
