package br.com.lanchonete.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class VendaDto implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date data;
	private double valorTotal;
	private String observacao;
	private int qtdeTotal;
	private List<ItemVendaDto> itensVenda;
	private ClienteDto cliente = new ClienteDto();
	private TipoPagamentoDto tipoPagamento = new TipoPagamentoDto();
}
