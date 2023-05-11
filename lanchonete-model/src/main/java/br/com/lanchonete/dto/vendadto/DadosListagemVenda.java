package br.com.lanchonete.dto.vendadto;

import java.util.Date;

import br.com.lanchonete.entity.Cliente;
import br.com.lanchonete.entity.TipoPagamento;
import br.com.lanchonete.entity.Venda;


public record DadosListagemVenda(Long id, Date data, double valorTotal, String observacao, int qtdeTotal,
		Cliente cliente, TipoPagamento tipoPagamento) {

	public DadosListagemVenda(Venda venda) {
		this(venda.getId(), venda.getData(), venda.getValorTotal(), venda.getObservacao(), venda.getQtdeTotal(),
				venda.getCliente(), venda.getTipoPagamento());
	}

}
