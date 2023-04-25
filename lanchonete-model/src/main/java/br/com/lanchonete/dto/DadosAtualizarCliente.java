package br.com.lanchonete.dto;

public record DadosAtualizarCliente(Long id, String nome, String cpf, String telefone, String email,DadosEndereco endereco) {

}
