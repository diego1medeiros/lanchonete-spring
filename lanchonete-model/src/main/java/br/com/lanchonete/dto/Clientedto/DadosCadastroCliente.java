package br.com.lanchonete.dto.Clientedto;

import br.com.lanchonete.dto.Enderecodto.DadosEndereco;

public record DadosCadastroCliente(Long id, String nome, String cpf, String telefone, String email,
		DadosEndereco endereco) {

}
