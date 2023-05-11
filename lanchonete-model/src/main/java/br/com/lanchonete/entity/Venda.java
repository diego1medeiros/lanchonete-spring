package br.com.lanchonete.entity;


import java.io.Serializable;
import java.util.Date;

import br.com.lanchonete.dto.vendadto.DadosCadastroVenda;
import jakarta.persistence.CascadeType;
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
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venda")
	private Long id;
	private Date data;
	@Column(name = "valor_total")
	private double valorTotal;
	private String observacao;
	@Column(name = "qtde_total")
	private int qtdeTotal;
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente =new Cliente();
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_tipo_pagamento")
	private TipoPagamento tipoPagamento = new TipoPagamento();

	public Venda(DadosCadastroVenda dadosVenda) {
		this.id = dadosVenda.id();
		this.data = dadosVenda.data();
		this.valorTotal = dadosVenda.valorTotal();
		this.observacao = dadosVenda.observacao();
		this.qtdeTotal = dadosVenda.qtdeTotal();
		this.cliente.setId(dadosVenda.cliente().id());
		this.tipoPagamento.setCodigo(dadosVenda.tipoPagamento().codigo());
		this.getTipoPagamento().setDescricao(dadosVenda.tipoPagamento().descricao());


	}
}

