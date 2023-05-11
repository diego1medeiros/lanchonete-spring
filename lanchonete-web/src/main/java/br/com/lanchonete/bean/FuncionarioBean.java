package br.com.lanchonete.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;

import br.com.lanchonete.dto.FuncionarioDto;
import br.com.lanchonete.enumeradores.FuncaoFuncionario;
import br.com.lanchonete.service.FuncionarioService;
import br.com.lanchonete.utils.BuscaCep;
import br.com.lanchonete.utils.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named(value = "funcionariobean")
@RequestScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioDto funcionario;

	@Inject
	private FuncionarioService funcionarioService;

	private FuncionarioDto[] listaFuncionarios;

	// cadastar e atualizar Funcionarios do banco de dados
	public String cadastrarFuncionario() {
		try {
			if (funcionario.getId() == null) {
				funcionarioService.cadastrarFuncionarioNoSpring(funcionario);
				Message.info("Cadastro", "Funcionario cadastrado com Sucesso!!!");
			} else {
				funcionarioService.atualizarrFuncionarioNoSpring(funcionario);
				Message.info("Atualização", "Funcionario atualizado com Sucesso!!!");
			}
			listarFuncionarios();
			limpar();
		} catch (Exception e) {
		}
		return null;
	}

	// lista Funcionarios do banco de dados
	@PostConstruct
	public void listarFuncionarios() {
		String json = funcionarioService.listarFuncionario();
		Gson gson = new Gson();
		listaFuncionarios = gson.fromJson(json, FuncionarioDto[].class);
		
	}

	// excluir Funcionarios do banco de dados
	public void excluirFuncionario(FuncionarioDto funcionario) {
		funcionarioService.excluirFuncionarioNoSpring(funcionario.getId());
		listarFuncionarios();
		limpar();
		Message.info("Excluir", "Funcionario  excluido com Sucesso!!!");

	}

	// logar no sistema

	public String isLoginSenhaValida(String login, String senha) {
		try {
			List<FuncionarioDto> listaFuncionario = Arrays.asList(listaFuncionarios);
			for (FuncionarioDto funcionario : listaFuncionario) {
				if (login.equals(funcionario.getLogin()) && senha.equals(funcionario.getSenha())) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.getExternalContext().getSessionMap().put("funcionarioLogado", funcionario);
					Message.info("Login e Senha valida!!! Funcionario " + funcionario.getNome() + " Logado", null);
					return "menuPrincipal?faces-redirect-true";
				}
			}
		} catch (Exception e) {
		}
		Message.erro("Login e Senha errada!!!", "");
		return null;
	}

	// deslogar do sistema
	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("funcionarioLogado");
		return "login?faces-redirect-true";
	}

	// busca Cep
	public void encontraCEP() {
		BuscaCep buscaCep = new BuscaCep(funcionario.getEndereco().getCep());
		if (buscaCep.getResultado() == 1) {
			funcionario.getEndereco().setRua(buscaCep.getTipoLogradouro() + " " + buscaCep.getLogradouro());
			funcionario.getEndereco().setEstado(buscaCep.getEstado());
			funcionario.getEndereco().setCidade(buscaCep.getCidade());
			funcionario.getEndereco().setBairro(buscaCep.getBairro());
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "CEP não foi encontrado", "CEP não foi encontrado"));
		}
	}

	// lista dos funicioanrio para um selectitens

	public List<SelectItem> getListaNomeFuncioanrio() {
		List<SelectItem> lista = new ArrayList<>();
		String json = funcionarioService.listarFuncionario();
		Gson gson = new Gson();
		FuncionarioDto[] listaFuncionarios = gson.fromJson(json, FuncionarioDto[].class);
		for (FuncionarioDto funcionario : listaFuncionarios) {
			lista.add(new SelectItem(funcionario.getId(), funcionario.getNome()));
		}
		return lista;
	}

	// limpar dados da tela
	public void limpar() {
		funcionario = new FuncionarioDto();
	}

	// enum das funções dos funcionarios
	public List<String> getFuncaoFuncionario() {
		return Arrays.asList(FuncaoFuncionario.getDescricaoComboBox());
	}
}
