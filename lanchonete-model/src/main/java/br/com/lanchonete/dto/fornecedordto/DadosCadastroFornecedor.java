package br.com.lanchonete.dto.fornecedordto;

import br.com.lanchonete.dto.Enderecodto.DadosEndereco;

public record DadosCadastroFornecedor(Long id, String razaoSocial, String cnpj, String telefone, String email,
		DadosEndereco endereco) {

}
