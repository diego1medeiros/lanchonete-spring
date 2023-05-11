package br.com.lanchonete.dto.movimentacaocaixa;

import java.util.Date;

import br.com.lanchonete.dto.funcionariodto.DadosCadastroFuncionario;

public record DadosMovimentacaoCaixa(Long id, String tipoMovimento, Date data, double valorTotal, double valorCaixa,
		String observacao, DadosCadastroFuncionario funcionario) {

}
