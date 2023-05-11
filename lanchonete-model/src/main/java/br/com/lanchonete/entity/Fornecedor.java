package br.com.lanchonete.entity;

import br.com.lanchonete.dto.fornecedordto.DadosCadastroFornecedor;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "razao_social")
	private String razaoSocial;
	private String cnpj;
	private String telefone;
	private String email;
	@Embedded
	private Endereco endereco;

	public Fornecedor(DadosCadastroFornecedor dadosFornecedor) {
		this.razaoSocial = dadosFornecedor.razaoSocial();
		this.cnpj = dadosFornecedor.cnpj();
		this.telefone = dadosFornecedor.telefone();
		this.email = dadosFornecedor.email();
		this.endereco = new Endereco(dadosFornecedor.endereco());
	}

	public void atualizarFornecedor(DadosCadastroFornecedor dadosFornecedor) {
		this.razaoSocial = dadosFornecedor.razaoSocial();
		this.cnpj = dadosFornecedor.cnpj();
		this.telefone = dadosFornecedor.telefone();
		this.email = dadosFornecedor.email();
		this.endereco = new Endereco(dadosFornecedor.endereco());

	}
	
	


}
