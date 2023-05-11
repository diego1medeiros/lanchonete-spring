package br.com.lanchonete.dto;

import br.com.lanchonete.entity.Cliente;
import br.com.lanchonete.entity.Endereco;

public record DadosListagemCliente(Long id, String nome, String cpf, String telefone, String email,Endereco endereco) {

	 public DadosListagemCliente(Cliente cliente) {
	        this(cliente.getId(),cliente.getNome(),cliente.getCpf(), cliente.getTelefone(),cliente.getEmail(),  cliente.getEndereco());
	    }

}
