package br.com.lanchonete.dto.funcionariodto;

import br.com.lanchonete.entity.Endereco;
import br.com.lanchonete.entity.Funcionario;

public record DadosListagemFuncionario(Long id, String nome, String cpf, String telefone, String email, String funcao,
		Double salario, String login, String senha, Endereco endereco) {

	public DadosListagemFuncionario(Funcionario funcionario) {
		this(funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getTelefone(),
				funcionario.getEmail(), funcionario.getFuncao(), funcionario.getSalario(), funcionario.getLogin(),
				funcionario.getSenha(), funcionario.getEndereco());
	}
}
