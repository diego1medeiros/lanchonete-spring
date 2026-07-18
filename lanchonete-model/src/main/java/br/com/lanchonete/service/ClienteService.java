package br.com.lanchonete.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.lanchonete.dto.Clientedto.DadosAtualizarCliente;
import br.com.lanchonete.dto.Clientedto.DadosCadastroCliente;
import br.com.lanchonete.dto.Clientedto.DadosListagemCliente;
import br.com.lanchonete.entity.Cliente;
import br.com.lanchonete.repository.ClienteRepository;


@Service
public class ClienteService implements ServiceImpl<DadosListagemCliente, DadosCadastroCliente, DadosAtualizarCliente>{

	@Autowired
	private ClienteRepository repository;

	public List <DadosListagemCliente>listarDados() {
		var listarClientes = repository.findAll().stream().map(DadosListagemCliente::new).toList();
		return listarClientes;
		
	}
	@Override
	public DadosListagemCliente cadastrarDados(DadosCadastroCliente dados) {
		Cliente cliente = new Cliente(dados);
		repository.save(cliente);
		return new DadosListagemCliente(cliente);
	}
	@Override
	public void excluirDados(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public DadosListagemCliente atualizarDados(DadosAtualizarCliente dados) {
		var cliente = repository.getReferenceById(dados.id());
		cliente.atualizarCliente(dados);
		return new DadosListagemCliente(cliente);



	
}
}
