package br.com.lanchonete.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.lanchonete.dto.funcionariodto.DadosCadastroFuncionario;
import br.com.lanchonete.dto.funcionariodto.DadosListagemFuncionario;
import br.com.lanchonete.entity.Funcionario;
import br.com.lanchonete.repository.FuncionarioRepository;

@RestController
@RequestMapping("/lanchonete")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository repository;
	
	@GetMapping("/listarfuncionario")
	public ResponseEntity<?> listarFuncionarios() {
		var listarFuncionarios = repository.findAll().stream().map(DadosListagemFuncionario::new).toList();
		return ResponseEntity.ok(listarFuncionarios);
	}
	
	@PostMapping("/cadastrarfuncionario")
	public ResponseEntity<?> cadastrarFuncionario(@RequestBody DadosCadastroFuncionario dadosFuncionario,
			UriComponentsBuilder uriComponentsBuilder) {
		Funcionario funcionario = new Funcionario(dadosFuncionario);
		repository.save(funcionario);
		URI uri = uriComponentsBuilder.path("/listarfuncionario/{id}").buildAndExpand(funcionario.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListagemFuncionario(funcionario));
	}
	
	@Transactional
	@PutMapping("/atualizarfuncionario")
	public ResponseEntity<?> atualizarFuncionario(@RequestBody DadosCadastroFuncionario dadosFuncionario) {
		var funcionario = repository.getReferenceById(dadosFuncionario.id());
		funcionario.atualizarFuncionario(dadosFuncionario);
		return ResponseEntity.ok(new DadosListagemFuncionario(funcionario));
	}
	
	@DeleteMapping("/excluirfuncionario/{id}")
	public ResponseEntity<?> excluirFuncionario(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
