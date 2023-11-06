package br.com.lanchonete.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;

import br.com.lanchonete.dto.FornecedorDto;
import br.com.lanchonete.service.FornecedorService;
import br.com.lanchonete.utils.BuscaCep;
import br.com.lanchonete.utils.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "fornecedorbean")
@RequestScoped
public class FornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private FornecedorDto[] listaFornecedores;

	@Inject
	private FornecedorDto fornecedor;

	@Inject
	private FornecedorService fornecedorService;

//cadastar e atualizar fornecedores do banco de dados
	public String cadastrarFornecedor() {
		try {
			if (fornecedor.getId() == null) {
				fornecedorService.cadastrarFornecedorNoSpring(fornecedor);
				Message.info("Cadastro", "Fornecedor  cadastrado com Sucesso!!!");
			} else {
				fornecedorService.atualizarrFornecedorNoSpring(fornecedor);
				Message.info("Atualização", "Fornecedor  atualizado com Sucesso!!!");
			}
			listarFornecedores();
			limpar();
		} catch (Exception e) {
		}
		return null;
	}

	// lista fornecedores do banco de dados
	@PostConstruct
	public void listarFornecedores() {
		String json = fornecedorService.listaFornecedor();
		Gson gson = new Gson();
		listaFornecedores = gson.fromJson(json, FornecedorDto[].class);
	}

	// excluir fornecedores do banco de dados
	public void excluirFornecedor(FornecedorDto fornecedor) {
		try {
			fornecedorService.excluirFornecedorNoSpring(fornecedor.getId());
			Message.info("Excluir", "Fornecedor excluido com Sucesso!!!");
			listarFornecedores();
			limpar();
		} catch (Exception e) {
		}
	}

	// busca Cep
	public void encontraCEP() {
		BuscaCep buscaCep = new BuscaCep(fornecedor.getEndereco().getCep());

		if (buscaCep.getResultado() == 1) {
			fornecedor.getEndereco().setRua(buscaCep.getTipoLogradouro() + " " + buscaCep.getLogradouro());
			fornecedor.getEndereco().setEstado(buscaCep.getEstado());
			fornecedor.getEndereco().setCidade(buscaCep.getCidade());
			fornecedor.getEndereco().setBairro(buscaCep.getBairro());
		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "CEP não foi encontrado", "CEP não foi encontrado"));
		}
	}

	// limpar dados da tela
	public void limpar() {
		fornecedor = new FornecedorDto();
	}

}