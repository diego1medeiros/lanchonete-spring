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
import br.com.lanchonete.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario/lanchonete")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@GetMapping
	public ResponseEntity<?> listarFuncionarios() {
		var listarFuncionarios = service.listarDados();
		return ResponseEntity.ok(listarFuncionarios);
	}

	@PostMapping
	public ResponseEntity<?> cadastrarFuncionario(@RequestBody DadosCadastroFuncionario dadosFuncionario,
			UriComponentsBuilder uriComponentsBuilder) {
		DadosListagemFuncionario funcionario = service.cadastrarDados(dadosFuncionario);
		URI uri = uriComponentsBuilder.path("/listarfuncionario/{id}").buildAndExpand(funcionario.id()).toUri();
		return ResponseEntity.created(uri).body(funcionario);
	}

	@Transactional
	@PutMapping
	public ResponseEntity<?> atualizarFuncionario(@RequestBody DadosCadastroFuncionario dadosFuncionario) {
		return ResponseEntity.ok(service.atualizarDados(dadosFuncionario));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirFuncionario(@PathVariable Long id) {
		service.excluirDados(id);
		return ResponseEntity.noContent().build();
	}

}
