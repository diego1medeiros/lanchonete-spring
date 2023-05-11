package br.com.lanchonete.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String categoria;
	private double preco;
	private String codigoBarra;
	private String codigoProduto;
	private String descricao;
	private int estoque ;
	private String caminhoImagem;
	private FornecedorDto fornecedor = new FornecedorDto();
	

}
