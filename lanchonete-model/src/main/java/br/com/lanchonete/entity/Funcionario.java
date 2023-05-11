package br.com.lanchonete.entity;

import br.com.lanchonete.dto.funcionariodto.DadosCadastroFuncionario;
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
public class Funcionario {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Long id;
		private String nome;
		private String cpf;
		private String telefone;
		private String email;
		private String funcao;
		private Double salario;
		private String login;
		private String senha;
		
		@Embedded
		private Endereco endereco;
		
		public Funcionario(DadosCadastroFuncionario dadosFuncionario) {
			this.nome = dadosFuncionario.nome();
			this.email = dadosFuncionario.email();
			this.telefone = dadosFuncionario.telefone();
			this.cpf = dadosFuncionario.cpf();
			this.funcao = dadosFuncionario.funcao();
			this.salario = dadosFuncionario.salario();
			this.login = dadosFuncionario.login();
			this.senha = dadosFuncionario.senha();
			this.endereco = new Endereco(dadosFuncionario.endereco());

			
		}

		public void atualizarFuncionario(DadosCadastroFuncionario dadosFuncionario) {
			this.nome = dadosFuncionario.nome();
			this.email = dadosFuncionario.email();
			this.telefone = dadosFuncionario.telefone();
			this.cpf = dadosFuncionario.cpf();
			this.funcao = dadosFuncionario.funcao();
			this.salario = dadosFuncionario.salario();
			this.login = dadosFuncionario.login();
			this.senha = dadosFuncionario.senha();
			this.endereco = new Endereco(dadosFuncionario.endereco());
			
		}
}
