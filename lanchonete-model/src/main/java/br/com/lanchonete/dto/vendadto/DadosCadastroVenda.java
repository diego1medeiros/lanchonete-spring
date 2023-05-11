package br.com.lanchonete.dto.vendadto;

import java.util.Date;
import java.util.List;

import br.com.lanchonete.dto.Clientedto.DadosCadastroCliente;
import br.com.lanchonete.entity.ItemVenda;

public record DadosCadastroVenda(Long id, Date data, double valorTotal, String observacao, int qtdeTotal,List<ItemVenda> itensVenda, 
		DadosCadastroCliente cliente, DadosCadastroPagamento tipoPagamento) {

}
