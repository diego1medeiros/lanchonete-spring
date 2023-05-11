package br.com.lanchonete.dto.funcionariodto;

import br.com.lanchonete.dto.Enderecodto.DadosEndereco;

public record DadosCadastroFuncionario(Long id, String nome, String cpf, String telefone, String email, String funcao,
		Double salario, String login, String senha, DadosEndereco endereco) {

}
