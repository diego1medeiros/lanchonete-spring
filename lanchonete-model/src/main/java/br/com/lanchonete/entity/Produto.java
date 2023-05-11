package br.com.lanchonete.entity;

import br.com.lanchonete.dto.produtodto.DadosCadastroProduto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Produto {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Long id;
		private String nome;
		private String categoria;
		private double preco;
		@Column(name = "codigo_barra")
		private String codigoBarra;
		@Column(name = "codigo_produto")
		private String codigoProduto;
		private String descricao;
		@Column(name = "qtde_estoque")
		private int estoque ;
		@Column(name = "caminho_imagem")
		private String caminhoImagem;
		@ManyToOne
		@JoinColumn(name = "id_fornecedor")
		private Fornecedor fornecedor = new Fornecedor();
		
		public Produto(DadosCadastroProduto dadosProduto) {
			super();
			this.nome = dadosProduto.nome();
			this.categoria = dadosProduto.categoria();
			this.preco = dadosProduto.preco();
			this.codigoBarra = dadosProduto.codigoBarra();
			this.codigoProduto = dadosProduto.codigoProduto();
			this.descricao = dadosProduto.descricao();
			this.estoque = dadosProduto.estoque();
			this.caminhoImagem = dadosProduto.caminhoImagem();
			this.fornecedor.setId(dadosProduto.fornecedor().id());
		}

		public void atualizarProduto(DadosCadastroProduto dadosProduto) {
			this.nome = dadosProduto.nome();
			this.categoria = dadosProduto.categoria();
			this.preco = dadosProduto.preco();
			this.codigoBarra = dadosProduto.codigoBarra();
			this.codigoProduto = dadosProduto.codigoProduto();
			this.descricao = dadosProduto.descricao();
			this.estoque = dadosProduto.estoque();
			this.caminhoImagem = dadosProduto.caminhoImagem();
			this.fornecedor.setId(dadosProduto.fornecedor().id());
		}
		}
		

