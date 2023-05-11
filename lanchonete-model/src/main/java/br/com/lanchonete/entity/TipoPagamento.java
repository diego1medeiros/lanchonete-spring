package br.com.lanchonete.entity;

import java.io.Serializable;

import br.com.lanchonete.dto.vendadto.DadosCadastroPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "tipopagamento")
public class TipoPagamento implements Serializable{

	public TipoPagamento(DadosCadastroPagamento tipoPagamento) {
		this.codigo = tipoPagamento.codigo();
		this.descricao = tipoPagamento.descricao();
	}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String descricao;

}
