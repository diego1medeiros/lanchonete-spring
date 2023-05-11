package br.com.lanchonete.entity;

import java.util.Date;

import br.com.lanchonete.dto.movimentacaocaixa.DadosMovimentacaoCaixa;
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
public class Caixa{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "tipomovimento")
	private String tipoMovimento;
	private Date data;
	@Column(name = "valor_total")
	private double valorTotal;
	@Column(name = "valor_caixa")
	private double valorCaixa;
	private String observacao;
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario = new Funcionario();

	public Caixa(DadosMovimentacaoCaixa dadosMovimentacaoCaixa) {
		this.id = dadosMovimentacaoCaixa.id();
		this.tipoMovimento = dadosMovimentacaoCaixa.tipoMovimento();
		this.data = dadosMovimentacaoCaixa.data();
		this.valorTotal = dadosMovimentacaoCaixa.valorTotal();
		this.valorCaixa = dadosMovimentacaoCaixa.valorCaixa();
		this.observacao = dadosMovimentacaoCaixa.observacao();
		this.funcionario.setId(dadosMovimentacaoCaixa.funcionario().id());
	}
	
}
