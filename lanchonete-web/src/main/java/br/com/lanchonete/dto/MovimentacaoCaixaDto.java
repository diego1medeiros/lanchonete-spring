package br.com.lanchonete.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoCaixaDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String tipoMovimento;
	private Date data;
	private double valorTotal;
	private double valorCaixa;
	private String observacao;
	private FuncionarioDto funcionario = new FuncionarioDto();
}
