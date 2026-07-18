package br.com.lanchonete.service;

import java.util.List;

public interface ServiceImpl<LIST, CADASTRA, ATUALIZAR> {

    List<LIST> listarDados();

    LIST cadastrarDados(CADASTRA dados);

    LIST atualizarDados(ATUALIZAR dados);

    void excluirDados(Long id);
}
		


