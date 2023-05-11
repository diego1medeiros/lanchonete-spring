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
public class FornecedorDto implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String razaoSocial;
	private String cnpj;
	private String telefone;
	private String email;
	private EnderecoDto endereco = new EnderecoDto();

}
