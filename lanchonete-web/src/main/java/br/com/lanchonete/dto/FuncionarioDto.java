package br.com.lanchonete.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDto implements Serializable  {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private String funcao;
	private Double salario;
	private String login;
	private String senha;
	private EnderecoDto endereco = new EnderecoDto();
}