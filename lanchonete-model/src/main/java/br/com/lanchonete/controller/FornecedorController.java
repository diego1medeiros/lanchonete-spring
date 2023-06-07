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

import br.com.lanchonete.dto.fornecedordto.DadosCadastroFornecedor;
import br.com.lanchonete.dto.fornecedordto.DadosListagemFornecedor;
import br.com.lanchonete.entity.Fornecedor;
import br.com.lanchonete.repository.FornecedorRepository;

@RestController
@RequestMapping("/fornecedor/lanchonete")
public class FornecedorController {

	@Autowired
	private FornecedorRepository repository;
	
	@GetMapping
	public ResponseEntity<?> listarFornecedores() {
		var listarFornecedores = repository.findAll().stream().map(DadosListagemFornecedor::new).toList();
		return ResponseEntity.ok(listarFornecedores);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> excluirFornecedor(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	@PutMapping
	public ResponseEntity<?> atualizarFornecedor(@RequestBody DadosCadastroFornecedor dadosFornecedor) {
		var fornecedor = repository.getReferenceById(dadosFornecedor.id());
		fornecedor.atualizarFornecedor(dadosFornecedor);
		return ResponseEntity.ok(new DadosListagemFornecedor(fornecedor));
	}

	@PostMapping
	public ResponseEntity<?> cadastrarFornecedor(@RequestBody DadosCadastroFornecedor dadosFornecedor,
			UriComponentsBuilder uriComponentsBuilder) {
		Fornecedor fornecedor = new Fornecedor(dadosFornecedor);
		repository.save(fornecedor);
		URI uri = uriComponentsBuilder.path("/listarfornecedor/{id}").buildAndExpand(fornecedor.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListagemFornecedor(fornecedor));
	}
}
