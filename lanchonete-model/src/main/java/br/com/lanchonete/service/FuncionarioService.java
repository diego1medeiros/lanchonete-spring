package br.com.lanchonete.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lanchonete.dto.Clientedto.DadosListagemCliente;
import br.com.lanchonete.dto.funcionariodto.DadosCadastroFuncionario;
import br.com.lanchonete.dto.funcionariodto.DadosListagemFuncionario;
import br.com.lanchonete.repository.FuncionarioRepository;
import br.com.lanchonete.entity.Funcionario;

@Service
public class FuncionarioService implements ServiceImpl<DadosListagemFuncionario, DadosCadastroFuncionario,DadosCadastroFuncionario> {

	@Autowired
	private FuncionarioRepository repository;
	
	@Override
	public List<DadosListagemFuncionario> listarDados() {
	var listarFuncionarios = repository.findAll().stream().map(DadosListagemFuncionario::new).toList();	
	return listarFuncionarios;	
	}

	@Override
	public DadosListagemFuncionario cadastrarDados(DadosCadastroFuncionario dados) {
		var  funcionario = new Funcionario(dados);
		repository.save(funcionario);
		return new DadosListagemFuncionario(funcionario);
	}
		

	@Override
	public DadosListagemFuncionario atualizarDados(DadosCadastroFuncionario dados) {
		var funcionario = repository.getReferenceById(dados.id());
		funcionario.atualizarFuncionario(dados);
		return new DadosListagemFuncionario(funcionario);

	}

	@Override
	public void excluirDados(Long id) {
		repository.deleteById(id);
	}

	



}
