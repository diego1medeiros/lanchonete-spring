package br.com.lanchonete.dto.fornecedordto;

import br.com.lanchonete.entity.Endereco;
import br.com.lanchonete.entity.Fornecedor;

public record DadosListagemFornecedor(Long id, String razaoSocial, String cnpj, String telefone, String email,
		Endereco endereco) {

	public DadosListagemFornecedor(Fornecedor fornecedor) {
		this(fornecedor.getId(), fornecedor.getRazaoSocial(), fornecedor.getCnpj(), fornecedor.getTelefone(),
				fornecedor.getEmail(), fornecedor.getEndereco());
	}
}
