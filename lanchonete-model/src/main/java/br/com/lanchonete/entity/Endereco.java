package br.com.lanchonete.entity;

import br.com.lanchonete.dto.DadosEndereco;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {

	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private String numero;
	private String cep;

	public Endereco(DadosEndereco endereco) {
		this.rua = endereco.rua();
		this.bairro = endereco.bairro();
		this.cep = endereco.cep();
		this.estado = endereco.estado();
		this.cidade = endereco.cidade();
		this.numero = endereco.numero();

	}

	public void atualizarEndereco(DadosEndereco dadosEndereco) {
		this.rua = dadosEndereco.rua();
		this.bairro = dadosEndereco.bairro();
		this.cep = dadosEndereco.cep();
		this.estado = dadosEndereco.estado();
		this.cidade = dadosEndereco.cidade();
		this.numero = dadosEndereco.numero();
		
	}

	
}
