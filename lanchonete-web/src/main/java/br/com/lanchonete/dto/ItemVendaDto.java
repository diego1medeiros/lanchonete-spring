package br.com.lanchonete.dto;


import java.io.Serializable;
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
public class ItemVendaDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private int qtde;
	private double valorTotal;
	private ProdutoDto produto = new ProdutoDto();
	private List<?> itensVenda;
	private VendaDto venda = new VendaDto();
}
