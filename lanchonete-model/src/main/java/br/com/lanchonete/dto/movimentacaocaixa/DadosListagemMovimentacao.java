package br.com.lanchonete.dto.movimentacaocaixa;

import java.util.Date;

import br.com.lanchonete.entity.Caixa;
import br.com.lanchonete.entity.Funcionario;

public record DadosListagemMovimentacao(Long id, String tipoMovimento, Date data, double valorTotal, double valorCaixa,
		String observacao, Funcionario funcionario) {

	public DadosListagemMovimentacao(Caixa movimentacaoCaixa) {
		this(movimentacaoCaixa.getId(), movimentacaoCaixa.getTipoMovimento(), movimentacaoCaixa.getData(),
				movimentacaoCaixa.getValorCaixa(), movimentacaoCaixa.getValorCaixa(),movimentacaoCaixa.getObservacao(),
				movimentacaoCaixa.getFuncionario());
	}
}
