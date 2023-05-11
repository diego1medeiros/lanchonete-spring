package br.com.lanchonete.dto.produtodto;

import br.com.lanchonete.entity.Fornecedor;
import br.com.lanchonete.entity.Produto;

public record DadosListagemProdutos(Long id, String nome, String categoria, double preco, String codigoBarra,
		String codigoProduto, String descricao, int estoque, String caminhoImagem,
		Fornecedor fornecedor) {

	public DadosListagemProdutos(Produto produto) {

		this(produto.getId(), produto.getNome(), produto.getCategoria(), produto.getPreco(), produto.getCodigoBarra(),
				produto.getCodigoProduto(), produto.getDescricao(), produto.getEstoque(),
				produto.getCaminhoImagem(), produto.getFornecedor());
	}

	

}
