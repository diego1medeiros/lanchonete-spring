package br.com.lanchonete.entity;

import br.com.lanchonete.dto.Clientedto.DadosAtualizarCliente;
import br.com.lanchonete.dto.Clientedto.DadosCadastroCliente;
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
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	
	@Embedded
	private Endereco endereco;

	public Cliente(DadosCadastroCliente dadosCliente) {
		this.nome = dadosCliente.nome();
		this.email = dadosCliente.email();
		this.telefone = dadosCliente.telefone();
		this.cpf = dadosCliente.cpf();
		this.endereco = new Endereco(dadosCliente.endereco());

	}

	public void atualizarCliente(DadosAtualizarCliente dadosCliente) {
		this.nome = dadosCliente.nome();
		this.email = dadosCliente.email();
		this.telefone = dadosCliente.telefone();
		this.cpf = dadosCliente.cpf();
		this.endereco.atualizarEndereco(dadosCliente.endereco());
	}

	
	
	
	
}
