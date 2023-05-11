package br.com.lanchonete.dto.produtodto;

import br.com.lanchonete.dto.fornecedordto.DadosCadastroFornecedor;

public record DadosCadastroProduto(Long id, String nome,String caminhoImagem, String categoria, double preco, String codigoBarra,
		String codigoProduto, String descricao, int estoque,  DadosCadastroFornecedor fornecedor) {

}
